package gui;

import exception.EmptyFieldException;
import exception.InvalidClaimException;
import model.claimRequest;
import model.item;
import service.claimService;
import service.itemService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminPanel extends JPanel {

    private final itemService itemService;
    private final claimService claimService;

    private DefaultTableModel itemTableModel;
    private DefaultTableModel claimTableModel;

    private JTable itemTable;
    private JTable claimTable;

    public AdminPanel(itemService itemService, claimService claimService) {
        this.itemService = itemService;
        this.claimService = claimService;

        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel wrapper = new JPanel(new BorderLayout(0, 20));
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Admin Panel");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tabs.addTab("Items", createItemsPanel());
        tabs.addTab("Claims", createClaimsPanel());

        wrapper.add(title, BorderLayout.NORTH);
        wrapper.add(tabs, BorderLayout.CENTER);

        add(wrapper, BorderLayout.CENTER);

        loadItems();
        loadClaims();
    }

    private JPanel createItemsPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setOpaque(false);

        itemTableModel = new DefaultTableModel(
                new Object[]{"ID", "Type", "Name", "Category", "Date"},
                0
        );

        itemTable = new JTable(itemTableModel);
        itemTable.setRowHeight(32);

        JScrollPane scrollPane = new JScrollPane(itemTable);

        JButton refreshButton = createButton("Refresh Items");
        JButton deleteButton = createButton("Delete Selected Item");

        refreshButton.addActionListener(e -> loadItems());
        deleteButton.addActionListener(e -> deleteSelectedItem());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createClaimsPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setOpaque(false);

        claimTableModel = new DefaultTableModel(
                new Object[]{"Claim ID", "User ID", "Item ID", "Status", "Submitted Date"},
                0
        );

        claimTable = new JTable(claimTableModel);
        claimTable.setRowHeight(32);

        JScrollPane scrollPane = new JScrollPane(claimTable);

        JButton refreshButton = createButton("Refresh Claims");
        JButton approveButton = createButton("Approve Claim");
        JButton rejectButton = createButton("Reject Claim");

        refreshButton.addActionListener(e -> loadClaims());
        approveButton.addActionListener(e -> approveSelectedClaim());
        rejectButton.addActionListener(e -> rejectSelectedClaim());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(refreshButton);
        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void loadItems() {
        itemTableModel.setRowCount(0);

        for (item item : itemService.getAllItems()) {
            itemTableModel.addRow(new Object[]{
                    item.getId(),
                    item.getClass().getSimpleName(),
                    item.getName(),
                    item.getCategory(),
                    item.getDate()
            });
        }
    }

    private void loadClaims() {
        claimTableModel.setRowCount(0);

        for (claimRequest claim : claimService.getAllClaims()) {
            claimTableModel.addRow(new Object[]{
                    claim.getClaimId(),
                    claim.getUserId(),
                    claim.getItemId(),
                    claim.getStatus(),
                    claim.getSubmittedDate()
            });
        }
    }

    private void deleteSelectedItem() {
        int selectedRow = itemTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item first.");
            return;
        }

        String itemId = itemTableModel.getValueAt(selectedRow, 0).toString();

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

    private void approveSelectedClaim() {
        int selectedRow = claimTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a claim first.");
            return;
        }

        String claimId = claimTableModel.getValueAt(selectedRow, 0).toString();

        try {
            claimService.approveClaim(claimId);
            JOptionPane.showMessageDialog(this, "Claim approved successfully.");
            loadClaims();

        } catch (EmptyFieldException | InvalidClaimException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void rejectSelectedClaim() {
        int selectedRow = claimTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a claim first.");
            return;
        }

        String claimId = claimTableModel.getValueAt(selectedRow, 0).toString();

        try {
            claimService.rejectClaim(claimId);
            JOptionPane.showMessageDialog(this, "Claim rejected successfully.");
            loadClaims();

        } catch (EmptyFieldException | InvalidClaimException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(new Color(28, 28, 28));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(170, 38));

        return button;
    }
}