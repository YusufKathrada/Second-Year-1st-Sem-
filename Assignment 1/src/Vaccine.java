// Yusuf Kathrada
// KTHYUS001
// Vaccine

/** 
* Creates a Vaccine object which stores the country, date and number of vaccinations
* @author Yusuf Kathrada
* @param Line
*/
public class Vaccine implements Comparable<Vaccine>
{
    String country;
    String date;
    String Vaccinations;
    
    public Vaccine(){}
    
    public Vaccine ( String Line )
    {
    
        // South Africa, 2020-02-01, 5432
    
        String [] Info = Line.split(",");
        country = Info[0];
        date = Info[1];
    
        if (Info.length == 3)
        {	
            Vaccinations = Info[2];
        
        }
        else
        {
            Vaccinations = "0";
        }
    
    
    }
        
      
    /**
    * Compares one Vaccine object to another
    * @author Yusuf Kathrada
    * @param
    * @return the integer 0 if the Vaccine objects match and 1 if they do not match 
    */
            
    public int compareTo (Vaccine v)
    {
        return (country+date).compareTo(v.country+v.date);
    }
    
    /**
    * Converts the Vaccination object into String
    * @author Yusuf Kathrada
    * @param
    * @return the String of the Vaccination object countaining the country and the number of vaccinations 
    */
    public String toString()
    {
    	return country + " = " + Vaccinations;
	}
}
	
