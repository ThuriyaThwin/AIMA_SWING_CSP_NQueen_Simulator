package lab2.NQueensPuzzle;

public class NQueenState {

	public int[]	board;

	public NQueenState(int nqueen) {
		board = new int[nqueen];
		for (int i = 0; i < nqueen; i++) {
			board[i] = 0;
		}
	}

	public NQueenState move(int queen, int y) {
		NQueenState s = new NQueenState(board.length);
		for (int i = 0; i < board.length; i++) {
			s.board[i] = board[i];
		}
		s.board[queen] = y;
		return s;
	}
}
