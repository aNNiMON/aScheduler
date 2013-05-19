package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.DAOKeeper;
import com.annimon.scheduler.data.Audiences;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.data.Groups;
import com.annimon.scheduler.data.Pairs;
import com.annimon.scheduler.data.Professors;
import com.annimon.scheduler.data.Subjects;
import com.annimon.scheduler.model.PairModel;
import com.annimon.scheduler.util.GUIUtils;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Форма редактирования пар.
 * @author aNNiMON
 */
public class PairsForm extends AbstractEntityForm {
    
    private JComboBox<String> dayComboBox, weekComboBox;
    private JSpinner numberSpinner;
    private JSpinner timeStartSpinner, timeEndSpinner;
    private JComboBox<String> audienceComboBox, subjectComboBox;
    private JComboBox<String> professorComboBox, groupComboBox;
    
    private Audiences[] audiences;
    private Subjects[] subjects;
    private Professors[] professors;
    private Groups[] groups;

    public PairsForm() {
        super(new PairModel(DAOKeeper.getPairDAO()));
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        audiences = getAudiencesArray();
        subjects = getSubjectsArray();
        professors = getProfessorsArray();
        groups = getGroupsArray();
        
        dataEditorPanel.add(GUIUtils.createLabel("День"));
        dayComboBox = new JComboBox<>();
        dayComboBox.setModel(new DefaultComboBoxModel(PairModel.DAY_NAMES));
        dataEditorPanel.add(dayComboBox);
        
        dataEditorPanel.add(GUIUtils.createLabel("Неделя"));
        weekComboBox = new JComboBox<>();
        weekComboBox.setModel(new DefaultComboBoxModel(PairModel.WEEK_NAMES));
        dataEditorPanel.add(weekComboBox);

        dataEditorPanel.add(GUIUtils.createLabel("Номер"));
        numberSpinner = new JSpinner();
        numberSpinner.setModel(new SpinnerNumberModel(Short.valueOf((short)1),
                Short.valueOf((short)0), Short.valueOf((short)10),
                Short.valueOf((short)1)));
        numberSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (numberSpinner.getValue() == null) return;
                
                // Если значение номера пары нулевое, то включаем выбор времени.
                short value = (short) numberSpinner.getValue();
                boolean timeComboBoxesEnable = (value == 0);
                
                timeStartSpinner.setEnabled(timeComboBoxesEnable);
                timeEndSpinner.setEnabled(timeComboBoxesEnable);
            }
        });
        dataEditorPanel.add(numberSpinner);
        
        dataEditorPanel.add(GUIUtils.createLabel("Начало"));
        timeStartSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeStartSpinner, "HH:mm:ss");
        timeStartSpinner.setEditor(timeEditor);
        dataEditorPanel.add(timeStartSpinner);
        
        dataEditorPanel.add(GUIUtils.createLabel("Конец"));
        timeEndSpinner = new JSpinner(new SpinnerDateModel());
        timeEditor = new JSpinner.DateEditor(timeEndSpinner, "HH:mm:ss");
        timeEndSpinner.setEditor(timeEditor);
        dataEditorPanel.add(timeEndSpinner);
        
        dataEditorPanel.add(GUIUtils.createLabel("Аудитория"));
        audienceComboBox = new JComboBox<>();
        String[] name = new String[audiences.length];
        for (int i = 0; i < audiences.length; i++) {
            name[i] = audiences[i].getFullNumber();
        }
        audienceComboBox.setModel(new DefaultComboBoxModel(name));
        dataEditorPanel.add(audienceComboBox);
        
        dataEditorPanel.add(GUIUtils.createLabel("Предмет"));
        subjectComboBox = new JComboBox<>();
        name = new String[subjects.length];
        for (int i = 0; i < subjects.length; i++) {
            name[i] = subjects[i].getName();
        }
        subjectComboBox.setModel(new DefaultComboBoxModel(name));
        dataEditorPanel.add(subjectComboBox);
        
        dataEditorPanel.add(GUIUtils.createLabel("Преподаватель"));
        professorComboBox = new JComboBox<>();
        name = new String[professors.length];
        for (int i = 0; i < professors.length; i++) {
            name[i] = professors[i].getName();
        }
        professorComboBox.setModel(new DefaultComboBoxModel(name));
        dataEditorPanel.add(professorComboBox);
        
        dataEditorPanel.add(GUIUtils.createLabel("Группа"));
        groupComboBox = new JComboBox<>();
        name = new String[groups.length];
        for (int i = 0; i < groups.length; i++) {
            name[i] = groups[i].getName();
        }
        groupComboBox.setModel(new DefaultComboBoxModel(name));
        dataEditorPanel.add(groupComboBox);
    }

    @Override
    protected void fillDataInEditorPanel(int rowSelected, JTable table) {
        numberSpinner.setValue(getValueAt(rowSelected, 1));
        timeStartSpinner.setValue(getValueAt(rowSelected, 2));
        timeEndSpinner.setValue(getValueAt(rowSelected, 3));
        
        String day = getValueAt(rowSelected, 4).toString();
        int index = 0;
        for (int i = 0; i < PairModel.DAY_NAMES.length; i++) {
            if (day.endsWith(PairModel.DAY_NAMES[i])) {
                index = i;
            }
        }
        dayComboBox.setSelectedIndex(index);
        
        String week = getValueAt(rowSelected, 5).toString();
        index = 0;
        for (int i = 0; i < PairModel.WEEK_NAMES.length; i++) {
            if (week.endsWith(PairModel.WEEK_NAMES[i])) {
                index = i;
            }
        }
        weekComboBox.setSelectedIndex(index);
        
        String audience = getValueAt(rowSelected, 6).toString();
        index = 0;
        for (int i = 0; i < audiences.length; i++) {
            if (audience.endsWith(audiences[i].getFullNumber())) {
                index = i;
            }
        }
        audienceComboBox.setSelectedIndex(index);
        
        String subject = getValueAt(rowSelected, 7).toString();
        index = 0;
        for (int i = 0; i < subjects.length; i++) {
            if (subject.endsWith(subjects[i].getAbbreviation())) {
                index = i;
            }
        }
        subjectComboBox.setSelectedIndex(index);
        
        String professor = getValueAt(rowSelected, 8).toString();
        index = 0;
        for (int i = 0; i < professors.length; i++) {
            if (professor.endsWith(professors[i].getName())) {
                index = i;
            }
        }
        professorComboBox.setSelectedIndex(index);
        
        String group = getValueAt(rowSelected, 9).toString();
        index = 0;
        for (int i = 0; i < groups.length; i++) {
            if (group.endsWith(groups[i].getName())) {
                index = i;
            }
        }
        groupComboBox.setSelectedIndex(index);
    }

    @Override
    protected Entity getEntity(int row, int id) {
        short number = (short) numberSpinner.getValue();
        Date startTime = null, endTime = null;
        if (number == 0) {
            startTime = (Date) timeStartSpinner.getValue();
            endTime = (Date) timeEndSpinner.getValue();
        }
        
        short day = (short) (dayComboBox.getSelectedIndex() + 1);
        int weekIndex = weekComboBox.getSelectedIndex() - 1;
        Short week = Short.valueOf((short) weekIndex);
        if (weekIndex < 0) week = null;
        
        int audienceIndex = audienceComboBox.getSelectedIndex();
        int subjectIndex = subjectComboBox.getSelectedIndex();
        int professorIndex = professorComboBox.getSelectedIndex();
        int groupIndex = groupComboBox.getSelectedIndex();
        
        Pairs pr = new Pairs();
        pr.setId(id);
        pr.setNumber(number);
        pr.setTimeBegin(startTime);
        pr.setTimeEnd(endTime);
        pr.setDay(day);
        pr.setWeek(week);
        pr.setAudienceId(audiences[audienceIndex].getId());
        pr.setSubjectId(subjects[subjectIndex].getId());
        pr.setProfessorId(professors[professorIndex].getId());
        pr.setGroupId(groups[groupIndex].getId());
        
        return pr;
    }
    
    private Audiences[] getAudiencesArray() {
        List<Entity> list = DAOKeeper.getAudienceDAO().select();
        Audiences[] array = new Audiences[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Entity entity = list.get(i);
            array[i] = (Audiences) entity;
        }
        return array;
    }
    
    private Subjects[] getSubjectsArray() {
        List<Entity> list = DAOKeeper.getSubjectDAO().select();
        Subjects[] array = new Subjects[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Entity entity = list.get(i);
            array[i] = (Subjects) entity;
        }
        return array;
    }
    
    private Professors[] getProfessorsArray() {
        List<Entity> list = DAOKeeper.getProfessorDAO().select();
        Professors[] array = new Professors[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Entity entity = list.get(i);
            array[i] = (Professors) entity;
        }
        return array;
    }
    
    private Groups[] getGroupsArray() {
        List<Entity> list = DAOKeeper.getGroupDAO().select();
        Groups[] array = new Groups[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Entity entity = list.get(i);
            array[i] = (Groups) entity;
        }
        return array;
    }
}
