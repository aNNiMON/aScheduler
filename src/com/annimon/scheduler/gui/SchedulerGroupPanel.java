package com.annimon.scheduler.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Панель для представления расписания для группы (по дням).
 * @author aNNiMON
 */
public class SchedulerGroupPanel extends JPanel {

    public SchedulerGroupPanel(SchedulerDayPanel[] panels) {
        setLayout(new GridLayout(6, 0));
        for (int i = 0; i < panels.length; i++) {
            add(panels[i]);
        }
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
    }
    
    public void addSchedulerDayPanel(SchedulerDayPanel panel) {
        add(panel);
    }
}
