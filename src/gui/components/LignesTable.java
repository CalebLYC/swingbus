/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import gui.Window;
import gui.pages.EditLigne;
import gui.pages.Ligne;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import gui.pages.Lignes;
import java.util.List;
import services.implementations.LigneServiceImpl;
import services.interfaces.LigneService;

/**
 *
 * @author Caleb Lyc
 */
public class LignesTable extends JPanel {
    private JPanel mainPanel;
    private LigneService service = new LigneServiceImpl();
    private List<entities.Ligne> lignes;

    public LignesTable() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lignes = service.lister();

        addTitleRow();
        for(entities.Ligne ligne : lignes){
            addLine(ligne);
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        setLayout(new GridLayout(1, 1));
        add(scrollPane);
    }

    private void addTitleRow() {
        JPanel titlePanel = new JPanel(new GridLayout(1, 5, 1, 0));
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel numeroTitleLabel = createTitleLabel("Numéro");
        JLabel nomTitleLabel = createTitleLabel("Nom");
        JLabel destTitleLabel = createTitleLabel("Destination");
        JLabel distTitleLabel = createTitleLabel("Distance");
        JLabel actionsTitleLabel = createTitleLabel("Actions");

        titlePanel.add(numeroTitleLabel);
        titlePanel.add(nomTitleLabel);
        titlePanel.add(destTitleLabel);
        titlePanel.add(distTitleLabel);
        titlePanel.add(actionsTitleLabel);

        mainPanel.add(titlePanel);
    }

    private void addLine(entities.Ligne ligne) {
        JPanel linePanel = new JPanel(new GridLayout(1, 3, 10, 0));
        linePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel numeroLabel = createLabel("Ligne " + Integer.toString(ligne.getNumero()));
        JLabel nomLabel = createLabel(ligne.getNom());
        JLabel destLabel = createLabel(ligne.getDestination());
        JLabel distLabel = createLabel(""+ligne.getDistance());

        JButton modifierButton = createButton("Modifier");
        modifierButton.setBackground(new Color(46, 204, 113));
        modifierButton.setForeground(Color.WHITE);
        modifierButton.setFocusPainted(false);
        
        JButton supprimerButton = createButton("Supprimer");
        supprimerButton.setBackground(Color.red);
        supprimerButton.setForeground(Color.WHITE);
        supprimerButton.setFocusPainted(false);
        
        JButton voirButton = createButton("Voir");
        voirButton.setBackground( new Color(41, 128, 185));
        voirButton.setForeground(Color.WHITE);
        voirButton.setFocusPainted(false);

        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditLigne(ligne);
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(LignesTable.this, "Voulez-vous vraiment supprimer cette ligne ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(LignesTable.this, "Ligne " + ligne.getNumero() + " supprimée avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    service.supprimer(ligne.getNumero());
                   Window.getInstance().changePage(Lignes.pageId);
                }
            }
        });
        
        voirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.getInstance().changePage(Ligne.pageId ,ligne.getNumero());
                Window.getInstance().setTitle("Ligne" + ligne.getNumero());
            }
        });

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.LINE_AXIS));
        actionsPanel.add(modifierButton);
        actionsPanel.add(Box.createHorizontalStrut(10));
        actionsPanel.add(supprimerButton);
        actionsPanel.add(Box.createHorizontalStrut(10));
        actionsPanel.add(voirButton);

        linePanel.add(numeroLabel);
        linePanel.add(nomLabel);
        linePanel.add(destLabel);
        linePanel.add(distLabel);
        linePanel.add(actionsPanel);

        mainPanel.add(linePanel);
    }

    private JLabel createTitleLabel(String text) {
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        return titleLabel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setMargin(new Insets(2, 3, 2, 3));
        return button;
    }
}
