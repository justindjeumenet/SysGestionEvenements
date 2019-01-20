/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;

import SysGestionEvenement.Domaine.PrixOffre.Offre;
import SysGestionEvenement.Domaine.PrixOffre.Prix;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.Serializable;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alemyre
 */
public class Siege implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int numeroSiege;  
    private Color couleurSiege;
    private Prix prixSiege;
    private Point centreSiege;
    private boolean selectione;
    private int rayon;
    private Point centreScene;
    private double distanceSiegeScene;
   // private ListOffre listOffreSiege;
    private Offre offre;
    private double pourcentageOffre;
    private String descriptionOffre;
    private final List<Offre> listOffreSiege;
   
    public Siege(int numeroSiege,Point point,int rayon, Color couleurSiege,Point centreScene) {
            this.numeroSiege = numeroSiege;
            this.prixSiege = new Prix(0,Color.BLACK);
            this.couleurSiege = couleurSiege;
            this.selectione = false;         
            this.rayon=rayon;
            this.centreScene=centreScene;
            this.centreSiege=point;
            this.distanceSiegeScene = calculDistanceSiegeScene(centreSiege, centreScene);
            this.offre = new Offre(0, "");
            this.descriptionOffre = offre.getDescriptionOffre();
            this.listOffreSiege = new ArrayList<>();
    }

    public double getDistanceSiegeScene()
    {
        return distanceSiegeScene;
    }

    public Prix getPrixSiege() {
        return prixSiege;
    }

    public void setPrixSiege(Prix prixSiege) {
        this.prixSiege = prixSiege;
    }

    public Point getCentreSiege() {
        return centreSiege;
    }

    public void setCentreSiege(Point centreSiege) {
        this.centreSiege = centreSiege;
    }
    public int getNumeroSiege() {
            return numeroSiege;
    }
   

    public Prix getPrice() {
            return prixSiege;
    }


    public Color getCouleurSiege() {
        return couleurSiege;
    }

    public void changerSelection() {
            this.selectione = !this.selectione;
    }
    public void assignerNumeroSiege(int numeroSiege) {
            this.numeroSiege=numeroSiege;
    }


    public void assignerPrice(Prix prixSiege) {
            this.prixSiege=prixSiege;
    }


    public void assignerCouleurSiege(Color couleurSiege) {
        this.couleurSiege = couleurSiege;
    }
    
     public boolean isSelected() {
        return this.selectione;
    }

    void translate(Point delta) {
        this.centreSiege.x = (int) (this.centreSiege.x + delta.x);
        this.centreSiege.y = (int) (this.centreSiege.y + delta.y);
         
    }
    
         public   boolean contains(double x, double y) {
        return (xIsInsideItemWidth(x) && yIsInsideItemHeight(y));
    }
    
    public boolean xIsInsideItemWidth(double x) {
            return (x < this.centreSiege.getX() + (rayon)) && (x > this.centreSiege.getX() - (rayon));
    }

    public boolean yIsInsideItemHeight(double y) {
            return (y < centreSiege.getY() + (rayon)) && (y > centreSiege.getY() - (rayon));
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public Point getCentreScene() {
        return centreScene;
    }

    public void setCentreScene(Point centreScene) {
        this.centreScene = centreScene;
    }
    
        public Point getPoint() {
            return centreSiege;
    }
        
        public void setPoint(Point centreSiege) {
        this.centreSiege = centreSiege;
    }
        public void modifierSiege(int numeroSiege,Point point,int rayon, Color couleurSiege,Point centreScene) {
            this.numeroSiege = numeroSiege;
            this.prixSiege = new Prix(0,Color.BLACK);
            this.couleurSiege = couleurSiege;
            this.selectione = false;         
            this.rayon=rayon;
            this.centreScene=centreScene;
            this.centreSiege=point;
            this.distanceSiegeScene = calculDistanceSiegeScene(centreSiege, centreScene);
    }
        
    public double calculDistanceSiegeScene(Point centreSiege, Point centreScene)
    {
        double distance = Math.sqrt(((centreScene.x - this.centreSiege.x) * (centreScene.x - centreSiege.x)) + ((centreScene.y - centreSiege.y) * (centreScene.y - centreSiege.y)));
        return Math.round(distance);
    }
    
    public String getDescriptionOffre() {
         if(this.descriptionOffre == null){
            this.descriptionOffre = "";
         }
         return descriptionOffre;
    }
    
     public void assignerDescription(String descriptionOffre) {
       Font monFont = new Font("Ubuntu", Font.BOLD, 20);
       
       this.descriptionOffre = offre.getDescriptionOffre().toUpperCase().substring(0, 2);
    }

    public void assignerListOffre(Offre myOffre){
        listOffreSiege.add(myOffre);
    }
    
    public List<Offre> getOffreFromList(){
        return listOffreSiege;
    }
    
    public void removeOffreFromList(int nombre){
        listOffreSiege.remove(nombre);
    }
    
    public void assignerOffre(Offre offre) {
       this.offre = offre;
    }
    
    public Offre getOffre(String descriptionOffre) {
       return offre;
    }

    public void setCouleurSiege(Color couleurSiege) {
        this.couleurSiege = couleurSiege;
    }

    public void setDistanceSiegeScene(double distanceSiegeScene) {
        this.distanceSiegeScene = distanceSiegeScene;
    }
    
    
    public Siege clone(){
            Siege unSiege = new Siege(numeroSiege, centreScene, rayon,  couleurSiege, centreScene);
            unSiege.setPrixSiege(prixSiege);
            unSiege.setPoint(centreSiege);
            unSiege.setRayon(rayon);
            unSiege.setCentreScene(centreScene);
            unSiege.setCouleurSiege(couleurSiege);
            unSiege.setDistanceSiegeScene(distanceSiegeScene);
                      
            return unSiege;
        }
        
}
