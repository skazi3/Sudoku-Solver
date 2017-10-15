import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class SudokuSolver extends JFrame{
	private Container container;
	private GridLayout grid;
	private int GRID_SIZE = 3;
	
	public SudokuSolver() {
		super("Suduko Solver");
		grid = new GridLayout(GRID_SIZE,GRID_SIZE, 3,3);
		
		container = getContentPane();
		container.setLayout(grid);
		//loop through and add a sub-grid
		for(int row = 0; row < GRID_SIZE; row++) {
			for(int col = 0; col < GRID_SIZE; col++) {
				container.add(new MyGrid().getContainer());
			}
		}
		
		setSize(200, 200);
		setTitle("Sudoku Solver");
		setVisible(true);
		
	}
}
	



