import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class SudokuSolver extends JFrame{
	private Container c;
	private GridLayout grid;
	private int GRID_SIZE = 3;
	private HelperButtons hb;
	private JMenuBar menuBar;
	private MyGrid sudokuGrid;
	private ArrayList<PuzzleData> loadedPuzzle, storedPuzzle;
	
	
	public SudokuSolver() {
		super("Suduko Solver");
		loadedPuzzle = new ArrayList<PuzzleData>();
		storedPuzzle = new ArrayList<PuzzleData>();
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
		JMenu helpMenu = new JMenu("Help");
		
		//file menu items
		JMenuItem loadItem = new JMenuItem("Load");
		loadItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFile();
				repaint();
			}
		});
		JMenuItem storeItem = new JMenuItem("Store");
		storeItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				storeFile();
			}
		});
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		//help menu items
		JMenuItem aboutSudoku = new JMenuItem("About Sudoku");
		aboutSudoku.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,
		    		    "How Sudoku works!:",
		    		    "About Sudoku",
		    		    JOptionPane.PLAIN_MESSAGE);
			}
		});
		JMenuItem aboutGame = new JMenuItem("About the Interface");
		aboutGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,
		    		    "How our interface works!:",
		    		    "About the Interface",
		    		    JOptionPane.PLAIN_MESSAGE);
			}
		});
		JMenuItem aboutProgrammers = new JMenuItem("About the Writers");
		aboutProgrammers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,
		    		    "This game was coded by: \nSarah Kazi: skazi3\n"
		    		    + "Zaynab Almoujahed: \nKayla Condrella: \n",
		    		    "About Us!",
		    		    JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		fileMenu.add(loadItem);
		fileMenu.add(storeItem);
		fileMenu.add(exitItem);
		
		helpMenu.add(aboutSudoku);
		helpMenu.add(aboutGame);
		helpMenu.add(aboutProgrammers);
		
		
		mb.add(fileMenu);
		mb.add(helpMenu);
		
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
		for(PuzzleData d: loadedPuzzle) {
			int index = getContainerIndex(d.getRow(), d.getCol());
			sudokuGrid.setVal(d, index);
		}
	}
	
	//load file action listener method
	private void loadFile() {
		
		JFileChooser fc = new JFileChooser("/Users/sarahkazi/Documents/cs342/Sudoku-Solver");
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
			        	loadedPuzzle.add(new PuzzleData(row, col, val));
    
		        }

		        sc.close();
		    } 
		    catch (FileNotFoundException fnf) {
		        fnf.printStackTrace();
		    }

		}
		setValues();
	}
	public void storeFile() {
		storedPuzzle = sudokuGrid.getStoredPuzzle();
		
		try {
			PrintWriter writer = new PrintWriter("storedPuzzleData.txt", "UTF-8");
			for(PuzzleData d: storedPuzzle) {
				writer.println(d.getRow() + " " + d.getCol() + " " + d.getVal());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
	



