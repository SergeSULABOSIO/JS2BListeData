/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN_LIST_DATA;

import java.awt.Component;
import javax.swing.JTable;

/**
 *
 * @author HP Pavilion
 */
public abstract class LDListener {
    public abstract void OnActualiseBarreOutilsEtMenuContextuel(LDModele model);
    public abstract void OnInitialiseBarreOutilsEtMenuContextuel(LDModele model);
    public abstract void OnAfficheMenuContextuel(Component support, int x, int y);
    public abstract void OnConstruitLigneListe(LDModele model, LDLigne ligne, JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column);
}
