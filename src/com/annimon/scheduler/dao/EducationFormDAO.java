package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.EducationForms;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class EducationFormDAO implements IDAO<EducationForms> {
    
    private final IResultSetHandler<EducationForms> handler = new IResultSetHandler<EducationForms>() {

        @Override
        public EducationForms process(ResultSet rs) throws Exception {
            EducationForms ef = new EducationForms();
            ef.set_Id(rs.getShort(1));
            ef.setType(rs.getString(2));
            return ef;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM education_forms ORDER BY `type`";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(EducationForms ef) {
        String sql = "INSERT INTO education_forms(id, type) " +
                "VALUES (?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            ef.getId(), ef.getType()
        });
        return rowNum;
    }
    
    @Override
    public int update(EducationForms ef) {
        String sql = "UPDATE education_forms SET type = ? WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            ef.getType(), ef.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(EducationForms ef) {
        String sql = "DELETE FROM education_forms WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            ef.get_Id()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler<EducationForms> getResultSetHandler() {
        return handler;
    }
}
