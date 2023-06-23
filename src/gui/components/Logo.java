/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Caleb Lyc
 */

public class Logo extends JPanel {
    private JLabel label;
    private ImageIcon icon;

    public Logo() {
        this.setBackground(Color.darkGray);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Utilisation de FlowLayout avec alignement à gauche et espacement

        initializeComponents();
    }

    private void initializeComponents() {
        ImageIcon originalIcon = new ImageIcon("assets/img/logo.png");
        Image image = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        label = new JLabel(icon);
        label.setText("SOTRAL");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.white);
        label.setHorizontalTextPosition(SwingConstants.RIGHT); // Alignement du texte à droite de l'icône
        label.setVerticalTextPosition(SwingConstants.CENTER); // Alignement vertical centré du texte

        add(label);
    }
}