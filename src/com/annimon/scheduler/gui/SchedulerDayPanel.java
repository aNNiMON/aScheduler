package com.annimon.scheduler.gui;

import com.annimon.scheduler.data.Pairs;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Панель для представления одного дня, включающего множество пар.
 * @author aNNiMON
 */
public class SchedulerDayPanel extends JPanel {

    public SchedulerDayPanel(SchedulerPairPanel[] panels) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        for (int i = 0; i < panels.length; i++) {
            add(panels[i]);
        }
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    }
    
    public void addSchedulerPairPanel(SchedulerPairPanel panel) {
        add(panel);
    }
    
    /*public SchedulerDayPanel(Pairs[] pairs) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        for (int i = 0; i < pairs.length; i++) {
            Pairs pair = pairs[i];
            
            String numberPair = "", timeEnd = "";
            if ( (pair.getTimeBegin() != null) && (pair.getTimeEnd() != null) ) {
                numberPair = pair.getTimeBegin().toString();
                timeEnd = pair.getTimeEnd().toString();
            } else {
                numberPair = String.valueOf(pair.getNumber());
            }
            SchedulerPairPanel panel = new SchedulerPairPanel(numberPair, timeEnd);
            add(panel);
        }
    }*/

}