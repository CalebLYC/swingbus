/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import gui.pages.AddLigne;

/**
 *
 * @author Caleb Lyc
 */
public class AddLigneButton extends JPanel {
    private JButton addButton;
    private String text;
    
    public AddLigneButton(String text){
        this.setLayout(new BorderLayout());
        this.text = text;
        initializeComponents();
    }
    private void initializeComponents(){
        addButton = new JButton(text);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(52, 73, 94));
        addButton.setPreferredSize(new Dimension(150, 60));
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddLigne();
            }
        }); 
        //this.add(new WhiteSpace(new Dimension(800,100)), Color.lightGray);
          JPanel rightMargin = new JPanel();
        rightMargin.setPreferredSize(new Dimension(100, 10));
        JPanel panel = new JPanel();
        panel.add(rightMargin, BorderLayout.EAST);
        
        panel.add(addButton, BorderLayout.CENTER);
        this.add(panel, BorderLayout.EAST);
        
      
        JPanel bottomMargin = new JPanel();
        bottomMargin.setPreferredSize(new Dimension(10, 10));
        
        
        this.add(bottomMargin, BorderLayout.SOUTH);
    }
}
