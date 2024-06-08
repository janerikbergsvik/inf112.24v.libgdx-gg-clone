package inf112.core.app.View.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.app.MainGame;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.View.GameScreens.Hud;
import inf112.core.app.View.GameScreens.IHud;
import inf112.core.app.View.GameScreens.StageIntroScreen;

/**
 * The Class GameOverScreen.
 */
public class GameOverScreen extends AbstractScreen implements Screen {
    // Width and height to fit fonts to screen.
	static final int WIDTH = 900;
	static final int HEIGHT = 900;
	
	// Fields related to table
	private Table table;
	private TextButton retryButton;
	private TextButton exitButton;
	private ILevelManager levels;
	private IHud hud;
	private IAudio audio;
	
	/**
	 * Instantiates a new game over screen.
	 *
	 * @param game the game
	 * @param batch the batch
	 * @param levels the levels
	 * @param hud the hud
	 * @param audio the audio
	 */
	public GameOverScreen(MainGame game, Batch batch, ILevelManager levels, IHud hud, IAudio audio) {		
		super(game, batch, WIDTH, HEIGHT);
		this.levels = levels;
		this.hud = hud;
		this.audio = audio;
		
		setupTable();
	}
	
	@Override
	public void show() {		
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
	protected void setupTable() {
		// New table to fill with buttons
    	table = new Table();
        
    	// Adjust table.
    	table.setFillParent(true);
    		
    	// Add instruction labels to table
        Label instruction1 = new Label(" YOU ARE DEAD ! ", skin, "big");
        Label instruction2 = new Label(" ", skin);
        Label instruction3 = new Label(" GAME OVER ! ", skin, "big");
        Label instruction4 = new Label(" ", skin);
        Label instruction5 = new Label(String.format("CURRENT SCORE / HIGH SCORE"), skin, "big");
        Label instruction6 = new Label(" ", skin);
        Label instruction7 = new Label(String.format("%d / %d", hud.getScore(), this.game.getSettings().getHighScore()), skin, "big");
        Label instruction8 = new Label(" ", skin);
        
        // Add labels to the table
        table.add(instruction1).expandX().pad(1).row(); // Add instruction1 with padding
        table.add(instruction2).expandX().pad(1).row(); // Add instruction2 with padding
        table.add(instruction3).expandX().pad(1).row(); // Add instruction3 with padding
        table.add(instruction4).expandX().pad(1).row(); // Add instruction3 with padding
        table.add(instruction5).expandX().pad(1).row(); // Add instruction3 with padding
        table.add(instruction6).expandX().pad(1).row(); // Add instruction3 with padding
        table.add(instruction7).expandX().pad(1).row(); // Add instruction3 with padding
        table.add(instruction8).expandX().pad(1).row(); // Add instruction3 with padding
              
        // Add back button
    	retryButton = new TextButton("Retry", skin); 
    	exitButton = new TextButton("Exit to main menu", skin); 
  
        table.add(retryButton).expandX().pad(10); // Add the back button with padding
        table.row().pad(10,0,0,10);
        table.add(exitButton).expandX().pad(10); // Add the back button with padding   
        
    	// Add listeners to exit button.
    	retryButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
          		game.setScreen(new StageIntroScreen(game, batch, levels, new Hud(batch, 1), audio));
          	    Gdx.app.log("GG-Clone", "retry clicked.");
            }
            });        	

    	// Add listeners to exit button.
    	exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
          		game.setScreen(new MainScreen(game, batch, levels, new Hud(batch, 1), audio));
          	    Gdx.app.log("GG-Clone", "exit to main menu clicked.");
            }
            });
	}

	@Override
	public void render(float delta) {
		  // Clear screen
	      Gdx.gl.glClearColor(0, 0, 0, 0);
	      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	      
	      // Render stage
	      stage.act();
	      stage.draw();
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
		// Dispose of skin and atlas.
        skin.dispose();
        atlas.dispose();
	}
}
