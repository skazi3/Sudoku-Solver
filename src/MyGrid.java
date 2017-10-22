import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MyGrid{
	private GridLayout subGrid, sudokuGrid;
	private MyButton[][] nineGrid; 
	private int GRID_SIZE = 3;
	private Container container;
	private ArrayList<MyContainer> subContainers;
	
	//constructor for the actual grid,
	//initialize overarching grids
	MyGrid(){
		nineGrid = new MyButton[GRID_SIZE*3][GRID_SIZE*3];
		subContainers = new ArrayList<MyContainer>(GRID_SIZE);
		sudokuGrid = new GridLayout(GRID_SIZE,GRID_SIZE, 3,3);
		container = new Container();
		container.setLayout(sudokuGrid);

		initializeGrids();

	}
	//initialize subContainers
	private void initializeGrids() {
		for(int j = 0; j < GRID_SIZE*3; j++)
			for(int k = 0; k < GRID_SIZE*3; k++){
				nineGrid[j][k] = new MyButton(" ");
				nineGrid[j][k].setHasVal(false);
			}
		for(int i = 0; i < 9; i++) {
			MyContainer c = new MyContainer();
			repaint(c);
			subContainers.add(c);
			container.add(c);
			
		}
		
	}
	//get main container of whole grid
	public Container getContainer() {
		return container;
	}
	//get sub container of sub grid
	public MyContainer getSubContainer(int i) {
		return subContainers.get(i);
	}

	//function to repaint grid every time based on values of button
	public void repaint(MyContainer subContainer) {
		
		   subContainer.revalidate();
		   subContainer.repaint();
		   
	}
	//map a 9x9 grid to a 3x3 to store in subcontainer
	private int mapCoordinate(int x){
		switch(x){
			case 4:
			case 7:
			 return 1;
			case 5:
			case 8:
			 return 2;
			case 6:
			case 9:
			 return 3;
			default: return x;
		}
	}
	//actually set fixed candidates onto grid
	public void setVal(PuzzleData pd, int index){
		//add to nine grid
		addNineGridButton(pd.getRow()-1, pd.getCol()-1, pd.getVal());
		//add to 3x3 grid by mapping
		int row = mapCoordinate(pd.getRow());
		int col = mapCoordinate(pd.getCol());
		
		MyContainer subContainer = getSubContainer(index);
		subContainer.addButtonValue(pd.getVal(), row, col);
		
	}
	//add button value to nine grid and set has value to true
	private void addNineGridButton(int row, int col, int val) {
		nineGrid[row][col].setActionCommand(Integer.toString(val));
		nineGrid[row][col].setHasVal(true);
	}
	//store current puzzle into a file if the nine grid has a 
	//button with a value in it
	public ArrayList<PuzzleData> getStoredPuzzle() {
		ArrayList<PuzzleData> storedPuzzle = new ArrayList<PuzzleData>();
		//iterate through the nine grid
		for(int row = 0; row < GRID_SIZE*3; row++) {
			for(int col = 0; col < GRID_SIZE*3; col++) {
				if(nineGrid[row][col].hasVal() == true) {
					storedPuzzle.add(new PuzzleData(row+1, col+1, nineGrid[row][col].getVal()));
				}
			}
		}
		
		
		return storedPuzzle;
	}
	

}