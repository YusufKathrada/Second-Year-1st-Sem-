// Graph class: evaluate shortest paths.
//
// CONSTRUCTION: with no parameters.
//
// ******************PUBLIC OPERATIONS**********************
// void addEdge( String v, String w, double cvw )
//                              --> Add additional edge
// void printPath( String w )   --> Print path after alg is run
// void unweighted( String s )  --> Single-source unweighted
// void dijkstra( String s )    --> Single-source weighted
// void negative( String s )    --> Single-source negative weighted
// void acyclic( String s )     --> Single-source acyclic
// ******************ERRORS*********************************
// Some error checking is performed to make sure graph is ok,
// and to make sure graph satisfies properties needed by each
// algorithm.  Exceptions are thrown if errors are detected.

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
import java.lang.StringBuilder;

public class Graph
{
    public static final double INFINITY = Double.MAX_VALUE;
    private Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );

    /**
     * Add a new edge to the graph.
     */
    public void addEdge( String sourceName, String destName, double cost )
    {
        Vertex v = getVertex( sourceName );
        Vertex w = getVertex( destName );
        v.adj.add( new Edge( w, cost ) );
    }

    /**
     * Driver routine to handle unreachables and print total cost.
     * It calls recursive routine to print shortest path to
     * destNode after a shortest path algorithm has run.
     */
    public void printPathto( String destName )
    {
        Vertex w = vertexMap.get( destName );
        if( w == null )
            throw new NoSuchElementException( "Destination vertex not found" );
        else if( w.dist == INFINITY )
            System.out.println( destName + " is unreachable" );
        else
        {
            printPathvto( w );
        }
    }
    
     public void printPathback( String destName )
    {
        Vertex w = vertexMap.get( destName );
        if( w == null )
            throw new NoSuchElementException( "Destination vertex not found" );
        else if( w.dist == INFINITY )
            System.out.println( destName + " is unreachable" );
        else
        {
            printPathvback( w );
            System.out.println( );
        }
    }

    /**
     * If vertexName is not present, add it to vertexMap.
     * In either case, return the Vertex.
     */
    private Vertex getVertex( String vertexName )
    {
        Vertex v = vertexMap.get( vertexName );
        if( v == null )
        {
            v = new Vertex( vertexName );
            vertexMap.put( vertexName, v );
        }
        return v;
    }

    /**
     * Recursive routine to print shortest path to dest
     * after running shortest path algorithm. The path
     * is known to exist.
     */
     
    private void printPath( Vertex dest )
    {
        if( dest.prev != null )
        {
            printPath( dest.prev );

        }
        
        System.out.print(dest.name);
        System.out.print(" ");
    }
    
   String k = "";
   private void printPathvto( Vertex dest )
    {
        boolean yes = true;
        if (yes == true)
        {
			if( dest.prev != null )
			{
				printPath( dest.prev );
				yes = false;
			}
			k = dest.name + " " + k; 
			
		}
		System.out.print(k.substring(1,k.indexOf(dest.name)+1));
        //System.out.print(h.substring(1));
        
    }
   
   String h = "";
   private void printPathvback( Vertex dest )
    {
        boolean on = true;
        if (on == true)
        {
			if( dest.prev != null && dest.name.equals( dest.name ))
			{
				printPath( dest.prev );
				on = false;
			}
			h = dest.name + " "; 
			
		}
		System.out.print(h.substring(0));
        //System.out.print(h.substring(1));
        
    }
    
    /**
     * Initializes the vertex output info prior to running
     * any shortest path algorithm.
     */
    private void clearAll( )
    {
        for( Vertex v : vertexMap.values( ) )
            v.reset( );
    }

    /**
     * Single-source weighted shortest-path algorithm. (Dijkstra) 
     * using priority queues based on the binary heap
     */
    public void dijkstra( String startName )
    {
        PriorityQueue<Path> pq = new PriorityQueue<Path>( );

        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        clearAll( );
        pq.add( new Path( start, 0 ) ); start.dist = 0;
        
        int nodesSeen = 0;
        while( !pq.isEmpty( ) && nodesSeen < vertexMap.size( ) )
        {
            Path vrec = pq.remove( );
            Vertex v = vrec.dest;
            if( v.scratch != 0 )  // already processed v
                continue;
                
            v.scratch = 1;
            nodesSeen++;

            for( Edge e : v.adj )
            {
                Vertex w = e.dest;
                double cvw = e.cost;
                
                if( cvw < 0 )
                    throw new GraphException( "Graph has negative edges" );
                    
                if( w.dist > v.dist + cvw )
                {
                    w.dist = v.dist +cvw;
                    w.prev = v;
                    pq.add( new Path( w, w.dist ) );
                }
            }
        }
    }


    /**
     * Process a request; return false if end of file.
     */
    public static String processRequest(String hospital, String victim, Graph g )
    {
        double totalcost = 0;
        String startName = hospital;
        String destName = victim;
        try
        {
          
            g.dijkstra( startName );
            Vertex toHospital = g.getVertex( destName );
            totalcost = totalcost + toHospital.dist;
            
            g.dijkstra(destName);
            Vertex toVictim = g.getVertex( startName );
            totalcost = totalcost + toVictim.dist;
             
            /*System.out.println();
            g.dijkstra( startName );
            g.printPathto( destName );
            g.dijkstra(destName);
            g.printPathback( startName );*/
            
    
        }
        catch( NoSuchElementException e )
          { System.out.println("No such element"); }
        catch( GraphException e )
          { System.err.println( e );}
        
         return Double.toString(totalcost) + " " + startName + " " + destName;
        
        }
      
 }
