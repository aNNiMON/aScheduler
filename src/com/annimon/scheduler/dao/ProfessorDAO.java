package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Professors;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class ProfessorDAO implements IDAO<Professors> {
    
    private final IResultSetHandler<Professors> handler = new IResultSetHandler<Professors>() {

        @Override
        public Professors process(ResultSet rs) throws Exception {
            Professors pr = new Professors();
            pr.setId(rs.getInt(1));
            pr.setLastname(rs.getString(2));
            pr.setFirstname(rs.getString(3));
            pr.setMiddlename(rs.getString(4));
            return pr;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM professors ORDER BY `lastname`";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Professors pr) {
        String sql = "INSERT INTO professors(id, lastname, firstname, middlename) " +
                "VALUES (?, ?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            pr.getId(), pr.getLastname(), pr.getFirstname(), pr.getMiddlename()
        });
        return rowNum;
    }
    
    @Override
    public int update(Professors pr) {
        String sql = "UPDATE professors SET lastname = ?, firstname = ?, " +
                " middlename = ? WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            pr.getLastname(), pr.getFirstname(), pr.getMiddlename(), pr.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Professors pr) {
        String sql = "DELETE FROM professors WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            pr.getId()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler<Professors> getResultSetHandler() {
        return handler;
    }
}
