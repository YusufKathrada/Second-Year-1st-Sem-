// Yusuf Kathrada
// KTHYUS001
// VaccineArray

/**
* Class that creates a array of Vaccine objects
* @author Yusuf Kathrada
*/
public class VaccineArray
{
    Vaccine[] data = new Vaccine[10000];
    int records = 0;
    int opCount = 0;       // Counts num of data comparisons
    
    /**
    * Adds a Vaccine object to the VaccineArray
    * @author Yusuf Kathrada
    * @param insert the Vaccine object
    */    
    public void add (Vaccine V)
    {
        data[records] = V;
        records++;
    }
    
    /**
    * Searches for a match of the Vaccine object in the VaccineArray
    * @author Yusuf Kathrada
    * @param insert Vaccine object you wish to search for
    * @return the Vaccine object if there is a match and null if there is not a match
    */
    public Vaccine find (Vaccine V)
    {
        // compare to 
     	for (int x = 0; x < records; x++)
     	{
      opCount++;     //instrumentation
     		if (V.compareTo(data[x]) == 0)
     		{
     			return data[x];
     		}
     		
     	}
     	return null;
     } 
     
      
      /**
    * A counter of the number of data comparisons performed
    * @author Yusuf Kathrada
    * @return the number of data comparisons 
    */
      public int getCount()
      {
         return opCount;
      }
      
      /**
    * Clears the data comparisons counter
    * @author Yusuf Kathrada
    */
      public void clearOpCount()
      {
      	opCount = 0;
      }
    
}

