//this class is basically a struct to store info about each file line of row, col, and the val
public class PuzzleData {

	private int row;
	private int col;
	private int val;
	
	public PuzzleData(int r, int c, int v) {
		row = r;
		col = c;
		val = v;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public int getVal() {
		return val;
	}
}
