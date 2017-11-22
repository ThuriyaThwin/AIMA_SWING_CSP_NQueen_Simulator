package logic.app.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WorkingPage extends JPanel{
	InfoPage infoPage = null;
	InputPage inputPage = null;
	CommandPage commandPage = null;
	
	public WorkingPage(InfoPage infoPage) {
		this.infoPage = infoPage;
		
		this.inputPage = new InputPage(this.infoPage);
		this.commandPage = new CommandPage(this.infoPage, this.inputPage);
		
		Box vertBox = Box.createVerticalBox();
		
		vertBox.add(this.inputPage);
		vertBox.add(this.commandPage);
		
		this.setLayout(new BorderLayout());
		this.add(vertBox, BorderLayout.CENTER);
		
		this.setMinimumSize(new Dimension(0, 0));
	}

}
