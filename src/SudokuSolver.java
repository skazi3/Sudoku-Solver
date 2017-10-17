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
		
		//redraws suduko grid with the proper values at each row,col
		if(menuBar.loadClicked == true) {
			redrawGrid();
			
		}
		setSize(450, 450);
		setVisible(true);
		
	}
	private void redrawGrid() {
		ArrayList<PuzzleData> data = menuBar.getData();
		
	}
	
	private Container makeGrid() {
		MyGrid sudokuGrid = new MyGrid();
		setSize(400, 400);
		setTitle("Sudoku Solver");
		setVisible(true);
		return sudokuGrid.getContainer();
	}
	
	
}
	



