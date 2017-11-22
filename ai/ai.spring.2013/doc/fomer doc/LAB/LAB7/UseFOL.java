/*
 * Student ID = XXXXXX
 * Student name = sssssss
 **/

package logic.fol;

import aima.core.logic.fol.domain.FOLDomain;
import aima.core.logic.fol.kb.FOLKnowledgeBase;
import aima.core.logic.fol.inference.InferenceResult;

public class UseFOL {

	public static void main(String[] args) {
		FOLDomain domain = new FOLDomain();
		domain.addConstant("An");
		domain.addConstant("Quang");
		domain.addPredicate("pmh"); 		//pmh(x) = x can has house in PMH
		domain.addPredicate("rich");		//rich(x) = x  is rich
		domain.addPredicate("muchmoney");	//muchmoney(x) = x has much money
		domain.addPredicate("govstaff"); 	//govstaff(x) = x receives ONLY salary from government sector
		
		FOLKnowledgeBase myKB = new FOLKnowledgeBase(domain);
		myKB.tell("(pmh(x) => rich(x))"); 
		myKB.tell("(rich(x) => muchmoney(x))");
		myKB.tell("(govstaff(x) => (NOT muchmoney(x)) )");
		myKB.tell("(pmh(Quang))");
		myKB.tell("(govstaff(An))");
		
		//String conclusion = "(pmh(An))"; //An can has house in PMH
		//String conclusion = "(pmh(Quang))"; //Quang can has house in PMH
		//String conclusion = "(muchmoney(An))"; //An has much money
		String conclusion = "(NOT muchmoney(An))"; //An has not much money
		
		InferenceResult ir = myKB.ask(conclusion);
		
		System.out.println("The conclusion is " + ir.isTrue());
	}

}
