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
 * The Class Ground.
 */
public class Ground extends TileObject{

    /**
     * Instantiates a new ground.
     *
     * @param hud the hud
     * @param player the player
     * @param world the box2d world
     * @param map the map
     * @param audio the audio
     * @param bounds the bounds
     */
    public Ground(IHud hud, IPlayer player, World world, TiledMap map, IAudio audio, Rectangle bounds){
    	super(hud, player, world, map, audio, bounds);   	    	
        fixture.setUserData(this);        
    }

	@Override
	public void onHairContact() {
		Gdx.app.log("Ground-Hair", "Contact");
	}

	@Override
	public void onPlayerContact() {
		Gdx.app.log("Ground-Player", "Contact");
	}

	@Override
	public void onFeetContact() {
		player.setGroundContact(true);		
		Gdx.app.log("Ground-Feet", "Contact");
	}

	@Override
	public void onFeetContactEnd() {
		player.setGroundContact(false);		
		Gdx.app.log("Ground-Feet", "Contact ended.");		
	}
}
