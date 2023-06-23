/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.layout;

import gui.Window;
import gui.pages.Bus;
import gui.pages.EmploiDuTemps;
import gui.pages.Historique;
import gui.pages.Home;
import gui.pages.Lignes;
import gui.pages.Personnel;
import gui.pages.VoyagesEnCours;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Caleb Lyc
 */
public class Sidebar extends JPanel {
    private Window window;
    
    public Sidebar(Window window) {
        this.window = window;
        this.setBackground(new Color(41, 128, 185)); // Couleur de fond personnalisée
        this.setPreferredSize(new Dimension(140, 800));
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Ajout des boutons de menu avec des icônes
        MenuItem item1 = new MenuItem(Home.pageId, "Tableau de bord", "assets/img/home.png");
        MenuItem item2 = new MenuItem(EmploiDuTemps.pageId, "Emploi du temps", "assets/img/schedule.png");
        MenuItem item3 = new MenuItem(Lignes.pageId, "Lignes", "assets/img/ligne.png");
        MenuItem item4 = new MenuItem(Bus.pageId, "Bus", "assets/img/bus.png");
        MenuItem item5 = new MenuItem(Personnel.pageId, "Personnel", "assets/img/group.png");
        MenuItem item6 = new MenuItem(VoyagesEnCours.pageId, "Voyages en cours", "assets/img/travel.png");
        MenuItem item7 = new MenuItem(Historique.pageId, "Historique", "assets/img/history.png");
        
        switch (window.getCurrentPage()) {
            case 1:
                item1.setBackground(new Color(63, 81, 181));
                break;
             case 2:
                item2.setBackground(new Color(63, 81, 181));
                break;
             case 3:
                item3.setBackground(new Color(63, 81, 181));
                break;
             case 4:
                item4.setBackground(new Color(63, 81, 181));
                break;
             case 5:
                item5.setBackground(new Color(63, 81, 181));
                break;
             case 6:
                item6.setBackground(new Color(63, 81, 181));
                break;
             case 7:
                item7.setBackground(new Color(63, 81, 181));
                break;
            default:
                item1.setBackground(new Color(63, 81, 181));
        }

        this.add(item1);
        this.add(item2);
        this.add(item3);
        this.add(item4);
        this.add(item5);
        this.add(item6);
        this.add(item7);
    }
}

class MenuItem extends JButton {
    private int pageId;
    private String text;

    public MenuItem(int id, String text, String iconPath) {
        this.text = text;
        this.setText(text);
        this.setIcon(new ImageIcon(iconPath));

        this.pageId = id;
        setProperties();
        listen();
    }

    private void setProperties() {
        // Couleur de fond normale
        this.setBackground(new Color(41, 128, 185));

        // Couleur de fond lors du survol
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new Color(63, 81, 181)); // Changer la couleur de survol selon vos préférences
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new Color(41, 128, 185)); // Revenir à la couleur normale après le survol
            }
        });

        // Autres propriétés du bouton
        this.setPreferredSize(new Dimension(120, 40));
        this.setFont(new Font("Arial", Font.BOLD, 14));
        //this.setBackground(Color.WHITE);
        this.setForeground(Color.white);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setOpaque(true);

        // Redimensionner l'icône et changer sa couleur en blanc
        Icon icon = getIcon();
        if (icon instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();
            Image resizedImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            resizedIcon.setDescription(text);
            setIcon(resizedIcon);
            setDisabledIcon(resizedIcon);
        }
    }

    private void listen() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.getInstance().changePage(pageId);
            }
        });
    }
}