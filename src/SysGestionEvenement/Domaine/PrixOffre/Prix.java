/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.PrixOffre;

import java.awt.Color;
import java.io.Serializable;
/**
 *
 * @author alemyre
 */
public class Prix implements Serializable
{
    private static final long serialVersionUID = 1L;
    private double montantPrix;
    private Color couleur;

    public Prix(double p_montantPrix, Color p_couleur) 
    {
        this.montantPrix = p_montantPrix;
        this.couleur = p_couleur;
    }
    
    
    public void setPrix(double p_montantPrix)
    {
        this.montantPrix = p_montantPrix;
    }
    
    public double getPrix()
    {
        return this.montantPrix;
    }
    
    public Color getColor()
    {
        return this.couleur;
    }
    
} 
