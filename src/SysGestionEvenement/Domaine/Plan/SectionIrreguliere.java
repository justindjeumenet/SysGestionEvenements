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
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ordinateur
 */
public class SectionIrreguliere extends Section implements Serializable
{
     private static final long serialVersionUID = 1L;
    //private int nbColonne;
    //private int nbRange;
    //private Point coinGauche;
    private Dimension dimensionSection;
    private boolean selectionne;
    private Point centreSection;
    private Point centreScene;
    private double distanceSectionScene;
   // private Point coinInfGauche;
   // private Point coinSupDroit;
   // private Point coinInfDroit;
    private Polygon polySectionIrr;
    private Rectangle rectPolySectionIrr;
    private List<Point> listPointSectionIrr;
    private Point pointdebut;
    private List <SommetPolygone> listSommet;
    
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

    public Polygon getPolySectionIrr() {
        return polySectionIrr;
    }

    public void setPolySectionIrr(Polygon polySectionIrr) {
        this.polySectionIrr = polySectionIrr;
    }

    public Rectangle getRectPolySectionIrr() {
        return rectPolySectionIrr;
    }

    public void setRectPolySectionIrr(Rectangle rectPolySectionIrr) {
        this.rectPolySectionIrr = rectPolySectionIrr;
    }
    
    
    public SectionIrreguliere(String nomSection,int elevationSection,int angleSection,EspaceVital espaceVitalSection,Point centreScene,Polygon poly,List <Point> sommetsSection,Point ptDebut) 
      {

          super(nomSection,elevationSection,angleSection,espaceVitalSection);
        
          ///double largeur=nbColonne*espaceVitalSection.getDimensionEspaceVital().width;
          //double longueur=nbRange*espaceVitalSection.getDimensionEspaceVital().height;
          //this.dimensionSection=new Dimension((int)largeur,(int)longueur);
          this.selectionne = false;
         // this.centreSection=new Point(this.coinGauche.x+this.dimensionSection.width/2,this.coinGauche.y+this.dimensionSection.height/2);
          this.centreScene=centreScene;
          this.polySectionIrr=poly;
          this.pointdebut=ptDebut;
          this.rectPolySectionIrr=this.polySectionIrr.getBounds();
          
          this.listPointSectionIrr=sommetsSection;

       //ajout pour calcul
          //this.distanceSectionScene= calculDistanceSectionIrrScene(centreSection, centreScene);
          
          this.centreSection=calculCentrePoly(this.listPointSectionIrr);        
          System.out.println("CENTRE POLYGONE+++++++++++++++++++"+centreSection);
            this.distanceSectionScene= calculDistanceSectionIrrScene(centreSection, centreScene);
           this.listSommet=new LinkedList();
          //Ajouter des cercles pour chaque sommet
          for (Point p : this.listPointSectionIrr) { 
              
              SommetPolygone s=new SommetPolygone(p,4);
              this.listSommet.add(s);
             // System.out.println("Sommet ajoute+++++++++++++++++++"+p);
            } 

         
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
          boolean trouveRange=false;

        int i=0;
        //System.out.println("ESPACE VITAL:"+super.getEspaceVitalSection().getDimensionEspaceVital());
        int espacex=super.getEspaceVitalSection().getDimensionEspaceVital().width;
        int espacey=super.getEspaceVitalSection().getDimensionEspaceVital().height;
        Point coinGaucheRange=new Point(this.rectPolySectionIrr.x,this.rectPolySectionIrr.y);
        while(!this.longueurSectionParcourue(coinGaucheRange))
        {  // System.out.println("while1");
            while((coinGaucheRange.x<(this.rectPolySectionIrr.x+this.rectPolySectionIrr.width))&&(trouveRange==false))
            {   //System.out.println("while2: "+coinGaucheRange);
              //  System.out.println("while2: "+(this.rectPolySectionIrr.x+this.rectPolySectionIrr.width));
                if((this.possibiliteSiege(coinGaucheRange, espacex, espacey)))
                {   
                     i++;
                    Range rangeSection = new Range(i,coinGaucheRange,0,new Dimension(this.rectPolySectionIrr.width,espacey),new Point(0,0),this.centreScene);
                  //  System.out.println("ajout d'une range1**************************************************************");
                    
                    trouveRange=true;
                   int nbSiege=0;
                    //ACTIVER CETTE LIGNE POUR AJOUTER DES SIEGES AUTO
                    if(auto)
                    {
                    nbSiege=this.ajouterSiegeAuto(rangeSection, espacex, espacey);
                    
                    Point centreRange=new Point(rangeSection.getCoinGaucheRange().x+(nbSiege*espacex/2),rangeSection.getCoinInfGaucheRange().y+espacey/2);
                    rangeSection.setCentreRange(centreRange);
                    Dimension dimensionRange=new Dimension(nbSiege*espacex,espacey);
                    rangeSection.setDimensionRange(dimensionRange);
                    rangeSection.setCoinInfGaucheRange(new Point(coinGaucheRange.x,coinGaucheRange.y+dimensionRange.height));
                    rangeSection.setCoinSupDroitRange(new Point(coinGaucheRange.x+dimensionRange.width,coinGaucheRange.y));
                    rangeSection.setCoinInfDroitRange(new Point(coinGaucheRange.x+dimensionRange.width,coinGaucheRange.y+dimensionRange.height));
                    }
                    rangeSection.setPolyRange(construirePolygone(rangeSection.getCoinGaucheRange(),rangeSection.getCoinSupDroitRange(),rangeSection.getCoinInfDroitRange(),rangeSection.getCoinInfGaucheRange()));
                    super.listRangeSection.add(rangeSection);   
                }
                else
                {
                    coinGaucheRange=new Point(coinGaucheRange.x+1,coinGaucheRange.y);
                }
            }
            if((trouveRange))
            {
                coinGaucheRange=new Point(this.rectPolySectionIrr.x,coinGaucheRange.y+espacey);
                trouveRange=false;
            }
            else
            {
                coinGaucheRange=new Point(this.rectPolySectionIrr.x,coinGaucheRange.y+1);
            }
        }
       
         for(Range item:super.getListRangeSection())
        {
            if (auto)
            {
            item.roterRange(item.getCoinGaucheRange(),item.getCoinSupDroitRange(),item.getCoinInfDroitRange(),item.getCoinInfGaucheRange(),centreSection, Math.toRadians(45));
            roterPoint(item.getCentreRange(),centreSection);
            }
            item.setPolyRange(construirePolygone(item.getCoinGaucheRange(),item.getCoinSupDroitRange(),item.getCoinInfDroitRange(),item.getCoinInfGaucheRange()));
            
         }
        if(auto)
        {  
            this.polySectionIrr.reset();
        
          for (Point p : this.listPointSectionIrr) { 
              
              roterPoint(p,this.centreSection);
              this.polySectionIrr.addPoint(p.x, p.y);
            } 
        }
        
      }
    
        public int ajouterSiegeAuto(Range rangeCourante,int espaceVx,int espaceVy)
    {
       int nbSiegeRange=0;
       Point cGaucheSiege=new Point(rangeCourante.getCoinGaucheRange());
       while(possibiliteSiege(cGaucheSiege, espaceVx,espaceVy))
       {
           nbSiegeRange++;
           Point centreSiege=new Point(rangeCourante.getCoinGaucheRange().x+espaceVx/2+(nbSiegeRange-1)*espaceVx,rangeCourante.getCoinGaucheRange().y+espaceVy/2);
           roterPoint(centreSiege,centreSection);
           rangeCourante.ajoutSiege(nbSiegeRange, centreSiege, espaceVx/4, Color.black, centreScene);
              
           cGaucheSiege=new Point(cGaucheSiege.x+espaceVx,cGaucheSiege.y);
           
       }
       return nbSiegeRange;
    }
    public void ajouterSiegeManuel(Range rangeCourante,int espaceVx,int espaceVy)
    {
       int nbSiegeRange=rangeCourante.getListSiegeRange().size();
       Point cGaucheSiege=new Point(rangeCourante.getCoinGaucheRange());
       if(possibiliteSiege(cGaucheSiege, espaceVx,espaceVy))
       {
           nbSiegeRange++;
           Point centreSiege=new Point(rangeCourante.getCoinGaucheRange().x+espaceVx/2+(nbSiegeRange-1)*espaceVx,rangeCourante.getCoinGaucheRange().y+espaceVy/2);
           //roterPoint(centreSiege,centreSection);
           rangeCourante.ajoutSiege(nbSiegeRange, centreSiege, espaceVx/4, Color.black, centreScene);
              
           cGaucheSiege=new Point(cGaucheSiege.x+espaceVx,cGaucheSiege.y);
           
       }
       
    }
    public boolean longueurSectionParcourue(Point cSupGaucheRange)
    {
        return cSupGaucheRange.y>=this.rectPolySectionIrr.y+this.rectPolySectionIrr.height;
    }
    public boolean possibiliteSiege(Point cSupGauchesiege,int largeur,int longueur)
    {
        return (this.polySectionIrr.contains(cSupGauchesiege.x+1,cSupGauchesiege.y+1)&&this.polySectionIrr.contains(cSupGauchesiege.x+largeur-1,cSupGauchesiege.y+1)&&this.polySectionIrr.contains(cSupGauchesiege.x+1,cSupGauchesiege.y+longueur-1)&&this.polySectionIrr.contains(cSupGauchesiege.x+largeur-1,cSupGauchesiege.y+longueur-1)&&cSupGauchesiege.x<this.rectPolySectionIrr.x+this.rectPolySectionIrr.width);
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
        return this.polySectionIrr.contains(x, y);
    }

         public boolean isSelected() {
        return this.selectionne;
    }

    void translate(Point delta) {
       //  this.pointdebut.x = (int) (this.pointdebut.x + delta.x);
       // this.pointdebut.y = (int) (this.pointdebut.y + delta.y);
        for (SommetPolygone itemso: this.listSommet){
               itemso.translate(delta);
            } 
        this.polySectionIrr.translate(delta.x, delta.y);
         
    }
     public Polygon construirePolygone(Point p1,Point p2,Point p3,Point p4)
            {
                            int[] xx = {(int) p1.getX(), (int) p2.getX(), (int)p3.getX(), (int) p4.getX()};
                int[] yy = {(int) p1.getY(), (int) p2.getY(), (int)p3.getY(), (int) p4.getY()};
                Polygon poly3 = new Polygon(xx, yy, xx.length);
                return poly3;
            
            }
      
      public void roterSection(Point p1,Point p2,Point p3,Point p4,Point center){
                double angle=Math.toRadians(45);
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

            public void roterPoint(Point p1,Point center){
                double angle=Math.toRadians(45);
              
               double x1 = p1.getX() - center.x;
               double y1 = p1.getY() - center.y;
  
                double temp_x1 = x1 * Math.cos(angle) + y1 * Math.sin(angle);
                double temp_y1 = -x1 * Math.sin(angle) + y1 * Math.cos(angle);

               p1.setLocation(temp_x1 + center.x, temp_y1 + center.y);
           

  
            }


      // ajout pour calcul  
    public double calculDistanceSectionIrrScene(Point centreSection, Point centreScene)
    {
        double distance = Math.sqrt(((centreScene.x - centreSection.x) * (centreScene.x - centreSection.x)) + ((centreScene.y - centreSection.y) * (centreScene.y - centreSection.y)));
        return Math.round(distance);
    }    
    
    
    
    
    
    
    
    
    
    
}
