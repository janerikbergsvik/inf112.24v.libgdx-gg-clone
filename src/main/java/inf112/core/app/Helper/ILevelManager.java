package inf112.core.app.Helper;

import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * The Interface ILevelManager.
 */
public interface ILevelManager {

	/**
	 * Gets the current level tiledmap.
	 *
	 * @return the current level tiledmap.
	 */
	TiledMap getCurrentLevel();

	/**
	 * Gets the current level number.
	 *
	 * @return the current level number
	 */
	int getCurrentLevelNumber();

	/**
	 * Gets the current level path.
	 *
	 * @return the current level path
	 */
	String getCurrentLevelPath();

	/**
	 * Go to next level, if available.
	 *
	 * @return true, if successful
	 */
	boolean nextLevel();

	/**
	 * Check if next level is available
	 *
	 * @return true, if successful
	 */
	boolean nextLevelAvailable();

	/**
	 * Checks if level is completed.
	 *
	 * @return true, if is completed
	 */
	boolean isCompleted();

	/**
	 * Sets the level to completed.
	 */
	void setCompleted();

	/**
	 * Reset level counter.
	 */
	void reset();

}