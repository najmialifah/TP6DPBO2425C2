import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class App {

    private static JFrame frame;

    public static void main(String[] args) {
        showMenu();
    }

    // Tampilan menu awal
    private static void showMenu() {
        frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image bg = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setLayout(null);

        JLabel title = new JLabel("FLAPPY BIRD", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(30, 150, 300, 50);
        panel.add(title);

        // Tombol Play
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 16));
        playButton.setBounds(120, 270, 120, 40);
        playButton.setBackground(new Color(255, 240, 180));
        playButton.setFocusPainted(false);
        playButton.addActionListener((ActionEvent e) -> startGame());
        panel.add(playButton);

        // Tombol Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBounds(120, 330, 120, 40);
        exitButton.setBackground(new Color(255, 200, 200));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    // Setelah klik Play, tampilkan game
    private static void startGame() {
        frame.getContentPane().removeAll();

        Logic logika = new Logic();
        View tampilan = new View(logika);
        logika.setView(tampilan);

        frame.add(tampilan);
        frame.revalidate();
        frame.repaint();

        SwingUtilities.invokeLater(() -> {
            tampilan.requestFocusInWindow();
        });
    }
}
