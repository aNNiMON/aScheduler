package com.annimon.scheduler.model;

import com.annimon.scheduler.dao.IDAO;
import com.annimon.scheduler.data.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Модель таблиц.
 * @author aNNiMON
 */
public abstract class EntityTableModel extends AbstractTableModel {

    private final IDAO dao;
    private final List<Entity> entityList;
    private String[] columnNames;
    private List<Object[]> itemList;

    public EntityTableModel(IDAO dao) {
        this.dao = dao;
        entityList = dao.select();
    }

    public Entity getEntity(int index) {
        return entityList.get(index);
    }
    
    public void insert(Entity entity) {
        if (entity == null) return;
        
        int id = dao.insert(entity);
        entity.setId(id);
        
        int row = entityList.size();
        entityList.add(entity);
        itemList.add(fillRow(row));
        fireTableRowsInserted(0, row);
    }
    
    public void delete(int row, Entity entity) {
        if (entity == null) return;
        
        dao.delete(entity);
        
        entityList.remove(row);
        itemList.remove(row);
        fireTableRowsDeleted(0, row);
    }
    
    public void update(int row, Entity entity) {
        if (entity == null) return;
        
        dao.update(entity);
        
        entityList.set(row, entity);
        itemList.set(row, fillRow(row));
        fireTableRowsUpdated(row, row);
    }
    
    @Override
    public Object getValueAt(int rowindex, int columnindex) {
        Object[] row = itemList.get(rowindex);
        return row[columnindex];
    }

    @Override
    public int getRowCount() {
        return itemList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int param) {
        return columnNames[param];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    protected abstract String[] getColumnNames();

    protected abstract Object[] fillRow(int index);
    
    protected void initTableModel() {
        columnNames = getColumnNames();
        fillList(entityList.size());
    }

    private void fillList(int rowCount) {
        itemList = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            itemList.add(fillRow(i));
        }
    }
}
