import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class SudokuSolver extends JFrame{
	private Container c;
	private GridLayout grid;
	private int GRID_SIZE = 3;
	
	
	public SudokuSolver() {
		super("Suduko Solver");
		HelperButtons hb = new HelperButtons();
		MyJMenuBar menuBar = new MyJMenuBar();
		
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(makeGrid());
		c.add(hb.getPanel(), BorderLayout.EAST);
		c.setBackground(Color.gray);
		setJMenuBar(menuBar.getMenuBar());
		setSize(450, 450);
		setVisible(true);
		
	}
	
	private Container makeGrid() {
		Container container;
		grid = new GridLayout(GRID_SIZE,GRID_SIZE, 3,3);
		container = new Container();
		container.setLayout(grid);

		//loop through and add a sub-grid
		for(int row = 0; row < GRID_SIZE; row++) {
			for(int col = 0; col < GRID_SIZE; col++) {
				container.add(new MyGrid().getContainer());
			}
			
		}
		
		setSize(400, 400);
		setTitle("Sudoku Solver");
		setVisible(true);
		return container;
	}
	
	
}
	



