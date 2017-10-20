import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MyGrid{
	private GridLayout subGrid, sudokuGrid;
	private MyButton[][] numbers; 
	private int GRID_SIZE = 3;
	private Container subContainer, container;
	
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
		numbers[row][col].setActionCommand(Integer.toString(val));
		numbers[row][col].setText(Integer.toString(val));
		
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