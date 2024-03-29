import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
//Class: Sudoku Solver
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

		setJMenuBar(returnMenuBar());
		
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		Container sudokuContainer = makeGrid();
		c.add(sudokuContainer);
		c.add(sudokuGrid.getPanel(), BorderLayout.EAST);
		c.add(sudokuGrid.getLeftPanel(), BorderLayout.WEST);
		c.setBackground(Color.BLACK);
		
		//set a fixed size
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
	//create menu bar with action listeners
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
		    		    "How Sudoku works!: "
		    		    + "Sudoku is a logic based puzzle that has numbers 1-9 in every column, row, and box."
		    		    + " No number can be repeated in an individual column, row, or box.",
		    		    "About Sudoku",
		    		    JOptionPane.PLAIN_MESSAGE);
			}
		});
		JMenuItem aboutGame = new JMenuItem("About the Interface");
		aboutGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,
		    		    "How our interface works!:\nOur Sudoku game contains one large JFrame, with nine\n"
		    		    + "subContainers, and an underlying nine by nine container to connect each box\n"
		    		    + "A panel on the left side will inform you about which button you have clicked to\n place "
		    		    + "into the tile, if you have the eraser chosen, or will display candidate information\n about"
		    		    + "each button. The hints section allows you to toggle on fill which will stop you from\n placing"
		    		    + "a wrong button, and it also helps perform algorithms to resolve a single cell.\nClicking the last"
		    		    + "menu item will solve the entire board.",

		    		    "About the interface!",
		    		    JOptionPane.PLAIN_MESSAGE);
			}
		});
		JMenuItem aboutProgrammers = new JMenuItem("About the Writers");
		aboutProgrammers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,
		    		    "This game was coded by: \nSarah Kazi: skazi3\n"
		    		    + "Zaynab Almoujahed: zalmou2\nKayla Condrella: kcondr3\n",
		    		    "About Us!",
		    		    JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		//hints menu items
		JCheckBoxMenuItem onFillCheckBox = new JCheckBoxMenuItem("On Fill");
		onFillCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sudokuGrid.setOnFill(true);
			}
		});
		JMenuItem single = new JMenuItem("Single");
		single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sudokuGrid.performSingleAlgorithm();
			}
		});
		JMenuItem hiddenSingle = new JMenuItem("Hidden Single");
		hiddenSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sudokuGrid.performHiddenSingleAlgorithm();
			}
		});
		
		JMenuItem nakedPairs= new JMenuItem("Naked Pairs");
		nakedPairs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sudokuGrid.performNakedPairsAlgorithm();
			}
		});
		
		JMenuItem lockedCandidate = new JMenuItem("Locked Candidate");
		lockedCandidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JMenuItem solvePuzzle = new JMenuItem("Solve Puzzle");
		lockedCandidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sudokuGrid.solvePuzzle(hiddenSingle, single, lockedCandidate, nakedPairs);
			}
		});
		
		//add to the respective menu's
		fileMenu.add(loadItem);
		fileMenu.add(storeItem);
		fileMenu.add(exitItem);
		
		helpMenu.add(aboutSudoku);
		helpMenu.add(aboutGame);
		helpMenu.add(aboutProgrammers);
		
		hintsMenu.add(onFillCheckBox);
		hintsMenu.add(single);
		hintsMenu.add(hiddenSingle);
		hintsMenu.add(lockedCandidate);
		hintsMenu.add(nakedPairs);
		hintsMenu.add(solvePuzzle);
		
		mb.add(fileMenu);
		mb.add(helpMenu);
		mb.add(hintsMenu);
		
		return mb;
		
	}
	//_______________________________________________________________________//
	//returns the index of a container based on its 
	//position on the nine grid (basically to map the numbers given to us in data file
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
	



