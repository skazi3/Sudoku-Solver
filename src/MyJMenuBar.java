import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class MyJMenuBar extends JMenuBar{
	private JMenuBar menuBar;
	private JMenu fileMenu, helpMenu, hintsMenu; 
	private JMenuItem loadMenuItem;
	public MyJMenuBar() {
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
			JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fc.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
			}
		}
		
	}
	
}
