// Yusuf Kathrada
// KTHYUS001
// VaccineBSTEntry


public class Entry implements Comparable<Entry>
{
   String country;
   String date;
   String Vaccinations;
   
   public Entry(){}
   
   public Entry(String Line)
   {
    
       // South Africa, 2020-02-01, 5432
       
      String [] Info = Line.split(",");
      country = Info[0];
      date = Info[1];
       
      if (Info.length == 3)
      {	
           Vaccinations = Info[2];	//if line of data has vaccinations
      } 
       
       else
      {
           Vaccinations = "0";		// if line of data does not set vaccinations = 0
      }    
       
   }
   
   public int compareTo (Entry e)
   {
      return (country+date).compareTo(e.country+e.date);	// Compare To method for searching 
   }
     
   
   public String toString()
   {
      return country + " = " + Vaccinations;
	}


}