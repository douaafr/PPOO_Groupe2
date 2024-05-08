package model;

public class TestFiche {
	public static void main(String[] args) {
		FichePersonnage fiche1 = new FichePersonnage("Perso1", 1);
		Portrait p = new Portrait();
//		IntItem intI1 = new IntItem();
//		StringItem stringI1 = new StringItem();
//		BooleanItem booleanI1 = new BooleanItem();
//		StringItem stringI2 = new StringItem();
//		BooleanItem booleanI2 = new BooleanItem();
//		
//		Item bio = new Item("Biographie");
//		Item comp = new Item("Compétences");
//		Item equip = new Item("Equipements");
//		
//		intI1.setName("niveau");
//		intI1.setField(15);
//		booleanI1.setName("épée");
//		booleanI1.setField(false);
//		booleanI2.setName("bouclier");
//		booleanI2.setField(true);
//		stringI2.setName("combat");
//		stringI2.setField("manie les armes blanches");
//		
//		comp.addStringSousItem(stringI2);
//		comp.addIntSousItem(intI1);
//		
//		equip.addBooleanSousItem(booleanI1);
//		equip.addBooleanSousItem(booleanI2);
//		
//		bio.addStringSousItem(stringI1);
//		
//		Conteneur c1 = new Conteneur(0, 0);
//		Conteneur c2 = new Conteneur(0, 1);
//		
//		c1.addItem(bio);
//		c2.addItem(equip);
//		c2.addItem(comp);
//		
//		fiche1.setPortrait(p);
//		fiche1.addContainer(c1);
//		fiche1.addContainer(c2);
		
		fiche1.addCompetence("manie les armes blanches");
		fiche1.addEquipement("épée");
		fiche1.addEquipement("bouclier");
		Statistique stat1 = new Statistique("force", 3);
		Statistique stat2 = new Statistique("agilité", 2);
		fiche1.addStatistique(stat1);
		fiche1.addStatistique(stat2);
		
        System.out.println("Informations de la fiche personnage :");
        System.out.println("	Nom du personnage : " + fiche1.getName());
        System.out.println("	ID de la fiche : " + fiche1.getIdFiche());
        //p.addPhoto();
        System.out.println("	Chemin de la photo : " + p.getPath());
        System.out.println("\nCompétences :");
        if(fiche1.getCompetences() != null) {
        	for(String c : fiche1.getCompetences()) {
        		System.out.println(c);
        	}
        }
        System.out.println("\nEquipements :");
        if(fiche1.getEquipements() != null) {
        	for(String e : fiche1.getEquipements()) {
        		System.out.println(e);
        	}
        }
        System.out.println("\nStatistiques :");
        if(fiche1.getStatistiques() != null) {
        	for(Statistique s : fiche1.getStatistiques()) {
        		System.out.println(s.getName()+" "+s.getValue());
        	}
        }
        
//        System.out.println("\nConteneurs de la fiche personnage :");
//        if(fiche1.getContainers() != null) {
//        	for (Conteneur c : fiche1.getContainers()) {
//                System.out.println("Position X : " + c.getPosX() + ", Position Y : " + c.getPosY());
//                for(Item i : c.getItems()) {
//                	System.out.println("Nom de l'item :" + i.getName());
//                	if(i.getStringItems() != null) {
//                		for(StringItem si : i.getStringItems()) {
//                			System.out.println("Nom du sous-item :" + si.getName());
//                			System.out.println("Valeur du sous-item :" + si.getField());
//                		}
//                	}
//                	if(i.getIntItems() != null) {
//                		for(IntItem ii : i.getIntItems()) {
//                			System.out.println("Nom du sous-item :" + ii.getName());
//                			System.out.println("Valeur du sous-item :" + ii.getField());
//                		}
//                	}
//                	if(i.getBooleanItems() != null) {
//                		for(BooleanItem bi : i.getBooleanItems()) {
//                			System.out.println("Nom du sous-item :" + bi.getName());
//                			System.out.println("Valeur du sous-item :" + bi.getField());
//                		}
//                	}
//                	System.out.println();
//                }
//            }
//        }
//        System.out.println("Changement de position entre 2 conteneurs :");
//        Conteneur cont1 = fiche1.getContainer(0);
//        Conteneur cont2 = fiche1.getContainer(1);
//        System.out.println("Conteneur 1 : posX=" + cont1.getPosX() + " posY=" + cont1.getPosY());
//        System.out.println("Conteneur 2 : posX=" + cont2.getPosX() + " posY=" + cont2.getPosY());
//        fiche1.changeLocation(cont1, cont2);
//        System.out.println("Conteneur 1 : posX=" + cont1.getPosX() + " posY=" + cont1.getPosY());
//        System.out.println("Conteneur 2 : posX=" + cont2.getPosX() + " posY=" + cont2.getPosY());
	}
}
