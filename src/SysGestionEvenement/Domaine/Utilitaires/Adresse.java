/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Domaine.Utilitaires;

/**
 *
 * @author alemyre
 */
public class Adresse 
{
    private int numeroCivique;
    private String nomRue;
    private String codePostal;
    private String ville;
    private String province;

    void Adresse (int p_numeroCivique, String p_nomRue, String p_codePostal, String p_ville, String p_province)
    {
        this.numeroCivique = p_numeroCivique;
        this.nomRue = p_nomRue;
        this.codePostal = p_codePostal;
        this.ville = p_ville;
        this.province = p_province;
    }
    

    void modifierAdresse(Adresse adresse)
    {
        // à compléter
    }
    
        void supprimerAdresse(Adresse adresse)
    {
        // à compléter
    }
    
    
}
