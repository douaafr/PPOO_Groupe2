package model;

import java.util.*;

/**
 * 
 */
public class IntItem {

	 public String name;
	 public int field;
 
	 public IntItem() {
	        
	        
	    }
	 public IntItem(String name, int field) {
	        this.name = name;
	        this.field = field;
	    }
	
    
  
    public void setName(String name) {
    	this.name = name ;
    
    }

    /**
     * 
     */
    public String getName() {
       return name;
    }

    /**
     * 
     */
    public void setField(int field) {
        this.field = field;
    }

    /**
     * 
     */
    public int getField() {
        return field;
    }

    /**
     * 
     */
    public void changeName(String newName) {
        this.name = newName;
    }

}