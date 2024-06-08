package inf112.core.app.Characters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
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
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.core.app.MainGame;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ObjectListener;
import inf112.core.app.View.GameScreens.Hud;
import inf112.core.app.View.GameScreens.IHud;

class GianaPlayerTest {
	// Variables
	IPlayer player;		
	World world;
	TextureAtlas atlas;
	IHud hud;		
	TiledMap map;
	IAudio audio;
	TmxMapLoader maploader;
	Rectangle rect;
	
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
		// Initialize variables, create and mock objects.		
		MainGame.PPM = 10;
		world = spy(new World(new Vector2(0,-9.81f), true));
		world.setContactListener(new ObjectListener());		  
		atlas = new TextureAtlas(Gdx.files.internal("textures/sisters_and_enemies/sisters_and_enemies.pack")); 	     			
		audio = mock(IAudio.class);
		maploader = new TmxMapLoader();
		map = maploader.load("levels//tilesets_scaled_32x32/level2.tmx");
		hud = new Hud(mock(Batch.class), 1);				   
		rect=new Rectangle();		
		rect.setWidth(100);
		rect.setHeight(100);		
		Array<IProjectile> bullets = new Array<>();
		player = new GianaPlayer(world, atlas, bullets);
	}
    
	/**
	 * Level completed check.
	 */
	@Test
	void completedLevelTest() {
		player.setCompletedLevel(true);
		assertTrue(player.hasCompletedLevel());
		player.setCompletedLevel(false);
		assertFalse(player.hasCompletedLevel());
	}

	/**
	 * Speed- and speedfactor test.
	 */
	@Test
	void speedTest() {
		assertTrue(player.getSpeedX()== 5.0f);
		assertTrue(player.getSpeedY()== 8.4f);
		assertTrue(player.getSpeedFactor()== 1f);
		player.setSpeedFactor(2f);
		assertTrue(player.getSpeedFactor() == 2f);
		player.update(5f);
		}

	/**
	 * Is player in contact with ground? Then allowed to jump.
	 */
	@Test
	void playerGroundContactTest() {
		player.setGroundContact(true);
		assertTrue(player.getGroundContact());
		player.moveUp();
		player.setGroundContact(false);
		assertFalse(player.getGroundContact());
	}

	/**
	 * Powerup timeout test
	 */
	@Test
	void powerupTimeOutTest() {
			player.setDoubleLightningTimeout(20.0f);
			assertTrue(player.getDoubleLightningTimeout() == 20.0f);
			player.setSingleLightningTimeout(20.0f);
			assertTrue(player.getSingleLightningTimeout() == 20.0f);
	}

	/**
	 * Shoot activation test
	 */
	@Test
	void shootActivationTest() {
		player.setShootActive(true);
		assertTrue(player.isShootActive());
		player.setSingleLightningTimeout(20.0f);
		try {
			player.shoot();
		}
		catch(Exception e) {
			assertTrue(false);
		}	
	}

	/**
	 * Player health test
	 */
	@Test
	void playerHealthTest() {
		player.applyHealthChange(0);
		assertFalse(player.isDead());
		player.applyHealthChange(-99);
		assertTrue(player.isDead());
	}

	/**
	 * Player movement test
	 */
	@Test
	void movePlayerTest() {
		try {
			player.moveLeft();
			player.moveRight();
			player.setGroundContact(true);
			player.moveUp();
			player.moveDown();
		}
		
		catch(Exception e) {
			assertTrue(false);
		}	
	}

	/**
	 * Player getState, getBody and draw tests.
	 */
	@Test
	void otherPlayerTest() {
		try {			
			player.moveLeft();
			player.moveLeft();							
			player.getFrame(0.5f);
			player.setGroundContact(true);
			player.moveUp();
			player.moveUp();			
			player.getFrame(0.5f);
			player.moveRight();
			player.moveRight();
			player.getFrame(0.5f);			
			player.draw(mock(Batch.class));
			player.getBody();
		}
		
		catch(Exception e) {
			assertTrue(false);
		}	
	}

	}	


