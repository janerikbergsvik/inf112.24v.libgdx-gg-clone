package inf112.core.app.View.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.core.app.MainGame;
import inf112.core.app.Helper.GameLabelStyler;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;

/**
 * The Class StageIntroScreen.
 */
public class StageIntroScreen implements Screen {
	// Initialization of variables.
	private MainGame game;
	private Viewport viewport;
	private OrthographicCamera cam;
	private Stage stage;
	private Batch batch;
	  
	// Level manager
	private ILevelManager levels;
	
    // Width and height to fit fonts to screen.
	static final int WIDTH = 800;
	static final int HEIGHT = 900;
	
	// Time counter
	private float timeCounter = 0;
	private float timeDelay = 3.0f;
	
	// Fields related to table
	private Table table;
	
	// Audio and hud
	private IAudio audio;
	private IHud hud;
	   
	/**
	 * Instantiates a new stage intro screen.
	 *
	 * @param game the game
	 * @param batch the batch
	 * @param levels the levels
	 * @param hud the hud
	 * @param audio the audio
	 */
	public StageIntroScreen(MainGame game, Batch batch, ILevelManager levels, IHud hud, IAudio audio) {
		// Retrieve instance of GameLoader, batch and levels.
		this.game = game;
		this.batch = batch;
		this.levels = levels;
		this.audio = audio;
		this.hud = hud;

		// Setup table.
		setupTable();
	}
	
	@Override
	public void show() {	        
	    // Initialize camera and set viewport.
	    cam = new OrthographicCamera();
        viewport = new FitViewport(WIDTH, HEIGHT, cam);
        viewport.apply();
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
		cam.update();

		// Use stage for user interface together with listeners.		
		stage = new Stage(viewport, batch);    
        
		// Let stage control user input.
        Gdx.input.setInputProcessor(stage);
        
        //Add table to stage
        stage.addActor(table);

        // Render stage. 
        stage.act();
        stage.draw();         
	}

	/**
	 * Setup table.
	 */
	private void setupTable() {
		// New table to fill with buttons
    	table = new Table();
        
    	// Adjust table.
    	table.setFillParent(true);
    	  
    	  // Get great Giana Sisters TrueType font.
        Label.LabelStyle labelStyle = GameLabelStyler.getLabelStyle("truetypefonts/giana/Giana.ttf", 36, 227, 230, 106);
        
	    // Set debug lines.
    	//table.setDebug(true); 
    	
	    // Add labels to the table  
        Label label1 = new Label(String.format("STAGE %01d", levels.getCurrentLevelNumber()), labelStyle);
	    Label label2 = new Label(" ",labelStyle);
	    Label label3 = new Label("GET READY !",labelStyle);
		label1.setAlignment(Align.center);
		label2.setAlignment(Align.center);
		label3.setAlignment(Align.center);
	    
		table.row();				
		table.add(label1);
		table.row();				
		table.add(label2);
		table.row();				
		table.add(label3);
	}

	@Override
	public void render(float delta) {
		  // Clear screen
	      Gdx.gl.glClearColor(0, 0, 0, 0);
	      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	      
	      // Render stage
	      stage.act();
	      stage.draw();
	      
	      // Update timer and switch when introduction has been
	      // on screen for 5 seconds.
	      timeCounter += Gdx.graphics.getDeltaTime();
	    	
	      // If reached 1 second, then count 1 second down.
	      if (timeCounter >= timeDelay) {
	    		timeCounter = 0;
	    		game.setScreen(new PlayScreen(game, audio, levels, hud, batch));	    		
	    	 	}
	}

	@Override
	public void resize(int width, int height) {
		  // Resize event
	      viewport.update(width,height);
	      cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
		  cam.update();
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
		// TODO Auto-generated method stub
	}
}
