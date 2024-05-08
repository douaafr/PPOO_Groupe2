package  model;

import java.util.*;
import java.io.Serializable;

public class FichePersonnage implements Serializable {

	private static final long serialVersionUID = 4032237744168435781L;
	public String name;
    public int idFiche;
    //public Vector<Conteneur> listContainers = new Vector<Conteneur>();
    public int age;
    public int pointDeVie;
    public Portrait portrait = new Portrait();
    public String biographie;
    public List<String> competences;
    public List<String> equipements;
    public List<Statistique> statistiques;

//    public FichePersonnage(Vector<Conteneur> lc) {
//    	listContainers = lc;
//    }
    
    public FichePersonnage() {
    }
    
    public FichePersonnage(String name, int idFiche) {
    	this.name= name;
    	this.idFiche= idFiche;
    }
    
    public FichePersonnage(String name) {
    	this.name =name;
    }
    
    public FichePersonnage(int idFiche) {
    	this.idFiche= idFiche;
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
    
    public void setAge(int a) {
    	this.age = a;
    }
    
    public int getAge() {
    	return this.age;
    }
    
    public void setPointDeVie(int pv) {
    	this.pointDeVie = pv;
    }
    
    public int getPointDeVie() {
    	return this.pointDeVie;
    }
    
    public void setPortrait(Portrait p) {
    	this.portrait = p;
    }
    
    public Portrait getPortrait() {
    	return this.portrait;
    }
    
    public void setBiographie(String bio) {
    	this.biographie = bio;
    }
    
    public String getBiographie() {
    	return this.biographie;
    }
    
    public void addCompetence(String competence) {
        if (competences == null) {
            competences = new ArrayList<>();
        }
        competences.add(competence);
    }

    public List<String> getCompetences() {
        return competences;
    }

    public void addEquipement(String equipement) {
        if (equipements == null) {
            equipements = new ArrayList<>();
        }
        equipements.add(equipement);
    }

    public List<String> getEquipements() {
        return equipements;
    }

    public void addStatistique(Statistique statistique) {
        if (statistiques == null) {
            statistiques = new ArrayList<>();
        }
        statistiques.add(statistique);
    }

    public List<Statistique> getStatistiques() {
        return statistiques;
    }

//    public void addContainer(Conteneur c) {
//        listContainers.add(c);
//    }
//
//    public Vector<Conteneur> getContainers() {
//        return this.listContainers;
//    }
//    
//    public Conteneur getContainer(int i) {
//    	return listContainers.get(i);
//    }
//
//    public void deleteContainer(int i) {
//        listContainers.remove(i);
//    }

//    public void changeLocation(Conteneur c1, Conteneur c2) {
//    	int tmpX = c1.posX;
//    	int tmpY = c1.posY;
//    	c1.posX = c2.posX;
//    	c1.posY = c2.posY;
//    	c2.posX = tmpX;
//    	c2.posY = tmpY;
//    }
//    
//    public void changeLocation(Portrait p, Conteneur c) {
//    	int tmpX = p.posX;
//    	int tmpY = p.posY;
//    	p.posX = c.posX;
//    	p.posY = c.posY;
//    	c.posX = tmpX;
//    	c.posY = tmpY;
//    }
}