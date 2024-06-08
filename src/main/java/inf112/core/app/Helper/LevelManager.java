package inf112.core.app.Helper;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * The Class LevelManager.
 */
public class LevelManager implements ILevelManager {
	// Level variables
	private List<String> levels;
	private String currentLevelPath;
	private int index;
	private boolean isCompleted;
	
	// Tiles map
	private TmxMapLoader maploader;
	private TiledMap currentMap;

	/**
	 * Instantiates a new level manager.
	 */
	public LevelManager() {
		// Index
		index = 0;
		
		// Add maps to list
		levels = new LinkedList<String>();
		levels.add("levels//tilesets_scaled_32x32/level1.tmx");
		levels.add("levels//tilesets_scaled_32x32/level2.tmx");
	
		// Load first map
		maploader = new TmxMapLoader();
		currentMap = getCurrentLevel();
		
		// Game not completed
		isCompleted = false;
	}

	@Override
	public TiledMap getCurrentLevel() {
		currentLevelPath = levels.get(index);
		currentMap = maploader.load(currentLevelPath);
		return currentMap;
	}
	
	@Override
	public int getCurrentLevelNumber() {
		return (index + 1);
	}

	@Override
	public String getCurrentLevelPath() {
		return currentLevelPath;
	}

	@Override
	public boolean nextLevel() {
		if (index+1 <= levels.size()-1) {
			index++;
			currentLevelPath = levels.get(index);
			currentMap = maploader.load(currentLevelPath);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean nextLevelAvailable() {
		if (index+1 <= levels.size()-1) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isCompleted() {
		return isCompleted;
	}
	
	@Override
	public void setCompleted() {
		isCompleted = true;
	}

	@Override
	public void reset() {
		isCompleted = false;
		index = 0;
	}
}
