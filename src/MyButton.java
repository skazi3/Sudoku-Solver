import javax.swing.*;

public class MyButton extends JButton{
	private boolean hasValue;
	public MyButton(String text) {
		super(text);
		hasValue = false;
	}
	 
	public boolean hasVal() {
		return hasValue;
	}
	public void setHasVal(boolean y) {
		hasValue = y;
	}

}
