package lab2.WaterJugsProblem;

public class WaterJugState {

	public static final int	JUG3LITER	= 0;
	public static final int	JUG4LITER	= 1;

	public int				m_state[]	= { 0, 0 };

	public WaterJugState(int state[]) {
		System.arraycopy(state, 0, m_state, 0, state.length);

		if (m_state[JUG3LITER] > 3)
			m_state[JUG3LITER] = 3;
		if (m_state[JUG3LITER] < 0)
			m_state[JUG3LITER] = 0;

		if (m_state[JUG4LITER] > 4)
			m_state[JUG4LITER] = 4;
		if (m_state[JUG4LITER] < 0)
			m_state[JUG4LITER] = 0;

		System.out.println(toString());
	}

	WaterJugState jug4to3() {

		int j3 = m_state[JUG3LITER] + m_state[JUG4LITER];
		int j4 = m_state[JUG4LITER] - (3 - m_state[JUG3LITER]);

		int newState[] = { j3, j4 };
		System.out.println(" jug4to3 ");
		return new WaterJugState(newState);
	}

	WaterJugState jug3to4() {
		int j4 = m_state[JUG4LITER] + (m_state[JUG3LITER]);
		int j3 = m_state[JUG3LITER] - (4 - m_state[JUG4LITER]);
		System.out.println(" jug3to4 ");
		int newState[] = { j3, j4 };
		return new WaterJugState(newState);
	}

	WaterJugState fillin3() {
		int j3 = 3;
		int j4 = m_state[JUG4LITER];
		System.out.println(" fill in 3 ");
		int newState[] = { j3, j4 };
		return new WaterJugState(newState);
	}

	WaterJugState fillin4() {
		int j3 = m_state[JUG3LITER];
		int j4 = 4;
		System.out.println(" fill in 4 ");
		int newState[] = { j3, j4 };
		return new WaterJugState(newState);
	}

	WaterJugState fillout3() {
		int j3 = 0;
		int j4 = m_state[JUG4LITER];
		System.out.println(" fill out 3 ");
		int newState[] = { j3, j4 };
		return new WaterJugState(newState);
	}

	WaterJugState fillout4() {
		int j3 = m_state[JUG3LITER];
		int j4 = 0;
		System.out.println(" fill out 4 ");
		int newState[] = { j3, j4 };
		return new WaterJugState(newState);
	}

	public String toString() {
		String result = "";
		result = "[ JUG 3 LITER = " + m_state[JUG3LITER] + "; " + "JUG 4 LITRE = " + m_state[JUG4LITER] + "]";
		return result;
	}

	public boolean canJug4toJug3() {
		boolean condi = canFillInJug3Liter() && canFillOutJug4Liter();
		if (condi)
			return true;
		return false;
	}

	public boolean canJug3toJug4() {
		boolean condi = canFillInJug4Liter() && canFillOutJug3Liter();
		if (condi)
			return true;
		return false;
	}

	public boolean canFillInJug3Liter() {
		boolean condi = (m_state[JUG3LITER] < 3);
		if (condi)
			return true;
		return false;
	}

	public boolean canFillInJug4Liter() {
		boolean condi = (m_state[JUG4LITER] < 4);
		if (condi)
			return true;
		return false;
	}

	public boolean canFillOutJug3Liter() {
		boolean condi = (m_state[JUG3LITER] > 0);
		if (condi)
			return true;
		return false;
	}

	public boolean canFillOutJug4Liter() {
		boolean condi = (m_state[JUG4LITER] > 0);
		if (condi)
			return true;
		return false;
	}
}
