package inf112.core.app.Characters;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.MainGame;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class Enemy.
 * Contains common methods for a enemy.
 */
public abstract class Enemy extends Sprite implements IEnemy{
    
    protected World world;
    protected IGameModel model;
    protected Body b2body;
    protected Vector2 velocity;
    private int damage = -1;
    protected boolean scheduledForRemoval;
    protected boolean deadWorldEnemy;
    protected float stateTime;
    protected TextureAtlas atlas;
    protected IHud hud;
    
    /**
     * Instantiates a new enemy.
     *
     * @param world the world
     * @param hud the hud
     * @param atlas the atlas
     * @param x the x
     * @param y the y
     */
    public Enemy(World world, IHud hud, TextureAtlas atlas, float x, float y){
        this.world = world;
        this.hud = hud;
        this.atlas = atlas;
        setPosition(x, y);
        defineEntity();
    }

    /**
     * Define entity.
     */
    public void defineEntity() {
        // Make Box2d shape.
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        // Enemy fixture
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(12/ MainGame.PPM);
        
        // Set collision bits
        // Please note how GameModel.COLLAPSED_BIT is not part of objects that enemy will collide with.
        // This is key to collapsing objects.
        fdef.filter.categoryBits = IGameModel.ENEMY_BIT;
        fdef.filter.maskBits = IGameModel.OBJECT_BIT | IGameModel.COIN_BIT | IGameModel.POWERUP_BIT | IGameModel.GROUND_BIT | IGameModel.EMPTY_BIT | IGameModel.ENEMY_BIT | IGameModel.GIANA_BIT | IGameModel.BULLET_BIT;
        
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        // Head shape
        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-10, 16).scl(1/MainGame.PPM);
        vertice[1] = new Vector2(10, 16).scl(1/MainGame.PPM);
        vertice[2] = new Vector2(-6, 6).scl(1/MainGame.PPM);
        vertice[3] = new Vector2(6, 6).scl(1/MainGame.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = IGameModel.ENEMY_HEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    @Override
	public abstract void hitOnHead();

    @Override
	public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }

    @Override
	public abstract void update(float delta);

    @Override
	public int getDamage() {
    	return damage;
    }
    
    @Override
	public void draw(Batch batch){
        if(!deadWorldEnemy || stateTime < 1){
            super.draw(batch);
        }
    }
    
}
