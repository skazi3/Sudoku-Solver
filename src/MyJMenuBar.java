import javax.swing.*;

public class MyJMenuBar extends JMenuBar{
	private JMenuBar menuBar;
	private JMenu fileMenu, helpMenu, hintsMenu; 
	public MyJMenuBar() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		hintsMenu = new JMenu("Hints");
		menuBar.add(fileMenu);
		menuBar.add(helpMenu); 
		menuBar.add(hintsMenu);
		
	}
	public JMenuBar getMenuBar() {
		return menuBar;
	}
}
