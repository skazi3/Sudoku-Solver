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
	public boolean loadClicked = false;
	public MyJMenuBar() {
		data = new ArrayList<PuzzleData>();
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		hintsMenu = new JMenu("Hints");
		
		loadMenuItem = new JMenuItem("Load File");
		
		fileMenu.add(loadMenuItem);
		loadMenuItem.addActionListener(new loadActionListener());
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu); 
		menuBar.add(hintsMenu);
		
	}
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	private class loadActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser("/Users/sarahkazi/Documents/cs342/project3");
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fc.getSelectedFile();
				loadClicked = true;
			    try {

			        Scanner sc = new Scanner(selectedFile);

			        while (sc.hasNextLine()) {
			            int row = sc.nextInt();
			            int col = sc.nextInt();
			            int val = sc.nextInt();
			        
			            data.add(new PuzzleData(row, col, val));
			           
			        }
			        sc.close();
			    } 
			    catch (FileNotFoundException fnf) {
			        fnf.printStackTrace();
			    }
			}
		}
		
	}

	public ArrayList<PuzzleData> getData(){
		if(loadClicked)
			return data;
		else
			return null;
	}
	
}
