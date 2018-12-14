/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TEST;

/**
 *
 * @author HP Pavilion
 */
public class Produit {

    public int id;
    public String nom;
    public String description;
    public int idUtilisateur;
    public double montant;
    public String nomUtilisateur;
    public String dateEnreg;
    public int tailleResultat;

    public Produit() {
        this.id = -1;
        this.nom = "";
        this.description = "";
        this.idUtilisateur = -1;
        this.nomUtilisateur = "";
        this.dateEnreg = "";
        this.tailleResultat = 0;
        this.montant = 0;
    }

    public Produit(int id, String nom, String description, int idUtilisateur, double montant, String nomUtilisateur, String dateEnreg, int tailleResultat) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.idUtilisateur = idUtilisateur;
        this.montant = montant;
        this.nomUtilisateur = nomUtilisateur;
        this.dateEnreg = dateEnreg;
        this.tailleResultat = tailleResultat;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getDateEnreg() {
        return dateEnreg;
    }

    public void setDateEnreg(String dateEnreg) {
        this.dateEnreg = dateEnreg;
    }

    public int getTailleResultat() {
        return tailleResultat;
    }

    public void setTailleResultat(int tailleResultat) {
        this.tailleResultat = tailleResultat;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", idUtilisateur=" + idUtilisateur + ", montant=" + montant + ", nomUtilisateur=" + nomUtilisateur + ", dateEnreg=" + dateEnreg + ", tailleResultat=" + tailleResultat + '}';
    }
    
    

    public static void main(String[] a) {
        String id = "1_SERGE SULA";
        if(id.contains("_")){
            System.out.println("OUI!!!!");
        }else{
            System.out.println("Non...");
        }
    }

}
