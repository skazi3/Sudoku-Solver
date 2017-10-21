import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MyGrid{
	private GridLayout subGrid, sudokuGrid;
	private MyButton[][] numbers; 
	private int GRID_SIZE = 3;
	private Container container;
	//private ArrayList<PuzzleData> pd;
	private ArrayList<MyContainer> subContainers;
	
	//constructor for the actual grid
	MyGrid(){
		numbers = new MyButton[GRID_SIZE][GRID_SIZE];
		subContainers = new ArrayList<MyContainer>(9);
		sudokuGrid = new GridLayout(GRID_SIZE,GRID_SIZE, 3,3);
		container = new Container();
		container.setLayout(sudokuGrid);

		initializeGrids();

	}
	
	private void initializeGrids() {
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
	public void setVal(PuzzleData pd, int index){
		int row = mapCoordinate(pd.getRow());
		int col = mapCoordinate(pd.getCol());
		MyContainer subContainer = getSubContainer(index);
		
		subContainer.addButtonValue(pd.getVal(), row, col);
		
	}
	

}