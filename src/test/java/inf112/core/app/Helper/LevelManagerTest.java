package inf112.core.app.Helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;


class LevelManagerTest {
	// Variables
	ILevelManager levels;
	
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
		levels = new LevelManager();		
	}

	/**
	 * Test that checks current map.
	 */
	@Test
	void getCurrentLevelTest() {
		assertTrue(levels.getCurrentLevelNumber() == 1);
		TiledMap currentMap = levels.getCurrentLevel();
		assertTrue(currentMap != null);
		assertTrue(currentMap instanceof TiledMap);
		assertTrue(levels.getCurrentLevelPath().contains("level1.tmx"));				
	}
	
	/**
	 * Test that level availability and counting.
	 */
	@Test
	void nextLevelTest() {
		assertTrue(levels.nextLevelAvailable());
		levels.nextLevel();
		assertFalse(levels.nextLevelAvailable());
	}		

	/**
	 * Test completion of levels.
	 */
		@Test
		void completedTest() {						
			assertFalse(levels.isCompleted());
			levels.setCompleted();
			assertTrue(levels.isCompleted());
		}

		/**
		 * Test reset of levels.
		 */
		@Test
		void resetCompletedTest() {
			levels.setCompleted();
			levels.reset();
			assertFalse(levels.isCompleted());				
		}					
}

