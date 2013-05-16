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
public class FacultyDAO implements IDAO {
    
    private final IResultSetHandler handler = new IResultSetHandler() {

        @Override
        public Entity process(ResultSet rs) throws Exception {
            Faculties fc = new Faculties();
            fc.setId(rs.getInt(1));
            fc.setName(rs.getString(2));
            fc.setAbbreviation(rs.getString(3));
            return fc;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM faculties";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Entity entity) {
        Faculties fc = cast(entity);
        
        String sql = "INSERT INTO faculties(id, name, abbreviation) " +
                "VALUES (?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            fc.getId(), fc.getName(), fc.getAbbreviation()
        });
        return rowNum;
    }
    
    @Override
    public int update(Entity entity) {
        Faculties fc = cast(entity);
        
        String sql = "UPDATE faculties SET name = ?, abbreviation = ? " +
                " WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            fc.getName(), fc.getAbbreviation(), fc.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Entity entity) {
        String sql = "DELETE FROM faculties WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            entity.getId()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler getResultSetHandler() {
        return handler;
    }

    private Faculties cast(Entity entity) {
        if (entity instanceof Faculties) {
            return (Faculties) entity;
        }
        throw new ClassCastException();
    }
}
