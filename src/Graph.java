/**
 * Graph.java
 * 
 * The purpose of this class is to represent an undirected, weighted graph.
 * 
 * @author Michael Yeaple
 *
 */

import java.util.*;
//import static java.util.Arrays.asList;

public class Graph {

	private int numVertices;
	
	private LinkedList<Vertex> bfsOpen;
	private LinkedList<Vertex> bfsClosed;
	private Stack<Vertex> dfsOpen;
	private LinkedList<Vertex> dfsClosed;
	
	private HashMap<String, Vertex> vertices;
//	private ArrayList<ArrayList<String>> gAdjList;
	
	private String[] vNames = { 
		"A", // 0
		"B", // 1
		"C", // 2
		"D", // 3
		"E", // 4
		"F", // 5
		"G", // 6
		"H", // 7
		"I", // 8
		"J", // 9
		"K", // 10
		"L", // 11
		"M", // 12
		"N", // 13
		"O", // 14
		"P", // 15
		"R"  // 16
	};
	
	/**
	 * Graph()
	 * 
	 * The default constructor. Builds a graph of vertices.
	 */
	public Graph() {
		Generate();
	}
	
	/**
	 * Generate()
	 * 
	 * Generates the graph based upon the variables from the input file.
	 */
	private void Generate()
	{
		final int NUMBER_OF_VERTICES = vNames.length;
		numVertices = NUMBER_OF_VERTICES;
		
		// Initialize our vertices.
		vertices = new HashMap<String, Vertex>();
		for (int i = 0;  i < NUMBER_OF_VERTICES; i++)
		{
			vertices.put(vNames[i], new Vertex(vNames[i]));
//			gAdjList.add(new ArrayList<String>());
		}
		
		try
		{
			// A points to B, C, D
			vertices.get(vNames[0]).AddEdge(vertices.get(vNames[1]), 1);
			vertices.get(vNames[0]).AddEdge(vertices.get(vNames[2]), 1);
			vertices.get(vNames[0]).AddEdge(vertices.get(vNames[3]), 1);
//			gAdjList.get(0).addAll(
//				asList(
//					vNames[1],
//					vNames[2],
//					vNames[3]
//				)
//			);
			
			// B points to E, F, G
			vertices.get(vNames[1]).AddEdge(vertices.get(vNames[4]), 1);
			vertices.get(vNames[1]).AddEdge(vertices.get(vNames[5]), 1);
			vertices.get(vNames[1]).AddEdge(vertices.get(vNames[6]), 1);
//			gAdjList.get(1).addAll(
//				asList(
//					vNames[4],
//					vNames[5],
//					vNames[6]
//				)
//			);
			
			// C points to G
			vertices.get(vNames[2]).AddEdge(vertices.get(vNames[6]), 1);
//			gAdjList.get(2).add(vNames[6]);
			
			// D points to H, I
			vertices.get(vNames[3]).AddEdge(vertices.get(vNames[7]), 1);
			vertices.get(vNames[3]).AddEdge(vertices.get(vNames[8]), 1);
//			gAdjList.get(3).addAll(
//				asList(
//					vNames[7],
//					vNames[8]
//				)
//			);
			
			// E points to J, K, L
			vertices.get(vNames[4]).AddEdge(vertices.get(vNames[9]), 1);
			vertices.get(vNames[4]).AddEdge(vertices.get(vNames[10]), 1);
			vertices.get(vNames[4]).AddEdge(vertices.get(vNames[11]), 1);
//			gAdjList.get(4).addAll(
//				asList(
//					vNames[9],
//					vNames[10],
//					vNames[11]
//				)
//			);
			
			// F points to L, A
			vertices.get(vNames[5]).AddEdge(vertices.get(vNames[11]), 1);
			vertices.get(vNames[5]).AddEdge(vertices.get(vNames[0]), 1);
//			gAdjList.get(5).addAll(
//				asList(
//					vNames[11],
//					vNames[0]
//				)
//			);
			
			// G points to M, N, H
			vertices.get(vNames[6]).AddEdge(vertices.get(vNames[12]), 1);
			vertices.get(vNames[6]).AddEdge(vertices.get(vNames[13]), 1);
			vertices.get(vNames[6]).AddEdge(vertices.get(vNames[7]), 1);
//			gAdjList.get(6).addAll(
//				asList(
//					vNames[12],
//					vNames[13],
//					vNames[7]
//				)
//			);
			
			// H points to A, O, P
			vertices.get(vNames[7]).AddEdge(vertices.get(vNames[0]), 1);
			vertices.get(vNames[7]).AddEdge(vertices.get(vNames[14]), 1);
			vertices.get(vNames[7]).AddEdge(vertices.get(vNames[15]), 1);
//			gAdjList.get(7).addAll(
//				asList(
//					vertices.get(vNames[0]).GetName(),
//					vertices.get(vNames[14]).GetName(),
//					vertices.get(vNames[15]).GetName()
//				)
//			);
			
			// I points to P, R
			vertices.get(vNames[8]).AddEdge(vertices.get(vNames[15]), 1);
			vertices.get(vNames[8]).AddEdge(vertices.get(vNames[16]), 1);
//			gAdjList.get(8).addAll(
//				asList(
//					vNames[15],
//					vNames[16]
//				)
//			);
		} catch (VertexException e){
			UIS.ExitWithError(e);
		}
	}
	
	/**
	 * PrintAdjacencyList()
	 * 
	 * Prints the graph represented as an adjacency list.
	 */
	public void PrintAdjacencyList()
	{		
		System.out.println("\nThe graph as an adjacency list:");
		for (int i = 0; i < vNames.length; i++)
		{
			// Sanity check -- should always be true.
			if (vertices.containsKey(vNames[i]))
			{
				Vertex currVertex = vertices.get(vNames[i]);
				
				LinkedList<String> edges = currVertex.GetEdges();
				
				String currLine = currVertex.GetName() + "->";
				
				for (int j = 0; j < numVertices; j++)
				{
					if (edges.contains(vNames[j]))
					{
						currLine += " " + vNames[j];
					}
				}
				
				System.out.println(currLine);
			}
		}
	}
	
	public void PrintOpenClosed(SearchType sType)
	{
		boolean first = true;
		String openStr = "\nOpen: [ ";
		
		// Default to BFS.
		Iterator<Vertex> qIter = bfsOpen.iterator();
		switch (sType)
		{
			case BFS:
				break;
			case DFS:
				qIter = dfsOpen.iterator();
				break;
			case IDS:
//				qIter = idsOpen.iterator();
				break;
		}
		while(qIter.hasNext())
		{
			if (!first)
				openStr += ", ";
			
			openStr += qIter.next().GetName();
			first = false;
		}
		openStr += " ]";
		
		first = true;
		String closedStr = "Closed: [ ";
		
		// Default to BFS.
		qIter = bfsClosed.iterator();
		switch (sType)
		{
			case BFS:
				break;
			case DFS:
				qIter = dfsClosed.iterator();
				break;
			case IDS:
//				qIter = idsClosed.iterator();
				break;
		}
		while (qIter.hasNext())
		{
			if (!first)
				closedStr += ", ";
			
			closedStr += qIter.next().GetName();
			first = false;
		}
		closedStr += " ]";
		
		System.out.println(openStr);
		System.out.println(closedStr);
	}
	
	/**
	 * BFS()
	 * 
	 * Performs a breadth-first search on the graph. This is actually the
	 * "set up" function.
	 * 
	 * @param find - the name of the node to find
	 */
	public void BFS(String find)
	{
		bfsOpen = new LinkedList<Vertex>();
		bfsClosed = new LinkedList<Vertex>();
		
		System.out.println("Beginning BFS...");
		
		BFSHelper(find);
		
		System.out.println("Finished BFS!");
	}
	
	/**
	 * BFSHelper()
	 * 
	 * Helper to do the actual BFS on the graph.
	 * 
	 * @param find - the name of the node to find
	 */
	private void BFSHelper(String find)
	{
		// Add vertex A
		bfsOpen.add(vertices.get(vNames[0]));
		
		while (!bfsOpen.isEmpty())
		{
			PrintOpenClosed(SearchType.BFS);
			
			Vertex vCurrent = bfsOpen.pop();
			
			LinkedList<Vertex> next = GetNextVertices(vCurrent);
			
			for (int i = 0; i < next.size(); i++)
			{
				Vertex vNext = next.get(i);
				// Only add vertices we don't have in one of our queues.
				if (!bfsOpen.contains(vNext) && !bfsClosed.contains(vNext))
				{
					bfsOpen.add(vNext);
				}
			}
			
			bfsClosed.add(vCurrent);
			
			if (bfsOpen.contains(vertices.get(find)))
			{
				PrintOpenClosed(SearchType.BFS);
				System.out.println("Found " + find + "! Done with BFS.");
				break;
			}
		}
	}
	
	/**
	 * DFS()
	 * 
	 * Performs a depth-first search on the graph.
	 * 
	 * @param find - the name of the node to find
	 */
	public void DFS(String find)
	{
		dfsOpen = new Stack<Vertex>();
		dfsClosed = new LinkedList<Vertex>();
		
		System.out.println("Beginning DFS...");
		
		DFSHelper(find);
		
		System.out.println("Finished DFS!");
	}
	
	/**
	 * DFSHelper()
	 * 
	 * Recursive function to do a depth-first search on the graph.
	 * 
	 * @param find - the name of the node to find
	 */
	private void DFSHelper(String find)
	{
		// Add vertex A
		dfsOpen.push(vertices.get(vNames[0]));
		
		while (!dfsOpen.isEmpty())
		{
			PrintOpenClosed(SearchType.DFS);
			
			Vertex vCurrent = dfsOpen.pop();
			
			LinkedList<Vertex> next = GetNextVertices(vCurrent);
			
			for (int i = 0; i < next.size(); i++)
			{
				Vertex vNext = next.get(i);
				// Only add vertices we don't have in one of our queues.
				if (!dfsOpen.contains(vNext) && !dfsClosed.contains(vNext))
				{
					dfsOpen.push(vNext);
				}
			}
			
			dfsClosed.add(vCurrent);
			
			if (dfsOpen.contains(vertices.get(find)))
			{
				PrintOpenClosed(SearchType.DFS);
				System.out.println("Found " + find + "! Done with BFS.");
				break;
			}
		}
	}
	
	/**
	 * GetNextVertices()
	 * 
	 * Gets a LinkedList of the vertices connected to the one passed in.
	 * 
	 * @param current - the vertex whose connected vertices you want to get.
	 * @return
	 */
	private LinkedList<Vertex> GetNextVertices(Vertex current)
	{
		LinkedList<Vertex> vNext = new LinkedList<Vertex>();
		
		LinkedList<String> edges = current.GetEdges();
		
		for (int i = 0; i < edges.size(); i++)
		{
			vNext.add(vertices.get(edges.get(i)));
		}
		
		return vNext;
	}
	
}
