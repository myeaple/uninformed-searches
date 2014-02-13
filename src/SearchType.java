
public enum SearchType {
	
	BFS(1),
	DFS(2),
	IDS(3);
	
	private int value;
	
	private SearchType(int value) {
		this.value = value;
	}
	
	public int GetValue(){
		return value;
	}
}
