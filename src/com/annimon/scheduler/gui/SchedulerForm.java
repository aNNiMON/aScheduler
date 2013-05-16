package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.FacultyDAO;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Faculties;
import com.annimon.scheduler.util.ExceptionHandler;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * Главная форма приложения.
 * @author aNNiMON
 */
public class SchedulerForm extends JFrame {
    
    public SchedulerForm() {
        SchedulerPanel schedulerPanel = new SchedulerPanel(getFacultiesArray());
        SchedulerMenuPanel schedulerMenuPanel = new SchedulerMenuPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(schedulerPanel, BorderLayout.CENTER);
        getContentPane().add(schedulerMenuPanel, BorderLayout.EAST);

        pack();
    }
    
    private Faculties[] getFacultiesArray() {
        FacultyDAO faculty = new FacultyDAO();
        List<Entity> facultiesList = faculty.select();
        Faculties[] array = new Faculties[facultiesList.size()];
        for (int i = 0; i < facultiesList.size(); i++) {
            Entity entity = facultiesList.get(i);
            array[i] = (Faculties) entity;
        }
        return array;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ExceptionHandler.handle(ex, "look and feel init");
        }

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new SchedulerForm().setVisible(true);
            }
        });
    }
}
