package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.GroupDAO;
import com.annimon.scheduler.data.Departments;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Groups;
import com.annimon.scheduler.util.DBConnection;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Панель для представления факультета (множество кафедр).
 * @author aNNiMON
 */
public class SchedulerFacultyPanel extends JPanel {

    public SchedulerFacultyPanel() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    }
    
    public final void addSchedulerDepartmentPanel(Departments departments) {
        JPanel departmentPanel = new JPanel();
        JLabel departmentLabel = new JLabel();

        departmentPanel.setLayout(new BorderLayout());
        departmentLabel.setText(departments.getName());
        departmentPanel.add(departmentLabel, BorderLayout.PAGE_START);
        departmentPanel.add(createScheduleTableGroup(departments), BorderLayout.CENTER);

        add(departmentPanel);
    }
    
    /*
     * Получить список групп указанной кафедры и заполнить панель расписания.
     */
    private SchedulerDepartmentPanel createScheduleTableGroup(Departments departments) {
        String sql = "SELECT * FROM groups WHERE department = ?";
        List<Entity> groupsList = DBConnection.getInstance().executeQuery(sql, new Object[] {
            departments.getId()
        }, new GroupDAO().getResultSetHandler());
        
        SchedulerDepartmentPanel departmentPanel = new SchedulerDepartmentPanel();
        for (Entity entity : groupsList) {
            Groups groups = (Groups) entity;
            departmentPanel.addSchedulerGroupPanel(groups);
        }
        
        return departmentPanel;
    }
}
