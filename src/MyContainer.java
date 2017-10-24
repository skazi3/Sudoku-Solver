import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class MyContainer extends Container {
	
	private int GRID_SIZE = 3;
	private MyButton[][] numbers;
	private MyButton currentButton;
	private ArrayList<Integer> oneThruNine = new ArrayList<Integer>();
	
	private boolean eraserChosen;
	private boolean currentButtonChosen;
	
	public MyContainer(){
		for(int i = 1; i <10; i++) {
			oneThruNine.add(i);
		}
		numbers = new MyButton[3][3];
		eraserChosen = false;
		currentButton = new MyButton(" ", false);
		GridLayout subGrid = new GridLayout(GRID_SIZE, GRID_SIZE, 1, 1);
		setLayout(subGrid);
		
		for(int row = 0; row < GRID_SIZE; row++) { 
			for(int col = 0; col < GRID_SIZE; col++) {
				numbers[row][col] = new MyButton(" ", false);
				numbers[row][col].setCandidates(oneThruNine);
				numbers[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(eraserChosen) 
							eraseValues((MyButton)e.getSource());
						
						else
							storeHelperButton((MyButton)e.getSource());
					}
				});
				add(numbers[row][col]);
				
			}
		}	
		
		setVisible(true);
		setSize(200, 200);
		
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
		if(isChosen == true)
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
