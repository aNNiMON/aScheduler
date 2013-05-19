package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Departments;
import com.annimon.scheduler.data.EducationForms;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Groups;
import com.annimon.scheduler.data.Specialities;
import com.annimon.scheduler.model.GroupModel;
import com.annimon.scheduler.util.GUIUtils;
import com.annimon.scheduler.util.JTextFieldLimit;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Форма редактирования групп.
 * @author aNNiMON
 */
public class GroupsForm extends AbstractEntityForm {
    
    private JTextField nameTextFild;
    private JSpinner yearSpinner, strengthSpinner;
    private JComboBox<String> specialityComboBox, departmentComboBox, educationFormComboBox;
    
    private Specialities[] specialities;
    private Departments[] departments;
    private EducationForms[] educationForms;

    public GroupsForm() {
        super(new GroupModel(DAOKeeper.getGroupDAO()));
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        specialities = getSpecialitiesArray();
        departments = getDepartmentsArray();
        educationForms = getEducationFormsArray();
        
        dataEditorPanel.add(GUIUtils.createLabel("Название"));
        nameTextFild = new JTextField();
        nameTextFild.setDocument(new JTextFieldLimit(255));
        dataEditorPanel.add(nameTextFild);

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
    protected void fillDataInEditorPanel(int rowSelected, JTable table) {
        nameTextFild.setText(getValueAt(rowSelected, 1).toString());
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
        short year = (short) yearSpinner.getValue();
        short strength = (short) strengthSpinner.getValue();
        int specialityIndex = specialityComboBox.getSelectedIndex();
        int departmentIndex = departmentComboBox.getSelectedIndex();
        int educationFormIndex = educationFormComboBox.getSelectedIndex();
        
        Groups gr = new Groups();
        gr.setId(id);
        gr.setName(nameTextFild.getText());
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
