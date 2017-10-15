import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class SudokuSolver extends JFrame{
	private Container container;
	private GridLayout grid;
	
	public SudokuSolver() {
		super("Suduko Solver");
		container = getContentPane();
		grid = new GridLayout(3,3, 1,1);
		
		
		setSize(200, 200);
		setVisible( true );
		
	}
	

}

