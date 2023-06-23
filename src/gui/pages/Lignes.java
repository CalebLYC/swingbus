/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import gui.Window;
import gui.components.AddLigneButton;
import gui.components.LignesTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Caleb Lyc
 */
public class Lignes extends Page {
    public static int pageId = 3;
    
    public Lignes(){
        Window.getInstance().setTitle("Gestion des lignes");
        this.setLayout(new BorderLayout());
        this.add(new AddLigneButton("Ajouter une ligne"), BorderLayout.NORTH);
        this.add(new LignesTable(), BorderLayout.CENTER);
        
        JPanel eastMargin = new JPanel();
        eastMargin.setPreferredSize(new Dimension(5,5));
        this.add(eastMargin, BorderLayout.EAST);
        JPanel westMargin = new JPanel();
        westMargin.setPreferredSize(new Dimension(5,5));
        this.add(westMargin, BorderLayout.WEST);
    }
}

