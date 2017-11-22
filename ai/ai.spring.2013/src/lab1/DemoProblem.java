package lab1;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.GoalTest;
import aima.core.search.framework.ResultFunction;

public class DemoProblem implements GoalTest  {

	public int m_val = 0;
	
	public static Action FIRST = new DynamicAction("FIRST");
	public static Action SECOND = new DynamicAction("SECOND");
	public static Action THIRD = new DynamicAction("THIRD");
	public static Action FOURTH = new DynamicAction("FOURTH");
	
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	
	public DemoProblem() {
		m_val = 0;
	}
	
	
	public DemoProblem(int i) {
		this.m_val  = i;
	}


	@Override
	public boolean isGoalState(Object state) {	
		DemoProblem astate = (DemoProblem)state;
		if(astate.m_val == 14) return true;
		else return false;

	}

	
	
	public static class EPActionsFunction implements ActionsFunction
	{

		@Override
		public Set<Action> actions(Object s) {
			Set<Action> actions = new LinkedHashSet<Action>();
			actions.add(DemoProblem.FIRST);
			actions.add(DemoProblem.SECOND);
			actions.add(DemoProblem.THIRD);
			return actions;
		}
	}
	
	public static class EPResultFunction implements ResultFunction {

			@Override
			public Object result(Object s, Action a) {
				DemoProblem state = (DemoProblem)s;
				System.out.println("TRY " + a + " "); 
				if(state.m_val == 0){
					if(DemoProblem.FIRST.equals(a)){
						System.out.println(">" + 1);
						return new DemoProblem(1);
					}
					if(DemoProblem.SECOND.equals(a)){
						System.out.println(">" + 2);
						return new DemoProblem(2);
					}
					if(DemoProblem.THIRD.equals(a)){
						System.out.println(">" + 3);
						return new DemoProblem(3);
					}
					
				}
				
				if(state.m_val == 1){
					if(DemoProblem.FIRST.equals(a)){
						System.out.println(">" + 4);
						return new DemoProblem(4);
					}
					if(DemoProblem.SECOND.equals(a)){
						System.out.println(">" + 5);
						return new DemoProblem(5);
					}
					if(DemoProblem.THIRD.equals(a)){
						System.out.println(">" + 6);
						return new DemoProblem(6);
					}
				}
				if(state.m_val == 2){
					if(DemoProblem.FIRST.equals(a)){
						System.out.println(">" + 7);
						return new DemoProblem(7);
					}
					if(DemoProblem.SECOND.equals(a)){
						System.out.println(">" + 8);
						return new DemoProblem(8);
					}
					if(DemoProblem.THIRD.equals(a)){
						System.out.println(">" + 9);
						return new DemoProblem(9);
					}
				}
				if(state.m_val == 3){
					if(DemoProblem.FIRST.equals(a)){
						System.out.println(">" + 10);
						return new DemoProblem(10);
					}
					if(DemoProblem.SECOND.equals(a)){
						System.out.println(">" + 11);
						return new DemoProblem(11);
					}
					if(DemoProblem.THIRD.equals(a)){
						System.out.println(">" + 12);
						return new DemoProblem(12);
					}
				}

				if(state.m_val == 7){
					if(DemoProblem.FIRST.equals(a)){
						System.out.println(">" + 13);
						return new DemoProblem(13);
					}
					if(DemoProblem.SECOND.equals(a)){
						System.out.println(">" + 14);
						return new DemoProblem(14);
					}
					if(DemoProblem.THIRD.equals(a)){
						System.out.println(">" + 15);
						return new DemoProblem(15);
					}
				}
				if(state.m_val == 12){
					if(DemoProblem.FIRST.equals(a)){
						System.out.println(">" + 16);
						return new DemoProblem(16);
					}
					if(DemoProblem.SECOND.equals(a)){
						System.out.println(">" + 17);
						return new DemoProblem(17);
					}
					if(DemoProblem.THIRD.equals(a)){
						System.out.println(">" + 18);
						return new DemoProblem(18);
					}
				}
				return s;

			}
			
		}
}
