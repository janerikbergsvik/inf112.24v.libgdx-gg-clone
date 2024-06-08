package inf112.core.app.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.Bullet;
import inf112.core.app.Characters.GianaPlayer;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ILevelManager;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.View.GameScreens.Hud;
import inf112.core.app.View.GameScreens.IHud;
import inf112.core.app.View.MenuScreens.MainScreen;

/**
 * The Class GameController.
 * The Controller part of the Model-view-controller.
 */
public class GameController implements IGameController {
    private IGameModel model;
	private Batch batch;
	private MainGame game;
	private ILevelManager levels;
	private IHud hud;
	private IAudio audio;
	
    /**
     * Instantiates a new game controller.
     *
     * @param game the MainGame
     * @param model the Model part of the Model-view-controller.
     * @param batch the batch
     * @param levels the levels
     * @param hud the hud
     * @param audio the audio
     */
    public GameController(MainGame game, IGameModel model, Batch batch, ILevelManager levels, IHud hud, IAudio audio) {
        this.model = model;
        this.batch = batch;
        this.game = game;
        this.levels = levels;
        this.hud = hud;
        this.audio = audio;
	}
 
    @Override
	public void handleInput(float delta_t) {
    	// Get current instance of player.
    	IPlayer player = model.getPlayer();
    	
      	if (Gdx.input.isTouched()) {
      		Gdx.app.log("Input touched.","");        	
    		}
    	
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
        	Gdx.app.log("Left key pressed.", "");       
        	player.moveLeft();
        }
    		
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
        	Gdx.app.log("Right key pressed.", "");
        	player.moveRight(); 
        }

        if((Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W))) {
        	Gdx.app.log("Up key pressed.", "");
        	player.moveUp();
        }

        if((Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S))) {
        	Gdx.app.log("Down key pressed.", "");
        	player.moveDown();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
        	if(player.isShootActive()) {
                Gdx.app.log("Space key pressed.", "");
                
           	    player.shoot();	
            }
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            Gdx.app.log("N pressed. Level to be skipped.", "");
            player.setCompletedLevel(true);
        }
    
  		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
  			Gdx.app.log("Escape key pressed.", "");
  			levels.reset();
			game.setScreen(new MainScreen(game, batch, levels, new Hud(batch, 1), audio));  		
  			}       
  		
  		if (Gdx.input.isKeyJustPressed(Input.Keys.D) && Gdx.input.isKeyJustPressed(Input.Keys.I) && Gdx.input.isKeyJustPressed(Input.Keys.G)) {
    	    Gdx.app.log("DIGI cheat activated.", "");
  			hud.changeTime(300);
  	    	hud.changeLives(9);
  	    	player.applyHealthChange(9999);
  	    	player.setShootActive(true);
  	    	player.setCheatShootActive(true);
  		}
    }
}