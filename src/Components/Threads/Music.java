package Components.Threads;

import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;

import java.io.File;

import static javafx.scene.media.MediaPlayer.INDEFINITE;

public class Music {

    private static Task task;
    private static final File file = new File("resources/music/music.wav");
    public static final AudioClip audio = new AudioClip(file.toURI().toString());

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

    public static void stopMusic() {
        if (audio.isPlaying()) {
            audio.stop();
        }
        if (task != null) {
            task.cancel(true);
        }
    }
}
