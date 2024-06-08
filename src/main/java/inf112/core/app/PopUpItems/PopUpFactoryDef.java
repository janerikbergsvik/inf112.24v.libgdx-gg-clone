package inf112.core.app.PopUpItems;

import com.badlogic.gdx.math.Vector2;

/**
 * The Class PopUpFactoryDef.
 */
public class PopUpFactoryDef {
	public Vector2 position;
	public Class<?> type;
	
	/**
	 * Instantiates a new pop up factory def.
	 *
	 * @param position the position of item.
	 * @param type the type of popupitem.
	 */
	public PopUpFactoryDef(Vector2 position, Class<?> type) {	
			this.position = position;
			this.type = type;
		}
	}

