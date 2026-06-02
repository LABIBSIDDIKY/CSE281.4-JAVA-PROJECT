package gui;

import exception.EmptyFieldException;
import exception.InvalidClaimException;
import model.claimRequest;
import model.claimStatus;
import service.claimService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ClaimPanel extends JPanel {

    private final claimService claimService;

    private JTextField userIdField;
    private JTextField itemIdField;

    public ClaimPanel(claimService claimService) {

        this.claimService = claimService;

        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);

        JPanel card = new MainFrame.GlassPanel();
        card.setPreferredSize(new Dimension(700, 450));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Submit Claim Request");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JLabel subtitle = new JLabel("Claim an item that you believe belongs to you.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(25));

        userIdField = createTextField();
        itemIdField = createTextField();

        card.add(createField("User ID", userIdField));
        card.add(Box.createVerticalStrut(15));

        card.add(createField("Item ID", itemIdField));
        card.add(Box.createVerticalStrut(25));

        JButton submitButton = new JButton("Submit Claim");
        submitButton.setFocusPainted(false);
        submitButton.setBackground(new Color(28, 28, 28));
        submitButton.setForeground(Color.WHITE);

        submitButton.addActionListener(e -> submitClaim());

        card.add(submitButton);

        wrapper.add(card);

        add(wrapper, BorderLayout.CENTER);
    }

    private void submitClaim() {

        try {

            claimRequest claim = new claimRequest(
                    "C" + System.currentTimeMillis(),
                    userIdField.getText(),
                    itemIdField.getText(),
                    claimStatus.PENDING,
                    LocalDate.now()
            );

            claimService.submitClaim(claim);

            JOptionPane.showMessageDialog(
                    this,
                    "Claim submitted successfully!"
            );

            userIdField.setText("");
            itemIdField.setText("");

        } catch (EmptyFieldException | InvalidClaimException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private JPanel createField(String labelText, JTextField field) {

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    private JTextField createTextField() {

        JTextField field = new JTextField();

        field.setPreferredSize(new Dimension(0, 42));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));

        return field;
    }
}