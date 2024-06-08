package inf112.core.app.View.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.core.app.MainGame;

/**
 * The Class AbstractScreen.
 * Used for common parts for screens to avoid repetition.
 */
public abstract class AbstractScreen implements Screen {
	// Initialization of variables.
	protected MainGame game;
	protected TextureAtlas atlas;
	protected Skin skin;
	protected Viewport viewport;
	protected OrthographicCamera cam;
	protected Stage stage;
	protected Batch batch;
	      
    	/**
	     * Instantiates a new abstract screen.
	     *
	     * @param game the game
	     * @param batch the batch
	     * @param width the width
	     * @param height the height
	     */
	    public AbstractScreen(MainGame game, Batch batch, int width, int height) {
		// Init game and batch.
		this.game = game;
		this.batch = batch;
		
		// Load glassy user interface
		this.atlas = new TextureAtlas(Gdx.files.internal("skins/glassy/glassy-ui.atlas"));
		this.skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"), atlas);	

	    // Initialize camera and set viewport.
	    this.cam = new OrthographicCamera();
        this.viewport = new FitViewport(width, height, cam);
        this.viewport.apply();
        this.cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
		this.cam.update();

		// Use stage for user interface together with listeners.
		this.stage = new Stage(viewport, batch);    
	}
	    
	/**
	 * Setup table.
	 */
	protected abstract void setupTable();	    
}
