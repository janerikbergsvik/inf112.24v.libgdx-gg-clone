package inf112.core.app.View.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.core.app.Main;
import inf112.core.app.MainGame;
import inf112.core.app.Helper.GameLabelStyler;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class CreditsScreen.
 */
public class CreditsScreen implements Screen {
	// Initialization of variables.
	private MainGame game;
	private Stage stage;
	
	// Tiled level variables
	private OrthogonalTiledMapRenderer tiledmaprenderer;

	// Tiles map
	private TmxMapLoader maploader;
    private TiledMap map;
	
    // Screen variables
    private Viewport viewport;
    private OrthographicCamera cam;
    private Batch batch;
    
	// Level manager, hud and audio
	private ILevelManager levels;
	private IHud hud;
	private IAudio audio;
	
	// Fields related to table
	private Table table;
	
	// Horizontal scrollers
	private ScrollPane scrollPane1;
	private ScrollPane scrollPane2;
	private ScrollPane scrollPane3;
	private ScrollPane scrollPane4;
	private ScrollPane scrollPane5;
	private Label label11;
	private Label label22;
	private Label label33;
	private Label label44;
	private Label label55;
	
	// Timers & Adjusters
	private float scrollSpeed = 150f; // Adjust the scroll speed as needed
	private float scrollTimer = 0f;
	private Integer x_adjuster = 2;
	private Boolean isForward = true;
	
	/**
	 * Instantiates a new credits screen.
	 *
	 * @param game the game
	 * @param batch the batch
	 * @param levels the levels
	 * @param hud the hud
	 * @param audio the audio
	 */
	public CreditsScreen(MainGame game, Batch batch, ILevelManager levels, IHud hud, IAudio audio) {
		// Initialize variables.
		this.game = game;
		this.batch = batch;
		this.levels = levels;
		this.hud = hud;
		this.audio = audio;
		
		// Setup table		
		setupTable();
		
	    // Camera for following the player
	    // Viewport for setting aspect ratio despite screen size
	    cam = new OrthographicCamera();
	    viewport = new FitViewport(Main.WINDOW_WIDTH / MainGame.PPM, Main.WINDOW_HEIGHT / MainGame.PPM, cam);
	    viewport.apply(); 
	     
		// Render map	
	    maploader = new TmxMapLoader();
	    map = maploader.load("levels//tilesets_scaled_32x32/level2.tmx");	    
	}
	
	@Override
	public void show() {			
			setupStage();
       	}

	/**
	 * Setup stage.
	 */
	private void setupStage() {
		// Render map
		tiledmaprenderer = new OrthogonalTiledMapRenderer(map, 1 / MainGame.PPM);
		
		// Setup stage and viewport		
        viewport = new FitViewport(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        stage = new Stage(viewport, batch);
        
        stage.addActor(table);

        // Let stage control user input.
       	Gdx.input.setInputProcessor(stage);
	}

	/**
	 * Setup table.
	 */
	private void setupTable() {
		// New table to fill with buttons
    	table = new Table();
        
    	// Adjust table.
    	table.top();
    	table.setFillParent(true);

   	    // Get great Giana Sisters TrueType font.
        Label.LabelStyle labelStyle = GameLabelStyler.getLabelStyle("truetypefonts/giana/Giana.ttf", 19, 91, 174, 75);
        
	    // Add labels to the table  
    	Label label1 = new Label("PRESS ESCAPE TO EXIT",labelStyle);
    	Label label2 = new Label(" ",labelStyle);
    	Label label3 = new Label(" ",labelStyle);

    	label1.setAlignment(Align.center);
    	label2.setAlignment(Align.center);
    	label3.setAlignment(Align.center);

    	String newString1 = "Digi-teamet Credits                                                         ".repeat(50);
    	label11 = new Label(newString1, labelStyle);
	    label11.setWrap(false); // disable wrapping for horizontal scroll
	    label11.setWidth(10000); // set a width larger than the screen to allow scrolling
	    String newString2 = " Kristoffer Vestbø                                                          ".repeat(50);
    	label22 = new Label(newString2, labelStyle);
	    label22.setWrap(false);
	    label22.setWidth(10000);
		String newString3 = " Jan Erik Bergsvik                                                          ".repeat(50);
    	label33 = new Label(newString3, labelStyle);
	    label33.setWrap(false); 
	    label33.setWidth(10000);
		String newString4 = "  Endre Jørgensen                                                           ".repeat(50);
    	label44 = new Label(newString4, labelStyle);
	    label44.setWrap(false); 
	    label44.setWidth(10000);
		String newString5 = "  Tanja Todorovic                                                           ".repeat(50);
    	label55 = new Label(newString5, labelStyle);
	    label55.setWrap(false);
	    label55.setWidth(10000); 
 		    		
	    // Create scroll pane and add label to it.
	    scrollPane1 = new ScrollPane(label11);
	    scrollPane2 = new ScrollPane(label22);
		scrollPane3 = new ScrollPane(label33);
	    scrollPane4 = new ScrollPane(label44);
		scrollPane5 = new ScrollPane(label55);
		
		// Add to table
		table.add(label1).pad(5).fillX().expandX();
		table.row().pad(5);				
		table.add(scrollPane1).pad(5).fillX().expandX();
		table.row().pad(5);
		table.add(scrollPane2).pad(5).fillX().expandX();
		table.row().pad(5);
		table.add(scrollPane3).pad(5).fillX().expandX();
		table.row().pad(5);
		table.add(scrollPane4).pad(5).fillX().expandX();
		table.row().pad(5);
		table.add(scrollPane5).pad(5).fillX().expandX();
		table.row().pad(5);				
		table.add(label3).pad(5).fillX().expandX();
					    
    	//Add table to stage
        scrollPane1.setScrollX(0);
        scrollPane2.setScrollX(0);
		scrollPane3.setScrollX(0);
        scrollPane4.setScrollX(0);
		scrollPane5.setScrollX(0);
	}
	
	/**
	 * Change scroll position.
	 *
	 * @param label the label
	 * @param scrollTimer the scroll timer
	 * @param scrollSpeed the scroll speed
	 * @param scrollpane the scrollpane
	 */
	private void changeScrollPosition(Label label, float scrollTimer, float scrollSpeed, ScrollPane scrollpane) {
		scrollpane.setScrollX((scrollTimer * scrollSpeed) % (label.getWidth() + Gdx.graphics.getWidth()));  	
	}
	
	@Override
	public void render(float delta) {
     	  // Set camera x position to player coordinates and update
		  if(cam.position.x > 250/2 && isForward)  {
				x_adjuster = -2;
				isForward = !isForward;
			}
		  if (cam.position.x < 20/2 && !isForward) {
				x_adjuster = 2;
				isForward = !isForward;
		  }

		  cam.position.x = cam.position.x + x_adjuster / MainGame.PPM;
	 	  cam.update();

	 	  // Clear screen
	 	  Gdx.gl.glClearColor(0, 0, 0, 0);
	 	  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	      
	 	  // Draw tile map
		  tiledmaprenderer.setView(cam);
		  tiledmaprenderer.render();

		  // Move text scroller horizontally
	      scrollTimer += Gdx.graphics.getDeltaTime();
	      	     	      		      
	      changeScrollPosition(label11, scrollTimer, scrollSpeed, scrollPane1);
	      changeScrollPosition(label22, scrollTimer, scrollSpeed, scrollPane2);
	      changeScrollPosition(label33, scrollTimer, scrollSpeed, scrollPane3);
	      changeScrollPosition(label44, scrollTimer, scrollSpeed, scrollPane4);
	      changeScrollPosition(label55, scrollTimer, scrollSpeed, scrollPane5);
	      	        
	      // Render stage
	  	  batch.setProjectionMatrix(stage.getCamera().combined);
	      stage.act();
	      stage.draw();
	      
	      // Exit if esc pressed
	      if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
				game.setScreen(new MainScreen(game, batch, levels, hud, audio));  		
	  			}       
	}

	@Override
	public void resize(int width, int height) {	
		  // Resize event
	      viewport.update(width,height);
	      cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
		  cam.update();
		  
	      stage.getViewport().update(width, height, true);
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
