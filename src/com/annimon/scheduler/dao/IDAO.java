package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.IResultSetHandler;
import java.util.List;

/**
 * Интерфейс объекта доступа к данным.
 * @author aNNiMON
 * @param <T>
 */
public interface IDAO<T extends Entity> {

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
    public int insert(T entity);
    
    /**
     * Удалить объект из таблицы.
     * @param entity удаляемый объект
     * @return код операции
     */
    public int delete(T entity);
    
    /**
     * Обновить объект.
     * @param entity обновляемый объект типа Entity
     * @return код операции
     */
    public int update(T entity);
    
    /**
     * Получить обработчик данных из ResultSet конкретной таблицы.
     * @return интерфейс IResultSetHandler
     */
    public IResultSetHandler<T> getResultSetHandler();
}
