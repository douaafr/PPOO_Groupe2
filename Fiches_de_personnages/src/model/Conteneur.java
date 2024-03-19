package model;

import java.util.*;

public class Conteneur {	

    public int posX;
    public int posY;
    public Vector<Item> listItems;
    
    public Conteneur(int x, int y, Vector<Item> li) {
    	this.posX = x;
    	this.posY = y;
    	this.listItems = li;
    }
    
    public void setPos(int x, int y) {
    	this.posX = x;
    	this.posY = y;
    }
    
    public int getPosX() {
    	return this.posX;
    }
    
    public int getPosY() {
    	return this.posY;
    }
    
    public Item getItem(int i) {
    	return listItems.get(i);
    }
    
    public void addItem(Item it) {
        listItems.add(it);
    }

    public void deleteItem(int i) {
        listItems.remove(i);
    }
}