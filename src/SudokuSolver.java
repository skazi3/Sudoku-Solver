import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

//main class that starts off the game and initializes jframe
public class SudokuSolver extends JFrame{
	private Container c;
	private GridLayout grid;
	private int GRID_SIZE = 3;
	private JMenuBar menuBar;
	private MyGrid sudokuGrid;
	private JLabel curLabel;
	private ArrayList<PuzzleData> loadedPuzzle, storedPuzzle;

	//_______________________________________________________________________//
	//constructor to add everything to container c
	public SudokuSolver() {
		super("Suduko Solver");
		loadedPuzzle = new ArrayList<PuzzleData>();
		storedPuzzle = new ArrayList<PuzzleData>();
		menuBar = returnMenuBar();
		c = getContentPane();
		c.setLayout(new BorderLayout());
		Container sudokuContainer = makeGrid();
		c.add(sudokuContainer);
		c.add(sudokuGrid.getPanel(), BorderLayout.EAST);
		c.setBackground(Color.gray);
		setJMenuBar(menuBar);
		
		setSize(700, 600);
		setVisible(true);
		
	}
	//_______________________________________________________________________//
	//make the myGrid (which contains subgrids and ninegrid
	private Container makeGrid() {
		sudokuGrid = new MyGrid();
		curLabel = new JLabel(sudokuGrid.getCurrentButtonLabel());

		setSize(400, 400);
		setTitle("Sudoku Solver");
		setVisible(true);
		return sudokuGrid.getContainer();
	}
	//_______________________________________________________________________//
	//create menu bar with actions
	private JMenuBar returnMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		JMenu hintsMenu = new JMenu("Hints");
		
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
		
		//hints menu items
		
		
		fileMenu.add(loadItem);
		fileMenu.add(storeItem);
		fileMenu.add(exitItem);
		
		helpMenu.add(aboutSudoku);
		helpMenu.add(aboutGame);
		helpMenu.add(aboutProgrammers);
		
		
		mb.add(fileMenu);
		mb.add(helpMenu);
		mb.add(hintsMenu);
		
		return mb;
		
	}
	//_______________________________________________________________________//
	//returns the index of a container based on its 
	//position on the nine grid
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
	//_______________________________________________________________________//
	//actually set the value sent in to the container
	private void setValues() {
		for(PuzzleData d: loadedPuzzle) {
			int index = getContainerIndex(d.getRow(), d.getCol());
			sudokuGrid.setVal(d, index);
		}
	}
	//_______________________________________________________________________//
	//load file action listener method
	private void loadFile() {
		
		JFileChooser fc = new JFileChooser("/Users/sarahkazi/Documents/cs342/Sudoku-Solver");
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File sf = fc.getSelectedFile();
			try {
				//store into puzzle data
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
		//call set values
		setValues();
	}
	//_______________________________________________________________________//
	//function to write a puzzle to a file
	public void storeFile() {
		storedPuzzle = sudokuGrid.getStoredPuzzle();
		//surround file writer by try and catch
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
	//_______________________________________________________________________//
}
	



