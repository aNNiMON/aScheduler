package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Groups;
import com.annimon.scheduler.data.Pairs;
import com.annimon.scheduler.util.DBConnection;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Панель для представления кафедры (включает в себя список групп).
 * @author aNNiMON
 */
public class SchedulerDepartmentPanel extends JPanel {
    
    public SchedulerDepartmentPanel() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    }
    
    public final void addSchedulerGroupPanel(Groups groups) {
        JPanel groupPanel = new JPanel();
        JLabel groupLabel = new JLabel();

        groupPanel.setLayout(new BorderLayout());
        groupLabel.setText(groups.getName());
        groupPanel.add(groupLabel, BorderLayout.PAGE_START);
        groupPanel.add(createScheduleTableDay(groups), BorderLayout.CENTER);

        add(groupPanel);
    }
    
    /*
     * Получить список дней с расписаниес для указанной группы и заполнить панель расписания.
     */
    private SchedulerGroupPanel createScheduleTableDay(Groups groups) {
        SchedulerGroupPanel groupPanel = new SchedulerGroupPanel();
        for (int day = 1; day <= 6; day++) {
            groupPanel.addSchedulerDayPanel(getPairsAtDay(groups, day));
        }
        return groupPanel;
    }
    
    /*
     * Получить список пар в выбранный день.
     */
    private Pairs[] getPairsAtDay(Groups group, int day) {
        String sql = "SELECT * FROM pairs WHERE `group` = ? AND `day` = ? ORDER BY `number`";
        List<Entity> pairsList = DBConnection.getInstance().executeQuery(sql, new Object[] {
            group.getId(), day
        }, DAOKeeper.getPairDAO().getResultSetHandler());
        
        final int size = pairsList.size();
        Pairs[] pairs = new Pairs[size];
        for (int i = 0; i < size; i++) {
            pairs[i] = (Pairs) pairsList.get(i); 
        }

        return pairs;
    }

}
