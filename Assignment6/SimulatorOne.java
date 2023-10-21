import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
  /**
     * A main routine that:
     * 1. Reads a file containing edges (supplied as a command-line parameter);
     * 2. Forms the graph;
     * 3. Repeatedly prompts for two vertices and
     *    runs the shortest path algorithm.
     * The data file is a sequence of lines of the format
     *    source destination cost
     */


public class SimulatorOne{
	public static void main(String [] args){
	
	Graph gr = new Graph();
		
	try{
		
		//File f = new File("Trial10.txt");
		/*if (f.exists()) 
            System.out.println("Exists"); 
        else
            System.out.println("Does not Exists");*/
        
        //Scanner
		Scanner graphFile = new Scanner(System.in);
		//Scanner graphFile = new Scanner(f);
		//Number of vertices
		int vertices = Integer.parseInt(graphFile.nextLine());
		//Vertex-Edge connections
		String vertex;

		for (int i=1; i< vertices+1; i++)
		{		
			vertex = graphFile.nextLine().trim();
			//System.out.println(vertex.substring(0,1) + " is a source vertex.");
			String array1[]= vertex.substring(2).split(" ");
			for (int j = 0; j < array1.length; j += 2) {
				//System.out.println(vertex.substring(0,1)+ " " + array1[j] + " " + array1[j+1]);
				String source  = vertex.substring(0,1);
				String dest    = array1[j];
				int    cost    = Integer.parseInt( array1[j+1] );
				gr.addEdge( source, dest, cost );		
			}
		}
			
		
		//Number of Hospitals
		int hospitals = 0; 	
		hospitals = Integer.parseInt(graphFile.nextLine( ).trim());
				//System.out.println(hospitals + " Hospitals:");
		
		//Hospital Vertices
		String HospitalVertex;		
		HospitalVertex = graphFile.nextLine( ).trim();
		String hospArray[]= HospitalVertex.substring(0).split(" ");
		for(int k = 0; k < hospArray.length; k++){
					//System.out.println(hospArray[k] + " ");
		}
				
				//System.out.println("\n");
		
		//Number of Victims
		int victims = 0;
		victims = Integer.parseInt(graphFile.nextLine( ).strip());
				//System.out.println( victims + " Victims:");
		 
		//Victim Vertices
		String VictimVertex;
		VictimVertex = graphFile.nextLine( ).trim();
		String vicArray[] = VictimVertex.split(" ");	
		for(int l = 0; l < vicArray.length; l++){
			  //System.out.println(vicArray[l] + " ");
		}
		
		//ArrayList of ArrayLists
		ArrayList<ArrayList<String> > conductor =  new ArrayList<ArrayList<String> >();

		for(int o = 0; o < vicArray.length; o++)
		{
			  ArrayList<String> calls = new ArrayList<String>();	
			  for (int u = 0; u < hospArray.length; u++)
			  {      
				String mako = gr.processRequest (hospArray[u],vicArray[o], gr);
				calls.add(mako);
						    
			  }
			  conductor.add(calls);	 
      } 
         
		 for (int i = 0; i < conductor.size(); i++) 
		 { 
			System.out.println("victim " + vicArray[i].substring(0));
			List<Integer> costlist = new ArrayList<Integer>();
			List<String> hosplist = new ArrayList<String>();
			List<String> victlist = new ArrayList<String>();
            for (int j = 0; j < conductor.get(i).size(); j++) 
            {
				costlist.add((int)Double.parseDouble(conductor.get(i).get(j).substring(0,conductor.get(i).get(j).indexOf(" ")+1)));
				hosplist.add(conductor.get(i).get(j).substring(conductor.get(i).get(j).indexOf(" ")+1, conductor.get(i).get(j).lastIndexOf(" ")));
				victlist.add(conductor.get(i).get(j).substring(conductor.get(i).get(j).lastIndexOf(" ")+1));
			} 
			
			for (int q = 0; q < conductor.get(i).size(); q++)
			{
				int costa = (int)Double.parseDouble(conductor.get(i).get(q).substring(0,conductor.get(i).get(q).indexOf(" ")+1));
				if(costa == Collections.min(costlist).intValue())
				{
					System.out.println("hospital " + hosplist.get(q));
					gr.dijkstra( hosplist.get(q) );
					gr.printPathto( victlist.get(q) );
					//System.out.print("goin");
					gr.dijkstra(victlist.get(q));
					gr.printPathback( hosplist.get(q) );
				}
			}	
		}
	 }
	 catch( NoSuchElementException e )
          { System.out.println("No such element"); }
      catch( GraphException e )
          { System.err.println( e );}
       catch( Exception e){
			System.out.println("file name non-existant");
		}
	 
	}
}

