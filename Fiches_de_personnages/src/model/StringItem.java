package model;

public class StringItem {

	 public String name;
	 public String field;

	 public StringItem() {
	        
	 }
	 
	 public StringItem(String name, String field) {
	        this.name = name;
	        this.field = field;
	 }
	 
	 public void setName(String name) {
        this.name = name;
	 }

    public String getName() {
        return name;
    }

    public void setField(String field) {
        this.field = field;
    }
 
    public String getField() {
        return field;
    }

    public void changeName(String newName ) {
        this.name = newName;
    }
}
