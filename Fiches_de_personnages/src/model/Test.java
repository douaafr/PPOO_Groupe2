package model;
public class Test {
    public static void main(String[] args) throws Exception {
    	
    	
    	System.out.println("Début du programme.");
    	System.out.println(System.getProperty("java.class.path"));

        // Créer une instance de Compte
        Compte compte = new Compte();
        System.out.println("Création d'une instance de Compte.");

        // Créer un nouvel utilisateur
        String identifiant = "userTest";
        String nvMdp = "nouveauMdp";
        String motDePasse = nvMdp;
        //System.out.println("Tentative de création d'un nouvel utilisateur...");
        //compte.createAccount(identifiant, motDePasse);
        //System.out.println("Utilisateur " + identifiant + " créé avec succès."); 

        // Essayer de se connecter avec le nouvel utilisateur
        System.out.println("Tentative de connexion...");
        
        Utilisateur utilisateur = compte.signIn(identifiant, motDePasse);
        if (utilisateur != null) {
            System.out.println("Connexion réussie pour l'utilisateur " + identifiant);
        } else {
            System.out.println("Échec de la connexion pour l'utilisateur " + identifiant);
        }
        
        
        // Ajouter une fiche de personnage
        System.out.println("Ajout d'une nouvelle fiche de personnage pour " + identifiant + "...");
        utilisateur.addFichePersonnage("Fiche 1");
        System.out.println("Fiche ajoutée avec succès.");

        // Lister toutes les fiches de personnages
        System.out.println("Liste des fiches de personnages pour " + identifiant + " :");
        utilisateur.getFichesPersonnages().forEach(fiche -> System.out.println("- " + fiche.getName()));

        
        System.out.println(utilisateur.getPwd());
        // Déconnexion
        System.out.println("Déconnexion de l'utilisateur " + identifiant + "...");
        compte.signOut(utilisateur);
        System.out.println("Déconnexion réussie.");
        
        
        System.out.println("Fin du programme.");
        utilisateur.getFichesPersonnages().forEach(fiche -> System.out.println("- " + fiche.getName()));
    }
}
