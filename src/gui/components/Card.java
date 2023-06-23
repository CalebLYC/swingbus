/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Caleb Lyc
 */
public class Card extends JPanel {
    private JLabel titleLabel;
    private JLabel valueLabel;

    public Card(String title, String value) {
        this.setPreferredSize(new Dimension(200, 100));
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.gray));

        setLayout(new GridBagLayout());

        titleLabel = new JLabel(title);
        valueLabel = new JLabel(value);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(5, 5, 5, 5); // Marges internes

        add(titleLabel, gbc);

        gbc.gridy = 1;
        add(valueLabel, gbc);
    }
}
