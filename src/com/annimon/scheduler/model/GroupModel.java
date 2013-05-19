package com.annimon.scheduler.model;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.dao.IDAO;
import com.annimon.scheduler.data.Departments;
import com.annimon.scheduler.data.EducationForms;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Groups;
import com.annimon.scheduler.data.Specialities;
import com.annimon.scheduler.util.DBConnection;
import java.util.List;

/**
 * Модель таблицы групп.
 * @author aNNiMON
 */
public class GroupModel extends EntityTableModel {
    
    private static final String[] COLUMN_NAMES = {
        "ID", "Название", "Год создания", "Численность",
        "Специальность", "Кафедра", "Форма обучения"
    };
    
    public GroupModel(IDAO dao) {
        super(dao);
        initTableModel();
    }
    
    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object[] fillRow(int index) {
        Groups gr = (Groups) getEntity(index);
        
        String speciality;
        String sql = "SELECT * FROM specialities WHERE id = ?";
        List<Entity> list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            gr.getSpecialityId()
        },  DAOKeeper.getSpecialityDAO().getResultSetHandler());
        speciality = ((Specialities) list.get(0)).getName();
        
        String department;
        sql = "SELECT * FROM departments WHERE id = ?";
        list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            gr.getDepartmentId()
        },  DAOKeeper.getDepartmentDAO().getResultSetHandler());
        department = ((Departments) list.get(0)).getName();
        
        String form;
        sql = "SELECT * FROM education_forms WHERE id = ?";
        list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            gr.getEducationFormId()
        },  DAOKeeper.getEducationFormDAO().getResultSetHandler());
        form = ((EducationForms) list.get(0)).getType();
        
        return new Object[] {
            gr.getId(),
            gr.getName(),
            gr.getFormationYear(),
            gr.getStrength(),
            speciality,
            department,
            form
        };
    }
    
}
