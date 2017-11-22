package tutorial;

import java.util.List;
import java.util.Set;

import aima.core.logic.fol.domain.FOLDomain;
import aima.core.logic.fol.inference.InferenceResult;
import aima.core.logic.fol.kb.FOLKnowledgeBase;
import aima.core.logic.fol.kb.data.CNF;
import aima.core.logic.fol.kb.data.Clause;
import aima.core.logic.fol.parsing.FOLVisitor;
import aima.core.logic.fol.parsing.ast.FOLNode;
import aima.core.logic.fol.parsing.ast.Sentence;

public class UseFOL {

	public static void main(String[] args) {
		FOLDomain domain = new FOLDomain();
		domain.addConstant("HOA");
//		domain.addConstant("B");

		domain.addPredicate("a");
		domain.addPredicate("b");
		domain.addPredicate("c");
		domain.addPredicate("d");

		FOLKnowledgeBase myKB = new FOLKnowledgeBase(domain);
		myKB.tell(" NOT((math(x) AND NOT program(x) )");
		myKB.tell(" ((program(x) => job(x) )");
		myKB.tell(" ((student(x) => math(x) )");
		//myKB.tell("(b(x) => d(x))");
		

		List<Sentence> sentence =  myKB.getOriginalSentences();
		for (Sentence sentence2 : sentence) {
			System.out.println("Sentence = " + sentence2);
		}
		
		//CNF cnf =  new 
		
		Set<Clause> a = myKB.getAllClauses();
		for (Clause clause : a) {
			System.out.println("clause = " + clause);
		} 
		
		InferenceResult ir = myKB.ask("(a(A))");
		System.out.println("IS TRUE = " + ir.isTrue());
	}

}
