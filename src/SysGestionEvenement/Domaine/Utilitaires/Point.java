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
public class Point 
{
    private double coordonner_X;
    private double coordonner_Y;
    private double coordonner_Z;
    
     Point(double coordonner_x, double coordonner_y, double coordonner_z ){
         this.coordonner_X = coordonner_x;
         this.coordonner_Y = coordonner_y;
         this.coordonner_Z = coordonner_z;
     }

    public double getCoordonner_X() {
        return coordonner_X;
    }

    public double getCoordonner_Y() {
        return coordonner_Y;
    }

    public double getCoordonner_Z() {
        return coordonner_Z;
    }

    public void setCoordonner_X(double coordonner_X) {
        this.coordonner_X = coordonner_X;
    }

    public void setCoordonner_Y(double coordonner_Y) {
        this.coordonner_Y = coordonner_Y;
    }

    public void setCoordonner_Z(double coordonner_Z) {
        this.coordonner_Z = coordonner_Z;
    }
     
     
    
}
