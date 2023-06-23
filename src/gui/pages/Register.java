/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import entities.Administrateur;
import gui.Window;
import gui.components.AddForm;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import services.implementations.AdminServiceImpl;
import services.interfaces.AdminService;
/**
 *
 * @author Caleb Lyc
 */
public class Register extends JFrame {

    private AdminService service = new AdminServiceImpl();
    private JTextField nomField;
    private JTextField prenomField;
    private JSpinner dateField;
    private JTextField usernameField;
    private JTextField passwordField;

    public Register() throws ParseException {
        this.setTitle("S'inscrire");
        ImageIcon icon = new ImageIcon("assets/img/busImg.jpg");
        setIconImage(icon.getImage());
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Création du panneau pour contenir le formulaire et les JComboBox
        AddForm addForm = new AddForm();
        nomField = addForm.addFormField("Nom");
        prenomField = addForm.addFormField("Prénom");
        dateField = addForm.createDatePicker("Date de naissance");
        usernameField = addForm.addFormField("Nom d'utilisateur");
        passwordField = addForm.addFormField("Mot de passe");

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
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                Date dateNaissance = (Date) dateField.getValue();
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (validateInputs(nom, prenom, dateNaissance, username, password)) {
                    try {
                        Administrateur admin = new Administrateur(nom, prenom, dateNaissance, username, password);
                        service.ajouter(admin);
                        String message = "Inscription effectuée avec succès\n Nom: "
                                + nom + " \nPrénom: "
                                + prenom + " ";
                        JOptionPane.showMessageDialog(Register.this, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        Window.getInstance().changePage(Personnel.pageId);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Register.this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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

    private boolean validateInputs(String nom, String prenom, Date date, String username, String password) {
        if (nom.isEmpty() || prenom.isEmpty() || username.isEmpty() || password.isEmpty() || date == null) {
            JOptionPane.showMessageDialog(Register.this, "Veuillez remplir correctement tous les champs.", "Erreur de validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
