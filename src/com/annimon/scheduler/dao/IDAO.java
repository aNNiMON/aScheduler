package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.IResultSetHandler;
import java.util.List;

/**
 * Интерфейс объекта доступа к данным.
 * @author aNNiMON
 */
public interface IDAO {

    /**
     * Выбор всех данных из таблицы.
     * @return список данных типа Entity
     */
    public List<Entity> select();

    /**
     * Добавить новый объект.
     * @param entity добавляемый объект типа Entity
     * @return id вставленного объекта / код операции
     */
    public int insert(Entity entity);
    
    /**
     * Удалить объект из таблицы.
     * @param entity удаляемый объект
     * @return код операции
     */
    public int delete(Entity entity);
    
    /**
     * Обновить объект.
     * @param entity обновляемый объект типа Entity
     * @return код операции
     */
    public int update(Entity entity);
    
    /**
     * Получить обработчик данных из ResultSet конкретной таблицы.
     * @return интерфейс IResultSetHandler
     */
    public IResultSetHandler getResultSetHandler();
}
