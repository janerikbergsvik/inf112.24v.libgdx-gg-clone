package inf112.core.app.View.GameScreens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.core.app.Main;
import inf112.core.app.Helper.GameLabelStyler;

/**
 * The Class Hud.
 */
public class Hud implements IHud {
	// Stage & viewport
    public Stage stage;
	private Viewport viewport;
	
	// Counters
	private Integer levelCountDown;
	private float timeCounter;
	private Integer score;
	private Integer lives;
	private Integer level;

	// Labels
	private Label scoreLabel_text;
	private Label scoreLabel_num;
	private Label livesLabel_text;
	private Label livesLabel_num;
	private Label stageLabel_text;
	private Label stageLabel_num;
	private Label timeLabel_text;
	private Label timeLabel_num;
	private Label.LabelStyle labelstyle;

    /**
     * Instantiates a new hud.
     *
     * @param batch the batch
     * @param level the level
     */
    public Hud(Batch batch, int level){
    	// Init variables
    	this.levelCountDown = 300;
    	this.timeCounter = 0;
    	this.score = 0;
    	this.lives = 3;
        this.level = level;
        
        // Setup Hud table
        initHudTable(batch, level);
    }

	/**
	 * Inits the hud table.
	 *
	 * @param batch the batch
	 * @param level the level
	 */
	private void initHudTable(Batch batch, int level) {
		viewport = new FitViewport(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        // Get great Giana Sisters TrueType font.
        labelstyle = GameLabelStyler.getLabelStyle("truetypefonts/giana/Giana.ttf", 17, 227, 230, 106);
        
        scoreLabel_num = new Label(String.format("%06d", score), labelstyle);
        scoreLabel_text = new Label(String.format("GIANA"), labelstyle);
        livesLabel_num = new Label(String.format("%02d", lives), labelstyle);
        livesLabel_text = new Label(String.format("LIVES"),  labelstyle);
        stageLabel_num = new Label(String.format("%01d", level), labelstyle);
        stageLabel_text = new Label(String.format("STAGE"), labelstyle);
        timeLabel_num = new Label(String.format("%03d", levelCountDown), labelstyle);
        timeLabel_text = new Label(String.format("TIME"), labelstyle);

        table.add(scoreLabel_text).expandX().padTop(10);
        table.add(livesLabel_text).expandX().padTop(10);
        table.add(stageLabel_text).expandX().padTop(10);
        table.add(timeLabel_text).expandX().padTop(10);

        table.row();

        table.add(scoreLabel_num).expandX();
        table.add(livesLabel_num).expandX();
        table.add(stageLabel_num).expandX();
        table.add(timeLabel_num).expandX();

        stage.addActor(table);
	}
    
    @Override
	public void update(float delta_t) {
    	timeCounter += delta_t;
    	
    	// If reached 1 second, then count 1 second down.
    	if (timeCounter >= 1) {
    		timeCounter = 0;
    		levelCountDown = levelCountDown - 1;
    	 	}
    	
    	// Modify level count down label.
        timeLabel_num.setText(String.format("%03d", levelCountDown));
    }

	@Override
	public boolean isTimeOut() {
		if (levelCountDown <= 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isOutOfLives() {
		if (lives <= 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public void changeScore(int delta) {
		score = score + delta;
		if(score <= 0) {
			score = 0;
		}
		scoreLabel_num.setText(String.format("%06d", score));
	}

	@Override
	public void changeLives(int delta) {
		lives = lives + delta;
		if(lives <= 0) {
			lives = 0;
		}
		livesLabel_num.setText(String.format("%02d", lives));
		}

	@Override
	public void changeLevel(int delta) {
		level = level + delta;
		if(level <= 1) {
			level = 1;
		}
		stageLabel_num.setText(String.format("%01d", level));
        }

	@Override
	public void changeTime(int delta) {
		levelCountDown = levelCountDown + delta;
		if(levelCountDown <=0) {
			levelCountDown = 0;
		}
		
		timeLabel_num.setText(String.format("%03d", levelCountDown));
        }

	@Override
	public int getLevel() {
		return level.intValue();
	}

	@Override
	public int getScore() {
		return score.intValue();
	}

	@Override
	public void resetScore() {
		score = 0;
		changeScore(0);
	}

	@Override
	public Stage getStage() {
		return stage;
	}
}
