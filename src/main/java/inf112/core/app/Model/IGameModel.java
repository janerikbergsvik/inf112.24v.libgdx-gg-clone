package inf112.core.app.Model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.BatchTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Characters.IProjectile;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.Helper.PhysicsWorldCreator;
import inf112.core.app.PopUpItems.IPopUpItem;
import inf112.core.app.PopUpItems.PopUpItem;
import inf112.core.app.View.GameScreens.Hud;
import inf112.core.app.View.GameScreens.IHud;
import inf112.core.app.PopUpItems.PopUpFactoryDef;

/**
 * The Interface IGameModel.
 */
public interface IGameModel {

	// Player can collide with the following mask bits
	short GROUND_BIT = 1;
	short GIANA_BIT = 2;
	short COLLAPSED_BIT = 4;
	short COIN_BIT = 8;
	short OBJECT_BIT = 16;
	short POWERUP_BIT = 32;
	short EMPTY_BIT = 64;
	short BULLET_BIT = 128;
	short ENEMY_BIT = 256;
	short ENEMY_HEAD_BIT = 512;
	short POPUP_ITEM_BIT = 1024;
	short FINISH_GAME_BIT = 2048;

	/**
	 * Update for model used for the Model-view-controller
	 *
	 * @param delta the delta
	 */
	void update(float delta);

	/**
	 * Gets the creator with all world objects.
	 *
	 * @return the creator
	 */
	// Get creator
	PhysicsWorldCreator getCreator();

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	IPlayer getPlayer();

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	TiledMap getMap();
	
	/**
	 * Gets the bullets.
	 *
	 * @return the bullets
	 */
	Array<IProjectile> getBullets();

	/**
	 * Gets the pop up items.
	 *
	 * @return the pop up items
	 */
	Array<IPopUpItem> getPopUpItems();

	/**
	 * Gets the hud.
	 *
	 * @return the hud
	 */
	IHud getHud();
}