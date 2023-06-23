/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

/**
 *
 * @author Caleb Lyc
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.swing.JFrame;

public class Ligne extends Page {
    public static int pageid = 8;
    
    private String ligneName;
    private List<String> busList;
    private List<String> personnelList;

    public Ligne(String ligneName, List<String> busList, List<String> personnelList) {
        this.ligneName = ligneName;
        this.busList = busList;
        this.personnelList = personnelList;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel lignePanel = createLignePanel();
        JPanel busPanel = createBusPanel();
        JPanel personnelPanel = createPersonnelPanel();

        add(lignePanel, BorderLayout.NORTH);
        add(busPanel, BorderLayout.CENTER);
        add(personnelPanel, BorderLayout.SOUTH);
    }

    private JPanel createLignePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel ligneLabel = new JLabel("Ligne : " + ligneName);
        ligneLabel.setFont(new Font("Arial", Font.BOLD, 18));

        panel.add(ligneLabel);

        return panel;
    }

    private JPanel createBusPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Bus"));

        for (String bus : busList) {
            JPanel busCard = createCard(bus);
            panel.add(busCard);
        }

        return panel;
    }

    private JPanel createPersonnelPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Personnel"));

        for (String personnel : personnelList) {
            JPanel personnelCard = createCard(personnel);
            panel.add(personnelCard);
        }

        return panel;
    }

    private JPanel createCard(String text) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(200, 100));
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setBackground(Color.WHITE);

        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(label, BorderLayout.CENTER);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                List<String> busList = new ArrayList<>();
                busList.add("Bus 1");
                busList.add("Bus 2");
                busList.add("Bus 3");

                List<String> personnelList = new ArrayList<>();
                personnelList.add("Personnel 1");
                personnelList.add("Personnel 2");
                personnelList.add("Personnel 3");

                JFrame frame = new JFrame("Ligne");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new Ligne("Ligne 1", busList, personnelList));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}