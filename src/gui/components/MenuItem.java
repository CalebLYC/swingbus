/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import gui.Window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Caleb Lyc
 */
public class MenuItem extends JButton {
    private int pageId;
    
    public MenuItem(int id, String text) {
        // Définition du texte et de l'icône du bouton
        this.setText(text);
        this.setIcon(new ImageIcon("icon.png")); // Remplacez "icon.png" par le chemin de votre icône
        
        this.pageId = id;
        setProperties(); 
        listen();
    }
    
    private void setProperties(){
        // Personnalisation de l'apparence
        this.setBackground(Color.darkGray);
        this.setPreferredSize(new Dimension(120, 40));
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setForeground(Color.black);

        // Ajout d'un effet de survol
        this.setRolloverEnabled(true);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setRolloverIcon(new ImageIcon("hover_icon.png")); // Remplacez "hover_icon.png" par le chemin de votre icône de survol
        this.setForeground(Color.white);
    }
    
    private void listen(){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.getInstance().changePage(pageId);
            }
        });
    }
}
