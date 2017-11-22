package lab4.NQueen;

import java.util.List;

import aima.core.agent.Action;
import aima.core.search.framework.GoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.SearchAgent;
import aima.core.search.local.HillClimbingSearch;
import aima.core.search.local.Scheduler;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.local.SimulatedAnnealingSearch.SearchOutcome;

public class NQueenApp {

	public NQueensState	start;

	public List<Action> getActionsHillClimbingSearch() throws Exception {
		System.out.println("HILL CLIMBING SEARCH");

		Problem problem = new Problem(start, new ActionFactory(), new NQueenResultFactory(), new GoalTest() {

			@Override
			public boolean isGoalState(Object state) {

				NQueensState nqueen = (NQueensState) state;
				if (nqueen.getNumberOfAttackingPairs() == 0) { return true; }
				return false;
			}
		});

		HillClimbingSearch search = new HillClimbingSearch(new AttackingPairsHeuristic());

		SearchAgent agent = new SearchAgent(problem, search);
		List<Action> actions = agent.getActions();

		if (search.getOutcome() == HillClimbingSearch.SearchOutcome.SOLUTION_FOUND) {
			System.out.println("Solution Found after: " + actions.size() + " actions");

		} else {
			System.out.println("FAILURE after: " + actions.size() + " actions");
		}

		System.out.println("List of Actions have been performed:");
		for (Action a : actions) {
			System.out.println(a.toString());
		}

		return actions;
	}

	public List<Action> getActionsSimulatedAnnealingSearch() throws Exception {

		System.out.println("SIMULATED ANNEALING SEARCH");

		Problem problem = new Problem(start, new ActionFactory(), new NQueenResultFactory(), new GoalTest() {

			@Override
			public boolean isGoalState(Object state) {

				NQueensState nqueen = (NQueensState) state;
				if (nqueen.getNumberOfAttackingPairs() == 0) { return true; }
				return false;
			}
		});

		SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(new AttackingPairsHeuristic(), new Scheduler(20, 0.045, 100));

		SearchAgent agent = new SearchAgent(problem, search);
		List<Action> actions = agent.getActions();

		if (search.getOutcome() == SearchOutcome.SOLUTION_FOUND) {
			System.out.println("Solution Found after: " + actions.size() + " actions");
		} else
			System.out.println("FAILURE after: " + actions.size() + " actions");

		System.out.println("List of Actions have been performed:");
		for (Action a : actions) {
			System.out.println(a.toString());
		}

		return actions;
	}

	public static void main(String[] arg) {

		NQueensState start = new NQueensState(4);
		start.setRandomBoard();
		try {

			NQueenApp app = new NQueenApp();
			app.start = start;
			List<Action> a = app.getActionsSimulatedAnnealingSearch();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
