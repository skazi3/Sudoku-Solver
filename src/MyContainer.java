import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;


public class MyContainer extends Container {
	
	private MyGrid gridPane;
	
	private MyButton[][] numbers;
	private MyButton[][] nineGrid;
	private MyButton currentButton;
	
	private int index;
	private int GRID_SIZE = 3;
	
	private boolean isOnFillMode;
	private boolean eraserChosen;
	private boolean currentButtonChosen;
	private boolean showCandidates;
	
	
	public MyContainer(int i, MyGrid g){
		gridPane = g;
		numbers = new MyButton[3][3];
		index = i;
		eraserChosen = false;
		isOnFillMode = false;
		currentButton = new MyButton(" ", false, -1, -1);
		GridLayout subGrid = new GridLayout(GRID_SIZE, GRID_SIZE, 1, 1);
		setLayout(subGrid);
		showCandidates = false;
		
		for(int row = 0; row < GRID_SIZE; row++) { 
			for(int col = 0; col < GRID_SIZE; col++) {
				numbers[row][col] = new MyButton(" ", false, calculateRow(row), calculateCol(col));

				numbers[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MyButton b = (MyButton)e.getSource();
						if(eraserChosen) 
							eraseValues(b);
						else if(currentButtonChosen) {
							if(isOnFillMode == true) {
								//check if a proper value is being placed in the grid or not
								removeCandidates(b);
								b.printCandidates();
								if(validateUserMove(b) == false) {
									JOptionPane.showMessageDialog(null,
							    		    "wrong move :(\n"+ currentButton.getVal() +" can't be placed there\n",
							    		    "WRONG MOVE!",
							    		    JOptionPane.PLAIN_MESSAGE);
								}
								else
									storeHelperButton(b);
							}
							
						}
						else if(showCandidates) {
							removeCandidates(b);
							b.printCandidates();
						}
						
					}
				});
				add(numbers[row][col]);
				
			}
		}	
		
		
		setVisible(true);
		setSize(200, 200);
		
	}
	public void setOnFill(boolean isOnFill) {
		isOnFillMode = isOnFill;
	}
	public boolean validateUserMove(MyButton b) {
		System.out.println("In validate");
		int curButtonVal = currentButton.getVal();
		if(currentButton.hasVal())

			if(b.getCandidates().contains(curButtonVal) == false) {
				return false;
			}
		
		return true;
	}
	
	public int calculateRow(int i){
		switch(index){
		case 0:
		case 1:
		case 2:
			return i;
		case 3:
		case 4:
		case 5:
			return i+3;
		case 6:
		case 7: 
		case 8:
			return i+6;
			
		default:
			return -1;	
		}
	}
	public int calculateCol(int i){
		switch(index){
		case 0:
		case 3:
		case 6:
			return i;
		case 1:
		case 4:
		case 7:
			return i+3;
		case 2:
		case 5:
		case 8:
			return i+6;
			
		default:
			return -1;	
		}
	}
	//_______________________________________________________________________//
	public void setShowCandidates(boolean show) {
		showCandidates = show;
		if(showCandidates) {
			currentButtonChosen = false;
			eraserChosen = false;
		}
	
	}
	
	public void setNineGrid(MyButton[][] ng) {
		nineGrid = ng;
	}
	
	public void removeCandidates(MyButton b) {
		removeCandidatesSquare(b);
		removeCandidatesRow(b);
		removeCandidateColumn(b);
		
		
	}
	public void removeCandidatesSquare(MyButton b){
		for(int row = 0; row < GRID_SIZE; row++) {
			for(int col = 0; col < GRID_SIZE; col++) {
				if(numbers[row][col].getVal() != b.getVal() && numbers[row][col].getVal() != -1) {
					//makes sure we're removing a number that exists in candidates
					if(b.getCandidates().contains(numbers[row][col].getVal()))
						b.removeCandidate(numbers[row][col].getVal());
				}
			}
		}
	}
	public void removeCandidatesRow(MyButton b){
		for(int i =0; i < 9; i++){
			 if(nineGrid[b.getRow()][i].getVal() != b.getVal() && nineGrid[b.getRow()][i].getVal() != -1){
				 //if candidates still has the value we remove it
				if(b.getCandidates().contains(nineGrid[b.getRow()][i].getVal()))
					b.removeCandidate(nineGrid[b.getRow()][i].getVal());
			 }
		}
		
	}
	public void removeCandidateColumn(MyButton b){
		for(int i = 0; i < 9; i++){
			if(nineGrid[i][b.getCol()].getVal() != b.getVal() && nineGrid[i][b.getCol()].getVal() != -1){
				if(b.getCandidates().contains(nineGrid[i][b.getCol()].getVal()))
					b.removeCandidate(nineGrid[i][b.getCol()].getVal());
			 }
		}
	}
	//_______________________________________________________________________//
	private void storeHelperButton(MyButton b) {
		if(currentButton.hasVal() && b.isFixed() == false) {
			b.setText(currentButton.getText());
			b.setHasVal(true);
						
			nineGrid[b.getRow()][b.getCol()].setText(currentButton.getText());
		}
	}
	//_______________________________________________________________________//
	private void eraseValues(MyButton b) {
		if(b.hasVal() && eraserChosen == true && b.isFixed() == false) {
			b.setText(" ");
			b.setHasVal(false);
		}
		
	}
	//_______________________________________________________________________//
	public void setCurrentButton(MyButton b) {
		currentButton = b;
		currentButton.setHasVal(true);
		currentButtonChosen = true;
		eraserChosen = false;
	}
	//_______________________________________________________________________//
	public void setEraserChosen(boolean isChosen) {
		eraserChosen = isChosen;
		if(eraserChosen)
			currentButtonChosen = false;
	}
	//_______________________________________________________________________//
	public void updateButtons(){
		
		removeAll();
		for(int row = 0; row < GRID_SIZE; row++) { 
			for(int col = 0; col < GRID_SIZE; col++) {
				add(numbers[row][col]);
			}
		}	
	}
	//_______________________________________________________________________//
	public void addButtonValue(int value, int row, int col, boolean isFixed){
		numbers[row-1][col-1].setText(Integer.toString(value));
		numbers[row-1][col-1].setIsFixed(isFixed);
		updateButtons();
	}
	public void performSingle() {
		for(int row = 0; row < GRID_SIZE; row++) { 
			for(int col = 0; col < GRID_SIZE; col++) {
				removeCandidates(numbers[row][col]);
				MyButton temp = numbers[row][col];
				
				if(temp.getCandidates().size() == 1) {
					int singleVal = temp.getCandidates().get(0);
					numbers[row][col].setText(Integer.toString(singleVal));
					nineGrid[temp.getRow()][temp.getCol()].setText(Integer.toString(temp.getCandidates().get(0)));
					updateButtons();
					JOptionPane.showMessageDialog(null,
			    		    "Single algorithm found on button at [" + temp.getRow() + "," + temp.getCol() + "] and"
			    		    		+ "resolved with a value of "+ singleVal + "\n",
			    		    "SINGLE",
			    		    JOptionPane.PLAIN_MESSAGE);
					return;
				}

			}
		}
	}
	

}
