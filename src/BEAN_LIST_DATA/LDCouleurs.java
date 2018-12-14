/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN_LIST_DATA;

import java.awt.Color;

/**
 *
 * @author user
 */
public class LDCouleurs {

    public static Color COULEUR_GRISE_SOMBRE = new Color(210, 210, 210);
    public static Color COULEUR_GRISE_CLAIRE = Color.lightGray;
    public static Color COULEUR_NOIRE = Color.black;
    public static Color COULEUR_BLANCHE = Color.WHITE;
    public static Color COULEUR_BLEUE = Color.BLUE;
    public static Color COULEUR_VERTE = Color.GREEN;
    public static Color COULEUR_JAUNE = Color.YELLOW;
    public static Color COULEUR_ORANGE = Color.ORANGE;
    public static Color COULEUR_ROSE = Color.PINK;
    public static Color COULEUR_ROUGE = Color.RED;
    public static Color COULEUR_BLEUE_CIEL = Color.CYAN;
    public static Color COULEUR_MAGENTA = Color.MAGENTA;

    public Color arriereNormal = COULEUR_GRISE_CLAIRE;
    public Color avantNormal = COULEUR_NOIRE;
    public Color arriereSelection = COULEUR_NOIRE;
    public Color avantSelection = COULEUR_GRISE_CLAIRE;

    public LDCouleurs() {
    }

    public LDCouleurs(Color arrierePlanNormal, Color avantPlanNormal, Color arrierePlanSelection, Color avantPlanSelection) {
        if (arrierePlanNormal != null) {
            this.arriereNormal = arrierePlanNormal;
        }
        if (avantPlanNormal != null) {
            this.avantNormal = avantPlanNormal;
        }
        if (arrierePlanSelection != null) {
            this.arriereSelection = arrierePlanSelection;
        }
        if (avantPlanSelection != null) {
            this.avantSelection = avantPlanSelection;
        }
    }

    public void setCouleurNormal(Color arrierePlanNormal, Color avantPlanNormal) {
        if (arrierePlanNormal != null) {
            this.arriereNormal = arrierePlanNormal;
        }
        if (avantPlanNormal != null) {
            this.avantNormal = avantPlanNormal;
        }
    }

    public void setCouleurSelection(Color arrierePlanSelection, Color avantPlanSelection) {
        if (arrierePlanSelection != null) {
            this.arriereSelection = arrierePlanSelection;
        }
        if (avantPlanSelection != null) {
            this.avantSelection = avantPlanSelection;
        }
    }

}
