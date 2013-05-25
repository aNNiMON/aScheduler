package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Professors;
import com.annimon.scheduler.model.PairModel;
import com.annimon.scheduler.model.ProfessorModel;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.HtmlBuilder;

/**
 * Отчёты по преподавателям.
 * @author aNNiMON
 */
public class ReportProfessors extends AbstractRepotsForm {
    
    private ProfessorModel model;

    @Override
    protected void selectionChanged(int selectionIndex) {
        Professors prof = (Professors) model.getEntity(selectionIndex);
        
        // Самый загруженный день преподавателя.
        String sql = "CALL prof_doomsday(?)";
        Object[][] result = DBConnection.getInstance().executeQuery(sql, new Object[] {
            prof.getId()
        });
        int index = (Integer) result[0][0];
        String doomsday = PairModel.DAY_NAMES[index - 1];
        
        // Выходные дни преподавателя.
        sql = "CALL prof_weekends(?)";
        result = DBConnection.getInstance().executeQuery(sql, new Object[] {
            prof.getId()
        });
        String[] weekends = new String[result.length];
        for (int i = 0; i < weekends.length; i++) {
            index = (Integer) result[i][0];
            weekends[i] = PairModel.DAY_NAMES[index - 1];
        }
        
        // Какие предметы читает преподаватель.
        sql = "CALL prof_subjects(?)";
        result = DBConnection.getInstance().executeQuery(sql, new Object[] {
            prof.getId()
        });
        String[] subjects = new String[result.length];
        for (int i = 0; i < subjects.length; i++) {
            subjects[i] = (String) result[i][0];
        }
        
        // ФИО, загруженный день, выходные, читаемые предметы.
        Object[] params = new Object[] {
            prof.toString(), doomsday, weekends, subjects
        };
        HtmlBuilder html = new HtmlBuilder("professors.rep", params);
        setInfoText(html.toString());
    }

    @Override
    protected String[] setComboBoxValues() {
        model = new ProfessorModel(DAOKeeper.getProfessorDAO());
        
        String[] name = new String[model.getRowCount()];
        for (int i = 0; i < name.length; i++) {
            name[i] = ((Professors) model.getEntity(i)).toString();
        }
        return name;
    }
}
