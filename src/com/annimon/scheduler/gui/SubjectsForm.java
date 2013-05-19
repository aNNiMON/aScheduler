package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.SubjectDAO;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Subjects;
import com.annimon.scheduler.model.SubjectModel;
import com.annimon.scheduler.util.ExceptionHandler;
import com.annimon.scheduler.util.GUIUtils;
import com.annimon.scheduler.util.JTextFieldLimit;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Форма редактирования предметов.
 * @author aNNiMON
 */
public class SubjectsForm extends AbstractEntityForm {
    
    private JTextField nameTextFild, abbreviationTextField;

    public SubjectsForm() {
        super(new SubjectModel(new SubjectDAO()));
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        dataEditorPanel.add(GUIUtils.createLabel("Название"));
        nameTextFild = new JTextField();
        nameTextFild.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(nameTextFild);

        dataEditorPanel.add(GUIUtils.createLabel("Сокращение"));
        abbreviationTextField = new JTextField();
        abbreviationTextField.setDocument(new JTextFieldLimit(12));
        dataEditorPanel.add(abbreviationTextField);
    }

    @Override
    protected void fillDataInEditorPanel(int rowSelected, JTable table) {
        nameTextFild.setText(getValueAt(rowSelected, 1).toString());
        abbreviationTextField.setText(getValueAt(rowSelected, 2).toString());
    }

    @Override
    protected Entity getEntity(int row, int id) {
        Subjects sb = new Subjects();
        sb.setId(id);
        sb.setName(nameTextFild.getText());
        sb.setAbbreviation(abbreviationTextField.getText());
        
        return sb;
    }
}
