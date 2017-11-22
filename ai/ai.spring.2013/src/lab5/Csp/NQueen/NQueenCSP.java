package lab5.Csp.NQueen;

import java.util.List;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;

public class NQueenCSP extends CSP {
	INQueenListener	iNQueenListener;

	public void setNQueenListener(INQueenListener i) {

		iNQueenListener = i;
	}

	public NQueenCSP(List<Variable> queenCollection, INQueenListener iNQueenListener) {
		super(queenCollection);
		this.iNQueenListener = iNQueenListener;
		Object[] d = new Object[queenCollection.size()];
		for (int i = 0; i < queenCollection.size(); i++) {
			d[i] = new Integer(i);
		}
		Domain colors = new Domain(d);

		for (Variable var : getVariables())
			setDomain(var, colors);

		// add constraint
		for (int i = 0; i < queenCollection.size(); i++) {
			for (int j = 0; j < queenCollection.size(); j++) {
				if (i == j)
					continue;

				QueenConstraint queenConstraint = new QueenConstraint(queenCollection.get(i), queenCollection.get(j));
				queenConstraint.setNQueenListener(iNQueenListener);
				addConstraint(queenConstraint);
			}
		}

	}
}
