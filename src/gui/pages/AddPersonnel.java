/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import entities.Poste;
import gui.Window;
import gui.components.AddForm;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import services.implementations.PersonnelServiceImpl;
import services.implementations.PosteServiceImpl;
import services.interfaces.PersonnelService;
import services.interfaces.PosteService;

/**
 *
 * @author Caleb Lyc
 */
public class AddPersonnel extends JFrame {
    private PersonnelService service = new PersonnelServiceImpl();
    private PosteService posteService = new PosteServiceImpl();
    private List<Poste> postes;
    private JTextField nomField;
    private JTextField prenomField;
    private JSpinner dateField;
    
    public AddPersonnel() throws ParseException {
        this.setTitle("Ajouter un Personnel");
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        postes = posteService.lister();

        // Création du JComboBox pour choisir le poste correspondant
        JComboBox<String> posteComboBox = new JComboBox<>();
        for(Poste poste: postes){
            posteComboBox.addItem(poste.getLibelle());
        }

        // Création du panneau pour contenir le formulaire et les JComboBox
        AddForm addForm = new AddForm();
        nomField = addForm.addFormField("Nom");
        prenomField = addForm.addFormField("Prénom");
        dateField = addForm.createDatePicker("Date de naissance");
        addForm.addFormField("Poste", posteComboBox);

        JButton addButton = new JButton("Ajouter");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));

        JButton cancelButton = new JButton("Annuler");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        this.add(addForm, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String poste = (String) posteComboBox.getSelectedItem();
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                Date dateNaissance = (Date)dateField.getValue();
                System.out.println(dateNaissance);
                
                if (validateInputs(poste, nom, prenom, dateNaissance)) {
                    Poste posteV = posteService.trouver(poste);
                    entities.Personnel personnel = new entities.Personnel(nom, prenom, dateNaissance, posteV);
                    try{
                        service.ajouter(personnel);
                        String message = "Nouveau menbre du personnel: "
                            + nom + " "
                            + prenom + " "
                            + "ajouté avec succès";
                        JOptionPane.showMessageDialog(AddPersonnel.this, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        Window.getInstance().changePage(Personnel.pageId);
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(AddPersonnel.this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private boolean validateInputs(String poste, String nom, String prenom, Date dateNaissance) {
    if (poste.isEmpty() || nom.isEmpty() || prenom.isEmpty() || dateNaissance == null) {
        JOptionPane.showMessageDialog(AddPersonnel.this, "Veuillez remplir correctement tous les champs.", "Erreur de validation", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    return true;
}
}

