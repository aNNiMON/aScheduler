package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Professors;
import com.annimon.scheduler.exceptions.EmptyFieldException;
import com.annimon.scheduler.model.ProfessorModel;
import com.annimon.scheduler.util.GUIUtils;
import com.annimon.scheduler.util.JTextFieldLimit;
import com.mysql.jdbc.StringUtils;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Форма редактирования преподавателей.
 * @author aNNiMON
 */
public class ProfessorsForm extends AbstractEntityForm {
    
    private JTextField lastnameTextField, firstnameTextField, middlenameTextField;

    public ProfessorsForm() {
        super(new ProfessorModel(DAOKeeper.getProfessorDAO()));
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
    protected void fillComponentsInEditorPanel(int rowSelected) {
        lastnameTextField.setText(getValueAt(rowSelected, 1).toString());
        firstnameTextField.setText(getValueAt(rowSelected, 2).toString());
        middlenameTextField.setText(getValueAt(rowSelected, 3).toString());
    }

    @Override
    protected Entity getEntity(int row, int id) {
        final String lastname = lastnameTextField.getText();
        if (StringUtils.isNullOrEmpty(lastname)) {
            GUIUtils.showErrorMessage(new EmptyFieldException("Фамилия"));
            return null;
        }
        final String firstname = firstnameTextField.getText();
        if (StringUtils.isNullOrEmpty(firstname)) {
            GUIUtils.showErrorMessage(new EmptyFieldException("Имя"));
            return null;
        }
        
        Professors pr = new Professors();
        pr.setId(id);
        pr.setLastname(lastname);
        pr.setFirstname(firstname);
        pr.setMiddlename(middlenameTextField.getText());
        
        return pr;
    }
}
