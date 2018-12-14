/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN_LIST_DATA;

//import Objets.MenuContextuel;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author HP Pavilion
 */
public class LDTable {

    private JScrollPane scrollListePrincipale = null;
    private JTable table = null;
    private LDListener ecouteur = null;
    private LDLigne ligne = null;
    private LDModele model = null;
    public static final int tailListe = 20;

    public LDTable(JScrollPane scrollListePrincipale, LDListener listeDataListener) {
        this.scrollListePrincipale = scrollListePrincipale;
        this.ecouteur = listeDataListener;
        this.ligne = new LDLigne();
    }

    public void construire(LDModele model) {
        this.ecouteur.OnInitialiseBarreOutilsEtMenuContextuel(model);
        this.model = model;
        this.table = new JTable(model);

        this.table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onClicSouris(evt);
            }
        });
        this.table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onTapeClavier(evt);
            }
        });
        
        this.OnConstruitElementListe();
        this.table.setRowHeight(60);
        this.scrollListePrincipale.setViewportView(table);
    }

    private void onTapeClavier(java.awt.event.KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DELETE:
                supprimer(true);
                break;
            case KeyEvent.VK_CONTEXT_MENU:
                ecouteur.OnAfficheMenuContextuel(evt.getComponent(), table.getX(), table.getY());
                break;
            default:
                ecouterSelection();
                break;
        }
    }

    private void onClicSouris(java.awt.event.MouseEvent evt) {
        switch (evt.getButton()) {
            case MouseEvent.BUTTON1:
                //clic droit
                ecouterSelection();
                break;
            case MouseEvent.BUTTON3:
                //clic droit
                ecouteur.OnAfficheMenuContextuel(evt.getComponent(), evt.getX(), evt.getY());
                break;
            default:
                //ecouterSelection();
                break;
        }
    }

    private void OnConstruitElementListe() {
        //Desiner le rendu de chaque element de la liste
        this.table.setDefaultRenderer(model.getColumnClass(0), new TableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                ecouteur.OnConstruitLigneListe(model, ligne, table, value, isSelected, hasFocus, row, column);
                ligne.sentirSelection(isSelected, row);
                return ligne;
            }
        });
    }

    private void ecouterSelection() {
        if (model != null && ecouteur != null) {
            this.model.setIdSelectedLine(this.table.getSelectedRow());
            this.ecouteur.OnActualiseBarreOutilsEtMenuContextuel(this.model);
        }
    }

    public void supprimer(boolean showDialog) {
        if (model != null) {
            this.model.supprimerObjet(showDialog);
        }
    }

}
