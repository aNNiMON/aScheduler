package com.annimon.scheduler.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Панель для представления одного предмета.
 * @author aNNiMON
 */
public class SchedulerSubjectPanel extends JPanel {

    public SchedulerSubjectPanel(String subject, String audience, String professor) {
        GridBagConstraints gridBagConstraints;

        JLabel subjectLabel = new JLabel();
        JLabel audienceLabel = new JLabel();
        JLabel professorLabel = new JLabel();

        setLayout(new GridBagLayout());

        subjectLabel.setText(subject);
        add(subjectLabel, new GridBagConstraints());

        audienceLabel.setText(audience);
        add(audienceLabel, new GridBagConstraints());

        professorLabel.setText(professor);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        add(professorLabel, gridBagConstraints);
    }
    
}
