import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MyGrid{
	private GridLayout subGrid, sudokuGrid;
	private MyButton[][] numbers; 
	private int GRID_SIZE = 3;
	private Container container;
	//private ArrayList<PuzzleData> pd;
	private ArrayList<Container> subContainers;
	
	//constructor for the actual grid
	MyGrid(){
		numbers = new MyButton[GRID_SIZE][GRID_SIZE];
		subContainers = new ArrayList<Container>(9);
		sudokuGrid = new GridLayout(GRID_SIZE,GRID_SIZE, 3,3);
		container = new Container();
		container.setLayout(sudokuGrid);

		initializeGrids();

	}
	
	private void initializeGrids() {
		for(int i = 0; i < 9; i++) {
			Container c = new Container();
			c = repaint(c);
			
			subGrid = new GridLayout(GRID_SIZE, GRID_SIZE, 0, 0);
			c.setLayout(subGrid);
			subContainers.add(c);
			c.setVisible(true);
			c.setSize(100, 100);
			container.add(c);
			
		}
		
	}
	//get main container of whole grid
	public Container getContainer() {
		return container;
	}
	//get sub container of sub grid
	public Container getSubContainer(int i) {
		return subContainers.get(i);
	}


	//function to repaint grid every time based on values of button
	public Container repaint(Container subContainer) {
		subContainer.removeAll();
		   for(int row = 0; row < GRID_SIZE; row++) { 
			for(int col = 0; col < GRID_SIZE; col++) {
				//need to add action listener too
				numbers[row][col] = new MyButton(" ");
				
				subContainer.add(numbers[row][col]);
			}
		   }
		   subContainer.revalidate();
		   subContainer.repaint();
		   return subContainer;
	}
	private int mapCoordinate(int x){
		switch(x){
			case 4, 7: return 1;
			case 5, 8: return 2;
			case 6, 9: return 3;
			default: return x;
		}
	}	
	public void setVal(PuzzleData pd, int index){
		int row = mapCoordinate(pd.getRow());
		int col = mapCoordinate(pd.getCol());
		Container subContainer = getSubContainer(index);

		//subContainer.
		
	}
	

}