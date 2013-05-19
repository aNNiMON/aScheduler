package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Specialities;
import com.annimon.scheduler.model.SpecialityModel;
import com.annimon.scheduler.util.GUIUtils;
import com.annimon.scheduler.util.JTextFieldLimit;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Форма редактирования специальностей.
 * @author aNNiMON
 */
public class SpecialitiesForm extends AbstractEntityForm {
    
    private JTextField codeTextField, nameTextField;

    public SpecialitiesForm() {
        super(new SpecialityModel(DAOKeeper.getSpecialityDAO()));
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        dataEditorPanel.add(GUIUtils.createLabel("Код"));
        codeTextField = new JTextField();
        codeTextField.setDocument(new JTextFieldLimit(32));
        dataEditorPanel.add(codeTextField);

        dataEditorPanel.add(GUIUtils.createLabel("Название"));
        nameTextField = new JTextField();
        nameTextField.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(nameTextField);
    }

    @Override
    protected void fillDataInEditorPanel(int rowSelected, JTable table) {
        codeTextField.setText(getValueAt(rowSelected, 1).toString());
        nameTextField.setText(getValueAt(rowSelected, 2).toString());
    }

    @Override
    protected Entity getEntity(int row, int id) {
        Specialities sp = new Specialities();
        sp.setId(id);
        sp.setCode(codeTextField.getText());
        sp.setName(nameTextField.getText());
        
        return sp;
    }
}
