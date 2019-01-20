/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ordinateur
 */
public class SectionAdmissionGenerale extends Section implements Serializable{
    
       private static final long serialVersionUID = 1L;
    private Dimension dimensionSection;
    private boolean selectionne;
    private Point centreSection;
    private Point centreScene;
    private double distanceSectionScene;
    private Polygon polySectionGen;
    private List<Point> listPointSectionGen;
    private Point pointdebut;
    private List <SommetPolygone> listSommet;
    private int quantitePersonnes;
       
    
    public SectionAdmissionGenerale(String nomSection,int elevationSection,int angleSection,EspaceVital espaceVitalSection,Point centreScene,Polygon poly,List <Point> sommetsSection,Point ptDebut,int quantitePersonnes) 
      {
          super(nomSection,elevationSection,angleSection,espaceVitalSection);
          this.quantitePersonnes=quantitePersonnes;
           this.selectionne = false;
          this.centreScene=centreScene;
          this.polySectionGen=poly;
          this.pointdebut=ptDebut;         
          this.listPointSectionGen=sommetsSection;      
          this.centreSection=calculCentrePoly(this.listPointSectionGen);        
          System.out.println("CENTRE POLYGONE+++++++++++++++++++"+centreSection);
          this.distanceSectionScene= calculDistanceSectionGenScene(centreSection, centreScene);
          this.listSommet=new LinkedList();
          //Ajouter des cercles pour chaque sommet
          for (Point p : this.listPointSectionGen) { 
              
              SommetPolygone s=new SommetPolygone(p,4);
              this.listSommet.add(s);
             
            } 
          this.polySectionGen.reset();
         for (Point p : this.listPointSectionGen) { 
              
              roterPoint(p,this.centreSection);
              this.polySectionGen.addPoint(p.x, p.y);
            } 
      
      }

    public List<Point> getListPointSectionGen() {
        return listPointSectionGen;
    }

    public void setListPointSectionGen(List<Point> listPointSectionGen) {
        this.listPointSectionGen = listPointSectionGen;
    }

    public int getQuantitePersonnes() {
        return quantitePersonnes;
    }

    public void setQuantitePersonnes(int quantitePersonnes) {
        this.quantitePersonnes = quantitePersonnes;
    }
    
    
       
     public Point getPointdebut() {
        return pointdebut;
    }

    public void setPointdebut(Point pointdebut) {
        this.pointdebut = pointdebut;
    }

    public Dimension getDimensionSection() {
        return dimensionSection;
    }

    public void setDimensionSection(Dimension dimensionSection) {
        this.dimensionSection = dimensionSection;
    }

    public Point getCentreSection() {
        return centreSection;
    }

    public void setCentreSection(Point centreSection) {
        this.centreSection = centreSection;
    }

    public double getDistanceSectionScene() {
        return distanceSectionScene;
    }

    public void setDistanceSectionScene(double distanceSectionScene) {
        this.distanceSectionScene = distanceSectionScene;
    }

    public Polygon getPolySectionGen() {
        return polySectionGen;
    }

    public void setPolySectionGen(Polygon polySectionGen) {
        this.polySectionGen = polySectionGen;
    }


    
    
    public List<SommetPolygone> getListSommet() {
        return listSommet;
    }

    public void setListSommet(List<SommetPolygone> listSommet) {
        this.listSommet = listSommet;
    }
    
    public static Point calculCentrePoly(List<Point> points) { 
        double xsum = 0; 
        double ysum = 0; 
 
        double n = points.size(); 
 
        for (Point p : points) { 
            xsum += p.x; 
            ysum += p.y; 
 
        } 
        Point centrepoly =new Point((int)(xsum / n),(int)(ysum / n)); 
        return centrepoly; 
    } 
    
    public  void ajoutRange(int p_nbRange,int p_nbColonne,boolean auto)
      {
         
      }
    
     
        public  void redistribuerSiege()
      {
          
      }


     void switchSelection() {
            this.selectionne = !this.selectionne;
            // System.out.println("Section selectionnée");
             System.out.println(selectionne);
    }
    
    public   boolean contains(double x, double y) {
        return this.polySectionGen.contains(x, y);
    }

         public boolean isSelected() {
        return this.selectionne;
    }

    void translate(Point delta) {
      //  this.pointdebut.x = (int) (this.pointdebut.x + delta.x);
      //  this.pointdebut.y = (int) (this.pointdebut.y + delta.y);
        for (SommetPolygone itemso: this.listSommet){
               itemso.translate(delta);
            } 
        this.polySectionGen.translate(delta.x, delta.y);
         
    }
     public Polygon construirePolygone(Point p1,Point p2,Point p3,Point p4)
            {
                            int[] xx = {(int) p1.getX(), (int) p2.getX(), (int)p3.getX(), (int) p4.getX()};
                int[] yy = {(int) p1.getY(), (int) p2.getY(), (int)p3.getY(), (int) p4.getY()};
                Polygon poly3 = new Polygon(xx, yy, xx.length);
                return poly3;
            
            }
     
   
      

            public void roterPoint(Point p1,Point center){
               double angle=Math.toRadians(45);
             // double angle=calculerAngle();
               double x1 = p1.getX() - center.x;
               double y1 = p1.getY() - center.y;
  
                double temp_x1 = x1 * Math.cos(angle) + y1 * Math.sin(angle);
                double temp_y1 = -x1 * Math.sin(angle) + y1 * Math.cos(angle);

               p1.setLocation(temp_x1 + center.x, temp_y1 + center.y);
           

  
            }


      // ajout pour calcul  
    public double calculDistanceSectionGenScene(Point centreSection, Point centreScene)
    {
        double distance = Math.sqrt(((centreScene.x - centreSection.x) * (centreScene.x - centreSection.x)) + ((centreScene.y - centreSection.y) * (centreScene.y - centreSection.y)));
        return Math.round(distance);
    }    
    
    
    
    
     double calculerAngle()
    {
        Point coinSupGauchederange=new Point();
        Point coininfGauchederange=new Point();
        Point coininfDroitderange=new Point();
        Point coinsupDroitderange=new Point();
        //Recuperation des dimensions d'une range
         for(Range itemRange:super.getListRangeSection())
        {
            coinSupGauchederange=itemRange.getCoinGaucheRange();
            coininfGauchederange=itemRange.getCoinInfGaucheRange();
            coininfDroitderange=itemRange.getCoinInfDroitRange();
            coinsupDroitderange=itemRange.getCoinSupDroitRange(); 
        }


// Range range1=
          double angleSectionScene=Math.atan2(this.centreSection.getY()-this.centreScene.getY(),this.centreSection.getX()-this.centreScene.getX())-Math.atan2(coinsupDroitderange.getY()-coinSupGauchederange.getY(),coinsupDroitderange.getX()-coinSupGauchederange.getX());
          double angleSection=Math.toRadians(90)-angleSectionScene;
        //  System.out.println("Angle avec centre en radians au depart: "+angleSectionScene);
        //  System.out.println("Angle en radian a ajouter a la section pour atteindre 90: "+angleSection);
          return angleSection;
          
    }


    
    
    
        
    
     
    
    
    
    
    
    
    
}
