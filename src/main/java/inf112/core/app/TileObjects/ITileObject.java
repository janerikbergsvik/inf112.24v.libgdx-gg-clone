package inf112.core.app.TileObjects;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * The Interface ITileObject.
 */
public interface ITileObject {

	/**
	 * Sets the collision category.
	 *
	 * @param bit the new collision category
	 */
	void setCollisionCategory(short bit);

	/**
	 * Gets the cell.
	 *
	 * @return the cell
	 */
	TiledMapTileLayer.Cell getCell();

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	int getScore();

	/**
	 * On hair contact.
	 */
	void onHairContact();

	/**
	 * On player contact.
	 */
	void onPlayerContact();

	/**
	 * On feet contact.
	 */
	void onFeetContact();

	/**
	 * On feet contact end.
	 */
	void onFeetContactEnd();

}