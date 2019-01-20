/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.gui.Dessin;

import SysGestionEvenement.Domaine.Utilitaires.AfficherInfoSieges;
import SysGestionEvenement.gui.Affichage.MainWindow;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;


/**
 *
 * @author Alain
 */
public class DrawingPanel extends javax.swing.JPanel
  {
   
    private MainWindow mainWindow;
    public Dimension initialDimension;
    private Point start;
    private Point end;
    private boolean dessiner =true;
    private boolean dessiner2 = true;
            
    
    public DrawingPanel(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        int width = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*0.85);
        setPreferredSize(new Dimension(width,1));
        setVisible(true);
        int height = (int)(width*0.5);
        initialDimension = new Dimension(width,height);
         AfficherInfoSieges mesInfo = new AfficherInfoSieges(this);
        addMouseMotionListener(mesInfo);
       // MouseAdapter mousse = new MousseHand();
       // addMouseMotionListener(mousse);
        //addMouseListener(mousse);
    }
    
  
    @Override
    protected void paintComponent( Graphics g )
    {
        //contenue ici
        //System.out.println("Le repaint avant");
        if (mainWindow != null){
            //System.out.println("Le repaint apres");
            MouseAdapter adapter = new MousseHand();
            addMouseMotionListener(adapter);
            addMouseListener(adapter);
            
            super.paintComponent(g); 
            PlanDrawer mainDessinateur = new PlanDrawer(mainWindow.controlleur,initialDimension);
            mainDessinateur.dessiner(g);
        }
    }
    
    public class MousseHand extends MouseAdapter{
        
    /* public void mouseReleased(MouseEvent e) { 
        if(dessiner2){
        start = mainWindow.p_point;
        end = e.getPoint();
        System.out.println("mousseHand released");
        System.out.println(end);
        System.out.println("mousseHand relesed");
        mainWindow.controlleur.ajouterUneScene(start, end);
        //mainWindow.controlleur.getPlan().getScene().setPointfint(end);
         repaint();
        } 
        dessiner2=false;
    } */  
  /*     
    public void mouseDragged(MouseEvent e) { 
        if(dessiner2){
        end = e.getPoint();
         repaint();
        }     
    }
 
    public void mousePressed(MouseEvent e) {
        if (dessiner){
        start = e.getPoint();
        }
        dessiner = false;
    }*/
    
    
}
  }
