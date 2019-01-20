/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.PrixOffre;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author justin
 */
public class ListOffre implements Serializable
{
   
    private static final long serialVersionUID = 1L;
    private final List<Offre> listOffre;
    
    public ListOffre()
    {
        listOffre = new LinkedList<>();
    }
    
    public void ajoutOffreListe(Offre offre)
    {
        listOffre.add(offre);
    }
    
    public boolean isEmpty()
    {
        return listOffre.isEmpty();
    }
    
    public List<Offre> getListeOffre()
    {
        return listOffre;
    }
}
