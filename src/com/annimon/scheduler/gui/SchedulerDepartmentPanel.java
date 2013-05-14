package com.annimon.scheduler.gui;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Панель для представления кафедры (включает в себя список групп).
 * @author aNNiMON
 */
public class SchedulerDepartmentPanel extends JPanel {
    
    public SchedulerDepartmentPanel(String[] groupNames, SchedulerGroupPanel[] panels) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        
        for (int i = 0; i < panels.length; i++) {
            addSchedulerGroupPanel(groupNames[i], panels[i]);
        }
    }
    
    public final void addSchedulerGroupPanel(String groupName, SchedulerGroupPanel panel) {
        JPanel groupPanel = new JPanel();
        JLabel groupLabel = new JLabel();

        groupPanel.setLayout(new BorderLayout());
        groupLabel.setText(groupName);
        groupPanel.add(groupLabel, BorderLayout.PAGE_START);
        groupPanel.add(panel, BorderLayout.CENTER);

        add(groupPanel);
    }

}
