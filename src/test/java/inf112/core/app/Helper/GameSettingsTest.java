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


public class GameSettingsTest {
	GameSettings gamesettings;
	
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
		gamesettings = spy(new GameSettings());
	}
	
	/**
	 * GameSpeed setting test
	 */
	@Test
	void gameSpeedTest() {				
		gamesettings.setGameSpeed(10f);
		assertTrue(gamesettings.getGameSpeed() == 10f);

}

	/**
	 * HighScore setting test
	 */
	@Test
	void highScoreTest() {		
		float highscore = gamesettings.getHighScore();
		gamesettings.setHighScore(highscore+1f);
		assertTrue(gamesettings.getHighScore() == highscore+1f);
}
}
		