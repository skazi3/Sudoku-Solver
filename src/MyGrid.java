import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MyGrid{
	private GridLayout subGrid, sudokuGrid;
	private MyButton[][] numbers; 
	private int GRID_SIZE = 3;
	private Container subContainer, container;
	private ArrayList<PuzzleData> pd;
	
	//constructor for the actual grid
	MyGrid(){

		sudokuGrid = new GridLayout(GRID_SIZE,GRID_SIZE, 3,3);
		container = new Container();
		container.setLayout(sudokuGrid);

		//loop through and add a sub-grid
		for(int i =0; i < GRID_SIZE*3; i++) {
			container.add(initializeSubGrid());
		}

	}
	
	private Container initializeSubGrid() {
		subGrid = new GridLayout(GRID_SIZE,GRID_SIZE,0, 0);
		numbers = new MyButton[GRID_SIZE][GRID_SIZE];
		
		subContainer = new Container();
		subContainer.setLayout(subGrid);
		
		//loop through, initialize button and add to container
		repaint();
		return subContainer;
	}
	//get main container of whole grid
	public Container getContainer() {
		return container;
	}
	//get sub container of sub grid
	public Container getSubContainer() {
		return subContainer;
	}
	public void setValue(int row, int col, int val) {
		int gridNo = getGridNo(row, col);
		pd.add(new PuzzleData(row, col, val, gridNo));
		
	}
	private int getGridNo(int row, int col) {
		int gridNum = 0;
		if(row>=1 && row <=3 && col >=1 && col <=3) 
			gridNum = 1;
		else if(row>=1 && row <=3 && col >= 4 && col <=6) 
			gridNum = 2;
		else if(row>=1 && row <=3 && col >= 7 && col <=9) 
			gridNum = 3;
		else if(row>=4 && row <=6 && col >= 1 && col <=3) 
			gridNum = 4;
		else if(row>=4 && row <=6 && col >= 4 && col <=6) 
			gridNum = 5;
		else if(row>=4 && row <=6 && col >= 7 && col <=9) 
			gridNum = 6;
		else if(row>=7 && row <=9 && col >= 1 && col <=3) 
			gridNum = 7;
		else if(row>=7 && row <=9 && col >= 4 && col <=6) 
			gridNum = 8;
		else if(row>=7 && row <=9 && col >= 7 && col <=9) 
			gridNum = 9;
		
		return gridNum;
	}
	
	//function to repaint grid every time based on values of button
	public void repaint() {
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
	}
	

}