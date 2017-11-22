package logic.app.gui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InfoPage extends JPanel{
	private JTextArea infoBox = new JTextArea(10, 200);
	private JLabel lbText = new JLabel("Information Area:", JLabel.LEFT);
	
	public InfoPage() {
		this.lbText.setForeground(Color.GREEN);
		this.infoBox.setTabSize(3);
		
		Box vertBox = Box.createVerticalBox();
		vertBox.add(this.lbText);
		vertBox.add(new JScrollPane(this.infoBox));
		
		this.setLayout(new BorderLayout());
		this.add(vertBox, BorderLayout.CENTER);
		this.setMinimumSize(new Dimension(0, 0));
	}
	
	public String getInfoText(){
		return this.infoBox.getText();
	}
	public void setInfoText(String text){
		this.infoBox.setText(text);
	}
	public void appendInfoText(String text){
		this.infoBox.append(text);
	}

}
