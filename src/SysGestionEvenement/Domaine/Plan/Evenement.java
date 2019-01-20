/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;


/**
 *
 * @author JustinDjeumene
 */
public class Evenement extends JDialog
{
    public Evenement(){
        this.setTitle("Information sur l'evenement");
        this.setBackground(new java.awt.Color(150, 150, 150));
        //this.setSize(new java.awt.Dimension(500, 600));
        this.getAccessibleContext().setAccessibleParent(null);
        this.setMinimumSize(new java.awt.Dimension(600, 500));
        
        
        
    }
}
