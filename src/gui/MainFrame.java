package gui;

import service.claimService;
import service.itemService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private itemService itemService;
    private claimService claimService;

    public MainFrame() {
        setTitle("Lost & Found Management System");
        setSize(1150, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        itemService = new itemService();
        claimService = new claimService();

        JPanel root = new JPanel(new BorderLayout(24, 24));
        root.setBackground(new Color(226, 226, 222));
        root.setBorder(BorderFactory.createEmptyBorder(28, 28, 28, 28));

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setOpaque(false);

        contentPanel.add(createDashboardPage(), "dashboard");
        contentPanel.add(new RegisterLostItemPanel(itemService), "lost");
        contentPanel.add(new RegisterFoundItemPanel(itemService), "found");
        contentPanel.add(new SearchPanel(itemService), "search");
        contentPanel.add(new ClaimPanel(claimService), "claims");
        contentPanel.add(new AdminPanel(itemService, claimService), "admin");

        root.add(createSidebar(), BorderLayout.WEST);
        root.add(contentPanel, BorderLayout.CENTER);

        add(root);
        setVisible(true);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new GlassPanel();
        sidebar.setPreferredSize(new Dimension(210, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(28, 22, 28, 22));

        JLabel logo = new JLabel("LFMS");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(40));

        sidebar.add(menuButton("Dashboard", "dashboard", true));
        sidebar.add(menuButton("Register Lost Item", "lost", false));
        sidebar.add(menuButton("Register Found Item", "found", false));
        sidebar.add(menuButton("Search Items", "search", false));
        sidebar.add(menuButton("Claims", "claims", false));
        sidebar.add(menuButton("Admin Panel", "admin", false));

        return sidebar;
    }

    private JButton menuButton(String text, String page, boolean active) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(165, 42));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (active) {
            button.setBackground(new Color(28, 28, 28));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(245, 245, 245));
            button.setForeground(new Color(35, 35, 35));
        }

        button.addActionListener(e -> {
            if (page.equals("admin")) {
                JPasswordField passwordField = new JPasswordField();

                int option = JOptionPane.showConfirmDialog(
                        this,
                        passwordField,
                        "Enter Admin Password",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (option == JOptionPane.OK_OPTION) {
                    String password = new String(passwordField.getPassword());

                    if (password.equals("admin123")) {
                        cardLayout.show(contentPanel, page);
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Incorrect admin password."
                        );
                    }
                }
            } else {
                cardLayout.show(contentPanel, page);
            }
        });

        return button;
    }

    private JPanel createDashboardPage() {
        JPanel page = new JPanel(new BorderLayout(0, 24));
        page.setOpaque(false);

        JLabel title = new JLabel("Lost & Found Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));

        JPanel grid = new JPanel(new GridLayout(2, 2, 24, 24));
        grid.setOpaque(false);

        grid.add(infoCard("Register Lost Item", "Submit details of an item you lost."));
        grid.add(infoCard("Register Found Item", "Report an item you found on campus."));
        grid.add(infoCard("Search Items", "Browse lost and found records."));
        grid.add(infoCard("Claims", "Submit and review claim requests."));

        page.add(title, BorderLayout.NORTH);
        page.add(grid, BorderLayout.CENTER);

        return page;
    }

    private JPanel createPlaceholderPage(String pageTitle) {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);

        JPanel card = new GlassPanel();
        card.setPreferredSize(new Dimension(780, 430));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));

        JLabel title = new JLabel(pageTitle);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JLabel subtitle = new JLabel("This page will be implemented next.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setForeground(new Color(90, 90, 90));

        card.add(title);
        card.add(Box.createVerticalStrut(12));
        card.add(subtitle);

        wrapper.add(card);
        return wrapper;
    }

    private JPanel infoCard(String title, String description) {
        JPanel card = new GlassPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(28, 28, 28, 28));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 21));

        JLabel descLabel = new JLabel("<html><p style='width:240px'>" + description + "</p></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        descLabel.setForeground(new Color(80, 80, 80));

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(18));
        card.add(descLabel);

        return card;
    }

    public static class GlassPanel extends JPanel {

        public GlassPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(255, 255, 255, 170));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);

            g2.setColor(new Color(255, 255, 255, 210));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 32, 32);

            g2.dispose();
            super.paintComponent(g);
        }
    }
}