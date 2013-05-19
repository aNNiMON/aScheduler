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
    
    private static final Dimension MAX_BUTTON_DIMENSION = new Dimension(150, 30);
    
    private enum ButtonId {
        REFRESH,
        AUDIENCES, PROFESSORS, SUBJECTS,
        FACULTIES, DEPARTMENTS,
        HELP, ABOUT, EXIT
    };
    
    public SchedulerMenuPanel() {
        setLayout(new BorderLayout());

        // Кнопки работы с таблицами.
        JPanel tableButtonsPanel = new JPanel();
        tableButtonsPanel.setLayout(new BoxLayout(tableButtonsPanel, BoxLayout.PAGE_AXIS));
        tableButtonsPanel.add(createButton("Обновить", ButtonId.REFRESH));
        tableButtonsPanel.add(createButton("Аудитории", ButtonId.AUDIENCES));
        tableButtonsPanel.add(createButton("Преподаватели", ButtonId.PROFESSORS));
        tableButtonsPanel.add(createButton("Предметы", ButtonId.SUBJECTS));
        tableButtonsPanel.add(createButton("Факультеты", ButtonId.FACULTIES));
        tableButtonsPanel.add(createButton("Кафедры", ButtonId.DEPARTMENTS));
        add(tableButtonsPanel, BorderLayout.CENTER);

        // Кнопки управления.
        JPanel controlButtonsPanel = new JPanel();
        controlButtonsPanel.setLayout(new BoxLayout(controlButtonsPanel, BoxLayout.PAGE_AXIS));
        controlButtonsPanel.add(createButton("Помощь", ButtonId.HELP));
        controlButtonsPanel.add(createButton("О программе", ButtonId.ABOUT));
        controlButtonsPanel.add(createButton("Выход", ButtonId.EXIT));

        add(controlButtonsPanel, BorderLayout.SOUTH);
    }
    
    private JButton createButton(String text, final ButtonId id) {
        JButton button = new JButton();
        button.setText(text);
        button.setMaximumSize(MAX_BUTTON_DIMENSION);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonClick(id);
            }
        });
        return button;
    }
    
    private void onButtonClick(ButtonId id) {
        switch (id) {
            case REFRESH:
                SchedulerPanel.getInstance().refreshScheduler();
                break;
            case AUDIENCES:
                new AudiencesForm().setVisible(true);
                break;
            case PROFESSORS:
                new ProfessorsForm().setVisible(true);
                break;
            case SUBJECTS:
                new SubjectsForm().setVisible(true);
                break;
            case FACULTIES:
                new FacultiesForm().setVisible(true);
                break;
            case DEPARTMENTS:
                new DepartmentsForm().setVisible(true);
                break;
                
            case HELP:
                break;
            case ABOUT:
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }
    
}
