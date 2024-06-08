package inf112.core.app.View.GameScreens;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * The Interface IHud.
 */
public interface IHud {

	/**
	 * Update hud, typically timer.
	 *
	 * @param delta_t the delta t
	 */
	void update(float delta_t);

	/**
	 * Checks if time is out.
	 *
	 * @return true, if time is out
	 */
	boolean isTimeOut();

	/**
	 * Checks if is out of lives.
	 *
	 * @return true, if is out of lives
	 */
	boolean isOutOfLives();

	/**
	 * Change score.
	 *
	 * @param delta the delta
	 */
	void changeScore(int delta);

	/**
	 * Change lives.
	 *
	 * @param delta the delta
	 */
	void changeLives(int delta);

	/**
	 * Change level.
	 *
	 * @param delta the delta
	 */
	void changeLevel(int delta);

	/**
	 * Change time.
	 *
	 * @param delta the delta
	 */
	void changeTime(int delta);

	/**
	 * Gets the current score.
	 *
	 * @return the score
	 */
	int getScore();

	/**
	 * Reset the score.
	 */
	void resetScore();

	/**
	 * Gets the level.
	 *
	 * @return the level number
	 */
	int getLevel();

	/**
	 * Gets the stage.
	 *
	 * @return the stage
	 */
	Stage getStage();

}