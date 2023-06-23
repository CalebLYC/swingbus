/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import entities.Voyage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import services.implementations.VoyageServiceImpl;
import services.interfaces.VoyageService;

/**
 *
 * @author Caleb Lyc
 */
public class VoyagesEnCours extends Page {
    public static int pageId = 6;
    private VoyageService service = new VoyageServiceImpl();
    private List<Voyage> voyages;

    private JTable table;

    public VoyagesEnCours() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Voyages en cours");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

       
        JButton consulterButton = createButton("Consulter la fiche", new Color(41, 128, 185));
        JButton contacterButton = createButton("Contacter", new Color(123, 45, 67));
       

        buttonsPanel.add(consulterButton);
        buttonsPanel.add(contacterButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        voyages = service.lister();
        String[] columnNames = {"Identifiant", "Ligne", "Bus", "Nombre de places", "Conducteur", "Départ"};
        Object[][] data = new Object[voyages.size()][6];
        for(int i=0; i<voyages.size(); i++){
            data[i][0] = voyages.get(i).getId();
            data[i][1] = "Ligne " + voyages.get(i).getLigne().getNumero() + ": " + voyages.get(i).getLigne().getNom();
            data[i][2] = voyages.get(i).getBus().getImmatriculation() + ": " + voyages.get(i).getBus().getMarque();
            data[i][3] = voyages.get(i).getNombrePassagers();
            data[i][4] = voyages.get(i).getPersonnel().get(0).getNom() + " " + voyages.get(i).getPersonnel().get(0).getPrenom();
            if(voyages.get(i).isVersPeripherie()){
                data[i][5] = voyages.get(i).getLigne().getDepart();
            }else{
                data[i][5] = voyages.get(i).getLigne().getDestination();
            }
        }

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
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        consulterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Integer id = (Integer) table.getValueAt(row, 0);
                    JOptionPane.showMessageDialog(null, "Consulter la fiche de: " + id.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun voyage sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        contacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Integer id = (Integer) table.getValueAt(row, 0);
                    JOptionPane.showMessageDialog(null, "Contacter : " + id.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun voyage sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
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

    private JButton createButtonWithIcon(String text, Color color, String iconPath) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setIcon(new ImageIcon(iconPath));
        button.setHorizontalTextPosition(SwingConstants.LEFT);

        return button;
    }
}
