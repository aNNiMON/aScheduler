package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Departments;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Faculties;
import com.annimon.scheduler.util.DBConnection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 * Панель представления расписания с возможностью выбора факультета.
 * @author aNNiMON
 */
public class SchedulerPanel extends JPanel {
    
    private static final Dimension VIEWPORT_DIMENSION = new Dimension(800, 500);
    private static SchedulerPanel instance;
    
    private JComboBox facultyComboBox;
    private Faculties[] faculties;

    public SchedulerPanel() {
        instance = SchedulerPanel.this;
        
        JPanel facultyChoosePanel = new JPanel();
        JLabel facultyLabel = new JLabel();
        facultyComboBox = new JComboBox();

        setLayout(new BorderLayout());

        facultyChoosePanel.setLayout(new BoxLayout(facultyChoosePanel, BoxLayout.LINE_AXIS));

        facultyLabel.setText("Факультет   ");
        facultyChoosePanel.add(facultyLabel);

        faculties = getFacultiesArray();
        String[] facultyNames = getFacultyNames(faculties);
        facultyComboBox.setModel(new DefaultComboBoxModel(facultyNames));
        facultyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int index = facultyComboBox.getSelectedIndex();
                remove(getComponent(getComponentCount() - 1));
                createScheduleTableDepartment(faculties[index]);
                validate();
            }
        });
        facultyChoosePanel.add(facultyComboBox);

        add(facultyChoosePanel, BorderLayout.NORTH);

        if (faculties.length > 0) {
            createScheduleTableDepartment(faculties[0]);
        }
    }
    
    public static SchedulerPanel getInstance() {
        return instance;
    }
    
    /**
     * Принудительно обновить данные расписания.
     */
    public void refreshScheduler() {
        faculties = getFacultiesArray();
        String[] facultyNames = getFacultyNames(faculties);
        facultyComboBox.setModel(new DefaultComboBoxModel(facultyNames));
        
        remove(getComponent(getComponentCount() - 1));
        createScheduleTableDepartment(faculties[0]);
        validate();
    }
    
    /*
     * Получить список кафедр указанного факультета и заполнить панель расписания.
     */
    private void createScheduleTableDepartment(Faculties faculties) {
        String sql = "SELECT * FROM departments WHERE faculty = ?";
        List<Entity> departmensList = DBConnection.getInstance().executeQuery(sql, new Object[] {
            faculties.getId()
        }, DAOKeeper.getDepartmentDAO().getResultSetHandler());
        
        SchedulerFacultyPanel facultyPanel = new SchedulerFacultyPanel();
        for (Entity entity : departmensList) {
            Departments department = (Departments) entity;
            facultyPanel.addSchedulerDepartmentPanel(department);
        }
        
        JScrollPane scroll = new JScrollPane();
        JViewport viewport = new JViewport();
        viewport.add(facultyPanel);
        viewport.setViewSize(VIEWPORT_DIMENSION);
        scroll.setViewport(viewport);
        scroll.setPreferredSize(VIEWPORT_DIMENSION);
        add(scroll, BorderLayout.CENTER);
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

    private String[] getFacultyNames(final Faculties[] faculties) {
        String[] facultyNames = new String[faculties.length];
        for (int i = 0; i < faculties.length; i++) {
            facultyNames[i] = faculties[i].getName();
        }
        return facultyNames;
    }
}
