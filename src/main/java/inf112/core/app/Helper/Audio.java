package inf112.core.app.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * The Class Audio.
 */
public class Audio implements IAudio {
	// Initiation
	private AssetManager manager;
	private Music music;
	private float soundVolume;
	private float musicVolume;
	private boolean musicActive;
	private boolean soundActive;
	private IGameSettings settings;
	
	/**
	 * Instantiates a new audio instance.
	 *
	 * @param settings the settings
	 */
	public Audio(IGameSettings settings) {		
		// Assetmanager for sound.
		manager = new AssetManager();
		manager.load("music/kim-lightyear-leave-the-world-tonight-chiptune-edit-loop-132102.mp3", Music.class);
		manager.load("sounds/mixkit-little-piano-game-over-1944.wav", Sound.class);
		manager.load("sounds/mixkit-game-level-completed-2059.wav", Sound.class);
		manager.load("sounds/scale-f6-106128.mp3", Sound.class);
		
		manager.finishLoading();
	
		// Retrieve game settings.
		this.settings = settings;
		
		// Apply game settings.
		updateFromSettings();		
	}

	@Override
	public void updateFromSettings() {
		soundVolume = settings.getSoundVolume();
		musicVolume = settings.getMusicVolume();
		soundActive = settings.isSoundActive();
		musicActive = settings.isMusicActive();
		
		if (music != null) {
			music.setVolume(musicVolume);
			}
		}
	
	@Override
	public void startMusic() {
		updateFromSettings();
		if  (musicActive) {
			music = manager.get("music/kim-lightyear-leave-the-world-tonight-chiptune-edit-loop-132102.mp3", Music.class);
			music.setLooping(true);
			music.setVolume(musicVolume);
			music.play();
		}
	}

	@Override
	public void stopMusic() {
		updateFromSettings();
		music.setVolume(0.0f);
		music.setLooping(false);
		music.stop();
	}

	@Override
	public float getSoundVolume() {
		if ((settings.getSoundVolume() > 0.0f) && (settings.isSoundActive())) {
			Gdx.app.log("Sound is active", "");
			return settings.getSoundVolume();
		}
		Gdx.app.log("Sound is not active", "");
		return 0.0f;		
	}

	@Override
	public void playPickUp() {
		manager.get("sounds/scale-f6-106128.mp3", Sound.class).play(getSoundVolume());
	}
	
	@Override
	public void playDied() {
		manager.get("sounds/mixkit-little-piano-game-over-1944.wav", Sound.class).play(getSoundVolume());
	}

	@Override
	public void playCompletedLevel() {
		manager.get("sounds/mixkit-game-level-completed-2059.wav", Sound.class).play(getSoundVolume());
	}
}
		
