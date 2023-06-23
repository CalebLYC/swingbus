/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import gui.layout.Header;
import gui.layout.Sidebar;
import gui.pages.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Caleb Lyc
 */
public class Window extends JFrame {

    private static Window instance;
    private int currentPage = 1;

    private JPanel headerPanel;
    private JPanel sidebarPanel;
    private JPanel contentPanel;

    private Window() {
        this.setTitle("Bus");
        ImageIcon icon = new ImageIcon("assets/img/busImg.jpg");
        setIconImage(icon.getImage());
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        headerPanel = new Header();
        headerPanel.setBackground(Color.darkGray);

        sidebarPanel = new Sidebar(this);
        sidebarPanel.setBackground(Color.darkGray);

        contentPanel = new JPanel(new BorderLayout());

        this.getContentPane().add(headerPanel, BorderLayout.NORTH);
        this.getContentPane().add(sidebarPanel, BorderLayout.WEST);
        this.getContentPane().add(contentPanel, BorderLayout.CENTER);

        buildPage();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static synchronized Window getInstance() {
        if (instance == null) {
            instance = new Window();
        }

        System.out.println("ft");
        return instance;
    }

    private void buildPage() {
        contentPanel.removeAll();

        switch (currentPage) {
            case 1:
                contentPanel.add(new Home(), BorderLayout.CENTER);
                setTitle("Tableau de bord");
                break;
            case 2:
                contentPanel.add(new EmploiDuTemps(), BorderLayout.CENTER);
                setTitle("Emploi du temps");
                break;
            case 3:
                contentPanel.add(new Lignes(), BorderLayout.CENTER);
                setTitle("Lignes");
                break;
            case 4:
                contentPanel.add(new Bus(), BorderLayout.CENTER);
                setTitle("Bus");
                break;
            case 5:
                contentPanel.add(new Personnel(), BorderLayout.CENTER);
                setTitle("Personnel");
                break;
            case 6:
                contentPanel.add(new VoyagesEnCours(), BorderLayout.CENTER);
                setTitle("Voyages en cours");
                break;
            case 7:
                contentPanel.add(new Historique(), BorderLayout.CENTER);
                setTitle("Historique");
                break;
            default:
                contentPanel.add(new Home(), BorderLayout.CENTER);
                setTitle("Tableau de bord");
        }

        revalidate();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int page) {
        currentPage = page;
    }

    public void changePage(int page) {
        setCurrentPage(page);
        buildPage();
    }

    public void setTitleAndRefresh(String title) {
        this.setTitle(title);
        this.repaint();
        this.revalidate();
    }
}
