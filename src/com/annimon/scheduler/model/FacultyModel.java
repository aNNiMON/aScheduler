package com.annimon.scheduler.model;

import com.annimon.scheduler.dao.IDAO;
import com.annimon.scheduler.data.Faculties;

/**
 * Модель таблицы факультетов.
 * @author aNNiMON
 */
public class FacultyModel extends EntityTableModel<Faculties> {

    private static final String[] COLUMN_NAMES = {
        "ID", "Название", "Сокращение"
    };
    
    public FacultyModel(IDAO<Faculties> dao) {
        super(dao);
        initTableModel();
    }
    
    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object[] fillRow(int index) {
        Faculties fc = getEntity(index);
        return new Object[] {
            fc.getId(),
            fc.getName(),
            fc.getAbbreviation()
        };
    }
    
}
