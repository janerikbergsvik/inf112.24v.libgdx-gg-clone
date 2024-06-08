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
 * The Class PowerupText.
 */
public class PowerupText {
	// Stage and viewport	
    public Stage stage;
    private Viewport viewport;

    // Timers
    private Integer timer;    

    // Labels & table
    private Label instruction_text;
    private Label timeLabel_text;
    private Label timeLabel_num;
    private Label powerup_text;
    private Label.LabelStyle labelstyle;
    private Table table;
    
    /**
     * Instantiates a new powerup text.
     *
     * @param sb the Batch
     */
    public PowerupText(Batch sb){
    	// Set timer
        timer = 20;

        // Setup viewport
        viewport = new FitViewport(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        stage = new Stage(viewport, sb);

        // Setup table & add to stage.
        setupTable();
        stage.addActor(table);
    }

	/**
	 * Setup table.
	 */
	private void setupTable() {
		table = new Table();
        table.top();
        table.setFillParent(true);

        // Get great Giana Sisters TrueType font.
        labelstyle = GameLabelStyler.getLabelStyle("truetypefonts/giana/Giana.ttf", 17, 227, 230, 106);
         
        timeLabel_num = new Label(String.format("%02d", timer), labelstyle);
        timeLabel_text = new Label(String.format("SECONDS"), labelstyle);
        powerup_text = new Label(String.format("POWER UP - ACTIVATED"), labelstyle);
        instruction_text = new Label(String.format("PRESS SPACESBAR TO SHOOT"),  labelstyle);

        // Setup table
        float padding = stage.getHeight()/3;
        table.add(powerup_text).expandX().padTop(padding).padBottom(30);

        table.row();

        table.add(timeLabel_text).expandX();

        table.row();

        table.add(timeLabel_num).expandX().padBottom(30);

        table.row();

        table.add(instruction_text).expandX();
	}
    
    /**
     * Update.
     *
     * @param timeCountDown the time count down
     */
    public void update(float timeCountDown) {
    	// Modify count down label.
    	timeLabel_num.setText(String.format("%02d", Math.round(timeCountDown)));
    }

}
