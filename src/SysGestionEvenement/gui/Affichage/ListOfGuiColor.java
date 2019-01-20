/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.gui.Affichage;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author alemyre
 */
public class ListOfGuiColor {
    

    private List<GuiColorPanel> listGuiColorPanel;
    
    public ListOfGuiColor()
    {
        listGuiColorPanel = new LinkedList<GuiColorPanel>();
    }
    
    public void addComponentListe(GuiColorPanel colorPanel)
    {
        listGuiColorPanel.add(colorPanel);
    }
    
    public boolean isEmpty()
    {
        return listGuiColorPanel.isEmpty();
    }
    
    public List<GuiColorPanel> getComponentList()
    {
        return listGuiColorPanel;
    }
    
    
}

    
