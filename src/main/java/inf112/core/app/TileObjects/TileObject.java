package inf112.core.app.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.MainGame;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Model.GameModel;
import inf112.core.app.Model.IGameModel;
import inf112.core.app.View.GameScreens.IHud;

/**
 * The Class TileObject.
 */
public abstract class TileObject implements ITileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
    protected IAudio audio;
	protected IGameModel model;
	protected int score = 0;
	protected IPlayer player;
	protected IHud hud;
	
    /**
     * Instantiates a new tile object.
     *
     * @param hud the hud
     * @param player the player
     * @param world the box2d world
     * @param map the map
     * @param audio the audio
     * @param bounds the bounds
     */
    public TileObject(IHud hud, IPlayer player, World world, TiledMap map, IAudio audio, Rectangle bounds){
    	this.world = world;
    	this.map = map;
        this.bounds = bounds;
        this.audio = audio;
        this.player = player;
        this.hud = hud;        
        BodyDef bdef = new BodyDef();
        
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        
        bdef.type = BodyDef.BodyType.StaticBody;        
        bdef.position.set((bounds.getX()+bounds.getWidth() / 2) / MainGame.PPM, (bounds.getY() + bounds.getHeight() / 2) / MainGame.PPM);
        
        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth() / 2 / MainGame.PPM, bounds.getHeight() / 2 / MainGame.PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = IGameModel.OBJECT_BIT;
        fixture = body.createFixture(fdef);
    }

	@Override
	public void setCollisionCategory(short bit) {
		Filter filter = new Filter();
		filter.categoryBits = bit;
		fixture.setFilterData(filter);		
	}
	
	@Override
	public TiledMapTileLayer.Cell getCell() {
		// Retrieve layer
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(2);
		
		// Retrieve cell x, y positions.
		int xCellPos =  (int) (body.getPosition().x * MainGame.PPM / 32);
		int yCellPos =  (int) (body.getPosition().y * MainGame.PPM / 32);
		
		// Retrieve cell
		TiledMapTileLayer.Cell cell = layer.getCell(xCellPos, yCellPos); 
		
		// Log cell x,y position.
		//Gdx.app.log("Cell identified x-dir. ", String.valueOf((int)(body.getPosition().x * MainGame.PPM / 32)));
		//Gdx.app.log("Cell identified y-dir. ", String.valueOf((int)(body.getPosition().y * MainGame.PPM / 32)));
		
		return cell;
	}

	@Override
	public int getScore() {
		return score;
	}
	
	@Override
	public abstract void onHairContact();

	@Override
	public abstract void onPlayerContact();
	
	@Override
	public abstract void onFeetContact();

	@Override
	public abstract void onFeetContactEnd();
	
}
