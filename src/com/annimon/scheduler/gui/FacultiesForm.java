package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Faculties;
import com.annimon.scheduler.model.FacultyModel;
import com.annimon.scheduler.util.GUIUtils;
import com.annimon.scheduler.util.JTextFieldLimit;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Форма редактирования факультетов.
 * @author aNNiMON
 */
public class FacultiesForm extends AbstractEntityForm {
    
    private JTextField nameTextFild, abbreviationTextField;

    public FacultiesForm() {
        super(new FacultyModel(DAOKeeper.getFacultyDAO()));
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        dataEditorPanel.add(GUIUtils.createLabel("Название"));
        nameTextFild = new JTextField();
        nameTextFild.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(nameTextFild);

        dataEditorPanel.add(GUIUtils.createLabel("Сокращение"));
        abbreviationTextField = new JTextField();
        abbreviationTextField.setDocument(new JTextFieldLimit(10));
        dataEditorPanel.add(abbreviationTextField);
    }

    @Override
    protected void fillDataInEditorPanel(int rowSelected, JTable table) {
        nameTextFild.setText(getValueAt(rowSelected, 1).toString());
        abbreviationTextField.setText(getValueAt(rowSelected, 2).toString());
    }

    @Override
    protected Entity getEntity(int row, int id) {
        Faculties fc = new Faculties();
        fc.setId(id);
        fc.setName(nameTextFild.getText());
        fc.setAbbreviation(abbreviationTextField.getText());
        
        return fc;
    }
}
