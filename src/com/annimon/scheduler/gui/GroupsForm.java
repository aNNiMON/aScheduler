package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Departments;
import com.annimon.scheduler.data.EducationForms;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Groups;
import com.annimon.scheduler.data.Specialities;
import com.annimon.scheduler.exceptions.EmptyFieldException;
import com.annimon.scheduler.exceptions.ValueOutOfRangeException;
import com.annimon.scheduler.model.GroupModel;
import com.annimon.scheduler.util.GUIUtils;
import com.annimon.scheduler.util.JTextFieldLimit;
import com.mysql.jdbc.StringUtils;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Форма редактирования групп.
 * @author aNNiMON
 */
public class GroupsForm extends AbstractEntityForm {
    
    private JTextField nameTextField;
    private JSpinner yearSpinner, strengthSpinner;
    private JComboBox<String> specialityComboBox, departmentComboBox, educationFormComboBox;
    
    private Specialities[] specialities;
    private Departments[] departments;
    private EducationForms[] educationForms;

    public GroupsForm() {
        super(new GroupModel(DAOKeeper.getGroupDAO()));
    }
    
    public GroupsForm(String title) {
        super(new GroupModel(DAOKeeper.getGroupDAO()), title);
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        specialities = getSpecialitiesArray();
        departments = getDepartmentsArray();
        educationForms = getEducationFormsArray();
        
        dataEditorPanel.add(GUIUtils.createLabel("Название"));
        nameTextField = new JTextField();
        nameTextField.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(nameTextField);

        dataEditorPanel.add(GUIUtils.createLabel("Год создания"));
        yearSpinner = new JSpinner();
        yearSpinner.setModel(new SpinnerNumberModel(Short.valueOf((short)2013),
                Short.valueOf((short)2000), Short.valueOf((short)2100),
                Short.valueOf((short)1)));
        dataEditorPanel.add(yearSpinner);
        
        dataEditorPanel.add(GUIUtils.createLabel("Численность"));
        strengthSpinner = new JSpinner();
        strengthSpinner.setModel(GUIUtils.createShortSpinnerModel((short)1));
        dataEditorPanel.add(strengthSpinner);
        
        dataEditorPanel.add(GUIUtils.createLabel("Специальность"));
        specialityComboBox = new JComboBox<>();
        String[] specialityNames = new String[specialities.length];
        for (int i = 0; i < specialities.length; i++) {
            specialityNames[i] = specialities[i].getName();
        }
        specialityComboBox.setModel(new DefaultComboBoxModel(specialityNames));
        dataEditorPanel.add(specialityComboBox);
        
        dataEditorPanel.add(GUIUtils.createLabel("Кафедра"));
        departmentComboBox = new JComboBox<>();
        String[] departmentNames = new String[departments.length];
        for (int i = 0; i < departments.length; i++) {
            departmentNames[i] = departments[i].getName();
        }
        departmentComboBox.setModel(new DefaultComboBoxModel(departmentNames));
        dataEditorPanel.add(departmentComboBox);
        
        dataEditorPanel.add(GUIUtils.createLabel("Форма обучения"));
        educationFormComboBox = new JComboBox<>();
        String[] formNames = new String[educationForms.length];
        for (int i = 0; i < educationForms.length; i++) {
            formNames[i] = educationForms[i].getType();
        }
        educationFormComboBox.setModel(new DefaultComboBoxModel(formNames));
        dataEditorPanel.add(educationFormComboBox);
    }

    @Override
    protected void fillComponentsInEditorPanel(int rowSelected) {
        nameTextField.setText(getValueAt(rowSelected, 1).toString());
        yearSpinner.setValue(Short.valueOf(getValueAt(rowSelected, 2).toString()));
        strengthSpinner.setValue(getValueAt(rowSelected, 3));
        
        String speciality = getValueAt(rowSelected, 4).toString();
        int index = 0;
        for (int i = 0; i < specialities.length; i++) {
            if (speciality.endsWith(specialities[i].getName())) {
                index = i;
            }
        }
        specialityComboBox.setSelectedIndex(index);
        
        String department = getValueAt(rowSelected, 5).toString();
        index = 0;
        for (int i = 0; i < departments.length; i++) {
            if (department.endsWith(departments[i].getName())) {
                index = i;
            }
        }
        departmentComboBox.setSelectedIndex(index);
        
        String form = getValueAt(rowSelected, 3).toString();
        index = 0;
        for (int i = 0; i < educationForms.length; i++) {
            if (form.endsWith(educationForms[i].getType())) {
                index = i;
            }
        }
        educationFormComboBox.setSelectedIndex(index);
    }

    @Override
    protected Entity getEntity(int row, int id) {
        final String name = nameTextField.getText();
        if (StringUtils.isNullOrEmpty(name)) {
            GUIUtils.showErrorMessage(new EmptyFieldException("Название"));
            return null;
        }
        
        short year = (short) yearSpinner.getValue();
        short strength = (short) strengthSpinner.getValue();
        if (strength > 125) {
            GUIUtils.showErrorMessage(new ValueOutOfRangeException(strength, "> 125"));
            return null;
        }
        
        int specialityIndex = specialityComboBox.getSelectedIndex();
        if ( (specialityIndex < 0) || (specialityIndex >= specialities.length) ) {
            GUIUtils.showErrorMessage(new EmptyFieldException("Специальность"));
            return null;
        }
        int departmentIndex = departmentComboBox.getSelectedIndex();
        if ( (departmentIndex < 0) || (departmentIndex >= departments.length) ) {
            GUIUtils.showErrorMessage(new EmptyFieldException("Кафедра"));
            return null;
        }
        int educationFormIndex = educationFormComboBox.getSelectedIndex();
        if ( (educationFormIndex < 0) || (educationFormIndex >= educationForms.length) ) {
            GUIUtils.showErrorMessage(new EmptyFieldException("Форма обучения"));
            return null;
        }
        
        Groups gr = new Groups();
        gr.setId(id);
        gr.setName(name);
        gr.setFormationYear(year);
        gr.setStrength(strength);
        gr.setSpecialityId(specialities[specialityIndex].getId());
        gr.setDepartmentId(departments[departmentIndex].getId());
        gr.setEducationFormId(educationForms[educationFormIndex].get_Id());
        
        return gr;
    }
    
    private Specialities[] getSpecialitiesArray() {
        List<Entity> specialitiesList = DAOKeeper.getSpecialityDAO().select();
        Specialities[] array = new Specialities[specialitiesList.size()];
        for (int i = 0; i < specialitiesList.size(); i++) {
            Entity entity = specialitiesList.get(i);
            array[i] = (Specialities) entity;
        }
        return array;
    }
    
    private Departments[] getDepartmentsArray() {
        List<Entity> departmentsList = DAOKeeper.getDepartmentDAO().select();
        Departments[] array = new Departments[departmentsList.size()];
        for (int i = 0; i < departmentsList.size(); i++) {
            Entity entity = departmentsList.get(i);
            array[i] = (Departments) entity;
        }
        return array;
    }
    
    private EducationForms[] getEducationFormsArray() {
        List<Entity> educationFormsList = DAOKeeper.getEducationFormDAO().select();
        EducationForms[] array = new EducationForms[educationFormsList.size()];
        for (int i = 0; i < educationFormsList.size(); i++) {
            Entity entity = educationFormsList.get(i);
            array[i] = (EducationForms) entity;
        }
        return array;
    }
}
