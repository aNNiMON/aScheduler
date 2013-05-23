package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Audiences;
import com.annimon.scheduler.model.AudienceModel;
import com.annimon.scheduler.model.PairModel;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.HtmlBuilder;

/**
 * Отчёты по аудиториям.
 * @author aNNiMON
 */
public class ReportAudiences extends AbstractRepotsForm {
    
    private AudienceModel model;

    @Override
    protected void selectionChanged(int selectionIndex) {
        Audiences au = (Audiences) model.getEntity(selectionIndex);
        
        String sql = "CALL au_subjects(?)";
        Object[][] result = DBConnection.getInstance().executeQuery(sql, new Object[] {
            au.getId()
        });
        String[] subjects = new String[result.length];
        for (int i = 0; i < subjects.length; i++) {
            subjects[i] = (String) result[i][0];
        }
        
        Object[] params = new Object[] {
            au.getFullNumber(), AudienceModel.AUDIENCE_TYPE[au.getType()],
            au.getCapacity(), subjects
        };
        HtmlBuilder html = new HtmlBuilder("audiences.rep", params);
        setInfoText(html.toString());
    }

    @Override
    protected String[] setComboBoxValues() {
        model = new AudienceModel(DAOKeeper.getAudienceDAO());
        
        String[] name = new String[model.getRowCount()];
        for (int i = 0; i < name.length; i++) {
            name[i] = ((Audiences) model.getEntity(i)).getFullNumber();
        }
        return name;
    }
}
