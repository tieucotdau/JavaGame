import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Cloud extends CommonObject{
    private int fruitDropPosition;
    private boolean isDropItems;


    private Random random = new Random();
    private int temp;

    public Cloud(int distance) {
        isDropItems=false;
        this.level=1;
        this.x=1024+50*distance;
        this.y=20+30*distance;
        temp=random.nextInt(889)+40;
        this.speed=random.nextInt(8)+1;
        if(temp%speed==0){
            this.fruitDropPosition=temp+(x%speed);
        }else if(temp%speed!=0){
            this.fruitDropPosition=(temp-(temp%speed))-(x%speed);
        }
        this.type=random.nextInt(6)+1;
        this.image=new ImageIcon(getClass().getResource("/Images/cloud"+this.level+this.type+".png")).getImage();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
            graphics2D.drawImage(image,x,y,null);
    }

    public void move(int distanceCloud){
        x-=speed;
        if(x<-128){
            isDropItems=false;
            this.level=1;
            this.x=1024+50*distanceCloud;
            this.y=20+30*distanceCloud;
            temp=random.nextInt(889)+40;
            this.speed=random.nextInt(8)+1;
            if(temp%speed==0){
                this.fruitDropPosition=temp+(x%speed);
            }else if(temp%speed!=0){
                this.fruitDropPosition=(temp-(temp%speed))-(x%speed);
            }
            this.type=random.nextInt(6)+1;
            this.image=new ImageIcon(getClass().getResource("/Images/cloud"+this.level+this.type+".png")).getImage();
        }
    }

    public void updateLevel(int level){
        setLevel(level);
        this.image=new ImageIcon(getClass().getResource("/Images/cloud"+this.level+this.type+".png")).getImage();
    }

    public int getFruitDropPosition() {
        return fruitDropPosition;
    }

    public void setFruitDropPosition(int fruitDropPosition) {
        this.fruitDropPosition = fruitDropPosition;
    }

    public boolean isDropItems() {
        return isDropItems;
    }

    public void setDropItems(boolean dropItems) {
        isDropItems = dropItems;
    }
}
