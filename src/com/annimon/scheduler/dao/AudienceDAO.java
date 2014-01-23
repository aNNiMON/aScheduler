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
public class AudienceDAO implements IDAO<Audiences> {
    
    private final IResultSetHandler<Audiences> handler = new IResultSetHandler<Audiences>() {

        @Override
        public Audiences process(ResultSet rs) throws Exception {
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
        String sql = "SELECT * FROM audiences ORDER BY `number`";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Audiences au) {
        String sql = "INSERT INTO audiences(id, number, type, housing, capacity) " +
                "VALUES (?, ?, ?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            au.getId(), au.getNumber(), au.getType(), au.getHousing(), au.getCapacity()
        });
        return rowNum;
    }
    
    @Override
    public int update(Audiences au) {
        String sql = "UPDATE audiences SET number = ?, type = ?," +
                " housing = ?, capacity = ? WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            au.getNumber(), au.getType(), au.getHousing(), au.getCapacity(), au.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Audiences au) {
        String sql = "DELETE FROM audiences WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            au.getId()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler<Audiences> getResultSetHandler() {
        return handler;
    }
}
