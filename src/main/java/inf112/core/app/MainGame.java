package inf112.core.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.core.app.Helper.Audio;
import inf112.core.app.Helper.GameSettings;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.IGameSettings;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.Helper.LevelManager;
import inf112.core.app.View.GameScreens.Hud;
import inf112.core.app.View.MenuScreens.MainScreen;

/**
 * The Class MainGame.
 */
public class MainGame extends Game {
	// Pixels per meter conversion
	public static float PPM;
	
	// Game variables.
	private Batch batch;
	private IAudio audio;
	private Screen currentScreen;
	private IGameSettings settings;

	// Level manager and tiles variables
	private ILevelManager levels;
	
	// New hud object
	private Hud hud;
	
	@Override
	public void create() {
		// Logs events
		Gdx.app.log("GG-Clone", "started");
		
		// Batch shared. 
		batch = new SpriteBatch();
		
		// Get settings
		settings = new GameSettings();
		
		// Set game speed
		PPM = settings.getGameSpeed();

		// Sound/music manager
		audio = new Audio(settings);

		// Level manager
		levels = new LevelManager();

		// Make hud object
		hud = new Hud(batch, levels.getCurrentLevelNumber());

		// Set/change to main screen.
		changeScreen(new MainScreen(this, batch, levels, hud, audio));	
	}
	
    /**
     * Change screen.
     *
     * @param screen the screen
     */
    private void changeScreen(Screen screen) {
    	currentScreen = screen;
        setScreen(currentScreen);
        }
	
    @Override
    public void render() {
    	// Calls method from game class that renders screen.
    	super.render();
    }    
    
    @Override
    public void dispose() {
    }

	/**
	 * Sets the speed.
	 *
	 * @param value the new speed
	 */
	public void setSpeed(float value) {
		PPM = value;
	}
	
	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public float getSpeed() {
		return PPM;
	}	
	
	/**
	 * Gets the audio.
	 *
	 * @return the audio
	 */
	public IAudio getAudio() {
		return audio;
		}

	/**
	 * Gets the settings.
	 *
	 * @return the settings
	 */
	public IGameSettings getSettings() {
		return settings;
	}

}
