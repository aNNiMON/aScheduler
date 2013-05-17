package com.annimon.scheduler.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Панель меню.
 * @author aNNiMON
 */
public class SchedulerMenuPanel extends JPanel {
    
    private static final Dimension MAX_BUTTON_DIMENSION = new Dimension(150, 23);
    private static final Dimension MIN_BUTTON_DIMENSION = new Dimension(70, 23);
    
    public SchedulerMenuPanel() {
        JPanel tableButtonsPanel = new JPanel();
        JPanel controlButtonsPanel = new JPanel();
        JButton audienceButton = new JButton();
        JButton professorsButton = new JButton();
        JButton helpButton = new JButton();
        JButton aboutButton = new JButton();
        JButton exitButton = new JButton();

        setLayout(new BorderLayout());

        tableButtonsPanel.setLayout(new BoxLayout(tableButtonsPanel, BoxLayout.PAGE_AXIS));
        audienceButton.setText("Аудитории");
        audienceButton.setMaximumSize(MAX_BUTTON_DIMENSION);
        audienceButton.setMinimumSize(MIN_BUTTON_DIMENSION);
        audienceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AudienceForm().setVisible(true);
            }
        });
        tableButtonsPanel.add(audienceButton);
        
        professorsButton.setText("Преподаватели");
        professorsButton.setMaximumSize(MAX_BUTTON_DIMENSION);
        professorsButton.setMinimumSize(MIN_BUTTON_DIMENSION);
        tableButtonsPanel.add(professorsButton);
        
        add(tableButtonsPanel, BorderLayout.CENTER);

        controlButtonsPanel.setLayout(new BoxLayout(controlButtonsPanel, BoxLayout.PAGE_AXIS));

        helpButton.setText("Помощь");
        helpButton.setMaximumSize(MAX_BUTTON_DIMENSION);
        helpButton.setMinimumSize(MIN_BUTTON_DIMENSION);
        controlButtonsPanel.add(helpButton);

        aboutButton.setText("О программе");
        aboutButton.setMaximumSize(MAX_BUTTON_DIMENSION);
        aboutButton.setMinimumSize(MIN_BUTTON_DIMENSION);
        controlButtonsPanel.add(aboutButton);

        exitButton.setText("Выход");
        exitButton.setMaximumSize(MAX_BUTTON_DIMENSION);
        exitButton.setMinimumSize(MIN_BUTTON_DIMENSION);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        controlButtonsPanel.add(exitButton);

        add(controlButtonsPanel, BorderLayout.SOUTH);
    }
    
}
