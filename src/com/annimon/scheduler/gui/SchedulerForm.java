package com.annimon.scheduler.gui;

import com.annimon.scheduler.data.Faculties;
import com.annimon.scheduler.util.ExceptionHandler;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Главная форма приложения.
 * @author aNNiMON
 */
public class SchedulerForm extends JFrame {
    
    private SchedulerMenuPanel schedulerMenuPanel1;
    private SchedulerPanel schedulerPanel;

    public SchedulerForm() {
        Faculties fc1 = new Faculties();
        fc1.setName("Физический");
        Faculties fc2 = new Faculties();
        fc2.setName("Филологический");
        Faculties[] faculties = new Faculties[] { fc1, fc2 };
        schedulerPanel = new SchedulerPanel(faculties);
        schedulerMenuPanel1 = new SchedulerMenuPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(schedulerPanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(schedulerMenuPanel1, java.awt.BorderLayout.EAST);

        pack();
        
        //schedulerPanel.createScheduleTable(fc1);
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
