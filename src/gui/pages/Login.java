/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import entities.Administrateur;
import gui.Window;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import services.implementations.AdminServiceImpl;
import services.interfaces.AdminService;

/**
 *
 * @author Caleb Lyc
 */
public class Login extends JFrame {

    private JPanel contentPanel;
    private JButton addButton;
    private JButton annulerButton;
    JTextField usernameField;
    JTextField passwordField;
    AdminService service = new AdminServiceImpl();

    public Login() {
        setTitle("Se connecter");
        ImageIcon icon = new ImageIcon("assets/img/busImg.jpg");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        addFields();

        addButton = new JButton("Se connecter");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        annulerButton = new JButton("S'inscrire");
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
            try {
                new Register();
                dispose();
            } catch (ParseException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        setVisible(true);
    }

    private void addFields() {
        usernameField = addFormField("Nom d'utilisateur");
        passwordField = addFormField("Mot de passe");
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
            String username = usernameField.getText();
            String password = passwordField.getText();
            Administrateur user = service.login(username, password);
            if (user != null) {
                Window.getInstance();
                StringBuilder sb = new StringBuilder();
                sb.append("Authentification réussie\n");
                sb.append("Bienvenue Mr ");
                sb.append(user.getPrenom()).append(" ").append(user.getNom());
                sb.append("\nConnecté sous le pseudonyme: ");
                sb.append(username);
                JOptionPane.showMessageDialog(this, sb.toString(), "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
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
