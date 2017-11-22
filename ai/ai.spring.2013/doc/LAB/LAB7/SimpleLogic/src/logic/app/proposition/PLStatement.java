package logic.app.proposition;

import java.util.Iterator;
import java.util.Set;

import aima.core.logic.propositional.parsing.PEParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.visitors.CNFClauseGatherer;
import aima.core.logic.propositional.visitors.CNFTransformer;

public class PLStatement {
	private String statementText = "";
	
	public PLStatement(String statementText) {
		this.statementText = statementText;
	}
	
	public String getStatementText(){
		return this.statementText;
	}
	public String getClauseText(){
		PEParser parser = new PEParser();
		CNFClauseGatherer gatherer = new CNFClauseGatherer();
		CNFTransformer transformer = new CNFTransformer();
		//
		Sentence parsedSentence = (Sentence) parser.parse(this.statementText);
		Sentence transformedSentence = transformer.transform(parsedSentence);
		Set<Sentence> clauses = gatherer.getClausesFrom(transformedSentence);
		
		//
		String ret = "";
		int i = 1;
		Iterator<Sentence> iter = clauses.iterator();
		while(iter.hasNext()){
			Sentence sentence = iter.next();
			ret += "\t<" + i + "> "+ sentence.toString() + "\n";
			i++;
		}
		
		return ret;
	}

}
