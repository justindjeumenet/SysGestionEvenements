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
 * @author justin
 */
public class ListOffreAfficher {
    
    private final List<OffreAfficherPanel> listGuiOffrePanel;
    
    public ListOffreAfficher()
    {
        listGuiOffrePanel = new LinkedList<>();
    }
    
    public void addComponentListe(OffreAfficherPanel offrePanel)
    {
        listGuiOffrePanel.add(offrePanel);
    }
    
    public boolean isEmpty()
    {
        return listGuiOffrePanel.isEmpty();
    }
    
    public List<OffreAfficherPanel> getComponentList()
    {
        return listGuiOffrePanel;
    }
}
