package inf112.core.app.Helper;

import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.IEnemy;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Characters.Insect;
import inf112.core.app.Characters.Owl;
import inf112.core.app.PopUpItems.PopUpFactoryDef;
import inf112.core.app.TileObjects.Coin;
import inf112.core.app.TileObjects.DeathBlock;
import inf112.core.app.TileObjects.FinishLine;
import inf112.core.app.TileObjects.Ground;
import inf112.core.app.TileObjects.Powerup;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class PhysicsWorldCreator.
 */
public class PhysicsWorldCreator {
    private Array<IEnemy> enemies;
       
    /**
     * Instantiates a new physics world creator.
     *
     * @param itemsToSpawn the items to spawn
     * @param hud the hud
     * @param atlas the atlas with textures
     * @param player the player
     * @param world the box2d world
     * @param map the tiled map
     * @param audio the audio
     */
    public PhysicsWorldCreator(LinkedBlockingQueue<PopUpFactoryDef> itemsToSpawn, IHud hud, TextureAtlas atlas, IPlayer player, World world, TiledMap map, IAudio audio){        
        // Create ground bodies/fixtures
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                
            new Ground(hud, player, world, map, audio, rect);        
            }

        // Create coins bodies/fixtures
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(hud, player, world, map, audio, rect);
        }

        // Create deathblock bodies/fixtures
        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new DeathBlock(hud, player, world, map, audio, rect);
        }

        // Create powerup bodies/fixtures
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
                        
			new Powerup(itemsToSpawn, hud, player, world, map, audio, rect);
        }

        // Create finish line bodies/fixtures
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new FinishLine(hud, player, world, map, audio, rect);
        }

        // Create all enemies
        enemies = new Array<IEnemy>();
        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            enemies.add(new Owl(world, hud, atlas, rect.getX() / MainGame.PPM, rect.getY()/MainGame.PPM));
            enemies.add(new Insect(world, hud, atlas, rect.getX() / MainGame.PPM, rect.getY()/MainGame.PPM));
        }
    }        	  

    /**
     * Gets the enemies.
     *
     * @return the enemies
     */
    public Array<IEnemy> getEnemies(){
        return enemies;
    }
}
