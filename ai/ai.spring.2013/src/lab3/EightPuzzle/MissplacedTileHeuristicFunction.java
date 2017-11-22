package lab3.EightPuzzle;


import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

public class MissplacedTileHeuristicFunction implements HeuristicFunction {
	PuzzleState m_goalState;
	public MissplacedTileHeuristicFunction(int state[]){
		m_goalState = new PuzzleState(state);
	}
	
	public double h(Object state) {
		PuzzleState curState = (PuzzleState) state;
		int cost = 0;
		for (int tile = 1; tile < 9; tile++) {
			int curIndex = curState.getIndexOfTile(tile);
			int goalIndex = m_goalState.getIndexOfTile(tile);
			if(curIndex != goalIndex)
				cost++;
		}
		return cost;
	}
}