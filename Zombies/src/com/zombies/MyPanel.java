package com.zombies;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPanel extends JPanel {
    boolean[] flag = new boolean[256];
    boolean fire = false;

    GameManager manager = new GameManager();

    Image imBackground = new ImageIcon(
            getClass().getResource("/background.png")
    ).getImage();
    Image imCursor = new ImageIcon(
            getClass().getResource("/crosshair.png")
    ).getImage();

    public MyPanel() {
        manager.initGame();
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                imCursor, new Point(0, 0), "Cursor"
        ));
        addMouseMotionListener(motionListener);
        setFocusable(true);
        addKeyListener(keyListener);
        addMouseListener(adapter);

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.drawImage(imBackground, 0, 0,
                MyFrame.W_FRAME, MyFrame.H_FRAME, null);
        manager.draw(g2d);
    }

    MouseMotionListener motionListener = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            mouseMoved(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            manager.player.changeAngle(e.getX(), e.getY());
        }
    };

    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            flag[e.getKeyCode()] = true;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            flag[e.getKeyCode()] = false;
        }
    };

    MouseAdapter adapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (e.getButton() == MouseEvent.BUTTON1) {
                fire = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (e.getButton() == MouseEvent.BUTTON1) {
                fire = false;
            }
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (flag[KeyEvent.VK_LEFT] == true) {
                    manager.player.move(KeyEvent.VK_LEFT);
                }
                if (flag[KeyEvent.VK_RIGHT] == true) {
                    manager.player.move(KeyEvent.VK_RIGHT);
                }
                if (flag[KeyEvent.VK_UP] == true) {
                    manager.player.move(KeyEvent.VK_UP);
                }
                if (flag[KeyEvent.VK_DOWN] == true) {
                    manager.player.move(KeyEvent.VK_DOWN);
                }
                if (fire == true) {
                    manager.player.fire(manager.arrBullet);
                }
                boolean isDie = manager.AI();
                if (isDie == true) {
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "Do you want to replay?",
                            "Game over",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (result == JOptionPane.YES_OPTION) {
                        fire = false;
                        flag = new boolean[256];
                        manager.initGame();
                    } else {
                        System.exit(0);
                    }
                }
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
