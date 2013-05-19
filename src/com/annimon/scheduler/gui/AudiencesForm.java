package com.annimon.scheduler.gui;

import com.annimon.scheduler.dao.AudienceDAO;
import com.annimon.scheduler.data.Audiences;
import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.model.AudienceModel;
import com.annimon.scheduler.util.GUIUtils;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;

/**
 * Форма редактирования аудиторий.
 * @author aNNiMON
 */
public class AudiencesForm extends AbstractEntityForm {
    
    private JSpinner capacitySpinner, housingSpinner;
    private JSpinner numberSpinner;
    private JComboBox typeComboBox;

    public AudiencesForm() {
        super(new AudienceModel(new AudienceDAO()));
    }

    @Override
    protected void fillDataEditorPanel(JPanel dataEditorPanel) {
        dataEditorPanel.add(GUIUtils.createLabel("Номер"));
        numberSpinner = new JSpinner();
        numberSpinner.setModel(GUIUtils.createShortSpinnerModel());
        dataEditorPanel.add(numberSpinner);

        dataEditorPanel.add(GUIUtils.createLabel("Тип"));
        typeComboBox = new JComboBox();
        typeComboBox.setModel(new DefaultComboBoxModel(AudienceModel.AUDIENCE_TYPE));
        dataEditorPanel.add(typeComboBox);

        dataEditorPanel.add(GUIUtils.createLabel("Корпус"));
        housingSpinner = new JSpinner();
        housingSpinner.setModel(GUIUtils.createShortSpinnerModel());
        dataEditorPanel.add(housingSpinner);

        dataEditorPanel.add(GUIUtils.createLabel("Вместимость"));
        capacitySpinner = new JSpinner();
        capacitySpinner.setModel(GUIUtils.createShortSpinnerModel());
        dataEditorPanel.add(capacitySpinner);
    }

    @Override
    protected void fillDataInEditorPanel(int rowSelected, JTable table) {
        numberSpinner.setValue(getValueAt(rowSelected, 1));
        String type = (String) getValueAt(rowSelected, 2);
        typeComboBox.setSelectedIndex(type.equals(AudienceModel.AUDIENCE_TYPE[0]) ? 0 : 1);
        housingSpinner.setValue(getValueAt(rowSelected, 3));
        capacitySpinner.setValue(getValueAt(rowSelected, 4));
    }

    @Override
    protected Entity getEntity(int row, int id) {
        short number = (short) numberSpinner.getValue();
        short type = (short) typeComboBox.getSelectedIndex();
        short housing = (short) housingSpinner.getValue();
        short capacity = (short) capacitySpinner.getValue();

        Audiences au = new Audiences();
        au.setId(id);
        au.setNumber(number);
        au.setType(type);
        au.setHousing(housing);
        au.setCapacity(capacity);
        
        return au;
    }
}
