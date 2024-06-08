package inf112.core.app.Characters;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.MainGame;
import inf112.core.app.Helper.IAudio;
import inf112.core.app.Helper.ObjectListener;
import inf112.core.app.View.GameScreens.Hud;
import inf112.core.app.View.GameScreens.IHud;

class BulletTest {
	// Variables
	IPlayer player;		
	World world;
	TextureAtlas atlas;
	IHud hud;		
	TiledMap map;
	IAudio audio;
	TmxMapLoader maploader;
	Body b2body;
	
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
		// Initialize variables, create and mock objects.		
		MainGame.PPM = 10;
		world = spy(new World(new Vector2(0,-9.81f), true));
		world.setContactListener(new ObjectListener());		  
		atlas = new TextureAtlas(Gdx.files.internal("textures/sisters_and_enemies/sisters_and_enemies.pack")); 	     			
		player = mock(GianaPlayer.class);			  				
		audio = mock(IAudio.class);
		maploader = new TmxMapLoader();
		map = maploader.load("levels//tilesets_scaled_32x32/level2.tmx");
		hud = new Hud(mock(Batch.class), 1);				   		
	}
	
	// Throw null exception test.
    @Test
    public void shouldThrowNullPointerExceptionWhenWorldConstructorGravityIsNull() {
        assertThrows(NullPointerException.class, () -> new Bullet(new World(null,false), null));
    }
    
	/**
	 * Verify that bullet bullet.class runs properly.
	 */
	@Test
	void bulletTest() {
		// Player specific mocks.
		when(player.runningRight()).thenReturn(true);
		BodyDef bdef = new BodyDef();
        bdef.position.set(752 / MainGame.PPM, 112 / MainGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = spy(world.createBody(bdef));
        when(player.getBody()).thenReturn(b2body);
		when(b2body.getLinearVelocity()).thenReturn(new Vector2(0f,0f));
		
		// See that methods run properly.
		try {
			// Initialize projectile.
			IProjectile projectile = new Bullet(world, player); 
			projectile.defineEntity();
			projectile.update(0.5f);
			assertTrue(true);
			}			
		
		catch(Exception e) {
			assertTrue(false);
		}	
	}	
}


