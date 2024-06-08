package inf112.core.app.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.core.app.MainGame;
import inf112.core.app.Model.IGameModel;

/**
 * The Class GianaPlayer.
 */
public class GianaPlayer extends Sprite implements IPlayer{
    
    /**
     * The Enum for player states.
     */
    public enum State {FALLING, JUMPING, STANDING, RUNNING};

    // States for player
    public State currentState;
    public State previousState;
    private float stateTimer;
    private boolean runningRight;
    private boolean groundContact;

    // World and character body
    public World world;
    private Body b2body;

    // Shoot delay counter
	float shootDelayCounter;
    float shootResetTimeDelay;       
    boolean allowedToShoot;
    boolean isCheatShootActive;
    
    // Character textures
    private TextureRegion gianaStand;
    private Animation<TextureRegion> gianaRun;
    private Animation<TextureRegion> gianaJump;	   
     
    // Player dead or alive
    private int health;
    
    // Player current speed
	private float v_factor = 1.0f;
	private final float speedx = 5.0f;
	private final float speedy = 8.4f;
	
    // Player has completed level
    boolean hasCompletedLevel;
    
    // Power up time_outs/bullet parameters
    float speedTimeout = 99999;
    float shootTimeout = 99999;
	private boolean isShootActive = false;
	private Array<IProjectile> bullets;
    
    /**
     * Instantiates a new giana player.
     *
     * @param world the world
     * @param atlas the atlas containing textures.
     * @param bullets the bullets
     */
    public GianaPlayer(World world, TextureAtlas atlas, Array<IProjectile> bullets){
    	// Load Giana textures.
    	super(atlas.findRegion("giana_sisters_scaled_transparent"));
        
    	// Get Box2D world.
    	this.world = world;
    	
    	// Get bullets
    	this.bullets = bullets;
    	
        // Set default state values
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        // Health defaults to 10.
        health = 10;
        
        // Player has completed level
        hasCompletedLevel = false;
        
        // Retrieve texture regions for player.
        getTextureRegions();

        // Create Box2D player.
        defineEntity();     
        
        // Create TextureRegion for standing Giana
        setBounds(0, 0, 32 / MainGame.PPM, 32 / MainGame.PPM);
        setRegion(gianaStand);
        
        // How often to allow player to shoot bullets                    
        shootResetTimeDelay = 0.3f;
        allowedToShoot = true;
        isCheatShootActive = false;
    }

	/**
	 * Gets the texture regions.
	 *
	 * @return the texture regions
	 */
	private void getTextureRegions() {
		// Get Giana run frames and add to animation
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 4; i++){
            frames.add(new TextureRegion(getTexture(), i*32-1, 64, 32, 32));
        }
        gianaRun = new Animation<>(0.1f, frames);
        frames.clear();

        // Get Giana jump frames and add to animation
        for(int i = 4; i < 6; i++){
            frames.add(new TextureRegion(getTexture(), i*32-1, 64, 32, 32));
        }
        gianaJump = new Animation<>(0.1f, frames);
        frames.clear();

        // Get Giana standing texture
        gianaStand = new TextureRegion(getTexture(), 0-1, 64, 32, 32);
	}

    @Override
	public boolean runningRight() {
    	return runningRight;
    }
  
    @Override
	public void update(float delta_t){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta_t));         

        // Bullet delay counter
        shootDelayCounter = shootDelayCounter + delta_t;
        if (shootDelayCounter >= shootResetTimeDelay) {
        	allowedToShoot = true;            
            shootDelayCounter = 0.0f;        
        }
    }

    @Override
	public State getState(){
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    @Override
	public TextureRegion getFrame(float delta_t){
        // Gets Giana's current state
        currentState = getState();

        TextureRegion region;

        // Gets the animation keyFrame for current state
        switch(currentState){
            case JUMPING:
                region = gianaJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = gianaRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = gianaStand;
                break;
        }

        // If player is running left, and texture is facing right, do flip it
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        // If player is running left, and texture is facing right, do flip it
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }
        
        // If currentstate equals previous state, increase state timer, else reset timer
        stateTimer = currentState == previousState ? stateTimer + delta_t : 0;
        previousState = currentState;
        return region;
    }

    @Override
	public void shoot(){			
    	try {
			if (allowedToShoot) {    	
    			if (bullets != null) 
    	    		bullets.add(new Bullet(world, this));
    			shootDelayCounter = 0.0f;						
    			allowedToShoot = false;
    		} }
    	
    	catch (Exception e) {
  		      Gdx.app.log("Error in GianaPlayer.class while shooting.", "");
  		    } 
	    }

    @Override
	public void defineEntity(){
    	// Make Box2d shape of player.
        BodyDef bdef = new BodyDef();
        bdef.position.set(752 / MainGame.PPM, 112 / MainGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        // Giana fixture
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(12/ MainGame.PPM);
        
        // Set collision bits
        // Please note how GameModel.COLLAPSED_BIT is not part of objects that Giana will collide with.
        // This is key to collapsing objects.
        fdef.filter.categoryBits = IGameModel.GIANA_BIT;
        fdef.filter.maskBits = IGameModel.OBJECT_BIT | IGameModel.COIN_BIT | IGameModel.POWERUP_BIT | IGameModel.GROUND_BIT 
        	| IGameModel.EMPTY_BIT | IGameModel.ENEMY_BIT | IGameModel.ENEMY_HEAD_BIT | IGameModel.POPUP_ITEM_BIT | IGameModel.FINISH_GAME_BIT;
                
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
        
        // Make Box2d shape above Giana head for contact modelling.
        EdgeShape hair = new EdgeShape();
        Vector2 from_pos = new Vector2(-8 / MainGame.PPM, (12 / MainGame.PPM));
        Vector2 to_pos = new Vector2(8 / MainGame.PPM, (12 / MainGame.PPM));
        
        // Set edge from v1 to v2.
        hair.set(from_pos, to_pos);      
        fdef.shape = hair;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("hair");

        // Make Box2d shape below Giana feet for contact modelling.
        EdgeShape feet = new EdgeShape();
        from_pos = new Vector2(-8 / MainGame.PPM, (-12 / MainGame.PPM));
        to_pos = new Vector2(8 / MainGame.PPM, (-12 / MainGame.PPM));
        
        // Set edge from v1 to v2.
        feet.set(from_pos, to_pos);      
        fdef.shape = feet;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("feet");
    }        

    @Override
	public void draw(Batch batch){
        super.draw(batch);
    }

	@Override
	public void setGroundContact(boolean groundContact) {
		this.groundContact = groundContact;		
	}    

	@Override
	public boolean getGroundContact() {
		return this.groundContact;			
	}    
	
	@Override
	public void applyHealthChange(int change) {
		health = health + change;
		if (health <= 0) {
			health = 0;
		}
	}
	
	@Override
	public boolean setCompletedLevel(boolean status) {
		hasCompletedLevel = status;
		return hasCompletedLevel;
	}
	
	@Override
	public boolean hasCompletedLevel() {
		return hasCompletedLevel; 
	}
	
	@Override
	public boolean isDead() {
		if (health <= 0) {
			return true;
		}
		
		return false;		
	}
	
	@Override
	public void setSpeedFactor(float factor) {
		v_factor = factor;
	}
	
	@Override
	public float getSpeedFactor() {
		return v_factor;
	}
	
	@Override
	public void setShootActive(boolean value) {
		isShootActive = value;		
	}
	
	@Override
	public boolean isShootActive() {
		return isShootActive;			
	}
	
	@Override
	public float getSpeedX() {
		return speedx;
	}

	@Override
	public float getSpeedY() {
		return speedy;
	}

	@Override
	public void setDoubleLightningTimeout(float timeout) {
		speedTimeout = timeout;
	}
	
	@Override
	public float getDoubleLightningTimeout() {
		return speedTimeout;
	}

	@Override
	public void setSingleLightningTimeout(float timeout) {
		shootTimeout = timeout;
	}

	@Override
	public float getSingleLightningTimeout() {
		return shootTimeout;
	}

	public Body getBody() {
		return b2body;
	}

	@Override
	public void moveLeft() {
		if (b2body.getLinearVelocity().x >= -2) {			
			b2body.applyLinearImpulse(new Vector2(-getSpeedX()*getSpeedFactor(), 0), b2body.getWorldCenter(), true);
		}
	}
	
	@Override
	public void moveRight() {
	   	if (b2body.getLinearVelocity().x <= 2) {
	   		b2body.applyLinearImpulse(new Vector2(getSpeedX()*getSpeedFactor(), 0), b2body.getWorldCenter(), true);        	
	   }
	}

	@Override
	public void moveUp() {
    	if (getGroundContact()) {
    		b2body.applyLinearImpulse(new Vector2(0,getSpeedY()*getSpeedFactor()), b2body.getWorldCenter(), true);	
    	}            		
	}

	@Override
	public void moveDown() {
     	if (getGroundContact()) {
     		b2body.setLinearVelocity(new Vector2(0,0));
    	}            
		
	}

	@Override
	public boolean isCheatShootActive() {
		return isCheatShootActive;
	}

	@Override
	public void setCheatShootActive(boolean value) {
		isCheatShootActive = value;		
	}	
}
