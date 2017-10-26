import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class MyContainer extends Container {
	
	private int GRID_SIZE = 3;
	private MyButton[][] numbers;
	private MyButton currentButton;
	
	private int index;
	private boolean eraserChosen;
	private boolean currentButtonChosen;
	private boolean showCandidates;
	private MyButton[][] nineGrid;
	
	public MyContainer(int i){
		
		numbers = new MyButton[3][3];
		index = i;
		eraserChosen = false;
		currentButton = new MyButton(" ", false);
		GridLayout subGrid = new GridLayout(GRID_SIZE, GRID_SIZE, 1, 1);
		setLayout(subGrid);
		showCandidates = false;
		
		for(int row = 0; row < GRID_SIZE; row++) { 
			for(int col = 0; col < GRID_SIZE; col++) {
				numbers[row][col] = new MyButton(" ", false);

				numbers[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MyButton b = (MyButton)e.getSource();
						if(eraserChosen) 
							eraseValues(b);
						
						else if(currentButtonChosen)
							storeHelperButton(b);
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
	/*public void rowRemoval(MyButton b){
	 * for(int i =0; i < 9; i++){
	 * if(nineGrid[row][i].hasVal()==true){
	 * 		b.removeCandidate(nineGrid[row][i].getVal());
	 * }
	 * }
	 * 
	 * public void colRemoval(MyButton b){
	 * for(int i = 0; i < 9; i++){
	 * if(nineGrid[i][col].hasVal()==true){
	 * 		b.removeCandidate(nineGrid[i][col].getVal());
	 * }
	 * }
	 * }
	 */
	public void removeCandidates(MyButton b) {
		for(int row = 0; row < GRID_SIZE; row++) {
			for(int col = 0; col < GRID_SIZE; col++) {
				if(numbers[row][col].getVal() != b.getVal() && numbers[row][col].getVal() != -1) {
					b.removeCandidate(numbers[row][col].getVal());
				}
			}
		}
	}
	//_______________________________________________________________________//
	private void storeHelperButton(MyButton b) {
		if(currentButton.hasVal() && b.isFixed() == false) {
			b.setText(currentButton.getText());
			b.setHasVal(true);
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
	

}
