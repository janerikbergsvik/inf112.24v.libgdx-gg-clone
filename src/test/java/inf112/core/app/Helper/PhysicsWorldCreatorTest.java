package inf112.core.app.Helper;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.GianaPlayer;
import inf112.core.app.Characters.IEnemy;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.TileObjects.Coin;
import inf112.core.app.TileObjects.ITileObject;
import inf112.core.app.View.GameScreens.Hud;
import inf112.core.app.View.GameScreens.IHud;
import inf112.core.app.PopUpItems.PopUpFactoryDef;

class PhysicsWorldCreatorTest {
	// Variables
	IPlayer player;		
	World world;
	TextureAtlas atlas;
	IHud hud;		
	ITileObject tileItem; 	
	TiledMap map;
	IAudio audio;
	Rectangle bounds;
	TmxMapLoader maploader;
	Rectangle rect;
	LinkedBlockingQueue<PopUpFactoryDef> itemsToSpawn;
	
	/**
	 * Static method run before everything else
	 */
	@BeforeAll
	static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
    	Gdx.gl = Gdx.gl20 = mock(GL20.class);
     
        ApplicationListener listener = new ApplicationListener() {

			@Override
			public void create() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void resize(int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void render() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void pause() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void resume() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}};
        new HeadlessApplication(listener, config);
        }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {		
		MainGame.PPM = 10;
		world = spy(new World(new Vector2(0,-9.81f), true));
		world.setContactListener(new ObjectListener());		  
		atlas = new TextureAtlas(Gdx.files.internal("textures/sisters_and_enemies/sisters_and_enemies.pack")); 	     			
		player = mock(GianaPlayer.class);			  				
		audio = mock(IAudio.class);
		maploader = new TmxMapLoader();
		map = maploader.load("levels//tilesets_scaled_32x32/level2.tmx");
		hud = new Hud(mock(Batch.class), 1);
		itemsToSpawn = null;				    
		rect=new Rectangle();		
		rect.setWidth(100);
		rect.setHeight(100);
	}
	
	/**
	 * Verify that PhysyicsWorldCreator.class runs properly.
	 */
	@Test
	void physicsWorldCreatorTest() {				
		// Assert score, should be 200 for coin and 0 elsewhere.
		tileItem = new Coin(hud, player, world, map, audio, rect);
		assertTrue(tileItem.getScore()==200 || tileItem.getScore()==0);
			
		// Assert that cell is obtained and that not null
		TiledMapTileLayer.Cell cell = tileItem.getCell();
		assertTrue(cell instanceof TiledMapTileLayer.Cell);
		assertTrue(cell != null);
			
		// See that map objects are created and run properly.		
		try {
			// Verify that tileobjects have been created.
			PhysicsWorldCreator creator = new PhysicsWorldCreator(itemsToSpawn, hud, atlas, player, world, map, audio);
			com.badlogic.gdx.utils.Array<IEnemy> enemies = creator.getEnemies();
			assertTrue(creator != null && creator instanceof PhysicsWorldCreator);
			
			// Verify number of enemies created.
			assertTrue(enemies.size==8);			
			}			
		
		catch(Exception e) {
			assertTrue(false);
		}
	}	
}


