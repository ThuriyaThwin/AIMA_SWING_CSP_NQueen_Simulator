package lab1;

import java.util.List;

import aima.core.agent.Action;
import aima.core.search.framework.GoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;

public class BFSearch {
	public static void main(String args[]) {
		try{
			
			
			GoalTest initTest  = new DemoProblem(0);
			GoalTest goldTest  = new DemoProblem(0);
			
			Problem problem = new Problem(initTest, //trang thai ban dau
					DemoProblem.getActionsFunction(),//
					DemoProblem.getResultFunction(),
					goldTest);

			Search search = new BreadthFirstSearch(new TreeSearch());
			//Search search = new DepthFirstSearch(new TreeSearch());
			//(3)Create an object to solve the problem by the algorithm

			SearchAgent agent = new SearchAgent(problem, search);

			//(4)Get the solution path

			List<Action> actions = agent.getActions();
		
			//(5)Print the path if you need

			for(Action a: actions){
				System.out.println("A = " + a.toString());
			}

		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
}

