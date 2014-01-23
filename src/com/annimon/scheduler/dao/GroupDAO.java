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
public class GroupDAO implements IDAO<Groups> {
    
    private final IResultSetHandler<Groups> handler = new IResultSetHandler<Groups>() {

        @Override
        public Groups process(ResultSet rs) throws Exception {
            Groups gr = new Groups();
            gr.setId(rs.getInt(1));
            gr.setSpecialityId(rs.getInt(2));
            gr.setName(rs.getString(3));
            gr.setFormationYear(rs.getShort(4));
            gr.setStrength(rs.getShort(5));
            gr.setDepartmentId(rs.getInt(6));
            gr.setEducationFormId(rs.getShort(7));
            return gr;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM groups ORDER BY `name`";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Groups gr) {
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
    public int update(Groups gr) {
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
    public int delete(Groups gr) {
        String sql = "DELETE FROM groups WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            gr.getId()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler<Groups> getResultSetHandler() {
        return handler;
    }
}
