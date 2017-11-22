/*
 * Student ID = XXXXXX
 * Student name = sssssss
 **/

package logic.fol;

import aima.core.logic.fol.domain.FOLDomain;
import aima.core.logic.fol.kb.FOLKnowledgeBase;
import aima.core.logic.fol.inference.InferenceResult;

public class Geometry1 {

	public static void main(String[] args) {
		FOLDomain domain = new FOLDomain();
		domain.addConstant("A");	//Point A
		domain.addConstant("B");	//Point B
		domain.addConstant("p");	//Plane A
		domain.addConstant("l");	//Line l
		domain.addConstant("t");	//Line t
		domain.addConstant("f");	//Line f
		
		domain.addPredicate("line_orth_plane"); 		//line_orth_plane(LINE, PLANE): LINE is orthogonal with PLANE	
		domain.addPredicate("line_orth_line");			//line_orth_line(LINE X, LINE Y): LINE X is orthogonal with LINE Y
		domain.addPredicate("plane_contain_line");		//plane_contain_line(PLANE, LINE): PLANE contains LINE
		domain.addPredicate("plane_contain_point");		//plane_contain_point(PLANE, POINT): PLANE contains POINT
		domain.addPredicate("line_contain_point");		//line_contain_point(LINE, POINT): LINE contains POINT
		domain.addPredicate("point_diff_point");		//point_diff_point(POINT X, POINT Y): POINT X is different to POINT Y
		domain.addPredicate("line_para_line");			//line_para_line(LINE X, LINE Y): LINE X is parallel to LINE Y
		
		FOLKnowledgeBase myKB = new FOLKnowledgeBase(domain);
		/*
		 * RULE:
		 * Line X is orthogonal to line Z if line x is orthogonal to plane Y and plane Y contains line Z
		 * */
		
		myKB.tell("(FORALL x(" +
				"			FORALL y(" +
				"					FORALL z(" +
				"							(line_orth_plane(x,y) AND plane_contain_line(y, z) ) => line_orth_line(x,z)" +
				"							)" +
				"					)" +
				"			)" +
				"	)");
		
		/*
		 * RULE:
		 * Plane X conains line Y if both of them contains two different points A and B
		 * */
		
		myKB.tell("(FORALL x(" +
				"			FORALL y(" +
				"					FORALL a(" +
				"							FORALL b(" +
				"									(" +
				"										plane_contain_point(x, a) AND " +
				"										plane_contain_point(x, b) AND " +
				"										line_contain_point(y, a) AND " +
				"										line_contain_point(y, b) AND " +
				"										point_diff_point(a, b) " +
				"									) " +
				"									=> " +
				"									plane_contain_line(x,y) " +
				"									)" +
				"							)" +
				"					)" +
				"			)" +
				"	)");
		
		/*
		 * RULE:
		 * Line X is orthogonal to line Z if line X is orthognal to line Y and Y is parallel to line Z
		 * */
		myKB.tell("(FORALL x(" +
				"			FORALL y(" +
				"					FORALL z(" +
				"							(line_orth_line(x,y) AND line_para_line(y, z) ) => line_orth_line(x,z)" +
				"							)" +
				"					)" +
				"			)" +
				"	)");
		
		
		myKB.tell("( plane_contain_point(p, A) )");
		myKB.tell("( plane_contain_point(p, B) )");
		myKB.tell("( line_contain_point(t, A) )");
		myKB.tell("( line_contain_point(t, B) )");
		myKB.tell("( point_diff_point(A, B) )");
		myKB.tell("( line_orth_plane(l, p) )");
		myKB.tell("( line_para_line(t, f) )");
		
	
		String conclusion = "(line_orth_line(l, f) )"; 
		

		InferenceResult ir = myKB.ask(conclusion);
		
		System.out.println("The conclusion is " + ir.isTrue());
	}

}
