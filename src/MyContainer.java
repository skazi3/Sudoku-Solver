import java.awt.*;


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
				add(numbers[row][col]);
			}
		}	
		
		setVisible(true);
		setSize(100, 100);
		
	}
	
	public void updateButtons(MyButton[][] buttons){
		
		removeAll();
		for(int row = 0; row < GRID_SIZE; row++) { 
			for(int col = 0; col < GRID_SIZE; col++) {
				numbers[row][col] = buttons[row][col];
				add(numbers[row][col]);
			}
		}	
	}
	
	
}
