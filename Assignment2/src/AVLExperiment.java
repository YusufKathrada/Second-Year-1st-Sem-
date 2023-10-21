// Yusuf Kathrada
// KTHYUS001
// AVLExperiment


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;

/**
* AVLExperiment that reads in data from a file, contains a method to randomise the data, stores it in an AVL Tree and then outputs results to a file
* @author Yusuf Kathrada
*/
public class AVLExperiment {

    Scanner input = new Scanner(System.in);
    Vaccine[] vaccineArray = new Vaccine[9919];
    AVLTree<Vaccine> randomVacData = new AVLTree<Vaccine>();   //Contains randomised vac data

    AVLTree<Vaccine> fullVacData = new AVLTree<Vaccine>();     //Contains vac data in orginal order

	/**
	 * Reads in data from file and stores in an array of type Vaccine 
	 * @author Yusuf Kathrada
	 * @param a String file name from which data is inputted
	 */ 
    public void readInFile(String file) 
    {
        File f = new File(file);
        
        fullVacData.clearInsertionsCount();
        fullVacData.clearOpCount();
        
        try {
            Scanner input = new Scanner(f);
            
            int x = 0;        
                 
            while (input.hasNextLine()) 
            {
                String line = input.nextLine();
                Vaccine vaccine = new Vaccine(line);
                fullVacData.insert(vaccine);
                vaccineArray[x]=vaccine;     // Add all lines to an Array of type Vaccine (currently in order)
                x++;                         
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Incorrect file name entered or not found");
        }

    }
    
/**
 * Randomized AVL Tree outputs results and best, worst and average case to specified files
 * @author Yusuf Kathrada
 * @param a String file name from which data is inputted, a String file name for which results will write to and and and int which is the degree of randomization
 */ 
    public void randomizedFile(String file,String resultsFile, int degree) 
    {
        File f = new File(file);
        
        AVLTree<Vaccine> randomizedData = new AVLTree<Vaccine>();
        
        randomizedData.clearInsertionsCount();
        randomizedData.clearOpCount();
        
        int file_size = 0;
        int position = 0;
        
        String[] Line = new String[10000];
        
        try 
        {
            Scanner input = new Scanner(f);
            

            while (input.hasNextLine()) 
            {
                String line = input.nextLine();
                Vaccine vaccine = new Vaccine(line);
                Line[position] = line;
                
                randomizedData.insert(vaccine);
                position++;
                file_size++;
            }
            
//-------------------------------------------------------------------            
            int worst_case_pos = file_size - 1;
            String[] case_input = new String[2];
            
            for (int i = 0; i < file_size; i++) 
            {
                if (i == worst_case_pos) 
                {
                    String line = Line[i];
                    case_input = line.split(",");
                }
            }

            String temp2 = case_input[0] + "," + case_input[1];
            Vaccine bt_temp = new Vaccine(temp2);
            BinaryTreeNode Temp2 = new BinaryTreeNode(bt_temp, null, null);
            Temp2 = randomizedData.find(bt_temp);
            if (Temp2 == null) 
            {
                System.out.println(case_input[0] + " = <Not Found>");
            }
            
            int worstCaseCount = randomizedData.num_operations;      //Worst Case
            
//-------------------------------------------------------------------    
        
            int average_case_pos = worst_case_pos / 2;
            case_input = new String[2];
            for (int i = 0; i < file_size; i++) 
            {
                if (i == average_case_pos) 
                {
                    String line = Line[i];
                    case_input = line.split(",");
                }
            }

            temp2 = case_input[0] + "," + case_input[1];
            bt_temp = new Vaccine(temp2);
            Temp2 = new BinaryTreeNode(bt_temp, null, null);
            Temp2 = randomizedData.find(bt_temp);
            if (Temp2 == null) 
            {
                System.out.println(case_input[0] + " = <Not Found>");
            }
            int averageCaseCount = randomizedData.num_operations - worstCaseCount; // Average Case
            int bestCaseCount = 1;

//------------------------------------------------------------------------

            // Outputting results to file
            try {
                FileWriter writer = new FileWriter(resultsFile);
                
                writer.append("AVLExperiment User Input Results:\n");
                writer.append("\n");
                
                writer.append("Randomisation Level: "+degree+"\n");
                writer.append("\n");       
                         
                writer.append("Number of data comparisons for data of size "+file_size+ "\n");
                
                writer.append("Best Case: " + bestCaseCount + "\n");
                writer.append("Average Case: " + averageCaseCount + "\n");
                writer.append("Worst Case: " + worstCaseCount + "\n");
                writer.append("\n");
                
                writer.append("Number of insertion-related comparisons: "+randomizedData.num_insertions + "\n");
                //writer.append("Number of search-related comparisons: " +randomizedData.num_operations);
                
                writer.close();
            } 
            catch (IOException e) 
            {
                System.out.println("An error occurred.");
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Incorrect file name entered or not found");
        }
        randomizedData.clearInsertionsCount();     // Clear counts, shouldnt be a running total 
        randomizedData.clearOpCount();

    }

	/**
	 * Randomizes the data in an array and inserts it into an AVL Tree
	 * @author Yusuf Kathrada
	 * @param int which refers to the degree of randomization 
	 */ 
   public void randomization(int a)
   {      
      File f = new File("/home/yusuf/Documents/CSC2001F/Assignments/Assignment2/data/vaccinations.txt");

      Random rd = new Random();
      
      try
      {
         if (a!=0)
         {
         
            int randomFactor = a*495;
            
            // Starting from the last element and swapping one by one.
            for (int i = randomFactor-1; i > 0; i--) 
            {
                   
               // Pick a random index from 0 to i
               int j = rd.nextInt(i+1);
                   
               // Swap array[i] with the element at random index
               // Utilising Fisher-Yates shuffle method to randomise data
               Vaccine temp = vaccineArray[i];
               vaccineArray[i] = vaccineArray[j];
               vaccineArray[j] = temp;
            }
         }
         
         for (int x = 0; x<vaccineArray.length; x++)           // inserting randomised array into the AVL tree
         {
            randomVacData.insert(vaccineArray[x]);
         }
         
         try 
         {
            FileWriter writer = new FileWriter("/home/yusuf/Documents/CSC2001F/Assignments/Assignment2/data/RandomVaccinationList.txt");
            for (int i = 0; i < 9919; i++) 
            {
               writer.append(vaccineArray[i] + "\n");
            }
               writer.close();
            } 
            catch (IOException e) 
            {
               System.out.println("An error occurred.");
            }
         
      }
      catch (Exception e) 
      {
            System.out.println("Incorrect file name entered or not found: " + e);
      }
   }
/**
 * Main method is invoked 
 * @author Yusuf Kathrada
 * @param String[] args 
 */ 
    public static void main(String[] args) {
        Scanner factor = new Scanner(System.in);
        int factorIn = factor.nextInt();
        
        AVLExperiment experiment = new AVLExperiment();
        
        experiment.readInFile("/home/yusuf/Documents/CSC2001F/Assignments/Assignment2/data/vaccinations.txt");
        experiment.randomization(factorIn);
        experiment.randomizedFile("/home/yusuf/Documents/CSC2001F/Assignments/Assignment2/data/RandomVaccinationList.txt","/home/yusuf/Documents/CSC2001F/Assignments/Assignment2/data/UserInputResults.txt",factorIn);
        
        for (int i = 1; i<21; i++)
        {
            experiment.randomization(i);
            
            experiment.randomizedFile("/home/yusuf/Documents/CSC2001F/Assignments/Assignment2/data/RandomVaccinationList.txt","/home/yusuf/Documents/CSC2001F/Assignments/Assignment2/data/Results"+i+".txt",i);
        }


    }

}
