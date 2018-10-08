import java.awt.*;

public abstract class CommonObject {

    Image image;
    int speed;
    int x, y;
    int level;
    int type;

    public abstract void draw(Graphics2D graphics2D);

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
