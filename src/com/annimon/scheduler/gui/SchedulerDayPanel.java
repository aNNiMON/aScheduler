package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.AudienceDAO;
import com.annimon.scheduler.dao.ProfessorDAO;
import com.annimon.scheduler.dao.SubjectDAO;
import com.annimon.scheduler.data.Audiences;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Pairs;
import com.annimon.scheduler.data.Professors;
import com.annimon.scheduler.data.Subjects;
import com.annimon.scheduler.util.DBConnection;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Панель для представления одного дня, включающего множество пар.
 * @author aNNiMON
 */
public class SchedulerDayPanel extends JPanel {

    public SchedulerDayPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    }
    
    public void addSchedulerPairPanel(Pairs[] pairs) {
        if (pairs.length == 0) add(createEmptyPair());
        
        for (int i = 0; i < pairs.length; i++) {
            add(createSchedulerPair(pairs[i]));
        }
    }
    
    private SchedulerPairPanel createSchedulerPair(Pairs pairs) {
        short number = pairs.getNumber();
        Date timeBegin = pairs.getTimeBegin();
        Date timeEnd = pairs.getTimeEnd();

        String numberStr = String.valueOf(number);
        String timeEndStr = "";
        if ( (timeBegin != null) && (timeEnd != null) && (number == 0)) {
            numberStr = timeBegin.toString();
            timeEndStr = timeEnd.toString();
        }
        
        SchedulerPairPanel pairPanel = new SchedulerPairPanel(numberStr, timeEndStr);
        fillPairPanel(pairPanel, pairs);
        return pairPanel;
    }
    
    private void fillPairPanel(SchedulerPairPanel panel, Pairs pair) {
        Short week = pair.getWeek();
        
        String subject;
        String sql = "SELECT * FROM subjects WHERE id = ?";
        List<Entity> list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            pair.getSubjectId()
        }, new SubjectDAO().getResultSetHandler());
        subject = ((Subjects) list.get(0)).getAbbreviation();
        
        String audience;
        sql = "SELECT * FROM audiences WHERE id = ?";
        list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            pair.getAudienceId()
        }, new AudienceDAO().getResultSetHandler());
        Audiences au = (Audiences) list.get(0);
        audience = " " + au.getNumber();
        if (au.getHousing() != 0) audience += " " + au.getHousing();
        
        String professor;
        sql = "SELECT * FROM professors WHERE id = ?";
        list = DBConnection.getInstance().executeQuery(sql, new Object[] {
            pair.getAudienceId()
        }, new ProfessorDAO().getResultSetHandler());
        professor = ((Professors) list.get(0)).getName();
        
        panel.addSubject(week, subject, audience, professor);
    }
    
    private SchedulerPairPanel createEmptyPair() {
        SchedulerPairPanel pairPanel = new SchedulerPairPanel("", "");
        pairPanel.addSubject(null, "---", "---", "---");
        return pairPanel;
    }

}