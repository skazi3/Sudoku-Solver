import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MyGrid{
	private GridLayout sudukoGrid;
	private ArrayList<JButton> buttons;
	private Container container;
	private int N = 9;
	
	MyGrid(Container c){
		sudukoGrid = new GridLayout(N,N,1,1);
		container = c;
		container.setLayout(sudukoGrid);
		buttons = getButtons();
	}
	private ArrayList<JButton> getButtons() {
		ArrayList<JButton> buttons = new ArrayList<JButton>(N);
		for(int i = 0; i < N; i++ ) {
	    	  	buttons.add(new JButton(Integer.toString(i+1)));
	
	    	  	//buttons.get(i).addActionListener(this);
	    	  	container.add(buttons.get(i));
		}
		return buttons;
		
	}

}
