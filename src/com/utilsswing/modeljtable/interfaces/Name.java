package com.utilsswing.modeljtable.interfaces;

import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miguel Lopez
 */
public interface Name {

    /**
     *
     * @return
     */
    public List<String> getName();

    /**
     *
     * @return
     */
    public List<Class> getClazz();

    /**
     *
     * @return
     */
    public List<Boolean> getEdit();

    /**
     *
     * @return
     */
    public List<Class> getWrapperClazz();

    /**
     *
     * @return
     */
    public Map<Integer, String> getNameFields();
}
