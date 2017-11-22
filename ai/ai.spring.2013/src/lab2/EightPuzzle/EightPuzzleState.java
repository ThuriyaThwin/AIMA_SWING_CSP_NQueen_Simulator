package lab2.EightPuzzle;

import aima.core.util.datastructure.XYLocation;

public class EightPuzzleState {

	public int				m_state[][]			= { { 2,1,3 },//
													{ 4,7,6 },//
													{ 5,8,0 } };

	public static final int	m_state_goal[][]	= { { 1,2,3 },//
													{ 4,5,6},//
													{ 7,8,0 } };

	public EightPuzzleState(int state[][]) {
		System.arraycopy(state, 0, m_state, 0, state.length);
		System.out.println(toString());
	}

	public EightPuzzleState() {

		System.out.println(toString());

	}

	public XYLocation getLocation() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (m_state[i][j] == 0) { return new XYLocation(i, j); }
			}
		}
		return null;
	}
	public XYLocation getLocation2() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (m_state[i][j] == 0) { return new XYLocation(j, i); }
			}
		}
		return null;
	}
	public static void main(String[] a) {
		EightPuzzleState s = new EightPuzzleState();

		System.out.println(s.canGoUp());
		System.out.println(s.canGoDown());
		System.out.println(s.canGoLeft());
		System.out.println(s.canGoRight());

//		s.moveUp();
//		s.moveLeft();
//		s.moveDown();
		s.moveRight();
		//s.moveLeft();
	}

	public boolean canGoUp() {
		if (getLocation2().getYCoOrdinate() == 0)
			return false;
		else
			return true;
	}

	public boolean canGoDown() {
		if (getLocation2().getYCoOrdinate() == 2)
			return false;
		else
			return true;
	}

	public boolean canGoLeft() {

		if (getLocation2().getXCoOrdinate() == 0)
			return false;
		else
			return true;
	}

	public boolean canGoRight() {
		if (getLocation2().getXCoOrdinate() == 2)
			return false;
		else
			return true;
	}

	EightPuzzleState moveUp() {

		XYLocation xy = getLocation();
		System.out.println("xy=" + xy); 
		int state[][]=new int[3][3];		
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		
		int s_value = state[xy.getXCoOrdinate()-1][xy.getYCoOrdinate()];
		state[xy.getXCoOrdinate()-1][xy.getYCoOrdinate()] = 0;
		state[xy.getXCoOrdinate()][xy.getYCoOrdinate()] = s_value;
	
		return new EightPuzzleState(state);
	}

	EightPuzzleState moveDown() {
		XYLocation xy = getLocation();
		System.out.println("xy=" + xy); 
		int state[][]=new int[3][3];		
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		
		int s_value = state[xy.getXCoOrdinate()+1][xy.getYCoOrdinate()];
		state[xy.getXCoOrdinate()+1][xy.getYCoOrdinate()] = 0;
		state[xy.getXCoOrdinate()][xy.getYCoOrdinate()] = s_value;
		
		return new EightPuzzleState(state);
	}

	EightPuzzleState moveLeft() {
		XYLocation xy = getLocation();
		System.out.println("xy=" + xy); 
		
		int state[][]=new int[3][3];		
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		
		int s_value = state[xy.getXCoOrdinate()][xy.getYCoOrdinate()-1];
		state[xy.getXCoOrdinate()][xy.getYCoOrdinate()-1] = 0;
		state[xy.getXCoOrdinate()][xy.getYCoOrdinate()] = s_value;
		return new EightPuzzleState(state);
	}

	EightPuzzleState moveRight() {
		XYLocation xy = getLocation();
		System.out.println("xy=" + xy); 
		int state[][]=new int[3][3];		
		System.arraycopy(m_state, 0, state, 0, m_state.length);
		int s_value = state[xy.getXCoOrdinate()][xy.getYCoOrdinate()+1];
		state[xy.getXCoOrdinate()][xy.getYCoOrdinate()+1] = 0;
		state[xy.getXCoOrdinate()][xy.getYCoOrdinate()] = s_value;
		return new EightPuzzleState(state);
	}

	public String toString() {
		String result = "";
		result += "\n" + m_state[0][0] + " " + m_state[0][1] + " " + m_state[0][2];
		result += "\n" + m_state[1][0] + " " + m_state[1][1] + " " + m_state[1][2];
		result += "\n" + m_state[2][0] + " " + m_state[2][1] + " " + m_state[2][2];
		return result;
	}
}
