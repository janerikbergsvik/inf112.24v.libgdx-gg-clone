package inf112.core.app.Helper;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.core.app.MainGame;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.TileObjects.ITileObject;


class ObjectListenerTest {
	// Variables
	ObjectListener listener;
	
	/**
	 * Static method run before everything else
	 */
	@BeforeAll
	static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
    	Gdx.gl = Gdx.gl20 = mock(GL20.class);
    
        ApplicationListener listener = new ApplicationListener() {

			@Override
			public void create() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void resize(int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void render() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void pause() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void resume() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}};
        new HeadlessApplication(listener, config);
        }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
		listener = new ObjectListener();		
	}

	/**
	 * On player contact, dummy method used for testing purposes
	 */
	public void onPlayerContact() {		
	}
	
	/**
	 * Test that checks that the ObjectListener works as intended.
	 * Here limited to test only a given contact, but may be extended
	 * in the same way.
	 */
	@Test
	void objectListenerTest() {
		// Pixel to metre has to be set.
		MainGame.PPM = 10;
		
		// Init. body variables.
	    BodyDef bdef = new BodyDef();
	    bdef.position.set(752 / MainGame.PPM, 112 / MainGame.PPM);
	    bdef.type = BodyDef.BodyType.DynamicBody;
	    
	    // Init. world and body inside world.
	    World world = new World(new Vector2(0,-9.81f), true);
	    Body b2body = world.createBody(bdef);
	    
	    // Make fixture.
	    FixtureDef fdef = new FixtureDef();
	    CircleShape shape = new CircleShape();
	    shape.setRadius(12/ MainGame.PPM);
	    
	    // Set filter maskBits
	    fdef.filter.maskBits = IGameModel.OBJECT_BIT | IGameModel.COIN_BIT | IGameModel.POWERUP_BIT | IGameModel.GROUND_BIT;
	    fdef.shape = shape;
	  
	    // Mock onPlayerContact method when contact
	    // occurs.
	    ITileObject obj1 = mock(ITileObject.class);
	    ITileObject obj2 = mock(ITileObject.class);
	    b2body.createFixture(fdef).setUserData(obj1);
	    b2body.createFixture(fdef).setUserData(obj2);
	    doNothing().when(obj1).onPlayerContact();	       
	    doNothing().when(obj2).onPlayerContact();	       
		       
	    // Set categorybits on filters, such that contact occurs between wanted
	    // objects given below. GIANA_BIT & COIN_BIT.
	    // This is just an example that could have been extended to cover every part of
	    // switch statement.
	    Array<Fixture> fixtures = b2body.getFixtureList();		
		Fixture fixtureA = fixtures.get(0);
		Fixture fixtureB = fixtures.get(1);
		System.out.println(fixtureA.getUserData());
		Filter filterA = spy(new Filter());		
		Filter filterB = spy(new Filter());
		filterB.categoryBits = IGameModel.GIANA_BIT;
		filterA.categoryBits = IGameModel.COIN_BIT;
		
		fixtureA.setFilterData(filterA);
		fixtureB.setFilterData(filterB);

		Contact contact = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		// Test contact.
		listener.beginContact(contact);
	}
	
}

