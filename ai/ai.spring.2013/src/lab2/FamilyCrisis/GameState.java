package lab2.FamilyCrisis;

public class GameState {

	// move from East to West
	public static final int	EAST_SIDE	= 0;
	public static final int	WEST_SIDE	= 1;

	public static final int	SECOND_LEFT	= 0;
	public static final int	LIGHT		= 1;

	public static final int	P1			= 2;
	public static final int	P3			= 3;
	public static final int	P6			= 4;
	public static final int	P8			= 5;
	public static final int	P12			= 6;

	// order of array: second left, light side, child, brother, mother, farther,
	// grantfarther
	public int				m_state[]	= { 30, EAST_SIDE, EAST_SIDE, EAST_SIDE, EAST_SIDE, EAST_SIDE, EAST_SIDE };

	public GameState(int state[]) {
		m_state[0] = state[0];
		m_state[1] = state[1];
		m_state[2] = state[2];
		m_state[3] = state[3];
		m_state[4] = state[4];
		m_state[5] = state[5];
		m_state[6] = state[6];
		try {
			// Thread.sleep(500);
			System.out.println(toString());
		} catch (Exception ex) {}

	}

	public static int invertDirection(int direct) {
		return ((direct == WEST_SIDE) ? EAST_SIDE : WEST_SIDE);
	}

	public boolean NotSameSide(int index1, int index2) {
		if (m_state[index1] != m_state[index2])
			return true;
		else
			return false;
	}

	public boolean canMoveA_1_Alone() {
		if (NotSameSide(P1, LIGHT))
			return false;
		if (m_state[0] >= 1)
			return true;
		else
			return false;
	};

	public boolean canMoveA_3_Alone() {
		if (NotSameSide(P3, LIGHT))
			return false;
		if (m_state[0] >= 3)
			return true;
		else
			return false;
	};

	public boolean canMoveA_6_Alone() {
		if (NotSameSide(P6, LIGHT))
			return false;
		if (m_state[0] >= 6)
			return true;
		else
			return false;
	};

	public boolean canMoveA_8_Alone() {
		if (NotSameSide(P8, LIGHT))
			return false;
		if (m_state[0] >= 8)
			return true;
		else
			return false;
	};

	public boolean canMoveA_12_Alone() {
		if (NotSameSide(P12, LIGHT))
			return false;
		if (m_state[0] >= 12)
			return true;
		else
			return false;
	}

	public boolean canMoveA_1_With_3() {
		return canMoveA_1_Alone() && canMoveA_3_Alone();
	}

	public boolean canMoveA_1_With_6() {
		return canMoveA_1_Alone() && canMoveA_6_Alone();
	}

	public boolean canMoveA_1_With_8() {
		return canMoveA_1_Alone() && canMoveA_8_Alone();
	}

	public boolean canMoveA_1_With_12() {
		return canMoveA_1_Alone() && canMoveA_12_Alone();
	}

	public boolean canMoveA_3_With_6() {
		return canMoveA_3_Alone() && canMoveA_6_Alone();
	}

	public boolean canMoveA_3_With_8() {
		return canMoveA_3_Alone() && canMoveA_8_Alone();
	}

	public boolean canMoveA_3_With_12() {
		return canMoveA_3_Alone() && canMoveA_12_Alone();
	}

	public boolean canMoveA_6_With_8() {
		return canMoveA_6_Alone() && canMoveA_8_Alone();
	}

	public boolean canMoveA_6_With_12() {
		return canMoveA_6_Alone() && canMoveA_12_Alone();
	}

	public boolean canMoveA_8_With_12() {
		return canMoveA_8_Alone() && canMoveA_12_Alone();
	}

	public GameState moveA_1_Alone() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P1] = invertDirection(state[P1]);
		state[SECOND_LEFT] = state[SECOND_LEFT] - 1;
		state[LIGHT] = invertDirection(state[LIGHT]);

		return new GameState(state);

	};

	public GameState moveA_3_Alone() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P3] = invertDirection(state[P3]);
		state[SECOND_LEFT] = state[SECOND_LEFT] - 3;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);

	};

	public GameState moveA_6_Alone() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P6] = invertDirection(state[P6]);
		state[SECOND_LEFT] = state[SECOND_LEFT] - 6;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);

	};

	public GameState moveA_8_Alone() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P8] = invertDirection(state[P8]);
		state[SECOND_LEFT] = state[SECOND_LEFT] - 8;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);

	};

	public GameState moveA_12_Alone() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P12] = invertDirection(state[P12]);
		state[SECOND_LEFT] = state[SECOND_LEFT] - 12;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);

	}

	public GameState moveA_1_With_3() {

		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P1] = invertDirection(state[P1]);
		state[P3] = invertDirection(state[P3]);

		state[SECOND_LEFT] = state[SECOND_LEFT] - 3;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);

	}

	public GameState moveA_1_With_6() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P1] = invertDirection(state[P1]);
		state[P6] = invertDirection(state[P6]);

		state[SECOND_LEFT] = state[SECOND_LEFT] - 6;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);

	}

	public GameState moveA_1_With_8() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P1] = invertDirection(state[P1]);
		state[P8] = invertDirection(state[P8]);

		state[SECOND_LEFT] = state[SECOND_LEFT] - 8;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);

	}

	public GameState moveA_1_With_12() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P1] = invertDirection(state[P1]);
		state[P12] = invertDirection(state[P12]);

		state[SECOND_LEFT] = state[SECOND_LEFT] - 12;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);

	}

	public GameState moveA_3_With_6() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P3] = invertDirection(state[P3]);
		state[P6] = invertDirection(state[P6]);

		state[SECOND_LEFT] = state[SECOND_LEFT] - 6;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);
	}

	public GameState moveA_3_With_8() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P3] = invertDirection(state[P3]);
		state[P8] = invertDirection(state[P8]);

		state[SECOND_LEFT] = state[SECOND_LEFT] - 8;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);
	}

	public GameState moveA_3_With_12() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P3] = invertDirection(state[P3]);
		state[P12] = invertDirection(state[P12]);

		state[SECOND_LEFT] = state[SECOND_LEFT] - 12;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);
	}

	public GameState moveA_6_With_8() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P6] = invertDirection(state[P6]);
		state[P8] = invertDirection(state[P8]);

		state[SECOND_LEFT] = state[SECOND_LEFT] - 8;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);
	}

	public GameState moveA_6_With_12() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P6] = invertDirection(state[P6]);
		state[P12] = invertDirection(state[P12]);

		state[SECOND_LEFT] = state[SECOND_LEFT] - 12;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);
	}

	public GameState moveA_8_With_12() {
		int[] state = new int[7];
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		state[P8] = invertDirection(state[P8]);
		state[P12] = invertDirection(state[P12]);

		state[SECOND_LEFT] = state[SECOND_LEFT] - 12;
		state[LIGHT] = invertDirection(state[LIGHT]);
		return new GameState(state);
	}

	public String toString() {
		String result = "";
		result += "Second left " + m_state[0] + " Light = " + m_state[1] + " : ";
		for (int i = 2; i < 7; i++) {
			result += " " + m_state[i] + " ";
		}
		return result;
	}

}
