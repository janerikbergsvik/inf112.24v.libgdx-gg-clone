package inf112.core.app.Helper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;


class GameLabelStylerTest {
	/**
	 * Static method run before everything else
	 */
	@BeforeAll
	static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
    	Gdx.gl = Gdx.gl20 = mock(GL20.class);
     
        ApplicationListener listener = new ApplicationListener() {

			@Override
			public void create() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void resize(int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void render() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void pause() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void resume() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}};
        new HeadlessApplication(listener, config);
        }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
	}
	
	/**
	 * 
	 * @return mocked Stage
	 */
	static Stage mockStage() {
		Viewport viewport = mock(Viewport.class);
		viewport.setCamera(mock(Camera.class));
		return new Stage(viewport, mock(SpriteBatch.class));
	}
		
	/**
	 * GameLabelStylerTest
	 */
	@Test
	void gameLabelStylerTest() {
		// Init variables.
	    Label.LabelStyle labelstyle;

	    try {
	    	// Get great Giana Sisters TrueType font.
	        labelstyle = GameLabelStyler.getLabelStyle("truetypefonts/giana/Giana.ttf", 17, 227, 230, 106);
	        assertTrue(true);
	       	}
	        
	    catch(Exception e) {
	    			labelstyle = null;
	        		assertTrue(false);
	        	}     
        
        // Make label text
        Label labeltext = new Label(String.format("DIGI-teamet"), labelstyle);

        // Add to table
        Table table = new Table();
        table.add(labeltext);
        
        // Check if properly set.
        assertTrue(table instanceof Table);
        assertTrue(labeltext instanceof Label);
        assertTrue(labelstyle instanceof LabelStyle);
        assertNotNull(table);
        assertNotNull(labeltext);
        assertNotNull(labelstyle);
        
        // Check that act is called properly
        Stage stage = spy(mockStage());//mockStage();
        stage.addActor(table);
        stage.act(0f);
        verify(stage, times(1)).act(0f);
	}
}
