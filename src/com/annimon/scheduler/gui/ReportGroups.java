package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.model.GroupModel;
import com.annimon.scheduler.util.HtmlBuilder;

/**
 * Отчёты по группам.
 * @author aNNiMON
 */
public class ReportGroups extends AbstractRepotsForm {
    
    private GroupModel model;
    
    public ReportGroups(String title) {
        super(title);
    }

    @Override
    protected void selectionChanged(int selectionIndex) {
        Object[] params = model.getRowObjects(selectionIndex);
        System.arraycopy(params, 1, params, 0, params.length - 1);
        HtmlBuilder html = new HtmlBuilder("groups.rep", params);
        setInfoText(html.toString());
    }

    @Override
    protected String[] setComboBoxValues() {
        model = new GroupModel(DAOKeeper.getGroupDAO());
        
        String[] name = new String[model.getRowCount()];
        for (int i = 0; i < name.length; i++) {
            name[i] = model.getEntity(i).getName();
        }
        return name;
    }
}
