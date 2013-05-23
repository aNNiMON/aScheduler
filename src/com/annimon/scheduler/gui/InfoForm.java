package com.annimon.scheduler.gui;

import com.annimon.scheduler.util.HtmlBuilder;

/**
 * Базовый макет формы для окон информации (помощь, о программе).
 * @author aNNiMON
 */
public class InfoForm extends AbstractRepotsForm {

    public InfoForm(String htmlResource) {
        // Удаляем ComboBox.
        getContentPane().remove(0);
        HtmlBuilder html = new HtmlBuilder(htmlResource, null);
        super.setInfoText(html.toString());
        pack();
    }

    @Override
    protected String[] setComboBoxValues() {
        return new String[0];
    }

    @Override
    protected void selectionChanged(int selectionIndex) {
        
    }
}
