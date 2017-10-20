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
				
			}
		});
		JMenuItem storeItem = new JMenuItem("Store");
			
		
		fileMenu.add(loadItem);
		fileMenu.add(storeItem);
		mb.add(fileMenu);
		
		return mb;
		
	}
	private void setValues() {
		
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
	



