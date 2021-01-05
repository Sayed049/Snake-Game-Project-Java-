package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class project extends JPanel implements KeyListener, ActionListener {

    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];
    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    private int lengthofsnake = 3;

    private Timer timer;
    private int delay = 100;

    private ImageIcon snakeImage;
    private int[] enemyxpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325,
        350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775,
        800, 825, 850};
    private int[] enemyypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325,
        350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

    private ImageIcon enemyImage;
    private Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);
    private int score = 0;
    private int moves = 0;

    private ImageIcon titleImage;

    public project() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics g) {

        if (moves == 0) {
            snakexlength[2] = 50;
            snakexlength[1] = 75;
            snakexlength[0] = 100;

            snakeylength[2] = 100;
            snakeylength[1] = 100;
            snakeylength[0] = 100;

        }
        //image border
        g.setColor(Color.blue);
        g.drawRect(24, 10, 851, 55);
        //title image
        titleImage = new ImageIcon("image/snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);
        //gameplay border
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);
        //gameplay background
        g.setColor(Color.gray);
        g.fillRect(25, 75, 850, 575);
        //draw scores
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores - " + score, 780, 30);
        //draw ;ength
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length - " + lengthofsnake, 780, 50);
        rightmouth = new ImageIcon("image/rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);

        for (int i = 0; i < lengthofsnake; i++) {
            if (i == 0 & right) {
                rightmouth = new ImageIcon("image/rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
            }
            if (i == 0 & left) {
                leftmouth = new ImageIcon("image/leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
            }
            if (i == 0 & up) {
                upmouth = new ImageIcon("image/upmouth.png");
                upmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
            }
            if (i == 0 & down) {
                downmouth = new ImageIcon("image/downmouth.png");
                downmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
            }
            if (i != 0) {
                snakeImage = new ImageIcon("image/snakeImage.png");
                snakeImage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
            }

        }
        enemyImage = new ImageIcon("image/enemy.png");
        if ((enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0])) {
            score++;
            lengthofsnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        enemyImage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
        for (int b = 1; b < lengthofsnake; b++) {
            if (snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]) {
                right = false;
                left = false;
                up = false;
                down = false;
                g.setColor(Color.green);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString(" Game Over ", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString(" Please Enter Space To Start Again ", 350, 340);
            }

        }

        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moves = 0;
            score = 0;
            lengthofsnake = 3;
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            right = true;
            if (!left) {
                right = true;
            } else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            left = true;
            if (!right) {
                left = true;
            } else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            up = true;
            if (!down) {
                up = true;
            } else {
                up = false;
                down = true;
            }
            right = false;
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            down = true;
            if (!up) {
                down = true;
            } else {
                down = false;
                up = true;
            }
            right = false;
            left = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {
            for (int a = lengthofsnake; a >= 0; a--) {
                snakeylength[a + 1] = snakeylength[a];
            }
            for (int a = lengthofsnake; a >= 0; a--) {
                if (a == 0) {
                    snakexlength[a] = snakexlength[a] + 25;
                } else {
                    snakexlength[a] = snakexlength[a - 1];
                }
                if (snakexlength[a] > 850) {
                    snakexlength[a] = 25;
                }
            }
            repaint();
        }
        if (left) {
            for (int a = lengthofsnake; a >= 0; a--) {
                snakeylength[a + 1] = snakeylength[a];
            }
            for (int a = lengthofsnake; a >= 0; a--) {
                if (a == 0) {
                    snakexlength[a] = snakexlength[a] - 25;
                } else {
                    snakexlength[a] = snakexlength[a - 1];
                }
                if (snakexlength[a] < 25) {
                    snakexlength[a] = 850;
                }
            }
            repaint();
        }
        if (up) {
            for (int a = lengthofsnake; a >= 0; a--) {
                snakexlength[a + 1] = snakexlength[a];
            }
            for (int a = lengthofsnake; a >= 0; a--) {
                if (a == 0) {
                    snakeylength[a] = snakeylength[a] - 25;
                } else {
                    snakeylength[a] = snakeylength[a - 1];
                }
                if (snakeylength[a] < 75) {
                    snakeylength[a] = 625;
                }
            }
            repaint();
        }
        if (down) {
            for (int a = lengthofsnake; a >= 0; a--) {
                snakexlength[a + 1] = snakexlength[a];
            }
            for (int a = lengthofsnake; a >= 0; a--) {
                if (a == 0) {
                    snakeylength[a] = snakeylength[a] + 25;
                } else {
                    snakeylength[a] = snakeylength[a - 1];
                }
                if (snakeylength[a] > 625) {
                    snakeylength[a] = 75;
                }
            }
            repaint();
        }

    }
}
