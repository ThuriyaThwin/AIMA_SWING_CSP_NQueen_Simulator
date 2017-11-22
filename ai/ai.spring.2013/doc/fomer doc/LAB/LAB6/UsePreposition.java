package logic.preposition;

import java.util.Iterator;
import java.util.Set;

import aima.core.logic.propositional.algorithms.KnowledgeBase;
import aima.core.logic.propositional.algorithms.PLResolution;
import aima.core.logic.propositional.parsing.PEParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.visitors.CNFClauseGatherer;
import aima.core.logic.propositional.visitors.CNFTransformer;

public class UsePreposition {

	public static void main(String[] args) {
		//Create a knowledge base
		KnowledgeBase kb = new KnowledgeBase();
		//Create a Resolution-based prover
		PLResolution prover = new PLResolution();
		
		//Add facts to the knowledge base
		kb.tell("((T AND P) => S)");
		kb.tell("((A OR D) => T)");
		kb.tell("((E AND F) => P)");
		kb.tell("((B OR C) => A)");
		kb.tell("((G OR H) => D)");
		kb.tell("(C => (A AND F))");
		kb.tell("(H => (D AND E))");
		kb.tell("(C)");
		kb.tell("(H)");
		
		//Declare the sentence, called 'conclusion', that you want to infer from the knowledge base
		//String conclusion = "(NOT S AND T)";
		String conclusion = "(F)";
		
		//Prove the conclusion
		boolean result = prover.plResolution(kb, conclusion);
		
		//Create Parser: to verify syntax
		PEParser parser = new PEParser();
		//Create Transformer: to convert to CNF sentences
		CNFTransformer transformer =  new CNFTransformer();
		//Create Gatherer: to break into CNF clause
		CNFClauseGatherer gatherer = new CNFClauseGatherer();
		
		//Output:===============================================
		System.out.println("Knowledge Base contains the following sentences:");
		for(int i=0; i< kb.getSentences().size(); i++){
			System.out.println((i+1) + ".\t" + kb.getSentences().get(i).toString());
		}
		System.out.println("CNF Clauses of the Knowledge base:");
		
		int index = 1;
		for(int i=0; i< kb.getSentences().size(); i++){
			String str_sentence = kb.getSentences().get(i).toString();
			//PARSE
			Sentence pl_sentence = (Sentence) parser.parse(str_sentence);
			//TRANSFORM
			Sentence cnf_sentence = transformer.transform(pl_sentence);
			//GATHER
			Set<Sentence> clauses	= gatherer.getClausesFrom(cnf_sentence);
			Iterator<Sentence> iter = clauses.iterator();
			while(iter.hasNext()){
				Sentence clause = iter.next();
				System.out.println(index + ".\t" + clause.toString());
				index++;
			}
		}
		
		System.out.println("The sentence which we want to prove is as follows:");
			System.out.println(conclusion);
		
		//Print the result:
		//Result = TRUE means the prover has found a CONTRADITION  
		//Result = FALSE means the prover COULD NOT find a CONTRADITION  
		if(result){
			System.out.println("We CAN infer " + conclusion + " from the knowledge base");
		}
		else{
			System.out.println("We CANNOT infer " + conclusion + " from the knowledge base");
		}
		
	}
}
