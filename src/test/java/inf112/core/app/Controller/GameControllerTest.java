package inf112.core.app.Controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Model.GameModel;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.View.GameScreens.IHud;

class GameControllerTest {
	/**
	 * Static method run before everything else
	 */
	@BeforeAll
	static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
    	Gdx.gl = mock(GL20.class);
    
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
	 * Game controller test 
	 */
	@Test
	void gameControllerTest() {
		// Mock IPlayer, IGameModel and relevant methods.
		IGameModel model = mock(GameModel.class);
		when(model.getMap()).thenReturn(new TiledMap());
		IPlayer player = mock(IPlayer.class);
		when(model.getPlayer()).thenReturn(player);
		when(player.isShootActive()).thenReturn(true);
			
		// Spy on input Gdx.input.
		Gdx.input = spy(Gdx.input.getClass());
    	
		// Mock responses from keyboard.
	    when(Gdx.input.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);
	    when(Gdx.input.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);
	    when(Gdx.input.isKeyJustPressed(Input.Keys.UP)).thenReturn(true);
	    when(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)).thenReturn(true);
	    when(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)).thenReturn(true);
	    when(Gdx.input.isKeyJustPressed(Input.Keys.D)).thenReturn(true);
	    when(Gdx.input.isKeyJustPressed(Input.Keys.I)).thenReturn(true);
	    when(Gdx.input.isKeyJustPressed(Input.Keys.G)).thenReturn(true);
	    
	    // Initiate controller
		IGameController controller = new GameController(mock(MainGame.class), model, mock(SpriteBatch.class), null, mock(IHud.class), null);
			
		// Run through handleInput() method.
        controller.handleInput(0.5f);

        // Check afterwards that functions have been activated the
        // current number of times.
        try {
        	verify(Gdx.input, times(1)).isTouched();
            verify(Gdx.input, times(1)).isKeyPressed(Input.Keys.LEFT);
            verify(Gdx.input, times(1)).isKeyPressed(Input.Keys.RIGHT);
            verify(Gdx.input, times(1)).isKeyJustPressed(Input.Keys.UP);
            verify(Gdx.input, times(1)).isKeyJustPressed(Input.Keys.DOWN);
            verify(Gdx.input, times(1)).isKeyJustPressed(Input.Keys.SPACE);
            verify(Gdx.input, times(1)).isKeyJustPressed(Input.Keys.D);
            verify(Gdx.input, times(1)).isKeyJustPressed(Input.Keys.I);
            verify(Gdx.input, times(1)).isKeyJustPressed(Input.Keys.G);
            assertTrue(true);
        	}
        
        catch(Exception e) {
        		assertTrue(false);
        	}
	}
}
