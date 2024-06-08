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
 * The Class FinishLine.
 */
public class FinishLine extends TileObject{

	/**
	 * Instantiates a new finish line.
	 *
	 * @param hud the hud
	 * @param player the player
	 * @param world the box2d world
	 * @param map the map
	 * @param audio the audio
	 * @param bounds the bounds
	 */
	public FinishLine(IHud hud, IPlayer player, World world, TiledMap map, IAudio audio, Rectangle bounds){
    	super(hud, player, world, map, audio, bounds);
        fixture.setUserData(this);
        setCollisionCategory(IGameModel.FINISH_GAME_BIT);
        }

	@Override
	public void onHairContact() {
		// Set completed level
		player.setCompletedLevel(true);
		audio.playCompletedLevel();
		Gdx.app.log("Finishline-Hair", "Contact");
	}

	@Override
	public void onPlayerContact() {
		// Set completed level
		player.setCompletedLevel(true);
		audio.playCompletedLevel();
		Gdx.app.log("Finishline-Player", "Contact");
	}

	@Override
	public void onFeetContact() {
		// Set completed level
		player.setCompletedLevel(true);
		audio.playCompletedLevel();
		player.setGroundContact(true);		
		Gdx.app.log("Finishline-Feet", "Contact");
	}

	@Override
	public void onFeetContactEnd() {
		player.setCompletedLevel(true);
		audio.playCompletedLevel();
		player.setGroundContact(false);		
		Gdx.app.log("Finishline-Feet", "Contact ended.");		
	}
}
