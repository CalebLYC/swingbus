/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Caleb Lyc
 */
public class Emploi extends JFrame {
    public Emploi() {
        setTitle("FICHE HORAIRE DES BUS UL SOTRAL DU 14 MARS 2022");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel titleLabel = new JLabel("FICHE HORAIRE DES BUS UL SOTRAL DU 14 MARS 2022");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"", "L14: AGOE ASSIYEYE", "L17:", "L13: ADETIKOPE", "L15: ZANGUERA", "L16: SEGBE"};
        Object[][] data = {
                {"", "ADJOLOLO", "B1", "B2", "B3", "B1"},
                {"Départ", "05:30", "06:00", "Départ", "05:30", "B1"},
                {"Départ", "B2", "B3", "B1", "B2", "Départ"},
                {"Départ", "05:30", "06:00", "05:30", "06:00", "Départ"},
                {"MATIN", "07:00", "08:00", "07:00", "07:00", "08:00"},
                {"PERIPHERIE", "11:30", "11:30", "11:30", "11:30", "11:30"},
                {"MIDI", "13:00", "11:30", "13:00", "13:00", "13:00"},
                {"13:00", "13:00", "13:00", "13:00", "13:00", "13:00"},
                {"16:30", "17:00", "16:30", "16:30", "17:00", "17:30"},
                {"SOIR", "CAMPUS", "18:30", "19:00", "19:00", "18:30"},
                {"L19: AKODESSEWA", "L21: CAMPUS- E.U", "L22:", "L18: ADAKPAME", "L20: AVEPOZO", "DJAGBLE"},
                {"", "B1", "B2", "B3", "B1", "B2"},
                {"Départ", "Départ", "Départ", "Départ", "Départ", "Départ"},
                {"Départ", "Départ", "Départ", "Départ", "Départ", "Départ"},
                {"05:30", "06:00", "05:30", "06:00", "05:30", "06:00"},
                {"07:00", "08:00", "07:00", "08:00", "07:00", "08:00"},
                {"MATIN", "PERIPHERIE", "11:30", "11:30", "11:30", "11:30"},
                {"13:00", "13:00", "13:00", "13:00", "13:00", "13:00"},
                {"CAMPUS", "16:30", "17:00", "17:30", "16:30", "17:00"},
                {"16:30", "17:00", "17:30", "16:30", "17:00", "16:30"},
                {"SOIR", "18:30", "19:00", "18:30", "19:00", "18:30"},
                {"18:30", "19:00", "18:30", "19:00", "18:30", "19:00"}
        };

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Custom cell renderer for specific columns
        CustomTableCellRenderer customRenderer = new CustomTableCellRenderer();
        table.getColumnModel().getColumn(0).setCellRenderer(customRenderer);
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmploiDuTemps();
            }
        });
    }

    // Custom cell renderer for specific columns
    private class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Apply custom styles based on row and column
            if (row % 2 == 0) {
                cell.setBackground(new Color(230, 230, 230));
            } else {
                cell.setBackground(Color.WHITE);
            }

            if (column == 0) {
                cell.setFont(cell.getFont().deriveFont(Font.BOLD));
            }

            return cell;
        }
    }
}
