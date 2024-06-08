package inf112.core.app.View;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * The Interface IGameRenderer.
 */
public interface IGameRenderer {

	/**
	 * Render the view part of the Model-view-controller.
	 */
	void render();

	/**
	 * Resize event.
	 *
	 * @param width the width
	 * @param height the height
	 */
	void resize(int width, int height);

	/**
	 * Dispose event.
	 */
	void dispose();

	/**
	 * Gets the stage.
	 *
	 * @return the stage
	 */
	public Stage getStage();
}