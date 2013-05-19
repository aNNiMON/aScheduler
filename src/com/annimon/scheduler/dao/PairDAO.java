package com.annimon.scheduler.dao;

import com.annimon.scheduler.data.Pairs;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.util.DBConnection;
import com.annimon.scheduler.util.IResultSetHandler;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class PairDAO implements IDAO {
    
    private final IResultSetHandler handler = new IResultSetHandler() {

        @Override
        public Entity process(ResultSet rs) throws Exception {
            Pairs pr = new Pairs();
            pr.setId(rs.getInt(1));
            pr.setNumber(rs.getShort(2));
            pr.setTimeBegin(rs.getTime(3));
            pr.setTimeEnd(rs.getTime(4));
            pr.setDay(rs.getShort(5));
            pr.setWeek(rs.getShort(6), rs.wasNull());
            pr.setAudienceId(rs.getInt(7));
            pr.setSubjectId(rs.getInt(8));
            pr.setProfessorId(rs.getInt(9));
            pr.setGroupId(rs.getInt(10));
            return pr;
        }
    };

    @Override
    public List<Entity> select() {
        String sql = "SELECT * FROM pairs";
        List<Entity> resultList = DBConnection.getInstance().executeQuery(sql, null, handler);
	return resultList;
    }

    @Override
    public int insert(Entity entity) {
        Pairs pr = cast(entity);
        
        String sql = "INSERT INTO pairs(id, number, time_begin, time_end, day, " + 
                "week, audience, subject, professor, `group`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            pr.getId(), pr.getNumber(), pr.getTimeBegin(), pr.getTimeEnd(), pr.getDay(),
            pr.getWeek(), pr.getAudienceId(), pr.getSubjectId(), pr.getProfessorId(),
            pr.getGroupId()
        });
        return rowNum;
    }
    
    @Override
    public int update(Entity entity) {
        Pairs pr = cast(entity);
        
        String sql = "UPDATE pairs SET number = ?, time_begin = ?, time_end = ?, " +
                "day = ?, week = ?, audience = ?, subject = ?, " +
                "professor = ?, `group` = ? WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            pr.getNumber(), pr.getTimeBegin(), pr.getTimeEnd(),
            pr.getDay(), pr.getWeek(), pr.getAudienceId(), pr.getSubjectId(),
            pr.getProfessorId(),  pr.getGroupId(), pr.getId()
        });
        return rowNum;
    }

    @Override
    public int delete(Entity entity) {
        String sql = "DELETE FROM pairs WHERE id = ?";
        int rowNum = DBConnection.getInstance().executeUpdate(sql, new Object[] {
            entity.getId()
        });
        return rowNum;
    }
    
    @Override
    public IResultSetHandler getResultSetHandler() {
        return handler;
    }

    private Pairs cast(Entity entity) {
        if (entity instanceof Pairs) {
            return (Pairs) entity;
        }
        throw new ClassCastException();
    } 
}
