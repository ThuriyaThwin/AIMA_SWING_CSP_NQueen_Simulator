package csp;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;


public class QueenConstraint implements Constraint {

	private Variable var1;
	private Variable var2;
	private List<Variable> scope;

	public QueenConstraint(Variable var1, Variable var2) {
		this.var1 = var1;
		this.var2 = var2;
		scope = new ArrayList<Variable>(2);
		scope.add(var1);
		scope.add(var2);
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment assignment) {
		if(assignment.getAssignment(var1) == null || assignment.getAssignment(var2) == null)
			return true;
		
		QueenVariable queen1 = (QueenVariable)var1;
		QueenVariable queen2 = (QueenVariable)var2;
		int queen1_x = queen1.index;
		int queen1_y = ((Integer)assignment.getAssignment(var1)).intValue();
		int queen2_x = queen2.index;
		int queen2_y = ((Integer)assignment.getAssignment(var2)).intValue();
		boolean cond1 = queen1_y != queen2_y;
		boolean cond2 = Math.abs(queen1_x - queen2_x) != (Math.abs(queen1_y - queen2_y));
		
		return cond1 && cond2;
	}
}
