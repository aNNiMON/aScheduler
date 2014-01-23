package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Subjects;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class SubjectDAO implements IDAO<Subjects> {
    
    private final IResultSetHandler<Subjects> handler = new IResultSetHandler<Subjects>() {

        @Override
        public Subjects process(ResultSet rs) throws Exception {
            Subjects sb = new Subjects();
            sb.setId(rs.getInt(1));
            sb.setName(rs.getString(2));
            sb.setAbbreviation(rs.getString(3));
            return sb;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM subjects ORDER BY `name`";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Subjects sb) {
        String sql = "INSERT INTO subjects(id, name, abbreviation) " +
                "VALUES (?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            sb.getId(), sb.getName(), sb.getAbbreviation()
        });
        return rowNum;
    }
    
    @Override
    public int update(Subjects sb) {
        String sql = "UPDATE subjects SET name = ?, abbreviation = ? " +
                " WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            sb.getName(), sb.getAbbreviation(), sb.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Subjects sb) {
        String sql = "DELETE FROM subjects WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            sb.getId()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler<Subjects> getResultSetHandler() {
        return handler;
    }
}
