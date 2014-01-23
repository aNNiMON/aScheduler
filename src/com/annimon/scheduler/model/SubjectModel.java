package com.annimon.scheduler.model;

import com.annimon.scheduler.dao.IDAO;
import com.annimon.scheduler.data.Subjects;

/**
 * Модель таблицы предметов.
 * @author aNNiMON
 */
public class SubjectModel extends EntityTableModel<Subjects> {

    private static final String[] COLUMN_NAMES = {
        "ID", "Название", "Сокращение"
    };
    
    public SubjectModel(IDAO<Subjects> dao) {
        super(dao);
        initTableModel();
    }
    
    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object[] fillRow(int index) {
        Subjects sb = getEntity(index);
        return new Object[] {
            sb.getId(),
            sb.getName(),
            sb.getAbbreviation()
        };
    }
    
}
