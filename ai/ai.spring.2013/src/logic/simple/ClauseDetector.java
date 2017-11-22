package logic.simple;

import java.util.Iterator;
import java.util.Set;


import aima.core.logic.propositional.algorithms.KnowledgeBase;
import aima.core.logic.propositional.algorithms.PLResolution;
import aima.core.logic.propositional.parsing.PEParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.parsing.ast.Symbol;
import aima.core.logic.propositional.visitors.CNFClauseGatherer;
import aima.core.logic.propositional.visitors.CNFTransformer;

public class ClauseDetector {
	public static void main(String[] args) {
		//forOneSentence();
		question_7_page_414();
		//question_16_page_415();	
	}
	private static void forOneSentence(){	
		//Process:
		// Input String -> Parser -> CNF Transformer -> Gatherer -> Output clauses (i.e., disjunctions)
		PEParser parser = new PEParser();						//from AIMA
		CNFTransformer transformer = new CNFTransformer(); 		//from AIMA
		CNFClauseGatherer gatherer = new CNFClauseGatherer();	//from AIMA
		
		String inputStatement = "((A OR B) => (NOT C AND D) )";
		Sentence plSentence = (Sentence) parser.parse(inputStatement);
		Sentence transformed = transformer.transform(plSentence);
		Set<Sentence> clauses = gatherer.getClausesFrom(transformed);
		
		//Get and print clauses
		int i = 1;
		Iterator<Sentence> iter = clauses.iterator();
		System.out.println("List of clauses from the input sentence:");
		while(iter.hasNext()){
			Sentence aClause = iter.next();
			String line = "(" + i + ") " + aClause.toString();
			System.out.println(line);
			i++;
		}
			
	}
	
	private static void question_7_page_414(){
		String premises[] ={
			"( (E OR F) => (C AND D) )",
			"( (D OR G) => H)",
			"( E OR G)"
		};
		String conclusion = "(H)";
		
		//Process:
		// Input String -> Parser -> CNF Transformer -> Gatherer -> Output clauses (i.e., disjunctions)
		PEParser parser = new PEParser();						//from AIMA
		CNFTransformer transformer = new CNFTransformer(); 		//from AIMA
		CNFClauseGatherer gatherer = new CNFClauseGatherer();	//from AIMA
		
		
		//for input sentences in premises
		for(int i=0; i< premises.length; i++){
			String inputStatement = premises[i];
			
			Sentence plSentence = (Sentence) parser.parse(inputStatement);
			Sentence transformed = transformer.transform(plSentence);
			Set<Sentence> clauses = gatherer.getClausesFrom(transformed);
			
			//Get and print clauses
			int c = 1;
			Iterator<Sentence> iter = clauses.iterator();
			System.out.println("\n Premise: " + inputStatement);
			while(iter.hasNext()){
				Sentence aClause = iter.next();
				String line = "\t(" + c + ") " + aClause.toString();
				System.out.println(line);
				c++;
			}
		}
		//for NOT of the conclusion
		
		String inputStatement = "( NOT " + conclusion + ")";
		
		Sentence plSentence = (Sentence) parser.parse(inputStatement);
		Sentence transformed = transformer.transform(plSentence);
		Set<Sentence> clauses = gatherer.getClausesFrom(transformed);
		
		//Get and print clauses
		int c = 1;
		Iterator<Sentence> iter = clauses.iterator();
		System.out.println("\n Negation of the conclusion: " + inputStatement);
		while(iter.hasNext()){
			Sentence aClause = iter.next();
			String line = "\t(" + c + ") " + aClause.toString();
			System.out.println(line);
			c++;
		}
			
	}
	
	private static void question_16_page_415(){
		String premises[] ={
				"( (N OR O) => (C AND D) )",
				"( (D OR K) => (P OR NOT C) )",
				"( (P OR G) => NOT (N AND D) )"
			};
			String conclusion = "(NOT N)";
		
		//Process:
		// Input String -> Parser -> CNF Transformer -> Gatherer -> Output clauses (i.e., disjunctions)
		PEParser parser = new PEParser();						//from AIMA
		CNFTransformer transformer = new CNFTransformer(); 		//from AIMA
		CNFClauseGatherer gatherer = new CNFClauseGatherer();	//from AIMA
		
		
		//for input sentences in premises
		for(int i=0; i< premises.length; i++){
			String inputStatement = premises[i];
			
			Sentence plSentence = (Sentence) parser.parse(inputStatement);
			Sentence transformed = transformer.transform(plSentence);
			Set<Sentence> clauses = gatherer.getClausesFrom(transformed);
			
			//Get and print clauses
			int c = 1;
			Iterator<Sentence> iter = clauses.iterator();
			System.out.println("\n Premise: " + inputStatement);
			while(iter.hasNext()){
				Sentence aClause = iter.next();
				String line = "\t(" + c + ") " + aClause.toString();
				System.out.println(line);
				c++;
			}
		}
		//for NOT of the conclusion
		
		String inputStatement = "( NOT " + conclusion + ")";
		
		Sentence plSentence = (Sentence) parser.parse(inputStatement);
		Sentence transformed = transformer.transform(plSentence);
		Set<Sentence> clauses = gatherer.getClausesFrom(transformed);
		
		//Get and print clauses
		int c = 1;
		Iterator<Sentence> iter = clauses.iterator();
		System.out.println("\n Negation of the conclusion: " + inputStatement);
		while(iter.hasNext()){
			Sentence aClause = iter.next();
			String line = "\t(" + c + ") " + aClause.toString();
			System.out.println(line);
			c++;
		}
			
	}

}
