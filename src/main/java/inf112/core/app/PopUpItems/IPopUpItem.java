package inf112.core.app.PopUpItems;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * The Interface IPopUpItem.
 */
public interface IPopUpItem {

	/**
	 * Use popupitem method.
	 */
	void use();

	/**
	 * Draw popupitem.
	 *
	 * @param batch the batch
	 */
	void draw(Batch batch);

	/**
	 * Destroy popupitem.
	 */
	void destroy();

	/**
	 * Update popupitem method.
	 *
	 * @param dt the dt
	 */
	void update(float dt);

	/**
	 * Reverse velocity.
	 *
	 * @param x the x
	 * @param y the y
	 */
	void reverseVelocity(boolean x, boolean y);

	/**
	 * Gets a random direction for popupitem.
	 *
	 * @return the random direction
	 */
	int getRandomDirection();

	/**
	 * Define entity.
	 */
	void defineEntity();

}