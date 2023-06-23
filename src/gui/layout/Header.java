/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.layout;

import gui.components.SearchBar;
import gui.components.WhiteSpace;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Caleb Lyc
 */
public class Header extends JPanel {
    private JPanel logo;
    private JPanel searchbar;

    public Header() {
        this.setBackground(new Color(44, 62, 80)); 
        this.setPreferredSize(new Dimension(1400, 70));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        initializeComponents();
    }

    private void initializeComponents() {
        logo = new gui.components.Logo();
        add(logo);
        this.add(new WhiteSpace(new Dimension(500, 60), Color.darkGray));
        searchbar = new SearchBar();
        this.add(searchbar);
    }
}

class Logo extends JPanel {
    private JLabel label;

    public Logo() {
        this.setBackground(new Color(44, 62, 80)); // Couleur de fond personnalisée
        initializeComponents();
    }

    private void initializeComponents() {
        label = new JLabel("SOTRAL");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.white);
        add(label);
    }
}
