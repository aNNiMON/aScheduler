package com.annimon.scheduler.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * Панель для представления одной пары с возможностью указания верхней или нижней недели.
 * @author aNNiMON
 */
public class SchedulerPairPanel extends JPanel {

    private final JPanel subjectsPanel;

    public SchedulerPairPanel(String numberPair, String timeEnd) {
        JPanel numberPairPanel = new JPanel();
        JLabel numberPairLabel = new JLabel();
        JLabel timeEndLabel = new JLabel();
        subjectsPanel = new JPanel();

        setLayout(new BorderLayout());

        numberPairPanel.setLayout(new GridLayout(2, 0));

        numberPairLabel.setText(numberPair);
        numberPairLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        numberPairPanel.add(numberPairLabel);

        timeEndLabel.setText(timeEnd);
        timeEndLabel.setVerticalAlignment(SwingConstants.TOP);
        numberPairPanel.add(timeEndLabel);

        add(numberPairPanel, BorderLayout.LINE_START);

        subjectsPanel.setLayout(new BoxLayout(subjectsPanel, BoxLayout.PAGE_AXIS));

        add(subjectsPanel, BorderLayout.CENTER);
        
        setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
    }
    
    public void addSubject(Short week, String subject, String audience, String professor) {
        SchedulerSubjectPanel panel = new SchedulerSubjectPanel(subject, audience, professor);
        if (week == null) subjectsPanel.add(panel);
        else {
            SchedulerSubjectPanel empty = new SchedulerSubjectPanel("--", "--", "--");
            if (week.intValue() == 0) {
                subjectsPanel.add(panel);
                subjectsPanel.add(empty);
            } else if (week.intValue() == 1) {
                subjectsPanel.add(empty);
                subjectsPanel.add(panel);
            }
        }
    }
    
}
