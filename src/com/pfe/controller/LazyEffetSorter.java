package com.pfe.controller;


import java.lang.reflect.Field;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

import com.pfe.model.DetailEffet;
 
public class LazyEffetSorter implements Comparator<DetailEffet> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazyEffetSorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    public int compare(DetailEffet effet1, DetailEffet effet2) {
        try {
        	Field field = DetailEffet.class.getDeclaredField(this.sortField);
            field.setAccessible(true);
            Object value1 = field.get(effet1);
            Object value2 = field.get(effet2);
            @SuppressWarnings("unchecked")
			int value = ((Comparable<Object>)value1).compareTo(value2);
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException();
        }
    }
}