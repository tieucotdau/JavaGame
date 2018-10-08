import Sound.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private static final int NUMBER_CLOUD = 6;
    private final int NUMBER_PLAY = 10;

    private Background backGround;
    private Basket basket;
    private MyPanel panel;

    private ArrayList<Cloud> arrMay = new ArrayList<>();
    private ArrayList<Items> arrVatPham = new ArrayList<>();

    private Random random = new Random();
    private boolean gameReady;
    private boolean gameOver;

    private int scoreApple, scoreBanana, scoreGrape, scoreWaterMelon, scorePineApple, scoreBomb;
    private int totalScore;
    private int numberPlay;
    private boolean isNextLevel;

    private SoundManager soundManager;

    public GameManager() {
        soundManager = new SoundManager();

        numberPlay = NUMBER_PLAY;
        gameReady = true;
        gameOver = false;
        backGround = new Background();
        basket = new Basket();
        khoiTaoMay();
    }

    private void khoiTaoMay() {
        for (int i = 0; i < NUMBER_CLOUD; i++) {
            arrMay.add(new Cloud(i));
        }
    }


    public void xuLyVaCham() {
        for (int i = 0; i < arrVatPham.size(); i++) {
            boolean isVaCham = basket.xuLyVaCham(arrVatPham.get(i));
            if (isVaCham) {
                switch (arrVatPham.get(i).getType()) {
                    case Items.APPLE:
                        soundManager.getSoundFruit().play();
                        scoreApple++;
                        break;
                    case Items.BANANA:
                        soundManager.getSoundFruit().play();
                        scoreBanana++;
                        break;
                    case Items.GRAPE:
                        soundManager.getSoundFruit().play();
                        scoreGrape++;
                        break;
                    case Items.WATER_MELON:
                        soundManager.getSoundFruit().play();
                        scoreWaterMelon++;
                        break;
                    case Items.PINE_APPLE:
                        soundManager.getSoundFruit().play();
                        scorePineApple++;
                        break;
                    case Items.BOMB:
                        soundManager.getSoundBomb().play();
                        scoreBomb++;
                        break;
                }
                arrVatPham.remove(i);
                totalScore = scoreApple * 20 + scoreBanana * 30 + scoreGrape * 10 + scoreWaterMelon * 50 + scorePineApple * 40 + scoreBomb * -50;
                panel.updateScore();
            }

        }

    }

    public void veMay(Graphics2D graphics2D) {
        for (int i = 0; i < NUMBER_CLOUD; i++) {
            arrMay.get(i).draw(graphics2D);
            Cloud cloud = arrMay.get(i);
            if (cloud.getX() == cloud.getFruitDropPosition() && !cloud.isDropItems() && !isGameReady() && !isGameOver()) {
                arrVatPham.add(new Items(cloud.getX(), cloud.getY(), cloud.getLevel()));
                soundManager.getSoundCloud().play();
                cloud.setDropItems(true);
            }

        }
    }

    public void veVatPham(Graphics2D graphics2D) {
        for (int i = 0; i < arrVatPham.size(); i++) {
            arrVatPham.get(i).draw(graphics2D);
        }
    }


    public void veBackGround(Graphics2D graphics2D) {
        backGround.draw(graphics2D);
    }

    public void veBasket(Graphics2D graphics2D) {
        basket.draw(graphics2D);
    }


    public void diChuyenBackGround() {
        backGround.move();
    }

    public void diChuyenBasket(int huong) {
        basket.move(huong);
    }

    public void diChuyenTatCaMay() {
        for (int i = 0; i < arrMay.size(); i++) {
            arrMay.get(i).move(i);


        }
    }

    public void diChuyenTatCaVatPham() {
        for (int i = 0; i < arrVatPham.size(); i++) {

            arrVatPham.get(i).move();

            if (arrVatPham.get(i).getY()> GUI.HEIGHT_FRAME) {
                arrVatPham.remove(i);
                soundManager.getSoundSlide().play();
                numberPlay--;
            }

        }

        panel.updateScore();
        if (numberPlay == 0) {
            gameOver = true;
            arrVatPham.clear();
        }
    }

    public void veTextReady(Graphics2D graphics2D) {
        if (gameReady) {
            soundManager.getSoundBackground().loop(1000).play();
            Image image = new ImageIcon(getClass().getResource("/Images/textGetReady.png")).getImage();
            graphics2D.drawImage(image, 300, 300, null);
        }
        soundManager.getSoundBackground().stop();

    }

    public void veTextGameOver(Graphics2D graphics2D) {
        if (gameOver) {
            soundManager.getSoundBackground().loop(1000).play();
            Image image = new ImageIcon(getClass().getResource("/Images/textGameOver.png")).getImage();
            graphics2D.drawImage(image, 300, 300, null);
        }
    }

    public void kiemTraLevel() {
        if (totalScore > 100 && totalScore < 300 && !isNextLevel) {
            int action = JOptionPane.showConfirmDialog(null,
                    "Level 2?",
                    "Welcome", JOptionPane.YES_OPTION);

            arrVatPham.clear();

            backGround.setLevel(2);
            basket.setLevel(2);

            //May
            for (int i = 0; i < arrMay.size(); i++) {
                arrMay.get(i).updateLevel(2);
            }

            for (int i = 0; i < arrVatPham.size(); i++) {
                arrVatPham.get(i).updateLevel(2);
            }
            isNextLevel=true;

        }else if(getTotalScore() >= 300 && isNextLevel) {
            int action = JOptionPane.showConfirmDialog(null,
                    "Level 3?",
                    "Welcome", JOptionPane.YES_OPTION);

            arrVatPham.clear();

            backGround.setLevel(3);
            basket.setLevel(3);

            //May
            for (int i = 0; i < arrMay.size(); i++) {
                arrMay.get(i).updateLevel(3);
            }

            for (int i = 0; i < arrVatPham.size(); i++) {
                arrVatPham.get(i).updateLevel(3);
            }
            isNextLevel=false;
        }


    }


    public void reset() {
        gameReady = false;
        gameOver = false;
        totalScore = 0;

        scoreApple = 0;
        scoreBanana = 0;
        scoreGrape = 0;
        scoreWaterMelon =0;
        scoreBomb = 0;
        scorePineApple = 0;

        backGround.setLevel(1);
        basket.setLevel(1);


        for (int i = 0; i < arrMay.size(); i++) {
            arrMay.get(i).updateLevel(1);
        }

        for (int i = 0; i < arrVatPham.size(); i++) {
            arrVatPham.get(i).updateLevel(1);
        }

        numberPlay = NUMBER_PLAY;

        panel.updateScore();
    }

    public boolean isGameReady() {
        return gameReady;
    }

    public void setGameReady(boolean gameReady) {
        this.gameReady = gameReady;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public MyPanel getPanel() {
        return panel;
    }

    public void setPanel(MyPanel panel) {
        this.panel = panel;
    }

    public int getScoreApple() {
        return scoreApple;
    }

    public void setScoreApple(int scoreApple) {
        this.scoreApple = scoreApple;
    }

    public int getScoreBanana() {
        return scoreBanana;
    }

    public void setScoreBanana(int scoreBanana) {
        this.scoreBanana = scoreBanana;
    }

    public int getScoreGrape() {
        return scoreGrape;
    }

    public void setScoreGrape(int scoreGrape) {
        this.scoreGrape = scoreGrape;
    }

    public int getScoreWaterMelon() {
        return scoreWaterMelon;
    }

    public void setScoreWaterMelon(int scoreWaterMelon) {
        this.scoreWaterMelon = scoreWaterMelon;
    }

    public int getScorePineApple() {
        return scorePineApple;
    }

    public void setScorePineApple(int scorePineApple) {
        this.scorePineApple = scorePineApple;
    }

    public int getScoreBomb() {
        return scoreBomb;
    }

    public void setScoreBomb(int scoreBomb) {
        this.scoreBomb = scoreBomb;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getNumberPlay() {
        return numberPlay;
    }

    public void setNumberPlay(int numberPlay) {
        this.numberPlay = numberPlay;
    }

    public boolean isNextLevel() {
        return isNextLevel;
    }

    public void setNextLevel(boolean nextLevel) {
        isNextLevel = nextLevel;
    }
}
