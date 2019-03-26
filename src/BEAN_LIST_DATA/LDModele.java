/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN_LIST_DATA;

import SOURCES.Constante;
import SOURCES.DetailViewer;
import SOURCES.Propriete;
import Sources.CHAMP_LOCAL;
import Sources.Ecouteurs.ProprieteAdapter;
import Sources.Ecouteurs.ProprieteEvent;
import Sources.PROPRIETE;
import Sources.UI.JS2BPanelPropriete;
import UI.JS2bTextField;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP Pavilion
 */
public abstract class LDModele extends AbstractTableModel {

    public String dateA, dateB = "";
    public int idSelectedLine = -1;
    public Vector listeObjets = new Vector();
    public String critereRapide;
    public ProprieteEvent proprieteEvent = null;
    public JLabel labelResultat = null;
    public JButton btback = null;
    public JButton btnext = null;
    public JTabbedPane TabListe = null;
    public int pageEncours = 1;
    public String modelName = "";
    public ImageIcon iconeChargement, iconeRepos, iconeListeData, iconePropriete;
    public Class classeName;
    public LDLineViewer lDLineViewer;
    public JS2BPanelPropriete panPropriete = null;
    private JTabbedPane tabPropriete;
    private JS2bTextField champRechercheRapide = null;
    public Vector<CHAMP_LOCAL> proAEviter = new Vector<>();
    public JTabbedPane tabDetails;
    public JScrollPane scrollDetails;
    private DetailViewer detailViewer = null;

    public final ImageIcon Infos_01 = new javax.swing.ImageIcon(getClass().getResource("/IMG/Infos01.png"));
    public final ImageIcon Calendrier_01 = new javax.swing.ImageIcon(getClass().getResource("/IMG/Calendrier01.png"));
    public final ImageIcon Texte_01 = new javax.swing.ImageIcon(getClass().getResource("/IMG/Texte01.png"));
    public final ImageIcon Nombre_01 = new javax.swing.ImageIcon(getClass().getResource("/IMG/nombre01.png"));
    public final ImageIcon Fermer_01 = new javax.swing.ImageIcon(getClass().getResource("/IMG/Fermer01.png"));
    public final ImageIcon Filtrer_01 = new javax.swing.ImageIcon(getClass().getResource("/IMG/Filtrer01.png"));
    public final ImageIcon Chercher_01 = new javax.swing.ImageIcon(getClass().getResource("/IMG/Chercher01.png"));

    //Titre de la liste
    public String[] entete = {" Résultat de la recherche : "};

    public LDModele(JTabbedPane tabDetails, JScrollPane scrollDetails, Class classeName, JLabel labelResultat, JButton btback, JButton btnext, ImageIcon iconeListeData, JTabbedPane TabListe, ImageIcon iconePropriete, JTabbedPane tabPropriete, JS2bTextField champRechercheRapide, boolean isModifiable, String modelName, ImageIcon iconeChargement, ImageIcon iconeRepos) {
        super();
        this.labelResultat = labelResultat;
        this.btback = btback;
        this.btnext = btnext;
        this.TabListe = TabListe;
        this.modelName = modelName;
        this.proprieteEvent = null;//On réinitialise l'evenement
        this.iconeChargement = iconeChargement;
        this.iconeRepos = iconeRepos;
        this.classeName = classeName;
        this.tabDetails = tabDetails;
        this.scrollDetails = scrollDetails;
        this.tabPropriete = tabPropriete;
        this.champRechercheRapide = champRechercheRapide;
        this.iconeListeData = iconeListeData;
        this.iconePropriete = iconePropriete;
        this.panPropriete = new JS2BPanelPropriete(iconePropriete, modelName, isModifiable);

        initialiser();
        ecouterPropriete();
        ecouterRechercheRapide();
        chargerTablePropriete();
    }

    public void chargerTablePropriete() {
        panPropriete.chargerPanel(tabPropriete);
        TabListe.setIconAt(0, iconeListeData);
    }

    private void ecouterRechercheRapide() {
        champRechercheRapide.setIcon(Chercher_01);
        champRechercheRapide.setTextInitial("Recherche rapide...");
        champRechercheRapide.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (champRechercheRapide.getText().length() != 0) {
                    actualiser_(champRechercheRapide.getText());
                }
            }
        });
    }

    public void initialiser() {
        initChampAIgnorer(proAEviter);
        dateA = "1960/06/30 00:00:01";
        dateB = Util.getDate_today_night();
        panPropriete.viderListe();
        panPropriete.AjouterSeparateur("Actions");
        panPropriete.AjouterBouton(Fermer_01, "FERMER");
        panPropriete.AjouterBouton(Filtrer_01, "FILTRER");
        panPropriete.AjouterSeparateur("Critères");

        if (isDatable() == true) {
            CHAMP_LOCAL champ_dateA = new CHAMP_LOCAL(Calendrier_01, "Entre", "dateA", null, Util.getDate("1960/06/30 00:00:01"), PROPRIETE.TYPE_CHOIX_DATE);
            CHAMP_LOCAL champ_dateB = new CHAMP_LOCAL(Calendrier_01, "Et", "dateB", null, Util.getDate(Util.getDate_today_night()), PROPRIETE.TYPE_CHOIX_DATE);
            panPropriete.AjouterPropriete(champ_dateA);
            panPropriete.AjouterPropriete(champ_dateB);
        }

        //Chargement automatique des champs
        for (Field champ : classeName.getDeclaredFields()) {
            if (isAIgnorer(champ.getName()) == false) {
                //System.out.println("*** " + champ.getName());
                //On ne construit dynamiquement que les CHAMPS contenant la date, les champs DOUBLE et les champs STRING
                //Le reste le USER doit le construire manuellement dans le méthode initChampListeChoix()

                if ((champ.getType() == String.class) && (champ.getName().contains("date") == false)) {
                    CHAMP_LOCAL champ_local = new CHAMP_LOCAL(Texte_01, champ.getName(), champ.getName(), null, "", PROPRIETE.TYPE_SAISIE_TEXTE);
                    panPropriete.AjouterPropriete(champ_local);
                } else if (champ.getType() == double.class) {
                    CHAMP_LOCAL champ_local = new CHAMP_LOCAL(Nombre_01, champ.getName(), champ.getName(), null, "0", PROPRIETE.TYPE_SAISIE_NUMBRE);
                    panPropriete.AjouterPropriete(champ_local);
                }
            }

        }
        initChampListeChoixOuValeurEntieres(panPropriete);
    }

    private boolean isAIgnorer(String codeSQL) {
        for (CHAMP_LOCAL champ_local : proAEviter) {
            if (champ_local.getCodeSql().equals(codeSQL)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDatable() {
        for (Field champ : classeName.getDeclaredFields()) {
            if (champ.getName().contains("date") == false) {
                return true;
            }
        }
        return false;
    }

    protected void chargerDataCritere(Object critere) {
        if (TabListe.getComponentCount() != 0) {
            TabListe.setTitleAt(0, "(...) " + modelName.toUpperCase());
        }
        listeObjets.removeAllElements();
        //On désactive les boutons de navigation entre pages
        activerBoutonsNavigation(false, "Chargement...", iconeChargement);

        if (proprieteEvent != null) {
            //Récupération des dates
            PROPRIETE pDateA = getProprieteCodeSQL("dateA");
            PROPRIETE pDateB = getProprieteCodeSQL("dateB");
            if (pDateA != null & pDateB != null) {
                dateA = Util.getDate((Date) pDateA.getValeurSelectionne());
                dateB = Util.getDate((Date) pDateB.getValeurSelectionne());
                //System.out.println("Entre " + dateA + " et " + dateB);
            }

            for (Field champ : classeName.getDeclaredFields()) {
                PROPRIETE propriete = getProprieteCodeSQL(champ.getName());
                if (propriete != null) {
                    try {
                        if (champ.getType() == String.class) {
                            champ.set(critere, propriete.getValeurSelectionne());
                        } else if (champ.getType() == double.class) {
                            champ.set(critere, Double.parseDouble(propriete.getValeurSelectionne() + "".trim()));
                        } else if (champ.getType() == int.class) {
                            champ.set(critere, getID(propriete));
                        } else if (champ.getType() == double.class) {
                            champ.set(critere, getDouble(propriete));
                        } else {
                            //System.out.println("Champ nom reconnu = " + champ.getName() + ", Prop = " + propriete.getNom() + " : " + getID(propriete));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private int getID(PROPRIETE prop) {
        int val = -1;
        if (((prop.getValeurSelectionne() + "")).contains("_")) {
            try {
                val = Integer.parseInt((prop.getValeurSelectionne() + "").split("_")[0]);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        } else {
            return getInt(prop);
        }

        return val;
    }

    private int getInt(PROPRIETE prop) {
        int val = -1;
        try {
            val = Integer.parseInt((prop.getValeurSelectionne() + ""));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return val;
    }

    private double getDouble(PROPRIETE prop) {
        double val = 0;
        try {
            val = Double.parseDouble((prop.getValeurSelectionne() + ""));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return val;
    }

    protected void terminerActualisation(boolean oui, String message) {
        fireTableDataChanged();
        if (TabListe.getComponentCount() != 0) {
            TabListe.setTitleAt(0, "(" + listeObjets.size() + ") " + modelName.toUpperCase());
        }
        activerBoutonsNavigation(oui, message, iconeRepos);
    }

    protected PROPRIETE getProprieteCodeSQL(String codeSQL) {
        for (PROPRIETE prop : proAEviter) {
            //System.out.println("Champ à eviter = " + prop.getCodeSql());
            if (prop.getCodeSql().equals(codeSQL)) {
                return null;
            }
        }

        for (PROPRIETE prop : proprieteEvent.getListePro()) {
            if (prop.getCodeSql().equals(codeSQL)) {
                return prop;
            }
        }
        return null;
    }

    private void ecouterPropriete() {
        panPropriete.addProprieteListener(new ProprieteAdapter() {

            @Override
            public void proprieteValidee(ProprieteEvent event) {
                try {
                    if (event.getNomBouton().toLowerCase().equals("fermer") || event.getNomBouton().toLowerCase().equals("close")) {
                        if (TabListe != null) {
                            TabListe.removeAll();
                            
                        }
                        if (tabPropriete != null) {
                            //tabPropriete.removeAll();
                            tabPropriete.remove(panPropriete);
                        }
                    } else {
                        setCritere(1, "");
                        actualiser(event);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void vider() {
        listeObjets = new Vector();
        fireTableDataChanged();
    }

    public void activerBoutonsNavigation(boolean oui, String texte, ImageIcon icone) {
        labelResultat.setText(texte);
        if (icone != null) {
            labelResultat.setIcon(icone);
        }
        if (btback != null) {
            btback.setEnabled(oui);
        }
        if (btnext != null) {
            btnext.setEnabled(oui);
        }
    }

    public void setCritere(int pageEncours, String critereRapide) {
        this.critereRapide = critereRapide;
        this.pageEncours = pageEncours;
    }

    public int getPageEncours() {
        return this.pageEncours;
    }

    public int getIdSelectedLine() {
        return idSelectedLine;
    }

    public void setIdSelectedLine(int idSelectedLine) {
        this.idSelectedLine = idSelectedLine;
        afficherDetail();
    }

    public Object getSelectedObject() {
        Object obj = null;
        if (listeObjets != null) {
            if (!listeObjets.isEmpty() && idSelectedLine >= 0) {
                obj = listeObjets.elementAt(idSelectedLine);
            }
        }
        return obj;
    }

    public void setListeObjets(Vector nvliste, int tailleResultat) {
        this.listeObjets = nvliste;
        fireTableDataChanged();
        
        if (!nvliste.isEmpty()) {
            actualiserGestionnairePages(tailleResultat);
        } else {
            activerBoutonsNavigation(true, "Aucun élément trouvé sur le serveur.", iconeRepos);
        }
    }

    @Override
    public int getRowCount() {
        return listeObjets.size();
    }

    @Override
    public int getColumnCount() {
        return entete.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; //Toutes les cellules sont non éditables
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = listeObjets.elementAt(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return entete[columnIndex];
    }

    public void pagePrecendente(String critereRapide) {
        int pgEnc = getPageEncours();
        if ((pgEnc) > 1) {
            setCritere(pgEnc - 1, critereRapide.trim());
            actualiser(null);
        } else {
            if (btback != null) {
                btback.setEnabled(false);
            }
        }
    }

    public void pageSuivante(String critereRapide) {
        int pgEnc = getPageEncours();
        setCritere(pgEnc + 1, critereRapide.trim());
        actualiser(null);
        if (btback != null) {
            btback.setEnabled(true);
        }
    }

    public Class getColumnClass(int columnIndex) {
        return classeName;
    }

    public void actualiser_(String critereRapide) {
        this.critereRapide = critereRapide;
        actualiser(null);
    }

    public void actualiser() {
        actualiser(null);
    }

    public void setValeurDetails(ImageIcon icone) {
        if (scrollDetails != null && tabDetails != null) {
            detailViewer = new DetailViewer(icone, modelName, getSelectedObject(), scrollDetails, tabDetails, 12) {
                @Override
                public void initPropConstantes(Vector<Constante> vector) {
                    iniDetailsConstantes(vector);
                }
                
                @Override
                public void initPropAEviter(Vector<Propriete> vector) {
                    iniDetailsAEviter(vector);
                }

                @Override
                public void initPropSpeciaux(Vector<Propriete> vector) {
                    
                }
            };
        }
    }
    
    public abstract void iniDetailsConstantes(Vector<Constante> vector);
    
    public abstract void iniDetailsAEviter(Vector<Propriete> vector);

    private void actualiserGestionnairePages(int nbElementTotal) {
        int nbPagesTotale = 1;
        if (LDTable.tailListe != 0) {
            nbPagesTotale = nbElementTotal / LDTable.tailListe;
            if ((nbElementTotal % LDTable.tailListe) != 0) {
                nbPagesTotale++;
            }
        }
        activerBoutonsNavigation(true, listeObjets.size() + " élément(s)/" + nbElementTotal + ", Page " + pageEncours + "/" + nbPagesTotale, iconeRepos);
        if (pageEncours == nbPagesTotale) {
            btnext.setEnabled(false);
            btback.setEnabled(true);
        }
        if (pageEncours == 1) {
            btback.setEnabled(false);
        }
    }

    

    public abstract void initObjetCritere(ProprieteEvent event);

    public abstract void initChampListeChoixOuValeurEntieres(JS2BPanelPropriete jS2BPanelPropriete);

    public abstract void initChampAIgnorer(Vector<CHAMP_LOCAL> proAEviter);

    public abstract void supprimerObjet(boolean withDialog);

    public abstract void actualiser(ProprieteEvent event);
    
    public abstract void initConstantes();

    public abstract void afficherDetail();
    
    public abstract void PDF();

}
