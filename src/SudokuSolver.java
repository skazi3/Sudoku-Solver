import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class SudokuSolver extends JFrame{
	private Container c;
	private GridLayout grid;
	private int GRID_SIZE = 3;
	private HelperButtons hb;
	private JMenuBar menuBar;
	private MyGrid sudokuGrid;
	
	
	public SudokuSolver() {
		super("Suduko Solver");
	 	hb = new HelperButtons();
		menuBar = returnMenuBar();
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(makeGrid());
		c.add(hb.getPanel(), BorderLayout.EAST);
		c.setBackground(Color.gray);
		setJMenuBar(menuBar);
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
	private JMenuBar returnMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem loadItem = new JMenuItem("Load");
		loadItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add load file method
			}
		});
		JMenuItem storeItem = new JMenuItem("Store");
		
		fileMenu.add(loadItem);
		fileMenu.add(storeItem);
		mb.add(fileMenu);
		
		return mb;
		
	}
	
	
}
	



