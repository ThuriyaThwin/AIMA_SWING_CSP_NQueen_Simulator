package logic.app.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;

public class MainWindow extends JFrame{
	WorkingPage workingPage = null;
	InfoPage infoPage = null;
	
	
	public MainWindow(String title) {
		super(title);
		
		//init pages
		this.infoPage = new InfoPage();
		this.workingPage = new WorkingPage(this.infoPage);
		
		JSplitPane plPage = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.workingPage, this.infoPage);
		plPage.setResizeWeight(0.8);
		plPage.setOneTouchExpandable(true);
		plPage.setContinuousLayout(true);
        
        //TabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(plPage, "Propositional Logic");
        tabbedPane.add(new JPanel(), "Predicate Logic");
        
        Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(tabbedPane, BorderLayout.CENTER);
		
		
		//
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		this.setVisible(true);
		
		
	}

}
