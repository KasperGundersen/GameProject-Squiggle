package Components.Threads;

import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;

import java.io.File;

import static javafx.scene.media.MediaPlayer.INDEFINITE;

public class Music {

    private static Task task;
    private static final File file = new File("resources/music/music.wav");
    private static final AudioClip audio = new AudioClip(file.toURI().toString());

    public static void playMusic() {
        task = new Task() {
            @Override
            protected Object call() throws Exception {
                System.out.println("Playing music");
                int s = INDEFINITE;
                //AudioClip audio = new AudioClip(getClass().getResource("resources/music/music.wav").toExternalForm());
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
        audio.stop();
        task.cancel(true);
    }
}
