import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Items extends CommonObject{

    static final int APPLE = 1;
    static final int BANANA = 2;
    static final int GRAPE = 3;
    static final int WATER_MELON = 4;
    static final int PINE_APPLE = 5;
    static final int BOMB = 6;

    private Random random = new Random();

    public Items(int x, int y, int level) {
        this.x=x;
        this.y=y;
        this.level=level;
        this.type=random.nextInt(6)+1;
        this.speed=random.nextInt(10)+4;
        this.image=new ImageIcon(getClass().getResource("/Images/Fruit"+this.level+this.type+".png")).getImage();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image,x,y,null);
    }

    public void move(){
        y+=speed;
    }

    public void updateLevel(int level){
        setLevel(level);
        this.image=new ImageIcon(getClass().getResource("/Images/Fruit"+this.level+this.type+".png")).getImage();
    }
}
