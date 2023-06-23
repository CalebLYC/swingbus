/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import gui.Window;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import services.implementations.LigneServiceImpl;
import services.interfaces.LigneService;

/**
 *
 * @author Caleb Lyc
 */
public class EditLigne extends JFrame {

    private JPanel contentPanel;
    private JButton addButton;
    private JButton annulerButton;
    JTextField numeroField;
    JTextField distField;
    JTextField nomField;
    JTextField deparField;
    JTextField destField;
    LigneService service = new LigneServiceImpl();
    private entities.Ligne ligneToEdit;

    public EditLigne(entities.Ligne ligne) {
        ligneToEdit = ligne;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        addFields();

        addButton = new JButton("Modifier");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        annulerButton = new JButton("Annuler");
        annulerButton.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(annulerButton);

        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            if (validateFields()) {
                displayDialog();
                clearFields();
            }
        });
        annulerButton.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
    }

    private void addFields() {
        numeroField = addFormField("Numéro de la ligne");
        numeroField.setText("" + ligneToEdit.getNumero());
        nomField = addFormField("Nom");
        nomField.setText(ligneToEdit.getNom());
        deparField = addFormField("Départ");
        deparField.setText(ligneToEdit.getDepart());
        destField = addFormField("Destination");
        destField.setText(ligneToEdit.getDestination());
        distField = addFormField("Distance");
        distField.setText("" + ligneToEdit.getDistance());
    }

    private JTextField addFormField(String label) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = contentPanel.getComponentCount();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 5, 10);

        JLabel fieldLabel = new JLabel(label + ":");
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        contentPanel.add(fieldLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0);

        contentPanel.add(textField, gbc);
        return textField;
    }

    private boolean validateFields() {
        Component[] components = contentPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                if (textField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }

    private void displayDialog() {
        try {
            Integer numero = Integer.valueOf(numeroField.getText());
            String nom = nomField.getText();
            String depart = deparField.getText();
            String destination = destField.getText();
            float distance = Float.parseFloat(distField.getText());
            entities.Ligne ligne = new entities.Ligne(numero, nom, distance, depart, destination);
            service.modifier(ligne);
            StringBuilder sb = new StringBuilder();
            sb.append("Ligne");
            sb.append(numero);
            sb.append(": ");
            sb.append(nom);
            sb.append(" modifiée avec succès");
            JOptionPane.showMessageDialog(this, sb.toString(), "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            Window.getInstance().changePage(Lignes.pageId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        Component[] components = contentPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setText("");
            }
        }
    }
}
