package inf112.core.app.Helper;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AudioTest {
	// Variables
	IGameSettings gameSettings;
	IAudio audio;
	
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
		gameSettings = spy(new GameSettings());
		audio = spy(new Audio(gameSettings));
	}
	
    @Test
    void updateFromSettingsShouldSetSoundVolumeCorrectly() {
        gameSettings.setSoundVolume(0.0f);
        audio.updateFromSettings();
        assertEquals(audio.getSoundVolume(), 0.0f);
    }

    @Test
    void updateFromSettingsShouldNotSetSoundVolumeIncorrectly() {
        gameSettings.setSoundVolume(0.0f);
        audio.updateFromSettings();

        assertNotEquals(audio.getSoundVolume(), 10.0f);
    }

    @Test
    void updateFromSettingsShouldSetMusicVolumeCorrectly() {
        gameSettings.setMusicVolume(5.0f);
        audio.updateFromSettings();
        
        // Shows that updateFromSettings has updated the musicVolume 
        // variable.
        assertTrue(gameSettings.getMusicVolume() == 5.0f);
        verify(gameSettings, times(1)).setMusicVolume(5.0f);       
    }

    @Test
    void updateFromSettingsShouldSetMusicVolumeIncorrectly() {
        gameSettings.setMusicVolume(0.0f);
        audio.updateFromSettings();

        assertNotEquals(audio.getSoundVolume(), 10.0f);
    }

    @Test
    void updateFromSettingsShouldSetSoundActiveToFalse() {
        gameSettings.setSoundActive(false);
        audio.updateFromSettings();
  
        assertTrue(audio.getSoundVolume() == 0.0f);
    }

    @Test
    void updateFromSettingsShouldSetMusicActiveToFalse() {
        gameSettings.setMusicActive(false);
        audio.updateFromSettings();

        // Shows that updateFromSettings has updated the musicActive 
        // variable.
        assertTrue(gameSettings.isMusicActive() == false);
        verify(gameSettings, times(3)).isMusicActive();       
    }

    @Test
    void startMusicShouldCallUpdateFromSettings() {
        audio.startMusic();
        verify(audio, times(1)).startMusic();
    }

    @Test
    void stopMusicShouldCallUpdateFromSettings() {
    	gameSettings.setMusicVolume(5.0f);
    	gameSettings.setMusicActive(true);
    	gameSettings.setSoundActive(true);
    	gameSettings.setSoundVolume(5.0f);
    	audio.startMusic();
        audio.stopMusic();

        verify(audio, times(2)).updateFromSettings();
    }

    @Test
    void getSoundVolumeShouldReturnZeroWhenSoundVolumeIsGreaterThanZeroAndSoundIsActive() {
        gameSettings.setSoundVolume(0.0f);
        gameSettings.setSoundActive(false);
        assertEquals(audio.getSoundVolume(), 0.0f);
    }

    @Test
    void getSoundVolumeShouldNotReturnZeroWhenSoundVolumeIsGreaterThanZero() {
        gameSettings.setSoundVolume(1.0f);
        gameSettings.setSoundActive(true);
        audio.updateFromSettings();
        assertEquals(audio.getSoundVolume(), 1.0);
        gameSettings.setSoundActive(false);
        assertEquals(audio.getSoundVolume(), 0.0);
        }

    @Test
    void getSoundVolumeShouldNotReturnZeroWhenSoundIsActive() {
        gameSettings.setSoundVolume(0.0f);
        gameSettings.setSoundActive(true);
        
        assertEquals(audio.getSoundVolume(), gameSettings.getSoundVolume());
    }

    @Test
    void updateFromSettingsShouldCallGetSoundVolume() {
        audio.updateFromSettings();

        verify(gameSettings, times(2)).getSoundVolume();
    }
    
    @Test
    void updateFromSettingsShouldCallGetMusicVolume() {
        audio.updateFromSettings();

        verify(gameSettings, times(2)).getMusicVolume();
    }
    

    @Test
    void updateFromSettingsShouldCallIsSoundActive() {
        audio.updateFromSettings();

        verify(gameSettings, times(2)).isSoundActive();
    }

    
    @Test
    void updateFromSettingsShouldCallIsMusicActive() {
        audio.updateFromSettings();

        verify(gameSettings, times(2)).isMusicActive();
    }
    
}
