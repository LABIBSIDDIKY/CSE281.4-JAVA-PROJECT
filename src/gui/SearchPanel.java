package gui;

import model.item;
import service.itemService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchPanel extends JPanel {

    private itemService itemService;
    private JTextField searchField;
    private DefaultTableModel tableModel;

    public SearchPanel(itemService itemService) {
        this.itemService = itemService;

        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel wrapper = new JPanel(new BorderLayout(0, 20));
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Search Items");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JPanel searchBar = new MainFrame.GlassPanel();
        searchBar.setLayout(new BorderLayout(12, 0));
        searchBar.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(28, 28, 28));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);

        searchBar.add(searchField, BorderLayout.CENTER);
        searchBar.add(searchButton, BorderLayout.EAST);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Name", "Category", "Date", "Type"},
                0
        );

        JTable table = new JTable(tableModel);
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane tableScroll = new JScrollPane(table);

        searchButton.addActionListener(e -> loadItems());

        wrapper.add(title, BorderLayout.NORTH);
        wrapper.add(searchBar, BorderLayout.CENTER);
        wrapper.add(tableScroll, BorderLayout.SOUTH);

        add(wrapper, BorderLayout.CENTER);
    }

    private void loadItems() {
        tableModel.setRowCount(0);

        String keyword = searchField.getText();

        try {
            for (item item : itemService.searchByName(keyword)) {
                tableModel.addRow(new Object[]{
                        item.getId(),
                        item.getName(),
                        item.getCategory(),
                        item.getDate(),
                        item.getClass().getSimpleName()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}