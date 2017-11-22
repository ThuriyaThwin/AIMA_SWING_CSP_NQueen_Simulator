package lab4.NQueen;


import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.environment.nqueens.QueenAction;
import aima.core.search.framework.ActionsFunction;
import aima.core.util.datastructure.XYLocation;

public class ActionFactory implements ActionsFunction{

	public Set<Action> actions(Object s) {
		Set<Action> actionList = new LinkedHashSet<Action>();
		NQueensState board = (NQueensState) s;
		for (int i = 0; i < board.getSize(); i++)
			for (int j = 0; j < board.getSize(); j++) {
				XYLocation loc = new XYLocation(i, j);
				if (!board.queenExistsAt(loc))
					actionList.add(new QueenAction(QueenAction.MOVE_QUEEN, loc));
			}
		return actionList;
	}

}
