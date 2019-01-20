/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.gui.Affichage;

import SysGestionEvenement.Controlleur.PlanControlleur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;


/**
 *
 * @author justin
 */
public class OffreAfficherPanel extends JPanel{
    
    private final JLabel pourcentageOffreAffiche = new JLabel();
    private final JLabel descriptionOffreAffiche = new JLabel();
    private boolean isSelected = false;
    private PlanControlleur controlleur = new PlanControlleur();
    
    public OffreAfficherPanel(){
    
        this.controlleur = new PlanControlleur();
        this.setAutoscrolls(true);
        //le this est le panne conteneur
        this.setMaximumSize(new Dimension(192,40));
        this.setBackground(new Color(200,200,200));
        //this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(descriptionOffreAffiche);
        this.add(pourcentageOffreAffiche);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1) );
        
        this.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) 
        {
                setBackgroundOffreSelected();                                                                          
        }

        });
        
        
        this.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) 
        {

                setBackgroundOffreNotSelected();                                                                 
        }

        });
    }
    
    
   
    
        
    public void setPourcentageOffre(double pourcentage)
    {
         pourcentageOffreAffiche.setText(Double.toString(pourcentage));
    }
    
    public Double getPourcentageOffre()
    {
         return Double.parseDouble(pourcentageOffreAffiche.getText());
    }
    
    public void setDescription(String description)
    {
         descriptionOffreAffiche.setText(description);
    }
    
    public String getDescription()
    {
         return descriptionOffreAffiche.getText();
    }
    
    public void setBackgroundOffreSelected()
    {
        this.setBackground(new Color(100,100,100));
        pourcentageOffreAffiche.setForeground(Color.WHITE);
        descriptionOffreAffiche.setForeground(Color.WHITE);
        //int desc = controlleur.getPlan().getListSectionIrrPlan().get(0).getListRangeSection().get(0).getListSiegeRange().get(0).getOffreFromList().size();
        //String nombre = String.valueOf(desc);
        this.setToolTipText("Nous avons "+0+ " sieges associes a cette offre");
        
    }
    
    public void setBackgroundOffreNotSelected()
    {
        this.setBackground(new Color(200,200,200));
        pourcentageOffreAffiche.setForeground(new Color(60,60,60));
        descriptionOffreAffiche.setForeground(new Color(60,60,60));
        
    }
    
    public boolean getIsSelected()
    {
        return this.isSelected;
    }
    
    
}
