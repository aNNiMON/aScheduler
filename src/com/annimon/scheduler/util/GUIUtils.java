package com.annimon.scheduler.util;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 * Методы для работы с пользовательским интерфейсом.
 * @author aNNiMON
 */
public class GUIUtils {

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(text);
        return label;
    }
    
    public static void showErrorMessage(Throwable throwable) {
        ExceptionHandler.handle(throwable);
        JOptionPane.showMessageDialog(null, throwable.getMessage(), "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static SpinnerNumberModel createShortSpinnerModel() {
        return createShortSpinnerModel((short) 0);
    }
    
    public static SpinnerNumberModel createShortSpinnerModel(short min) {
        return new SpinnerNumberModel(min, min, null, (short) 1);
    }
}
