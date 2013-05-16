package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.IResultSetHandler;
import java.util.List;

/**
 * Интерфейс объекта доступа к данным.
 * @author aNNiMON
 */
public interface IDAO {

    public List<Entity> select();

    public int insert(Entity entity);
    
    public int delete(Entity entity);
    
    public int update(Entity entity);
    
    public IResultSetHandler getResultSetHandler();
}
