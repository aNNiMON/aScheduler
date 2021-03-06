package com.annimon.scheduler.gui;

import com.annimon.scheduler.data.Entity;
import com.annimon.scheduler.model.EntityTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Макет формы для всех сущностей.
 * @author aNNiMON
 */
public abstract class AbstractEntityForm extends JDialog {
    
    private static final Dimension MAX_BUTTON_DIMENSION = new Dimension(85, 30);

    private JTable table;
    private JButton addButton, deleteButton, editButton;
    private JPanel commandsPanel, dataEditorPanel;
    
    private final EntityTableModel model;

    public AbstractEntityForm(EntityTableModel model) {
        this.model = model;
        setModal(true);
        initComponents();
    }
    
    public AbstractEntityForm(EntityTableModel model, String title) {
        this(model);
        setTitle(title);
    }
    
    private void initComponents() {
        table = new JTable();
        dataEditorPanel = new JPanel();
        commandsPanel = new JPanel();
        addButton = new JButton();
        deleteButton = new JButton();
        editButton = new JButton();
        JScrollPane tableScroll = new JScrollPane();
        
        // Каждая таблица должна иметь возможность сортировки и одиночного выбора.
        table.setAutoCreateRowSorter(true);
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new RowListener());
        tableScroll.setViewportView(table);
        getContentPane().add(tableScroll, BorderLayout.CENTER);
        
        // Панель данных заполняется в каждом классе-потомке.
        dataEditorPanel.setLayout(new GridLayout(0, 2));
        fillDataEditorPanel(dataEditorPanel);
        getContentPane().add(dataEditorPanel, BorderLayout.PAGE_END);
        
        // Панель команд для добавления/удаления/изменения данных едина для всех таблиц.
        commandsPanel.setLayout(new BoxLayout(commandsPanel, BoxLayout.PAGE_AXIS));
        
        addButton.setText("Добавить");
        addButton.setMaximumSize(MAX_BUTTON_DIMENSION);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                model.insert(getEntity(-1));
            }
        });
        commandsPanel.add(addButton);

        deleteButton.setText("Удалить");
        deleteButton.setMaximumSize(MAX_BUTTON_DIMENSION);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int rowSelected = table.getSelectedRow();
                if (rowSelected == -1) return;

                model.delete(table.convertRowIndexToModel(rowSelected), getEntity(rowSelected));
            }
        });
        commandsPanel.add(deleteButton);

        editButton.setText("Изменить");
        editButton.setMaximumSize(MAX_BUTTON_DIMENSION);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int rowSelected = table.getSelectedRow();
                if (rowSelected == -1) return;

                model.update(table.convertRowIndexToModel(rowSelected), getEntity(rowSelected));
            }
        });
        commandsPanel.add(editButton);

        getContentPane().add(commandsPanel, BorderLayout.LINE_END);

        pack();
    }
    
    /**
     * Заполнить панель изменения данных.
     * @param dataEditor JPanel с макетом GridLayout(0, 2)
     */
    protected abstract void fillDataEditorPanel(JPanel dataEditor);
    
    /**
     * Заполнить компоненты панели данных из таблицы.
     * @param rowSelected с какой строки таблицы брать данные
     */
    protected abstract void fillComponentsInEditorPanel(int rowSelected);
    
    /**
     * Получить новый объект Entity из информации панели данных.
     * @param row для какой строки таблицы делаются изменения
     * @param id идентификатор Entity
     * @return объект Entity
     */
    protected abstract Entity getEntity(int row, int id);
    
    protected Object getValueAt(int row, int column) {
        return table.getValueAt(row, table.convertColumnIndexToView(column));
    }
    
    private Entity getEntity(int rowSelected) {
        int id = 0;
        if (rowSelected != -1) {
            id = (int) getValueAt(rowSelected, 0);
        }
        
        return getEntity(rowSelected, id);
    }
    
    /** Слушатель события выбора строки таблицы. */
    private class RowListener implements ListSelectionListener {
        
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            
            int rowSelected = table.getSelectedRow();
            if (rowSelected == -1) return;
            
            // При выборе строки наполняем панель данных.
            fillComponentsInEditorPanel(rowSelected);
        }

    }
}
