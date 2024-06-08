package inf112.core.app.Helper;

/**
 * The Interface IGameSettings.
 */
public interface IGameSettings {

	/**
	 * Gets the music volume.
	 *
	 * @return the music volume
	 */
	float getMusicVolume();

	/**
	 * Sets the music volume.
	 *
	 * @param volume the new music volume
	 */
	void setMusicVolume(float volume);

	/**
	 * Checks if music is active.
	 *
	 * @return true, if is music active
	 */
	boolean isMusicActive();

	/**
	 * Sets the music active status.
	 *
	 * @param status Active then true, Not active then false.
	 */
	void setMusicActive(boolean status);

	/**
	 * Gets the sound volume.
	 *
	 * @return the sound volume
	 */
	float getSoundVolume();

	/**
	 * Sets the sound volume.
	 *
	 * @param volume the new sound volume
	 */
	void setSoundVolume(float volume);

	/**
	 * Checks if sound is active.
	 *
	 * @return true, if sound is active
	 */
	boolean isSoundActive();

	/**
	 * Sets the sound active status.
	 *
	 * @param status Active then true, Not active then false.
	 */
	void setSoundActive(boolean status);

	/**
	 * Gets the game speed.
	 *
	 * @return the game speed
	 */
	float getGameSpeed();

	/**
	 * Sets the game speed.
	 *
	 * @param speed the new game speed
	 */
	void setGameSpeed(float speed);

	/**
	 * Gets the high score.
	 *
	 * @return the high score
	 */
	int getHighScore();

	/**
	 * Sets the high score.
	 *
	 * @param score the score
	 * @return true, if successful
	 */
	boolean setHighScore(float score);

}