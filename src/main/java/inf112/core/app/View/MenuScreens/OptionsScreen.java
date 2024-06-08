package inf112.core.app.View.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.core.app.MainGame;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class OptionsScreen.
 */
public class OptionsScreen extends AbstractScreen implements Screen {	 
    // Width and height to fit fonts to screen.
	static final int WIDTH = 800;
	static final int HEIGHT = 900;
	
	// Fields related to table
	private Table table;
	private Label titleLabel; 
	private Label musicLabel;
	private Label soundLabel;
	private Label speedLabel;
	private Label musicCheckboxLabel;
	private Label soundCheckboxLabel;
	private CheckBox musicCheckbox;
	private Slider musicSlider;
	private CheckBox soundCheckbox;
	private Slider soundSlider;
	private Slider speedSlider;
	private TextButton backButton;
	
	// Levels, hud and audio
	private ILevelManager levels;
	private IHud hud;
	private IAudio audio;
	
	/**
	 * Instantiates a new options screen.
	 *
	 * @param game the game
	 * @param batch the batch
	 * @param levels the levels
	 * @param hud the hud
	 * @param audio the audio
	 */
	public OptionsScreen(MainGame game, Batch batch, ILevelManager levels, IHud hud, IAudio audio) {
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
    	
    	// Set text on labels.
        titleLabel = new Label( "Options", skin, "big");
      	musicCheckboxLabel = new Label("Music on/off", skin, "big");
        musicLabel = new Label("Music volume", skin, "big");
        soundCheckboxLabel = new Label("Sound on/off", skin, "big");  	
    	soundLabel = new Label("Sound volume", skin, "big");
    	speedLabel = new Label("Game speed", skin, "big");
    	
        // Music on/off checkbox
    	musicCheckbox = new CheckBox(null, skin);
    	musicCheckbox.setChecked(game.getSettings().isMusicActive());
    	musicCheckbox.addListener(new ChangeListener() {
    	    @Override
    	    public void changed(ChangeEvent event, Actor actor) {
    	              	game.getSettings().setMusicActive(musicCheckbox.isChecked());
    	              	if (musicCheckbox.isChecked()) {
    	              		Gdx.app.log("startMusic()", "");
    	              		game.getAudio().startMusic();
    	              	}
    	              	else {
    	              		Gdx.app.log("stopMusic()", "");
    	              		game.getAudio().stopMusic();
    	              	}  
   	    }
    	});	

    	// Music volume slider
    	musicSlider = new Slider( 0f, 1f, 0.05f, false, skin);
    	musicSlider.setValue(game.getSettings().getMusicVolume());
    	musicSlider.addListener(new ChangeListener(){
    	  		@Override
    			public void changed(ChangeEvent event, Actor actor) {
    	  			game.getSettings().setMusicVolume(musicSlider.getValue());
    	  			game.getAudio().updateFromSettings();
    		}
    	});

        // Sound on/off checkbox
    	soundCheckbox = new CheckBox(null, skin);
    	soundCheckbox.setChecked(game.getSettings().isSoundActive());
    	soundCheckbox.addListener(new ChangeListener() {
    	    @Override
    	    public void changed(ChangeEvent event, Actor actor) {
    	              	game.getSettings().setSoundActive(soundCheckbox.isChecked());
    	              	game.getAudio().updateFromSettings();
    	    }
    	});	

    	// Sound volume slider
    	soundSlider = new Slider(0f, 1f, 0.05f, false, skin);
    	soundSlider.setValue(game.getSettings().getSoundVolume());
    	soundSlider.addListener(new ChangeListener() {
    	  		@Override
    			public void changed(ChangeEvent event, Actor actor) {
    	  			game.getSettings().setSoundVolume(soundSlider.getValue());
    	  			game.getAudio().updateFromSettings();
    		}
    	});

    	// Speed slider
    	speedSlider = new Slider(1f, 64f, 1f, false, skin);
    	speedSlider.setValue(game.getSettings().getGameSpeed());
    	speedSlider.addListener(new ChangeListener() {
    	  		@Override
    			public void changed(ChangeEvent event, Actor actor) {
    	  			game.getSettings().setGameSpeed(speedSlider.getValue());
    	  			game.setSpeed(speedSlider.getValue());
    		}
    	});

    	// Add back button
    	backButton = new TextButton("Back", skin); 
  
    	// Make and style table.
      	table.add(titleLabel).colspan(2).expandX();
      	table.row().pad(10,0,0,10);
    	table.add(speedLabel).left().expandX();
    	table.add(speedSlider).fillX().expandX();
    	table.row().pad(10,0,0,10);
    	table.add(musicCheckboxLabel).left().expandX();
    	table.add(musicCheckbox).expandX();
    	table.row().pad(10,0,0,10);
    	table.add(musicLabel).left().expandX();
    	table.add(musicSlider).fillX().expandX();
    	table.row().pad(10,0,0,10);
    	table.add(soundCheckboxLabel).left().expandX();
    	table.add(soundCheckbox).expandX();
    	table.row().pad(10,0,0,10);
    	table.add(soundLabel).left().expandX();
    	table.add(soundSlider).fillX().expandX();
    	table.row().pad(10,0,0,10);
    	table.add(backButton).colspan(2).expandX();
          

    	// Add listeners to back button.
    	backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
          		game.setScreen(new MainScreen(game, batch, levels, hud, audio));
          	    Gdx.app.log("GG-Clone", "back clicked.");
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
