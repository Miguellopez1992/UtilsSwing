/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.utilsswing.modeljtable.implementation;

import com.utilsswing.modeljtable.annotations.ColumnName;
import com.utilsswing.modeljtable.interfaces.Name;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Miguel Lopez
 * @param <T>
 */
public class ArrayName<T> implements Name{
    private List<String> names;
    private List<Class> clazz;
    private List<Class> wrapperClazz;
    private List<Boolean> edit; 
    private Map<Integer,String> nameFields; 
    
    /**
     *
     * @param object
     */
    public ArrayName(T object) {
        loadProperty(object);
        exception();
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<String> getName() {
        return names;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Class> getClazz() {
        return clazz;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Boolean> getEdit() {
        return edit;
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Class> getWrapperClazz() {
        return wrapperClazz;
    }
        
    /**
     *
     * @return
     */
    @Override
    public Map<Integer, String> getNameFields() {
        return nameFields;
    }

    private void loadProperty(T object) {
        Field[] field = object.getClass().getDeclaredFields();
        int i = 0;
        for (Field f : field) {
            isColumnName(f);
        }
    }

    private void isColumnName(Field f) {
        Annotation[] annotations = f.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof ColumnName) {
                createInstance();
                ColumnName columnName = (ColumnName) annotation;
                names.add(columnName.name());
                clazz.add(f.getType());
                wrapperClazz.add(_clazz(f.getType()));
                nameFields.put(names.size() - 1, f.getName());
                edit.add(columnName.edit());
            }
        }
    }
    private Class _clazz(Class clazz){
        switch(clazz.getName()){
            case "boolean": return java.lang.Boolean.class;
            case "int": return java.lang.Integer.class;
            case "float": return java.lang.Float.class;
            case "double": return java.lang.Double.class;
            case "long": return java.lang.Long.class;
            case "byte" : return java.lang.Byte.class;
            case "char": return java.lang.Character.class;
            default : return clazz;
        }
    }

    private void createInstance() {
       if(names==null&&clazz==null){
           names=new ArrayList();
           clazz=new ArrayList();
           wrapperClazz=new ArrayList();
           nameFields = new HashMap();
           edit = new ArrayList();
       }        
    }

    private void exception() {
        if(names==null&&clazz==null){
            throw new java.lang.NullPointerException("No exist ColumnName annotations");
        } 
    }
    
}
