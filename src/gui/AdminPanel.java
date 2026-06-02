package gui;

import exception.EmptyFieldException;
import model.item;
import service.itemService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminPanel extends JPanel {

    private final itemService itemService;
    private DefaultTableModel tableModel;
    private JTable table;

    public AdminPanel(itemService itemService) {
        this.itemService = itemService;

        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel wrapper = new JPanel(new BorderLayout(0, 20));
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Admin Panel");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Type", "Name", "Category", "Date"},
                0
        );

        table = new JTable(tableModel);
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane tableScroll = new JScrollPane(table);

        JButton refreshButton = createButton("Refresh");
        JButton deleteButton = createButton("Delete Selected Item");

        refreshButton.addActionListener(e -> loadItems());
        deleteButton.addActionListener(e -> deleteSelectedItem());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);

        wrapper.add(title, BorderLayout.NORTH);
        wrapper.add(tableScroll, BorderLayout.CENTER);
        wrapper.add(buttonPanel, BorderLayout.SOUTH);

        add(wrapper, BorderLayout.CENTER);

        loadItems();
    }

    private void loadItems() {
        tableModel.setRowCount(0);

        for (item item : itemService.getAllItems()) {
            tableModel.addRow(new Object[]{
                    item.getId(),
                    item.getClass().getSimpleName(),
                    item.getName(),
                    item.getCategory(),
                    item.getDate()
            });
        }
    }

    private void deleteSelectedItem() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item first.");
            return;
        }

        String itemId = tableModel.getValueAt(selectedRow, 0).toString();

        try {
            boolean deleted = itemService.deleteItem(itemId);

            if (deleted) {
                JOptionPane.showMessageDialog(this, "Item deleted successfully.");
                loadItems();
            } else {
                JOptionPane.showMessageDialog(this, "Item not found.");
            }

        } catch (EmptyFieldException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(28, 28, 28));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(180, 40));

        return button;
    }
}