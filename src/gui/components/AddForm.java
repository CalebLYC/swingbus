/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.components;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Caleb Lyc
 */
public class AddForm extends JPanel {
    private GridBagConstraints gbc;

    public AddForm() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 5, 10);
    }

    public JTextField addFormField(String label) {
        JTextField textField = new JTextField();
        JLabel fieldLabel = new JLabel(label + ":");
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridx = 0;
        add(fieldLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        add(textField, gbc);
        gbc.gridy++;
        return textField;
    }

    public JComboBox addFormField(String label, JComboBox comboBox) {
        JLabel fieldLabel = new JLabel(label + ":");
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridx = 0;
        add(fieldLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        add(comboBox, gbc);
        gbc.gridy++;
        return comboBox;
    }
    
    public JFormattedTextField createDatePicker() throws ParseException {
        MaskFormatter dateFormatter = new MaskFormatter("####-##-##");
        dateFormatter.setPlaceholderCharacter('_');

        JFormattedTextField dateField = new JFormattedTextField(dateFormatter);
        dateField.setColumns(10);

        return dateField;
    }
    
    public JSpinner createDatePicker(String label) throws ParseException {
    JLabel fieldLabel = new JLabel(label + ":");
    fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        SpinnerDateModel dateModel = new SpinnerDateModel();
    JSpinner spinner = new JSpinner(dateModel);

    JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
    DateFormatter dateFormatter = (DateFormatter) dateEditor.getTextField().getFormatter();
    dateFormatter.setAllowsInvalid(false);
    dateFormatter.setOverwriteMode(true);
    dateFormatter.setCommitsOnValidEdit(true);

    spinner.setEditor(dateEditor);

    gbc.gridx = 0;
    add(fieldLabel, gbc);

    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;

    add(spinner, gbc);
    gbc.gridy++;

    return spinner;
}
    
    public JSpinner createDateTimePicker(String label) throws ParseException {
    JLabel fieldLabel = new JLabel(label + ":");
    fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        SpinnerDateModel dateModel = new SpinnerDateModel();
    JSpinner spinner = new JSpinner(dateModel);

    JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd HH:mm");
    DateFormatter dateFormatter = (DateFormatter) dateEditor.getTextField().getFormatter();
    dateFormatter.setAllowsInvalid(false);
    dateFormatter.setOverwriteMode(true);
    dateFormatter.setCommitsOnValidEdit(true);

    spinner.setEditor(dateEditor);

    gbc.gridx = 0;
    add(fieldLabel, gbc);

    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;

    add(spinner, gbc);
    gbc.gridy++;

    return spinner;
}
    
    public JCheckBox addFormCheckBox(String label) {
    JCheckBox checkBox = new JCheckBox(label);
    JLabel fieldLabel = new JLabel(label + ":");
    fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));

    gbc.gridx = 0;
    add(fieldLabel, gbc);

    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;

    add(checkBox, gbc);
    gbc.gridy++;
    return checkBox;
}



    public void clearFields() {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setText("");
            } else if (component instanceof JComboBox) {
                JComboBox<?> comboBox = (JComboBox<?>) component;
                comboBox.setSelectedIndex(0);
            }
        }
    }

    public String getTextFieldValue(String label) {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel fieldLabel = (JLabel) component;
                if (fieldLabel.getText().equals(label + ":")) {
                    int index = getIndex(fieldLabel);
                    Component valueComponent = components[index + 1];
                    if (valueComponent instanceof JTextField) {
                        JTextField textField = (JTextField) valueComponent;
                        return textField.getText();
                    }
                }
            }
        }
        return "";
    }

    public String getComboBoxValue(String label) {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel fieldLabel = (JLabel) component;
                if (fieldLabel.getText().equals(label + ":")) {
                    int index = getIndex(fieldLabel);
                    Component valueComponent = components[index + 1];
                    if (valueComponent instanceof JComboBox) {
                        JComboBox<?> comboBox = (JComboBox<?>) valueComponent;
                        return (String) comboBox.getSelectedItem();
                    }
                }
            }
        }
        return "";
    }

    private int getIndex(Component component) {
        Component[] components = getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] == component) {
                return i;
            }
        }
        return -1;
    }
}
