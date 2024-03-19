package  model;

import java.util.*;

public class FichePersonnage {

    public String name;
    public Vector<Conteneur> listContainers;
    public Portrait portrait;

    public FichePersonnage(Vector<Conteneur> lc) {
    	listContainers = lc;
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

    public void addContainer(Conteneur c) {
        listContainers.add(c);
    }

    public List<Conteneur> getContainers() {
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