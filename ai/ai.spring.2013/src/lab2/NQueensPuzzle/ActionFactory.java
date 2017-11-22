package lab2.NQueensPuzzle;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.ActionsFunction;

public class ActionFactory implements ActionsFunction {

	@Override
	public Set<Action> actions(Object s) {
		NQueenState state = (NQueenState) s;
		Set<Action> actionList = new LinkedHashSet<Action>();

		for (int i = 0; i < state.board.length; i++) {
			for (int j = 0; j < state.board.length; j++) {
				if (i != j) {
					actionList.add(new QueenAction(i, j));
				}
			}
		}

		return actionList;
	}

	public static class QueenAction extends DynamicAction {

		public int	queen, move;

		public QueenAction(int queen, int move) {
			super("move Q" + queen + " to " + move);
			this.queen = queen;
			this.move = move;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = (prime * result) + move;
			result = (prime * result) + queen;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			QueenAction other = (QueenAction) obj;
			if (move != other.move)
				return false;
			if (queen != other.queen)
				return false;
			return true;
		}

	}
}
