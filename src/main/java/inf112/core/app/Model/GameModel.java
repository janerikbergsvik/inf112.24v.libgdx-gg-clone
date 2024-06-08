package inf112.core.app.Model;

import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.GianaPlayer;
import inf112.core.app.Characters.IEnemy;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Characters.IProjectile;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.Helper.ObjectListener;
import inf112.core.app.Helper.PhysicsWorldCreator;
import inf112.core.app.PopUpItems.Ball;
import inf112.core.app.PopUpItems.Clock;
import inf112.core.app.PopUpItems.DoubleLightning;
import inf112.core.app.PopUpItems.IPopUpItem;
import inf112.core.app.PopUpItems.PopUpFactoryDef;
import inf112.core.app.PopUpItems.SingleLightning;
import inf112.core.app.View.GameScreens.Hud;
import inf112.core.app.View.GameScreens.IHud;
import inf112.core.app.View.GameScreens.StageIntroScreen;
import inf112.core.app.View.MenuScreens.CompletedScreen;
import inf112.core.app.View.MenuScreens.GameOverScreen;

/**
 * The Class GameModel.
 * The model part of the Model-view-controller.
 */
public class GameModel implements IGameModel {
	// Batch
	private Batch batch;
	
	// Level manager and tiles map variables
	private TiledMap map;
	private ILevelManager levels;
	
	// Hud
	private IHud hud;
	
	// Textures for player- and enemies
	private TextureAtlas atlas;
	private TextureAtlas powerups;
	
    // Box2d physics
	private World world;
	private PhysicsWorldCreator creator;
    
    // Player character
	private IPlayer player;
	
	// Bullet
	private Array<IProjectile> bullets;		
	
	// Popup items
    private Array<IPopUpItem> popUpItems;
    private LinkedBlockingQueue<PopUpFactoryDef> itemsToSpawn;    
    
    // Maingame itself.
	private MainGame game;

	// Audio from MainGame
	private IAudio audio;

	
 /**
  * Instantiates a new game model.
  *
  * @param game the MainGame
  * @param audio the audio
  * @param levels the levels
  * @param hud the hud
  * @param batch the batch
  * @param width the width
  * @param height the height
  */
 public GameModel(MainGame game, IAudio audio, ILevelManager levels, IHud hud, Batch batch, float width, float height) { 	
	// Retrieve MainGame
	this.game = game;
	this.audio = audio;
	this.levels = levels;
	this.hud = hud;
	this.batch = batch;
		 
	// Get current level
	map = levels.getCurrentLevel();	
	
	// Load sisters and enemies pack.
    atlas = new TextureAtlas(Gdx.files.internal("textures/sisters_and_enemies/sisters_and_enemies.pack")); 
    
    // Load power-up pack.
    powerups = new TextureAtlas(Gdx.files.internal("textures/powerups/powerups.atlas"));   

    // Create a world with gravity.
	world = new World(new Vector2(0,-9.81f), true);

    // Set contact listener.
    world.setContactListener(new ObjectListener());    
    
    // Popup items
    popUpItems = new Array<IPopUpItem>();
    itemsToSpawn = new LinkedBlockingQueue<PopUpFactoryDef>();
    
    // Create bullets    
    bullets = new Array<IProjectile>();   
    
    // Create Giana player & Physics world.
    player = new GianaPlayer(world, atlas, bullets);
	creator = new PhysicsWorldCreator(itemsToSpawn, hud, atlas, player, world, map, audio);        	
 }

/**
 * Handle spawning items.
 */
private void handleSpawningItems() {
	 if(!itemsToSpawn.isEmpty()) {
		 PopUpFactoryDef idef = itemsToSpawn.poll();
		 if(idef.type == Clock.class) {
			 popUpItems.add(new Clock(player, world, hud, powerups, idef.position.x, idef.position.y));
		 }
		 if(idef.type == SingleLightning.class) {
			 popUpItems.add(new SingleLightning(player, world, hud, powerups, idef.position.x, idef.position.y));
		 }
		 if(idef.type == DoubleLightning.class) {
			 popUpItems.add(new DoubleLightning(player, world, hud, powerups, idef.position.x, idef.position.y));			
		 }
		 if(idef.type == Ball.class) {
			popUpItems.add(new Ball(player, world, hud, powerups, idef.position.x, idef.position.y));			 			
		 }		 
	 }
 }
 
 @Override
public void update(float delta) {
	 // Model updates
	 // Update hud
	 hud.update(delta);
	 
	 // Handle spawning pop-up items.
	 handleSpawningItems();
	 
	 // Takes a step in the physics simulation
     world.step(1/60f, 6, 3);
  
     // Here player states are handled;
     // I.e. handling of out of health, life reduction, redirection to completion of level, game over,
     // time out, etc.
     handlePlayerStates();
     
     // Update player position- and texture based on player state.
     player.update(delta);

     // Update enemies
	 for(IEnemy enemy : creator.getEnemies())
	 	enemy.update(delta);
     
	 // Update bullets
     if (bullets != null) {
     	for(IProjectile bullet : bullets) {
             bullet.update(delta);
             if(bullet.isScheduledForRemoval())
                 bullets.removeValue(bullet, true);
         }	
     }     
     
     // Update popupitems
     if (popUpItems != null) {
      	for(IPopUpItem item : popUpItems) {
              item.update(delta);
          }	
      }     
 }

@Override
public PhysicsWorldCreator getCreator(){
	return creator;
 }


@Override
public IPlayer getPlayer() {
     return player;
 }

@Override
public TiledMap getMap() {
	return map;
}

@Override
public Array<IProjectile> getBullets() {
	return bullets;	
}

@Override
public Array<IPopUpItem> getPopUpItems() {
	return popUpItems;
}

@Override
public IHud getHud() {
	return hud;
	
}

/**
 * Handle player states.
 */
private void handlePlayerStates() {
    // If time is up, then set player health to zero.
    // Next part will handle if lives left, etc.
    if (hud.isTimeOut()) {
    	player.applyHealthChange(-99999);
    	Gdx.app.log("Player is out of time. Health set to zero.", "");
     }
 
    // If player is dead and out of lives, go to game over screen.
    // Else reduce lives and retry at same level.
    if (player.isDead()) {
   	 hud.changeLives(-1);
	 audio.playDied();
   	 
   	 if (hud.isOutOfLives()) {
   		 Gdx.app.log("Player is dead and out of lives. Game over.", "");
       	 levels.reset();
         game.getSettings().setHighScore(hud.getScore());
         hud.changeLevel(-9999);
		 hud.changeTime(-9999);
		 hud.changeTime(300);
		 hud.changeLives(3);
  		 game.setScreen(new GameOverScreen(game, batch, levels, hud, audio));  		            
   	 }
   	 else {
   		 Gdx.app.log("Player has more lives, retry stage", "");
   		 game.getSettings().setHighScore(hud.getScore());
   		 hud.resetScore();
   		 game.setScreen(new StageIntroScreen(game, batch, levels, hud, audio));
   	 }
    }
    
    // Player has completed a level
    if (player.hasCompletedLevel()) {
	   	player.setCompletedLevel(false);
	   	game.getSettings().setHighScore(hud.getScore());
	   	Gdx.app.log("Player has completed level.", "");
	   	 
	   	// Go to next level
	    if (levels.nextLevelAvailable()) {
	     		Gdx.app.log("Completed level. Go to next level.", "");
	     		levels.nextLevel();
				hud.changeLevel(1);
				hud.changeTime(-9999);
				hud.changeTime(300);
				game.setScreen(new StageIntroScreen(game, batch, levels, hud, audio));
			}
	     	
     	// Levels have been completed.
     	else {
	     		levels.reset();
	     		hud.changeLevel(1);
				hud.changeTime(-9999);
				hud.changeTime(300);
				hud.changeLives(-9999);
				hud.changeLives(3);
				Gdx.app.log("No more levels available. Completed game.", "");
	     		game.setScreen(new CompletedScreen(game, batch, levels, hud, audio));        		
	     	}	
	    }
}

}
