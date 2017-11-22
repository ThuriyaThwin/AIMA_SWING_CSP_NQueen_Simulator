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


public class FOL_Resolution2 {
	
	public static void main(String[] args) {
		question_7_page_439();
	}
	

	private static void question_7_page_439(){
		String premises[] ={
			"( FORALL point1 FORALL point2 FORALL plane FORALL line " +
					"( "  +
						" ((( point_on_plane(point2, plane) AND point_on_plane(point1, plane)) " +
						" AND point_on_line(point2, line)) AND point_on_line(point1, line)) " +
						" => line_on_plane(line, plane) " +
					")" +
			")",
			"(FORALL line1 FORALL line2 FORALL plane " +
					"( " +
						"(line_orth_plane(line1, plane) AND line_on_plane(line2, plane) ) " +
						" => line_orth_line(line1, line2)" + 
					")" +
			")",
			
			"( FORALL line1 FORALL line2 " +
					"(" +
						"(line_orth_line(line1, line2)) <=> (line_orth_line(line2, line1))" +
					")" +
			")",
			
			"(point_on_plane(POINT1, PLANE))",
			"(point_on_plane(POINT2, PLANE))",
			"(point_on_line(POINT1, LINE))",
			"(point_on_line(POINT2, LINE))",
			"(line_orth_plane(LINE_1, PLANE) )"
		};
		//String conclusion = "(line_on_plane(LINE, PLANE))";
		String conclusion = "(line_orth_line(LINE_1, LINE))";
		
		//Define domain
		FOLDomain domain = new FOLDomain();
		domain.addPredicate("point_on_line");
		domain.addPredicate("point_on_plane");
		domain.addPredicate("line_on_plane");
		domain.addPredicate("line_orth_plane");
		domain.addPredicate("line_orth_line");
		domain.addConstant("POINT1");
		domain.addConstant("POINT2");
		domain.addConstant("PLANE");
		domain.addConstant("LINE");
		domain.addConstant("LINE_1");
		domain.addAnswerLiteral();
		
		//Create inference procedure
		//FOLTFMResolution ip = new FOLTFMResolution();
		FOLOTTERLikeTheoremProver ip = new FOLOTTERLikeTheoremProver();
		StandardizeApartIndexicalFactory.flush();
		
		//Create knowledge base
		FOLKnowledgeBase kb = new FOLKnowledgeBase(domain, ip);
		
		//Add premises to the KB
		for(int i=0; i< premises.length; i++){
			kb.tell(premises[i]);
		}
		
		StandardizeApartIndexicalFactory.flush();
		//Ask the Knowledge base
		InferenceResult answer = kb.ask(conclusion);
		
		if(answer.isTrue())
			System.out.println("The proof is " + answer.isTrue());
		else
			System.out.println("The proof is " + answer.isTrue());
		
		System.out.println("Here is detail information:");
		System.out.println("-------------------------------------------------\n");
		
		//Print 
		String kbStr = kb.toString();
		System.out.println("The knowledge Base contains the following sentences:");
		System.out.println(kbStr);
		System.out.println("Query: " + conclusion);
		for (Proof p : answer.getProofs()) {
			System.out.print(ProofPrinter.printProof(p));
			System.out.println("");
		}
	}
}
