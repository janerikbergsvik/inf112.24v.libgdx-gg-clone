package inf112.core.app.View.MenuScreens;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.core.app.MainGame;
import inf112.core.app.Helper.GameSettings;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.View.GameScreens.IHud;
import inf112.core.app.View.GameScreens.PowerupText;
import inf112.core.app.View.GameScreens.StageIntroScreen;

class GeneralScreenTest {

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
	}
	
	/**
	 * Verify that creditsScreen is properly initialized and that tables
	 * are properly generated.
	 */
	@Test
	void creditsScreenTest() {	
		MainGame game = mock(MainGame.class);				
		
		// See that methods run properly.
		try {
			CreditsScreen obj = spy(new CreditsScreen(game, mock(SpriteBatch.class), mock(ILevelManager.class), mock(IHud.class), mock(IAudio.class)));
			obj.pause();
			obj.resume();
			obj.hide();
			obj.dispose();				
			assertTrue(true);
			}			
		
		catch(Exception e) {
			assertTrue(false);
		}		
	}

	/**
	 * Verify that helpScreen is properly initialized and that tables
	 * are properly generated.
	 */
	@Test
	void helpScreenTest() {	
		MainGame game = mock(MainGame.class);				
		
		// See that methods run properly.
		try {
			HelpScreen obj = spy(new HelpScreen(game, mock(SpriteBatch.class), mock(ILevelManager.class), mock(IHud.class), mock(IAudio.class)));
			obj.pause();
			obj.resume();
			obj.hide();
			obj.dispose();	
			obj.resize(10,10);
			assertTrue(true);
			}			
		
		catch(Exception e) {
			assertTrue(false);
		}		
	}

	/**
	 * Verify that mainScreen is properly initialized and that tables
	 * are properly generated.
	 */
	@Test
	void mainScreenTest() {	
		MainGame game = mock(MainGame.class);				
		
		// See that methods run properly.
		try {			
			MainScreen obj = spy(new MainScreen(game, mock(SpriteBatch.class), mock(ILevelManager.class), mock(IHud.class), mock(IAudio.class)));
			obj.pause();
			obj.resume();
			obj.hide();
			obj.dispose();	
			obj.resize(10,10);
			assertTrue(true);
			}			
		
		catch(Exception e) {
			assertTrue(false);
		}		
	}

	/**
	 * Verify thatcompletedScreen is properly initialized and that tables
	 * are properly generated.
	 */
	@Test
	void completedScreenTest() {	
		MainGame game = mock(MainGame.class);				
		when(game.getSettings()).thenReturn(new GameSettings());	
		
		// See that methods run properly.
		try {
			CompletedScreen obj = spy(new CompletedScreen(game, mock(SpriteBatch.class), mock(ILevelManager.class), mock(IHud.class), mock(IAudio.class)));
			obj.pause();
			obj.resume();
			obj.hide();
			obj.dispose();									
			assertTrue(true);
			}			
		
		catch(Exception e) {
			assertTrue(false);
		}		
	}

	/**
	 * Verify that gameOverScreen is properly initialized and that tables
	 * are properly generated.
	 */
	@Test
	void gameOverScreenTest() {	
		MainGame game = spy(MainGame.class);				
		when(game.getSettings()).thenReturn(new GameSettings());				
		
		// See that methods run properly.
		try {	
			GameOverScreen help = spy(new GameOverScreen(game, mock(SpriteBatch.class), mock(ILevelManager.class), mock(IHud.class), mock(IAudio.class)));
			help.pause();
			help.resume();
			help.hide();
			help.dispose();
			help.resize(10,10);
			assertTrue(true);
			}			
		
		catch(Exception e) {
			assertTrue(false);
		}		
	}
	
	/**
	 * Verify that optionsScreen is properly initialized and that tables
	 * are properly generated.
	 */
	@Test
	void optionsScreenTest() {	
		MainGame game = spy(MainGame.class);				
		when(game.getSettings()).thenReturn(new GameSettings());				
			
		// See that methods run properly.
		try {	
			OptionsScreen options = spy(new OptionsScreen(game, mock(SpriteBatch.class), mock(ILevelManager.class), mock(IHud.class), mock(IAudio.class)));
			options.pause();
			options.resume();
			options.hide();
			options.dispose();
			options.resize(10,10);
			assertTrue(true);
			}			
			
		catch(Exception e) {
			assertTrue(false);
		}				
	}

	/**
	 * Verify that StageIntroScreen is properly initialized and that tables
	 * are properly generated.
	 */
	@Test
	void stageIntroScreenTest() {	
		MainGame game = spy(MainGame.class);				
		when(game.getSettings()).thenReturn(new GameSettings());				
		
		// See that methods run properly.
		try {	
			StageIntroScreen obj = spy(new StageIntroScreen(game, mock(SpriteBatch.class), mock(ILevelManager.class), mock(IHud.class), mock(IAudio.class)));
			obj.pause();
			obj.resume();
			obj.hide();
			obj.dispose();					
			assertTrue(true);
			}			
		
		catch(Exception e) {
			assertTrue(false);
		}
	}
	
		
		/**
		 * Verify that PowerupText is properly initialized and that tables
		 * are properly generated.
		 */
		@Test
		void powerUpTextTest() {	
			MainGame game = spy(MainGame.class);				
			when(game.getSettings()).thenReturn(new GameSettings());				
			
			// See that methods run properly.
			try {	
				PowerupText obj = spy(new PowerupText(mock(SpriteBatch.class)));
				obj.update(0.5f);
				assertTrue(true);
				}			
			
			catch(Exception e) {
				assertTrue(false);
			}	
	}
}


