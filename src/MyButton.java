//MyButton extends JButton and has an additional
//hasVal condition that checks if there is a value
//at given button in array or not

import javax.swing.*;
import java.util.*;

public class MyButton extends JButton{
	private boolean hasValue;
	private boolean isFixedCandidate;
	private ArrayList<Integer> candidates = new ArrayList<Integer>();
	private int row;
	private int col;
	
	public MyButton(String text, boolean isFixed, int r, int c) {
		super(text);
		hasValue = false;
		isFixedCandidate = isFixed;
		for(int i = 1; i < 10; i++) {
			candidates.add(i);
		}
		row = r;
		col = c;
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
		if(getActionCommand() == " ")
			return -1;
		return Integer.parseInt(getActionCommand());
	}
	
	public boolean isFixed() {
		return isFixedCandidate;
	}
	public void setIsFixed(boolean isFixed) {
		isFixedCandidate = isFixed;
	}
	
	public void setCandidates(ArrayList<Integer> possible) {
		candidates = possible;
	}
	public void printCandidates() {
		System.out.println("Candidates: \n");
		for(int i : candidates) {
			System.out.println(i);
		}
		
	}
	
	public void removeCandidate(int val) {
		if (val == -1)
			return;
		int i = candidates.indexOf(val);
		if(i!= -1)
		 candidates.remove(i);
	}
	public void resetCandidateList(){
		candidates.removeAll(candidates);
		for(int i = 1; i < 10; i++) {
			candidates.add(i);
		}
	}
	
	public ArrayList<Integer> getCandidates(){
		return candidates;
	}
	
	public void setRow(int r) {
		row = r;
	}
	public void setCol(int c) {
		col = c;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}

}
