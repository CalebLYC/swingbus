/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import dao.implementations.PersonnelDaoImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import services.implementations.BusServiceImpl;
import services.implementations.LigneServiceImpl;

/**
 *
 * @author Caleb Lyc
 */
public class Home extends Page {
    public static int pageId = 1;
    private Integer ligneNumber = new LigneServiceImpl().lister().size();
    private Integer busNumber = new BusServiceImpl().lister().size();
    private Integer condNumber = new PersonnelDaoImpl().lister().size();

    public Home() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        initializeComponents();
    }

    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(20, 20, 20, 20);

        JPanel card1 = createCardPanel("Nombre de lignes", ligneNumber.toString(), Color.decode("#1ABC9C"), "assets/img/ligne.png");
        JPanel card2 = createCardPanel("Nombre de bus", busNumber.toString(), Color.decode("#E67E22"), "assets/img/bus.png");
        JPanel card3 = createCardPanel("Membres du personnel", condNumber.toString(), Color.decode("#3498DB"), "assets/img/group.png");
        JPanel card4 = createCardPanel("Nombre moyen de passagers", "125", Color.decode("#9B59B6"), "assets/img/person.png");

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(card1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(card2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(card3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(card4, gbc);
    }

    private JPanel createCardPanel(String title, String value, Color color, String imagePath) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(color, 2));
        panel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(color);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(Color.BLACK);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 36));

        JLabel graphLabel = new JLabel(new ImageIcon(imagePath));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(20, 20, 10, 20);
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 10, 20);
        panel.add(valueLabel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 20, 20, 20);
        panel.add(graphLabel, gbc);

        return panel;
    }
}
