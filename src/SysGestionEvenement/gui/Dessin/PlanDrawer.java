/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.gui.Dessin;

import SysGestionEvenement.Controlleur.PlanControlleur;
import SysGestionEvenement.Domaine.Plan.SectionRectangulaire;
import SysGestionEvenement.Domaine.Plan.Range;
import SysGestionEvenement.Domaine.Plan.Siege;
import SysGestionEvenement.Domaine.Plan.EspaceVital;
import SysGestionEvenement.Domaine.Plan.Scene;
import SysGestionEvenement.Domaine.Plan.SectionIrreguliere;
import SysGestionEvenement.Domaine.Plan.SommetPolygone;
import SysGestionEvenement.Domaine.Plan.SectionAdmissionGenerale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author ordinateur
 */
public class PlanDrawer 

{
    
    
    private final PlanControlleur controlleur;
    private Dimension initialDimension;
    private Point start;
    private Point end;
    public PlanDrawer(PlanControlleur controlleur, Dimension initialDimension) {
            this.controlleur = controlleur;
            this.initialDimension = initialDimension;
    }
    
    public void dessiner(Graphics g) {
        
            if(this.controlleur.gridStatus()==true)
            {
                dessinerGrille(g);
            }
            
            dessinerScene(g, this.controlleur.getPlan().getScene());
            
            List<SommetPolygone> itemsommets = this.controlleur.getPlan().getListSommetSectionCourante();         
            for (SommetPolygone itemso: itemsommets){
               dessinerSommet(g,itemso);
            }  
           // dessinerSommet(g,this.controlleur.getPlan().getSommetDebut());
            List<SectionIrreguliere> itemsI = this.controlleur.getPlan().getListSectionIrrPlan();           
            for ( SectionIrreguliere item: itemsI){
                for ( SommetPolygone s: item.getListSommet()){
                   // System.out.println("Sommet affiché+++++++++++++++++++"+s.getPoint());
                     dessinerSommet(g,s);
                }
            
            }
            
              List<SectionAdmissionGenerale> itemsG = this.controlleur.getPlan().getListSectionGenPlan();           
            for ( SectionAdmissionGenerale item: itemsG){
                for ( SommetPolygone s: item.getListSommet()){
                   // System.out.println("Sommet affiché+++++++++++++++++++"+s.getPoint());
                     dessinerSommet(g,s);
                }
            
            }
            List<Point> itempoints = this.controlleur.getPlan().getListPointSectionCourante();
            g.setColor(Color.RED);
            for (Point itemp: itempoints){
            g.drawLine(itemp.x, itemp.y, itemp.x, itemp.y);
          
            }
            
            
            
            
            List<SectionRectangulaire> items = controlleur.getPlan().getListSectionRect();
            for (SectionRectangulaire item: items){
                dessinerSectionRect(g,item);              
            }
            List<SectionIrreguliere> itemsIrr = controlleur.getPlan().getListSectionIrrPlan();
            for (SectionIrreguliere item: itemsIrr){
                dessinerSectionIrr(g,item);              
            }
            
              List<SectionAdmissionGenerale> itemsGen = controlleur.getPlan().getListSectionGenPlan();
            for (SectionAdmissionGenerale item: itemsGen){
                dessinerSectionGen(g,item);              
            }
            
    }
    
        public void dessinerGrille(Graphics g)
    {
        int largeurPlan = controlleur.getPlan().getInitialDimension().width;
        int hauteurPlan = controlleur.getPlan().getInitialDimension().height;
        
        // grille est de 28 pixels x 28 pixels
        
        for(int i = 0; i < largeurPlan; i+=controlleur.getGridSize())
        {   
            if(i % 112 == 0)
            {
            g.setColor(new Color(50,50,50,60));
            }
            else
            {
                g.setColor(new Color(50,50,50,30));
            }
            g.drawLine(i, 0, i, hauteurPlan);
        }
        
        for(int i = 0; i < hauteurPlan; i+=controlleur.getGridSize())
        {   
            if(i % 112 == 0)
            {
                g.setColor(new Color(50,50,50,60));
            }
            else
            {
                g.setColor(new Color(50,50,50,30));
            }
            g.drawLine(0, i, largeurPlan, i);
        }

    }
   
     public void dessinerSectionGen(Graphics g, SectionAdmissionGenerale section)
    {   if (section.isSelected()) {
        g.setColor(Color.RED);
        g.drawPolygon(section.getPolySectionGen());
    }else{
        g.setColor(Color.BLUE);
        g.drawPolygon(section.getPolySectionGen());
    }
    }

    public void dessinerSectionRect(Graphics g, SectionRectangulaire p_section) {
        if(this.controlleur.getPlan().isActifZome()){
                int zomeSection = this.controlleur.getPlan().getZoome();
                
                int sectionCoinGauchex=p_section.getCoinGauche().x;
                int sectionCoinGauchey=p_section.getCoinGauche().y;
                Dimension dimensionSection=p_section.getDimensionSection();            
                g.setColor(new Color(140,98,57));
                
                if (p_section.isSelected()) {
                         int largeuroffset=dimensionSection.width+4 + zomeSection;
                         int longueuroffset=dimensionSection.height+4 + zomeSection;
               // g.drawRect((sectionCoinGauchex-2)-(zomeSection/2), (sectionCoinGauchey-2)-(zomeSection/2), largeuroffset , longueuroffset);
                g.drawPolygon(p_section.getPolySection());
                }
               // else{
                 g.drawPolygon(p_section.getPolySection());   
               // g.drawRect(sectionCoinGauchex-(zomeSection/2), sectionCoinGauchey-(zomeSection/2), dimensionSection.width +zomeSection, dimensionSection.height+zomeSection);
               // }
                
                int rangx=0;
                int rangy=0;
                List<Range> itemsRange = p_section.getListRangeSection();
                for (Range itemRange: itemsRange){
                    g.drawPolygon(itemRange.getPolyRange());
                    List<Siege> itemsSiege = itemRange.getListSiegeRange();
                    for (Siege itemSiege: itemsSiege){
                        
                        
                        dessinerSiege(g,itemSiege,p_section.getEspaceVitalSection());
                         rangx=itemSiege.getPoint().x + zomeSection;
                         rangy=itemSiege.getPoint().y + zomeSection;
                        
                        
                    }
                    
                    g.setColor(Color.BLACK);
                    g.drawString (Integer.toString(itemRange.getNumeroRange()), rangx+p_section.getEspaceVitalSection().getDimensionEspaceVital().width +zomeSection,rangy+p_section.getEspaceVitalSection().getDimensionEspaceVital().height/4 +zomeSection);   
                    
                }
        }else{
            
            int sectionCoinGauchex=p_section.getCoinGauche().x;
                int sectionCoinGauchey=p_section.getCoinGauche().y;
                Dimension dimensionSection=p_section.getDimensionSection();            
                g.setColor(new Color(140,98,57));
                if (p_section.isSelected()) {
                         int largeuroffset=dimensionSection.width+4;
                         int longueuroffset=dimensionSection.height+4;
                g.drawRect(sectionCoinGauchex-2, sectionCoinGauchey-2, largeuroffset, longueuroffset);
                }
               // else{
                    
                g.drawRect(sectionCoinGauchex, sectionCoinGauchey, dimensionSection.width, dimensionSection.height);
               // }
                
                int rangx=0;
                int rangy=0;
                List<Range> itemsRange = p_section.getListRangeSection();
                for (Range itemRange: itemsRange){
                    List<Siege> itemsSiege = itemRange.getListSiegeRange();
                    for (Siege itemSiege: itemsSiege){
  
                        dessinerSiege(g,itemSiege,p_section.getEspaceVitalSection());
                         rangx=itemSiege.getPoint().x;
                        rangy=itemSiege.getPoint().y;
      
                    }                   
                    g.setColor(Color.BLACK);
                    g.drawString (Integer.toString(itemRange.getNumeroRange()), rangx+p_section.getEspaceVitalSection().getDimensionEspaceVital().width,rangy+p_section.getEspaceVitalSection().getDimensionEspaceVital().height/4);      
                }
            
            
        }
                
                
                
    }
    
     public void dessinerSiege(Graphics g, Siege p_siege,EspaceVital p_espaceVital ) {
                if(this.controlleur.getPlan().isActifZome()){
                    int zomeSiege = this.controlleur.getPlan().getZoome();
                    Color color = p_siege.getCouleurSiege();
                     if (p_siege.isSelected()) {
                         
                        g.setColor(Color.RED);
                        int offsetRadius = p_siege.getRayon() + 5 + zomeSiege/2 ;
                        g.fillOval(((int) p_siege.getCentreSiege().getX() - offsetRadius) - (zomeSiege/4),((int) p_siege.getCentreSiege().getY() - offsetRadius )-(zomeSiege/4), offsetRadius * 2, offsetRadius * 2);
                        Font h = new Font("Helvetica", Font.BOLD, 15);
                        g.setFont(h);
                        g.setColor(Color.WHITE); 
                        g.drawString (p_siege.getDescriptionOffre(), p_siege.getPoint().x,p_siege.getPoint().y);
                     
                     }
                    int rayon=p_siege.getRayon()+zomeSiege/2;
                    g.setColor(color);
                    g.fillOval(((int)p_siege.getCentreSiege().getX()-rayon) -zomeSiege/4,((int)p_siege.getCentreSiege().getY()-rayon) -zomeSiege/4, (rayon*2)+zomeSiege/2, (rayon*2)+zomeSiege/2);
                    Font h = new Font("Helvetica", Font.BOLD, 15);
                    g.setFont(h);
                    g.setColor(Color.WHITE);   
                    g.drawString (Integer.toString(p_siege.getNumeroSiege()), p_siege.getPoint().x,p_siege.getPoint().y);
                    g.drawString (p_siege.getDescriptionOffre(), p_siege.getPoint().x,p_siege.getPoint().y);
                }
                else{
                    
                     Color color = p_siege.getCouleurSiege();
                     if (p_siege.isSelected()) {
                         
                        g.setColor(Color.RED);
                        int offsetRadius = p_siege.getRayon() + 5;
                        g.fillOval((int) p_siege.getCentreSiege().getX() - offsetRadius,(int) p_siege.getCentreSiege().getY() - offsetRadius, offsetRadius * 2, offsetRadius * 2);
                        Font h = new Font("Helvetica", Font.BOLD, 15);
                        g.setFont(h);
                        g.setColor(Color.WHITE);
                        g.drawString (p_siege.getDescriptionOffre(), p_siege.getPoint().x,p_siege.getPoint().y);
                     
                     }
                    int rayon=p_siege.getRayon();
                    g.setColor(color);
                    g.fillOval((int)p_siege.getCentreSiege().getX()-rayon,(int)p_siege.getCentreSiege().getY()-rayon, rayon*2, rayon*2);
                    Font h = new Font("Helvetica", Font.BOLD, 15);
                    g.setFont(h);
                    g.setColor(Color.WHITE);   
                    g.drawString (Integer.toString(p_siege.getNumeroSiege()), p_siege.getPoint().x,p_siege.getPoint().y);
                    g.drawString (p_siege.getDescriptionOffre(), p_siege.getPoint().x,p_siege.getPoint().y);
                    
                
                
                }     
    }
     
     
    
          public void dessinerSommet(Graphics g, SommetPolygone p_sommet ) {
               
                    
                    // Color color = p_sommet.getPoint().getCouleurSiege();
                     if (p_sommet.isSelected()) {
                         
                        g.setColor(Color.RED);
                        int offsetRadius = p_sommet.getRayon();
                        g.fillOval((int) p_sommet.getPoint().getX() - offsetRadius,(int) p_sommet.getPoint().getY() - offsetRadius, offsetRadius * 2, offsetRadius * 2);
                      //  Font h = new Font("Helvetica", Font.BOLD, 15);
                      //  g.setFont(h);
                      //  g.setColor(Color.WHITE);   
                     
                     }
                    int rayon=p_sommet.getRayon();
                    g.setColor(Color.BLUE);
                    g.fillOval((int)p_sommet.getPoint().getX()-rayon,(int)p_sommet.getPoint().getY()-rayon, rayon*2, rayon*2);
                    Font h = new Font("Helvetica", Font.BOLD, 15);
                    g.setFont(h);
                    g.setColor(Color.WHITE);   
                    //g.drawString (Integer.toString(p_sommet..getNumeroSiege()), p_siege.getPoint().x,p_siege.getPoint().y);
                    
                
                
                    
    } 
     
     public void dessinerSectionIrr(Graphics g, SectionIrreguliere section)
    {
        if(section.isSelected())g.setColor(Color.RED);
        else g.setColor(Color.BLUE);
        g.drawPolygon(section.getPolySectionIrr());
      //  g.setColor(Color.GREEN);
    //    g.drawRect(section.getRectPolySectionIrr().x,section.getRectPolySectionIrr().y,section.getRectPolySectionIrr().width,section.getRectPolySectionIrr().height);
    //    g.setColor(Color.BLUE);
        List<Range> itemsRange = section.getListRangeSection();
        for (Range itemRange: itemsRange){
                    //System.out.println("DESSINER RANGE"+itemRange.getNumeroRange());
            g.drawPolygon(itemRange.getPolyRange());
            int numeroRangex=section.getRectPolySectionIrr().x+section.getRectPolySectionIrr().width;
            int numeroRangey=itemRange.getCoinGaucheRange().y+section.getEspaceVitalSection().getDimensionEspaceVital().height/2;            
            List<Siege> itemsSiege = itemRange.getListSiegeRange();
            for (Siege itemSiege: itemsSiege){
                g.setColor(Color.red);
                dessinerSiege(g,itemSiege,section.getEspaceVitalSection());  
             }
            g.setColor(Color.BLACK);
            g.drawString (Integer.toString(itemRange.getNumeroRange()), numeroRangex+section.getEspaceVitalSection().getDimensionEspaceVital().width ,numeroRangey+section.getEspaceVitalSection().getDimensionEspaceVital().height/4);   
         }

    }
    
    
    
      public void dessinerScene(Graphics g, Scene scene) {
          
       if(this.controlleur.getPlan().isActifZome()) {
           
         //  System.out.println("zommer dans drawer la D ABORD LA SCENE S");
           int zomeScene = this.controlleur.getPlan().getZoome();
           
        if (scene!=null ){
                Point coinDebut =scene.getPointdebut();
                Point coinFin=scene.getPointfint();
                
                int inverse;
                
                int coinDebutx = (int)scene.getPointdebut().x;
                int coinDebuty = (int)scene.getPointdebut().y;
                int coinFinx = (int)scene.getPointfint().x;
                int coinFiny = (int)scene.getPointfint().y;
                
                if(coinDebutx > coinFinx) { inverse = coinDebutx; coinDebutx = coinFinx; coinFinx = inverse; }
                if(coinDebuty >coinFiny) { inverse = coinDebuty; coinDebuty = coinFiny; coinFiny = inverse; }
                //Dimension dimensionSection=p; 
                if(scene.isSelected()){
                g.setColor(Color.BLUE);
                int ofsetLargeur = (coinFinx -coinDebutx)+4 + zomeScene;
                int ofsetLongueur = (coinFiny -coinDebuty)+4 + zomeScene;
                g.drawPolygon(this.controlleur.getPlan().getScene().getPolygoneScene());
                //g.drawRect((coinDebutx-2)-(zomeScene/2), (coinDebuty-2)-(zomeScene/2), ofsetLargeur, ofsetLongueur);      
                }
                
                g.drawPolygon(this.controlleur.getPlan().getScene().getPolygoneScene());
                g.setColor(new Color(140,98,57));
               // g.fillRect((coinDebutx)-(zomeScene/2), (coinDebuty)-(zomeScene/2), (coinFinx -coinDebutx)+zomeScene, (coinFiny -coinDebuty)+zomeScene);
                
                }
      }
       else{
           if (scene!=null ){
                Point coinDebut =scene.getPointdebut();
                Point coinFin=scene.getPointfint();
                
                int inverse;
                
                int coinDebutx = (int)scene.getPointdebut().x;
                int coinDebuty = (int)scene.getPointdebut().y;
                int coinFinx = (int)scene.getPointfint().x;
                int coinFiny = (int)scene.getPointfint().y;
                
                if(coinDebutx > coinFinx) { inverse = coinDebutx; coinDebutx = coinFinx; coinFinx = inverse; }
                if(coinDebuty >coinFiny) { inverse = coinDebuty; coinDebuty = coinFiny; coinFiny = inverse; }
                //Dimension dimensionSection=p; 
                if(scene.isSelected()){
                g.setColor(Color.BLUE); ///new Color(255,98,57)
                int ofsetLargeur = (coinFinx -coinDebutx)+4 ;
                int ofsetLongueur = (coinFiny -coinDebuty)+4;
                g.drawPolygon(this.controlleur.getPlan().getScene().getPolygoneScene());
                //g.drawRect(coinDebutx-2, coinDebuty-2, ofsetLargeur, ofsetLongueur);      
                }
               
                g.drawPolygon(this.controlleur.getPlan().getScene().getPolygoneScene());
                g.setColor(new Color(140,98,57));
                //g.fillRect(coinDebutx, coinDebuty, (coinFinx -coinDebutx), (coinFiny -coinDebuty));
                
                }
       
       }
                
    }
    
    
    
    
}
