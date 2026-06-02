package gui;

import exception.DuplicateItemException;
import exception.EmptyFieldException;
import model.foundItem;
import service.itemService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class RegisterFoundItemPanel extends JPanel {

    private itemService itemService;

    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField categoryField;
    private JTextField dateField;
    private JTextField locationField;
    private JTextField holderField;
    private JTextField storedAtField;

    public RegisterFoundItemPanel(itemService itemService) {

        this.itemService = itemService;

        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel card = new MainFrame.GlassPanel();
        card.setPreferredSize(new Dimension(780, 720));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Register Found Item");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Submit details of a found item.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(90, 90, 90));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(25));

        nameField = createTextField();
        descriptionField = createTextField();
        categoryField = createTextField();
        dateField = createTextField();
        locationField = createTextField();
        holderField = createTextField();
        storedAtField = createTextField();

        card.add(createField("Item Name", nameField));
        card.add(Box.createVerticalStrut(12));
        card.add(createField("Description", descriptionField));
        card.add(Box.createVerticalStrut(12));
        card.add(createField("Category", categoryField));
        card.add(Box.createVerticalStrut(12));
        card.add(createField("Date Found (YYYY-MM-DD)", dateField));
        card.add(Box.createVerticalStrut(12));
        card.add(createField("Found Location", locationField));
        card.add(Box.createVerticalStrut(12));
        card.add(createField("Current Holder", holderField));
        card.add(Box.createVerticalStrut(12));
        card.add(createField("Stored At", storedAtField));
        card.add(Box.createVerticalStrut(25));

        JButton submitButton = createSubmitButton();
        submitButton.addActionListener(e -> submitFoundItem());

        card.add(submitButton);
        wrapper.add(card);

        JScrollPane scrollPane = new JScrollPane(wrapper);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void submitFoundItem() {
        try {
            foundItem item = new foundItem(
                    "F" + System.currentTimeMillis(),
                    nameField.getText(),
                    descriptionField.getText(),
                    categoryField.getText(),
                    LocalDate.parse(dateField.getText()),
                    locationField.getText(),
                    holderField.getText(),
                    storedAtField.getText()
            );

            itemService.addItem(item);

            JOptionPane.showMessageDialog(this, "Found item registered successfully!");
            clearFields();

        } catch (EmptyFieldException | DuplicateItemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Date must be YYYY-MM-DD.");
        }
    }

    private JPanel createField(String labelText, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setPreferredSize(new Dimension(0, 42));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        return field;
    }

    private JButton createSubmitButton() {
        JButton button = new JButton("Submit Found Item");
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(28, 28, 28));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(180, 42));
        button.setMaximumSize(new Dimension(180, 42));

        return button;
    }

    private void clearFields() {
        nameField.setText("");
        descriptionField.setText("");
        categoryField.setText("");
        dateField.setText("");
        locationField.setText("");
        holderField.setText("");
        storedAtField.setText("");
    }
}