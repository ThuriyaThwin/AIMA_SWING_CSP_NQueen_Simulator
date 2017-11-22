package lab2.MissionariesAndCannibals;

public class GameState {

	public static final int	EAST_C		= 1;
	public static final int	EAST_M		= 0;
	public static final int	EAST_SIDE	= 0;
	public static final int	WEST_C		= 3;
	public static final int	WEST_M		= 2;
	public static final int	WEST_SIDE	= 1;

	public int				m_state[]	= { 3, 3, 0, 0 };

	public int				transpoter	= 0;

	public GameState(int transpoter, int state[]) {
		m_state[0] = state[0];
		m_state[1] = state[1];
		m_state[2] = state[2];
		m_state[3] = state[3];
		this.transpoter = transpoter;
		System.out.println(toString());

		try {

			// Thread.sleep(500);
			if (!isValidState(state)) {
				isValidState(state);
				System.out.println("invalid data'");

				throw new Exception();
			}
		} catch (Exception ex) {

			ex.printStackTrace();
			System.exit(0);

		}
	}

	public boolean canMoveAMissionaryWithACannibal() {
		int[] newState = state_mc();

		boolean cond1 = isValidState(newState);

		if (cond1)
			return true;
		else
			return false;
	}

	public boolean canMoveCannibalAlone() {
		int[] newState = state_c();

		boolean cond1 = isValidState(newState);

		if (cond1)
			return true;
		else
			return false;
	}

	public boolean canMoveCannibalsWithFellow() {
		int[] newState = state_cc();

		boolean cond1 = isValidState(newState);

		if (cond1)
			return true;
		else
			return false;
	}

	public boolean canMoveMissionaryAlone() {
		int[] newState = state_m();

		boolean cond1 = isValidState(newState);

		if (cond1)
			return true;
		else
			return false;
	}

	public boolean canMoveMissionaryWithFellow() {
		int[] newState = state_mm();

		boolean cond1 = isValidState(newState);

		if (cond1)
			return true;
		else
			return false;
	}


	boolean isValidState(int[] state) {

		if (state[EAST_M] > 3)
			return false;
		if (state[EAST_C] > 3)
			return false;

		if (state[WEST_M] > 3)
			return false;
		if (state[WEST_C] > 3)
			return false;

		if (state[EAST_M] < 0)
			return false;
		if (state[EAST_C] < 0)
			return false;

		if (state[WEST_M] < 0)
			return false;
		if (state[WEST_C] < 0)
			return false;

		if ((state[EAST_M] + state[WEST_M]) != 3)
			return false;
		if ((state[EAST_C] + state[WEST_C]) != 3)
			return false;

		if ((state[EAST_M] < state[EAST_C]) && (state[EAST_M] != 0))
			return false;
		if ((state[WEST_M] < state[WEST_C]) && (state[WEST_M] != 0))
			return false;

		System.out.println("True: " + state[EAST_M] + " " + state[EAST_C] + " " + state[WEST_M] + " " + state[WEST_C]);

		return true;
	}

	public GameState moveCannibalAlone() {

		System.out.println("try move a cannibal alone");
		return new GameState(movetranspoter(), state_c());
	}

	public GameState moveCannibalsWithFellow() {

		System.out.println("try move a 2 cannibals");
		return new GameState(movetranspoter(), state_cc());
	}

	public GameState moveMissionaryAlone() {

		System.out.println("try move a missionary alone");
		return new GameState(movetranspoter(), state_m());
	}

	public GameState moveMissionaryAndCannibal() {
		System.out.println("try move a missionary and cannibal");
		return new GameState(movetranspoter(), state_mc());
	}

	public GameState moveMissionaryWithFellow() {

		System.out.println("try move a 2 missionaries");
		return new GameState(movetranspoter(), state_mm());
	}

	public int movetranspoter() {
		if (transpoter == EAST_SIDE) {
			return WEST_SIDE;
		} else {
			return EAST_SIDE;
		}
	}


	private String num2String(int side) {
		if (side == WEST_SIDE)
			return "WEST";
		else
			return "EAST";
	}

	private int[] state_c() {
		int newState[] = null;
		if (transpoter == EAST_SIDE) {

			int e_m = m_state[EAST_M];
			int e_c = m_state[EAST_C] - 1;

			int w_m = m_state[WEST_M];
			int w_c = m_state[WEST_C] + 1;
			System.out.println("State C AT E :" + e_m + "," + e_c + "-" + w_m + "," + w_c);
			newState = new int[] { e_m, e_c, w_m, w_c };

		} else if (transpoter == WEST_SIDE) {

			int e_m = m_state[EAST_M];
			int e_c = m_state[EAST_C] + 1;

			int w_m = m_state[WEST_M];
			int w_c = m_state[WEST_C] - 1;
			System.out.println("State C AT W :" + e_m + "," + e_c + "-" + w_m + "," + w_c);
			newState = new int[] { e_m, e_c, w_m, w_c };

		}
		return newState;
	}

	private int[] state_cc() {
		int newState[] = null;
		if (transpoter == EAST_SIDE) {

			int e_m = m_state[EAST_M];
			int e_c = m_state[EAST_C] - 2;

			int w_m = m_state[WEST_M];
			int w_c = m_state[WEST_C] + 2;
			System.out.println("State CC AT E :" + e_m + "," + e_c + "-" + w_m + "," + w_c);
			newState = new int[] { e_m, e_c, w_m, w_c };

		} else if (transpoter == WEST_SIDE) {

			int e_m = m_state[EAST_M];
			int e_c = m_state[EAST_C] + 2;

			int w_m = m_state[WEST_M];
			int w_c = m_state[WEST_C] - 2;
			System.out.println("State CC AT W :" + e_m + "," + e_c + "-" + w_m + "," + w_c);
			newState = new int[] { e_m, e_c, w_m, w_c };

		}
		return newState;
	}

	private int[] state_m() {
		int newState[] = null;
		if (transpoter == EAST_SIDE) {

			int e_m = m_state[EAST_M] - 1;
			int e_c = m_state[EAST_C];

			int w_m = m_state[WEST_M] + 1;
			int w_c = m_state[WEST_C];
			System.out.println("State M AT E :" + e_m + "," + e_c + "-" + w_m + "," + w_c);
			newState = new int[] { e_m, e_c, w_m, w_c };

		} else if (transpoter == WEST_SIDE) {

			int e_m = m_state[EAST_M] + 1;
			int e_c = m_state[EAST_C];

			int w_m = m_state[WEST_M] - 1;
			int w_c = m_state[WEST_C];
			System.out.println("State M AT W :" + e_m + "," + e_c + "-" + w_m + "," + w_c);
			newState = new int[] { e_m, e_c, w_m, w_c };

		}
		return newState;
	}

	private int[] state_mc() {
		int newState[] = null;
		if (transpoter == EAST_SIDE) {

			int e_m = m_state[EAST_M] - 1;
			int e_c = m_state[EAST_C] - 1;

			int w_m = m_state[WEST_M] + 1;
			int w_c = m_state[WEST_C] + 1;
			System.out.println("State MC AT E :" + e_m + "," + e_c + "-" + w_m + "," + w_c);
			newState = new int[] { e_m, e_c, w_m, w_c };
			// System.out.println("MC:E " + m_state[0] + "-" + m_state[1] + "-"
			// + m_state[2] + "-" + m_state[3]);
			// try {
			//
			// Thread.sleep(1000);
			// throw new Exception();
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// }

		} else if (transpoter == WEST_SIDE) {

			int e_m = m_state[EAST_M] + 1;
			int e_c = m_state[EAST_C] + 1;

			int w_m = m_state[WEST_M] - 1;
			int w_c = m_state[WEST_C] - 1;
			System.out.println("State MC AT W :" + e_m + "," + e_c + "-" + w_m + "," + w_c);
			newState = new int[] { e_m, e_c, w_m, w_c };

			// System.out.println("MC:W" + m_state[0] + "-" + m_state[1] + "-" +
			// m_state[2] + "-" + m_state[3]);
			//
			// try {
			//
			// Thread.sleep(1000);
			// throw new Exception();
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// }

		}

		return newState;
	}

	private int[] state_mm() {
		int newState[] = null;
		if (transpoter == EAST_SIDE) {

			int e_m = m_state[EAST_M] - 2;
			int e_c = m_state[EAST_C];

			int w_m = m_state[WEST_M] + 2;
			int w_c = m_state[WEST_C];
			System.out.println("State MM AT E :" + e_m + "," + e_c + "-" + w_m + "," + w_c);
			newState = new int[] { e_m, e_c, w_m, w_c };

		} else if (transpoter == WEST_SIDE) {

			int e_m = m_state[EAST_M] + 2;
			int e_c = m_state[EAST_C];

			int w_m = m_state[WEST_M] - 2;
			int w_c = m_state[WEST_C];
			System.out.println("State MM AT W :" + e_m + "," + e_c + "-" + w_m + "," + w_c);
			newState = new int[] { e_m, e_c, w_m, w_c };

		}
		return newState;
	}

	public String toString() {
		String result = "";
		result = "Transpoter AT " + num2String(transpoter) + " [ EAST [ Missionaries = " + m_state[EAST_M] + ", Cannibals=" + m_state[EAST_C] + "];  WEST  [ Missionaries = " + +m_state[WEST_M] + ", Cannibals=" + m_state[WEST_C] + " ]";
		return result;
	}

}
