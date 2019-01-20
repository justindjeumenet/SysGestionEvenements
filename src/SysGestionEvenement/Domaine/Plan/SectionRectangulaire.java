/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author alemyre
 */
public class SectionRectangulaire extends Section implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int nbColonne;
    private int nbRange;
    private Point coinGauche;
    private Dimension dimensionSection;
    private boolean selectionne;
    private Point centreSection;
    private Point centreScene;
    private double distanceSectionScene;
    private Point coinInfGauche;
    private Point coinSupDroit;
    private Point coinInfDroit;
    private Polygon polySection;
    private String nomSection;
    private int elevationSection;
    private int angleSection=90;
    private EspaceVital espaceVitalSection;
    private  double angleSectionScene;

    public SectionRectangulaire(String nomSection,int elevationSection,int angleSection,EspaceVital espaceVitalSection,int nbRange,int nbColonne,Point coinGauche,Point centreScene) 
    {
        super(nomSection,elevationSection,angleSection,espaceVitalSection);
        this.nbColonne = nbColonne;
        this.nbRange = nbRange;
        this.coinGauche = coinGauche;
        double largeur=nbColonne*espaceVitalSection.getDimensionEspaceVital().width;
        double longueur=nbRange*espaceVitalSection.getDimensionEspaceVital().height;
        this.dimensionSection=new Dimension((int)largeur,(int)longueur);
        this.selectionne = false;
        this.centreSection=new Point(this.coinGauche.x+this.dimensionSection.width/2,this.coinGauche.y+this.dimensionSection.height/2);
        this.centreScene=centreScene;
        this.coinInfGauche=new Point(this.coinGauche.x,this.coinGauche.y+dimensionSection.height);
        this.coinInfDroit=new Point(this.coinGauche.x+dimensionSection.width,this.coinGauche.y+dimensionSection.height);
        this.coinSupDroit=new Point(this.coinGauche.x+dimensionSection.width,this.coinGauche.y);
        this.polySection=construirePolygone(this.coinGauche,this.coinSupDroit,this.coinInfDroit,this.coinInfGauche);
        this.distanceSectionScene = calculDistanceSectionScene(this.centreSection, centreScene);
        //calculerAngle();
        this.angleSectionScene=Math.atan2(this.centreSection.getY()-this.centreScene.getY(),this.centreSection.getX()-this.centreScene.getX())-Math.atan2(this.coinSupDroit.getY()-this.coinGauche.getY(),this.coinSupDroit.getX()-this.coinGauche.getX());
        this.angleSection = (int) (Math.toRadians(this.angleSection)- angleSectionScene);
    }
    
    
    public double getDistanceSectionScene() {
        return distanceSectionScene;
    }

    public void setDistanceSectionScene(double distanceSectionScene) {
        this.distanceSectionScene = distanceSectionScene;
    }

    public Point getCoinInfGauche() {
        return coinInfGauche;
    }

    public void setCoinInfGauche(Point coinInfGauche) {
        this.coinInfGauche = coinInfGauche;
    }

    public Point getCoinSupDroit() {
        return coinSupDroit;
    }

    public void setCoinSupDroit(Point coinSupDroit) {
        this.coinSupDroit = coinSupDroit;
    }

    public Point getCoinInfDroit() {
        return coinInfDroit;
    }

    public void setCoinInfDroit(Point coinInfDroit) {
        this.coinInfDroit = coinInfDroit;
    }

    public Polygon getPolySection() {
        return polySection;
    }

    public void setPolySection(Polygon polySection) {
        this.polySection = polySection;
    }


    public Point getCentreScene() {
        return centreScene;
    }

    public void setCentreScene(Point centreScene) {
        this.centreScene = centreScene;
    }
    
    public Dimension getDimensionSection() {
        return dimensionSection;
    }

    public void setDimensionSection(Dimension dimensionSection) {
        this.dimensionSection = dimensionSection;
    }

    public int getNbColonne() {
        return nbColonne;
    }

    public void setNbColonne(int nbColonne) {
        this.nbColonne = nbColonne;
    }

    public int getNbRange() {
        return nbRange;
    }

    public void setNbRange(int nbRange) {
        this.nbRange = nbRange;
    }

    public Point getCoinGauche() {
        return coinGauche;
    }

    public void setCoinGauche(Point coinGauche) {
        this.coinGauche = coinGauche;
    }

    public void setAngleSection(int angleSection) {
        this.angleSection = angleSection;
        System.out.println("angle controlleur"+angleSection);
        for (Range itemRange : this.getListRangeSection()) {
             for (Siege itemSiege : itemRange.getListSiegeRange()){
                 roterPoint(itemSiege.getPoint(),this.centreSection);
             }
              itemRange.roterRange(itemRange.getCoinGaucheRange(),itemRange.getCoinSupDroitRange(),itemRange.getCoinInfDroitRange(),itemRange.getCoinInfGaucheRange(),centreSection,Math.toRadians(this.angleSection)-angleSectionScene);
              roterPoint(itemRange.getCentreRange(),centreSection);
              itemRange.setPolyRange(construirePolygone(itemRange.getCoinGaucheRange(),itemRange.getCoinSupDroitRange(),itemRange.getCoinInfDroitRange(),itemRange.getCoinInfGaucheRange()));
               
        }
                
                   
        roterSection(coinGauche,coinSupDroit,coinInfDroit,coinInfGauche,centreSection);
        setPolySection(construirePolygone(coinGauche,coinSupDroit,coinInfDroit,coinInfGauche));
        
    }
 

    public Point getCentreSection() {
        return centreSection;
    }

    public void setCentreSection(Point centreSection) {
        this.centreSection = centreSection;
    }
      public  void redistribuerSiege()
      {
          
      }


      public  void ajoutRange(int p_nbRange,int p_nbColonne,boolean auto)
      { 
        int largeurSiege=super.getEspaceVitalSection().getDimensionEspaceVital().width/2;
        int longueurSiege=super.getEspaceVitalSection().getDimensionEspaceVital().height/2;
        
        
                int largeurRange=this.dimensionSection.width;
        int longueurRange=this.dimensionSection.height/p_nbRange;
        for (int i=1;i<=p_nbRange;i++)
          { 
              Point coinGaucheRange=new Point(this.getCoinGauche().x,this.getCoinGauche().y+(i-1)*longueurRange);
              //roterPoint(coinGaucheRange,centreSection);
              Range rangeSection = new Range(i,coinGaucheRange,p_nbColonne,new Dimension(largeurRange,longueurRange),new Point(this.coinGauche.x+(largeurRange/2),this.coinGauche.y+longueurRange/2+(i-1)*longueurRange),this.centreScene);
              
              rangeSection.roterRange(rangeSection.getCoinGaucheRange(),rangeSection.getCoinSupDroitRange(),rangeSection.getCoinInfDroitRange(),rangeSection.getCoinInfGaucheRange(),centreSection,Math.toRadians(this.angleSection)-angleSectionScene);
              roterPoint(rangeSection.getCentreRange(),centreSection);
              rangeSection.setPolyRange(construirePolygone(rangeSection.getCoinGaucheRange(),rangeSection.getCoinSupDroitRange(),rangeSection.getCoinInfDroitRange(),rangeSection.getCoinInfGaucheRange()));
              for (int j=1;j<=p_nbColonne;j++)
              {
                  int rayon=super.getEspaceVitalSection().getDimensionEspaceVital().width/4;
                  Point centreSiege=new Point(this.getCoinGauche().x+(super.getEspaceVitalSection().getDimensionEspaceVital().width/2)+(j-1)*super.getEspaceVitalSection().getDimensionEspaceVital().width,this.getCoinGauche().y+(super.getEspaceVitalSection().getDimensionEspaceVital().height/2)+(i-1)*super.getEspaceVitalSection().getDimensionEspaceVital().height);
                  roterPoint(centreSiege,centreSection); 
                  rangeSection.ajoutSiege(j, centreSiege, rayon, Color.BLACK, centreScene);
                
              }  
                super.listRangeSection.add(rangeSection);
          
          }
        
        
      }
      
      
    void switchSelection() {
            this.selectionne = !this.selectionne;
             System.out.println("Section selectionnée");
             System.out.println(selectionne);
    }
    
    public   boolean contains(double x, double y) {
        return this.polySection.contains(x, y);
    }
  //  public   boolean contains(double x, double y) {
  //      return (xIsInsideItemWidth(x) && yIsInsideItemHeight(y));
  //  }
    
   public boolean xIsInsideItemWidth(double x) {
            return (x < this.centreSection.getX() + (dimensionSection.width/2)) && (x > this.centreSection.getX() - (dimensionSection.width/2));
    }

  public boolean yIsInsideItemHeight(double y) {
            return (y < centreSection.getY() + (dimensionSection.height/2)) && (y > centreSection.getY() - (dimensionSection.height/2));
    }

    
     public boolean isSelected() {
        return this.selectionne;
    }

    void translate(Point delta) {
        this.coinGauche.x = (int) (this.coinGauche.x + delta.x);
        this.coinGauche.y = (int) (this.coinGauche.y + delta.y);
        this.coinInfDroit.x = (int)(this.coinInfDroit.x +delta.x);
        this.coinInfDroit.y = (int)(this.coinInfDroit.y +delta.y);
        this.coinInfGauche.x = (int)(this.coinInfGauche.x +delta.x);
        this.coinInfGauche.y = (int)(this.coinInfGauche.y +delta.y);
        this.coinSupDroit.x = (int)(this.coinSupDroit.x +delta.x);
        this.coinSupDroit.y = (int)(this.coinSupDroit.y +delta.y);
        this.polySection.translate(delta.x, delta.y);
         
        //ajout pour mise à jour du centre lors du déplacement ainsi que la distance à la scène
        Point centreSectionRect = new Point(this.coinGauche.x+this.dimensionSection.width/2,this.coinGauche.y+this.dimensionSection.height/2);
        setCentreSection(centreSectionRect);
        System.out.println("Le centre de la section" + getCentreSection());
        
        double distanceSectionScene = calculDistanceSectionScene(this.centreSection, this.centreScene);
        setDistanceSectionScene(distanceSectionScene);
        
    }
    
    
       public void ModifierSectionRectangulaire(String nomSection,int elevationSection,int angleSection,EspaceVital espaceVitalSection,int nbRange,int nbColonne,Point coinGauche) 
      {

          //super(nomSection,elevationSection,angleSection,espaceVitalSection);
          super.setAngleSection(angleSection);
          super.setElevationSection(elevationSection);
          super.setEspaceVitalSection(espaceVitalSection);
        //  super.setNomSection(nomSection);
          this.nbColonne = nbColonne;
          this.nbRange = nbRange;
          //this.coinGauche = coinGauche;
          double largeur=nbColonne*espaceVitalSection.getLargeur();
          double longueur=nbRange*espaceVitalSection.getProfondeur();
          this.dimensionSection=new Dimension((int)largeur,(int)longueur);
          this.selectionne = false;
          
          this.centreSection=new Point(this.coinGauche.x+this.dimensionSection.width/2,this.coinGauche.y+this.dimensionSection.height/2);
          this.distanceSectionScene = calculDistanceSectionScene(this.centreSection, this.centreScene);
             
         
      }
       
           public boolean trouveRange(int p_numero)
   {
        boolean trouve=false; 
        for (Range item : this.listRangeSection) {
         
        if(item.getNumeroRange()==p_numero)
        {
              trouve= true;  
                
        }  
        
    }
    return trouve;
    }
 
    public  void modifierRange(int p_nbRange,int p_nbColonne)
      { 
          //VIDER LISTE AVANT
             while (!super.listRangeSection.isEmpty()) {
                    super.listRangeSection.remove(super.listRangeSection.size() - 1);
                     //System.out.println("section supprime une range");
                    }
        
        
            int largeurRange=this.dimensionSection.width;
            int longueurRange=this.dimensionSection.height/p_nbRange;
            for (int i=1;i<=p_nbRange;i++)
            { 
              Range rangeSection = new Range(i,new Point(this.getCoinGauche().x,this.getCoinGauche().y+(i-1)*longueurRange),p_nbColonne,new Dimension(largeurRange,longueurRange),new Point(this.coinGauche.x+(largeurRange/2),this.coinGauche.y+longueurRange/2+(i-1)*longueurRange),this.centreScene);
              for (int j=1;j<=p_nbColonne;j++)
              {
                  int rayon=super.getEspaceVitalSection().getDimensionEspaceVital().width/4;
                  Point centreSiege=new Point(this.getCoinGauche().x+(super.getEspaceVitalSection().getDimensionEspaceVital().width/2)+(j-1)*super.getEspaceVitalSection().getDimensionEspaceVital().width,this.getCoinGauche().y+(super.getEspaceVitalSection().getDimensionEspaceVital().height/2)+(i-1)*super.getEspaceVitalSection().getDimensionEspaceVital().height);
                   rangeSection.ajoutSiege(j, centreSiege, rayon, Color.BLACK, centreScene);
                
              }  
              
              
                super.listRangeSection.add(rangeSection);
                //System.out.println("section ajoute une nouvelle range");
          
          }
        
        
      }
      public Polygon construirePolygone(Point p1,Point p2,Point p3,Point p4)
            {
                            int[] xx = {(int) p1.getX(), (int) p2.getX(), (int)p3.getX(), (int) p4.getX()};
                int[] yy = {(int) p1.getY(), (int) p2.getY(), (int)p3.getY(), (int) p4.getY()};
                Polygon poly3 = new Polygon(xx, yy, xx.length);
                return poly3;
            
            }
      
      public void roterSection(Point p1,Point p2,Point p3,Point p4,Point center){
                //double angle=Math.toRadians(45);
                //center=this.centreSection;
                //double angle=calculerAngle();
                double angle=Math.toRadians(this.angleSection)-angleSectionScene;
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

            public void roterPoint(Point p1,Point center){
                //double angle=Math.toRadians(45);
              //double angle=calculerAngle();
              double angle=Math.toRadians(this.angleSection)-angleSectionScene;
               double x1 = p1.getX() - center.x;
               double y1 = p1.getY() - center.y;
  
                double temp_x1 = x1 * Math.cos(angle) + y1 * Math.sin(angle);
                double temp_y1 = -x1 * Math.sin(angle) + y1 * Math.cos(angle);

               p1.setLocation(temp_x1 + center.x, temp_y1 + center.y);
           

  
            }
    double calculerAngle()
    {
          double angleSectionScene=Math.atan2(this.centreSection.getY()-this.centreScene.getY(),this.centreSection.getX()-this.centreScene.getX())-Math.atan2(this.coinSupDroit.getY()-this.coinGauche.getY(),this.coinSupDroit.getX()-this.coinGauche.getX());
          //double angleSection=Math.toRadians(90)-angleSectionScene;
          //System.out.println("Angle avec centre en radians au depart: "+angleSectionScene);
          //System.out.println("Angle en radian a ajouter a la section pour atteindre 90: "+angleSection);
          return angleSectionScene;
          
    }


    public double calculDistanceSectionScene(Point centreSection, Point centreScene)
    {
        double distance = Math.sqrt(((centreScene.x - centreSection.x) * (centreScene.x - centreSection.x)) + ((centreScene.y - centreSection.y) * (centreScene.y - centreSection.y)));
        return Math.round(distance);
    }

    public void setListRangeSection(List<Range> listRangeSection) {
        this.listRangeSection = listRangeSection;
    }
    
    
    
    public SectionRectangulaire clone(){
            SectionRectangulaire uneSectRect =new SectionRectangulaire( nomSection,elevationSection, angleSection, espaceVitalSection, nbRange, nbColonne, coinGauche, centreScene);
           uneSectRect.setNbColonne(nbColonne);
           uneSectRect.setNbRange(nbRange);
           uneSectRect.setEspaceVitalSection(espaceVitalSection);
           uneSectRect.setListRangeSection(listRangeSection.stream().map(Range::clone).collect(Collectors.toList()));
           uneSectRect.setDimensionSection(dimensionSection);
           uneSectRect.setCentreScene(centreScene);
           uneSectRect.setCoinGauche(coinGauche);
           uneSectRect.setDistanceSectionScene(distanceSectionScene);
           uneSectRect.setElevationSection(elevationSection);
           uneSectRect.setCoinInfDroit(coinInfDroit);
           uneSectRect.setCoinInfGauche(coinInfGauche);
           uneSectRect.setCoinSupDroit(coinSupDroit);
           uneSectRect.setAngleSection(angleSection);
           uneSectRect.setPolySection(polySection);
           uneSectRect.setCentreSection(centreSection);
          // uneSectRect.setListPointSection(listPointSection.stream().map(Point::clone).collect(Collectors.toList()));
           uneSectRect.setListSiegeSection(listSiegeSection.stream().map(Siege::clone).collect(Collectors.toList()));
           return uneSectRect;
            }

         
}
