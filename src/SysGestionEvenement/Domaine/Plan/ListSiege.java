/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Plan;

import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;
/**
 *
 * @author alemyre
 */
public class ListSiege implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Siege> listSiege;
    
    public ListSiege(){
		listSiege = new LinkedList<Siege>();
	}

	public void add(Siege item){
		listSiege.add(item);
	}

	public boolean isEmpty() {
		return listSiege.isEmpty();
	}

	public List<Siege> getListSiege() {
		return listSiege;
	}

	public int getNbSiege() {
		return listSiege.size();
	}
    
}
