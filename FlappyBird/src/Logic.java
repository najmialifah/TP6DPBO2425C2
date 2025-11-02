import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    Image backgroundImage;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    int pipeVelocityX = -2;

    boolean gameOver = false;
    boolean started = false; // burung mulai jatuh setelah tekan space
    int score = 0;
    JLabel scoreLabel;

    JButton backToMenuButton; // tombol kembali ke menu

    public Logic() {
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<>();

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);

        // tombol kembali ke menu (belum ditampilkan dulu)
        backToMenuButton = new JButton("Kembali ke Menu");
        backToMenuButton.setFont(new Font("Arial", Font.BOLD, 14));
        backToMenuButton.setBounds(100, 380, 160, 35);
        backToMenuButton.setBackground(new Color(255, 230, 200));
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setVisible(false);
        backToMenuButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(view);
            if (frame != null) {
                frame.dispose();
                App.main(null); // balik ke menu utama
            }
        });

        pipesCooldown = new Timer(1500, e -> {
            if (!gameOver) placePipes();
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void setView(View view) {
        this.view = view;
        this.view.setLayout(null);
        this.scoreLabel.setBounds(10, 10, 150, 30);
        this.view.add(scoreLabel);
        this.view.add(backToMenuButton); // tombol ditambahkan ke view
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWaiting() {
        return !started && !gameOver;
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight),
                pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void move() {
        if (gameOver) return;

        if (started) {
            player.setVelocityY(player.getVelocityY() + gravity);
            player.setPosY(player.getPosY() + player.getVelocityY());
            player.setPosY(Math.max(player.getPosY(), 0));
        }

        if (player.getPosY() + player.getHeight() >= frameHeight) {
            gameOver = true;
            stopGame();
            showBackButton();
        }

        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            if (started &&
                    player.getPosX() < pipe.getPosX() + pipe.getWidth() &&
                    player.getPosX() + player.getWidth() > pipe.getPosX() &&
                    player.getPosY() < pipe.getPosY() + pipe.getHeight() &&
                    player.getPosY() + player.getHeight() > pipe.getPosY()) {
                gameOver = true;
                stopGame();
                showBackButton();
            }

            if (!pipe.passed && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.passed = true;
                if (started) {
                    score++;
                    scoreLabel.setText("Score: " + score / 2);
                }
            }
        }
    }

    private void showBackButton() {
        SwingUtilities.invokeLater(() -> backToMenuButton.setVisible(true));
    }

    private void hideBackButton() {
        backToMenuButton.setVisible(false);
    }

    private void stopGame() {
        gameLoop.stop();
        pipesCooldown.stop();
    }

    private void restartGame() {
        gameOver = false;
        started = false;
        score = 0;
        scoreLabel.setText("Score: 0");
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
        hideBackButton(); // tombol hilang saat restart
        gameLoop.start();
        pipesCooldown.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (view != null) view.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            player.setVelocityY(-10);
            started = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
    }
}
