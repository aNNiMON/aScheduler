package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Audiences;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class AudienceDAO implements IDAO {
    
    private final IResultSetHandler handler = new IResultSetHandler() {

        @Override
        public Entity process(ResultSet rs) throws Exception {
            Audiences au = new Audiences();
            au.setId(rs.getInt(1));
            au.setNumber(rs.getShort(2));
            au.setType(rs.getShort(3));
            au.setHousing(rs.getShort(4));
            au.setCapacity(rs.getShort(5));
            return au;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM audiences";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Entity entity) {
        Audiences au = cast(entity);
        
        String sql = "INSERT INTO audiences(id, number, type, housing, capacity) " +
                "VALUES (?, ?, ?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            au.getId(), au.getNumber(), au.getType(), au.getHousing(), au.getCapacity()
        });
        return rowNum;
    }
    
    @Override
    public int update(Entity entity) {
        Audiences au = cast(entity);
        
        String sql = "UPDATE audiences SET number = ?, type = ?," +
                " housing = ?, capacity = ? WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            au.getNumber(), au.getType(), au.getHousing(), au.getCapacity(), au.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Entity entity) {
        String sql = "DELETE FROM audiences WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            entity.getId()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler getResultSetHandler() {
        return handler;
    }

    private Audiences cast(Entity entity) {
        if (entity instanceof Audiences) {
            return (Audiences) entity;
        }
        throw new ClassCastException();
    } 
}
