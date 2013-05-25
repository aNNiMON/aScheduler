package com.annimon.scheduler.gui;

import com.annimon.scheduler.util.ExceptionHandler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * Базовый макет формы для отчётов.
 * @author aNNiMON
 */
public abstract class AbstractRepotsForm extends JDialog {

    private JEditorPane infoPane;
    
    public AbstractRepotsForm() {
        setModal(true);
        initComponents();
    }

    private void initComponents() {
        // Текст отчётов выводится в JEditorPane в HTML-представлении.
        infoPane = new JEditorPane();
        infoPane.setContentType("text/html");
        infoPane.setEditable(false);
        infoPane.setOpaque(false);
        infoPane.setBackground(new Color(0,0,0,0));
        // Возможность открытия ссылок в браузере.
        infoPane.addHyperlinkListener(new HyperlinkListener() {

            @Override
            public void hyperlinkUpdate(HyperlinkEvent hle) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                    openBrowser(hle.getURL().toString());
                }
            }
        });
        infoPane.setBorder(new EmptyBorder(5, 10, 2, 10));

        // Выбор данных для показа отчёта. Данные формируются в производных
        // классах в методе setComboBoxValues.
        final JComboBox selectorComboBox = new JComboBox();
        selectorComboBox.setModel(new DefaultComboBoxModel(setComboBoxValues()));
        selectorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int index = selectorComboBox.getSelectedIndex();
                selectionChanged(index);
                validate();
            }
        });
        
        getContentPane().add(selectorComboBox, BorderLayout.PAGE_START);
        getContentPane().add(infoPane, BorderLayout.CENTER);
        
        selectionChanged(0);
        pack();
    }
    
    protected void setInfoText(String text) {
        infoPane.setText(text);
        pack();
    }

    /**
     * Заполнить данные для отчёта.
     * Полученный массив строк будет отображаться в ComboBox.
     * @return массив строк
     */
    protected abstract String[] setComboBoxValues();
    
    protected abstract void selectionChanged(int selectionIndex);
    
    /**
     * Найти поддерживаемый браузер и открыть в нём URL.
     * @param url ссылка на удалённый ресурс
     */
    private void openBrowser(String url) {
        String osName = System.getProperty("os.name");
        try {
            if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec(
                        "rundll32 url.dll,FileProtocolHandler " + url);
            } else {
                String[] browsers = {"firefox", "opera", "konqueror",
                    "epiphany", "mozilla", "netscape", "chrome"};
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++) {
                    if (Runtime.getRuntime().exec(
                            new String[]{"which", browsers[count]})
                            .waitFor() == 0)
                        browser = browsers[count];
                }
                Runtime.getRuntime().exec(new String[]{browser, url});
            }
        } catch (IOException | InterruptedException e) {
            ExceptionHandler.handle(e, "open browser");
        }
    }
}
