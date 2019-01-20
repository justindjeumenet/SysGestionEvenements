/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;

import SysGestionEvenement.Domaine.PrixOffre.Prix;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;
import java.util.stream.Collectors;
/**
 *
 * @author alemyre
 */
public class Range implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int numeroRange;
    private int nbColonne;
    private Point centreRange;
    private List<Siege> listSiegeRange;
    private Dimension dimensionRange;
    private boolean selectione;
    private Point centreScene;
    private double distanceRangeScene;
    private Polygon polyRange;
    private Point coinGaucheRange;
    private Point coinInfGaucheRange;
    private Point coinSupDroitRange;
    private Point coinInfDroitRange;

    public Polygon getPolyRange() {
        return polyRange;
    }

    public void setPolyRange(Polygon polyRange) {
        this.polyRange = polyRange;
    }

    public Point getCoinInfGaucheRange() {
        return coinInfGaucheRange;
    }

    public void setCoinInfGaucheRange(Point coinInfGaucheRange) {
        this.coinInfGaucheRange = coinInfGaucheRange;
    }

    public Point getCoinSupDroitRange() {
        return coinSupDroitRange;
    }

    public void setCoinSupDroitRange(Point coinSupDroitRange) {
        this.coinSupDroitRange = coinSupDroitRange;
    }

    public Point getCoinInfDroitRange() {
        return coinInfDroitRange;
    }

    public void setCoinInfDroitRange(Point coinInfDroitRange) {
        this.coinInfDroitRange = coinInfDroitRange;
    }

    public double getDistanceRangeScene() {
        return distanceRangeScene;
    }

    public void setDistanceRangeScene(double distanceRangeScene) {
        this.distanceRangeScene = distanceRangeScene;
    }

    public Point getCoinGaucheRange() {
        return coinGaucheRange;
    }

    public void setCoinGaucheRange(Point coinGaucheRange) {
        this.coinGaucheRange = coinGaucheRange;
    }


    public Point getCentreScene() {
        return centreScene;
    }

    public void setCentreScene(Point centreScene) {
        this.centreScene = centreScene;
    }
    
    public int getNbColonne() {
        return nbColonne;
    }

    public void setNbColonne(int nbColonne) {
        this.nbColonne = nbColonne;
    }
       
    public List<Siege> getListSiegeRange() {
        return listSiegeRange;
    }

    public void setListSiegeRange(List<Siege> listSiegeRange) {
        this.listSiegeRange = listSiegeRange;
    }
    private Prix prixRange;
    
    public Range(int p_numeroRange,Point coinGaucheRange,int p_nbColonne,Dimension dimension,Point centreRange,Point centreScene) {
            this.numeroRange = p_numeroRange;
            this.nbColonne = p_nbColonne;
            this.listSiegeRange=new LinkedList<Siege>();
            this.prixRange = new Prix(0,Color.BLUE);
            this.dimensionRange=dimension;
            this.centreRange=centreRange;
            this.centreScene=centreScene;
            this.coinGaucheRange=coinGaucheRange;
           // this.rectangleRange=new Rectangle(coinGaucheRange.x,coinGaucheRange.y,dimension.width,dimension.height);
            this.coinInfGaucheRange=new Point(this.coinGaucheRange.x,this.coinGaucheRange.y+dimension.height);
            this.coinInfDroitRange=new Point(this.coinGaucheRange.x+dimension.width,this.coinGaucheRange.y+dimension.height);
            this.coinSupDroitRange=new Point(this.coinGaucheRange.x+dimension.width,this.coinGaucheRange.y);
            this.polyRange=new Polygon();
           // this.polyRange=construirePolygone(this.coinGaucheRange,this.coinSupDroitRange,this.coinInfDroitRange,this.coinInfGaucheRange);
            this.distanceRangeScene = calculDistanceSectionScene(centreRange, centreScene);
            System.out.println("distance du centre de la rangé à la scène" + distanceRangeScene);
            System.out.println("Centre Rangé" + centreRange);
            System.out.println("Centre Scène" + centreScene);
    }

      
        public void roterRange(Point p1,Point p2,Point p3,Point p4,Point center, double angle){
                //double angle=Math.toRadians(45);
                //center=this.centreSection;
                double x1 = p1.getX() - center.x;
                double y1 = p1.getY() - center.y;
                double x2 = p2.getX() - center.x;
                double y2 = p2.getY() - center.y;
                double x3 = p3.getX() - center.x;
                double y3 = p3.getY() - center.y;
                double x4 = p4.getX() - center.x;
                double y4 = p4.getY() - center.y;
                
                double temp_x1 = x1 * Math.cos(angle) + y1 * Math.sin(angle);
                double temp_y1 = -x1 * Math.sin(angle) + y1 * Math.cos(angle);
                double temp_x2 = x2 * Math.cos(angle) +y2 * Math.sin(angle);
                double temp_y2 = -x2 * Math.sin(angle) + y2 * Math.cos(angle);
                double temp_x3 = x3 * Math.cos(angle) +y3 * Math.sin(angle);
                double temp_y3 = -x3 * Math.sin(angle) +y3 * Math.cos(angle);
                double temp_x4 = x4 * Math.cos(angle) +y4 * Math.sin(angle);
                double temp_y4 = -x4 * Math.sin(angle) + y4* Math.cos(angle);

                p1.setLocation(temp_x1 + center.x, temp_y1 + center.y);
                p2.setLocation(temp_x2 + center.x, temp_y2 + center.y);
                p3.setLocation(temp_x3 + center.x, temp_y3 + center.y);
                p4.setLocation(temp_x4 + center.x, temp_y4 + center.y);
        
            }

    public Point getCentreRange() {
        return centreRange;
    }

    public void setCentreRange(Point centreRange) {
        this.centreRange = centreRange;
        // on update la distance à la scène
        setDistanceRangeScene(calculDistanceSectionScene(centreRange, centreScene)); 
    }
    public int getNumeroRange() {
            return numeroRange;
    }


    public Prix getPrixRange() {
            return prixRange;
    }
    public void assignerPrice(Prix prixRange) {
            this.prixRange=prixRange;
    }

       
     public  void ajoutSiege(int p_numerosiege,Point p_point,int rayon,Color p_couleurSiege,Point p_centreScene)
      {
          
            Siege siegeRange = new Siege(p_numerosiege,p_point,rayon,p_couleurSiege,p_centreScene);
            //System.out.println("rangee a creer un siege ");
            this.listSiegeRange.add(siegeRange);
          
      }
     
     
     
    public void changerSelection() {
        //System.out.println("++++++++STATUTRANGECHANGE++++++++++++++++++++++ "+this.selectione);
            this.selectione = !this.selectione;
           // System.out.println("++++++++STATUTRANGECHANGE++++++++++++++++++++++ "+this.selectione+this.numeroRange);
    }
     public boolean isSelected() {
        return this.selectione;
    }
     
   //  public   boolean contains(double x, double y) {
   //     return (xIsInsideItemWidth(x) && yIsInsideItemHeight(y));
   // }
      public   boolean contains(double x, double y) {
        return this.polyRange.contains(x, y);
    }
    
    public boolean xIsInsideItemWidth(double x) {
            return (x < this.centreRange.getX() + (dimensionRange.width/2)) && (x > this.centreRange.getX() - (dimensionRange.width/2));
    }

    public boolean yIsInsideItemHeight(double y) {
            return (y < centreRange.getY() + (dimensionRange.height/2)) && (y > centreRange.getY() - (dimensionRange.height/2));
    }
    void translate(Point delta) {
        this.coinGaucheRange.x = (int) (this.coinGaucheRange.x + delta.x);
        this.coinGaucheRange.y = (int) (this.coinGaucheRange.y + delta.y);
        this.polyRange.translate(delta.x, delta.y);
         
    }

    public Dimension getDimensionRange() {
        return dimensionRange;
    }

    public void setDimensionRange(Dimension dimensionRange) {
        this.dimensionRange = dimensionRange;
    }

    public void modifierRange(int p_numeroRange,int p_nbColonne,Dimension dimension,Point centreRange,Point centreScene) {
            this.numeroRange = p_numeroRange;
            this.nbColonne = p_nbColonne;
            //this.listSiegeRange=new LinkedList<Siege>();
            this.prixRange = new Prix(0,Color.BLUE);
            this.dimensionRange=dimension;
            this.centreRange=centreRange;
            this.centreScene=centreScene;
            this.distanceRangeScene = calculDistanceSectionScene(centreRange, centreScene);
    }
    
    public boolean trouveSiege(int p_numero)
   {
        boolean trouve=false; 
    for (Siege item : this.listSiegeRange) {
         
        if(item.getNumeroSiege()==p_numero)
        {
              trouve= true;  
                
        }  
        
    }
    return trouve;
    }
    
    public  void modifierSiege(int p_numerosiege,Point p_point,int rayon,Color p_couleurSiege,Point p_centreScene)
      {
          for (Siege item : this.listSiegeRange) {
              
              if(trouveSiege(p_numerosiege))
                {
                    item.modifierSiege(p_numerosiege,p_point,rayon,p_couleurSiege,p_centreScene);
                     System.out.println("rangee a modifie un siege ");
                }
            
          }
          
      }
    
    public double calculDistanceSectionScene(Point centreRange, Point centreScene)
    {
        double distance = Math.sqrt(((centreScene.x - centreRange.x) * (centreScene.x - centreRange.x)) + ((centreScene.y - centreRange.y) * (centreScene.y - centreRange.y)));
        return Math.round(distance);
    }
    
    public Range clone(){
        Range uneRange = new Range(numeroRange,coinGaucheRange,nbColonne,dimensionRange,centreRange,centreScene);
        uneRange.setListSiegeRange(listSiegeRange.stream().map(Siege::clone).collect(Collectors.toList()));
        uneRange.setNbColonne(nbColonne);
        uneRange.setPolyRange(polyRange);
        uneRange.setDimensionRange(dimensionRange);
        return uneRange;
        
    }

    
}
