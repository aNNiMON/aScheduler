package com.annimon.scheduler.gui;

import com.annimon.scheduler.data.Departments;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Faculties;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
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
    
    private JComboBox facultyComboBox;

    public SchedulerPanel(final Faculties[] faculties) {
        JPanel facultyChoosePanel = new JPanel();
        JLabel facultyLabel = new JLabel();
        facultyComboBox = new JComboBox();

        setLayout(new BorderLayout());

        facultyChoosePanel.setLayout(new BoxLayout(facultyChoosePanel, BoxLayout.LINE_AXIS));

        facultyLabel.setText("Факультет   ");
        facultyChoosePanel.add(facultyLabel);

        String[] facultyNames = new String[faculties.length];
        for (int i = 0; i < faculties.length; i++) {
            facultyNames[i] = faculties[i].getName();
        }
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

        createScheduleTableDepartment(faculties[0]);
    }
    
    /*
     * Получить список кафедр указанного факультета и заполнить панель расписания.
     */
    private void createScheduleTableDepartment(Faculties faculties) {
        String sql = "SELECT * FROM departments WHERE faculty = ?";
        List<Entity> departmensList = DBConnection.getInstance().executeQuery(sql, new Object[] {
            faculties.getId()
        }, new IResultSetHandler() {
            @Override
            public Entity process(ResultSet rs) throws Exception {
                Departments dp = new Departments();
                dp.setId(rs.getInt(1));
                dp.setName(rs.getString(2));
                dp.setFacultyId(rs.getInt(3));
                return dp;
            }
        });
        
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
}
