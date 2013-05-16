package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Specialities;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class SpecialityDAO implements IDAO {
    
    private final IResultSetHandler handler = new IResultSetHandler() {

        @Override
        public Entity process(ResultSet rs) throws Exception {
            Specialities sp = new Specialities();
            sp.setId(rs.getInt(1));
            sp.setCode(rs.getString(2));
            sp.setName(rs.getString(3));
            return sp;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM specialities";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Entity entity) {
        Specialities sp = cast(entity);
        
        String sql = "INSERT INTO specialities(id, code, name) " +
                "VALUES (?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            sp.getId(), sp.getCode(), sp.getName()
        });
        return rowNum;
    }
    
    @Override
    public int update(Entity entity) {
        Specialities sp = cast(entity);
        
        String sql = "UPDATE specialities SET code = ?, name = ? " +
                " WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            sp.getCode(), sp.getName(), sp.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Entity entity) {
        String sql = "DELETE FROM specialities WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            entity.getId()
        });
        return rowNum;
    }

    @Override
    public IResultSetHandler getResultSetHandler() {
        return handler;
    }
    
    private Specialities cast(Entity entity) {
        if (entity instanceof Specialities) {
            return (Specialities) entity;
        }
        throw new ClassCastException();
    } 
}
