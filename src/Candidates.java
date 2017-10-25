import java.util.*;

public class Candidates {

	private ArrayList<Integer> possiblecandidates = new ArrayList<Integer>();
	private ArrayList<Integer> comparison = new ArrayList<Integer>();
	
	public ArrayList<Integer> getcandidates(){
		for(int i = 1; i <10; i++) {
			possiblecandidates.add(i);
		}
		return possiblecandidates;
	}
	public void checkrow() {
		//Gonna loop though the whole row adding numbers to comparison;
	}
	public void checkcol() {
		//Loops through the whole col adding number to comparison;
		
	}
	public void container() {
		//Add to comparison from the three by three container
	}
}
