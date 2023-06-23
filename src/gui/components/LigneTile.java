/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Caleb Lyc
 */
public class LigneTile extends JPanel {
    public LigneTile(String numero, String nom){
        this.add(new JLabel("Ligne " + numero));
        this.add(new JLabel(nom));
        this.add(new JLabel("Supprimer"));
    }
}
