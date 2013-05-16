package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Groups;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class GroupDAO implements IDAO {
    
    private final IResultSetHandler handler = new IResultSetHandler() {

        @Override
        public Entity process(ResultSet rs) throws Exception {
            Groups gr = new Groups();
            gr.setId(rs.getInt(1));
            gr.setSpecialityId(rs.getInt(2));
            gr.setName(rs.getString(3));
            gr.setFormationYear(rs.getDate(4));
            gr.setStrength(rs.getShort(5));
            gr.setDepartmentId(rs.getInt(6));
            gr.setEducationFormId(rs.getInt(7));
            return gr;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM groups";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Entity entity) {
        Groups gr = cast(entity);
        
        String sql = "INSERT INTO groups(id, speciality, name, formation_year, " + 
                "strength, department, education_form) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            gr.getId(), gr.getSpecialityId(), gr.getName(), gr.getFormationYear(),
            gr.getStrength(), gr.getDepartmentId(), gr.getEducationFormId()
        });
        return rowNum;
    }
    
    @Override
    public int update(Entity entity) {
        Groups gr = cast(entity);
        
        String sql = "UPDATE groups SET speciality = ?, name = ?, " +
                "formation_year = ?, strength = ?, department = ?, " +
                "education_form = ? WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            gr.getSpecialityId(), gr.getName(),
            gr.getFormationYear(), gr.getStrength(), gr.getDepartmentId(),
            gr.getEducationFormId(), gr.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Entity entity) {
        String sql = "DELETE FROM groups WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            entity.getId()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler getResultSetHandler() {
        return handler;
    }

    private Groups cast(Entity entity) {
        if (entity instanceof Groups) {
            return (Groups) entity;
        }
        throw new ClassCastException();
    } 
}
