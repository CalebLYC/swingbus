/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import gui.Window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import services.implementations.BusServiceImpl;
import services.interfaces.BusService;

/**
 *
 * @author Caleb Lyc
 */
public class Bus extends JPanel {
    public static int pageId = 4;
    private JTable table;
    BusService service = new BusServiceImpl();
    List<entities.Bus> busList = new ArrayList<>();

    public Bus() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JButton addButton = new JButton("Ajouter");
        addButton.setPreferredSize(new Dimension(80, 25));
        addButton.setBackground(new Color(52, 152, 219)); // Couleur bleue
        addButton.setForeground(Color.WHITE);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddBus();
            }
        });

        add(addButton, BorderLayout.NORTH);

        String[] columnNames = {"Identifiant", "Numéro d'immatriculation", "Nombre de places", "Marque"};

    busList = service.lister();
    Object[][] data = new Object[busList.size()][4];

for (int i = 0; i < busList.size(); i++) {
    entities.Bus bus = busList.get(i);
    data[i][0] = bus.getId();
    data[i][1] = bus.getImmatriculation();
    data[i][2] = bus.getMaximalPlaces();
    data[i][3] = bus.getMarque();
}


        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JButton modifyButton = new JButton("Modifier");
        JButton deleteButton = new JButton("Supprimer");
        modifyButton.setPreferredSize(new Dimension(80, 25));
        deleteButton.setPreferredSize(new Dimension(80, 25));
        modifyButton.setBackground(new Color(46, 204, 113)); // Couleur verte
        modifyButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(231, 76, 60)); // Couleur rouge
        deleteButton.setForeground(Color.WHITE);

         modifyButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Object selectedValue = table.getValueAt(selectedRow, 0);
            if (selectedValue instanceof Integer) {
                Integer id = (Integer) selectedValue;
                new EditBus(service.trouver(id));
            } else {
                JOptionPane.showMessageDialog(Bus.this, "Valeur ID non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(Bus.this, "Veuillez sélectionner une ligne.", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }
});

        deleteButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Object selectedValue = table.getValueAt(selectedRow, 0);
            if (selectedValue instanceof Integer) {
                Integer id = (Integer) selectedValue;
                service.supprimer(id);
                JOptionPane.showMessageDialog(Bus.this, "Bus supprimée avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                Window.getInstance().changePage(Bus.pageId);
            } else {
                JOptionPane.showMessageDialog(Bus.this, "Valeur ID non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(Bus.this, "Veuillez sélectionner une ligne.", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }
});


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void afficherInfosLigne(String action, String identifiant) {
        JOptionPane.showMessageDialog(Bus.this, action + " l'élément avec l'identifiant : " + identifiant);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Gestion des bus");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new Bus());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
