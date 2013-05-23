package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Professors;
import com.annimon.scheduler.data.Subjects;
import com.annimon.scheduler.model.PairModel;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.HtmlBuilder;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 * Отчёты по преподавателям.
 * @author aNNiMON
 */
public class ReportProfessors extends AbstractRepotsForm {
    
    private Professors[] professors;

    @Override
    protected void selectionChanged(int selectionIndex) {
        Professors prof = professors[selectionIndex];
        
        String sql = "CALL prof_doomsday(?)";
        Object[][] result = DBConnection.getInstance().executeQuery(sql, new Object[] {
            prof.getId()
        });
        int index = (Integer) result[0][0];
        String doomsday = PairModel.DAY_NAMES[index - 1];
        
        sql = "CALL prof_weekends(?)";
        result = DBConnection.getInstance().executeQuery(sql, new Object[] {
            prof.getId()
        });
        String[] weekends = new String[result.length];
        for (int i = 0; i < weekends.length; i++) {
            index = (Integer) result[i][0];
            weekends[i] = PairModel.DAY_NAMES[index - 1];
        }
        
        // ФИО, загруженный день, выходные.
        Object[] params = new Object[] {
            prof.toString(), doomsday, weekends
        };
        HtmlBuilder html = new HtmlBuilder("professors.rep", params);
        setInfoText(html.toString());
    }

    @Override
    protected String[] setComboBoxValues() {
        professors = getProfessorsArray();
        
        String[] name = new String[professors.length];
        for (int i = 0; i < professors.length; i++) {
            name[i] = professors[i].toString();
        }
        return name;
    }
    
    private Professors[] getProfessorsArray() {
        List<Entity> list = DAOKeeper.getProfessorDAO().select();
        Professors[] array = new Professors[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Entity entity = list.get(i);
            array[i] = (Professors) entity;
        }
        return array;
    }
}
