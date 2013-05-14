package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Departments;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Faculties;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class DepartmentsDAO implements IDAO {
    
    private final IResultSetHandler handler = new IResultSetHandler() {

        @Override
        public Entity process(ResultSet rs) throws Exception {
            Departments dp = new Departments();
            dp.setId(rs.getInt(1));
            dp.setName(rs.getString(2));
            dp.setFacultyId(rs.getInt(3));
            //int facultyId = rs.getInt(3); 
            //dp.setFaculty(getFacultyFromId(facultyId));
            return dp;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM departments";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Entity entity) {
        Departments dp = cast(entity);
        
        String sql = "INSERT INTO departments(id, name, faculty) " +
                "VALUES (?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            dp.getId(), dp.getName(), dp.getFacultyId()/*dp.getFaculty().getId()*/
        });
        return rowNum;
    }
    
    @Override
    public int update(Entity entity) {
        Departments dp = cast(entity);
        
        String sql = "UPDATE departments SET name = ?, faculty = ? " +
                " WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            dp.getName(), dp.getFacultyId()/*dp.getFaculty().getId()*/, dp.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Entity entity) {
        String sql = "DELETE FROM departments WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            entity.getId()
        });
        return rowNum;
    }

    private Departments cast(Entity entity) {
        if (entity instanceof Departments) {
            return (Departments) entity;
        }
        throw new ClassCastException();
    }
    
    private Faculties getFacultyFromId(Integer id) {
        return null;
    }
}
