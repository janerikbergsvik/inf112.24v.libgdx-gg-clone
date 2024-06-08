package inf112.core.app.Characters;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface IEntity {
	
	/**
	 * Update entity position and texture
	 *
	 * @param delta_t the delta t
	 */
	void update(float delta_t);
	
	/**
	 * Creates a box2d body & fixtures for entity.
	 */
	void defineEntity();
	
	/**
	 * Draw the entity.
	 *
	 * @param batch the batch
	 */
	void draw(Batch batch);	
}
