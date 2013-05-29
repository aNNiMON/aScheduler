package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Subjects;
import com.annimon.scheduler.exceptions.EmptyFieldException;
import com.annimon.scheduler.model.SubjectModel;
import com.annimon.scheduler.util.GUIUtils;
import com.annimon.scheduler.util.JTextFieldLimit;
import com.mysql.jdbc.StringUtils;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Форма редактирования предметов.
 * @author aNNiMON
 */
public class SubjectsForm extends AbstractEntityForm {
    
    private JTextField nameTextField, abbreviationTextField;

    public SubjectsForm() {
        super(new SubjectModel(DAOKeeper.getSubjectDAO()));
    }
    
    public SubjectsForm(String title) {
        super(new SubjectModel(DAOKeeper.getSubjectDAO()), title);
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        dataEditorPanel.add(GUIUtils.createLabel("Название"));
        nameTextField = new JTextField();
        nameTextField.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(nameTextField);

        dataEditorPanel.add(GUIUtils.createLabel("Сокращение"));
        abbreviationTextField = new JTextField();
        abbreviationTextField.setDocument(new JTextFieldLimit(12));
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
        
        Subjects sb = new Subjects();
        sb.setId(id);
        sb.setName(name);
        sb.setAbbreviation(abbreviation);
        
        return sb;
    }
}
