package com.annimon.scheduler.model;

import com.annimon.scheduler.dao.IDAO;
import com.annimon.scheduler.data.Audiences;

/**
 * Модель таблицы аудиторий.
 * @author aNNiMON
 */
public class AudienceModel extends EntityTableModel {

    public static final String[] AUDIENCE_TYPE = {
        "Практическая", "Лекционная"
    };
    
    private static final String[] COLUMN_NAMES = {
        "ID", "Номер", "Тип", "Корпус", "Вместимость"
    };
    
    public AudienceModel(IDAO dao) {
        super(dao);
        initTableModel();
    }
    
    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Object[] fillRow(int index) {
        Audiences au = (Audiences) getEntity(index);
        return new Object[] {
            au.getId(),
            au.getNumber(),
            AUDIENCE_TYPE[au.getType()],
            au.getHousing(),
            au.getCapacity()
        };
    }
    
}
