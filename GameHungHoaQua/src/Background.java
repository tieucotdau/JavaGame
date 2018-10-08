import javax.swing.*;
import java.awt.*;

public class Background {

    int x;
    int y;
    int speed;
    int level;
    Image image;

    public Background() {
        x = 0;
        y = 0;
        speed = 1;
        level = 1;
        image = new ImageIcon(getClass().getResource("/Images/Background" + level + ".jpg")).getImage();


    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, x, y, null);
    }

    public void move() {
        x -= speed;
        if (x < -2048) {
            x = 0;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        image = new ImageIcon(getClass().getResource("/Images/Background" + level + ".jpg")).getImage();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
