package com.annimon.scheduler.gui;

import com.annimon.scheduler.data.Pairs;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Панель для представления расписания для группы (по дням).
 * @author aNNiMON
 */
public class SchedulerGroupPanel extends JPanel {

    public SchedulerGroupPanel() {
        setLayout(new GridLayout(6, 0));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
    }
    
    public void addSchedulerDayPanel(Pairs[] pairs) {
        SchedulerDayPanel dayPanel = new SchedulerDayPanel();
        dayPanel.addSchedulerPairPanel(pairs);
        add(dayPanel);
    }
}
