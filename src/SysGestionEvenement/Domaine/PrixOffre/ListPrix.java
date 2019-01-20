/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.PrixOffre;

import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;
/* *
 * @author alemyre
 */
public class ListPrix implements Serializable
{ 
    private static final long serialVersionUID = 1L;
    private List<Prix> listPrix;
    
    public ListPrix()
    {
        listPrix = new LinkedList<Prix>();
    }
    
    public void ajoutPrixListe(Prix p_prix)
    {
        listPrix.add(p_prix);
    }
    
    public boolean isEmpty()
    {
        return listPrix.isEmpty();
    }
    
    public List<Prix> getListePrix()
    {
        return listPrix;
    }
    
    
}
