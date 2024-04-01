package  model;

import java.util.*;
import java.io.Serializable;

public class FichePersonnage implements Serializable {

    public String name;
    public int idFiche;
    public Vector<Conteneur> listContainers = new Vector<Conteneur>();
    public Portrait portrait;

    public FichePersonnage(Vector<Conteneur> lc) {
    	listContainers = lc;
    }
    
    public FichePersonnage(String name, int idFiche) {
    	this.name= name;
    	this.idFiche= idFiche;
    }
    
    public FichePersonnage(String name) {
    	this.name =name;
    }
    
    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void changeName(String n) {
        this.name = n;
    }
    
    public void setIdFiche(int id) {
    	this.idFiche = id;
    }
    
    public int getIdFiche() {
    	return idFiche;
    }
    
    public void setPortrait(Portrait p) {
    	this.portrait = p;
    }
    
    public Portrait getPortrait() {
    	return this.portrait;
    }

    public void addContainer(Conteneur c) {
        listContainers.add(c);
    }

    public Vector<Conteneur> getContainers() {
        return this.listContainers;
    }
    
    public Conteneur getContainer(int i) {
    	return listContainers.get(i);
    }

    public void deleteContainer(int i) {
        listContainers.remove(i);
    }

    public void changeLocation(Conteneur c1, Conteneur c2) {
    	int tmpX = c1.posX;
    	int tmpY = c1.posY;
    	c1.posX = c2.posX;
    	c1.posY = c2.posY;
    	c2.posX = tmpX;
    	c2.posY = tmpY;
    }
    
}