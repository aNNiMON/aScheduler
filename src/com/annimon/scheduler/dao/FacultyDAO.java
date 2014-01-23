package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Faculties;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class FacultyDAO implements IDAO<Faculties> {
    
    private final IResultSetHandler<Faculties> handler = new IResultSetHandler<Faculties>() {

        @Override
        public Faculties process(ResultSet rs) throws Exception {
            Faculties fc = new Faculties();
            fc.setId(rs.getInt(1));
            fc.setName(rs.getString(2));
            fc.setAbbreviation(rs.getString(3));
            return fc;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM faculties ORDER BY `name`";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Faculties fc) {
        String sql = "INSERT INTO faculties(id, name, abbreviation) " +
                "VALUES (?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            fc.getId(), fc.getName(), fc.getAbbreviation()
        });
        return rowNum;
    }
    
    @Override
    public int update(Faculties fc) {
        String sql = "UPDATE faculties SET name = ?, abbreviation = ? WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            fc.getName(), fc.getAbbreviation(), fc.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Faculties fc) {
        String sql = "DELETE FROM faculties WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            fc.getId()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler<Faculties> getResultSetHandler() {
        return handler;
    }
}
