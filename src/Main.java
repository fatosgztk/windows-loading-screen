import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SymDev AI Loading");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frimport javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createUI);
    }

    private static void createUI() {
        JFrame frame = new JFrame("SymDev AI Boot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setUndecorated(true); // Windows boot hissi için
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        // LOGO
        JLabel logo = new JLabel("SYMDEV");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 48));
        panel.add(logo, gbc);

        // LOADING LABEL
        JLabel loading = new JLabel("Loading");
        loading.setForeground(Color.LIGHT_GRAY);
        loading.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        panel.add(loading, gbc);

        frame.setContentPane(panel);
        frame.setVisible(true);

        startLoadingAnimation(loading);
    }

    private static void startLoadingAnimation(JLabel label) {
        new Thread(() -> {
            String[] dots = {"", ".", "..", "..."};
            int i = 0;

            try {
                while (true) {
                    String text = "Loading" + dots[i % dots.length];
                    label.setText(text);
                    i++;
                    Thread.sleep(500);
                }
            } catch (InterruptedException ignored) {}
        }).start();
    }
}ame.setSize(800, 600);
            frame.setLayout(new GridBagLayout());

            JLabel label = new JLabel("Hello SymDev AI");
            label.setFont(new Font("Arial", Font.BOLD, 24));

            frame.add(label);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}