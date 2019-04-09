package Components.Threads;

import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;

import java.io.File;

import static javafx.scene.media.MediaPlayer.INDEFINITE;

public class Music {
    /**
     * Class containing methods for playing and stopping music
     */

    private static Task task;
    private static final File file = new File("resources/music/music1.wav");
    public static final AudioClip audio = new AudioClip(file.toURI().toString());

    /**
     * Method which plays music in its own separate thread
     */
    public static void playMusic() {
        task = new Task() {
            @Override
            protected Object call() throws Exception {
                int s = INDEFINITE;
                audio.setVolume(1.5f);
                audio.setCycleCount(s);
                audio.play();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * Method which stops the music if music is playing
     */
    public static void stopMusic() {
        if (audio.isPlaying()) {
            audio.stop();
        }
        if (task != null) {
            task.cancel(true);
        }
    }
}
