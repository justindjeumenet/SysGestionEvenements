/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;

import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author ordinateur
 */
public class SommetPolygone implements Serializable{
    
    
     private static final long serialVersionUID = 1L; 
    private boolean selectione;
    private int rayon;
    private Point sommet;

   
    public SommetPolygone(Point point,int rayon) {

            this.selectione = false;         
            this.rayon=rayon;
            this.sommet=point;

    }

    public void changerSelection() {
            this.selectione = !this.selectione;
    }



    
     public boolean isSelected() {
        return this.selectione;
    }

    void translate(Point delta) {
        this.sommet.x = (int) (this.sommet.x + delta.x);
        this.sommet.y = (int) (this.sommet.y + delta.y);
         
    }
    
         public   boolean contains(double x, double y) {
        return (xIsInsideItemWidth(x) && yIsInsideItemHeight(y));
    }
    
    public boolean xIsInsideItemWidth(double x) {
            return (x < this.sommet.getX() + (rayon)) && (x > this.sommet.getX() - (rayon));
    }

    public boolean yIsInsideItemHeight(double y) {
            return (y < sommet.getY() + (rayon)) && (y > sommet.getY() - (rayon));
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }


        public Point getPoint() {
            return sommet;
    }
        
        public void setPoint(Point sommet) {
        this.sommet = sommet;
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
