/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utilsswing.modeljtable.implementation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import com.utilsswing.modeljtable.interfaces.Name;
/**
 *
 * @author Miguel Lopez
 * @param <T>
 */
public class ModelJTable<T> extends AbstractTableModel{
        
    private List<T> row;
    private List<String> column;
    private List<Boolean> edit;
    private List<List<Boolean>> editTables;
    private List<Class> clazz; 
    private Name aName;
    private String getNameMethod;
    private String nameField;
    private T object;
    
    /**
     *
     * @return
     */
    public Class getGenericClass(){
        return object.getClass();
    }
        
    /**
     *
     * @param object
     */
    public ModelJTable(T object) {
        this.object = object;
        aName=new ArrayName(object);
        this.row = new ArrayList();
        this.column = aName.getName();
        this.clazz = aName.getWrapperClazz();
        this.edit = aName.getEdit();
        this.editTables = new ArrayList();
    }

    /**
     *
     * @param row
     * @param object
     */
    public ModelJTable(List<T> row, T object) {
        this.object = object;
        aName=new ArrayName(object);
        this.row = row;
        this.column = aName.getName();
        this.clazz = aName.getWrapperClazz();
        this.edit = aName.getEdit();
        this.editTables = new ArrayList();
    }
    
    /**
     *
     * @param object
     */
    public void addRow(T object){
        this.row.add(object);
        this.editTables.add(edit);
    }
    
    /**
     *
     * @param editColumn
     */
    public void setEditColumn(List<Boolean> editColumn){
        edit=editColumn;
        if (this.editTables!=null) {
            for (int i = 0; i < this.editTables.size(); i++) {
                this.editTables.set(i, editColumn);
            }
        }
    }
    
    /**
     *
     * @param indexRow
     * @param indexColumn
     * @param edit
     */
    public void setEditColumnRow(int indexRow,int indexColumn,boolean edit){
        this.editTables.get(indexRow).set(indexColumn,edit);
    }
    
    @Override
    public int getRowCount() {
        return row.size();
    }

    @Override
    public int getColumnCount() {
        return column.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return column.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return clazz.get(columnIndex);
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        object=row.get(rowIndex);
        nameField=(String) aName.getNameFields().get(columnIndex);
        try {
            if(clazz.get(columnIndex).getSimpleName().equalsIgnoreCase("boolean")){
                getNameMethod="is"+String.valueOf(nameField.charAt(0)).toUpperCase()
                    +nameField.substring(1);
            }else{
                getNameMethod="get"+String.valueOf(nameField.charAt(0)).toUpperCase()
                    +nameField.substring(1);
            }
            Method method = object.getClass().getDeclaredMethod(getNameMethod,null);
            return method.invoke(object, null);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ModelJTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return this.editTables.get(rowIndex).get(columnIndex);
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        object=row.get(rowIndex);
        nameField=(String) aName.getNameFields().get(columnIndex);
        String setNameMethod = "set" + String.valueOf(nameField.charAt(0)).toUpperCase()
                + nameField.substring(1);
        try {
            Method method = object.getClass().getMethod(setNameMethod,((Class)aName.getClazz().get(columnIndex)));
            method.invoke(object,aValue);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ModelJTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
