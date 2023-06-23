/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import gui.Window;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caleb Lyc
 */
public class GestionPage extends JPanel{
    public static int pageId = 4;
    private JTable table;

    public GestionPage(String title, String[] columnNames, Object[][] data){
        Window.getInstance().setTitle(title);
        
        // Créer le tableau avec les colonnes spécifiées
        

        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table = new JTable(model);

        // Ajouter un JScrollPane pour permettre le défilement si le contenu du tableau dépasse l'espace disponible
        JScrollPane scrollPane = new JScrollPane(table);

        // Définir la mise en page de la page Bus
        this.setLayout(new BorderLayout());
        this.add(new AddButton(""), BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        
        JPanel eastMargin = (new JPanel());
        eastMargin.setPreferredSize(new Dimension(100,500));
        JPanel westMargin = new JPanel();
        westMargin.setPreferredSize(new Dimension(100,500));
        JPanel topMargin = new JPanel();
        topMargin.setPreferredSize(new Dimension(500,100));
        JPanel bottomMargin = new JPanel();
        bottomMargin.setPreferredSize(new Dimension(500,100));
        
        this.add(eastMargin, BorderLayout.EAST);
        this.add(westMargin, BorderLayout.WEST);
        this.add(bottomMargin, BorderLayout.SOUTH);
    }
    
    public GestionPage(){
        
    }
}

