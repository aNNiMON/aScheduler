package com.annimon.scheduler.gui;

import com.annimon.scheduler.util.HtmlBuilder;

/**
 * Базовый макет формы для окон информации (помощь, о программе).
 * @author aNNiMON
 */
public class InfoForm extends AbstractRepotsForm {

    /**
     * Создаёт форму вывода информации.
     * @param htmlResource путь ко внутреннему HTML-ресурсу
     */
    public InfoForm(String htmlResource) {
        // Удаляем ComboBox.
        getContentPane().remove(0);
        HtmlBuilder html = new HtmlBuilder(htmlResource, null);
        super.setInfoText(html.toString());
        pack();
    }
    
    /**
     * Создаёт форму вывода информации.
     * @param htmlResource путь ко внутреннему HTML-ресурсу
     * @param title заголовок окна
     */
    public InfoForm(String htmlResource, String title) {
        this(htmlResource);
        setTitle(title);
    }

    @Override
    protected String[] setComboBoxValues() {
        // Так как ComboBox удалён, возвращаем пустой массив.
        return new String[0];
    }

    @Override
    protected void selectionChanged(int selectionIndex) {
        // не реализовано.
    }
}
