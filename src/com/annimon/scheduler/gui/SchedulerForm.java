package com.annimon.scheduler.gui;

import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.ExceptionHandler;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        setTitle("aScheduler - редактор расписания");
        SchedulerPanel schedulerPanel = new SchedulerPanel();
        SchedulerMenuPanel schedulerMenuPanel = new SchedulerMenuPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // При закрытии окна следует освободить ресурс связи с БД.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                DBConnection.getInstance().destroy();
            }
        });
        getContentPane().add(schedulerPanel, BorderLayout.CENTER);
        getContentPane().add(schedulerMenuPanel, BorderLayout.EAST);

        pack();
    }
    
    public static void main(String[] args) {
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
