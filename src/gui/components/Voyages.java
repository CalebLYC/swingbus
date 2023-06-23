/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Caleb Lyc
 */
public class Voyages extends JPanel {
    public static int pageId = 6;
    private JTable table;

    public Voyages() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Voyages en cours");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Identifiant", "Nombre de passagers", "Ligne", "Actions"};
        Object[][] data = {
                {"1", "40", "Ligne 12"},
                {"2", "30", "Ligne 21"},
                {"3", "50", "Ligne 13"},
                {"4", "35", "Ligne 14"},
                {"5", "25", "Ligne 18"},
                {"6", "40", "Ligne 12"},
                {"7", "30", "Ligne 14"},
                {"8", "50", "Ligne 13"},
                {"9", "35", "Ligne 14"},
                {"10", "25", "Ligne 18"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(40);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        table.getTableHeader().setForeground(Color.BLACK);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonCellRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JButton consulterButton = createButton("Consulter", new Color(54, 176, 75));
        JButton contactButton = createButton("Contact", new Color(255, 128, 0));

        buttonsPanel.add(consulterButton);
        buttonsPanel.add(contactButton);

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());

        return button;
    }

    class ButtonCellRenderer implements TableCellRenderer {
        private JPanel panel;
        private JButton consulterButton;
        private JButton contactButton;

        public ButtonCellRenderer() {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            consulterButton = createButton("Consulter", new Color(54, 176, 75));
            contactButton = createButton("Contact", new Color(255, 128, 0));

            panel.add(consulterButton);
            panel.add(contactButton);

            consulterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    String identifiant = (String) table.getModel().getValueAt(row, 0);
                    System.out.println("Consulter : " + identifiant);
                }
            });

            contactButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    String identifiant = (String) table.getModel().getValueAt(row, 0);
                    System.out.println("Contact : " + identifiant);
                }
            });
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return panel;
        }
    }
}
