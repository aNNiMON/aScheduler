package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Departments;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Faculties;
import com.annimon.scheduler.exceptions.EmptyFieldException;
import com.annimon.scheduler.model.DepartmentModel;
import com.annimon.scheduler.util.GUIUtils;
import com.annimon.scheduler.util.JTextFieldLimit;
import com.mysql.jdbc.StringUtils;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Форма редактирования кафедр.
 * @author aNNiMON
 */
public class DepartmentsForm extends AbstractEntityForm {
    
    private JTextField nameTextField;
    private JComboBox<String> facultyComboBox;
    
    private Faculties[] faculties;

    public DepartmentsForm() {
        super(new DepartmentModel(DAOKeeper.getDepartmentDAO()));
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        faculties = getFacultiesArray();
        
        dataEditorPanel.add(GUIUtils.createLabel("Название"));
        nameTextField = new JTextField();
        nameTextField.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(nameTextField);

        dataEditorPanel.add(GUIUtils.createLabel("Факультет"));
        facultyComboBox = new JComboBox<>();
        String[] facultyNames = new String[faculties.length];
        for (int i = 0; i < faculties.length; i++) {
            facultyNames[i] = faculties[i].getName();
        }
        facultyComboBox.setModel(new DefaultComboBoxModel(facultyNames));
        dataEditorPanel.add(facultyComboBox);
    }

    @Override
    protected void fillComponentsInEditorPanel(int rowSelected) {
        nameTextField.setText(getValueAt(rowSelected, 1).toString());
        String abbreviation = getValueAt(rowSelected, 2).toString();
        int index = 0;
        for (int i = 0; i < faculties.length; i++) {
            if (abbreviation.endsWith(faculties[i].getAbbreviation())) {
                index = i;
            }
        }
        facultyComboBox.setSelectedIndex(index);
    }

    @Override
    protected Entity getEntity(int row, int id) {
        final String name = nameTextField.getText();
        if (StringUtils.isNullOrEmpty(name)) {
            GUIUtils.showErrorMessage(new EmptyFieldException("Название"));
            return null;
        }
        
        int index = facultyComboBox.getSelectedIndex();
        if ( (index < 0) || (index >= faculties.length) ) {
            GUIUtils.showErrorMessage(new EmptyFieldException("Факультет"));
            return null;
        }
        
        Departments dp = new Departments();
        dp.setId(id);
        dp.setName(name);
        dp.setFacultyId(faculties[index].getId());
        
        return dp;
    }
    
    private Faculties[] getFacultiesArray() {
        List<Entity> facultiesList = DAOKeeper.getFacultyDAO().select();
        Faculties[] array = new Faculties[facultiesList.size()];
        for (int i = 0; i < facultiesList.size(); i++) {
            Entity entity = facultiesList.get(i);
            array[i] = (Faculties) entity;
        }
        return array;
    }
}
