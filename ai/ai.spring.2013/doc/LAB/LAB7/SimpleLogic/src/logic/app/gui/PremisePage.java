package logic.app.gui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PremisePage extends JPanel{
	private JTextArea premiseBox = new JTextArea(50, 200);
	private JLabel lbText = new JLabel("Type premises in the below area:", JLabel.LEFT );
	
	public PremisePage() {
		this.lbText.setForeground(Color.RED);
		this.premiseBox.setTabSize(2);
		
		Box vertBox = Box.createVerticalBox();
		vertBox.add(this.lbText);
		vertBox.add(new JScrollPane(this.premiseBox));
		
		this.setLayout(new BorderLayout());
		this.add(vertBox, BorderLayout.CENTER);
		this.setMinimumSize(new Dimension(0, 0));
	}
	
	public String getText(){
		return this.premiseBox.getText();
	}
	public void setText(String text){
		this.premiseBox.setText(text);
	}
	public void appendText(String text){
		this.premiseBox.append(text);
	}

}
