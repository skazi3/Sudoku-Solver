//Class: MyGrid
//Container that holds nine subcontainers. Essentially the whole 9x9 grid

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MyGrid{
	//private data members
	private GridLayout             subGrid, sudokuGrid;
	private ArrayList<MyContainer> subContainers;
	private Container              container;
	
	private int          GRID_SIZE = 3;
	private boolean      showCandidates;
	

	//helper buttons
	private MyButton[][] nineGrid; 
	private MyButton     currentButton;
	private GridLayout   digitGrid;
	private JPanel       leftPanel;
	private MyButton[]   digits;
	private JPanel       panel;

	

	private JLabel curLabel; 
	private JLabel modeLabel;
	private JLabel candidatesLabel;
	
	private JList<String> list;
	DefaultListModel<String> l1;
	
	//constructor for the actual grid,
	//initialize all the grids
	MyGrid(){
		//helper buttons for the right side
		digits = new MyButton[11];
		initializeDigits();
		initializeStatus();
		
		//actual grid
		showCandidates= false;
		nineGrid =      new MyButton[GRID_SIZE*3][GRID_SIZE*3];
		subContainers = new ArrayList<MyContainer>(GRID_SIZE);
		sudokuGrid =    new GridLayout(GRID_SIZE,GRID_SIZE, 5,5);
		container =     new Container();
		container.setLayout(sudokuGrid);
		
		initializeGrids();
		for(int i = 0; i < 9; i++)
			subContainers.get(i).setNineGrid(nineGrid);

	}
	//_______________________________________________________________________//
	//initializes the right panel with helper buttons
	private void initializeDigits(){
		digitGrid = new GridLayout(11, 1, 0, 0);
		 
		currentButton = new MyButton(" ", false, -1, -1);
		panel = new JPanel(digitGrid, false);
		//action listener for when any of the helper buttons are clicked
		for(int i = 0; i < 9; i++){
			digits[i] = new MyButton(Integer.toString(i+1), false, -1, -1);
			digits[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeLabel.setText("Insert Mode");
					setCurrentButton((MyButton)e.getSource());
					setCur((MyButton)e.getSource());
					curLabel.setText(getCurrentButtonLabel());
					
					if (!l1.isEmpty())
						l1.removeAllElements();
				}});
			panel.add(digits[i]);
			
		}
		//adds eraser to the eraser button
		Image eraser = new ImageIcon( "eraser.png" ).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon e = new ImageIcon(eraser);
		
		digits[9] = new MyButton(e);
		digits[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eraserChosen();
				modeLabel.setText("Eraser Mode");
				curLabel.setText("Eraser");
				if (!l1.isEmpty())
					l1.removeAllElements();
			}});
		//to show candidates of a button
		digits[10] = new MyButton("?", false, -1, -1);
		digits[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0; i < 9; i++)
					subContainers.get(i).setNineGrid(nineGrid);
				setShowCandidates();
				
				modeLabel.setText("Help Mode");
				curLabel.setText("Candidates: ");
			}
		});
		
		//add the two extra digits
		panel.add(digits[9]);
		panel.add(digits[10]);
		panel.setBackground(Color.LIGHT_GRAY);
		
	}
	//will display candidates on left panel
	public void displayCandidates(MyButton b){
		
		if (!l1.isEmpty())
			l1.removeAllElements();
		if(b.getText() == "?")
			return;
		for (int candidate : b.getCandidates())
          l1.addElement(Integer.toString(candidate));  

       
 
	}
	//_______________________________________________________________________//
	public void setShowCandidates() {
		showCandidates = true;
		for(int i = 0; i < 9; i++)
			subContainers.get(i).setShowCandidates(true);
	
	}
	//_______________________________________________________________________//
	//initializes left panel
	public void initializeStatus(){
		leftPanel = new JPanel(new GridLayout(3, 1, 0, 0), false);
		leftPanel.setBackground(Color.LIGHT_GRAY);
	     
		modeLabel = new JLabel("Insert Mode");
		
		curLabel = new JLabel(getCurrentButtonLabel());
		curLabel.setForeground(Color.black);
		candidatesLabel = new JLabel("Candidates: ");
		
		leftPanel.add(modeLabel);
		leftPanel.add(curLabel);
		l1 = new DefaultListModel<>();
		list = new JList<>(l1);  
	    list.setBounds(100,100, 75,37);  
	    leftPanel.add(list); 
	}
	//_______________________________________________________________________//
	//getters and setters for helper buttons
	public MyButton getButton(int i) {
		return digits[i];
	}
	//_______________________________________________________________________//
	public JPanel getPanel() {
		return panel;
	}
	//_______________________________________________________________________//
		public JPanel getLeftPanel() {
			return leftPanel;
		}
	//_______________________________________________________________________//
	public void setCurrentButton(MyButton b1) {
		currentButton.setHasVal(true);
		currentButton = b1;
	}
	//_______________________________________________________________________//
	public MyButton getCurrentButton() {
		System.out.println("Current: " + currentButton.getActionCommand());
		return currentButton;
	}
	//_______________________________________________________________________//
	public String getCurrentButtonLabel() {
		return "Current button:  \n\n" + currentButton.getActionCommand();
	}
	//_______________________________________________________________________//
	private void eraserChosen() {
		for(int i = 0; i < GRID_SIZE*3; i++) {
			subContainers.get(i).setEraserChosen(true);
		}
	}
	//_______________________________________________________________________//
	//functions for grid
	//initialize subContainers
	private void initializeGrids() {
		for(int j = 0; j < GRID_SIZE*3; j++)
			for(int k = 0; k < GRID_SIZE*3; k++){
				nineGrid[j][k] = new MyButton(" ", false, j, k);
				nineGrid[j][k].setHasVal(false);
			}
		for(int i = 0; i < 9; i++) {
			MyContainer c = new MyContainer(i, this);
			repaint(c);
			subContainers.add(c);
			container.add(c);
			
		}
		
	}
	//_______________________________________________________________________//
	//get main container of whole grid
	public Container getContainer() {
		return container;
	}
	//_______________________________________________________________________//
	//get sub container of sub grid
	public MyContainer getSubContainer(int i) {
		return subContainers.get(i);
	}
	//_______________________________________________________________________//
	//function to repaint grid every time based on values of button
	public void repaint(MyContainer subContainer) {
		
		   subContainer.revalidate();
		   subContainer.repaint();
		   
	}
	//_______________________________________________________________________//
	public void setCur(MyButton cur) {
		for(int i = 0; i < 9; i++) {
			subContainers.get(i).setCurrentButton(cur);
		}
	}
	//_______________________________________________________________________//
	//map a 9x9 grid to a 3x3 to store in subcontainer
	private int mapCoordinate(int x){
		switch(x){
			case 4:
			case 7:
			 return 1;
			case 5:
			case 8:
			 return 2;
			case 6:
			case 9:
			 return 3;
			default: return x;
		}
	}
	//_______________________________________________________________________//
	//actually set fixed candidates onto grid
	public void setVal(PuzzleData pd, int index){
		//add to nine grid
		addNineGridButton(pd.getRow()-1, pd.getCol()-1, pd.getVal());
		//add to 3x3 grid by mapping
		int row = mapCoordinate(pd.getRow());
		int col = mapCoordinate(pd.getCol());
		
		MyContainer subContainer = getSubContainer(index);
		subContainer.addButtonValue(pd.getVal(), row, col, true);
		
	}
	//_______________________________________________________________________//
	//add button value to nine grid and set has value to true
	public void addNineGridButton(int row, int col, int val) {
		nineGrid[row][col].setActionCommand(Integer.toString(val));
		nineGrid[row][col].setHasVal(true);
		
		
	}
	//toggle on fill 
	public void setOnFill(boolean isOnFill) {
		if(isOnFill) {
			for(MyContainer c: subContainers) {
				c.setOnFill(isOnFill);
			}
		}
			
	}
	//ALGORITHMS BEGIN HERE: tread carefully
	public void performSingleAlgorithm() {
		for(MyContainer sc: subContainers) {
			boolean resolvedSingle = sc.performSingle();
			if(resolvedSingle)
				break;
		}
	}
	public void performHiddenSingleAlgorithm() {
		for(MyContainer c: subContainers) {
			boolean resolved = c.performHiddenSingle();
			if(resolved)
				break;
		}		
	}
	public void performNakedPairsAlgorithm() {
		for(MyContainer sc: subContainers) {
			sc.performNakedPairsContainer();
		}
		MyContainer single = subContainers.get(1);
		 single.performNakedPairsRowandCol();
	}
	
	//_______________________________________________________________________//
	//store current puzzle into a file if the nine grid has a 
	//button with a value in it
	public ArrayList<PuzzleData> getStoredPuzzle() {
		ArrayList<PuzzleData> storedPuzzle = new ArrayList<PuzzleData>();
		//iterate through the nine grid
		for(int row = 0; row < GRID_SIZE*3; row++) 
			for(int col = 0; col < GRID_SIZE*3; col++) 
				if(nineGrid[row][col].hasVal() == true) 
					storedPuzzle.add(new PuzzleData(row+1, col+1, nineGrid[row][col].getVal()));
		
		return storedPuzzle;
	}
	//_______________________________________________________________________//
	
	
}