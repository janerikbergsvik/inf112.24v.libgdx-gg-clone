package inf112.core.app.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class Coin.
 */
public class Coin extends TileObject{	
    
    /**
     * Instantiates a new coin.
     *
     * @param hud the hud
     * @param player the player
     * @param world the box2d world
     * @param map the map
     * @param audio the audio
     * @param bounds the bounds
     */
    public Coin(IHud hud, IPlayer player, World world, TiledMap map, IAudio audio, Rectangle bounds){
    	super(hud, player, world, map, audio, bounds);
        fixture.setUserData(this);
        setCollisionCategory(IGameModel.COIN_BIT);
        
        // Set score to value for coin.
        score = 200;
    }

	@Override
	public void onHairContact() {
		Gdx.app.log("Coin-Hair", "Contact");
	}

	@Override
	public void onPlayerContact() {
		setCollisionCategory(IGameModel.COLLAPSED_BIT);

		TiledMapTileLayer.Cell cell = getCell();
		if (cell != null) {
			getCell().setTile(null);	
			audio.playPickUp();
			hud.changeScore(getScore());
			Gdx.app.log("Coin-Player", "Contact");
		}
	}

	@Override
	public void onFeetContact() {
		player.setGroundContact(true);
		Gdx.app.log("Coin-Feet", "Contact");
	}

	@Override
	public void onFeetContactEnd() {
		player.setGroundContact(false);		
		Gdx.app.log("Ground-Feet", "Contact ended.");		
	}
}
