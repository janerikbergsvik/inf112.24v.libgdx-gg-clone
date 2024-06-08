package inf112.core.app.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.core.app.MainGame;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class Owl.
 */
public class Owl extends Enemy{

    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;
    
    /**
     * Instantiates a new owl.
     *
     * @param world the world
     * @param hud the hud
     * @param atlas the atlas
     * @param x the x
     * @param y the y
     */
    public Owl(World world, IHud hud, TextureAtlas atlas, float x, float y){    	
        super(world, hud, atlas, x, y);
        frames = new Array<TextureRegion>();
        for(int i = 0; i < 2; i++){
            frames.add(new TextureRegion(atlas.findRegion("enemies_scaled_transparent"), i*32, 0, 32, 32));
        }
        walkAnimation = new Animation<>(0.4f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 32 / MainGame.PPM, 32 / MainGame.PPM);
        scheduledForRemoval = false;
        deadWorldEnemy = false;
        velocity = new Vector2(1.7f, 0);
    }

    @Override
    public void update(float dt){
        stateTime += dt;
        if(scheduledForRemoval && !deadWorldEnemy){
            world.destroyBody(b2body);
            deadWorldEnemy = true;
            setRegion(new TextureRegion(atlas.findRegion("enemies_scaled_transparent"), 64, 0, 32, 32));
            stateTime = 0;
            hud.changeScore(400);
        }
        else if(!deadWorldEnemy){
            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion(walkAnimation.getKeyFrame(stateTime, true));
        }
    }
    
    @Override
    public void hitOnHead() {
        scheduledForRemoval = true;
    }
    
}
