package com.annimon.scheduler.gui;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Панель для представления факультета (множество кафедр).
 * @author aNNiMON
 */
public class SchedulerFacultyPanel extends JPanel {

    public SchedulerFacultyPanel(String[] departmentNames, SchedulerDepartmentPanel[] panels) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        
        for (int i = 0; i < panels.length; i++) {
            addSchedulerDepartmentPanel(departmentNames[i], panels[i]);
        }
    }

    public final void addSchedulerDepartmentPanel(String departmentName, SchedulerDepartmentPanel panel) {
        JPanel departmentPanel = new JPanel();
        JLabel departmentLabel = new JLabel();

        departmentPanel.setLayout(new BorderLayout());
        departmentLabel.setText(departmentName);
        departmentPanel.add(departmentLabel, BorderLayout.PAGE_START);
        departmentPanel.add(panel, BorderLayout.CENTER);

        add(departmentPanel);
    }
}
