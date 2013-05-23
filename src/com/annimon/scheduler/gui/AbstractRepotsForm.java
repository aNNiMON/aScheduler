package com.annimon.scheduler.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 * Базовый макет формы для отчётов.
 * @author aNNiMON
 */
public abstract class AbstractRepotsForm extends JDialog {

    private JLabel infoLabel;
    
    public AbstractRepotsForm() {
        setModal(true);
        initComponents();
    }

    private void initComponents() {
        final JComboBox selectorComboBox = new JComboBox();
        infoLabel = new JLabel();
        infoLabel.setBorder(new EmptyBorder(5, 10, 2, 10));

        selectorComboBox.setModel(new DefaultComboBoxModel(setComboBoxValues()));
        selectorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int index = selectorComboBox.getSelectedIndex();
                selectionChanged(index);
                validate();
            }
        });
        
        getContentPane().add(selectorComboBox, BorderLayout.PAGE_START);
        getContentPane().add(infoLabel, BorderLayout.CENTER);
        
        selectionChanged(0);
        pack();
    }
    
    protected void setInfoText(String text) {
        infoLabel.setText(text);
    }

    protected abstract String[] setComboBoxValues();
    protected abstract void selectionChanged(int selectionIndex);
}
