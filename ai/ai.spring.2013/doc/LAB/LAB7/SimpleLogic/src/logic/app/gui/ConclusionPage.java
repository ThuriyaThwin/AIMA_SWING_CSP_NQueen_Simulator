package logic.app.gui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConclusionPage extends JPanel{
	private JTextArea conclusionBox = new JTextArea(50, 200);
	private JLabel lbText = new JLabel("Type expected conclusions in the below area:", JLabel.LEFT);
	
	public ConclusionPage() {
		this.lbText.setForeground(Color.RED);
		this.conclusionBox.setTabSize(2);
		
		Box vertBox = Box.createVerticalBox();
		vertBox.add(this.lbText);
		vertBox.add(new JScrollPane(this.conclusionBox));
		
		this.setLayout(new BorderLayout());
		this.add(vertBox, BorderLayout.CENTER);
		this.setMinimumSize(new Dimension(0, 0));
	}
	
	public String getText(){
		return this.conclusionBox.getText();
	}
	public void setText(String text){
		this.conclusionBox.setText(text);
	}
	public void appendText(String text){
		this.conclusionBox.append(text);
	}

}
