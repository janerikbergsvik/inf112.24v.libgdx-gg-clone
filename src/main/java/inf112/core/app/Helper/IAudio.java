package inf112.core.app.Helper;

/**
 * The Interface IAudio.
 */
public interface IAudio {

	/**
	 * Update audio settings from game settings.
	 */
	void updateFromSettings();

	/**
	 * Start music.
	 */
	void startMusic();

	/**
	 * Stop music.
	 */
	void stopMusic();

	/**
	 * Gets the sound volume.
	 *
	 * @return the sound volume
	 */
	float getSoundVolume();

	/**
	 * Play pick up sound.
	 */
	void playPickUp();

	/**
	 * Play died sound.
	 */
	void playDied();

	/**
	 * Play completed level sound.
	 */
	void playCompletedLevel();

}