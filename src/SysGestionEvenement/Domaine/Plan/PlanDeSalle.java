/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;

import SysGestionEvenement.Domaine.PrixOffre.ListOffre;
import SysGestionEvenement.Domaine.PrixOffre.ListPrix;
import SysGestionEvenement.Domaine.PrixOffre.Offre;
import SysGestionEvenement.Domaine.PrixOffre.Prix;
import SysGestionEvenement.gui.Affichage.MainWindow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.awt.Point;
import java.awt.Polygon;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Comparator;
import javax.swing.JPanel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;


/**
 *
 * @author JustinDjeumene
 */
public class PlanDeSalle implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List <SectionRectangulaire> listSectionRectPlan;
    public Dimension maDimension;
    private MainWindow mainWindow;
    private EspaceVital espaceVital;
    private int p_longueur;
    private int p_largeur;
    private Scene scenePlan;
    private ListPrix listPrix;
    private int zoome;
    private boolean actifZome =true;
    private boolean grid = false;
    private int gridSize = 28;
    private List <SectionIrreguliere> listSectionIrrPlan;
    private ListOffre listOffre;
    private double longueurEspVital; 
    private double largeurEspVital;
    private Point pointdebutSectionCourante;
   private List <SommetPolygone> listSommetSectionCourante;
   private SommetPolygone sommetDebut;
   private List <SectionAdmissionGenerale> listSectionGenPlan;

   
    private List <Point> listPointSectionCourante;
        public void ajouterPoint(Point pointSection) {
        if (this.listPointSectionCourante.isEmpty()){
            this.pointdebutSectionCourante=pointSection;
            this.sommetDebut=new SommetPolygone(pointSection,4);
           // System.out.println("SOMMET DEBUT PLAN:"+pointSection);
        }
        this.listPointSectionCourante.add(pointSection);
        this.listSommetSectionCourante.add(new SommetPolygone(pointSection,4));
    }

    public List<SectionAdmissionGenerale> getListSectionGenPlan() {
        return listSectionGenPlan;
    }

    public void setListSectionGenPlan(List<SectionAdmissionGenerale> listSectionGenPlan) {
        this.listSectionGenPlan = listSectionGenPlan;
    }

    public List<SommetPolygone> getListSommetSectionCourante() {
        return listSommetSectionCourante;
    }

    public void setListSommetSectionCourante(List<SommetPolygone> listSommetSectionCourante) {
        this.listSommetSectionCourante = listSommetSectionCourante;
    }

    public SommetPolygone getSommetDebut() {
        return sommetDebut;
    }

    public void setSommetDebut(SommetPolygone sommetDebut) {
        this.sommetDebut = sommetDebut;
    }


    public List<Point> getListPointSectionCourante() {
        return listPointSectionCourante;
    }
    public List<SectionIrreguliere> getListSectionIrrPlan() {
        return listSectionIrrPlan;
    }

    public void setListSectionIrrPlan(List<SectionIrreguliere> listSectionIrrPlan) {
        this.listSectionIrrPlan = listSectionIrrPlan;
    }
    public PlanDeSalle(int longueur, int largeur, double longueurEspVital, double largeurEspVital){
        this.p_largeur = largeur;
        this.p_longueur = longueur;
        this.listSectionRectPlan = new LinkedList<SectionRectangulaire>();
        this.listSectionIrrPlan = new LinkedList<SectionIrreguliere>();
        this.maDimension=new java.awt.Dimension(p_longueur, p_largeur);
        this.listPrix = new ListPrix();
        this.listOffre = new ListOffre();
        this.espaceVital=new EspaceVital(longueurEspVital,largeurEspVital);
        this.listPointSectionCourante=new LinkedList<Point>();
        this.listSectionGenPlan = new LinkedList<SectionAdmissionGenerale>(); 
        this.listSommetSectionCourante=new LinkedList<SommetPolygone>();
        this.sommetDebut=new SommetPolygone(new Point(0,0),0);
        // test alain ne pas effacer svp pour le moment
        /*
        EspaceVital monEspace = new EspaceVital(3, 3);
        SectionRectangulaire sectionUn = new SectionRectangulaire("hello", 50, 50, monEspace, 4, 3, new Point(50,8));
        SectionRectangulaire sectionDeux = new SectionRectangulaire("hello2", 50, 50, monEspace, 4, 3, new Point(200,80));
        SectionRectangulaire sectionTrois = new SectionRectangulaire("hello3", 50, 50, monEspace, 4, 3, new Point(100,80));
        this.listSectionRectPlan.add(sectionUn);
        this.listSectionRectPlan.add(sectionDeux);
        this.listSectionRectPlan.add(sectionTrois);
        this.scenePlan = new Scene(new Point(4,10), new Point(20,30));
        */
    }   

 

    public EspaceVital getEspaceVital() {
        return espaceVital;
    }
    
     public void ajoutSectionGenerale(String p_nomSection,int p_elevationSection,int p_angleSection,EspaceVital p_espaceVitalSection,Point p_centreScene,Polygon poly,int qtePersonnes){
        SectionAdmissionGenerale section= new SectionAdmissionGenerale(p_nomSection,p_elevationSection,p_angleSection,p_espaceVitalSection,p_centreScene,poly,this.listPointSectionCourante,this.pointdebutSectionCourante,qtePersonnes);
       // section.roterSection(section.getCoinGauche(),section.getCoinSupDroit(),section.getCoinInfDroit(),section.getCoinInfGauche(),section.getCentreSection());
       // section.setPolySection(section.construirePolygone(section.getCoinGauche(),section.getCoinSupDroit(),section.getCoinInfDroit(),section.getCoinInfGauche())); 
        this.listSectionGenPlan.add(section);
        this.listPointSectionCourante.clear();
    }
 
 public void ajoutSectionIrreguliere(String p_nomSection,int p_elevationSection,int p_angleSection,EspaceVital p_espaceVitalSection,Point p_centreScene,Polygon poly){
        SectionIrreguliere section= new SectionIrreguliere(p_nomSection,p_elevationSection,p_angleSection,p_espaceVitalSection,p_centreScene,poly,this.listPointSectionCourante,this.pointdebutSectionCourante);
        
        section.ajoutRange(0, 0,false);
       // section.roterSection(section.getCoinGauche(),section.getCoinSupDroit(),section.getCoinInfDroit(),section.getCoinInfGauche(),section.getCentreSection());
       // section.setPolySection(section.construirePolygone(section.getCoinGauche(),section.getCoinSupDroit(),section.getCoinInfDroit(),section.getCoinInfGauche()));

       
        this.listSectionIrrPlan.add(section);
        this.listPointSectionCourante.clear();
    }
 
 public void ajoutSectionIrreguliereAuto(String p_nomSection,int p_elevationSection,int p_angleSection,EspaceVital p_espaceVitalSection,Point p_centreScene,Polygon poly){
        
        SectionIrreguliere section= new SectionIrreguliere(p_nomSection,p_elevationSection,p_angleSection,p_espaceVitalSection,p_centreScene,poly,this.listPointSectionCourante,this.pointdebutSectionCourante);
        
        section.ajoutRange(0, 0,true);
       // section.roterSection(section.getCoinGauche(),section.getCoinSupDroit(),section.getCoinInfDroit(),section.getCoinInfGauche(),section.getCentreSection());
       // section.setPolySection(section.construirePolygone(section.getCoinGauche(),section.getCoinSupDroit(),section.getCoinInfDroit(),section.getCoinInfGauche()));

       
        this.listSectionIrrPlan.add(section);
        this.listPointSectionCourante.clear();
    }

    public void setListSectionRectPlan(List<SectionRectangulaire> listSectionRectPlan) {
        this.listSectionRectPlan = listSectionRectPlan;
    }

    public void setEspaceVital(EspaceVital espaceVital) {
        modifierEspaceVitalSections(espaceVital,this.espaceVital);
        this.espaceVital = espaceVital;
    }

    public int getZoome() {
        return zoome;
    }

    public boolean isActifZome() {
        return actifZome;
    }

    public void setZoome(int zoome) {
        this.zoome = zoome;
    }

    public void setActifZome(boolean actifZome) {
        this.actifZome = actifZome;
    }
    
    public void modifierEspaceVitalSections(EspaceVital espaceVital,EspaceVital espaceVitaldefaut) {
    //this.espaceVital = espaceVital;
       for (SectionRectangulaire item : this.listSectionRectPlan) {
         
        if((item.getEspaceVitalSection().getDimensionEspaceVital().width==espaceVitaldefaut.getDimensionEspaceVital().getWidth())&&(item.getEspaceVitalSection().getDimensionEspaceVital().height==espaceVitaldefaut.getDimensionEspaceVital().getHeight()))
        {
            item.setEspaceVitalSection(espaceVital);
            
        } 
        
    }
}
    

    public Dimension getInitialDimension(){
        return maDimension;
    }
    
    public void ajoutSectionRectangle(String p_nomSection,int p_elevationSection,int p_angleSection,EspaceVital p_espaceVitalSection,int p_nbRange,int p_nbColonne,Point p_coinGauche,Point p_centreScene){
        SectionRectangulaire section= new SectionRectangulaire(p_nomSection,p_elevationSection,p_angleSection,p_espaceVitalSection,p_nbRange,p_nbColonne,p_coinGauche,p_centreScene);
        
        section.ajoutRange(p_nbRange, p_nbColonne,false);
        section.roterSection(section.getCoinGauche(),section.getCoinSupDroit(),section.getCoinInfDroit(),section.getCoinInfGauche(),section.getCentreSection());
        section.setPolySection(section.construirePolygone(section.getCoinGauche(),section.getCoinSupDroit(),section.getCoinInfDroit(),section.getCoinInfGauche()));

       
        this.listSectionRectPlan.add(section);
    }
    
    public List<SectionRectangulaire> getListSectionRect() {
		return listSectionRectPlan;
	}
    
    public Scene getScene() {
        return scenePlan;
    }
    
    public Point getCentreScene()
    {   
        
        return scenePlan.getCentreScene();
    }
    
    
    public Dimension modifierPlanDeSalle(int longueur, int largeur){
        this.maDimension = new java.awt.Dimension(longueur, largeur);
        return this.maDimension;
    }
    
    public Dimension zoomer(){
        int longueurActuelle = (int)maDimension.getHeight() + 5;
        int largeurActuelle = (int)maDimension.getWidth() + 5;
    
        return modifierPlanDeSalle(longueurActuelle, largeurActuelle);
    }
    
    public Dimension dezoomer(){
      int longueurActuelle = (int)maDimension.getHeight() - 5;
      int largeurActuelle = (int)maDimension.getWidth() - 5;
    
        return modifierPlanDeSalle(longueurActuelle, largeurActuelle);
    }
    public void ajouterUneScene(Point debut, Point fin){
       
        this.scenePlan = new Scene(debut,fin);
        
    }
    
     public void switchSelectionStatus(double x, double y) {
        
        for (SectionRectangulaire item : this.listSectionRectPlan) {
            if (item.contains(x, y)) {
                        System.out.println("plan  change statut section");
                        item.switchSelection();
            
                for (Range itemRange : item.getListRangeSection()) {
                        itemRange.changerSelection();
                        for (Siege itemSiege : itemRange.getListSiegeRange()) {
                
                          itemSiege.changerSelection();
                    
                        }
                    
                 }    
            }       
                
                
        }    
    
    }
     
          public void switchSelectionStatusSiege(double x, double y) {
        //System.out.println("plan  change statut siege");
        for (SectionRectangulaire item : this.listSectionRectPlan) {
            if (item.contains(x, y)) {
                        item.switchSelection();           
                for (Range itemRange : item.getListRangeSection()) {
                    if (itemRange.contains(x, y)) {
                        itemRange.changerSelection();
                        for (Siege itemSiege : itemRange.getListSiegeRange()) {
                            if (itemSiege.contains(x, y)) {
                            System.out.println("plan  change statut siege");
                            itemSiege.changerSelection();
                            }
                        }
                    
                    }
                } 
            }               
        }    
    
    }
          
           public void switchSelectionStatusRange(double x, double y) {
        
        for (SectionRectangulaire item : this.listSectionRectPlan) {
            if (item.contains(x, y)) {
                        item.switchSelection();           
                for (Range itemRange : item.getListRangeSection()) {
                    if (itemRange.contains(x, y)) {
                        System.out.println("++++++++RANGECLIQUE++++++++++++++++++++++ "+itemRange.getNumeroRange());
                        itemRange.changerSelection();
                        System.out.println("plan  change statut Range");
                        for (Siege itemSiege : itemRange.getListSiegeRange()) {
                            System.out.println("plan  change statut siege");
                            itemSiege.changerSelection();
                    
                        }
                    
                    }
                }    
            }               
        }    
    
    }    
    public void switchSelectionStatusGen(double x, double y) {
        
        for (SectionAdmissionGenerale item : this.listSectionGenPlan) {
            if (item.contains(x, y)) {
                        System.out.println("plan  change statut section");
                        item.switchSelection();
            
                    
            }       
                
                
        }    
    
    }
         public void switchSelectionStatusIrr(double x, double y) {
        
        for (SectionIrreguliere item : this.listSectionIrrPlan) {
            if (item.contains(x, y)) {
                        System.out.println("plan  change statut section");
                        item.switchSelection();
            
                for (Range itemRange : item.getListRangeSection()) {
                        itemRange.changerSelection();
                        for (Siege itemSiege : itemRange.getListSiegeRange()) {
                
                          itemSiege.changerSelection();
                    
                        }
                    
                 }    
            }       
                
                
        }    
    
    }
         
   
 public void switchSelectionStatusSiegeIrr(double x, double y) {
        //System.out.println("plan  change statut siege");
        for (SectionIrreguliere item : this.getListSectionIrrPlan()) {
            if (item.contains(x, y)) {
                        item.switchSelection();           
                for (Range itemRange : item.getListRangeSection()) {
                    if (itemRange.contains(x, y)) {
                        itemRange.changerSelection();
                        for (Siege itemSiege : itemRange.getListSiegeRange()) {
                            if (itemSiege.contains(x, y)) {
                            System.out.println("plan  change statut siegeIrr");
                            itemSiege.changerSelection();
                            }
                        }
                    
                    }
                } 
            }               
        }    
    
    }
           public void switchSelectionStatusRangeIrr(double x, double y) {
        
        for (SectionIrreguliere item : this.getListSectionIrrPlan()) {
            if (item.contains(x, y)) {
                        item.switchSelection();           
                for (Range itemRange : item.getListRangeSection()) {
                    if (itemRange.contains(x, y)) {
                        System.out.println("++++++++RANGECLIQUE++++++++++++++++++++++ "+itemRange.getNumeroRange());
                        itemRange.changerSelection();
                        System.out.println("plan  change statut Range");
                        for (Siege itemSiege : itemRange.getListSiegeRange()) {
                            System.out.println("plan  change statut siege");
                            itemSiege.changerSelection();
                    
                        }
                    
                    }
                }    
            }               
        }    
    
    }
           
    public void updateSelectedItemsPositionIrr(Point delta) {
    if(gridStatus() == false)
    {
    System.out.println("plan  Parcours section");
         System.out.println(this.listSectionRectPlan.size());
        for (SectionIrreguliere item : this.listSectionIrrPlan) 
        {
            System.out.println("plan  deplacement section");
            System.out.println(item.isSelected());
                if (item.isSelected()) 
                {
                    System.out.println("plan  deplacement section effectif");
                        item.translate(delta);
                
                for (Range itemRange : item.getListRangeSection()) 
                {
                
                    itemRange.translate(delta);
                    for (Siege itemSiege : itemRange.getListSiegeRange()) 
                    {
                
                   // if (itemSiege.isSelected()) {
                        //System.out.println("plan  deplacement siege effectif");
                        itemSiege.translate(delta);
                    //}
                    }
                }    
                }    
                
        }   
    }
    
    else if(gridStatus() == true)        
    {
    System.out.println("plan déplacement grille Parcours section");
         System.out.println(this.listSectionRectPlan.size());
        for (SectionIrreguliere item : this.listSectionIrrPlan) 
        {           
 
            
            System.out.println("plan  deplacement section");
            System.out.println(item.isSelected());
            
            if (item.isSelected()) 
            {
                Point coinGaucheSectionActuelle = new Point(item.getPointdebut().x, item.getPointdebut().y);
               // on addition le coin gauche actuel x et x au déplacement
                Point coinGaucheSectionDeplace = new Point(coinGaucheSectionActuelle.x + delta.x, coinGaucheSectionActuelle.y + delta.y);
                // on obtient le point gauche magnétisé temporaire
                Point coinGaucheSectionMagnetise = new Point(calculPointMagnetise(coinGaucheSectionDeplace));                       
                //on trouve l'écart entre le point magnétisé final et initial et on utilise ceci pour mettre à jour la scène
                delta.setLocation(coinGaucheSectionMagnetise.x - coinGaucheSectionActuelle.x, coinGaucheSectionMagnetise.y - coinGaucheSectionActuelle.y);
                
                
                System.out.println("plan  deplacement section effectif");
                item.translate(delta);
                item.setPointdebut(calculPointMagnetise(item.getPointdebut()));
                for (Range itemRange : item.getListRangeSection()) 
                {

                itemRange.translate(delta);
                for (Siege itemSiege : itemRange.getListSiegeRange()) 
                {

               // if (itemSiege.isSelected()) {
                    //System.out.println("plan  deplacement siege effectif");
                    itemSiege.translate(delta);
                //}
                }
            }    
            }    
                
        }
    }
}
public void updateSelectedItemsPositionGen(Point delta) {
    if(gridStatus() == false)
    {
    System.out.println("plan  Parcours section");
         System.out.println(this.listSectionGenPlan.size());
        for (SectionAdmissionGenerale item : this.listSectionGenPlan) 
        {
            System.out.println("plan  deplacement section");
            System.out.println(item.isSelected());
                if (item.isSelected()) 
                {
                    System.out.println("plan  deplacement section effectif");
                        item.translate(delta);
                    
                }    
                
        }   
    }
    
    else if(gridStatus() == true)        
    {
    System.out.println("plan déplacement grille Parcours section");
         System.out.println(this.listSectionGenPlan.size());
        for (SectionAdmissionGenerale item : this.listSectionGenPlan) 
        {           
 
            
            System.out.println("plan  deplacement section");
            System.out.println(item.isSelected());
            
            if (item.isSelected()) 
            {
                Point coinGaucheSectionActuelle = new Point(item.getPointdebut().x, item.getPointdebut().y);
               // on addition le coin gauche actuel x et x au déplacement
                Point coinGaucheSectionDeplace = new Point(coinGaucheSectionActuelle.x + delta.x, coinGaucheSectionActuelle.y + delta.y);
                // on obtient le point gauche magnétisé temporaire
                Point coinGaucheSectionMagnetise = new Point(calculPointMagnetise(coinGaucheSectionDeplace));                       
                //on trouve l'écart entre le point magnétisé final et initial et on utilise ceci pour mettre à jour la scène
                delta.setLocation(coinGaucheSectionMagnetise.x - coinGaucheSectionActuelle.x, coinGaucheSectionMagnetise.y - coinGaucheSectionActuelle.y);
                
                
                System.out.println("plan  deplacement section effectif");
                item.translate(delta);
                item.setPointdebut(calculPointMagnetise(item.getPointdebut()));
                  
            }    
                
        }
    }
}
    



public void ajouterSiegeManuel(double x, double y) {
       
       for (SectionIrreguliere item : this.getListSectionIrrPlan()) {
               if (item.contains(x, y)) {
                           //item.switchSelection();           
                   for (Range itemRange : item.getListRangeSection()) {
                       if (itemRange.contains(x, y)) {
                          // System.out.println("++++++++RANGECLIQUEAJOUT SIEGE++++++++++++++++++++++ "+itemRange.getNumeroRange());
                           Point prochaincentreSiege;
                           if(!itemRange.getListSiegeRange().isEmpty())
                           {
                           Point derniersiegeRangeCentre=itemRange.getListSiegeRange().get(itemRange.getListSiegeRange().size()-1).getCentreSiege();
                           prochaincentreSiege=new Point(derniersiegeRangeCentre.x+item.getEspaceVitalSection().getDimensionEspaceVital().width,derniersiegeRangeCentre.y);
                           }else 
                           {prochaincentreSiege=new Point(itemRange.getCoinGaucheRange().x+item.getEspaceVitalSection().getDimensionEspaceVital().width/2,itemRange.getCoinGaucheRange().y+item.getEspaceVitalSection().getDimensionEspaceVital().height/2);}

                           if(item.possibiliteSiege(prochaincentreSiege, item.getEspaceVitalSection().getDimensionEspaceVital().width/2, item.getEspaceVitalSection().getDimensionEspaceVital().height/2))
                          { item.ajouterSiegeManuel(itemRange, item.getEspaceVitalSection().getDimensionEspaceVital().width, item.getEspaceVitalSection().getDimensionEspaceVital().height);
                          } 


                       }
                   }    
               }               
           }    



      }

    public void updateSelectedItemsPosition(Point delta) 
    {
        
        if(gridStatus() == false)
        {
                    for (SectionRectangulaire item : this.listSectionRectPlan) 
            {


                if (item.isSelected()) {
                    item.translate(delta);

                    for (Range itemRange : item.getListRangeSection()) {

                        itemRange.translate(delta);
                        for (Siege itemSiege : itemRange.getListSiegeRange()) {
                            itemSiege.translate(delta);

                        }
                    }
                }

            }  
        } 
        else if(gridStatus() == true)
        {
            for (SectionRectangulaire item : this.listSectionRectPlan) 
                {
                                
                    if (item.isSelected()) 
                    {
                        
                        // on trouve le coin gauche avant le déplacement
                        Point coinGaucheSectionActuelle = item.getCoinGauche();
                        // on addition le coin gauche actuel x et x au déplacement
                        Point coinGaucheSectionDeplace = new Point(coinGaucheSectionActuelle.x + delta.x, coinGaucheSectionActuelle.y + delta.y);
                        // on obtient le point gauche magnétisé temporaire
                        Point coinGaucheSectionMagnetise = new Point(calculPointMagnetise(coinGaucheSectionDeplace));                       
                        //on trouve l'écart entre le point magnétisé final et initial et on utilise ceci pour mettre à jour la scène
                        delta.setLocation(coinGaucheSectionMagnetise.x - coinGaucheSectionActuelle.x, coinGaucheSectionMagnetise.y - coinGaucheSectionActuelle.y);
                        
                        item.translate(delta);
                        item.setCoinGauche(calculPointMagnetise(item.getCoinGauche()));
                        for (Range itemRange : item.getListRangeSection()) 
                        {

                            itemRange.translate(delta);
                            itemRange.setCoinGaucheRange(calculPointMagnetise(itemRange.getCoinGaucheRange()));
                            for (Siege itemSiege : itemRange.getListSiegeRange()) 
                            {
                                itemSiege.translate(delta);


                            }
                        }
                    }
                }
        }
}    
         
   
    
    
    public void ajoutPrixList(Prix p_prix)
   {
        listPrix.ajoutPrixListe(p_prix);
   }
    
        public void assignerPrixElementSelectionne(Color couleur, Double prix)
   {
       
        List<Prix> listeDesPrix = this.getListePrix();
        List<SectionIrreguliere> listeDesSectionIrr = this.getListSectionIrrPlan();
        Prix nouveauPrix = new Prix(prix, couleur);
       
        for (Prix item : listeDesPrix)
        {
        
           if(item.getColor() == nouveauPrix.getColor())
           {
               
                if (item.getPrix() == nouveauPrix.getPrix()) 
                {
                    
                    for(SectionRectangulaire section : listSectionRectPlan)    
                    {
                         for (Range itemRange : section.getListRangeSection()) 
                         {
                             for (Siege itemSiege : itemRange.getListSiegeRange()) 
                             {
                                 if(itemSiege.isSelected())
                                 {
                                     itemSiege.assignerPrice(item);
                                     itemSiege.assignerCouleurSiege(item.getColor());
                                 }
                             }
                         }
                    }
                    
                    for(SectionIrreguliere sectionIrr : listeDesSectionIrr)    
                    {

                      for (Range itemRange : sectionIrr.getListRangeSection()) 
                      {
                          for (Siege itemSiege : itemRange.getListSiegeRange()) 
                          {
                              if(itemSiege.isSelected())
                                 {
                                     itemSiege.assignerPrice(item);
                                     itemSiege.assignerCouleurSiege(item.getColor());
                                     
                                 }


                          }
                      }
                   }
                    
                    

                }
           }
        
        }
    
   }  
    public List<Prix> getListePrix()
    {
        return listPrix.getListePrix();
    }
    
    
    public double creePrixAuto(Double prixMin, Double prixMax, String p_attribution)
    {    
        
        ArrayList<Double> listDistance = new  ArrayList<>();
        Point centreScene = this.getCentreScene();
        double elementPlusLoinDistance;
        double elementPlusProcheDistance;
        double pourcentagePrixRelatifdistance;
        double tauxEnFonctionDistance; // poucentage calculé de 100% prêt de la scène et en diminuant lorsqu'on s'éloigne;
        List<SectionRectangulaire> listeDesSectionRect =  this.getListSectionRect();
        List<SectionIrreguliere> listeDesSectionIrr = this.getListSectionIrrPlan();
        double revenueTotal = 0;
        ArrayList<Double> prixDesSiegesCumule = new ArrayList<>();
        List<Siege> listSiegePlan = new ArrayList<>();//les de Tout les sièges du plan
                
        if("Par Siège".equals(p_attribution))
        {
            // on récupère la liste des sections rectangulaire
            for(SectionRectangulaire sectionRect : listeDesSectionRect)
            {
                for (Range itemRange : sectionRect.getListRangeSection()) 
                {
                    for (Siege itemSiege : itemRange.getListSiegeRange())
                    {
                        listDistance.add(itemSiege.getDistanceSiegeScene());
                        System.out.println("distance siège scène reg" + itemSiege.getDistanceSiegeScene());
                    }
                }
            }
            
            // la liste des distances rectangulaire irrègulière 
            for(SectionIrreguliere sectionIrr : listeDesSectionIrr)
            {
                for (Range itemRange : sectionIrr.getListRangeSection()) 
                {
                    for (Siege itemSiege : itemRange.getListSiegeRange())
                    {
                        listDistance.add(itemSiege.getDistanceSiegeScene());
                        System.out.println("distance siège scène irr" + itemSiege.getDistanceSiegeScene());
                    }
                }
            }
                        
            
            
            Collections.sort(listDistance);
            elementPlusLoinDistance = listDistance.get(listDistance.size() - 1);
            elementPlusProcheDistance = listDistance.get(0);

            
            // aon va chercher tous les sièges du plan
            for(SectionRectangulaire section : listeDesSectionRect)    
            {
                
                for (Range itemRange : section.getListRangeSection()) 
                {         
                    
                    for (Siege itemSiege : itemRange.getListSiegeRange()) 
                    {
                        listSiegePlan.add(itemSiege);                     
                    }
                }
            }
            
            for(SectionIrreguliere section : listeDesSectionIrr)    
            {
                
                for (Range itemRange : section.getListRangeSection()) 
                {         
                    
                    for (Siege itemSiege : itemRange.getListSiegeRange()) 
                    {
                        listSiegePlan.add(itemSiege);                     
                    }
                }
            }
            
            
            for(Siege itemSiege : listSiegePlan)
            {
                tauxEnFonctionDistance = 1 - ((itemSiege.getDistanceSiegeScene()- elementPlusProcheDistance) / (elementPlusLoinDistance - elementPlusProcheDistance));
                double prix = prixMin + ( (prixMax - prixMin) * tauxEnFonctionDistance) ; // la fonction la plus importante pour le calcul, elle détermine l'échelle de distance et le prix asscocié
                                                    

                itemSiege.assignerPrice(new Prix(prix, colorGeneratorRed(tauxEnFonctionDistance)));
                itemSiege.assignerCouleurSiege(colorGeneratorRed(tauxEnFonctionDistance));
                prixDesSiegesCumule.add(itemSiege.getPrice().getPrix());
                revenueTotal = sommeSiege(prixDesSiegesCumule);           
            }
            
            
            
            
        }
        else if("Par Rangé".equals(p_attribution))    
        {
            // on récupère la liste des sections rectangulaire
            for(SectionRectangulaire sectionRect : listeDesSectionRect)
            {
                for (Range itemRange : sectionRect.getListRangeSection()) 
                {
                    listDistance.add(itemRange.getDistanceRangeScene());
                    System.out.println("distance rangé régulière " + itemRange.getDistanceRangeScene());

                }
            }
            
            for(SectionIrreguliere sectionIrr : listeDesSectionIrr)
            {
                for (Range itemRange : sectionIrr.getListRangeSection()) 
                {
                    
                    listDistance.add(itemRange.getDistanceRangeScene());
                    System.out.println("distance rangé irrégulière " + itemRange.getDistanceRangeScene());

                }
            }
            

            
            Collections.sort(listDistance);
            elementPlusLoinDistance = listDistance.get(listDistance.size() - 1);
            elementPlusProcheDistance = listDistance.get(0);


            for(SectionIrreguliere sectionIrr : listeDesSectionIrr)    
            {
                
                for (Range itemRange : sectionIrr.getListRangeSection()) 
                {
                    tauxEnFonctionDistance = 1 - ((itemRange.getDistanceRangeScene() - elementPlusProcheDistance) / (elementPlusLoinDistance - elementPlusProcheDistance));
                    double prix = prixMin + ( (prixMax - prixMin) * tauxEnFonctionDistance) ; // la fonction la plus importante pour le calcul, elle détermine l'échelle de distance et le prix asscocié                 
                    
                    for (Siege itemSiege : itemRange.getListSiegeRange()) 
                    {
                        itemSiege.assignerPrice(new Prix(prix, colorGenerator(tauxEnFonctionDistance)));
                        itemSiege.assignerCouleurSiege(colorGenerator(tauxEnFonctionDistance));
                        prixDesSiegesCumule.add(itemSiege.getPrice().getPrix());

                    }
                }
            }            
            
                  
            for(SectionRectangulaire section : listeDesSectionRect)    
            {
                
                for (Range itemRange : section.getListRangeSection()) 
                {
                    tauxEnFonctionDistance = 1 - ((itemRange.getDistanceRangeScene() - elementPlusProcheDistance) / (elementPlusLoinDistance - elementPlusProcheDistance));
                    double prix = prixMin + ( (prixMax - prixMin) * tauxEnFonctionDistance) ; // la fonction la plus importante pour le calcul, elle détermine l'échelle de distance et le prix asscocié                 
                    
                    for (Siege itemSiege : itemRange.getListSiegeRange()) 
                    {
                        itemSiege.assignerPrice(new Prix(prix, colorGenerator(tauxEnFonctionDistance)));
                        itemSiege.assignerCouleurSiege(colorGenerator(tauxEnFonctionDistance));
                        prixDesSiegesCumule.add(itemSiege.getPrice().getPrix());

                    }
                }
            }
            
            revenueTotal = sommeSiege(prixDesSiegesCumule);
            
        }
        else if("Par Section".equals(p_attribution))
        {
            // par section ok manque irregulier
            // on récupère la liste des sections rectangulaire
            for(SectionRectangulaire sectionRect : listeDesSectionRect)
            {
                listDistance.add(sectionRect.getDistanceSectionScene());
                System.out.println("Distance section section rect" + sectionRect.getDistanceSectionScene());
            }
            
            
            for(SectionIrreguliere sectionIrr : listeDesSectionIrr)
            {
                listDistance.add(sectionIrr.getDistanceSectionScene());
                System.out.println("Distance section irr" + sectionIrr.getDistanceSectionScene());
            }
            
            // todo: on fera  la liste des sections rectangulaire irrègulière 
            
            Collections.sort(listDistance);
            elementPlusLoinDistance = listDistance.get(listDistance.size() - 1);
            elementPlusProcheDistance = listDistance.get(0);

            
            
            for(SectionIrreguliere sectionIrr : listeDesSectionIrr)    
            {

                tauxEnFonctionDistance = 1 - ((sectionIrr.getDistanceSectionScene() - elementPlusProcheDistance) / (elementPlusLoinDistance - elementPlusProcheDistance));

                double prix = prixMin + ( (prixMax - prixMin) * tauxEnFonctionDistance) ; // la fonction la plus importante pour le calcul, elle détermine l'échelle de distance et le prix asscocié
                
                
                for (Range itemRange : sectionIrr.getListRangeSection()) 
                {
                    for (Siege itemSiege : itemRange.getListSiegeRange()) 
                    {
                        itemSiege.assignerPrice(new Prix(prix, colorGenerator(tauxEnFonctionDistance)));
                        itemSiege.assignerCouleurSiege(colorGenerator(tauxEnFonctionDistance));
                        prixDesSiegesCumule.add(itemSiege.getPrice().getPrix());
                        System.out.println("Prix section irr" + itemSiege.getPrice().getPrix());

                    }
                }
            }
            
            
            
                  
            for(SectionRectangulaire section : listeDesSectionRect)    
            {

                tauxEnFonctionDistance = 1 - ((section.getDistanceSectionScene() - elementPlusProcheDistance) / (elementPlusLoinDistance - elementPlusProcheDistance));

                double prix = prixMin + ( (prixMax - prixMin) * tauxEnFonctionDistance) ; // la fonction la plus importante pour le calcul, elle détermine l'échelle de distance et le prix asscocié
                
                
                for (Range itemRange : section.getListRangeSection()) 
                {
                    for (Siege itemSiege : itemRange.getListSiegeRange()) 
                    {
                        itemSiege.assignerPrice(new Prix(prix, colorGenerator(tauxEnFonctionDistance)));
                        itemSiege.assignerCouleurSiege(colorGenerator(tauxEnFonctionDistance));
                        prixDesSiegesCumule.add(itemSiege.getPrice().getPrix());
                        System.out.println("Prix section reg" + itemSiege.getPrice().getPrix());
                        
                    }
                }
            }
             
            revenueTotal = sommeSiege(prixDesSiegesCumule);
            

        }
            
        return revenueTotal;
        
    }
    
    public double sommeSiege(ArrayList<Double> prixSieges)
    {
        double somme = 0;
        for (Double prix : prixSieges)
        {
            somme += prix;
        }
        return somme;
    }
    
    
    public Color colorGeneratorRed(double taux)
    {
        
        float tauxConv = (float)taux;
        Color seatColor = new Color(tauxConv, 0, 0);
        return seatColor;  
    }
    
    
    
    public Color colorGenerator(double taux)
    {
        float red = (float) 0.0;
        float green = (float) 0.0;
        float blue = (float) 1.0;
        
        float tauxConv = (float)taux;
        Color seatColor = new Color(red, tauxConv, blue);
        return seatColor;  
    }
    
    public double calculDistanceSectionScene(Point centreSection)
    {
        double distance = Math.sqrt(getCentreScene().x - centreSection.x * getCentreScene().x - centreSection.x + getCentreScene().y - centreSection.y * getCentreScene().y - centreSection.y);
        return distance;
    }
    
    public double calculDistanceElementScene(Point centreElement, Point centreScene)
    
    {

        double distance = Math.sqrt(((centreScene.x - centreElement.x) * (centreScene.x - centreElement.x)) + ((centreScene.y - centreElement.y) * (centreScene.y - centreElement.y)));
        return distance;

    }
    
    public void switchSelectionStatusScene(double x, double y) {
        if(scenePlan.contains(x, y)){
       // System.out.println("plan  change statut scene");
        Scene uneScene = this.scenePlan; 
          uneScene.switchSelection();
        }       
    
    }
    
    public void updateSelectedScenePosition(Point delta) {
          
        Scene uneScene = this.scenePlan;
        uneScene.translate(delta);
        
    }
    
    
     public void ModifierSection(String nomSection,int elevationSection,int angleSection,EspaceVital espaceVitalSection,int nbRange,int nbColonne,Point coinGauche) 
   {
       
       for (SectionRectangulaire item : this.listSectionRectPlan) {
         
        if(trouveSection(nomSection))
        {
            if (item.isSelected())
            {
                item.ModifierSectionRectangulaire(nomSection, elevationSection, angleSection, espaceVitalSection, nbRange, nbColonne, coinGauche);
                item.modifierRange(nbRange, nbColonne);
            }
        }
        
    }
        
       
       
   } 
   
   public boolean trouveSection(String p_nomSection)
   {
        boolean trouve=false; 
    for (SectionRectangulaire item : this.listSectionRectPlan) {
         
        if(item.getNomSection().equals(p_nomSection))
        {
              trouve= true;
              break;
                
        }  
        
    }
  //  System.out.println("TROUVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE SECTION "+p_nomSection+trouve);
    return trouve;
    }
   
   public void modifierScenePoint(int longueur, int largeur){
        Scene uneScene = this.scenePlan;
         Point p_pointD = new Point((uneScene.getPointfint().x-longueur),(uneScene.getPointfint().y-largeur));
         Point p_pointF = new Point((uneScene.getPointdebut().x+longueur),(uneScene.getPointdebut().y+largeur));
         uneScene.setPointdebut(p_pointD);
         uneScene.setPointfint(p_pointF);
    }
    public void modifierSceneDimension(int p_longueur, int p_largeur){
        Scene uneScene = this.scenePlan;
        modifierScenePoint(p_longueur, p_largeur);
        uneScene.setLongueur(p_longueur);
        uneScene.setLargeur(p_largeur);
        
          
    }
    
   /* public void modifierDimensionSceneSouris(Point pDebut,Point pFin){
        
    Scene uneScene = this.scenePlan;
   uneScene.setPointdebut(pDebut);
   uneScene.setPointfint(pFin);  
   int p_longueur = (pFin.x - pDebut.x); 
   int p_largeur = (pFin.y - pDebut.y);
   uneScene.setLongueur(p_longueur);
   uneScene.setLargeur(p_largeur);
    
    }*/
    
    public void modifierDimensionSceneSouris(Point pDebut,Point pFinSup, Point pFin, Point pDebutInf){
        
   Scene uneScene = this.scenePlan;
   uneScene.setPointdebut(pDebut);
   uneScene.setPointfint(pFin);
 
   uneScene.setCentreScene(uneScene.getCentreScene());
   int p_longueur = (pFin.x - pDebut.x); 
   int p_largeur = (pFin.y - pDebut.y);
   uneScene.setLongueur(p_longueur);
   uneScene.setLargeur(p_largeur);
  pDebutInf = new Point(uneScene.getPointdebut().x, uneScene.getPointdebut().y + p_longueur );
  pFinSup = new Point(uneScene.getPointfint().x, uneScene.getPointfint().y - p_longueur);
   uneScene.setPolygoneScene(uneScene.construirePolygoneScene(pDebut,pFinSup , pFin, pDebutInf));
    
    }
    
    public void roterScene(int angle){
       this.scenePlan.setAngle1(angle);
       
   }
    
    public void roterSestionRectangulaire(int angle){
       for (SectionRectangulaire item : this.listSectionRectPlan){
           //System.out.println("angle planDesalle"+angle);
           item.setAngleSection(angle);
       }
    }
    
     public  void supprimerSection(String nomSection)
      {
          for (SectionRectangulaire item : this.listSectionRectPlan) {
         
        if(trouveSection(nomSection))
        {
            this.listSectionRectPlan.remove(item);
            //System.out.println("SECTION SUPPRIMEE: "+nomSection);
            break;
        }
      }
      }
     
    public PlanDeSalle getPlanDeSalle()
    {
        return this;
    }                    
    
    public PlanDeSalle ouvrirPlan(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException   
    {
        PlanDeSalle plan = null;
        FileInputStream fichierIn = new FileInputStream(filePath);
        ObjectInputStream  objis = new ObjectInputStream(fichierIn);
        plan = (PlanDeSalle)  objis.readObject();
        
        fichierIn.close();
        
        return plan;          
    }  
    
        public void activateGrid()
    {
        this.grid = true;
    }
    
    public void deActivateGrid()
    {
        this.grid = false;

    }
    
    public boolean gridStatus()
    {
        return this.grid;
    }
    
    public int getGridSize()
    {
        return this.gridSize;
    }
    
    public Point calculPointMagnetise(Point point)
    {
        point.x = Math.round(point.x / gridSize) * gridSize;
        point.y = Math.round(point.y / gridSize) * gridSize;
        return point;
    }
    
     
     public void ajoutOffreList(Offre offre)
   {
        listOffre.ajoutOffreListe(offre);
   }
     
     
   public List<Offre> getListeOffre()
    {
        return listOffre.getListeOffre();
    }

    public void setMaDimension(Dimension maDimension) {
        this.maDimension = maDimension;
    }

    public void setP_longueur(int p_longueur) {
        this.p_longueur = p_longueur;
    }

    public void setP_largeur(int p_largeur) {
        this.p_largeur = p_largeur;
    }

    public void setScenePlan(Scene scenePlan) {
        this.scenePlan = scenePlan;
    }

    public void setListPrix(ListPrix listPrix) {
        this.listPrix = listPrix;
    }

    public void setListPointSectionCourante(List<Point> listPointSectionCourante) {
        this.listPointSectionCourante = listPointSectionCourante;
    }
   
   
   public PlanDeSalle clone() {
        PlanDeSalle unPlan = new PlanDeSalle(p_longueur,p_largeur,longueurEspVital,largeurEspVital);
        unPlan.setEspaceVital(espaceVital);
        unPlan.setListSectionRectPlan(listSectionRectPlan.stream().map(SectionRectangulaire::clone).collect(Collectors.toList()));
        unPlan.setP_largeur(p_largeur);
        unPlan.setP_longueur(p_longueur);
        unPlan.setMaDimension(maDimension);
        unPlan.setScenePlan(scenePlan);
        unPlan.setListPrix(listPrix);
       // unPlan.scenePlan.setPointfint(this.scenePlan.getPointfint());
       // unPlan.scenePlan.setPointdebut(this.scenePlan.getPointdebut());
        
        return unPlan;
    }
   

   public void assignerOffreElementSelectionne(double pourcentageOffre, String description)
   {
       
        List<Offre> listeDesOffres = this.getListeOffre();
        List<SectionIrreguliere> listeDesSectionIrr = this.getListSectionIrrPlan();
        Offre monOffre = new Offre(pourcentageOffre, description);
       
        for (Offre item : listeDesOffres)
        {
        
           if(item.getPourcentageOffre()== monOffre.getPourcentageOffre())
           {
               for(SectionRectangulaire section : listSectionRectPlan)    
                    {
                         for (Range itemRange : section.getListRangeSection()) 
                         {
                             for (Siege itemSiege : itemRange.getListSiegeRange()) 
                             {
                                 if(itemSiege.isSelected())
                                 {
                                     itemSiege.assignerOffre(monOffre);
                                     itemSiege.assignerListOffre(monOffre);
                                     //itemSiege.assignerDescription(item.getDescriptionAfficheOffre());
                                 }
                             }
                         }
                    }
               
                for(SectionIrreguliere sectionIrr : listeDesSectionIrr)    
               {

                 for (Range itemRange : sectionIrr.getListRangeSection()) 
                 {
                     for (Siege itemSiege : itemRange.getListSiegeRange()) 
                     {
                         if(itemSiege.isSelected())
                            {
                                itemSiege.assignerOffre(monOffre);
                                itemSiege.assignerListOffre(monOffre);
                                //itemSiege.assignerDescription(item.getDescriptionAfficheOffre());
                            }
                         

                     }
                 }
              }
                
           }
        
        }
   }
   
   public void removeOffreElementSelectionne(double pourcentageOffre, String description)
   {
       
        List<Offre> listeDesOffres = this.getListeOffre();
        Offre monOffre = new Offre(pourcentageOffre, description);
       
        for (Offre item : listeDesOffres)
        {
        
           if(item.getPourcentageOffre()== monOffre.getPourcentageOffre())
           {
               for(SectionRectangulaire section : listSectionRectPlan)    
                    {
                         for (Range itemRange : section.getListRangeSection()) 
                         {
                             for (Siege itemSiege : itemRange.getListSiegeRange()) 
                             {
                                 if(itemSiege.isSelected())
                                 {
                                     
                                     if(!listeDesOffres.isEmpty()){
                                         for(int i = 0; i < listeDesOffres.size(); i++){
                                             itemSiege.removeOffreFromList(i);
                                         }
                                         
                                     }
                                     
                                 }
                             }
                         }
                    }
                
           }
        
        }
   }
//=======
}
