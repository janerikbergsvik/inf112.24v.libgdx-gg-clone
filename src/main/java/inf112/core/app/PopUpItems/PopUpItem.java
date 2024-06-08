package inf112.core.app.PopUpItems;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Model.GameModel;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.View.GameScreens.IHud;
import inf112.core.app.View.GameScreens.PlayScreen;

/**
 * The Class PopUpItem.
 */

public abstract class PopUpItem extends Sprite implements IPopUpItem {
	protected PlayScreen screen;
	protected World world;
	protected Vector2 velocity;
	protected boolean scheduledForRemoval;
	protected boolean deadWorldPopUpItem;
	protected Body b2body;
	protected float stateTime;
    protected Animation<TextureRegion> itemAnimation;
    protected Array<TextureRegion> frames;
    protected GameModel model;
    protected int direction;
	protected IPlayer player;
	protected TextureAtlas powerups;
	protected IHud hud;
    
	/**
	 * Instantiates a new pop up item.
	 *
	 * @param player the player
	 * @param world the world
	 * @param hud the hud
	 * @param powerups the powerups texture atlas.
	 * @param x the x
	 * @param y the y
	 */
	public PopUpItem(IPlayer player, World world, IHud hud, TextureAtlas powerups, float x, float y) {
		this.player = player;
		this.world = world;
		this.hud = hud;
		this.powerups = powerups;	
		
		setPosition(x, y);
		setBounds(getX(), getY(), 32 / MainGame.PPM, 32 /MainGame.PPM);
		defineEntity();
		scheduledForRemoval = false;
		deadWorldPopUpItem = false;
		direction = getRandomDirection();
		velocity = new Vector2(direction*4f, -9.81f);
 	}

	@Override
	public abstract void use();
	
	@Override
	public void draw(Batch batch) {
		if(!deadWorldPopUpItem) {
			super.draw(batch);
		}
	}
	
	@Override
	public void destroy() {
		scheduledForRemoval = true;
	}
	
	@Override
	public void defineEntity() {
		// Make Box2d shape.
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        // Fixture
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(16/ MainGame.PPM);
       
        fdef.filter.categoryBits = IGameModel.POPUP_ITEM_BIT;
        fdef.filter.maskBits = IGameModel.OBJECT_BIT | IGameModel.COIN_BIT | IGameModel.POWERUP_BIT 
        		| IGameModel.EMPTY_BIT | IGameModel.GIANA_BIT | IGameModel.GROUND_BIT | IGameModel.ENEMY_BIT | IGameModel.ENEMY_HEAD_BIT | IGameModel.POPUP_ITEM_BIT; 
 
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);		
	}
	
	@Override
	public void update(float dt) {
		if (scheduledForRemoval && !deadWorldPopUpItem) {
			world.destroyBody(b2body);
			deadWorldPopUpItem = true;
		}
	}
	
    @Override
	public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
    

	@Override
	public int getRandomDirection() {
	    Random randomizer = new Random();
	    ArrayList<Integer> classList = new ArrayList<>();
	    
	    classList.add(1);
	    classList.add(-1);
	    
        int index = randomizer.nextInt(classList.size());
        
        return classList.get(index);
	}

}
