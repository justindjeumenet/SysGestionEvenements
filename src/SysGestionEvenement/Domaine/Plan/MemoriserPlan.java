
package SysGestionEvenement.Domaine.Plan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class MemoriserPlan implements Serializable
{
    private ArrayList<PlanDeSalle> myList;
    private int index;
    private int currentList;
    static final int MAX_MEMOIRE = 20;
  
    
    public MemoriserPlan() { 
        myList = new ArrayList<>();
        this.index = myList.size();
        this.currentList = index;      
    }
    
    public void addClonePlan(PlanDeSalle unPlan) throws Exception{

        if (myList.size() < MAX_MEMOIRE)
        {
            Object copie = deepCopy(unPlan);
            this.myList.add((PlanDeSalle)copie);
            this.index++;
            System.err.println(myList);
        }
        else if(myList.size() > MAX_MEMOIRE)
        {
            this.myList.remove(0);
            Object copie = deepCopy(unPlan);
            this.myList.add((PlanDeSalle)copie);
            this.index++;
            System.err.println(myList);
        }

    }
    
    public PlanDeSalle precedent() 
    {
        if(currentList != -1)
        {
        PlanDeSalle planDeSalle = myList.get(this.index - 1);
        currentList = this.index--;
        return planDeSalle;
        }
            currentList = 0;
            return null;    
      }
    
    public PlanDeSalle suivant() {
        
        if(currentList < MAX_MEMOIRE)
        {
        PlanDeSalle planDeSalle = myList.get(this.index + 1);
        currentList = this.index++;
        return planDeSalle;
        }
        this.index = 19;
        return null;
    
 }
    
    //code deep copy pris de ce site web
    //http://www.javaworld.com/article/2077578/learn-java/java-tip-76--an-alternative-to-the-deep-copy-technique.html
    static public Object deepCopy(Object oldObj) throws Exception
   {
      ObjectOutputStream oos = null;
      ObjectInputStream ois = null;
      try
      {
         ByteArrayOutputStream bos = 
               new ByteArrayOutputStream(); // A
         oos = new ObjectOutputStream(bos); // B
         // serialize and pass the object
         oos.writeObject(oldObj);   // C
         oos.flush();               // D
         ByteArrayInputStream bin = 
               new ByteArrayInputStream(bos.toByteArray()); // E
         ois = new ObjectInputStream(bin);                  // F
         // return the new object
         return ois.readObject(); // G
      }
      catch(Exception e)
      {
         System.out.println("Exception in ObjectCloner = " + e);
         throw(e);
      }
      finally
      {
         oos.close();
         ois.close();
      }
   }  
    
    
}
