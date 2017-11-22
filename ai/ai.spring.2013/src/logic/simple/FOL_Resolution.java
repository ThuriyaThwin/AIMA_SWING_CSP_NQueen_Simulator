package logic.simple;

import java.util.Set;


import aima.core.logic.fol.StandardizeApartIndexicalFactory;
import aima.core.logic.fol.domain.DomainFactory;
import aima.core.logic.fol.domain.FOLDomain;
import aima.core.logic.fol.inference.FOLOTTERLikeTheoremProver;
import aima.core.logic.fol.inference.FOLTFMResolution;
import aima.core.logic.fol.inference.InferenceResult;
import aima.core.logic.fol.inference.proof.Proof;
import aima.core.logic.fol.inference.proof.ProofPrinter;
import aima.core.logic.fol.kb.FOLKnowledgeBase;
import aima.core.logic.propositional.algorithms.KnowledgeBase;
import aima.core.logic.propositional.algorithms.PLResolution;
import aima.core.logic.propositional.parsing.PEParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.parsing.ast.Symbol;


public class FOL_Resolution {
	
	public static void main(String[] args) {
		question_7_page_439();
	}
	

	private static void question_7_page_439(){
		String premises[] ={
			"(FORALL x (A(x) => (B(x) OR C(x))))",
			"(FORALL x (NOT C(x)) )",
			"(FORALL x (A(x)) )"
		};
		//String conclusion = "(EXISTS x B(x))";
		String conclusion = "( B(a) )";
		
		//Define domain
		FOLDomain domain = new FOLDomain();
		domain.addPredicate("A");
		domain.addPredicate("B");
		domain.addPredicate("C");
		domain.addConstant("a");
		
		//Create inference procedure
		//FOLTFMResolution ip = new FOLTFMResolution();
		FOLOTTERLikeTheoremProver ip = new FOLOTTERLikeTheoremProver();
		StandardizeApartIndexicalFactory.flush();
		
		//Create knowledge base
		FOLKnowledgeBase kb = new FOLKnowledgeBase(domain, ip);
		
		//Add premises to the KB
		for(int i=0; i< premises.length; i++) {
			kb.tell(premises[i]);
		}
		
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
