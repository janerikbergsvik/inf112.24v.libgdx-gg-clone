package inf112.core.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * The Class Main.
 */
public class Main {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 480;
    
    /**
     * The main method used for launching the application.
     *
     * @param args If any command-line arguments
     */
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Digi-teamet INF112-Prosjekt");
        cfg.setWindowedMode(WINDOW_WIDTH, WINDOW_HEIGHT);
        cfg.setWindowIcon("program_icon.png");
        cfg.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        cfg.useVsync(true);
        new Lwjgl3Application(new MainGame(), cfg);
    }
}
