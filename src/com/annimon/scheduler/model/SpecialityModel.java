package com.annimon.scheduler.model;

import com.annimon.scheduler.dao.IDAO;
import com.annimon.scheduler.data.Specialities;

/**
 * Модель таблицы специальностей.
 * @author aNNiMON
 */
public class SpecialityModel extends EntityTableModel<Specialities> {

    private static final String[] COLUMN_NAMES = {
        "ID", "Код", "Название"
    };
    
    public SpecialityModel(IDAO<Specialities> dao) {
        super(dao);
        initTableModel();
    }
    
    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object[] fillRow(int index) {
        Specialities sp = getEntity(index);
        return new Object[] {
            sp.getId(),
            sp.getCode(),
            sp.getName()
        };
    }
    
}
