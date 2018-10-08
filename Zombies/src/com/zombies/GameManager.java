package com.zombies;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class GameManager {
    Player player;
    ArrayList<Zombies> arrZombies;
    ArrayList<Bullet> arrBullet;
    int score;

    void initGame() {
        score = 0;
        arrBullet = new ArrayList<Bullet>();
        player = new Player(MyFrame.W_FRAME / 2, MyFrame.H_FRAME / 2);
        arrZombies = new ArrayList<>();
        initZombies();
    }

    void draw(Graphics2D g2d) {
        for (Bullet b : arrBullet) {
            b.draw(g2d);
        }
        player.draw(g2d);
        for (Zombies z : arrZombies) {
            z.draw(g2d);
        }
        g2d.setFont(new Font(null, Font.BOLD, 30));
        g2d.setColor(Color.RED);
        g2d.drawString(score + "", 10, 40);
    }

    void initZombies() {
        for (int i = 0; i < 5; i++) {
            arrZombies.add(new Zombies());
        }
    }

    boolean AI() {
        for (int i = arrZombies.size() - 1; i >= 0; i--) {
            arrZombies.get(i).move(player);
            boolean check = arrZombies.get(i).checkBullet(arrBullet);
            if (check == true) {
                arrZombies.remove(i);
                score += 100;
                if (arrZombies.size() == 0) {
                    initZombies();
                }
            }
        }
        for (int i = arrBullet.size() - 1; i >= 0; i--) {
            boolean check = arrBullet.get(i).move();
            if (check == false) {
                arrBullet.remove(i);
            }
        }
        return player.checkDie(arrZombies);
    }

    static void playSound(String name) {
        try {
            Clip clip = AudioSystem.getClip();
            File f = new File("src/" + name);
            AudioInputStream stream = AudioSystem.getAudioInputStream(f);
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
