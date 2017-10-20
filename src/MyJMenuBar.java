import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class MyJMenuBar extends JMenuBar{
	private JMenuBar menuBar;
	private JMenu fileMenu, helpMenu, hintsMenu; 
	private JMenuItem loadMenuItem;
	private ArrayList<PuzzleData> data;
	private boolean loadClicked;
	public MyJMenuBar() {
		loadClicked = false;
		data = new ArrayList<PuzzleData>();
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		hintsMenu = new JMenu("Hints");
		
		loadMenuItem = new JMenuItem("Load File");
		
		fileMenu.add(loadMenuItem);
		loadMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("/Users/sarahkazi/Documents/cs342/project3");
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					MyJMenuBar.this.data = getData(selectedFile);

				}
			}
		});
		
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu); 
		menuBar.add(hintsMenu);
		 
	}
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public ArrayList<PuzzleData> getData(File sf){
		loadClicked = true;
		try {

	        Scanner sc = new Scanner(sf);
	        int row =0, col= 0, val = 0;
	        while (sc.hasNextLine()) {
	        	if(sc.hasNextInt()) {
	            row = sc.nextInt();
	            col = sc.nextInt();
	            val = sc.nextInt();
	        	}
	        		int gridNo = getGridNo(row, col);
	            data.add(new PuzzleData(row, col, val, gridNo));
	           
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException fnf) {
	        fnf.printStackTrace();
	    }
		System.out.println("Data" + data.size());
		return data;
		
	}
	private int getGridNo(int row, int col) {
		int gridNum = 0;
		if(row>=1 && row <=3 && col >=1 && col <=3) 
			gridNum = 1;
		else if(row>=1 && row <=3 && col >= 4 && col <=6) 
			gridNum = 2;
		else if(row>=1 && row <=3 && col >= 7 && col <=9) 
			gridNum = 3;
		else if(row>=4 && row <=6 && col >= 1 && col <=3) 
			gridNum = 4;
		else if(row>=4 && row <=6 && col >= 4 && col <=6) 
			gridNum = 5;
		else if(row>=4 && row <=6 && col >= 7 && col <=9) 
			gridNum = 6;
		else if(row>=7 && row <=9 && col >= 1 && col <=3) 
			gridNum = 7;
		else if(row>=7 && row <=9 && col >= 4 && col <=6) 
			gridNum = 8;
		else if(row>=7 && row <=9 && col >= 7 && col <=9) 
			gridNum = 9;
		
		return gridNum;
	}
	public ArrayList<PuzzleData> getData(){
		if(isLoaded()) {
			System.out.println(data.size());
			return data;
		}
		else
			return null;
	}
	public boolean isLoaded() {
		return loadClicked;
	}
	
}
