package logic.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import aima.core.logic.fol.StandardizeApartIndexicalFactory;
import aima.core.logic.fol.domain.DomainFactory;
import aima.core.logic.fol.domain.FOLDomain;
import aima.core.logic.fol.inference.FOLBCAsk;
import aima.core.logic.fol.inference.FOLFCAsk;
import aima.core.logic.fol.inference.FOLTFMResolution;
import aima.core.logic.fol.inference.InferenceResult;
import aima.core.logic.fol.inference.proof.Proof;
import aima.core.logic.fol.inference.proof.ProofPrinter;
import aima.core.logic.fol.kb.FOLKnowledgeBase;
import aima.core.logic.fol.parsing.ast.Predicate;
import aima.core.logic.fol.parsing.ast.Term;
import aima.core.logic.fol.parsing.ast.Variable;
import aima.core.logic.propositional.algorithms.KnowledgeBase;
import aima.core.logic.propositional.algorithms.PLResolution;
import aima.core.logic.propositional.parsing.PEParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.parsing.ast.Symbol;


public class FOL_Ask {
	
	public static void main(String[] args) {
		question_7_page_439();
	}
	

	private static void question_7_page_439(){
		String premises[] ={
				"(A = B)",
				"(B = C)"
		};
		String conclusion = "(A = D)";
		
		//Define domain
		FOLDomain domain = new FOLDomain();
		domain.addConstant("A");
		domain.addConstant("B");
		domain.addConstant("C");
		domain.addConstant("D");
		
		
		//Create inference procedure
		FOLFCAsk ip = new FOLFCAsk();
		
		
		
		//Create knowledge base
		FOLKnowledgeBase kb = new FOLKnowledgeBase(domain, ip);
		
		//Add premises to the KB
		for(int i=0; i< premises.length; i++){
			kb.tell(premises[i]);
		}
		kb.tell("x = x");
		// Symmetry Axiom
		kb.tell("(x = y => y = x)");
		// Transitivity Axiom
		kb.tell("((x = y AND y = z) => x = z)");
		
		StandardizeApartIndexicalFactory.flush();
		//Ask the Knowledge base
		
		InferenceResult answer = kb.ask(conclusion);
		
		//Print 
		String kbStr = kb.toString();
		System.out.println("Weapons Knowledge Base contains the following sentences:");
		System.out.println(kbStr);
		System.out.println("Query: " + conclusion);
		for (Proof p : answer.getProofs()) {
			System.out.print(ProofPrinter.printProof(p));
			System.out.println("");
		}
	}
}
