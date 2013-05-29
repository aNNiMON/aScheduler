package com.annimon.scheduler.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Панель меню.
 * @author aNNiMON
 */
public class SchedulerMenuPanel extends JPanel {
    
    private static final Dimension MAX_BUTTON_DIMENSION = new Dimension(150, 30);

    private enum ButtonId {
        REFRESH,
        
        AUDIENCES, PROFESSORS, SUBJECTS, PAIRS,
        FACULTIES, DEPARTMENTS, SPECIALITIES, GROUPS,
        
        REPORT_PROFESSORS, REPORT_AUDIENCES, REPORT_GROUPS,
        
        HELP, ABOUT, EXIT
    };
    
    public SchedulerMenuPanel() {
        setLayout(new BorderLayout());

        // Кнопки работы с таблицами.
        JPanel tableButtonsPanel = new JPanel();
        tableButtonsPanel.setLayout(new BoxLayout(tableButtonsPanel, BoxLayout.PAGE_AXIS));
        tableButtonsPanel.add(createButton("Обновить", ButtonId.REFRESH));
        tableButtonsPanel.add(createLabel("Таблицы"));
        tableButtonsPanel.add(createButton("Аудитории", ButtonId.AUDIENCES));
        tableButtonsPanel.add(createButton("Преподаватели", ButtonId.PROFESSORS));
        tableButtonsPanel.add(createButton("Предметы", ButtonId.SUBJECTS));
        tableButtonsPanel.add(createButton("Пары", ButtonId.PAIRS));
        tableButtonsPanel.add(createButton("Факультеты", ButtonId.FACULTIES));
        tableButtonsPanel.add(createButton("Кафедры", ButtonId.DEPARTMENTS));
        tableButtonsPanel.add(createButton("Специальности", ButtonId.SPECIALITIES));
        tableButtonsPanel.add(createButton("Группы", ButtonId.GROUPS));
        tableButtonsPanel.add(createLabel("Отчёты"));
        tableButtonsPanel.add(createButton("Преподаватели", ButtonId.REPORT_PROFESSORS));
        tableButtonsPanel.add(createButton("Аудитории", ButtonId.REPORT_AUDIENCES));
        tableButtonsPanel.add(createButton("Группы", ButtonId.REPORT_GROUPS));
        add(tableButtonsPanel, BorderLayout.CENTER);

        // Кнопки управления.
        JPanel controlButtonsPanel = new JPanel();
        controlButtonsPanel.setLayout(new BoxLayout(controlButtonsPanel, BoxLayout.PAGE_AXIS));
        controlButtonsPanel.add(createButton("Помощь", ButtonId.HELP));
        controlButtonsPanel.add(createButton("О программе", ButtonId.ABOUT));
        controlButtonsPanel.add(createButton("Выход", ButtonId.EXIT));

        add(controlButtonsPanel, BorderLayout.SOUTH);
    }
    
    private JButton createButton(final String text, final ButtonId id) {
        JButton button = new JButton();
        button.setText(text);
        button.setMaximumSize(MAX_BUTTON_DIMENSION);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonClick(id, text);
            }
        });
        return button;
    }
    
    /**
     * Создание невидимой панели отступа.
     */
    private JPanel createGap() {
        JPanel gap = new JPanel();
        gap.setMaximumSize(MAX_BUTTON_DIMENSION);
        return gap;
    }
    
    /**
     * Создание метки.
     * @param text текст метки
     * @return объект JLabel
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel();
        label.setMaximumSize(MAX_BUTTON_DIMENSION);
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.BOTTOM);
        return label;
    }
    
    /**
     * Обработка нажатий кнопок.
     * @param id идентификатор нажатой кнопки
     * @param text текст нажатой кнопки (передаётся как заголовок форм)
     */
    private void onButtonClick(ButtonId id, String text) {
        switch (id) {
            case REFRESH:
                SchedulerPanel.getInstance().refreshScheduler();
                break;
                
            // Таблицы.
            case AUDIENCES:
                new AudiencesForm(text).setVisible(true);
                break;
            case PROFESSORS:
                new ProfessorsForm(text).setVisible(true);
                break;
            case SUBJECTS:
                new SubjectsForm(text).setVisible(true);
                break;
            case PAIRS:
                new PairsForm(text).setVisible(true);
                break;
            case FACULTIES:
                new FacultiesForm(text).setVisible(true);
                break;
            case DEPARTMENTS:
                new DepartmentsForm(text).setVisible(true);
                break;
            case SPECIALITIES:
                new SpecialitiesForm(text).setVisible(true);
                break;
            case GROUPS:
                new GroupsForm(text).setVisible(true);
                break;
            
            // Отчёты.
            case REPORT_PROFESSORS:
                new ReportProfessors(text).setVisible(true);
                break;
            case REPORT_AUDIENCES:
                new ReportAudiences(text).setVisible(true);
                break;
            case REPORT_GROUPS:
                new ReportGroups(text).setVisible(true);
                break;
            
            // Управление.
            case HELP:
            case ABOUT:
                new InfoForm(id == ButtonId.HELP ? "help.html" : "about.html", text).setVisible(true);
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }
    
}
