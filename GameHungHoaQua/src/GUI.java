import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    static final int WIDTH_FRAME = 1366;
    static final int HEIGHT_FRAME=768;

    public GUI() {
        int widthScreen = Toolkit.getDefaultToolkit().getScreenSize().width;
        int heightScreen=Toolkit.getDefaultToolkit().getScreenSize().height;

        setBounds((widthScreen - WIDTH_FRAME)/2,(heightScreen - HEIGHT_FRAME)/2,WIDTH_FRAME,HEIGHT_FRAME);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        MyPanel myPanel = new MyPanel();
        add(myPanel);
    }
}
