/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysGestionEvenement.Controlleur;

import SysGestionEvenement.Domaine.Plan.EspaceVital;
import SysGestionEvenement.Domaine.Plan.MemoriserPlan;
import SysGestionEvenement.Domaine.Plan.PlanDeSalle;

import SysGestionEvenement.Domaine.PrixOffre.Prix;
import SysGestionEvenement.Domaine.PrixOffre.ListPrix;
import SysGestionEvenement.Domaine.PrixOffre.Offre;
import SysGestionEvenement.gui.Dessin.PlanDrawer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author alemyre
 */
public class PlanControlleur 
{

    private PlanDeSalle plan;
    private int longueur;
    private int largeur;
    private ListPrix listPrix;
    private MemoriserPlan unMemo;
    
    public PlanControlleur()
    {     
       //plan=new PlanDeSalle(MainWindow mainWindow);
        this.plan=new PlanDeSalle(0, 0, 0, 0);
        this.listPlan=new LinkedList<PlanDeSalle>();
        System.out.println("mainwindow a appeler plancontrolleur");   
        this.unMemo = new MemoriserPlan();
        
        // instanciation de la liste de prix
        this.listPrix = new ListPrix();
    }

    public PlanDeSalle getPlan() {
        return plan;
    }

    public void setPlan(PlanDeSalle plan) {
        this.plan = plan;
    }

    public List<PlanDeSalle> getListPlan() {
        return listPlan;
    }

    public void setListPlan(List<PlanDeSalle> listPlan) {
        this.listPlan = listPlan;
    }
 
    
    private List <PlanDeSalle> listPlan;
    public PlanControlleur(PlanDeSalle p_plan)
    {
        this.plan=p_plan;
    }
    
   public void ajoutSectionRectangulaire(String p_nomSection,int p_elevationSection,int p_angleSection,Dimension p_espaceVitalSection,int p_nbLigne,int p_nbColonne,Point p_coinGauche,Point p_centreScene)
    { 
       EspaceVital espace=new EspaceVital(p_espaceVitalSection.width,p_espaceVitalSection.height);
       plan.ajoutSectionRectangle(p_nomSection, p_elevationSection, p_angleSection, espace, p_nbLigne, p_nbColonne, p_coinGauche,p_centreScene);
       // this.unMemo.addClonePlan(plan);
    }
   
    public void ajoutSectionIrreguliere(String p_nomSection,int p_elevationSection,int p_angleSection,Dimension p_espaceVitalSection,Point p_centreScene, Polygon poly)
    { 
       EspaceVital espace=new EspaceVital(p_espaceVitalSection.width,p_espaceVitalSection.height);
       plan.ajoutSectionIrreguliere(p_nomSection, p_elevationSection, p_angleSection, espace, p_centreScene,poly);
    }
    public void ajoutSectionIrreguliereAuto(String p_nomSection,int p_elevationSection,int p_angleSection,Dimension p_espaceVitalSection,Point p_centreScene, Polygon poly)
    { 
       EspaceVital espace=new EspaceVital(p_espaceVitalSection.width,p_espaceVitalSection.height);
       plan.ajoutSectionIrreguliereAuto(p_nomSection, p_elevationSection, p_angleSection, espace, p_centreScene,poly);
    }
     public void ajoutSectionGenerale(String p_nomSection,int p_elevationSection,int p_angleSection,Dimension p_espaceVitalSection,Point p_centreScene, Polygon poly,int qtePersonnes)
    { 
       EspaceVital espace=new EspaceVital(p_espaceVitalSection.width,p_espaceVitalSection.height);
       plan.ajoutSectionGenerale(p_nomSection, p_elevationSection, p_angleSection, espace, p_centreScene,poly,qtePersonnes);
    }
    public void ajouterSiegeManuel(double x, double y) {
          plan.ajouterSiegeManuel(x, y);
       }
    
    public void creerPrix(double p_prix, Color couleur)
   {
       
       Prix newPrix = new Prix(p_prix, couleur); 
       plan.ajoutPrixList(newPrix);
         
   }
    
    public List<Prix> getListPrix()
   {
       return plan.getListePrix();
   }
    
    public Double creePrixAuto(Double prixMin, Double prixMax, String p_attribution)
    {
       return this.plan.creePrixAuto(prixMin, prixMax, p_attribution);
    }
    
    public void assignerPrixElementSelectionne(Color couleur, Double prix)
   {
       this.plan.assignerPrixElementSelectionne(couleur, prix);
   }
    
    public Dimension clikerZoom(){
       return plan.zoomer();
    }       

    public Dimension clikerDezoomer(){
        return plan.dezoomer();
        
    }
    
    public Dimension afficherDialogPourModifierPlanDeSalle(int longueur, int largeur){
        return plan.modifierPlanDeSalle(longueur, largeur);
    }
    
    
    public TitledBorder bordureDessin(){
        Border lineNoir;
        TitledBorder titre;
        lineNoir = BorderFactory.createLineBorder(Color.red);
        titre = BorderFactory.createTitledBorder(lineNoir, "Salle, placez vos objets ici svp");
        titre.setTitleJustification(TitledBorder.CENTER);
        return titre;
    }
    
    public Box boxPourCadrerZoneDessin(JPanel jpanel){
      Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //box.setBackground(Color.red);
        //box.setLocation(150, 150);
                
        box.add(Box.createVerticalGlue());
        //box.add(Box.createHorizontalGlue());
        box.add(jpanel);
        box.add(Box.createVerticalGlue());
        return box;
    }
    
    public void ajouterUneScene(Point debut, Point fin){
         plan.ajouterUneScene(debut,fin);

    }
   public void switchSelectionStatusGen(double x, double y) {
        plan.switchSelectionStatusGen(x, y);
    }
    public void updateSelectedItemsPositionsGen(Point delta) {
        //System.out.println("Controlleur call plan deplacement");
        plan.updateSelectedItemsPositionGen(delta);
    } 
    public void switchSelectionStatus(double x, double y) {
        plan.switchSelectionStatus(x, y);
    }
    
    public void switchSelectionStatusRange(double x, double y) {
        plan.switchSelectionStatusRange(x, y);
    }
    public void switchSelectionStatusSiege(double x, double y) {
        plan.switchSelectionStatusSiege(x, y);
    }
    public void switchSelectionStatusIrr(double x, double y) {
        plan.switchSelectionStatusIrr(x, y);
    }
public void switchSelectionStatusRangeIrr(double x, double y) {
        plan.switchSelectionStatusRangeIrr(x, y);
    }
    public void switchSelectionStatusSiegeIrr(double x, double y) {
        plan.switchSelectionStatusSiegeIrr(x, y);
      
    }
    
    public void updateSelectedItemsPositions(Point delta) {
        System.out.println("Controlleur call plan deplacement");
        plan.updateSelectedItemsPosition(delta);
    }
    public void updateSelectedItemsPositionsIrr(Point delta) {
        System.out.println("Controlleur call plan deplacement");
        plan.updateSelectedItemsPositionIrr(delta);
    }

    
    public void updateSelectedScenePositions(Point delta) {
        System.out.println("Controlleur call scene deplacement");
        plan.updateSelectedScenePosition(delta);
    }
    
    public void switchSelectionStatusScene(double x, double y) {
        plan.switchSelectionStatusScene(x, y);
    }
    
    public void modifierSceneDimension(int p_longueur, int p_largeur){
        this.plan.modifierSceneDimension(p_longueur, p_largeur);
    }
    
    public void modifierEspaceVitalPlan(EspaceVital espaceVital,EspaceVital espaceVitaldefaut) {
        this.plan.setEspaceVital(espaceVital);
        this.plan.modifierEspaceVitalSections(espaceVital, espaceVitaldefaut);
        
        
    }
    
    //pour la sauvegarde du plan
    public PlanDeSalle getPlanDeSalle()
    {
        return plan.getPlanDeSalle();
    }
    
    // pour l'ouverture du plan
    public void ouvrirPlan(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException   
    {
        this.setPlan(plan.ouvrirPlan(filePath));
    }
    
    public Point calculPointMagnetise(Point point)
    {
        return plan.calculPointMagnetise(point);
    }
    
        public void activateGrid()
    {
        plan.activateGrid();

    }
    
    public void deActivateGrid()
    {
        plan.deActivateGrid();

    }
    
    public boolean gridStatus()
    {
        return plan.gridStatus();
    }
    
    public int getGridSize()
    {
        return plan.getGridSize();
    }
    
     public void creerOffre(double pourcentage, String description)
   {
       
       Offre offre = new Offre(pourcentage, description); 
       plan.ajoutOffreList(offre);
         
   }
    
    public List<Offre> getListOffre()
   {
       return plan.getListeOffre();
   }
    
   public void assignerOffreElementSelectionne(double pourcentage, String description)
   {
       this.plan.assignerOffreElementSelectionne(pourcentage, description);
   }
   
   public void removeOffreElementSelectionne(double pourcentage, String description)
   {
       this.plan.removeOffreElementSelectionne(pourcentage, description);
   }
   
   public void annulerDerniereModification() {
        System.out.println("appele annuler controleur dernier");
        //this.plan = unMemo.precedent();
        
        setPlan(unMemo.precedent());
    }

    public void retablirDerniereModification() {
            this.plan =unMemo.suivant();
        
    }
    
   public void memoriserPlan() throws Exception
   {
       unMemo.addClonePlan(this.getPlan());
   }
   
   
    public void exporterImage() throws Exception{
        
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
        fc.showSaveDialog(null);
        String path = fc.getSelectedFile().getAbsolutePath();
        BufferedImage image = new BufferedImage(plan.getInitialDimension().width, plan.getInitialDimension().height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.fillRect(0, 0, plan.maDimension.width, plan.maDimension.height);
        
        String name = path + ".png";
        
        PlanDrawer mainDessinateur = new PlanDrawer(this, this.plan.maDimension);
        mainDessinateur.dessiner(g);
        g.dispose();
        File outputfile = new File(name);
            try{
            ImageIO.write(image, "png", outputfile);
            }catch (IOException e) {
                System.out.println("erreur lors de la sauvegarde");
            }
            
      
    
    }
    public void roterScene(int angle){
        this.plan.roterScene(angle);
        
    }
    
    public void roterSectionRect(int angle){
        this.plan.roterSestionRectangulaire(angle);
        //System.out.println("angle controlleur"+angle);
        
    }

}
