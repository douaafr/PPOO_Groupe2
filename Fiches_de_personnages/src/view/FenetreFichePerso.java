package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import java.util.List;

import model.FichePersonnage;
import model.Statistique;
import model.Utilisateur;
import controller.ControllerFichePerso;

public class FenetreFichePerso extends JFrame {

	private Utilisateur utilisateur;
	private FichePersonnage fichePersonnage;
	private MenuAccueilView mAV;
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

    public FenetreFichePerso(Utilisateur user, FichePersonnage fichePersonnage, MenuAccueilView mav) {
    	this.utilisateur = user;
        this.fichePersonnage = fichePersonnage;
        this.mAV = mav;
        initUI();
    }

    private void initUI() {
        setTitle("Fiche Personnage");
        setSize(1155, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        namePanel.add(new JLabel("Nom *:"));
        namePanel.add(nameField);
        namePanel.add(new JLabel("Âge *:"));
        namePanel.add(ageField);
        namePanel.add(new JLabel("Points de Vie *:"));
        namePanel.add(pvField);
        topPanel.add(namePanel, BorderLayout.CENTER);

        getContentPane().add(topPanel, BorderLayout.NORTH);

        // Panneau de biographie en dessous du topPanel
        JPanel bioPanel = new JPanel(new BorderLayout());
        bioTextArea = new JTextArea(5, 20);
        bioTextArea.setLineWrap(true); // Active le retour à la ligne
        bioTextArea.setWrapStyleWord(true); // S'assure que les mots ne sont pas coupés en deux
        bioPanel.add(new JLabel("Biographie"), BorderLayout.NORTH);
        bioPanel.add(new JScrollPane(bioTextArea), BorderLayout.CENTER);
        getContentPane().add(bioPanel, BorderLayout.WEST);
        
        // Panneau central pour les statistiques
        JPanel statsPanel = new JPanel(new BorderLayout());
        statsTable = new JTable();
        setupStatsTable();
        statsPanel.add(new JLabel("Statistiques"), BorderLayout.NORTH);
        statsPanel.add(new JScrollPane(statsTable), BorderLayout.CENTER);
        getContentPane().add(statsPanel, BorderLayout.CENTER);

        // Panneau pour les compétences et l'équipement
        JPanel listPanel = new JPanel(new GridLayout(2, 1));
        JPanel compPanel = new JPanel(new BorderLayout());
        setupCompetencesList();
        compPanel.add(new JLabel("Compétences"), BorderLayout.NORTH);
        compPanel.add(new JScrollPane(competencesList), BorderLayout.CENTER);
        listPanel.add(compPanel);
        
        JPanel equipPanel = new JPanel(new BorderLayout());
        setupEquipementList();
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
        saveButton.setEnabled(false); // Désactiver le bouton par défaut
        controlPanel.add(saveButton);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);

        // Ajouter les DocumentListeners pour activer le bouton de sauvegarde
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkFields();
            }

            private void checkFields() {
                saveButton.setEnabled(!nameField.getText().trim().isEmpty()
                        && !pvField.getText().trim().isEmpty()
                        && !ageField.getText().trim().isEmpty());
            }
        };

        nameField.getDocument().addDocumentListener(documentListener);
        pvField.getDocument().addDocumentListener(documentListener);
        ageField.getDocument().addDocumentListener(documentListener);

        setVisible(true);
    }
    
    private void setupStatsTable() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
            
            @Override
            public void setValueAt(Object aValue, int row, int column) {
            	if (column == 1) {
            		try {
            			int newValue = Integer.parseInt(aValue.toString());
            			super.setValueAt(newValue, row, column); // Met à jour la vue seulement si la conversion est réussie
            			fichePersonnage.getStatistiques().get(row).setValue(newValue); // Met à jour le modèle
            		} catch (NumberFormatException e) {
            			JOptionPane.showMessageDialog(null, "La valeur ne peut être modifiée. Veuillez entrer un nombre valide.");
            		}
            	} else {
            		super.setValueAt(aValue, row, column); // Pour toutes les autres colonnes, permettre la modification normale.
            	}
            }
        };
        model.setColumnIdentifiers(new String[]{"Statistique", "Valeur"});
        statsTable.setModel(model);
        JPopupMenu popupMenu = createPopupMenu(statsTable, true);
        statsTable.setComponentPopupMenu(popupMenu);
    }

    private void setupCompetencesList() {
        competencesList = new JList<>(new DefaultListModel<>());
        JPopupMenu popupMenu = createPopupMenu(competencesList, false);
        competencesList.setComponentPopupMenu(popupMenu);
    }

    private void setupEquipementList() {
        equipementList = new JList<>(new DefaultListModel<>());
        JPopupMenu popupMenu = createPopupMenu(equipementList, false);
        equipementList.setComponentPopupMenu(popupMenu);
    }
    
    private JPopupMenu createPopupMenu(JComponent component, boolean isTable) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Supprimer");
        deleteItem.addActionListener(e -> {
            if (isTable) {
                int row = statsTable.getSelectedRow();
                if (row >= 0) {
                    ((DefaultTableModel) statsTable.getModel()).removeRow(row);
                    fichePersonnage.getStatistiques().remove(row);
                }
            } else {
                JList list = (JList) component;
                int index = list.getSelectedIndex();
                if (index != -1) {
                    ((DefaultListModel) list.getModel()).remove(index);
                    if (list == competencesList) {
                        fichePersonnage.getCompetences().remove(index);
                    } else if (list == equipementList) {
                        fichePersonnage.getEquipements().remove(index);
                    }
                }
            }
        });
        popupMenu.add(deleteItem);
        return popupMenu;
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

    public void updateStats(List<Statistique> statistiques) {
        DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
        model.setRowCount(0);
        for (Statistique stat : statistiques) {
            model.addRow(new Object[]{stat.getName(), stat.getValue()});
        }
    }

    public void updateCompetences(DefaultListModel<String> model) {
        competencesList.setModel(model);
    }

    public void updateEquipements(DefaultListModel<String> model) {
        equipementList.setModel(model);
    }

    private void updatePortrait() {
        if (fichePersonnage.getPortrait() != null) {
            portraitLabel.setIcon(new ImageIcon(fichePersonnage.getPortrait().getPath()));
        }
    }
    
    public void setController() {
    	ControllerFichePerso controller = new ControllerFichePerso(this.utilisateur, this.fichePersonnage, this, mAV);
    }

//    public static void main(String[] args) throws Exception {
//    	UIManager.setLookAndFeel(new NimbusLookAndFeel());
//    	
//    	Utilisateur utilisateur = new Utilisateur();
//        FichePersonnage fiche = new FichePersonnage(utilisateur.getFichesPersonnages().size()+1);
//        FenetreFichePerso view = new FenetreFichePerso(utilisateur, fiche);
//        view.setController();
//        view.pack();
//		view.setSize(1155, 800);
//		view.setLocationRelativeTo(null);
//		view.setResizable(false);
//		view.setVisible(true);
//    }
}
