/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Caleb Lyc
 */
public class WhiteSpace extends JPanel {
    public WhiteSpace(Dimension dimension, Color color){
        this.setPreferredSize(dimension);
        this.setBackground(color);
    }
    public WhiteSpace(Dimension dimension){
        this(dimension, Color.white);
    }
}
