package inf112.core.app.PopUpItems;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import inf112.core.app.Characters.GianaPlayer;
import inf112.core.app.Characters.IPlayer;
import inf112.core.app.View.GameScreens.IHud;

class ClockTest {
    // Variables
    IPlayer player;
    World world;
    TextureAtlas powerups;
    TextureAtlas atlas;
    IHud hud;
    IPopUpItem popUpItem;

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
        world = new World(new Vector2(0,-9.81f), true);
        atlas = new TextureAtlas(Gdx.files.internal("textures/sisters_and_enemies/sisters_and_enemies.pack"));
        powerups = new TextureAtlas(Gdx.files.internal("textures/powerups/powerups.atlas"));
        player = mock(GianaPlayer.class);
        hud = mock(IHud.class);
        popUpItem = new Clock(player, world, hud, powerups, 1, 1);
    }

    /**
     * Verify that PopUpItem clock.class runs properly.
     */
    @Test
    void clockTest() {
        try {
        	popUpItem.defineEntity();
			popUpItem.update(0.5f);
			popUpItem.destroy();
			popUpItem.update(0.5f);
			popUpItem.draw(mock(Batch.class));
			popUpItem.reverseVelocity(true, true);
			popUpItem.use();					
            assertTrue(true);
            
        }

        catch(Exception e) {
            assertTrue(false);
        }

    }

}
