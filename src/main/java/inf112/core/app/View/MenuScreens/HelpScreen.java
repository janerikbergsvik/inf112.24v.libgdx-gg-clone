package inf112.core.app.View.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.core.app.MainGame;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class HelpScreen.
 */
public class HelpScreen extends AbstractScreen implements Screen {
    // Table 
	private Table table;
	
	// Width and height to fit fonts to screen.
    static final int WIDTH = 1200;
    static final int HEIGHT = 900;
    
    // Levels, hud and audio.
	private ILevelManager levels;
	private IHud hud;
	private IAudio audio;

    /**
     * Instantiates a new help screen.
     *
     * @param game the game
     * @param batch the batch
     * @param levels the levels
     * @param hud the hud
     * @param audio the audio
     */
    public HelpScreen(MainGame game, Batch batch, ILevelManager levels, IHud hud, IAudio audio) {
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
		// New table to fill with instructions and back button
        table = new Table();

        // Adjust table.
        table.setFillParent(true);

        // Add instruction labels to table
        Label instruction1 = new Label(" To move up click the up arrow ", skin, "big");
        Label instruction2 = new Label(" To stop click the down arrow", skin, "big");
        Label instruction3 = new Label(" To move left click the left arrow", skin, "big");
        Label instruction4 = new Label(" To move right click the right arrow", skin, "big");
        Label instruction5 = new Label(" Esc in-game exits to main screen", skin, "big");
        Label instruction6 = new Label(" If shoot power-up, then space to shoot.", skin, "big");
        
        // Add labels to the table
        table.add(instruction1).expandX().pad(10).row(); // Add instruction1 with padding
        table.add(instruction2).expandX().pad(20).row(); // Add instruction2 with padding
        table.add(instruction3).expandX().pad(20).row(); // Add instruction3 with padding
        table.add(instruction4).expandX().pad(20).row(); // Add instruction3 with padding
        table.add(instruction5).expandX().pad(20).row(); // Add instruction3 with padding
        table.add(instruction6).expandX().pad(20).row(); // Add instruction3 with padding
        
           // Add a button to go back
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainScreen(game, batch, levels, hud, audio)); // Redirect to MainScreen
                Gdx.app.log("GG-Clone", "back clicked.");
            }
        });
        table.add(backButton).expandX().pad(10); // Add the back button with padding
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


