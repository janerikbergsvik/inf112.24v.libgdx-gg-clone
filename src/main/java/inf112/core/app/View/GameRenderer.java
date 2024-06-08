package inf112.core.app.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.core.app.Main;
import inf112.core.app.MainGame;
import inf112.core.app.Characters.IEnemy;
import inf112.core.app.Characters.IProjectile;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.PopUpItems.IPopUpItem;
import inf112.core.app.View.GameScreens.IHud;
import inf112.core.app.View.GameScreens.PowerupText;

/**
 * The Class GameRenderer.
 * The view part of the Model-view-controller.
 */
public class GameRenderer implements IGameRenderer {

// Model
private IGameModel model;

// Screen variables
private Viewport viewport;
private OrthographicCamera cam;
private Batch batch;

// Tiled level variables
private OrthogonalTiledMapRenderer tiledmaprenderer;

// Box2d
private Box2DDebugRenderer b2dr;

// Listener variables
public Stage stage;

// Hud
private IHud hud;

// Powerup active screen
private PowerupText powerupText;


/**
 * Instantiates a new game renderer.
 *
 * @param model the model
 * @param batch the batch
 * @param hud the hud
 */
public GameRenderer(IGameModel model, Batch batch, IHud hud) {
	 // Set initial parameters
    this.model = model;
    this.batch = batch;
    this.hud = hud;
	 
	// Set Powerup active screen
	powerupText = new PowerupText(batch);

    // Camera for following the player
    // Viewport for setting aspect ratio despire screen size
    cam = new OrthographicCamera();
    viewport = new FitViewport(Main.WINDOW_WIDTH / MainGame.PPM, Main.WINDOW_HEIGHT / MainGame.PPM, cam);
    viewport.apply(); // Not sure if necessary
    
	// Render map
    tiledmaprenderer = new OrthogonalTiledMapRenderer(model.getMap(), 1 / MainGame.PPM);
	
    // Setting camera start position
	cam.position.set(0, viewport.getWorldHeight()/2, 0);
	cam.update();
 }


 @Override
public void render() {	
     	// Set camera x position to player coordinates and update
	 	cam.position.x = model.getPlayer().getBody().getPosition().x;
	 	cam.update();
   
		// Clear screen.
		Gdx.gl.glClearColor(0, 0, 0, 0);;
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Draw tile map
		tiledmaprenderer.setView(cam);
		tiledmaprenderer.render();
		
		// Draw player
		batch.setProjectionMatrix(cam.combined);
		batch.begin();  
		model.getPlayer().draw(batch);
		
		// Draw enemies
		for(IEnemy enemy : model.getCreator().getEnemies())
	 		enemy.draw(batch);
		
		// Draw pop-up-items
		for(IPopUpItem popUpItem : model.getPopUpItems()) {
			popUpItem.draw(batch);
		}
			
		// Draw bullets
	    if (model.getBullets() != null) {
	    	for(IProjectile bullet : model.getBullets())
	    		bullet.draw(batch);
		    }	    

	    batch.end();

		// Draw hud
		batch.setProjectionMatrix(hud.getStage().getCamera().combined);
		hud.getStage().draw();

		// Draw Powerup
		batch.setProjectionMatrix(powerupText.stage.getCamera().combined);
		if(model.getPlayer().isShootActive() && !model.getPlayer().isCheatShootActive()){
			powerupText.update(model.getPlayer().getSingleLightningTimeout());
			powerupText.stage.draw();
		}
 }
 
@Override
public void resize(int width, int height) {
		// Resize viewport and reposition camera.
		viewport.update(width,  height);
	    cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
	    cam.update();		  		
	}

@Override
public void dispose() {
		model.getMap().dispose();
		tiledmaprenderer.dispose();
		batch.dispose();
 }

@Override
public Stage getStage() {
	return stage;
}

}
