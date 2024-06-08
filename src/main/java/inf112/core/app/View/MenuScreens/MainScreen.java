package inf112.core.app.View.MenuScreens;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import inf112.core.app.MainGame;
import inf112.core.app.Helper.GameLabelStyler;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.View.GameScreens.IHud;
import inf112.core.app.View.GameScreens.StageIntroScreen;


/**
 * The Class MainScreen.
 */
public class MainScreen extends AbstractScreen implements Screen{  
    // Width and height to fit fonts to screen.
	static final int WIDTH = 900;
	static final int HEIGHT = 900;

	/**
	 * The Enum setScreen.
	 */
	public enum setScreen {
	    NEW_GAME,
	    OPTIONS,
	    HELP,
	    CREDITS,
	    EXIT
	}

	// Table
	private Table table;
	
	// Level, hud and audio.
	private ILevelManager levels;
	private IHud hud;
	private IAudio audio;
	
	/**
	 * Instantiates a new main screen.
	 *
	 * @param game the game
	 * @param batch the batch
	 * @param levels the levels
	 * @param hud the hud
	 * @param audio the audio
	 */
	public MainScreen(MainGame game, Batch batch, ILevelManager levels, IHud hud, IAudio audio) {
		super(game, batch, WIDTH, HEIGHT);
		this.levels = levels;
		this.hud = hud;
		this.audio = audio;		
		
		audio.startMusic();
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

        // Create buttons & screens
        ArrayList<TextButton> buttons = new ArrayList<>();
        
        // Add buttons to table
        buttons.add(new TextButton("New game", skin));
        buttons.add(new TextButton("Options", skin));
        buttons.add(new TextButton("Help", skin));
		buttons.add(new TextButton("Credits", skin));
        buttons.add(new TextButton("Exit", skin));
		
        table.row().pad(5).fillX().center();
        table.add(new Image(new Texture(Gdx.files.internal("textures/gametitle00.png")))).pad(30);
        table.top();
        table.row();
        for (TextButton button : buttons) {
        	table.add(button);
            table.row();
        }
        
        // Get great Giana Sisters TrueType font.
        Label.LabelStyle labelStyle = GameLabelStyler.getLabelStyle("truetypefonts/giana/Giana.ttf", 30, 96, 173, 219);
        
        Label label1 = new Label(" ",labelStyle);
	    Label label2 = new Label("© DIGI-TEAMET 2024",labelStyle);
	    label2.setAlignment(Align.center);
	    table.row();				
		table.add(label1);
		table.row();				
		table.add(label2);
    	
        // Add listeners to buttons.
		mouseTracker(buttons.get(0), setScreen.NEW_GAME);
		mouseTracker(buttons.get(1), setScreen.OPTIONS);
		mouseTracker(buttons.get(2), setScreen.HELP);
		mouseTracker(buttons.get(3), setScreen.CREDITS);
		mouseTracker(buttons.get(4), setScreen.EXIT);
	}

	/**
	 * Mouse tracker.
	 *
	 * @param button the button
	 * @param type the type
	 */
	private void mouseTracker(TextButton button, setScreen type){
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // Redirect to main screen
                    Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
                    
					switch(type) {
						case NEW_GAME:
							game.setScreen(new StageIntroScreen(game, batch, levels, hud, audio));
							Gdx.app.log("GG-Clone", "new game clicked.");
							break;
						case OPTIONS:
							game.setScreen(new OptionsScreen(game, batch, levels, hud, audio));
							Gdx.app.log("GG-Clone", "options clicked.");
							break;
						case HELP:
							game.setScreen(new HelpScreen(game, batch, levels, hud, audio));
        					Gdx.app.log("GG-Clone", "help clicked.");
							break;
						case CREDITS:
							game.setScreen(new CreditsScreen(game, batch, levels, hud, audio));
							Gdx.app.log("GG-Clone", "Credits clicked.");
							break;
						case EXIT:
							Gdx.app.log("GG-Clone", "exit clicked.");
                			Gdx.app.exit();
							break;

						default:
						System.out.println("Nothing clicked");
					}
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
