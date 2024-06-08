package inf112.core.app.View.GameScreens;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;


class HudTest {
	// Variables
	IHud hud;
	
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
		Batch batch = mock(SpriteBatch.class);
		hud = new Hud(batch, 1);
	}
	
	/**
	 * 
	 * @return mocked Stage
	 */
	static Stage mockStage() {
		Viewport viewport = mock(Viewport.class);
		viewport.setCamera(mock(Camera.class));
		return new Stage(viewport, mock(SpriteBatch.class));
	}
		
	/**
	 * Verify that number of lives are counted and end up out of lives.
	 */
	@Test
	void changeLivesTest() {
		hud.changeLives(-3);
		if(hud.isOutOfLives()) 
				assertTrue(true);
			else 
				assertTrue(false);	
		}	

	/**
	 * Verify changing score.
	 */
	@Test
	void changeScoreTest() {
		hud.changeScore(1000);
		if(hud.getScore() == 1000) 
				assertTrue(true);
		else 
				assertTrue(false);	
		}		

	/**
	 * Verify score reset.
	 */
	@Test
	void resetScoreTest() {
		hud.changeScore(1000);
		hud.resetScore();		

		if(hud.getScore() == 0) 
				assertTrue(true);
		else 
				assertTrue(false);	
		}		
	
	/**
	 * Verify that time counter, and that end up out of time.
	 */
	@Test
	void changeTimeTest() {
		hud.changeTime(-300);
		if(hud.isTimeOut()) 
				assertTrue(true);
			else 
				assertTrue(false);	
		}	

	/**
	 * Verify that level can be set, and retrieved. 
	 * Level counter should automatically be reset to 1 when level is set to value below 1.
	 */
	@Test
	void changeLevelTest() {
		hud.changeLevel(-99);
		System.out.println(String.format("%d", hud.getLevel()));
		assertTrue(hud.getLevel() == 1);
		hud.changeLevel(99);
		assertTrue(hud.getLevel() == 99+1);
	}

	/**
	 * Verify that update method counts timer properly and that time counts down.
	 */
	@Test
	void updateTest() {
		// Call update method with 1.0 delta timestep 300 times to allow
		// for time-out.
		for (int i=0; i<300; i++) {
			hud.update(1);	
		}
		
		// Check if time is out.
		assertTrue(hud.isTimeOut());	
	} 
	
	@Test
	void resetScoreShouldCallChangeScore() {
	   Hud hud = mock(Hud.class);

	   doNothing().when(hud).changeScore(0);
	   hud.resetScore();

	   verify(hud, times(1)).resetScore();
	   }
}

