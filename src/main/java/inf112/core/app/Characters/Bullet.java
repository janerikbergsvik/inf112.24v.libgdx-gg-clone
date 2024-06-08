package inf112.core.app.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.MainGame;
import inf112.core.app.Model.IGameModel;

/**
 * The Class Bullet
 * It represents a projectile that a player can shoot.
 */
public class Bullet extends Sprite implements IProjectile{
    // World and character body
    private World world;
    private Body b2body;

    // Character textures
    private TextureRegion bulletRegion;     
	
    // Bullet positions and direction
    private float bulletX;
    private float bulletY;
    private float player_vel_X;
    private float player_vel_Y;
    private boolean bulletRight;
    
    // Controls if bullet is active in world or not, and if bullet is to be
    // removed from list of bullet instances. 
    private boolean deadWorldBullet;
    private boolean scheduledForRemoval;
    
    // Constructor parameters
    private float timeCounter;
	private IPlayer player;
    
    /**
     * Instantiates a new bullet.
     *
     * @param world the Boxd2 world
     * @param player the player
     */
    public Bullet(World world, IPlayer player){    	
    	// Set parameters
    	this.world = world;
    	this.player = player;

    	// Get player b2body location and velocity 
    	this.bulletX = player.getBody().getPosition().x;
    	this.player_vel_X = player.getBody().getLinearVelocity().x;
    	this.bulletY = player.getBody().getPosition().y;
    	this.player_vel_Y = player.getBody().getLinearVelocity().y;
    	this.bulletRight = player.runningRight();    	
    	
    	Texture bulletTexture = new Texture(Gdx.files.internal("textures/bullet.png"));
    	bulletRegion = new TextureRegion(bulletTexture, 0, 0, 10, 9);    	
        setRegion(bulletRegion);
        setBounds(bulletX, bulletY, 10 / MainGame.PPM, 9 / MainGame.PPM);
                    
        // Create Box2D bullet
        defineEntity();             
    }

    @Override
	public void update(float dt){
    		// Bullet time counter    	
    		timeCounter = timeCounter + dt;
    	
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            if((timeCounter > 4.0f || deadWorldBullet) && !scheduledForRemoval) {
                world.destroyBody(b2body);
                scheduledForRemoval = true;
            }
            if(b2body.getLinearVelocity().y > 1.5f)
                b2body.setLinearVelocity(b2body.getLinearVelocity().x+player_vel_X, 1.5f);
            
            if((bulletRight && b2body.getLinearVelocity().x < 0) || (!bulletRight && b2body.getLinearVelocity().x > 0))
            	deadWorldBullet = true;
        }
    
    @Override
	public boolean isScheduledForRemoval() {
        return scheduledForRemoval;        
    }
        
    @Override
	public void defineEntity(){
    	// Make Box2d shape of player.
        BodyDef bdef = new BodyDef();
        
        // Report where it travels        
        Gdx.app.log("Bullet should travel right?", String.valueOf(bulletRight));
        
        // Set bullet position at side of player body.                
        bdef.position.set(bulletRight ? bulletX + 12 / MainGame.PPM : bulletX - 12 / MainGame.PPM, bulletY);       
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);        
        b2body.setBullet(true);

        // Bullet fixture
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(3 / MainGame.PPM);
        
        // Set collision bits
        fdef.filter.categoryBits = IGameModel.BULLET_BIT;
        fdef.filter.maskBits = IGameModel.COIN_BIT | IGameModel.POWERUP_BIT | IGameModel.GROUND_BIT | IGameModel.EMPTY_BIT | IGameModel.OBJECT_BIT | IGameModel.ENEMY_BIT;        
        fdef.restitution = 1;
        fdef.friction = 0;
        fdef.shape = shape;        
        // fdef.isSensor = true;
        
        // Set name of user data.                
        b2body.createFixture(fdef).setUserData("bullet");
        b2body.setLinearVelocity(new Vector2(bulletRight ? 7.5f : -7.5f, 2.5f));                
    }

	@Override
	public void draw(Batch batch) {
		super.draw(batch);
		
	}
}
