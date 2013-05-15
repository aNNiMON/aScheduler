package com.annimon.scheduler.gui;

import com.annimon.scheduler.data.Faculties;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                createScheduleTable(faculties[index]);
            }
        });
        facultyChoosePanel.add(facultyComboBox);

        add(facultyChoosePanel, BorderLayout.NORTH);
        //add(schedulerFacultyPanel, BorderLayout.SOUTH);
        
        createScheduleTable(faculties[0]);
    }

    public final void createScheduleTable(Faculties faculties) {
        SchedulerGroupPanel po2 = new SchedulerGroupPanel(createSchedulerDayPanels());
        SchedulerGroupPanel siia1 = new SchedulerGroupPanel(createSchedulerDayPanels());
        
        String[] groupNames = new String[] { "2ПО-07", "СИИ А1" };
        SchedulerGroupPanel[] groupPanels = new SchedulerGroupPanel[] { po2, siia1 };
        
        SchedulerDepartmentPanel departmentPanel = new SchedulerDepartmentPanel(groupNames, groupPanels);
        
        String[] departmentNames = new String[] { "Компьютерных технологий" };
        SchedulerDepartmentPanel[] departmentPanels = new SchedulerDepartmentPanel[1];
        departmentPanels[0] = departmentPanel;
        
        SchedulerFacultyPanel facultyPanel = new SchedulerFacultyPanel(departmentNames, departmentPanels);
        JScrollPane scroll = new JScrollPane();
        JViewport viewport = new JViewport();
        viewport.add(facultyPanel);
        viewport.setViewSize(new Dimension(800, 500));
        scroll.setViewport(viewport);
        scroll.setPreferredSize(new Dimension(800, 500));
        add(scroll, BorderLayout.SOUTH);
    }
    
    private SchedulerPairPanel[] createSchedulerPairPanels() {
        SchedulerPairPanel[] pair1Panels = new SchedulerPairPanel[4];
        pair1Panels[0] = new SchedulerPairPanel("1", "");
        pair1Panels[0].addSubject((short)0, "ООП", "119", "Симасина О.А.");
        pair1Panels[1] = new SchedulerPairPanel("2", "");
        pair1Panels[1].addSubject((short)0, "ТПСПП", "120", "Надеева Е.А.");
        pair1Panels[1].addSubject((short)1, "--", "--", "--");
        pair1Panels[2] = new SchedulerPairPanel("3", "");
        pair1Panels[2].addSubject((short)0, "--", "--", "--");
        pair1Panels[2].addSubject((short)1, "Комп. сети", "115", "Лебедев А.С.");
        pair1Panels[3] = new SchedulerPairPanel("13:00", "13:30");
        pair1Panels[3].addSubject((short)0, "Классный час", "405", "Маншилина М.В.");
        
        return pair1Panels;
    }
    
    private SchedulerDayPanel[] createSchedulerDayPanels() {
        SchedulerDayPanel[] dayPanels = new SchedulerDayPanel[6];
        dayPanels[0] = new SchedulerDayPanel(createSchedulerPairPanels());
        dayPanels[1] = new SchedulerDayPanel(createSchedulerPairPanels());
        dayPanels[2] = new SchedulerDayPanel(createSchedulerPairPanels());
        dayPanels[3] = new SchedulerDayPanel(createSchedulerPairPanels());
        dayPanels[4] = new SchedulerDayPanel(createSchedulerPairPanels());
        dayPanels[5] = new SchedulerDayPanel(createSchedulerPairPanels());
        
        return dayPanels;
    }
}
