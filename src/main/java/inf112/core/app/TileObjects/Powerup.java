package inf112.core.app.TileObjects;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.PopUpItems.Ball;
import inf112.core.app.PopUpItems.Clock;
import inf112.core.app.PopUpItems.DoubleLightning;
import inf112.core.app.PopUpItems.PopUpFactoryDef;
import inf112.core.app.PopUpItems.SingleLightning;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class Powerup.
 */
public class Powerup extends TileObject{
    private static TiledMapTileSet powerUpTileSet; 
    private boolean emptyBlock;
	private Integer timer;
	private LinkedBlockingQueue<PopUpFactoryDef> itemsToSpawn;
    
    /**
     * Instantiates a new powerup.
     *
     * @param itemsToSpawn the items to spawn
     * @param hud the hud
     * @param player the player
     * @param world the box2d world
     * @param map the map
     * @param audio the audio
     * @param bounds the bounds
     */
    public Powerup(LinkedBlockingQueue<PopUpFactoryDef> itemsToSpawn, IHud hud, IPlayer player, World world, TiledMap map, IAudio audio, Rectangle bounds){
        super(hud, player, world, map, audio, bounds);                	
    	this.itemsToSpawn = itemsToSpawn;        
        
        emptyBlock = false;
        fixture.setUserData(this);
        setCollisionCategory(IGameModel.POWERUP_BIT);
        powerUpTileSet = map.getTileSets().getTileSet("wall_style_2");    
        }

	@Override
	public void onHairContact() {
		setCollisionCategory(IGameModel.EMPTY_BIT);
		TiledMapTileLayer.Cell cell = getCell();
		if (cell != null && !emptyBlock) {					   
		    getCell().setTile(powerUpTileSet.getTile(getCell().getTile().getId()+4));
		    
		}
		Gdx.app.log("Powerup-Hair", "Contact");

		if (!emptyBlock) {
			// It is now empty.
			emptyBlock = true;
					    
			// Return random power-up
			itemsToSpawn.add(new PopUpFactoryDef(new Vector2(body.getPosition().x, body.getPosition().y + 32 / MainGame.PPM), getRandomPowerUp()));	
		}
	}	
	
	/**
	 * Gets the random power up.
	 *
	 * @return the random power up
	 */
	private Class <?> getRandomPowerUp() {
	    Random randomizer = new Random();
	    ArrayList<Class <?>> classList = new ArrayList<>();
	    
	    classList.add(SingleLightning.class);
	    classList.add(DoubleLightning.class);
	    classList.add(Clock.class);
	    classList.add(Ball.class);
	    
        int index = randomizer.nextInt(classList.size());
        
        return classList.get(index);
	}

	@Override
	public void onPlayerContact() {
		player.setGroundContact(true);
		Gdx.app.log("Powerup-Player", "Contact");
	}

	@Override
	public void onFeetContact() {
		Gdx.app.log("Powerup-Feet", "Contact");
	}

	@Override
	public void onFeetContactEnd() {
		player.setGroundContact(false);		
		Gdx.app.log("Ground-Feet", "Contact ended.");		
	}

	/**
	 * Gets the current timer.
	 *
	 * @return the timer in seconds
	 */
	public Integer getSeconds(){
		return this.timer;
	}
}
