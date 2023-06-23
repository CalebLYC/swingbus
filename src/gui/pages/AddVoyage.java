/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import entities.Voyage;
import gui.Window;
import gui.components.AddForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import services.implementations.BusServiceImpl;
import services.implementations.LigneServiceImpl;
import services.implementations.PersonnelServiceImpl;
import services.implementations.VoyageServiceImpl;
import services.interfaces.VoyageService;

/**
 *
 * @author Caleb Lyc
 */
public class AddVoyage extends JFrame {
    private VoyageService service = new VoyageServiceImpl();
    private List<entities.Ligne> lignes;
    private List<entities.Bus> bus;
    private List<entities.Personnel> conducteurs;
    private JComboBox<String> ligneComboBox;
    private JComboBox<String> busComboBox;
    private JSpinner dateSpinner;
    private JComboBox conducField;
    private JComboBox versField;
    private Map<Integer, entities.Ligne> ligneMap = new HashMap<>();
    private Map<Integer, entities.Bus> busMap = new HashMap<>();
    private Map<Integer, entities.Personnel> conducMap = new HashMap<>();
    

    public AddVoyage() throws ParseException {
        setTitle("Ajouter un voyage");
        ImageIcon icon = new ImageIcon("assets/img/busImg.jpg");
        setIconImage(icon.getImage());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        AddForm addform = new AddForm();
        ligneComboBox = addform.addFormField("Ligne", new JComboBox<>());
        lignes = new LigneServiceImpl().lister();
        int index = 0;
        for (entities.Ligne ligne : lignes) {
            ligneComboBox.addItem(ligne.getNumero() + ": " + ligne.getNom());
            ligneMap.put(index, ligne);
            index++;
        }
        index = 0;
        busComboBox = addform.addFormField("Bus", new JComboBox());
        bus = new BusServiceImpl().lister();
        for (entities.Bus bu : bus) {
            busComboBox.addItem(bu.getImmatriculation() + ": " + bu.getMarque());
            busMap.put(index, bu);
            index++;
        }
        index = 0;
        conducField = addform.addFormField("Conducteur", new JComboBox());
        conducteurs = new PersonnelServiceImpl().lister();
        for (entities.Personnel con : conducteurs) {
            if(con.getPoste().getLibelle().equalsIgnoreCase("conducteur")){
                conducField.addItem(con.getNom() + " " +con.getPrenom());
                conducMap.put(index, con);
                index++;
            }
        }
        dateSpinner = addform.createDateTimePicker("Date et heure de départ");
        versField = addform.addFormField("Sens", new JComboBox());
        versField.addItem("Voyage vers le campus");
        versField.addItem("Voyage vers la périphérie");

        mainPanel.add(addform, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton ajouterButton = createButton("Ajouter", new Color(41, 128, 185));
        JButton annulerButton = createButton("Annuler", new Color(41, 128, 185));
        buttonPanel.add(ajouterButton);
        buttonPanel.add(annulerButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ligne = (String) ligneComboBox.getSelectedItem();
                String bus = (String) busComboBox.getSelectedItem();
                String conducteur = (String) conducField.getSelectedItem();
                String date = getFormattedDate();
                String vers = (String)versField.getSelectedItem();

                // Valider les champs
                if (ligne.isEmpty() || bus.isEmpty() || conducteur.isEmpty() || date.isEmpty()) {
                    showErrorDialog("Veuillez remplir tous les champs.");
                    return;
                }
                
                int ligneId = ligneComboBox.getSelectedIndex();
                entities.Ligne selectedLigne = ligneMap.get(ligneId);
                int busId = ligneComboBox.getSelectedIndex();
                entities.Bus selectedBus = busMap.get(busId);
                int conduId = ligneComboBox.getSelectedIndex();
                entities.Personnel selectedCond = conducMap.get(conduId);
                Date dateV = (Date)dateSpinner.getValue();
                boolean toPeri = false;
                if(vers.equalsIgnoreCase("Voyage vers la périphérie")){
                    toPeri = true;
                }
                System.out.println(ligneMap);
                System.out.println(selectedLigne.toString());
                Voyage voyage = new Voyage(dateV, toPeri, selectedLigne, selectedBus);
                voyage.getPersonnel().add(selectedCond);
                service.ajouter(voyage); 
                JOptionPane.showMessageDialog(AddVoyage.this, "Voyage ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                Window.getInstance().changePage(EmploiDuTemps.pageId);
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private String getFormattedDate() {
        Date selectedDate = (Date) dateSpinner.getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(selectedDate);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());

        return button;
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
