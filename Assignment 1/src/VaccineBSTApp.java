// Yusuf Kathrada
// KTHYUS001
// VaccineBST
// Final

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


/**
* VaccineBSTApp that reads in a file with vaccination data, contains a user interactive interface and outputs results
* @author Yusuf Kathrada
*/
public class VaccineBSTApp
{
                   
   Scanner input = new Scanner(System.in);

   BinarySearchTree<Entry> vacData = new BinarySearchTree<Entry>();
   
   /**
    * Reads the given "vaccinations.txt" file from which the data is inputted from 
    * @author Yusuf Kathrada
    */
   public void readInFile()
   {
      //Read in file into BST
        
      File f = new File("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/vaccinations.txt");
        
      try 
      {
         Scanner input = new Scanner(f);
        
         while (input.hasNextLine())
         {
            String line = input.nextLine();
            Entry entry = new Entry(line);   
            vacData.insert(entry);		//Insert data into BST
         }
        
      } 
      catch (Exception e) 
      {
          System.out.println("File cannot be found ");	// Report error if file cannot be found
      }
    }
    
    /**
    * Provides an interactive user interface for which inputs can be given and the results can be displayed
    * @author Yusuf Kathrada
    */ 
    public void userInterface()
   {
      //Interactive interface
        
      String [] countryArray = new String[200];
      int count = 0;
      
      System.out.println("Enter the date:");
      
      String date = input.nextLine();
      
      countryArray[count] = date;
      count++; 
      
      System.out.println("Enter the list of countries (end with an empty line): ");
      
      String entry = input.nextLine();
      
      int entryCounter = 0;
          
      while (!(entry.isEmpty()))	// Continue until user enters blank line
      {
         countryArray[count] = entry;
         count++;
         entry = input.nextLine();
         entryCounter++;
      }
        
      for (int x = 1; x < (entryCounter)+1; x++)
      {
          //BinaryTreeNode ( dataType d, BinaryTreeNode<dataType> l, BinaryTreeNode<dataType> r )    
         
              
         String temp = countryArray[x] + "," + countryArray[0];	//Splitting line into array
         Entry temporary = new Entry(temp);
         BinaryTreeNode temp2 = new BinaryTreeNode(temporary, null, null);  //Inserting into BTNode
         temp2 = vacData.find(temporary);	// searching for matches
         
         if (temp2 == null)
         {              
            System.out.println(countryArray[x] + " = <Not Found>");       // If no match 
         }
              
         try
         {
           if (temp2 != null){
           
               String info = temp2.toString();
               System.out.println(info);	//if match, print out results 

               }         
                        } 
            catch (Exception e) 
            {
            if (x < entryCounter){
              
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
           // System.out.println(worstCase);

            //Finds the worst case
            String[] input = new String[2];
            for (int i = 0; i < numLines; i++) {
                if (i == worstCase) {
                    String line = Line[i];
                    input = line.split(",");
                }
            }

	    String Temp = input[0] + "," + input[1];
	    Entry Temporary = new Entry(Temp);
            BinaryTreeNode Temp2 = new BinaryTreeNode(Temporary,null,null);
            Temp2 = vacData.find(Temporary);
            
            if (Temp2 == null) {
                System.out.println(input[0] + " = <Not Found>");

            }
            worstCaseOpp = vacData.opCount;
            //System.out.println("WorstCase: " + worstCaseOpp);

            //Finds the average case
            input = new String[2];
            for (int i = 0; i < numLines; i++) {
                if (i == middleCase) {
                    String line = Line[i];
                    input = line.split(",");
                }
            }

            Temp2 = new BinaryTreeNode(Temporary, null, null);
            Temp = input[0] + "," + input[1];
            Temporary = new Entry(Temp);
            Temp2 = vacData.find(Temporary);
            
            if (Temp2 == null) {
                System.out.println(input[0] + " = <Not Found>");

            }
            middleCaseOpp = vacData.opCount - worstCaseOpp;
           // System.out.println("AverageCase: " + middleCaseOpp);

            //Finds the best case
            input = new String[2];
            for (int i = 0; i < numLines; i++) {
                if (i == bestCase) {
                    String line = Line[i];
                    input = line.split(",");
                }
            }

            Temp2 = new BinaryTreeNode(Temporary, null, null);
            Temp = input[0] + "," + input[1];
            Temporary = new Entry(Temp);
            Temp2 = vacData.find(Temporary);
            
            if (Temp2 == null) {
                System.out.println(input[0] + " = <Not Found>");

            }
            bestCaseOpp = vacData.opCount - worstCaseOpp - middleCaseOpp;
           // System.out.println("BestCase: " + bestCaseOpp);

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
            vacData.clearOpCount();
     
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
                
                String Temp = input[0] + "," + input[1];
                Entry Temporary = new Entry(Temp);
                BinaryTreeNode Temp2 = new BinaryTreeNode(Temporary,null,null);
                Temp2 = vacData.find(Temporary);
                
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
                FileWriter myWriter = new FileWriter("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/TestDataOutputBST.txt");
                myWriter.append(hold);
                myWriter.close();
            } 
            catch (IOException e) 
            {
                System.out.println("An error occurred.");
            }

        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found!");
        }
    }

   /**
   * Invokes the getCount() method from the VaccienArray class and prints results of data comparisons
   * @author Yusuf Kathrada
   */ 
   public void operationsCount()
   {
      System.out.println("");
      System.out.println("Data Comparisons = "+vacData.getCount());
   } 
   
   


   public static void main(String[] args)
   {
      VaccineBSTApp VBSTA = new VaccineBSTApp();
      VBSTA.readInFile();
      VBSTA.userInterface();
      VBSTA.operationsCount();
      VBSTA.testData("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/TestData.txt");
      VBSTA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment1.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/ResultsBST1.txt");
      VBSTA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment2.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/ResultsBST2.txt");
      VBSTA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment3.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/ResultsBST3.txt");
      VBSTA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment4.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/ResultsBST4.txt");
      VBSTA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment5.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/ResultsBST5.txt");
      VBSTA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment6.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/ResultsBST6.txt");
      VBSTA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment7.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/ResultsBST7.txt");
      VBSTA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment8.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/ResultsBST8.txt");
      VBSTA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment9.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/ResultsBST9.txt");
      VBSTA.experiment("/home/yusuf/CSC2001F/Assignments/Assignment 1/data/Experiment10.txt","/home/yusuf/CSC2001F/Assignments/Assignment 1/data/ResultsBST10.txt");
      

	
	///home/yusuf/CSC2001F/Assignments/Assignment 1/data
   }
}
