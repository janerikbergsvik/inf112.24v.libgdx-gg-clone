package inf112.core.app.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * The Class GameLabelStyler.
 */
public class GameLabelStyler {
	
	  /**
  	 * Gets the label style.
  	 *
  	 * @param path the path
  	 * @param fontsize the fontsize
  	 * @param r the r
  	 * @param g the g
  	 * @param b the b
  	 * @return the label style
  	 */
  	public static Label.LabelStyle getLabelStyle(String path, int fontsize, float r, float g, float b) {
	        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = fontsize;
			
			// Original Giana Sisters color
			Color color = new com.badlogic.gdx.graphics.Color(r/255f, g/255f, b/255f, 255f/255f);
			parameter.color = color;
			BitmapFont generated_font = generator.generateFont(parameter); 
			generator.dispose(); 
		    Label.LabelStyle labelStyle = new Label.LabelStyle();
		    labelStyle.font = generated_font;
		
			return labelStyle;
	    }

}
