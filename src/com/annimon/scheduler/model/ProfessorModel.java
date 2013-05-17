package com.annimon.scheduler.model;

import com.annimon.scheduler.dao.IDAO;
import com.annimon.scheduler.data.Professors;

/**
 * Модель таблицы аудиторий.
 * @author aNNiMON
 */
public class ProfessorModel extends EntityTableModel {

    private static final String[] COLUMN_NAMES = {
        "ID", "Фамилия", "Имя", "Отчество"
    };
    
    public ProfessorModel(IDAO dao) {
        super(dao);
        initTableModel();
    }
    
    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object[] fillRow(int index) {
        Professors pr = (Professors) getEntity(index);
        return new Object[] {
            pr.getId(),
            pr.getLastname(),
            pr.getFirstname(),
            pr.getMiddlename()
        };
    }
    
}
