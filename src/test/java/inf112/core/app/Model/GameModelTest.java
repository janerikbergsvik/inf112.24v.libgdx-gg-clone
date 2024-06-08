package inf112.core.app.Model;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Helper.GameSettings;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.IGameSettings;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.Helper.LevelManager;
import inf112.core.app.Helper.ObjectListener;
import inf112.core.app.View.GameScreens.Hud;
import inf112.core.app.View.GameScreens.IHud;

class GameModelTest {
	// Variables
	IPlayer player;		
	World world;
	IHud hud;		
	IAudio audio;
	ILevelManager levels;
	IGameModel model;
	MainGame game;
	
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
		audio = mock(IAudio.class);
		hud = new Hud(mock(Batch.class), 1);				   
		IGameSettings gamesettings = spy(new GameSettings());
		game = spy(new MainGame());
		when(game.getSettings()).thenReturn(gamesettings);	
		doNothing().when(game).setScreen(isA(Screen.class));								
		levels = new LevelManager();
		hud = spy(new Hud(mock(Batch.class), 1));
	}	
		
	/**
	 * Verify that GameModel initializes properly.
	 */
	@Test
	void gameModelConstructorTest() {			
		// See that methods run properly.
		try {
			model = new GameModel(game, audio, levels, hud, mock(Batch.class), 50, 50);	
			assertTrue(true);
			}			
		
		catch(Exception e) {
			assertTrue(false);
		}	
	}

		/**
		 * Verify that GameModel initializes properly and that method handlePlayerStates() 
		 * is properly executing.
		 */
		@Test
		void gameModelHandlePlayerStatesTest() {			
			// See that methods run properly.
			try {
				model = new GameModel(game, audio, levels, hud, mock(Batch.class), 50, 50);	
				when(hud.isTimeOut()).thenReturn(true);	
				when(hud.isOutOfLives()).thenReturn(false);
				model.update(0.5f);
				when(hud.isTimeOut()).thenReturn(true);	
				when(hud.isOutOfLives()).thenReturn(true);							
				model.update(0.5f);
				player = model.getPlayer();
				player.setCompletedLevel(true);
				model.update(0.5f);
				when(hud.isOutOfLives()).thenReturn(false);		
				player.setCompletedLevel(false);		
				model.update(0.5f);
				assertTrue(true);
				}			
			
			catch(Exception e) {
				assertTrue(false);
			}					
}	}



