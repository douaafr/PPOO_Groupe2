package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import model.FichePersonnage;
import model.Utilisateur;
import controller.ControllerFichePerso;

public class FenetreFichePerso extends JFrame {

	private Utilisateur utilisateur;
	private FichePersonnage fichePersonnage;
    private JTextField nameField;
    private JTextField pvField;  
    private JTextField ageField;
    private JLabel portraitLabel;
    private JTextArea bioTextArea;
    private JTable statsTable;
    private JList<String> competencesList;
    private JList<String> equipementList;
    private JButton saveButton;
    private JButton changePortraitButton;
    private JButton addStatButton, addCompButton, addEquipButton;
    private JTextField newStatField, newStatValueField, newCompField, newEquipField;

    public FenetreFichePerso(Utilisateur user, FichePersonnage fichePersonnage) {
    	this.utilisateur = user;
        this.fichePersonnage = fichePersonnage;
        initUI();
    }

    private void initUI() {
        setTitle("Fiche Personnage");
        setSize(1155, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Panneau pour le portrait et le bouton changer portrait 
        JPanel portraitPanel = new JPanel(new BorderLayout());
        portraitLabel = new JLabel();
        portraitPanel.add(portraitLabel, BorderLayout.NORTH);
        changePortraitButton = new JButton("Changer Portrait");
        portraitPanel.add(changePortraitButton, BorderLayout.SOUTH);
        updatePortrait();

        // Panneau supérieur pour le portrait, le nom, l'âge et les points de vie
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(portraitPanel, BorderLayout.WEST);

        // Panneau de droite pour le nom, âge et points de vie
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nameField = new JTextField(20);
        ageField = new JTextField(5);
        pvField = new JTextField(5); 
        namePanel.add(new JLabel("Nom:"));
        namePanel.add(nameField);
        namePanel.add(new JLabel("Âge:"));
        namePanel.add(ageField);
        namePanel.add(new JLabel("Points de Vie:"));
        namePanel.add(pvField);
        topPanel.add(namePanel, BorderLayout.CENTER);

        getContentPane().add(topPanel, BorderLayout.NORTH);

        // Panneau de biographie en dessous du topPanel
        JPanel bioPanel = new JPanel(new BorderLayout());
        bioTextArea = new JTextArea(5, 20);
        bioPanel.add(new JLabel("Biographie"), BorderLayout.NORTH);
        bioPanel.add(new JScrollPane(bioTextArea), BorderLayout.CENTER);
        getContentPane().add(bioPanel, BorderLayout.WEST);

        // Panneau central pour les statistiques
        JPanel statsPanel = new JPanel(new BorderLayout());
        String[] colNames = {"Statistique", "Valeur"};
        statsTable = new JTable(new Object[][]{}, colNames);
        statsPanel.add(new JLabel("Statistiques"), BorderLayout.NORTH);
        statsPanel.add(new JScrollPane(statsTable), BorderLayout.CENTER);
        getContentPane().add(statsPanel, BorderLayout.CENTER);

        // Panneau pour les compétences et l'équipement
        JPanel listPanel = new JPanel(new GridLayout(2, 1));
        competencesList = new JList<>(new DefaultListModel<>());
        JPanel compPanel = new JPanel(new BorderLayout());
        compPanel.add(new JLabel("Compétences"), BorderLayout.NORTH);
        compPanel.add(new JScrollPane(competencesList), BorderLayout.CENTER);
        listPanel.add(compPanel);

        equipementList = new JList<>(new DefaultListModel<>());
        JPanel equipPanel = new JPanel(new BorderLayout());
        equipPanel.add(new JLabel("Équipement"), BorderLayout.NORTH);
        equipPanel.add(new JScrollPane(equipementList), BorderLayout.CENTER);
        listPanel.add(equipPanel);

        getContentPane().add(listPanel, BorderLayout.EAST);
        
        // Ajout des champs et boutons pour les statistiques, compétences et équipements
        JPanel statsInputPanel = new JPanel();
        newStatField = new JTextField(10);
        newStatValueField = new JTextField(5);
        addStatButton = new JButton("Ajouter Statistique");
        statsInputPanel.add(new JLabel("Nouvelle Statistique:"));
        statsInputPanel.add(newStatField);
        statsInputPanel.add(new JLabel("Valeur:"));
        statsInputPanel.add(newStatValueField); 
        statsInputPanel.add(addStatButton);
        statsPanel.add(statsInputPanel, BorderLayout.SOUTH);

        JPanel compsInputPanel = new JPanel();
        newCompField = new JTextField(10);
        addCompButton = new JButton("Ajouter Compétence");
        compsInputPanel.add(new JLabel("Nouvelle Compétence:"));
        compsInputPanel.add(newCompField);
        compsInputPanel.add(addCompButton);
        compPanel.add(compsInputPanel, BorderLayout.SOUTH);

        JPanel equipsInputPanel = new JPanel();
        newEquipField = new JTextField(10);
        addEquipButton = new JButton("Ajouter Équipement");
        equipsInputPanel.add(new JLabel("Nouvel Équipement:"));
        equipsInputPanel.add(newEquipField);
        equipsInputPanel.add(addEquipButton);
        equipPanel.add(equipsInputPanel, BorderLayout.SOUTH);

        // Panneau inférieur pour les boutons
        JPanel controlPanel = new JPanel();
        saveButton = new JButton("Sauvegarder");
        controlPanel.add(saveButton);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void addChangePortraitButtonListener(ActionListener listener) {
        changePortraitButton.addActionListener(listener);
    }
    
    public void addAddStatButtonListener(ActionListener listener) {
        addStatButton.addActionListener(listener);
    }

    public void addAddCompButtonListener(ActionListener listener) {
        addCompButton.addActionListener(listener);
    }

    public void addAddEquipButtonListener(ActionListener listener) {
        addEquipButton.addActionListener(listener);
    }


    public JTextField getNameField() {
        return nameField;
    }
    
    public JTextField getPVField() {
        return pvField;
    }
    
    public JTextField getAgeField() {
        return ageField;
    }

    public void setPortrait(String imagePath) {
        if (imagePath != null) {
            portraitLabel.setIcon(new ImageIcon(imagePath));
        }
    }
    
    public JTextArea getBiographie() {
    	return bioTextArea;
    }
    
    public JList<String> getCompetencesList() {
        return competencesList;
    }

    public JList<String> getEquipementList() {
        return equipementList;
    }
    
    public JTextField getNewStatField() {
        return newStatField;
    }
    
    public JTextField getNewStatValueField() {
        return newStatValueField;
    }

    public JTextField getNewCompField() {
        return newCompField;
    }

    public JTextField getNewEquipField() {
        return newEquipField;
    }
    
    public void updateStats(Object[][] statsData) {
        String[] columnNames = {"Statistique", "Valeur"};
        statsTable.setModel(new DefaultTableModel(statsData, columnNames));
    }

    public void updateCompetences(DefaultListModel<String> model) {
        competencesList.setModel(model);
    }

    public void updateEquipments(DefaultListModel<String> model) {
        equipementList.setModel(model);
    }

    private void updatePortrait() {
        if (fichePersonnage.getPortrait() != null) {
            portraitLabel.setIcon(new ImageIcon(fichePersonnage.getPortrait().getPath()));
        }
    }

    public static void main(String[] args) throws Exception {
    	UIManager.setLookAndFeel(new NimbusLookAndFeel());
    	
    	Utilisateur utilisateur = new Utilisateur();
        FichePersonnage fiche = new FichePersonnage(utilisateur.getFichesPersonnages().size()+1);
        FenetreFichePerso view = new FenetreFichePerso(utilisateur, fiche);
        ControllerFichePerso controller = new ControllerFichePerso(view.utilisateur, fiche, view);
        view.pack();
		view.setSize(1155, 800);
		view.setLocationRelativeTo(null);
		view.setResizable(false);
		view.setVisible(true);
    }
}