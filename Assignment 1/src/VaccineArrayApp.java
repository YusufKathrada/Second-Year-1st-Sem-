// Yusuf Kathrada
// KTHYUS001
// VaccineArrayApp


import java.io.*;
import java.util.*;
import java.util.Arrays;

/**
* VaccineArrayApp that reads in a file with vaccination data, contains a user interactive interface and outputs results
* @author Yusuf Kathrada
*/
public class VaccineArrayApp
{
      VaccineArray VA =  new VaccineArray();
      Scanner Input2 = new Scanner(System.in);
      int numLines = 0;		// Counts number of lines in file
    
    /**
    * Reads the given "vaccinations.txt" file from which the data is inputted from 
    * @author Yusuf Kathrada
    */
    public void readInFile()
    {
        //Read the content of a file into data structure
        
        File f = new File("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/vaccinations.txt");        
        try {
        Scanner Input = new Scanner(f);
        
        while (Input.hasNextLine())
        {
            
            String Line = Input.nextLine();
            VA.add(new Vaccine(Line));
            numLines++;
            
        }
        //System.out.println("num_lines: "+numLines);
        
      } catch (Exception e) {
      
          // do something, report error
          System.out.println("File cannot be found ");
           }  
    }
   
    /**
    * Provides an interactive user interface for which inputs can be given and the results can be displayed
    * @author Yusuf Kathrada
    */ 
    public void userInterface()
    {
        //Interactive interface
        
        String [] CountryArray = new String[200];
        int Count = 0;
        System.out.println("Enter the date:");
        String date = Input2.nextLine();
        CountryArray[Count] = date;
        Count++; 
        System.out.println("Enter the list of countries (end with an empty line): ");
        String Entry = Input2.nextLine();
        int EntryCounter = 0;
          
          while (!(Entry.isEmpty()))
          {
            
            CountryArray[Count] = Entry;
            Count++;
            Entry = Input2.nextLine();
            EntryCounter++;
           }
           
       
   
             
             
          for (int x = 1; x < (EntryCounter)+1; x++)
          {
              
              Vaccine Temp2 = new Vaccine();
              
              String Temp = CountryArray[x] + "," + CountryArray[0];
              Vaccine Temporary = new Vaccine(Temp);
              Temp2 = VA.find(Temporary);
              if (Temp2 == null)
               {              
                  System.out.println(CountryArray[x] + " = <Not Found>"); 
                  
               }
              
              try
               {
               System.out.println(Temp2.toString());
               }
              
              catch (Exception e) 
              {
                  if (x < (EntryCounter)+1){
              
                  continue;
              
              }
              break;
              }    
         }
    }
 
 /**
 * Automated experiment method which takes subsets of data and writes the results to a file 
 * @author Yusuf Kathrada
 * @param a String file name from which data is inputted and a String file name for which results will write to 
 */   
 public void experiment(String fileName, String resultFile) {
        File f = new File(fileName);
        try {
            int numLines = 0;
            int middleCase = 0;
            int worstCase = 0;
            int bestCase = 0;
            int worstCaseOpp = 0;
            int middleCaseOpp = 0;
            int bestCaseOpp = 0;
            
            Scanner Input = new Scanner(f);
            //Finds the size of an experiment file
            String[] Line = new String[10000];
            int pos = 0;
            while (Input.hasNextLine()) {
                String line = Input.nextLine();
                Line[pos] = line;
                pos++;
                numLines++;
            }

            Input.close();
            worstCase = numLines - 1;
            middleCase = worstCase / 2;
            //System.out.println(worstCase);

            //Finds the average case
            String[] input = new String[2];
            for (int i = 0; i < numLines; i++) {
                if (i == worstCase) {
                    String line = Line[i];
                    input = line.split(",");
                }
            }

            Vaccine Temp2 = new Vaccine();
            String Temp = input[0] + "," + input[1];
            //System.out.println(Temp);
            Vaccine Temporary = new Vaccine(Temp);
            Temp2 = VA.find(Temporary);
            if (Temp2 == null) {
                System.out.println(input[0] + " = <Not Found>");

            }
            worstCaseOpp = VA.opCount;
            //System.out.println("WorstCase: " + worstCaseOpp);

            //Finds the average case
            input = new String[2];
            for (int i = 0; i < numLines; i++) {
                if (i == middleCase) {
                    String line = Line[i];
                    input = line.split(",");
                }
            }

            Temp2 = new Vaccine();
            Temp = input[0] + "," + input[1];
            //System.out.println(Temp);
            Temporary = new Vaccine(Temp);
            Temp2 = VA.find(Temporary);
            if (Temp2 == null) {
                System.out.println(input[0] + " = <Not Found>");

            }
            middleCaseOpp = VA.opCount - worstCaseOpp;
            //System.out.println("AverageCase: " + middleCaseOpp);

            //Finds the best case
            input = new String[2];
            for (int i = 0; i < numLines; i++) {
                if (i == bestCase) {
                    String line = Line[i];
                    input = line.split(",");
                }
            }

            Temp2 = new Vaccine();
            Temp = input[0] + "," + input[1];
            //System.out.println(Temp);
            Temporary = new Vaccine(Temp);
            Temp2 = VA.find(Temporary);
            if (Temp2 == null) {
                System.out.println(input[0] + " = <Not Found>");

            }
            bestCaseOpp = VA.opCount - worstCaseOpp - middleCaseOpp;
            //System.out.println("BestCase: " + bestCaseOpp);

            try {
                FileWriter myWriter = new FileWriter(resultFile);
                myWriter.append("Best Case: " + bestCaseOpp + "\n");
                myWriter.append("Middle Case: " + middleCaseOpp + "\n");
                myWriter.append("Worst Case: " + worstCaseOpp + "\n");
                myWriter.close();
               // System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
            VA.clearOpCount();
     
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        
    }
 

/**
* Tests the valdity of VaccineArrayApp the by reading in data a variety of valid and invalid inputs from a file and outputting the results
* @author Yusuf Kathrada
* @param String file name from which the data will be read from
*/
 public void testData(String fileName) {
        File f = new File(fileName);
        try 
        {
            int numLines = 0;
            int worstCase = 0;
            int worstCaseOpp = 0;

            Scanner Input = new Scanner(f);
            //Finds the size of an experiment file
            String[] Line = new String[10000];
            int pos = 0;
            
            while (Input.hasNextLine())
            {
                String line = Input.nextLine();
                Line[pos] = line;
                pos++;
                numLines++;
            }

            Input.close();
            worstCase = numLines - 1;

            //Finds the worst case
            String[] input = new String[2];
            String hold = "";
            
            for (int i = 0; i < numLines; i++) 
            {
                String line = Line[i];
                input = line.split(",");
                Vaccine Temp2 = new Vaccine();
                String Temp = input[0] + "," + input[1];
                Vaccine Temporary = new Vaccine(Temp);
                Temp2 = VA.find(Temporary);
                
                if (Temp2 == null) 
                {
                    hold = hold + input[0] + " = <Not Found>\n";
                }
                
                if (Temp2 != null) 
                {
                    hold = hold + Temp2.toString() + "\n";
                }
            }
            //System.out.println(hold);
            try 
            {
                FileWriter myWriter = new FileWriter("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/TestDataOutputArray.txt");
                myWriter.append(hold);
                myWriter.close();
            } 
            catch (IOException e) 
            {
                System.out.println("An error occurred.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
 
 
     /**
    * Invokes the getCount() method from the VaccienArray class and prints results of data comparisons
    * @author Yusuf Kathrada
    */    
     public void OPPS()
     {
        System.out.println("");
        System.out.println("Data Comparisons = "+VA.getCount());
     }
     
    public static void main ( String[] args )
    {
    
      //  Vaccine v = new Vaccine("SOuth Africa,2022-01-01,54321");
      //  System.out.println(v.Vaccinations);
      
      VaccineArrayApp VAA = new VaccineArrayApp();
      VAA.readInFile();
      VAA.userInterface();
      VAA.OPPS();
      VAA.testData("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/TestData.txt");
      VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment1.txt", "/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results1.txt");
      VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment2.txt", "/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results2.txt");
        VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment2.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results2.txt");
        VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment3.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results3.txt");
        VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment4.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results4.txt");
        VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment5.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results5.txt");
        VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment6.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results6.txt");
        VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment7.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results7.txt");
        VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment8.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results8.txt");
        VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment9.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results9.txt");
        VAA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment10.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Results10.txt");
    }

}
