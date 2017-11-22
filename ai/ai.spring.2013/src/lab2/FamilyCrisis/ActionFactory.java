package lab2.FamilyCrisis;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.ActionsFunction;

public class ActionFactory implements ActionsFunction {

	//move anlone
	public static final Action	A_1		= new DynamicAction("A_1");
	public static final Action	A_3		= new DynamicAction("A_3");
	public static final Action	A_6		= new DynamicAction("A_6");
	public static final Action	A_8		= new DynamicAction("A_8");
	public static final Action	A_12	= new DynamicAction("A_12");

	//move 1 with other
	public static final Action	A_1_3	= new DynamicAction("A_1_3");
	public static final Action	A_1_6	= new DynamicAction("A_1_6");
	public static final Action	A_1_8	= new DynamicAction("A_1_8");
	public static final Action	A_1_12	= new DynamicAction("A_1_8");

	//move 3 with other
	public static final Action	A_3_6	= new DynamicAction("A_3_6");
	public static final Action	A_3_8	= new DynamicAction("A_3_8");
	public static final Action	A_3_12	= new DynamicAction("A_3_12");

	//move 6 with other
	public static final Action	A_6_8	= new DynamicAction("A_6_8");
	public static final Action	A_6_12	= new DynamicAction("A_6_12");

	//movw 8 with 12
	public static final Action	A_8_12	= new DynamicAction("A_8_12");
	
	public Set<Action> actions(Object s) {
		GameState state = (GameState) s;
		Set<Action> actionList = new LinkedHashSet<Action>();
		System.out.println("Get Actions");
		if (state.canMoveA_1_Alone()) {
			System.out.println("Get Actions A_1");
			actionList.add(A_1);
		}
		if (state.canMoveA_3_Alone()) {
			System.out.println("Get Actions A_3");
			actionList.add(A_3);
		}
		if (state.canMoveA_6_Alone()) {
			System.out.println("Get Actions A_6");
			actionList.add(A_6);
		}
		if (state.canMoveA_8_Alone()) {
			System.out.println("Get Actions A_8");
			actionList.add(A_8);
		}
		if (state.canMoveA_12_Alone()) {
			System.out.println("Get Actions A_12");
			actionList.add(A_12);
		}
		if (state.canMoveA_1_With_3()) {
			System.out.println("Get Actions A_1_3");
			actionList.add(A_1_3);
		}
		if (state.canMoveA_1_With_6()) {
			System.out.println("Get Actions A_1_6");
			actionList.add(A_1_6);
		}
		if (state.canMoveA_1_With_8()) {
			System.out.println("Get Actions A_1_8");
			actionList.add(A_1_8);
		}
		if (state.canMoveA_1_With_12()) {
			System.out.println("Get Actions A_1_12");
			actionList.add(A_1_12);
		}
		if (state.canMoveA_3_With_6()) {
			System.out.println("Get Actions A_3_6");
			actionList.add(A_3_6);
		}

		if (state.canMoveA_3_With_8()) {
			System.out.println("Get Actions A_3_8");
			actionList.add(A_3_8);
		}
		if (state.canMoveA_3_With_12()) {
			System.out.println("Get Actions A_3_12");
			actionList.add(A_3_12);
		}
		if (state.canMoveA_6_With_8()) {
			System.out.println("Get Actions A_6_6");
			actionList.add(A_6_8);
		}
		if (state.canMoveA_6_With_12()) {
			System.out.println("Get Actions A_6_12");
			actionList.add(A_6_12);
		}
		if (state.canMoveA_8_With_12()) {
			System.out.println("Get Actions A_8_12");
			actionList.add(A_8_12);
		}
		
		return actionList;
	}

}
