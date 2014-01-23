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
public class SpecialityDAO implements IDAO<Specialities> {
    
    private final IResultSetHandler<Specialities> handler = new IResultSetHandler<Specialities>() {

        @Override
        public Specialities process(ResultSet rs) throws Exception {
            Specialities sp = new Specialities();
            sp.setId(rs.getInt(1));
            sp.setCode(rs.getString(2));
            sp.setName(rs.getString(3));
            return sp;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM specialities ORDER BY `name`";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Specialities sp) {
        String sql = "INSERT INTO specialities(id, code, name) " +
                "VALUES (?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            sp.getId(), sp.getCode(), sp.getName()
        });
        return rowNum;
    }
    
    @Override
    public int update(Specialities sp) {
        String sql = "UPDATE specialities SET code = ?, name = ? " +
                " WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            sp.getCode(), sp.getName(), sp.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Specialities sp) {
        String sql = "DELETE FROM specialities WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            sp.getId()
        });
        return rowNum;
    }

    @Override
    public IResultSetHandler<Specialities> getResultSetHandler() {
        return handler;
    }
}
