package com.annimon.scheduler.util;

import javax.swing.JLabel;
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
    
    public static SpinnerNumberModel createShortSpinnerModel() {
        return new SpinnerNumberModel(
            Short.valueOf((short)0), Short.valueOf((short)0), null, Short.valueOf((short)1));
    }
}
