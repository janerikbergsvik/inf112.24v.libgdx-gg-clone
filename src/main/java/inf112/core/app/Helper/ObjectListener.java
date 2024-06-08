package inf112.core.app.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import inf112.core.app.Characters.IEnemy;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.PopUpItems.IPopUpItem;
import inf112.core.app.TileObjects.ITileObject;
import inf112.core.app.TileObjects.TileObject;

/**
 * The listener interface for receiving object events.
 * The class that is interested in processing a object
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addObjectListener</code> method. When
 * the object event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ObjectEvent
 */
public class ObjectListener implements ContactListener {

	/**
	 * Identify fixture corresponding to bodypart.
	 *
	 * @param contact the contact
	 * @param bodyPart the body part
	 * @return the fixture corresponding to body part
	 */
	private Fixture identifyFixture(Contact contact, String bodyPart){
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		Fixture object = null;

		if (fixtureA.getUserData() == bodyPart) {
			object = fixtureB;
		}
		
		else if (fixtureB.getUserData() == bodyPart) {
			object = fixtureA;
		}
		
		return object;
	}
		
	@Override
	public void beginContact(Contact contact) {						
		checkHeadAndFeetContact(contact);	
		checkEntityContacts(contact); 		
	}

	/**
	 * Check entity contacts.
	 *
	 * @param contact the contact
	 */
	private void checkEntityContacts(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		
		// Identify contact between fixtures.
		int contactDef = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;		
		
		// Identify contact between bits.
		try {
	
		switch(contactDef){		
			// If contact between finish game object and giana body.
			case IGameModel.FINISH_GAME_BIT | IGameModel.GIANA_BIT:
				if(fixtureA.getFilterData().categoryBits == IGameModel.FINISH_GAME_BIT)
					((ITileObject)fixtureA.getUserData()).onPlayerContact();
				else
					((ITileObject)fixtureB.getUserData()).onPlayerContact();
				break;
		 
			// If contact between giana body and coin
			case IGameModel.COIN_BIT | IGameModel.GIANA_BIT:
				if(fixtureA.getFilterData().categoryBits == IGameModel.COIN_BIT)
					((ITileObject)fixtureA.getUserData()).onPlayerContact();
				else
					((ITileObject)fixtureB.getUserData()).onPlayerContact();
				break;
	
			// If contact between giana and enemy head, remove enemy
			case IGameModel.ENEMY_HEAD_BIT | IGameModel.GIANA_BIT:
				if(fixtureA.getFilterData().categoryBits == IGameModel.ENEMY_HEAD_BIT)
					((IEnemy)fixtureA.getUserData()).hitOnHead();
				else
					((IEnemy)fixtureB.getUserData()).hitOnHead();
				break;
				
			// If contact between enemy and wall, turn around
			case IGameModel.ENEMY_BIT | IGameModel.OBJECT_BIT:
				if(fixtureA.getFilterData().categoryBits == IGameModel.ENEMY_BIT)
					((IEnemy)fixtureA.getUserData()).reverseVelocity(true, false);
				else
					((IEnemy)fixtureB.getUserData()).reverseVelocity(true, false);
				break;
		
				// If contact between giana and enemy, giana died
				case IGameModel.GIANA_BIT | IGameModel.ENEMY_BIT:
					if(fixtureA.getFilterData().categoryBits == IGameModel.GIANA_BIT) {
						int damage = ((IEnemy)fixtureB.getUserData()).getDamage();
						((IPlayer)fixtureA.getUserData()).applyHealthChange(damage);
						((IEnemy)fixtureB.getUserData()).reverseVelocity(true, false);
					}
					else {
						int damage = ((IEnemy)fixtureA.getUserData()).getDamage();
						((IPlayer)fixtureB.getUserData()).applyHealthChange(damage);
						((IEnemy)fixtureA.getUserData()).reverseVelocity(true, false);
					}
					Gdx.app.log("Giana got damaged.", "");				
					break;
				
			// If contact between enemy and enemy, turn both around
			case IGameModel.ENEMY_BIT | IGameModel.ENEMY_BIT:
					((IEnemy)fixtureA.getUserData()).reverseVelocity(true, false);
					((IEnemy)fixtureB.getUserData()).reverseVelocity(true, false);
					break;
	
			// If contact between enemy and bullet.
			case IGameModel.ENEMY_BIT | IGameModel.BULLET_BIT:
					if(fixtureA.getFilterData().categoryBits == IGameModel.ENEMY_BIT)
						((IEnemy)fixtureA.getUserData()).hitOnHead();
					else
						((IEnemy)fixtureB.getUserData()).hitOnHead();
					break;
			
			// If contact between popupitem and objects, turn around
			case IGameModel.POPUP_ITEM_BIT | IGameModel.OBJECT_BIT:
					if(fixtureA.getFilterData().categoryBits == IGameModel.POPUP_ITEM_BIT)
						((IPopUpItem)fixtureA.getUserData()).reverseVelocity(true, false);
					else
						((IPopUpItem)fixtureB.getUserData()).reverseVelocity(true, false);
					break;
	
			// If contact between popupitem and Giana sister, then pick-up item
			case IGameModel.POPUP_ITEM_BIT | IGameModel.GIANA_BIT:
					if(fixtureA.getFilterData().categoryBits == IGameModel.POPUP_ITEM_BIT)
						((IPopUpItem)fixtureA.getUserData()).use();
					else
						((IPopUpItem)fixtureB.getUserData()).use();
					break;
			
			default:
				Gdx.app.log("No contact identified.", "");
			}
		
	    } catch (Exception e) {
		      Gdx.app.log("Error in Objectlistener.java", "");
		    }
	}

	/**
	 * Check head and feet contact.
	 *
	 * @param contact the contact
	 */
	private void checkHeadAndFeetContact(Contact contact) {		
		Fixture object;
		// If contact between player hair and object.
		object = identifyFixture(contact, "hair");
		if(object != null && object.getUserData() != null && TileObject.class.isAssignableFrom(object.getUserData().getClass())) {
				((ITileObject) object.getUserData()).onHairContact();
		}	
		
		// If contact between player feet and object.
		object = identifyFixture(contact, "feet");
		if(object != null && object.getUserData() != null && TileObject.class.isAssignableFrom(object.getUserData().getClass())) {
				((ITileObject) object.getUserData()).onFeetContact();
			}
	}

	@Override
	public void endContact(Contact contact) {		
		Fixture object;
		
		// If contact between player feet and object ends.
		object = identifyFixture(contact, "feet");
		if(object != null && object.getUserData() != null && TileObject.class.isAssignableFrom(object.getUserData().getClass())) {
				((ITileObject) object.getUserData()).onFeetContactEnd();
			}			
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
}
