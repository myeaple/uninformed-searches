/**
 * Vertex.java
 * 
 * The purpose of this class is to represent a vertex within an
 * undirected, weighted graph.
 * 
 * @author Michael Yeaple
 *
 */

import java.util.*;

public class Vertex {
	
	private String name;
	// linkedVertices represents the vertices linked to this vertex in the
	// order they were added.
	private LinkedList<String> linkedVertices;
	private boolean visited;
	
	public Vertex(){ }
	
	/**
	 * Vertex() - specific constructor
	 * 
	 * @param name - the "name" of the vertex (an integer).
	 */
	public Vertex(String name)
	{
		this.name = name;
		linkedVertices = new LinkedList<String>();
	}
	
	/**
	 * AddEdge()
	 * 
	 * Adds a new edge connected to the provided vertex with the
	 * provided weight.
	 * 
	 * @param vNew - the vertex to connect to via the new edge.
	 * @param weight - the weight of the edge connecting the two vertices.
	 */
	public void AddEdge(Vertex vNew, long weight) throws VertexException
	{
		String vName = vNew.GetName();
		
		if (!linkedVertices.contains(vName))
		{
			linkedVertices.add(vName);
		}
		else
		{
			throw new VertexException("Edge already exists.");
		}
	}
	
	/**
	 * Visit()
	 * 
	 * Marks the current node as visited.
	 */
	public void Visit()
	{
		visited = true;
	}
	
	/**
	 * Reset()
	 * 
	 * Marks the current node as not visited.
	 */
	public void Reset()
	{
		visited = false;
	}
	
	/**
	 * IsVisited()
	 * 
	 * Returns true if the vertex has already been visited.
	 * 
	 * @return - true: vertex has been visited; false: vertex not visited.
	 */
	public boolean IsVisited()
	{
		return visited;
	}
	
	/**
	 * GetName()
	 * 
	 * Returns the name of the vertex as a string.
	 * 
	 * @return - the name of the vertex as a string.
	 */
	public String GetName()
	{
		return name;
	}
	
	/**
	 * GetEdges()
	 * 
	 * Returns the connected vertices.
	 * 
	 * @return - a linked list of the connected vertices.
	 */
	public LinkedList<String> GetEdges()
	{
		return linkedVertices;
	}
	
}
