import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    private Logic logic;

    int width = 360;
    int height = 640;

    public View(Logic logic) {
        this.logic = logic;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);

        setFocusable(true);
        addKeyListener(logic);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // gambar background
        Image bg = logic.getBackgroundImage();
        if (bg != null) {
            g.drawImage(bg, 0, 0, width, height, null);
        }

        // gambar pipa
        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (Pipe pipe : pipes) {
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // gambar burung
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        // teks tambahan
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));

        if (logic.isWaiting()) {
            drawCenteredText(g, "GET READY!");
        } else if (logic.isGameOver()) {
            drawCenteredText(g, "GAME OVER");
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Press R to Restart", width / 2 - 80, height / 2 + 40);
        }
    }

    private void drawCenteredText(Graphics g, String text) {
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2);
    }
}
