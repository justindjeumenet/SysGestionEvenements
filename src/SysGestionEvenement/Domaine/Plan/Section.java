/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;

import SysGestionEvenement.Domaine.PrixOffre.Prix;
import SysGestionEvenement.Domaine.Utilitaires.Dimension;
import SysGestionEvenement.Domaine.Utilitaires.ListPoints;
import java.awt.Point;
import java.awt.Polygon;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;
/**
 *
 * @author alemyre
 */
public abstract class Section implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Prix prixSection;
    private EspaceVital espaceVitalSection;
    private String nomSection;
    private int elevationSection;
    private int angleSection;
    List<Siege> listSiegeSection;
    public List<Range> listRangeSection;
    private List<Point> listPointSection;
   
    public Section(String nomSection,int elevationSection,int angleSection,EspaceVital espaceVitalSection) {
       this.nomSection=nomSection; 
       this.elevationSection=elevationSection;
       this.angleSection=angleSection;
       this.espaceVitalSection=espaceVitalSection;
       this.listRangeSection = new LinkedList<Range>();
       //this.prixSection=new Prix(0);
       
    }

    public Prix getPrixSection() {
        return prixSection;
    }

    public void setPrixSection(Prix prixSection) {
        this.prixSection = prixSection;
    }

    public EspaceVital getEspaceVitalSection() {
        return espaceVitalSection;
    }

    public void setEspaceVitalSection(EspaceVital espaceVitalSection) {
        this.espaceVitalSection = espaceVitalSection;
    }

    public String getNomSection() {
        return nomSection;
    }

    public void setNomSection(String nomSection) {
        this.nomSection = nomSection;
    }

    public int getElevationSection() {
        return elevationSection;
    }

    public void setElevationSection(int elevationSection) {
        this.elevationSection = elevationSection;
    }

    public int getAngleSection() {
        return angleSection;
    }

    public void setAngleSection(int angleSection) {
        this.angleSection = angleSection;
    }

    public List<Siege> getListSiegeSection() {
        return listSiegeSection;
    }

    public void setListSiegeSection(List<Siege> listSiegeSection) {
        this.listSiegeSection = listSiegeSection;
    }

    public List<Range> getListRangeSection() {
        return listRangeSection;
    }

    public void setListRangeSection(List<Range> listRangeSection) {
        this.listRangeSection = listRangeSection;
    }

    public List<Point> getListPointSection() {
        return listPointSection;
    }
    public Polygon getPolygoneSection() {
        Polygon polySection=new Polygon();
        return polySection;
    }

    public void setListPointSection(List<Point> listPointSection) {
        this.listPointSection = listPointSection;
    }
    public void reorienterSection(int angleSection,List<Point> listPointSection) {
        this.angleSection = angleSection;
        //Modifier aussi les points de la section
    }
    public void deplacerSection(List<Point> listPointSection) {
        //Modifier les points de la section
    }
    public double calculerAireEspaceVital(Dimension dimensionsEspace) {
        double aireE=0;
        //Calcul de l'aire
        return aireE;
    }
    public double calculerAireSection(Dimension dimensions) {
        double aireS=0;
        //Calcul de l'aire
        return aireS;
    }
    public int calculernbSiegeSection(double aireSection,double aireEspaceVital) {
        int nbSiege=0;
        //Calcul du nombre de sieges
        return nbSiege;
    }
    public void genererSiegeAutomatique(int nbSiege) {
        
        //creation des sieges et calcul du positionnement
        
    }
    public void modifierPointSection(Point coordonneeInitial,Point coordonneeFinal ) {
        
        //recherche le point à modifier dans la liste des points de la section et modifie ses coordonnées
        
    }
    
    public void updateListPointSection(Point coordonneeFinal) {
        
    }
    public abstract void redistribuerSiege();
    public abstract void ajoutRange(int p_numeroRange,int p_nbColonne,boolean auto);
    
    public Section clone(){
        
       Section uneSection = new Section(nomSection,elevationSection, angleSection, espaceVitalSection ) {
           @Override
           public void redistribuerSiege() {
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }

           @Override
           public void ajoutRange(int p_numeroRange, int p_nbColonne, boolean auto) {
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       };
            
       uneSection.setAngleSection(angleSection);
       uneSection.setElevationSection(elevationSection);
       uneSection.setListRangeSection(listRangeSection);
       uneSection.setListSiegeSection(listSiegeSection);
       uneSection.setNomSection(nomSection);
       uneSection.setPrixSection(prixSection);
       uneSection.setListPointSection(listPointSection);
       return uneSection;
    }
    
}
