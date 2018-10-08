package com.zombies;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet {
    double x;
    double y;
    double angle;
    double rotationX;
    double rotationY;
    Image img = new ImageIcon(getClass()
            .getResource("/bullet.png")).getImage();

    public Bullet(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
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

    boolean move(){
        x = x - 20 * Math.cos(angle);
        y = y - 20 * Math.sin(angle);
        if (x< - img.getWidth(null) || x >= MyFrame.W_FRAME){
            return false;
        }
        if (y< img.getHeight(null) || y >= MyFrame.H_FRAME){
            return false;
        }
        return true;
    }

    Rectangle getRect() {
        Rectangle rect = new Rectangle((int) x, (int) y,
                img.getWidth(null), img.getHeight(null));
        return rect;
    }
}
