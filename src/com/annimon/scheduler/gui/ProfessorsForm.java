package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.ProfessorDAO;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Professors;
import com.annimon.scheduler.model.ProfessorModel;
import com.annimon.scheduler.util.GUIUtils;
import com.annimon.scheduler.util.JTextFieldLimit;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Форма редактирования преподавателей.
 * @author aNNiMON
 */
public class ProfessorsForm extends AbstractEntityForm {
    
    private JTextField lastnameTextField, firstnameTextField, middlenameTextField;

    public ProfessorsForm() {
        super(new ProfessorModel(new ProfessorDAO()));
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        dataEditorPanel.add(GUIUtils.createLabel("Фамилия"));
        lastnameTextField = new JTextField();
        lastnameTextField.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(lastnameTextField);

        dataEditorPanel.add(GUIUtils.createLabel("Имя"));
        firstnameTextField = new JTextField();
        firstnameTextField.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(firstnameTextField);

        dataEditorPanel.add(GUIUtils.createLabel("Отчество"));
        middlenameTextField = new JTextField();
        middlenameTextField.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(middlenameTextField);
    }

    @Override
    protected void fillDataInEditorPanel(int rowSelected, JTable table) {
        lastnameTextField.setText(getValueAt(rowSelected, 1).toString());
        firstnameTextField.setText(getValueAt(rowSelected, 2).toString());
        middlenameTextField.setText(getValueAt(rowSelected, 3).toString());
    }

    @Override
    protected Entity getEntity(int row, int id) {
        Professors pr = new Professors();
        pr.setId(id);
        pr.setLastname(lastnameTextField.getText());
        pr.setFirstname(firstnameTextField.getText());
        pr.setMiddlename(middlenameTextField.getText());
        
        return pr;
    }
}
