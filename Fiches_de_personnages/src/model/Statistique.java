package model;

import java.io.Serializable;

public class Statistique implements Serializable{

	private static final long serialVersionUID = 4920305279045022742L;
	private String name;
    private int value;

    public Statistique(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
