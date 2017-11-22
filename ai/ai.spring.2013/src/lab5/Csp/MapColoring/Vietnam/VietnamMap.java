package lab5.Csp.MapColoring.Vietnam;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.NotEqualConstraint;
import aima.core.search.csp.Variable;

public class VietnamMap extends CSP {
	public static final Variable	DK		= new Variable("DK");
	public static final Variable	LD		= new Variable("LD");
	public static final Variable	BP		= new Variable("BP");
	public static final Variable	BT		= new Variable("BT");
	public static final Variable	BR		= new Variable("BR");
	public static final Variable	DN		= new Variable("DN");
	public static final Variable	HCM		= new Variable("HCM");
	public static final Variable	BD		= new Variable("BD");
	public static final Variable	TN		= new Variable("TN");

	public static final String		RED		= "RED";
	public static final String		GREEN	= "GREEN";
	public static final String		BLUE	= "BLUE";

	private static List<Variable> collectVariables() {
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(DK);
		variables.add(LD);
		variables.add(BP);
		variables.add(BT);
		variables.add(BR);
		variables.add(DN);
		variables.add(HCM);
		variables.add(BD);
		variables.add(TN);

		return variables;
	}

	public VietnamMap() {
		super(collectVariables());

		Domain colors = new Domain(new Object[] { RED, GREEN, BLUE });

		for (Variable var : getVariables())
			setDomain(var, colors);

		addConstraint(new NotEqualConstraint(DK, BP));
		addConstraint(new NotEqualConstraint(DK, LD));

		addConstraint(new NotEqualConstraint(LD, BP));
		addConstraint(new NotEqualConstraint(LD, DN));
		addConstraint(new NotEqualConstraint(LD, BT));

		addConstraint(new NotEqualConstraint(BT, DN));
		addConstraint(new NotEqualConstraint(BT, BR));

		addConstraint(new NotEqualConstraint(BR, DN));

		addConstraint(new NotEqualConstraint(DN, HCM));
		addConstraint(new NotEqualConstraint(DN, BD));
		addConstraint(new NotEqualConstraint(DN, BP));

		addConstraint(new NotEqualConstraint(BP, TN));
		addConstraint(new NotEqualConstraint(BP, BD));

		addConstraint(new NotEqualConstraint(TN, BD));
		addConstraint(new NotEqualConstraint(TN, HCM));

		addConstraint(new NotEqualConstraint(BD, HCM));

		//16 constraint

	}
}
