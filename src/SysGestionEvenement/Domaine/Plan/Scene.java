/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;
import SysGestionEvenement.Domaine.Utilitaires.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;

/**
 *
 * @author alemyre
 */
public class Scene implements Serializable
{
    //private Point debutScene;
    private static final long serialVersionUID = 1L;
    private Point pointdebut;
    private Point pointfint;
    private Point centreScene;
    private boolean selectionne;
    private int longueur ;
    private int largeur ;
    private int angle1;
    private Polygon polygoneScene;
    private Point pointDebutInf;
    private Point pointFinSup;

    public Scene(Point pointdebut, Point pointfint) {
        this.pointdebut = pointdebut;
        this.pointfint = pointfint;
        this.selectionne =false;
        this.largeur = (int)(pointfint.x - pointdebut.x);
        this.longueur = (int)(pointfint.y - pointdebut.y);
        this.centreScene = new Point((pointdebut.x + largeur/2),(pointdebut.y + longueur/2));
        this.pointDebutInf= new Point(pointdebut.x, pointdebut.y + longueur);
        this.pointFinSup = new Point(pointfint.x, pointfint.y - longueur);
        this.angle1 =0;
        this.polygoneScene = construirePolygoneScene( pointdebut, pointFinSup,pointfint,pointDebutInf);
    }
     
    
    public Point getPointdebut() {
        return pointdebut;
    }

    public Point getPointfint() {
        return pointfint;
    }

    public void setPointdebut(Point pointdebut) {
        this.pointdebut = pointdebut;
    }

    public void setPointfint(Point pointfint) {
        this.pointfint = pointfint;
    }
    
    public   boolean contains(double x, double y) {
        //return (xIsInsideItemWidth(x) && yIsInsideItemHeight(y));
        return this.polygoneScene.contains(x, y);
    }
    
    public boolean xIsInsideItemWidth(double x) {
            return (x < this.centreScene.getX() + (largeur/2)) && (x > this.centreScene.getX() - (largeur/2));
    }

  public boolean yIsInsideItemHeight(double y) {
            return (y < centreScene.getY() + (longueur/2)) && (y > centreScene.getY() - (longueur/2));
    }
    
    

    public Point getCentreScene() {
        return centreScene;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public boolean isSelectionne() {
        return selectionne;
    }
    
    

    public void setCentreScene(Point centreScene) {
        this.centreScene = centreScene;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
    
    public boolean isSelected() {
        return this.selectionne;
    }

    public int getAngle1() {
        return angle1;
    }

    public Polygon getPolygoneScene() {
        return polygoneScene;
    }

    public Point getPointDebutInf() {
        return pointDebutInf;
    }

    public Point getPointFinSup() {
        return pointFinSup;
    }

    public void setAngle1(int angle1) {
        this.angle1 = angle1;
        roterScene(pointdebut,pointFinSup,pointfint,pointDebutInf,centreScene );
        setPolygoneScene(construirePolygoneScene( pointdebut, pointFinSup,pointfint,pointDebutInf));
    }

    public void setPolygoneScene(Polygon polygoneScene) {
        this.polygoneScene = polygoneScene;
    }

    public void setPointDebutInf(Point pointDebutInf) {
        this.pointDebutInf = pointDebutInf;
    }

    public void setPointFinSup(Point pointFinSup) {
        this.pointFinSup = pointFinSup;
    }
    
    

    void translate(Point delta) {
        this.pointdebut.x = (int) (this.pointdebut.x + delta.x);
        this.pointdebut.y = (int) (this.pointdebut.y + delta.y);
        this.pointfint.x = (int) (this.pointfint.x + delta.x);
        this.pointfint.y = (int) (this.pointfint.y + delta.y);
        this.pointFinSup.x =(int)(this.pointFinSup.x + delta.x);
        this.pointFinSup.y =(int)(this.pointFinSup.y + delta.y);
        this.pointDebutInf.x =(int)(this.pointDebutInf.x + delta.x);
        this.pointDebutInf.y =(int)(this.pointDebutInf.y + delta.y);
        this.polygoneScene.translate(delta.x, delta.y);
        
        //pour update du centre lors du déplacement
        Point nouveauCentre = new Point((pointdebut.x + largeur/2),(pointdebut.y + longueur/2));
        setCentreScene(nouveauCentre);
        System.out.println(this.centreScene);
    }
    
    void switchSelection() {
            this.selectionne = !this.selectionne;
             System.out.println("Scene selectionnée");
             System.out.println(selectionne);
    }
    
    public Scene Clone(){
        Scene uneScene = new Scene(pointdebut, pointfint);
        uneScene.setPointdebut(pointdebut);
        uneScene.setPointfint(pointfint);
        uneScene.setLargeur(largeur);
        uneScene.setLongueur(longueur);
        uneScene.setCentreScene(centreScene);
        
        
        return uneScene;
    }
    
    public void roterScene(Point pointUn, Point pointDeux, Point pointTrois, Point pointQuatre, Point centre){
                double angle = Math.toRadians(angle1);
                double x1 = pointUn.getX() - centre.x;
                double y1 = pointUn.getY() - centre.y;
                double x2 = pointDeux.getX() - centre.x;
                double y2 = pointDeux.getY() - centre.y;
                double x3 = pointTrois.getX() - centre.x;
                double y3 = pointTrois.getY() - centre.y;
                double x4 = pointQuatre.getX() - centre.x;
                double y4 = pointQuatre.getY() - centre.y;
                
                double temp_x1 = x1 * Math.cos(angle) + y1 * Math.sin(angle);
                double temp_y1 = -x1 * Math.sin(angle) + y1 * Math.cos(angle);
                double temp_x2 = x2 * Math.cos(angle) +y2 * Math.sin(angle);
                double temp_y2 = -x2 * Math.sin(angle) + y2 * Math.cos(angle);
                double temp_x3 = x3 * Math.cos(angle) +y3 * Math.sin(angle);
                double temp_y3 = -x3 * Math.sin(angle) +y3 * Math.cos(angle);
                double temp_x4 = x4 * Math.cos(angle) +y4 * Math.sin(angle);
                double temp_y4 = -x4 * Math.sin(angle) + y4* Math.cos(angle);

                pointUn.setLocation(temp_x1 + centre.x, temp_y1 + centre.y);
                pointDeux.setLocation(temp_x2 + centre.x, temp_y2 + centre.y);
                pointTrois.setLocation(temp_x3 + centre.x, temp_y3 + centre.y);
                pointQuatre.setLocation(temp_x4 + centre.x, temp_y4 + centre.y);
           
    }
     public Polygon construirePolygoneScene(Point p1,Point p2,Point p3,Point p4)
            {
                            int[] xx = {(int) p1.getX(), (int) p2.getX(), (int)p3.getX(), (int) p4.getX()};
                int[] yy = {(int) p1.getY(), (int) p2.getY(), (int)p3.getY(), (int) p4.getY()};
                Polygon poly3 = new Polygon(xx, yy, xx.length);
                return poly3;
            
            }
    


    
}
