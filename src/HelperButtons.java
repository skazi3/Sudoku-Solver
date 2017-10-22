import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class HelperButtons {
	private MyButton[] helperButtons;
	private GridLayout helperGrid;
	private JPanel panel;
	private MyButton currentButton;
	 
	public HelperButtons(){
		
		
		helperButtons = new MyButton[10];
		helperGrid = new GridLayout(10, 1, 0, 0);
		currentButton = new MyButton(" ");
		
		panel = new JPanel(helperGrid, false);
		for(int i = 0; i < 9; i++){
			helperButtons[i] = new MyButton(Integer.toString(i+1));
			helperButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setCurrentButton((MyButton)e.getSource());
				}
			});
			panel.add(helperButtons[i]);
		}
		
		Image eraser = new ImageIcon( "eraser.png" ).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon e = new ImageIcon(eraser);
		
		helperButtons[9] = new MyButton(e);
		helperButtons[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eraserChosen();
			}
		});
		panel.add(helperButtons[9]);
		panel.setBackground(Color.LIGHT_GRAY);
	}
	public MyButton getButton(int i) {
		return helperButtons[i];
	}
	public JPanel getPanel() {
		return panel;
	}
	private void setCurrentButton(MyButton b1) {
		currentButton.setHasVal(true);
		currentButton = b1;
		
		
	}
	public MyButton getCurrentButton() {
		System.out.println("Current: " + currentButton.getActionCommand());
		return currentButton;
	}
	
	private void eraserChosen() {
		
	}
	

	

}
