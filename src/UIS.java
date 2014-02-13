/**
 * MST.java
 * 
 * The purpose of this application is to explore three different uninformed
 * searches -- breadth-first search, depth-first search, and iterative
 * deepening search.
 * 
 * @author Michael Yeaple
 */

public class UIS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Graph g = new Graph();
		
		g.PrintAdjacencyList();
		
		g.BFS("M");
		g.DFS("M");
		g.IDS("M");
	}
	
	/**
	 * ExitWithError()
	 * 
	 * Exits and prints an exception's error message to err when called.
	 * 
	 * @param e - the caught exception.
	 */
	public static void ExitWithError(Exception e)
	{
		System.err.println(String.format("Error: {0}", e.getMessage()));
		System.exit(1);
	}

}
