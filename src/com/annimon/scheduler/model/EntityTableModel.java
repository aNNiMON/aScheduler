package com.annimon.scheduler.model;

import com.annimon.scheduler.dao.IDAO;
import com.annimon.scheduler.data.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Модель таблиц.
 * @author aNNiMON
 * @param <T>
 */
public abstract class EntityTableModel<T extends Entity> extends AbstractTableModel {

    private final IDAO<T> dao;
    private List<Entity> entityList;
    private String[] columnNames;
    private List<Object[]> itemList;

    public EntityTableModel(IDAO<T> dao) {
        this.dao = dao;
        entityList = dao.select();
    }
    
    public Object[] getRowObjects(int row) {
        return fillRow(row);
    }

    public T getEntity(int index) {
        return (T) entityList.get(index);
    }
    
    public void insert(T entity) {
        if (entity == null) return;
        
        int id = dao.insert(entity);
        // Если при вставке получен код -1, значит произошла ошибка или 
        // ограничение. Вызываем метод isExitOnInsertionError для обработки
        // таких случаев. Если он вернёт true, значит не нужно добавлять новые
        // данные в таблицу (к примеру, произошло обновление конфликтующих записей).
        if (id == -1 && isExitOnInsertionError(entity)) {
            return;
        }
        entity.setId(id);
        
        int row = entityList.size();
        entityList.add(entity);
        itemList.add(fillRow(row));
        fireTableRowsInserted(0, row);
    }
    
    public void delete(int row, T entity) {
        if (entity == null) return;
        
        dao.delete(entity);
        
        entityList.remove(row);
        itemList.remove(row);
        fireTableRowsDeleted(0, row);
    }
    
    public void update(int row, T entity) {
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
    
    /**
     * Заполнить названия столбцов таблицы.
     * @return массив строк с названиями столбцов
     */
    protected abstract String[] getColumnNames();

    /**
     * Заполнить строку данными.
     * @param index индекс строки
     * @return массив объектов, которыми заполнится строка таблицы
     */
    protected abstract Object[] fillRow(int index);
    
    /**
     * Метод, который нужно переопределить в производных классах, для обработки
     * события, когда при вставке получен ответ -1.
     * @param entity объект Entity, с которым возникли проблемы
     * @return true - выйти из функции обновления, false - игнорировать
     */
    protected boolean isExitOnInsertionError(T entity) {
        return false;
    }
    
    protected void initTableModel() {
        columnNames = getColumnNames();
        fillList(entityList.size());
    }
    
    /**
     * Принудительно обновить данные таблицы из БД.
     */
    protected void refreshDataFromDAO() {
        entityList = dao.select();
        fillList(entityList.size());
        fireTableDataChanged();
    }

    private void fillList(int rowCount) {
        itemList = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            itemList.add(fillRow(i));
        }
    }
}
