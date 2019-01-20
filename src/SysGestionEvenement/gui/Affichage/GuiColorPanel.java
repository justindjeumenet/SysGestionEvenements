/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.gui.Affichage;
import SysGestionEvenement.gui.Dessin.PlanDrawer;
import SysGestionEvenement.Controlleur.PlanControlleur;
import SysGestionEvenement.gui.Dessin.DrawingPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 *
 * @author alemyre
 */
public class GuiColorPanel extends JPanel
{

    
    private JPanel listPanelCarreCouleur = new  JPanel();
    private JPanel listPanelSpacer = new  JPanel();
    private JLabel listPanelMontantPrix = new JLabel();
    private boolean isSelected = false;
    private PlanControlleur controlleur = new PlanControlleur();
    
   // private Color colorPrix = new Color();
   // private String Prix = new Prix();
    
    
    public GuiColorPanel()
    {
        this.controlleur = new PlanControlleur();

        //le this est le panne conteneur
        this.setMaximumSize(new Dimension(192,30));
        this.setBackground(new Color(200,200,200));
        this.add(listPanelCarreCouleur);
        this.add(listPanelSpacer);
        this.add(listPanelMontantPrix);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1) );

        listPanelSpacer.setMaximumSize(new Dimension(10,30));
        listPanelSpacer.setOpaque(true);
        listPanelSpacer.setBackground(new Color(200,200,200));
        
        listPanelCarreCouleur.setMaximumSize(new Dimension(30,30));

        
  
       
        this.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) 
        {
                setBackgroundPanelGuiSelected();                                                                          
        }

        });
        
                this.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) 
        {

                setBackgroundPanelGuiNotSelected();                                                                 
        }

        });
        
    }
    
    public boolean getIsSelected()
    {
        return this.isSelected;
    }
    
    public Color getColorLabel()
    {
        return listPanelCarreCouleur.getBackground();
    }
    
    public boolean setIsSelected(boolean selection)
    {
       return isSelected = selection;
    }
    
    public void setColorPanelPrix(Color color)
    {
        listPanelCarreCouleur.setBackground(color);
    }
    
    public void setLabelPrix(Double montantPrix)
    {
         listPanelMontantPrix.setText(Double.toString(montantPrix));
    }
    
    public Double getLabelMontalPrix()
    {
         return Double.parseDouble(listPanelMontantPrix.getText());
    }
    
    public void setBackgroundPanelGuiSelected()
    {
        this.setBackground(new Color(150,150,150));
        listPanelSpacer.setBackground(new Color(150,150,150));
    }
    
    public void setBackgroundPanelGuiNotSelected()
    {
        this.setBackground(new Color(200,200,200));
        listPanelSpacer.setBackground(new Color(200,200,200));
    }
    
    
    /*
    public int getguiPanelIndex()
    {
        //return this.get
    }
 
    */
    
}
