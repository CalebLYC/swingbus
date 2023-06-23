/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import gui.Window;
import javax.swing.JLabel;

/**
 *
 * @author Caleb Lyc
 */
public class Historique extends Page {
    public static int pageId = 7;
    
    public Historique(){
        Window.getInstance().setTitle("Historique");
        this.add(new JLabel("Historique"));
    }
}
