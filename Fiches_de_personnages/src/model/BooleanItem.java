package model;
import java.util.*;

/**
 * 
 */
public class BooleanItem {

	public String name;
    public boolean field;
    
    public BooleanItem(	) { 
    	
    }
    public BooleanItem(	String name, boolean field) {
    	this.name = name;
    	this.field = field;
    }
    

    /**
     * 
     */
    


    
    public void setName(String name) {
        this.name =name;
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
    public void setField(boolean field) {
        this.field = field;
    }

    /**
     * 
     */
    public Boolean getField() {
        return field;
    }

    
    public void changeName(String newName) {
      this.name = newName;
    }

}