package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Departments;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class DepartmentDAO implements IDAO<Departments> {
    
    private final IResultSetHandler<Departments> handler = new IResultSetHandler<Departments>() {

        @Override
        public Departments process(ResultSet rs) throws Exception {
            Departments dp = new Departments();
            dp.setId(rs.getInt(1));
            dp.setName(rs.getString(2));
            dp.setFacultyId(rs.getInt(3));
            return dp;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM departments ORDER BY `name`";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Departments dp) {
        String sql = "INSERT INTO departments(id, name, faculty) " +
                "VALUES (?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            dp.getId(), dp.getName(), dp.getFacultyId()/*dp.getFaculty().getId()*/
        });
        return rowNum;
    }
    
    @Override
    public int update(Departments dp) {
        String sql = "UPDATE departments SET name = ?, faculty = ? " +
                " WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            dp.getName(), dp.getFacultyId(), dp.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Departments dp) {
        String sql = "DELETE FROM departments WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            dp.getId()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler<Departments> getResultSetHandler() {
        return handler;
    }
}
