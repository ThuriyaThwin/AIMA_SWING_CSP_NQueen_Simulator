package lab2.MissionariesAndCannibals;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.ActionsFunction;

public class ActionFactory implements ActionsFunction {

	public static final Action	MISSIONARY_WITH_CANNIBAL	= new DynamicAction("MISSIONARY_WITH_CANNIBAL");
	public static final Action	MISSIONARY_WITH_FELLOW		= new DynamicAction("MISSIONARY_WITH_FELLOW");
	public static final Action	CANNIBALS_WITH_FELLOW		= new DynamicAction("CANNIBALS_WITH_FELLOW");
	public static final Action	MISSIONARY_MOVE_ALONE		= new DynamicAction("MISSIONARY_MOVE_ALONE");
	public static final Action	CANNIBAL_MOVE_ALONE			= new DynamicAction("CANNIBAL_MOVE_ALONE");

	public Set<Action> actions(Object s) {
		GameState state = (GameState) s;
		Set<Action> actionList = new LinkedHashSet<Action>();
		System.out.println("Get Actions");
		if (state.canMoveAMissionaryWithACannibal()) {
			System.out.println("Get Actions MISSIONARY_WITH_CANNIBAL");
			actionList.add(MISSIONARY_WITH_CANNIBAL);
		}
		if (state.canMoveMissionaryWithFellow()) {
			System.out.println("Get Actions MISSIONARY_WITH_FELLOW");
			actionList.add(MISSIONARY_WITH_FELLOW);
		}
		if (state.canMoveCannibalsWithFellow()) {
			System.out.println("Get Actions CANNIBALS_WITH_FELLOW");
			actionList.add(CANNIBALS_WITH_FELLOW);
		}
		if (state.canMoveMissionaryAlone()) {
			System.out.println("Get Actions MISSIONARY_MOVE_ALONE");
			actionList.add(MISSIONARY_MOVE_ALONE);
		}
		if (state.canMoveCannibalAlone()) {
			System.out.println("Get Actions CANNIBAL_MOVE_ALONE");
			actionList.add(CANNIBAL_MOVE_ALONE);
		}

		return actionList;
	}

}
