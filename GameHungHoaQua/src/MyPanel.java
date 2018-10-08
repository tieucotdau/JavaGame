import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

public class MyPanel extends JPanel implements Runnable {

    private JButton btnStart;
    private JButton btnPause;
    private JButton btnCancel;

    private JLabel txtBgScore;
    private JLabel txtInforScore;
    private JLabel txtBasket;
    private JLabel txtNumberPlay;
    private JLabel txtApple;
    private JLabel txtBanana;
    private JLabel txtGrape;
    private JLabel txtWaterMelon;
    private JLabel txtPineApple;
    private JLabel txtBomb;
    private JLabel txtTotalScore;

    private GameManager gameManager;

    private Thread thread;
    private boolean isRunning;
    private boolean isPause;
    private boolean statusPause;
    private int index;

    public MyPanel() {
        gameManager = new GameManager();
        gameManager.setPanel(this);
        setFocusable(true);
        addKeyListener(adapter);

        initViews();
        startThread();
    }

    private void startThread() {
        isPause = false;
        statusPause = false;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }


    private void initViews() {
        setLayout(null);

        Font fontTxt = new Font("Snap ITC", Font.ROMAN_BASELINE, 27);


        txtBgScore = new JLabel();
        Icon icon = new ImageIcon(getClass().getResource("/Images/BackgroundResult.jpg"));
        txtBgScore.setIcon(icon);
        txtBgScore.setBounds(1024, 0, 342, 768);

        txtInforScore = new JLabel();
        txtInforScore.setIcon(new ImageIcon(getClass().getResource("/Images/ScoreFruit1.png")));
        txtInforScore.setBounds(0, 90, 120, 588);

        txtBasket = new JLabel();
        txtBasket.setIcon(new ImageIcon(getClass().getResource("/Images/NumberBasket.png")));
        txtBasket.setBounds(20, 20, 70, 50);

        txtNumberPlay = new JLabel("10");
        txtNumberPlay.setForeground(Color.BLACK);
        txtNumberPlay.setBounds(100, 20, 50, 70);
        txtNumberPlay.setFont(fontTxt);

        txtApple = new JLabel("0");
        txtApple.setBounds(1072, 125, 120, 40);
        txtApple.setFont(fontTxt);

        txtBanana = new JLabel("0");
        txtBanana.setBounds(1172, 125, 120, 40);
        txtBanana.setFont(fontTxt);

        txtGrape = new JLabel("0");
        txtGrape.setBounds(1272, 125, 120, 40);
        txtGrape.setFont(fontTxt);

        txtWaterMelon = new JLabel("0");
        txtWaterMelon.setBounds(1072, 325, 120, 40);
        txtWaterMelon.setFont(fontTxt);

        txtPineApple = new JLabel("0");
        txtPineApple.setBounds(1172, 325, 120, 40);
        txtPineApple.setFont(fontTxt);

        txtBomb = new JLabel("0");
        txtBomb.setBounds(1272, 325, 120, 40);
        txtBomb.setFont(fontTxt);

        txtTotalScore = new JLabel("0");
        txtTotalScore.setBounds(1172, 550, 120, 40);
        txtTotalScore.setFont(fontTxt);

        btnStart = new JButton("New Game");
        btnStart.setBounds(1033, 680, 100, 40);
        btnStart.addActionListener(listener);

        btnPause = new JButton("Pause");
        btnPause.setBounds(1143, 680, 100, 40);
        btnPause.addActionListener(listener);

        btnCancel = new JButton("Exit");
        btnCancel.setBounds(1253, 680, 100, 40);
        btnCancel.addActionListener(listener);


        btnStart.setFocusable(false);
        btnPause.setFocusable(false);
        btnCancel.setFocusable(false);

        add(txtInforScore);
        add(txtBasket);
        add(txtNumberPlay);
        add(txtApple);
        add(txtBanana);
        add(txtGrape);
        add(txtWaterMelon);
        add(txtPineApple);
        add(txtBomb);
        add(txtTotalScore);
        add(btnStart);
        add(btnPause);
        add(btnCancel);
        add(txtBgScore);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        gameManager.veBackGround(graphics2D);
        gameManager.veBasket(graphics2D);
        gameManager.veMay(graphics2D);
        gameManager.veTextReady(graphics2D);
        gameManager.veTextGameOver(graphics2D);
        gameManager.veVatPham(graphics2D);

    }

    BitSet bitSet = new BitSet();

    KeyAdapter adapter = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            bitSet.clear();
        }

        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                bitSet.set(e.getKeyCode());
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                bitSet.set(e.getKeyCode());
            }


        }

    };


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnStart) {
                    gameManager.setGameReady(false);
                    btnPause.setText("Pause");
                    isPause = false;
                    statusPause = false;
                    gameManager.reset();
                } else if (e.getSource() == btnPause) {
                    if (isPause == false) {
                        btnPause.setText("Resume");
                        isPause = true;

                        statusPause = true;
                    } else {
                        btnPause.setText("Pause");
                        isPause = false;
                        statusPause = false;
                    }

                } else if (e.getSource() == btnCancel) {
                    int action = JOptionPane.showConfirmDialog(null,
                            "Ban co muon thoat game?",
                            "Thoat", JOptionPane.INFORMATION_MESSAGE);
                    if (action == 0) {
                        System.exit(0);
                        isRunning = false;
                    }
                }
            }
        };

        @Override
        public void run() {
            while (isRunning) {
                try {
                    Thread.sleep(5);
                    index++;
                    //Di chuyen Basket
                    if (bitSet.get(KeyEvent.VK_LEFT)) {
                        gameManager.diChuyenBasket(Basket.LEFT);
                    } else if (bitSet.get(KeyEvent.VK_RIGHT)) {
                        gameManager.diChuyenBasket(Basket.RIGHT);
                    }
                    //Di chuyen background
                    gameManager.diChuyenBackGround();
                    if (index % 4 == 0) {
                        gameManager.diChuyenTatCaMay();
                        gameManager.diChuyenTatCaVatPham();
                    }
                    gameManager.xuLyVaCham();
                    gameManager.kiemTraLevel();
                    repaint();

                    while (statusPause) {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        public void updateScore() {

            txtApple.setText(gameManager.getScoreApple() + "");
            txtBanana.setText(gameManager.getScoreBanana() + "");
            txtGrape.setText(gameManager.getScoreGrape() + "");
            txtWaterMelon.setText(gameManager.getScoreWaterMelon() + "");
            txtPineApple.setText(gameManager.getScorePineApple() + "");
            txtBomb.setText(gameManager.getScoreBomb() + "");

            txtNumberPlay.setText(gameManager.getNumberPlay() + "");
            txtTotalScore.setText(gameManager.getTotalScore() + "");
        }
}
