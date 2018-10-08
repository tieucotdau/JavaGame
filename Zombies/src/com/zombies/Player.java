package com.zombies;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Player {
    double x;
    double y;
    double rotationX;
    double rotationY;
    Image img = new ImageIcon(getClass()
            .getResource("/player.PNG")).getImage();
    double angle;
    double cX;
    double cY;
    long t;

    public Player(double x, double y) {
        this.x = x - img.getWidth(null) / 2;
        this.y = y - img.getHeight(null) / 2;
    }

    void draw(Graphics2D g2d) {
        rotationX = x + img.getWidth(null) / 2;
        rotationY = y + img.getHeight(null) / 2;
        AffineTransform transform = new AffineTransform();
        transform.rotate(0, 0, 0);
        g2d.rotate(angle, rotationX, rotationY);
        g2d.drawImage(img, (int) x, (int) y, null);
        g2d.setTransform(transform);
    }

    void changeAngle(double cX, double cY) {
        this.cX = cX;
        this.cY = cY;
        angle = Math.atan2(y - cY, x - cX);
    }

    void move(int orient) {
        int speed = 5;
        double xRaw = x;
        double yRaw = y;
        switch (orient) {
            case KeyEvent.VK_LEFT:
                x -= speed;
                break;
            case KeyEvent.VK_RIGHT:
                x += speed;
                break;
            case KeyEvent.VK_UP:
                y -= speed;
                break;
            case KeyEvent.VK_DOWN:
                y += speed;
                break;
        }
        if (x < 0 || x >= MyFrame.W_FRAME - img.getWidth(null)) {
            x = xRaw;
        }
        if (y < 0 || y >= MyFrame.H_FRAME - img.getHeight(null) - 40) {
            y = yRaw;
        }
        changeAngle(cX, cY);
    }

    void fire(ArrayList<Bullet> arr) {
        long T = System.currentTimeMillis();
        if (T - t < 150) {
            return;
        }
        t = T;
        Bullet bullet = new Bullet(rotationX - 10, rotationY, angle);
        arr.add(bullet);
        GameManager.playSound("bullet.wav");
    }

    Rectangle getRect() {
        Rectangle rect = new Rectangle((int) x, (int) y,
                img.getWidth(null), img.getHeight(null));
        return rect;
    }

    boolean checkDie(ArrayList<Zombies> arr) {
        for (Zombies z : arr) {
            Rectangle rect = getRect().intersection(z.getRect());
            if (rect.isEmpty() == false){
                GameManager.playSound("playerDeath.wav");
                return true;
            }
        }
        return false;
    }
}
