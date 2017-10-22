import java.awt.*;
import java.awt.event.*;


public class MyContainer extends Container {
	
	private int GRID_SIZE = 3;
	private MyButton[][] numbers;
	
	public MyContainer(){
		numbers = new MyButton[3][3];
		
		GridLayout subGrid = new GridLayout(GRID_SIZE, GRID_SIZE, 0, 0);
		setLayout(subGrid);
		
		for(int row = 0; row < GRID_SIZE; row++) { 
			for(int col = 0; col < GRID_SIZE; col++) {
				numbers[row][col] = new MyButton(" ");
				numbers[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//check if there is a helper button selected when grid
						//button is clicked then place it there
					}
				});
				add(numbers[row][col]);
			}
		}	
		
		setVisible(true);
		setSize(200, 200);
		
	}
	
	public void updateButtons(){
		
		removeAll();
		for(int row = 0; row < GRID_SIZE; row++) { 
			for(int col = 0; col < GRID_SIZE; col++) {
				add(numbers[row][col]);
			}
		}	
	}
	
	public void addButtonValue(int value, int row, int col){
		numbers[row-1][col-1].setText(Integer.toString(value));
		updateButtons();
	}
	
}
