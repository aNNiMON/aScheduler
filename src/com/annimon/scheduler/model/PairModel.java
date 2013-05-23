package com.annimon.scheduler.model;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.dao.IDAO;
import com.annimon.scheduler.data.Audiences;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Groups;
import com.annimon.scheduler.data.Pairs;
import com.annimon.scheduler.data.Professors;
import com.annimon.scheduler.data.Subjects;
import com.annimon.scheduler.util.DBConnection;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Модель таблицы пар.
 * @author aNNiMON
 */
public class PairModel extends EntityTableModel {
    
    public static final String[] DAY_NAMES = {
        "Понедельник", "Вторник", "Среда",
        "Четверг", "Пятница", "Суббота", "Воскресенье"
    };
    
    public static final String[] WEEK_NAMES = {
        "Любая", "Верхняя", "Нижняя"
    };
    
    private static final String[] COLUMN_NAMES = {
        "ID", "Номер", "Начало", "Конец", "День", "Неделя",
        "Аудитория", "Предмет", "Преподаватель", "Группа"
    };
    
    public PairModel(IDAO dao) {
        super(dao);
        initTableModel();
    }
    
    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }
    
    @Override
    protected boolean isExitOnInsertionError(Entity entity) {
        // Если при добавлении вернулся код -1, обновляем модель и выходим из функции добавления.
        super.refreshDataFromDAO();
        return true;
    }

    @Override
    protected Object[] fillRow(int index) {
        Pairs pr = (Pairs) getEntity(index);
        
        int weekIndex;
        if (pr.getWeek() == null) weekIndex = 0;
        else weekIndex = pr.getWeek() + 1;
        
        String timeBegin = null;
        if (pr.getTimeBegin() != null) {
            timeBegin = new SimpleDateFormat("HH:mm:ss").format(pr.getTimeBegin());
        }
        
        String timeEnd = null;
        if (pr.getTimeBegin() != null) {
            timeEnd = new SimpleDateFormat("HH:mm:ss").format(pr.getTimeEnd());
        }
        
        String audience;
        String sql = "SELECT * FROM audiences WHERE id = ?";
        List<Entity> list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            pr.getAudienceId()
        },  DAOKeeper.getAudienceDAO().getResultSetHandler());
        audience = ((Audiences) list.get(0)).getFullNumber();
        
        String subject;
        sql = "SELECT * FROM subjects WHERE id = ?";
        list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            pr.getSubjectId()
        },  DAOKeeper.getSubjectDAO().getResultSetHandler());
        subject = ((Subjects) list.get(0)).getAbbreviation();
        
        String professor;
        sql = "SELECT * FROM professors WHERE id = ?";
        list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            pr.getProfessorId()
        },  DAOKeeper.getProfessorDAO().getResultSetHandler());
        professor = ((Professors) list.get(0)).getName();
        
        String group;
        sql = "SELECT * FROM groups WHERE id = ?";
        list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            pr.getGroupId()
        },  DAOKeeper.getGroupDAO().getResultSetHandler());
        group = ((Groups) list.get(0)).getName();
        
        return new Object[] {
            pr.getId(),
            pr.getNumber(),
            timeBegin,
            timeEnd,
            DAY_NAMES[pr.getDay() - 1],
            WEEK_NAMES[weekIndex],
            audience,
            subject,
            professor,
            group
        };
    }
    
}
