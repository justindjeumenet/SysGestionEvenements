/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;

import java.awt.Dimension;
import java.io.Serializable;
/**
 *
 * @author alemyre
 */
public class EspaceVital implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Dimension dimension;
    private double largeur;
    private double profondeur;

    public EspaceVital(double p_largeur, double p_profondeur) 
    {
        this.largeur = p_largeur;
        this.profondeur = p_profondeur;  
        this.dimension = new Dimension();
        this.dimension.setSize(p_largeur, p_profondeur);
    }
    
    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(double profondeur) {
        this.profondeur = profondeur;
    }
    
    public Dimension getDimensionEspaceVital()
    {
        return this.dimension;
    }
      
}
