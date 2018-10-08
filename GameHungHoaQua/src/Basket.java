import javax.swing.*;
import java.awt.*;

public class Basket {

    public static final int LEFT = 213;
    public static final int RIGHT = 111;

    int x;
    int y;
    Image image;
    int speed;
    int level;

    public Basket() {
        speed = 3;
        level = 1;
        image = new ImageIcon(getClass().getResource("/Images/basket" + level + ".png")).getImage();
        x = 200;
        y = GUI.HEIGHT_FRAME - image.getHeight(null);

    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, x, y, null);
    }

    public void move(int distance) {
        if (distance == LEFT) {
            x -= speed;
            if (x <= 0) {
                x += speed;
            }
        } else if (distance == RIGHT) {
            x += speed;
            if (x > 1024 - image.getWidth(null)) {
                x -= speed;
            }
        }
    }


    public boolean xuLyVaCham(Items vatPham) {
        Rectangle rectBasket = new Rectangle(this.x, this.y, image.getWidth(null), image.getHeight(null));

        Rectangle rectVatPham = new Rectangle(vatPham.getX(), vatPham.getY()-100, vatPham.getImage().getWidth(null), vatPham.getImage().getHeight(null));

        boolean isCham = rectBasket.intersects(rectVatPham);
        if (isCham) {
            return true;
        }
        return false;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        image = new ImageIcon(getClass().getResource("/Images/basket" + level + ".png")).getImage();
    }
}
