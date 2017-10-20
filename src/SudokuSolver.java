import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class SudokuSolver extends JFrame{
	private Container c;
	private GridLayout grid;
	private int GRID_SIZE = 3;
	private HelperButtons hb;
	private MyJMenuBar menuBar;
	private MyGrid sudokuGrid;
	
	
	public SudokuSolver() {
		super("Suduko Solver");
	 	hb = new HelperButtons();
		menuBar = new MyJMenuBar();
		
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
		sudokuGrid = new MyGrid();
		setSize(400, 400);
		setTitle("Sudoku Solver");
		setVisible(true);
		return sudokuGrid.getContainer();
	}
	
	
}
	



