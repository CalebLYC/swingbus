/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.pages;

import entities.Voyage;
import gui.Window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import services.implementations.VoyageServiceImpl;
import services.interfaces.VoyageService;

/**
 *
 * @author Caleb Lyc
 */
public class EmploiDuTemps extends Page {
    public static int pageId = 2;
    VoyageService service = new VoyageServiceImpl();
    private List<Voyage> voyages;

    private JTable table;

    public EmploiDuTemps() {
        setLayout(new BorderLayout());
        //setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Emploi du temps");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JButton ajouterVoyageButton = createButton("Ajouter un voyage", new Color(41, 128, 185));
        JButton modifierButton = createButton("Modifier", new Color(54, 176, 75));
        JButton supprimerButton = createButton("Supprimer", new Color(255, 0, 0));
        JButton genererPDFButton = createButtonWithIcon("Générer PDF\n de l'emploi du temps", new Color(41, 128, 185), "path/to/icon.png");
        JButton voirEmploiButton = createButton("Voir l'emploi du temps", new Color(123, 45, 67));

        buttonsPanel.add(ajouterVoyageButton);
        buttonsPanel.add(modifierButton);
        buttonsPanel.add(supprimerButton);
        buttonsPanel.add(genererPDFButton);
        buttonsPanel.add(voirEmploiButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        voyages = service.lister();
        String[] columnNames = {"Identifiant", "Ligne", "Bus", "Conducteur", "Départ"};
        Object[][] data = new Object[voyages.size()][5];
        for(int i=0; i<voyages.size(); i++){
            data[i][0] = voyages.get(i).getId();
            data[i][1] = "Ligne " + voyages.get(i).getLigne().getNumero() + ": " + voyages.get(i).getLigne().getNom();
            data[i][2] = voyages.get(i).getBus().getImmatriculation() + ": " + voyages.get(i).getBus().getMarque();
            data[i][3] = voyages.get(i).getPersonnel().get(0).getNom() + " " + voyages.get(i).getPersonnel().get(0).getPrenom();
            if(voyages.get(i).isVersPeripherie()){
                data[i][4] = voyages.get(i).getLigne().getDepart();
            }else{
                data[i][4] = voyages.get(i).getLigne().getDestination();
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(40);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        table.getTableHeader().setForeground(Color.BLACK);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        ajouterVoyageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddVoyage();
                } catch (ParseException ex) {
                    Logger.getLogger(EmploiDuTemps.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Integer id = (Integer)table.getValueAt(row, 0);
                    try {
                        new EditVoyage(service.trouver(id));
                    } catch (ParseException ex) {
                        Logger.getLogger(EmploiDuTemps.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun voyage sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Integer id = (Integer)table.getValueAt(row, 0);
                    JOptionPane.showMessageDialog(null, "Supprimer : " + id.toString());
                    service.supprimer(id);
                    JOptionPane.showMessageDialog(null, "Voyage supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    Window.getInstance().changePage(EmploiDuTemps.pageId);
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun voyage sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        genererPDFButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Créer une instance de PrinterJob
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("EmploiDuTemps");

            // Définir le contenu à imprimer
            Printable printable = new Printable() {
                @Override
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex > 0) {
                        return NO_SUCH_PAGE;
                    }

                    // Définir les marges
                    int marginLeft = 50;
                    int marginTop = 50;

                    // Définir la largeur et la hauteur disponibles pour l'impression
                    int availableWidth = (int) pageFormat.getImageableWidth() - marginLeft * 2;
                    int availableHeight = (int) pageFormat.getImageableHeight() - marginTop * 2;

                    // Récupérer les dimensions du tableau
                    int tableWidth = table.getColumnModel().getTotalColumnWidth();
                    int tableHeight = table.getHeight();

                    // Calculer l'échelle pour ajuster la taille du tableau à la page
                    double scaleX = (double) availableWidth / tableWidth;
                    double scaleY = (double) availableHeight / tableHeight;
                    double scale = Math.min(scaleX, scaleY);

                    // Appliquer l'échelle à la transformation graphique
                    Graphics2D g2 = (Graphics2D) graphics;
                    g2.translate(pageFormat.getImageableX() + marginLeft, pageFormat.getImageableY() + marginTop);
                    g2.scale(scale, scale);

                    // Imprimer le tableau
                    table.print(g2);

                    return PAGE_EXISTS;
                }
            };

            // Définir le contenu à imprimer sur le PrinterJob
            job.setPrintable(printable);

            // Afficher la boîte de dialogue d'impression
            if (job.printDialog()) {
                // Lancer l'impression
                job.print();
                JOptionPane.showMessageDialog(null, "Le PDF a été généré avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la génération du PDF.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
});


        voirEmploiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Emploi();
            }
        });
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

    private JButton createButtonWithIcon(String text, Color color, String iconPath) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setIcon(new ImageIcon(iconPath));
        button.setHorizontalTextPosition(SwingConstants.LEFT);

        return button;
    }
}
