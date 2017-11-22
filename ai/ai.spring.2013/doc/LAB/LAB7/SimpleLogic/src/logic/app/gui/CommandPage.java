package logic.app.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


import java.util.Set;

import logic.app.proposition.PLCodeProcessor;


import aima.core.logic.propositional.algorithms.KnowledgeBase;
import aima.core.logic.propositional.algorithms.PLResolution;
import aima.core.logic.propositional.parsing.PEParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.parsing.ast.Symbol;
import aima.core.logic.propositional.visitors.CNFClauseGatherer;
import aima.core.logic.propositional.visitors.CNFTransformer;



public class CommandPage extends JPanel implements ActionListener{
	InfoPage infoPage = null;
	InputPage inputPage = null;
	
	JButton toCNF = new JButton("Convert to CNF");
	JButton prove = new JButton("Prove Conclusions from Premises");
	JButton load = new JButton("Load Default Problem");
	JButton clear = new JButton("Clear Output");
	
	public CommandPage(InfoPage infoPage, InputPage inputPage) {
		this.infoPage = infoPage;
		this.inputPage = inputPage;
		
		Box horiBox = Box.createHorizontalBox();
		horiBox.add(this.clear);
		horiBox.add(this.load);
		horiBox.add(this.toCNF);
		horiBox.add(this.prove);
		
		this.setLayout(new BorderLayout());
		this.add(horiBox, BorderLayout.CENTER);
		
		this.clear.addActionListener(this);
		this.load.addActionListener(this);
		this.toCNF.addActionListener(this);
		this.prove.addActionListener(this);
	}
	

	public void actionPerformed(ActionEvent event) {		
		if(event.getSource() == this.toCNF){
			String premises = this.inputPage.getPremiseText();
			String conclusions = this.inputPage.getConclusionText();
			
			PLCodeProcessor processor = new PLCodeProcessor(premises, conclusions);
			
			this.infoPage.setInfoText(processor.getPremiseClauseText());
		}
		if(event.getSource() == this.prove){
			String premises = this.inputPage.getPremiseText();
			String conclusions = this.inputPage.getConclusionText();
			
			PLCodeProcessor processor = new PLCodeProcessor(premises, conclusions);
			
			this.infoPage.setInfoText(processor.getProofResults());
		}
		if(event.getSource() == this.load){
			String premises = "" +
					"(N OR O) => (C AND D);\n" +
					"(D OR K) => (P OR NOT C);\n" +
					"(P OR G) => NOT (N AND D);\n";
			
			String conclusions = "" +
					"NOT N;\n" +
					"D OR K;";
					
			this.inputPage.setPremiseText(premises);
			this.inputPage.setConclusionText(conclusions);
					
		}
		if(event.getSource() == this.clear){
			this.infoPage.setInfoText("");
		}
		
	}

}
