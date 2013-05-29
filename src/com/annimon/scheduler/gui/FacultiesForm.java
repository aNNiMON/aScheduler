package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Faculties;
import com.annimon.scheduler.exceptions.EmptyFieldException;
import com.annimon.scheduler.model.FacultyModel;
import com.annimon.scheduler.util.GUIUtils;
import com.annimon.scheduler.util.JTextFieldLimit;
import com.mysql.jdbc.StringUtils;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Форма редактирования факультетов.
 * @author aNNiMON
 */
public class FacultiesForm extends AbstractEntityForm {
    
    private JTextField nameTextField, abbreviationTextField;

    public FacultiesForm() {
        super(new FacultyModel(DAOKeeper.getFacultyDAO()));
    }
    
    public FacultiesForm(String title) {
        super(new FacultyModel(DAOKeeper.getFacultyDAO()), title);
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        dataEditorPanel.add(GUIUtils.createLabel("Название"));
        nameTextField = new JTextField();
        nameTextField.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(nameTextField);

        dataEditorPanel.add(GUIUtils.createLabel("Сокращение"));
        abbreviationTextField = new JTextField();
        abbreviationTextField.setDocument(new JTextFieldLimit(10));
        dataEditorPanel.add(abbreviationTextField);
    }

    @Override
    protected void fillComponentsInEditorPanel(int rowSelected) {
        nameTextField.setText(getValueAt(rowSelected, 1).toString());
        abbreviationTextField.setText(getValueAt(rowSelected, 2).toString());
    }

    @Override
    protected Entity getEntity(int row, int id) {
        final String name = nameTextField.getText();
        if (StringUtils.isNullOrEmpty(name)) {
            GUIUtils.showErrorMessage(new EmptyFieldException("Название"));
            return null;
        }
        final String abbreviation = abbreviationTextField.getText();
        if (StringUtils.isNullOrEmpty(abbreviation)) {
            GUIUtils.showErrorMessage(new EmptyFieldException("Сокращение"));
            return null;
        }
        
        Faculties fc = new Faculties();
        fc.setId(id);
        fc.setName(name);
        fc.setAbbreviation(abbreviation);
        
        return fc;
    }
}
