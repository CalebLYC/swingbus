/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import gui.Window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import services.implementations.PersonnelServiceImpl;
import services.interfaces.PersonnelService;

/**
 *
 * @author Caleb Lyc
 */
public class Personnel extends JPanel {
    public static int pageId = 5;
    private JTable table;
    private PersonnelService service = new PersonnelServiceImpl();
    private List<entities.Personnel> personnel;

    public Personnel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        Window.getInstance().setTitle("Gestion du personnel");
        
        personnel = service.lister();

        // Données du personnel
        String[] columnNames = {"Identifiant", "Nom", "Prénom", "Date de naissance", "Poste"};
        Object[][] data = new Object[personnel.size()][5];
        for (int i = 0; i < personnel.size(); i++) {
            entities.Personnel perso = personnel.get(i);
    data[i][0] = perso.getId();
    data[i][1] = perso.getNom();
    data[i][2] = perso.getPrenom();
    data[i][3] = perso.getDateNaissance();
    data[i][4] = perso.getPoste().getLibelle();
}

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setRowHeight(30);
        table.getColumn("Poste").setCellRenderer(new LinkRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JButton addButton = new JButton("Ajouter un personnel");
        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.setFont(addButton.getFont().deriveFont(Font.BOLD, 14f));
        addButton.setBackground(new Color(52, 73, 94));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddPersonnel();
                } catch (ParseException ex) {
                    Logger.getLogger(Personnel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton modifyButton = new JButton("Modifier");
        JButton deleteButton = new JButton("Supprimer");
        modifyButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setPreferredSize(new Dimension(100, 30));
        modifyButton.setBackground(new Color(46, 204, 113));
        deleteButton.setBackground(Color.RED);
        modifyButton.setForeground(Color.WHITE);
        deleteButton.setForeground(Color.WHITE);
        modifyButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);
        
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Object selectedValue = table.getValueAt(selectedRow, 0);
                    if(selectedValue instanceof Integer){
                        Integer id = (Integer)selectedValue;
                        try {
                            new EditPersonnel(service.trouver(id));
                        } catch (ParseException ex) {
                            Logger.getLogger(Personnel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else {
                JOptionPane.showMessageDialog(Personnel.this, "Valeur ID non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
                } else {
                    JOptionPane.showMessageDialog(Personnel.this, "Aucune ligne sélectionnée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Object selectedValue = table.getValueAt(selectedRow, 0);
                    if(selectedValue instanceof Integer){
                        Integer id = (Integer)selectedValue;
                        service.supprimer(id);
                        JOptionPane.showMessageDialog(Personnel.this, "Membre du personnel supprimée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        Window.getInstance().changePage(Personnel.pageId);
                    }else {
                JOptionPane.showMessageDialog(Personnel.this, "Valeur ID non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
                } else {
                    JOptionPane.showMessageDialog(Personnel.this, "Aucune ligne sélectionnée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.add(modifyButton);
        buttonsPanel.add(deleteButton);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(addButton, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    private class LinkRenderer extends DefaultTableCellRenderer {
        private JButton linkButton;

        public LinkRenderer() {
            linkButton = new JButton();
            linkButton.setBorderPainted(false);
            linkButton.setContentAreaFilled(false);
            linkButton.setFocusPainted(false);
            linkButton.setOpaque(true);
            linkButton.setForeground(Color.BLUE);
            linkButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            linkButton.setText((String) value);
            return linkButton;
        }
    }
}
