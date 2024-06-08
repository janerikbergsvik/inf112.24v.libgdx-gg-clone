package inf112.core.app.Characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.core.app.Characters.GianaPlayer.State;

/**
 * The Interface IPlayer.
 */
public interface IPlayer extends IEntity {
	
	/**
	 * Returns true if player is running to the right.
	 *
	 * @return true or false
	 */
	boolean runningRight();

	/**
	 * Returns the correct state according to movement.
	 *
	 * @return the state
	 */
	State getState();

	/**
	 * Retrieves Player's TextureRegion based on state.
	 *
	 * @param delta_t the delta t
	 * @return the frame
	 */
	TextureRegion getFrame(float delta_t);

	/**
	 * Shoot a bullet.
	 */
	void shoot();

	/**
	 * Sets if player is in contact with ground.
	 *
	 * @param groundContact the new ground contact
	 */
	void setGroundContact(boolean groundContact);

	/**
	 * Gets if player is in contact with ground.
	 *
	 * @return the ground contact
	 */
	boolean getGroundContact();

	/**
	 * Apply health change.
	 *
	 * @param change the health
	 */
	void applyHealthChange(int change);

	/**
	 * Sets if completed level.
	 *
	 * @param status true if completed level, false if not.
	 * @return true, if completed
	 */
	boolean setCompletedLevel(boolean status);

	/**
	 * Checks for completed level.
	 *
	 * @return true, if successful
	 */
	boolean hasCompletedLevel();

	/**
	 * Checks if is dead.
	 *
	 * @return true, if is dead
	 */
	boolean isDead();

	/**
	 * Sets the speed factor.
	 *
	 * @param factor the new speed factor
	 */
	void setSpeedFactor(float factor);

	/**
	 * Gets the speed factor.
	 *
	 * @return the speed factor
	 */
	float getSpeedFactor();

	/**
	 * Gets the speed X.
	 *
	 * @return the speed X
	 */
	float getSpeedX();

	/**
	 * Gets the speed Y.
	 *
	 * @return the speed Y
	 */
	float getSpeedY();

	/**
	 * Sets the double lightning timeout.
	 *
	 * @param timeout the new double lightning timeout
	 */
	void setDoubleLightningTimeout(float timeout);

	/**
	 * Gets the double lightning timeout.
	 *
	 * @return the double lightning timeout
	 */
	float getDoubleLightningTimeout();

	/**
	 * Sets the single lightning timeout.
	 *
	 * @param timeout the new single lightning timeout
	 */
	void setSingleLightningTimeout(float timeout);

	/**
	 * Gets the single lightning timeout.
	 *
	 * @return the single lightning timeout
	 */
	float getSingleLightningTimeout();
	
	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	Body getBody();

	/**
	 * Left pressed.
	 */
	void moveLeft();

	/**
	 * Right pressed.
	 */
	void moveRight();

	/**
	 * Up pressed.
	 */
	void moveUp();

	/**
	 * Down pressed.
	 */
	void moveDown();

	/**
	 * Sets the shoot active.
	 *
	 * @param value the new shoot active
	 */
	void setShootActive(boolean value);

	/**
	 * Checks if is shoot active.
	 *
	 * @return true, if is shoot active
	 */
	boolean isShootActive();

	/**
	 * Checks if is cheat shoot active.
	 *
	 * @return true, if is cheat shoot active
	 */
	boolean isCheatShootActive();
	
	/**
	 * Sets the cheat shoot active.
	 *
	 * @param value the new cheat shoot active
	 */
	void setCheatShootActive(boolean value);

}