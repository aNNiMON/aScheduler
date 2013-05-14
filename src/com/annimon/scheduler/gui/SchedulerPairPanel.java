package com.annimon.scheduler.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
    }
    
    public void addSubject(Short week, String subject, String audience, String professor) {
        subjectsPanel.add(new SchedulerSubjectPanel(subject, audience, professor), week.intValue());
    }
    
}
