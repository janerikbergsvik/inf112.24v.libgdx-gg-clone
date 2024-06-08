package inf112.core.app.Helper;

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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.GianaPlayer;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.View.GameScreens.Hud;
import inf112.core.app.View.GameScreens.IHud;

class AudioGameSettingsTest {
	// Variables
	IPlayer player;		
	World world;
	TextureAtlas atlas;
	IHud hud;		
	TiledMap map;
	IAudio audio;
	TmxMapLoader maploader;
	IGameSettings gamesettings;
	
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
		player = mock(GianaPlayer.class);			  				
		audio = mock(IAudio.class);
		maploader = new TmxMapLoader();
		map = maploader.load("levels//tilesets_scaled_32x32/level2.tmx");
		hud = new Hud(mock(Batch.class), 1);				   
		gamesettings = spy(new GameSettings());
		audio = spy(new Audio(gamesettings));		
	}
	
	/**
	 * Combined IAudio and GameSettings test where they interact.
	 */
	@Test
	void combinedGameSettingsAndAudioTest() {				
		// Set music & sound active from GameSettings.
		gamesettings.setMusicActive(true);
		gamesettings.setMusicVolume(99f);
		gamesettings.setSoundActive(true);
		gamesettings.setSoundVolume(99f);
		audio.updateFromSettings();
		
		// Check sound volume
		assertTrue(audio.getSoundVolume() == 99f);
				
		// See that methods run properly.
		try {	
			audio.startMusic();
			audio.playPickUp();
			audio.playDied();
			audio.playCompletedLevel();
			audio.stopMusic();
			}
			
		catch(Exception e) {
			assertTrue(false);
		}		
	}	
}


