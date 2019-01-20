/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.PrixOffre;

import java.io.Serializable;


/**
 *
 * @author justinDjeumene
 */
public class Offre implements Serializable
{
    
    private double pourcentageOffre;
    private String description;
  

    public Offre(double pourcentageOffre, String description) 
    {
        
        this.pourcentageOffre = pourcentageOffre;
        this.description = description;
        
    }
    
    
    public void setOffre(double pourcentageOffre,String description)
    {
        this.pourcentageOffre = pourcentageOffre;
        this.description = description;
    }
    
    public String getDescriptionOffre()
    {
        return this.description;
        
    }
    
    public double getPourcentageOffre()
    {
        return this.pourcentageOffre;
        
    }
    
    public String getDescriptionAfficheOffre(){
      return this.description.substring(2);
    }
}

