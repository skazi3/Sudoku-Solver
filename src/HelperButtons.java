import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class HelperButtons {
	private MyButton[] helperButtons;
	private GridLayout helperGrid;
	private JPanel panel;
	private MyButton currentButton;
	 
	public HelperButtons(){
		Image eraser = new ImageIcon( "eraser.png" ).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon e = new ImageIcon(eraser);
		helperButtons = new MyButton[10];
		helperGrid = new GridLayout(10, 1, 0, 0);
		
		panel = new JPanel(helperGrid, false);

		
		for(int i = 0; i < 9; i++){
			helperButtons[i] = new MyButton(Integer.toString(i+1));
			helperButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					chooseCurrentButton((MyButton)e.getSource());
				}
			});
			panel.add(helperButtons[i]);
		}
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
	private void chooseCurrentButton(MyButton b1) {
		currentButton.setHasVal(true);
		currentButton = b1;
		
	}
	public int getCurrentButton() {
		return Integer.parseInt(currentButton.getActionCommand());
	}
	
	private void eraserChosen() {
		
	}
	

	

}
