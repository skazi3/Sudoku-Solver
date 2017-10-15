import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MyGrid{
	private GridLayout sudukoGrid;
	private JButton[][] numbers; 
	Container c;
	private int GRID_SIZE = 3;
	
	MyGrid(){
		c = new Container();
		sudukoGrid = new GridLayout(GRID_SIZE,GRID_SIZE,0, 0);
		numbers = new JButton[GRID_SIZE][GRID_SIZE];
		c.setLayout(sudukoGrid);
		for(int row = 0; row < GRID_SIZE; row++) 
			for(int col = 0; col < GRID_SIZE; col++) {
				numbers[row][col] = new JButton();
				numbers[row][col].setText(" ");
				c.add(numbers[row][col]);
	
			}
	}
	public Container getContainer() {
		return c;
	}
	

}
