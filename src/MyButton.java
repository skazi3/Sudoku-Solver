//MyButton extends JButton and has an additional
//hasVal condition that checks if there is a value
//at given button in array or not

import javax.swing.*;

public class MyButton extends JButton{
	private boolean hasValue;
	public MyButton(String text) {
		super(text);
		hasValue = false;
	}
	public MyButton(ImageIcon e) {
		super(e);
		hasValue = false;
	}
	 
	public boolean hasVal() {
		return hasValue;
	}
	public void setHasVal(boolean y) {
		hasValue = y;
	}
	public int getVal() {
		return Integer.parseInt(getActionCommand());
	}

}
