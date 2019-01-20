/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Utilitaires;

import SysGestionEvenement.Controlleur.PlanControlleur;
import SysGestionEvenement.Domaine.Plan.Range;
import SysGestionEvenement.Domaine.Plan.SectionRectangulaire;
import SysGestionEvenement.Domaine.Plan.Siege;
import SysGestionEvenement.gui.Dessin.DrawingPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author justin
 */
public class AfficherInfoSieges extends MouseAdapter{
   private final DrawingPanel drawingPanel;
   private final PlanControlleur planControlleur;
    
    public AfficherInfoSieges(DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
        planControlleur = new PlanControlleur();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        //System.out.println("Inside drawing panel! Thanks");
        
        for(SectionRectangulaire section : planControlleur.getPlan().getListSectionRect())    
        {
            System.out.println("Inside drawing panel! Thanks");
             for (Range itemRange : section.getListRangeSection()) 
             {
                 for (Siege itemSiege : itemRange.getListSiegeRange()) 
                 {
                    if(itemSiege.isSelected() && itemSiege.contains(me.getX(), me.getY()))
                    {
                      System.out.println("Inside Siege selectionne! Thanks"); 
                    }
                 }
             }
        }
    } 
}
