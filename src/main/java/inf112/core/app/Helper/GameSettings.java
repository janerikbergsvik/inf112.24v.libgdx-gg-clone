package inf112.core.app.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
	 
	/**
	 * The Class GameSettings.
	 */
	public class GameSettings implements IGameSettings {
		private static final String SET_NAME = "gg-clone-settings";
		private static final String MUSIC_VOL = "music_vol";
		private static final String MUSIC_ACTIVE = "music_active";
		private static final String SOUND_VOL = "sound_vol";
		private static final String SOUND_ACTIVE = "sound_active";
		private static final String GAME_SPEED = "game_speed";
		private static final String HIGH_SCORE = "high_score";
		
		/**
		 * Gets the stored game settings.
		 *
		 * @return the game settings
		 */
		private Preferences getGameSettings() {
			return Gdx.app.getPreferences(SET_NAME);
		}
			
		@Override
		public float getMusicVolume() {
			return getGameSettings().getFloat(MUSIC_VOL, 0.5f);
			}
	
		@Override
		public void setMusicVolume(float volume) {
			getGameSettings().putFloat(MUSIC_VOL, volume);
			getGameSettings().flush();
			}

		@Override
		public boolean isMusicActive() {
			return getGameSettings().getBoolean(MUSIC_ACTIVE, true);
		}

		@Override
		public void setMusicActive(boolean status) {
			getGameSettings().putBoolean(MUSIC_ACTIVE, status);
			getGameSettings().flush();			
		}

		@Override
		public float getSoundVolume() {
			return getGameSettings().getFloat(SOUND_VOL, 0.5f);
			}

		@Override
		public void setSoundVolume(float volume) {
			getGameSettings().putFloat(SOUND_VOL, volume);
			getGameSettings().flush();
			}
	
		@Override
		public boolean isSoundActive() {
			return getGameSettings().getBoolean(SOUND_ACTIVE, true);
		}
		
		@Override
		public void setSoundActive(boolean status) {
			getGameSettings().putBoolean(SOUND_ACTIVE, status);
			getGameSettings().flush();			
		}

		@Override
		public float getGameSpeed() {
			return getGameSettings().getFloat(GAME_SPEED, 32f);
			}

		@Override
		public void setGameSpeed(float speed) {
			getGameSettings().putFloat(GAME_SPEED, speed);
			getGameSettings().flush();
			}

		@Override
		public int getHighScore() {
			return (int) getGameSettings().getFloat(HIGH_SCORE, 0);
			}

		@Override
		public boolean setHighScore(float score) {
			if (getHighScore() <= score) {
				getGameSettings().putFloat(HIGH_SCORE, score);
				getGameSettings().flush();
				
				return true;
				}
			
			return false;
			}
}
