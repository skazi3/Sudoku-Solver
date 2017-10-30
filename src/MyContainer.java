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
								if(validateUserMove(b) == false)
									JOptionPane.showMessageDialog(null,
							    		    "wrong move :(\n"+ currentButton.getVal() +" can't be placed there\n",
							    		    "WRONG MOVE!",
							    		    JOptionPane.PLAIN_MESSAGE);
								else
									storeHelperButton(b);
							}
							else 
								storeHelperButton(b);
						}
						else if(showCandidates) {
							removeCandidates(b);
							gridPane.displayCandidates(b);
						}
					}
				});
				add(numbers[row][col]);	
			}
		}	
		
		
		setVisible(true);
		setSize(200, 200);
		
	}

	//_______________________________________________________________________//

	public void setOnFill(boolean isOnFill) {
		isOnFillMode = isOnFill;
	}
	public boolean validateUserMove(MyButton b) {
		int curButtonVal = currentButton.getVal();
		if(currentButton.hasVal())

			if(b.getCandidates().contains(curButtonVal) == false) {
				return false;
			}
		
		return true;
	}
	
	
	public int calculateRow(int i){
		switch(index){
		case 0:	case 1: case 2:
			return i;
		case 3:	case 4:	case 5:
			return i+3;
		case 6:	case 7:	case 8:
			return i+6;
			
		default:
			return -1;	
		}
	}
	//_______________________________________________________________________//
	public int calculateCol(int i){
		switch(index){
		case 0:	case 3:	case 6:
			return i;
		case 1:	case 4:	case 7:
			return i+3;
		case 2:	case 5:	case 8:
			return i+6;
			
		default:
			return -1;	
		}
	}
	
	public int calculateRowIn3(int i){
		switch(i){
		case 0:	case 1: case 2:
			return i;
		case 3:	case 4:	case 5:
			return i-3;
		case 6:	case 7:	case 8:
			return i-6;
			
		default:
			return -1;	
		}
	}
	//_______________________________________________________________________//
	public int calculateColIn3(int i){
		switch(i){
		case 0:	case 1: case 2:
			return i;
		case 3:	case 4:	case 5:
			return i-3;
		case 6:	case 7:	case 8:
			return i-6;
			
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
	//_______________________________________________________________________//
	public void setNineGrid(MyButton[][] ng) {
		nineGrid = ng;
	}
	//_______________________________________________________________________//
	public void removeCandidates(MyButton b) {
		removeCandidatesSquare(b);
		removeCandidatesRow(b);
		removeCandidateColumn(b);
		
	}
	//_______________________________________________________________________//
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
	//_______________________________________________________________________//
	public void removeCandidatesRow(MyButton b){
		for(int i =0; i < GRID_SIZE*3; i++){
			 if(nineGrid[b.getRow()][i].getVal() != b.getVal() && nineGrid[b.getRow()][i].getVal() != -1){
				 //if candidates still has the value we remove it
				if(b.getCandidates().contains(nineGrid[b.getRow()][i].getVal()))
					b.removeCandidate(nineGrid[b.getRow()][i].getVal());
			 }
		}
		
	}
	//_______________________________________________________________________//
	public void removeCandidateColumn(MyButton b){
		for(int i = 0; i < GRID_SIZE*3; i++){
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
			gridPane.addNineGridButton(b.getRow(), b.getCol(), currentButton.getVal());			
			nineGrid[b.getRow()][b.getCol()].setText(currentButton.getText());
		}
	}
	//_______________________________________________________________________//
	private void eraseValues(MyButton b) {
		if(b.hasVal() && eraserChosen == true && b.isFixed() == false) {
			nineGrid[b.getRow()][b.getCol()].setActionCommand(" ");
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
	public boolean performSingle() {
		boolean isResolved = false;
		for(int row = 0; row < GRID_SIZE; row++) { 
			for(int col = 0; col < GRID_SIZE; col++) {
				removeCandidates(numbers[row][col]);
				MyButton temp = numbers[row][col];
				if(isResolved) {
					break;
				}
				
				if(temp.getCandidates().size() == 1) {
					int singleVal = temp.getCandidates().get(0);
					numbers[row][col].setText(Integer.toString(singleVal));
					gridPane.addNineGridButton(temp.getRow(), temp.getCol(), singleVal);
					nineGrid[temp.getRow()][temp.getCol()].setText(Integer.toString(temp.getCandidates().get(0)));
					updateButtons();
					JOptionPane.showMessageDialog(null,
			    		    "Single algorithm found on button at [" + (temp.getRow()+1) + "," + (temp.getCol()+1) + "] and "
			    		    		+ "resolved with a value of "+ singleVal + "\n",
			    		    "SINGLE",
			    		    JOptionPane.PLAIN_MESSAGE);
					isResolved = true;
				}

			}
		}
		return isResolved;
	}
	
	
	public boolean removeHelper(int row, int col, int val){
		int r, c;
		r = calculateRowIn3(row);
		c = calculateColIn3(col);
		numbers[r][c].removeCandidate(val);
		
		if(numbers[r][c].getCandidates().size() == 1){
			
			int v = numbers[r][c].getCandidates().get(0);
			numbers[r][c].setText(Integer.toString(v));
			gridPane.addNineGridButton(numbers[r][c].getRow(), numbers[r][c].getCol(), v);
			nineGrid[numbers[r][c].getRow()][numbers[r][c].getCol()].setText(Integer.toString(numbers[r][c].getCandidates().get(0)));
			updateButtons();
			
			JOptionPane.showMessageDialog(null,
	    		    "Naked Pairs algorithm found on button at [" + (numbers[r][c].getRow()+1) + "," + (numbers[r][c].getCol()+1) + "] and "
	    		    		+ "resolved with a value of "+ v  + "\n",
	    		    "SINGLE",
	    		    JOptionPane.PLAIN_MESSAGE);
			return true;
		}
		return false;
	}
/*______________________________________________________*/	

	public boolean performHiddenSingle() {
		boolean isResolved = false;
		
		int row = 0;
		int col = 0;
		
		for( row = 0; row < GRID_SIZE; row++) { 
			for( col = 0; col < GRID_SIZE; col++) {
				removeCandidates(numbers[row][col]);
			}
		}	
		
		for( row = 0; row < GRID_SIZE; row++) { 
			
			for( col = 0; col < GRID_SIZE; col++) {
				if(numbers[row][col].getCandidates().size() == 1){
					isResolved = true;
					break;
				}
			}
			if(isResolved){
				break;
			}
		}
		
		if(isResolved){
			
			int val = numbers[row][col].getCandidates().get(0);
			numbers[row][col].setText(Integer.toString(val));
			gridPane.addNineGridButton(numbers[row][col].getRow(), numbers[row][col].getCol(), val);
			nineGrid[numbers[row][col].getRow()][numbers[row][col].getCol()].setText(Integer.toString(numbers[row][col].getCandidates().get(0)));
			updateButtons();
			
			JOptionPane.showMessageDialog(null,
	    		    "Hidden Single algorithm found on button at [" + (numbers[row][col].getRow()+1) + "," + (numbers[row][col].getCol()+1) + "] and "
	    		    		+ "resolved with a value of "+ val  + "\n",
	    		    "SINGLE",
	    		    JOptionPane.PLAIN_MESSAGE);
			
		}

		return isResolved;
	
	}
/*______________________________________________________*/	
	public boolean performNakedPairsContainer() {
		boolean resolved = false;
		if(pairsContainer() == true) 
			resolved = true;
		return resolved;
		
	}
	/*______________________________________________________*/	
	public boolean pairsContainer() {
		ArrayList<MyButton> nakedPairList = new ArrayList<MyButton>();
		boolean eliminated = false;
		int i = 0;
		for(int row = 0; row <GRID_SIZE; row++) {
			for(int col = 0; col <GRID_SIZE; col++) {
				removeCandidates(numbers[row][col]);
				if(numbers[row][col].getCandidates().size()==2) {
					i++;
					nakedPairList.add(numbers[row][col]);	
				
				}
			}
		}
		if( i > 1) {
			eliminated = findMatching(nakedPairList,1);
		}
		return eliminated;
	}
	
	/*______________________________________________________*/		
	public boolean findMatching(ArrayList<MyButton> pairs,int determine){
		boolean eliminated = false;
		for(int i = 0; i < pairs.size(); i++) {
			for(int j = i+1; j < pairs.size(); j++) {
				if(pairs.get(i).getCandidates().equals(pairs.get(j).getCandidates())) {
					if(determine == 1) {
					removeFromContainer(pairs.get(i));
					eliminated = true;
					}
					if(determine == 2) {
						eliminated = removePairsFromRow(pairs.get(i));
					}
					if(determine == 3) {
						eliminated = removePairsFromCol(pairs.get(i));
					}
				}
			}
		}
		return eliminated;
	}

/*______________________________________________________*/		
	public void removeFromContainer(MyButton found) {
		for(int row = 0; row < GRID_SIZE; row++) {
			for(int col = 0; col < GRID_SIZE; col++) {
				for(int i = 0; i < 2; i++) {
					if(numbers[row][col].getCandidates().contains(found.getCandidates().get(i)) && numbers[row][col].getCandidates().size() != 2) {
						numbers[row][col].removeCandidate(found.getCandidates().get(i));
					}
				}
			}
		}
	}
/*______________________________________________________*/	
	public boolean performNakedPairsRowandCol() {
		boolean resolved = false;
		removeCandidateRowandCol();
		
		if(pairsRows()== true)
			resolved = true;
		if(pairsCols() == true) 
			resolved = true;
		
		return resolved;
		
	}
	/*______________________________________________________*/	
	public void removeCandidateRowandCol() {
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col <9; col++) {
				removeCandidatesRow(nineGrid[row][col]);
			}
		}
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col <9; col++) {
				removeCandidateColumn(nineGrid[row][col]);
			}
		}
}
/*______________________________________________________*/		
	public boolean pairsRows() {
		boolean resolved = false;
		ArrayList<MyButton> pairs = new ArrayList<MyButton>();
		
		for(int row = 0; row < 9; row++) {
			int i =0;
			for(int col = 0; col <9; col++) {
				if(nineGrid[row][col].getCandidates().size() == 2) {
					i++;
					pairs.add(nineGrid[row][col]);
				}
			}
			if( i > 1) {
				resolved = findMatching(pairs,2);
				pairs = new ArrayList<MyButton>();
			}
		}
		return resolved;
	}
	/*_________________________________________________________________*/
	public boolean pairsCols(){
		boolean eliminated = false;
		ArrayList<MyButton> pairs = new ArrayList<MyButton>();
		
		for(int col = 0; col < 9; col++) {
			int i =0;
			for(int row = 0; row <9; row++) {
				if(nineGrid[row][col].getCandidates().size() == 2) {
					i++;
					pairs.add(nineGrid[row][col]);
				}
			}
			if( i > 1) {
				eliminated= findMatching(pairs,3);
				pairs = new ArrayList<MyButton>();
			}
		}
		return eliminated;
		
	}
/*______________________________________________________*/
	public boolean removePairsFromRow(MyButton found) {
		boolean resolved = false;
		int row = found.getRow();
			for(int col = 0; col < GRID_SIZE*3; col++) {
				for(int i = 0; i < 2; i++) {
					if(nineGrid[row][col].getCandidates().contains(found.getCandidates().get(i)) && nineGrid[row][col].getCandidates().size() > 2) {
						
						nineGrid[row][col].removeCandidate(found.getCandidates().get(i));
						resolved = gridPane.removeCandidate(found.getCandidates().get(i), row, col);
						if(resolved)
							return true;
					}
				}
				if(nineGrid[row][col].getCandidates().size() == 2) {
					if(nineGrid[row][col].getCandidates().contains(found.getCandidates().get(0)) 
							&& !(nineGrid[row][col].getCandidates().contains(found.getCandidates().get(1)))) {
						nineGrid[row][col].removeCandidate(found.getCandidates().get(0));
						resolved = gridPane.removeCandidate(found.getCandidates().get(0), row, col);
						if(resolved)
							return true;
					}
					if(nineGrid[row][col].getCandidates().contains(found.getCandidates().get(1)) 
							&& !(nineGrid[row][col].getCandidates().contains(found.getCandidates().get(0)))) {
						nineGrid[row][col].removeCandidate(found.getCandidates().get(1));
						resolved = gridPane.removeCandidate(found.getCandidates().get(1), row, col);
						if(resolved)
							return true;
					}
				}
				
				
			}
			return false;
	}

/*______________________________________________________*/

	public boolean removePairsFromCol(MyButton found) {
		boolean resolved = false;
		int col = found.getCol();
			for(int row = 0; row < GRID_SIZE*3; row++) {
				for(int i = 0; i < 2; i++) {
					if(nineGrid[row][col].getCandidates().contains(found.getCandidates().get(i)) && nineGrid[row][col].getCandidates().size() > 2) {
						
						nineGrid[row][col].removeCandidate(found.getCandidates().get(i));
						resolved = gridPane.removeCandidate(found.getCandidates().get(i), row, col);
						if(resolved)
							return true;
					}
				}
				if(nineGrid[row][col].getCandidates().size() == 2) {
					if(nineGrid[row][col].getCandidates().contains(found.getCandidates().get(0)) 
							&& !(nineGrid[row][col].getCandidates().contains(found.getCandidates().get(1)))) {
						nineGrid[row][col].removeCandidate(found.getCandidates().get(0));
						resolved = gridPane.removeCandidate(found.getCandidates().get(0), row, col);
						if(resolved)
							return true;
					}
					if(nineGrid[row][col].getCandidates().contains(found.getCandidates().get(1)) 
							&& !(nineGrid[row][col].getCandidates().contains(found.getCandidates().get(0)))) {
						nineGrid[row][col].removeCandidate(found.getCandidates().get(1));
						resolved = gridPane.removeCandidate(found.getCandidates().get(1), row, col);
						if(resolved)
							return true;
					}
				}
			}
			
			return false;
	}
	

}
