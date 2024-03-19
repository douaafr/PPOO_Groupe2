package model;

import java.util.*;

public class Item {

    public String name;

    public Item(String n) {
    	this.name = n;
    }
    
    public Vector<StringItem> listStringItem;
    public Vector<IntItem> listIntItem;
    public Vector<BooleanItem> listBooleanItem;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addStringSousItem(StringItem st) {
        listStringItem.add(st);
    }
    
    public void addIntSousItem(IntItem ii) {
        listIntItem.add(ii);
    }
    
    public void addBooleanSousItem(BooleanItem bi) {
        listBooleanItem.add(bi);
    }

    public void deleteStringSousItem(int i) {
        listStringItem.remove(i);
    }
    
    public void deleteIntSousItem(int i) {
        listIntItem.remove(i);
    }
    
    public void deleteBooleanSousItem(int i) {
    	listBooleanItem.remove(i);
    }
}