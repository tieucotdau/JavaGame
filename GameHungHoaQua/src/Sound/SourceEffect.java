package Sound;

import javax.sound.sampled.*;
import java.net.URL;

public class SourceEffect implements LineListener {
    private Clip clip;
    private boolean done = false;

    public SourceEffect(String songName) {
        URL url = getClass().getResource("/Sound/" + songName);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.addLineListener(this);
            clip.open(audioIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SourceEffect loop(int number) {
        if (clip != null) {
            clip.loop(number);
        }
        return this;
    }

    public void play() {
        new Thread() {
            public void run() {
                if (clip != null && !clip.isRunning()) {
                    clip.start();
                    while (!done) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP || event.getType() == LineEvent.Type.CLOSE) {
            done = true;
        }
    }
}
