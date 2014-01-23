package com.annimon.scheduler.util;

import com.annimon.scheduler.data.Entity;
import java.sql.ResultSet;

/**
 * Интерфейс обработки данных из ResultSet.
 * @author aNNiMON
 * @param <T>
 */
public interface IResultSetHandler<T extends Entity> {

    /*
     * Заполняет данные из ResultSet в объект Entity.
     */
    public T process(ResultSet rs) throws Exception;
}
