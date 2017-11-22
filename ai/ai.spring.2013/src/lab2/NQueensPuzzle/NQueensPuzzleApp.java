package lab2.NQueensPuzzle;

import java.util.List;

import aima.core.agent.Action;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;

public class NQueensPuzzleApp {
	public static void main(String args[]) {
		try {
			NQueenState intialState = new NQueenState(6);
			GoalEvaluator goalState = new GoalEvaluator();
			ActionFactory actionLister = new ActionFactory();
			ResultFactory resultLister = new ResultFactory();

			Problem problem = new Problem(intialState, actionLister, resultLister, goalState);

			//Search search = new BreadthFirstSearch(new TreeSearch());

			// Search search = new UniformCostSearch();
			//Search search = new DepthFirstSearch(new GraphSearch());
			//Search search = new DepthLimitedSearch(8);
			// Search search = new IterativeDeepeningSearch();

			Search search = new AStarSearch(new TreeSearch(), new HeuristicFunction() {

				@Override
				public double h(Object state) {
					int sum = 0;
					NQueenState s = (NQueenState) state;
					for (int i = 0; i < s.board.length; i++) {
						System.out.print(" - " + s.board[i]);
					}
					System.out.println();

					for (int i = 0; i < s.board.length; i++) {
						for (int j = 0; j < s.board.length; j++) {
							if (!GoalEvaluator.checkValidPair(i, s.board[i], j, s.board[j]))
								sum++;
						}

					}
					System.out.println("sum = " + sum);
					return sum;

				}
			});

			SearchAgent agent = new SearchAgent(problem, search);
			List<Action> actions = agent.getActions();

			System.out.println("List of Actions should be performed to reach the goal state:");
			for (Action a : actions) {
				System.out.println(a.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
