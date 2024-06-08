package inf112.core.app.Characters;

/**
 * The Interface IEnemy.
 */
public interface IEnemy extends IEntity {

	/**
	 * Method typically executed when Enemy is hit on head.
	 */
	void hitOnHead();

	/**
	 * Reverse velocity.
	 * Typically executed if enemy should turn around.
	 *
	 * @param x the x
	 * @param y the y
	 */
	void reverseVelocity(boolean x, boolean y);

	/**
	 * Gets the amount of damage that enemy inflicts.
	 *
	 * @return the damage
	 */
	int getDamage();

}
