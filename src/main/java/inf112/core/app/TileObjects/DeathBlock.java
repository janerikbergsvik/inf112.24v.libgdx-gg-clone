package inf112.core.app.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class DeathBlock.
 */
public class DeathBlock extends TileObject{
	// Damage that DeatBlock causes.
	private int damage = -100;
	
    /**
     * Instantiates a new death block.
     *
     * @param hud the hud
     * @param player the player
     * @param world the box2d world
     * @param map the map
     * @param audio the audio
     * @param bounds the bounds
     */
    public DeathBlock(IHud hud, IPlayer player, World world, TiledMap map, IAudio audio, Rectangle bounds){
    	super(hud, player, world, map, audio, bounds);
        fixture.setUserData(this);
    }

	@Override
	public void onHairContact() {
		player.applyHealthChange(damage);		
		Gdx.app.log("Deathblock-Hair", "Contact");
	}

	@Override
	public void onPlayerContact() {
		player.applyHealthChange(damage);		
		Gdx.app.log("Deathblock-Player", "Contact");
	}

	@Override
	public void onFeetContact() {
		player.setGroundContact(true);		
		player.applyHealthChange(damage);		
		Gdx.app.log("DeathBlock-Feet", "Contact");
	}

	@Override
	public void onFeetContactEnd() {
		player.setGroundContact(false);		
		Gdx.app.log("Ground-Feet", "Contact ended.");		
	}
}
