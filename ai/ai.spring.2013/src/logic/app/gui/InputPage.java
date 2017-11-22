package logic.app.gui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InputPage extends JPanel{
	InfoPage infoPage = null;
	PremisePage premiseBox = new PremisePage();
	ConclusionPage conclusionBox = new ConclusionPage();
	
	
	public InputPage(InfoPage infoPage) {
		this.infoPage = infoPage;
		
		Box horiBox = Box.createHorizontalBox();		
		horiBox.add(this.premiseBox);
		horiBox.add(this.conclusionBox);
		
		
		this.setLayout(new BorderLayout());
		this.add(horiBox, BorderLayout.CENTER);
	}
	
	public String getPremiseText(){
		return this.premiseBox.getText();
	}
	
	public String getConclusionText(){
		return this.conclusionBox.getText();
	}
	
	public void setPremiseText(String text){
		this.premiseBox.setText(text);
	}
	public void setConclusionText(String text){
		this.conclusionBox.setText(text);
	}

}
