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
public class ListRange implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Range> listRange;
    
    public ListRange(){
		listRange = new LinkedList<Range>();
	}

	public void add(Range item){
		listRange.add(item);
	}

	public boolean isEmpty() {
		return listRange.isEmpty();
	}

	public List<Range> getListRange() {
		return listRange;
	}

	public int getNbRange() {
		return listRange.size();
	}
     
}
