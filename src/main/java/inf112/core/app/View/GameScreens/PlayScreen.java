package inf112.core.app.View.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;

import inf112.core.app.Main;
import inf112.core.app.MainGame;
import inf112.core.app.Controller.GameController;
import inf112.core.app.Controller.IGameController;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.Model.GameModel;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.View.GameRenderer;
import inf112.core.app.View.IGameRenderer;
    
/**
 * The Class PlayScreen.
 * This is the Model-View-Controller. Here the
 * IGameModel, IGameRenderer and IGameController are controlled.
 */
public class PlayScreen implements Screen{
    // Model - View - Controller
    private IGameModel model;
	private IGameRenderer renderer;
	private IGameController controller;
	
	/**
	 * Instantiates a new play screen.
	 *
	 * @param game the game
	 * @param audio the audio
	 * @param levels the levels
	 * @param hud the hud
	 * @param batch the batch
	 */
	public PlayScreen(MainGame game, IAudio audio, ILevelManager levels, IHud hud, Batch batch) {
	    // Model - View - Controller		
	    model = new GameModel(game, audio, levels, hud, batch, Main.WINDOW_WIDTH, Main.WINDOW_WIDTH);
	    renderer = new GameRenderer(model, batch, hud);
	    controller = new GameController(game, model, batch, levels, hud, audio);
	    
		// New stage.
		Gdx.input.setInputProcessor(renderer.getStage());
		
		// Start background music
		audio.startMusic();
	}
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		// MODEL - VIEW - CONTROLLER
		// Update sequence below:
		// 1. Controller updates player based on controller input.
		controller.handleInput(delta);
		
		// 2. Model is updated.
		model.update(delta);

		// 3. Everything is rendered.
		renderer.render();		
	}
	
	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
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
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
	}
}
