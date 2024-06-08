package inf112.core.app.PopUpItems;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class DoubleLightning.
 */
public class DoubleLightning extends PopUpItem {
	private float timeout;
	
	/**
	 * Instantiates a new double lightning.
	 *
	 * @param player the player
	 * @param world the world
	 * @param hud the hud
	 * @param powerups the textureatlas for powerups
	 * @param x the x
	 * @param y the y
	 */
	public DoubleLightning(IPlayer player, World world, IHud hud, TextureAtlas powerups, float x, float y) {
		super(player, world, hud, powerups, x, y);								
	   
		frames = new Array<TextureRegion>();
        for(int i = 0; i < 3; i++){
            frames.add(new TextureRegion(powerups.findRegion("double_lightning"), i*32, 0, 32, 32));
               }
   
        itemAnimation = new Animation<>(0.2f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 32 / MainGame.PPM, 32 / MainGame.PPM);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		
        stateTime += dt;
        if(scheduledForRemoval && !deadWorldPopUpItem){
            world.destroyBody(b2body);
            deadWorldPopUpItem = true;
            setRegion(new TextureRegion(powerups.findRegion("double_lightning"), 32, 0, 32, 32));
            
            stateTime = 0;
        }
        else if(!deadWorldPopUpItem){
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            b2body.setLinearVelocity(velocity);
            setRegion(itemAnimation.getKeyFrame(stateTime, true));
        }     

        // Time out speed after a while
        timeout -= dt;
        player.setDoubleLightningTimeout(timeout);
        if(timeout <= 0) {
        	player.setSpeedFactor(1.0f);
        	timeout = 99999;
        }	
   }

	@Override
	public void use() {
		destroy();
		player.setSpeedFactor(1.5f);
		timeout = 20;
	}

	}
