import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.*;


public class SudokuSolver extends JFrame{
	private Container c;
	private GridLayout grid;
	private int GRID_SIZE = 3;
	private HelperButtons hb;
	private JMenuBar menuBar;
	private MyGrid sudokuGrid;
	private ArrayList<PuzzleData> pd;
	
	
	public SudokuSolver() {
		super("Suduko Solver");
		pd = new ArrayList<PuzzleData>();
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
				loadFile();
				repaint();
			}
		});
		JMenuItem storeItem = new JMenuItem("Store");
			
		
		fileMenu.add(loadItem);
		fileMenu.add(storeItem);
		mb.add(fileMenu);
		
		return mb;
		
	}
	private int getContainerIndex(int row, int col) {
		if(row <= 3 && row >= 1 && col <= 3 && col >=1) 
			return 0;
		else if(row <= 3 && row >= 1 && col <= 6 && col >=4) 
			return 1;
		else if(row <= 3 && row >= 1 && col <= 9 && col >=7) 
			return 2;
		else if(row <= 6 && row >= 4 && col <= 3 && col >=1) 
			return 3;	
		else if(row <= 6 && row >= 4 && col <= 6 && col >=4) 
			return 4;
		else if(row <= 6 && row >= 4 && col <= 9 && col >=7) 
			return 5;
		else if(row <= 9 && row >= 7 && col <= 3 && col >=1) 
			return 6;	
		else if(row <= 9 && row >= 7 && col <= 6 && col >=4) 
			return 7;
		else if(row <= 9 && row >= 7 && col <= 9 && col >=7) 
			return 8;
		
		return -1;
		
	}
	private void setValues() {
		for(PuzzleData d: pd) {
			int index = getContainerIndex(d.getRow(), d.getCol());
			sudokuGrid.setVal(d, index);
		}
	}
	
	//load file action listener method
	private void loadFile() {
		
		JFileChooser fc = new JFileChooser("/Users/sarahkazi/Documents/cs342/project3");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File sf = fc.getSelectedFile();
			try {

		        Scanner sc = new Scanner(sf);
		        int row =0, col= 0, val = 0;
		        while (sc.hasNextLine()) {
			        	if(sc.hasNextInt()) {
			            row = sc.nextInt();
			            col = sc.nextInt();
			            val = sc.nextInt();
			        	}
			        	pd.add(new PuzzleData(row, col, val));
    
		        }

		        sc.close();
		    } 
		    catch (FileNotFoundException fnf) {
		        fnf.printStackTrace();
		    }

		}
		setValues();
	}
	
	
}
	



