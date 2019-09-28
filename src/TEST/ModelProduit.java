/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEST;

import BEAN_LIST_DATA.LDModele;
import SOURCES.ConstanteViewer;
import SOURCES.ProprieteViewer;
import Sources.CHAMP_LOCAL;
import Sources.Ecouteurs.ProprieteEvent;
import Sources.PROPRIETE;
import Sources.UI.JS2BPanelPropriete;
import UI.JS2bTextField;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author HP Pavilion
 */
public class ModelProduit extends LDModele {

    private Produit critere = null;

    public ModelProduit(JTabbedPane tabDetails, JScrollPane scrollDetails, Class classeName, JLabel labelResultat, JButton btback, JButton btnext, ImageIcon iconeListeData, JTabbedPane TabListe, ImageIcon iconePropriete, JTabbedPane tabPropriete, JS2bTextField champRechercheRapide, boolean isModifiable, String modelName, ImageIcon iconeChargement, ImageIcon iconeRepos) {
        super(tabDetails, scrollDetails, classeName, labelResultat, btback, btnext, iconeListeData, TabListe, iconePropriete, tabPropriete, champRechercheRapide, isModifiable, modelName, iconeChargement, iconeRepos);
    }

    

    @Override
    public void actualiser(ProprieteEvent event) {
        initObjetCritere(event);
        //Chargement de données en fonction du critere fourni
        Vector<Produit> data = new Vector<>();
        for (int i = 0; i < 20; i++) {
            Produit prod = new Produit(i + 23, "PRODUIT #" + i, "RAS SUR LE PRODUIT #" + i, 1, 150, "Serge SULA BOSIO", "01/10/2018 23:21", 21);
            if (prod.getNom().contains(critere.getNom())) {
                data.add(prod);
            }
        }
        if(!data.isEmpty()){
            setListeObjets(data, data.firstElement().getTailleResultat());
        }else{
            setListeObjets(data, 0);
        }
    }

    

    @Override
    public void initObjetCritere(ProprieteEvent event) {
        if (event != null && proprieteEvent == null) {
            proprieteEvent = event;
        }
        critere = new Produit();
        critere.setNom(critereRapide);
        chargerDataCritere(critere);
    }

    @Override
    public void supprimerObjet(boolean withDialog) {
        Produit produit = (Produit) listeObjets.elementAt(idSelectedLine);
        if (produit != null) {
            boolean supprime = false;
            if (withDialog == true) {
                int dialogResult = JOptionPane.showConfirmDialog(TabListe, "Etes-vous sûr de vouloir supprimer " + produit.getNom() + "?", "Avertissement", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    supprime = true;
                }
            } else {
                supprime = true;
            }
            if (supprime == true) {
                //On supprime
                activerBoutonsNavigation(false, "Suppression...", iconeChargement);
                listeObjets.removeElementAt(idSelectedLine);
                fireTableDataChanged();
                activerBoutonsNavigation(false, "Prêt.", iconeRepos);
            }
        } else {
            JOptionPane.showMessageDialog(TabListe, "Aucun élément n'est séléctionné !");
        }
    }

    @Override
    public void afficherDetail() {
        Produit produit = (Produit) getSelectedObject();
        if (produit != null) {
            ImageIcon Infos_02 = new javax.swing.ImageIcon(getClass().getResource("/IMG/Infos02.png"));
            setValeurDetails(Infos_02);
        }
    }

    @Override
    public void initChampListeChoixOuValeurEntieres(JS2BPanelPropriete jS2BPanelPropriete) {
        //On initialise les champs des liste de choix

        //Choix Utilisateur
        Vector listeUtilisateur = new Vector();
        listeUtilisateur.add("TOUS LES UTILISATEURS");
        listeUtilisateur.add("1_SERGE SULA BOSIO");
        listeUtilisateur.add("2_ALAIN MAKULA BOFANDO");
        listeUtilisateur.add("3_KALLY KALALA KAZADI");
        CHAMP_LOCAL champ_listeUtil = new CHAMP_LOCAL(Infos_01, "idUtilisateur", "idUtilisateur", listeUtilisateur, listeUtilisateur.firstElement() + "", PROPRIETE.TYPE_CHOIX_LISTE);
        jS2BPanelPropriete.AjouterPropriete(champ_listeUtil);

    }

    @Override
    public void initChampAIgnorer(Vector<CHAMP_LOCAL> proAEviter) {
        proAEviter.add(new CHAMP_LOCAL(null, "nomUtilisateur", "nomUtilisateur", null, null, PROPRIETE.TYPE_CHOIX_LISTE));
    }

    @Override
    public void initConstantes() {
        
    }

    @Override
    public void iniDetailsConstantes(Vector<ConstanteViewer> vector) {
        
    }

    @Override
    public void iniDetailsAEviter(Vector<ProprieteViewer> vector) {
        
    }

    @Override
    public void PDF() {
        
    }

 

}
