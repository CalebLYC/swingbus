/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Caleb Lyc
 */
public class SearchBar extends JPanel {
    private JTextField searchBar;

    public SearchBar() {
        this.setPreferredSize(new Dimension(400, 50));
        this.setBackground(new Color(52, 73, 94)); // Couleur de fond personnalisée
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel label = new JLabel("Rechercher :");
        label.setForeground(Color.white);
        this.add(label);

        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(300, 30));
        searchBar.setBackground(new Color(189, 195, 199)); // Couleur de fond de la barre de recherche
        searchBar.setForeground(Color.black);
        searchBar.setFont(searchBar.getFont().deriveFont(14f));

        this.add(searchBar);
    }
}
