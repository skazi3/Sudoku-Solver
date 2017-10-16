import java.awt.*;
import javax.swing.*;


public class HelperButtons {
	private JButton[] helperButtons;
	private GridLayout helperGrid;
	private JPanel panel;
	
	public HelperButtons(){
		Image eraser = new ImageIcon( "eraser.png" ).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon e = new ImageIcon(eraser);
		helperButtons = new JButton[10];
		helperGrid = new GridLayout(10, 1, 0, 0);
		
		panel = new JPanel(helperGrid, false);

		
		for(int i = 0; i < 9; i++){
			helperButtons[i] = new JButton(Integer.toString(i+1));
			
			panel.add(helperButtons[i]);
		}
		helperButtons[9] = new JButton(e);
		panel.add(helperButtons[9]);
	}
	public JButton getButton(int i) {
		return helperButtons[i];
	}
	public JPanel getPanel() {
		return panel;
	}
	

}
