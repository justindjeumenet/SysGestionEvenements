/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SysGestionEvenement.Domaine.Utilitaires;
import java.awt.Point;
import java.util.List;
/**
 *
 * @author alemyre
 */
public class ListPoints {
    private List<Point> listPoints;

    public ListPoints() {
    }
    
    public List<Point> getListPoints() {
        return listPoints;
    }
    
    public void ajouterPoint(Point unPoint){
        listPoints.add(unPoint);
    }
    
    
}
