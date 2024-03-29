package model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Portrait {
    
    private String path;

    public Portrait() {
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void addPhoto() {
    	// Création d'une instance de JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        // Définir le répertoire initial du JFileChooser comme le répertoire personnel de l'utilisateur
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        // Création d'un filtre de fichier pour n'autoriser que les fichiers PNG, JPEG et JPG
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);
        // Afficher la boîte de dialogue pour sélectionner un fichier et attendre la réponse de l'utilisateur
        int result = fileChooser.showOpenDialog(null);
        // Si l'utilisateur a sélectionné un fichier et a cliqué sur "Ouvrir"
        if (result == JFileChooser.APPROVE_OPTION) {
            // Récupérer le fichier sélectionné
            File selectedFile = fileChooser.getSelectedFile();
            setPath(selectedFile.getAbsolutePath());

            // Répertoire de destination
            File destinationDirectory = new File("resources");
            // Vérifier si le répertoire de ressources existe, sinon le créer
            if (!destinationDirectory.exists()) {
                if (!destinationDirectory.mkdirs()) {
                    JOptionPane.showMessageDialog(null, "Impossible de créer le répertoire de ressources", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Copier le fichier sélectionné dans le répertoire de destination
            try {
                File destinationFile = new File(destinationDirectory, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                // Définir le chemin relatif de l'image
                setPath("resources/" + selectedFile.getName());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de la copie de l'image", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}