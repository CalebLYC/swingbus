/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package swingbus;

import gui.pages.Login;
import javax.swing.UIManager;

/**
 *
 * @author Caleb Lyc
 */
public class Swingbus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); // Look and feel multiplateforme
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Look and feel du système par défaut
            //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); // Nimbus look and feel
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); // Metal look and feel
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); // Motif look and feel
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Windows look and feel
            
            new Login();

        } catch (Exception e) {
            // Gestion de l'exception
        }
    }

}
