package com.annimon.scheduler.util;

import com.annimon.scheduler.data.Entity;
import java.sql.ResultSet;

/**
 * Интерфейс обработки данных из ResultSet.
 * @author aNNiMON
 */
public interface IResultSetHandler {

    public Entity process(ResultSet rs) throws Exception;
}
