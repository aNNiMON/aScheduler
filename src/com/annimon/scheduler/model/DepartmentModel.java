package com.annimon.scheduler.model;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.dao.IDAO;
import com.annimon.scheduler.data.Departments;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Faculties;
import com.annimon.scheduler.util.DBConnection;
import java.util.List;

/**
 * Модель таблицы кафедр.
 * @author aNNiMON
 */
public class DepartmentModel extends EntityTableModel {

    private static final String[] COLUMN_NAMES = {
        "ID", "Название", "Факультет"
    };
    
    public DepartmentModel(IDAO dao) {
        super(dao);
        initTableModel();
    }
    
    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object[] fillRow(int index) {
        Departments dp = (Departments) getEntity(index);
        
        String faculty;
        String sql = "SELECT * FROM faculties WHERE id = ?";
        List<Entity> list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            dp.getFacultyId()
        },  DAOKeeper.getFacultyDAO().getResultSetHandler());
        faculty = ((Faculties) list.get(0)).getAbbreviation();
        
        return new Object[] {
            dp.getId(),
            dp.getName(),
            faculty
        };
    }
    
}
