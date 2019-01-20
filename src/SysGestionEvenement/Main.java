/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement;

import javax.swing.JFrame;
import jdk.nashorn.internal.codegen.CompilerConstants;


/**
 *
 * @author alemyre
 */
public class Main {
    
    public static void main(String[] args) 
    {
        SysGestionEvenement.gui.Affichage.MainWindow mainWindow = new SysGestionEvenement.gui.Affichage.MainWindow();
        mainWindow.setExtendedState(mainWindow.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        mainWindow.setVisible(true);
             
        
    }
    
}
