/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Utilitaires;

/**
 *
 * @author justindjeumene
 */
public class Conversion {
    
    public static int deMetreEnPixel(double elementEnMetreAConvertir){
      int dimensionConvertieEnPixel = (int)Math.round((elementEnMetreAConvertir * 28.35));
      return dimensionConvertieEnPixel;
    }
    
    public static int deCmEnPixel(double elementEnCmAConvertir){
      int dimensionConvertieEnPixel = (int)Math.round((elementEnCmAConvertir * 0.2835));
      return dimensionConvertieEnPixel;
    }
    
    public static int dePixelEnMetre(double elementEnPixelAConvertir){
      int dimensionConvertieEnMetre = (int)Math.round((elementEnPixelAConvertir / 28.35));
      return dimensionConvertieEnMetre;
    }
    
    public static int dePixelEnCm(double elementEnPixelAConvertir){
      int dimensionConvertieEnCm = (int)Math.round((elementEnPixelAConvertir / 0.2835));
      return dimensionConvertieEnCm;
    }
}
