package logic.simple;

import java.util.Set;


import aima.core.logic.propositional.algorithms.KnowledgeBase;
import aima.core.logic.propositional.algorithms.PLResolution;
import aima.core.logic.propositional.parsing.PEParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.parsing.ast.Symbol;


public class Reasoner {
	
	public static void main(String[] args) {
		//question_7_page_414();
		question_16_page_415();	
	}
	
	private static void question_7_page_414(){
		String premises[] ={
			"( (E OR F) => (C AND D) )",
			"( (D OR G) => H)",
			"( E OR G)"
		};
		String conclusion = "(H)";
		
		KnowledgeBase base  = new KnowledgeBase();
		base.tellAll(premises);
		boolean isProved = base.askWithDpll(conclusion);
		
		if(isProved){
			System.out.println("The conclusion CAN be derived from the premises!");
		}
		else{
			System.out.println("The conclusion CANNOT be derived from the premises!");
		}
			
	}
	private static void question_16_page_415(){
		String premises[] ={
			"( (N OR O) => (C AND D) )",
			"( (D OR K) => (P OR NOT C) )",
			"( (P OR G) => NOT (N AND D) )"
		};
		String conclusion = "(NOT N)";
		
		KnowledgeBase base  = new KnowledgeBase();
		base.tellAll(premises);
		boolean isProved = base.askWithDpll(conclusion);
		
		if(isProved){
			System.out.println("The conclusion CAN be derived from the premises!");
		}
		else{
			System.out.println("The conclusion CANNOT be derived from the premises!");
		}
			
	}
}
