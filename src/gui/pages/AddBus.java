/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import gui.Window;
import gui.components.AddForm;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.DateTimeException;
import java.time.Year;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import services.implementations.BusServiceImpl;
import services.interfaces.BusService;

/**
 *
 * @author Caleb Lyc
 */
public class AddBus extends JFrame {

    private JTextField immaField;
    private JTextField maxField;
    private JTextField marqueField;
    private JTextField modelField;
    private JTextField yearField;
    private BusService service = new BusServiceImpl();

    public AddBus() {
        setTitle("Ajouter un Bus");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        AddForm addForm = new AddForm();
        immaField = addForm.addFormField("Numéro de Bus");
        maxField = addForm.addFormField("Nombre de places");
        marqueField = addForm.addFormField("Marque");
        modelField = addForm.addFormField("Modèle");
        yearField = addForm.addFormField("Année");

        JButton addButton = new JButton("Ajouter");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton annulerButton = new JButton("Annuler");
        annulerButton.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(annulerButton);

        add(addForm, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            if (validateFields()) {
                displayDialog();
                addForm.clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir correctement tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        annulerButton.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
    }

    private boolean validateFields() {
        String numeroBus = immaField.getText();
        String max = maxField.getText();
        String marque = marqueField.getText();
        String modele = modelField.getText();
        String annee = yearField.getText();

        // Vérifier si tous les champs sont remplis
        if (numeroBus.isEmpty() || max.isEmpty() || marque.isEmpty() || modele.isEmpty() || annee.isEmpty()) {
            return false;
        }

        // Vérifier si l'année est valide
        try {
            int yearValue = Integer.parseInt(annee);
            Year.of(yearValue); // Vérifie si l'année est valide
        } catch (NumberFormatException | DateTimeException e) {
            return false; // L'année n'est pas valide
        }

        return true;
    }

    private void displayDialog() {
        String numeroBus = immaField.getText();
        Integer max = Integer.valueOf(maxField.getText());
        String marque = marqueField.getText();
        String modele = modelField.getText();
        Integer annee = Integer.valueOf(yearField.getText());

        try {
            entities.Bus bus = new entities.Bus(numeroBus, max, marque, modele, annee);
            service.ajouter(bus);
            StringBuilder sb = new StringBuilder();
            sb.append("Nouveau bus ajouté : Bus de numéro");
            sb.append(numeroBus);
            JOptionPane.showMessageDialog(this, sb.toString(), "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            Window.getInstance().changePage(Bus.pageId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }
}
