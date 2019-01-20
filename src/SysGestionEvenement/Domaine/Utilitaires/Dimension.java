/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Utilitaires;


/**
 *
 * @author alemyre
 */
public class Dimension {
    private double largeur;
    private double profondeur;
    
    public Dimension(double profondeurs, double largeurs){
        this.profondeur = profondeurs;
        this.largeur = largeurs;
    }
    
    public double getLargeur(){
        return largeur;
    }
    
    public double getProndeur(){
        return profondeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public void setProfondeur(double profondeur) {
        this.profondeur = profondeur;
    }

 
    
}
