package lab4.NQueen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aima.core.util.datastructure.XYLocation;

public class NQueensState {
	int[][]	squares;

	int		size;

	public NQueensState(int n) {
		size = n;
		squares = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				squares[i][j] = 0;
			}
		}
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				squares[i][j] = 0;
			}
		}
	}

	public void setRandomBoard() {
		clear();
		Random rd = new Random();
		for (int x = 0; x < size; x++) {
			int y = rd.nextInt(size);
			squares[x][y] = 1;
		}
	}

	public void setBoard(List<XYLocation> al) {
		clear();
		for (int i = 0; i < al.size(); i++) {
			addQueenAt(al.get(i));
		}
	}

	public int getSize() {
		return size;
	}

	public void addQueenAt(XYLocation l) {
		if (!(queenExistsAt(l)))
			squares[l.getXCoOrdinate()][l.getYCoOrdinate()] = 1;
	}

	public void removeQueenFrom(XYLocation l) {
		if (squares[l.getXCoOrdinate()][l.getYCoOrdinate()] == 1) {
			squares[l.getXCoOrdinate()][l.getYCoOrdinate()] = 0;
		}
	}

	public void moveQueenTo(XYLocation l) {
		for (int i = 0; i < size; i++)
			squares[l.getXCoOrdinate()][i] = 0;
		squares[l.getXCoOrdinate()][l.getYCoOrdinate()] = 1;
	}

	public void moveQueen(XYLocation from, XYLocation to) {
		if ((queenExistsAt(from)) && (!(queenExistsAt(to)))) {
			removeQueenFrom(from);
			addQueenAt(to);
		}
	}

	public boolean queenExistsAt(XYLocation l) {
		return (queenExistsAt(l.getXCoOrdinate(), l.getYCoOrdinate()));
	}

	private boolean queenExistsAt(int x, int y) {
		return (squares[x][y] == 1);
	}

	public int getNumberOfQueensOnBoard() {
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (squares[i][j] == 1)
					count++;
			}
		}
		return count;
	}

	public List<XYLocation> getQueenPositions() {
		ArrayList<XYLocation> result = new ArrayList<XYLocation>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (queenExistsAt(i, j))
					result.add(new XYLocation(i, j));
			}
		}
		return result;

	}

	public int getNumberOfAttackingPairs() {
		int result = 0;
		for (XYLocation location : getQueenPositions()) {
			result += getNumberOfAttacksOn(location);
		}
		return result / 2;
	}

	public int getNumberOfAttacksOn(XYLocation l) {
		int x = l.getXCoOrdinate();
		int y = l.getYCoOrdinate();
		return numberOfHorizontalAttacksOn(x, y) + numberOfVerticalAttacksOn(x, y) + numberOfDiagonalAttacksOn(x, y);
	}

	public boolean isSquareUnderAttack(XYLocation l) {
		int x = l.getXCoOrdinate();
		int y = l.getYCoOrdinate();
		return (isSquareHorizontallyAttacked(x, y) || isSquareVerticallyAttacked(x, y) || isSquareDiagonallyAttacked(x, y));
	}

	private boolean isSquareHorizontallyAttacked(int x, int y) {
		return numberOfHorizontalAttacksOn(x, y) > 0;
	}

	private boolean isSquareVerticallyAttacked(int x, int y) {
		return numberOfVerticalAttacksOn(x, y) > 0;
	}

	private boolean isSquareDiagonallyAttacked(int x, int y) {
		return numberOfDiagonalAttacksOn(x, y) > 0;
	}

	private int numberOfHorizontalAttacksOn(int x, int y) {
		int retVal = 0;
		for (int i = 0; i < size; i++) {
			if ((queenExistsAt(i, y)))
				if (i != x)
					retVal++;
		}
		return retVal;
	}

	private int numberOfVerticalAttacksOn(int x, int y) {
		int retVal = 0;
		for (int j = 0; j < size; j++) {
			if ((queenExistsAt(x, j)))
				if (j != y)
					retVal++;
		}
		return retVal;
	}

	private int numberOfDiagonalAttacksOn(int x, int y) {
		int retVal = 0;
		int i;
		int j;
		// forward up diagonal
		for (i = (x + 1), j = (y - 1); ((i < size) && (j > -1)); i++, j--) {
			if (queenExistsAt(i, j))
				retVal++;
		}
		// forward down diagonal
		for (i = (x + 1), j = (y + 1); ((i < size) && (j < size)); i++, j++) {
			if (queenExistsAt(i, j))
				retVal++;
		}
		// backward up diagonal
		for (i = (x - 1), j = (y - 1); ((i > -1) && (j > -1)); i--, j--) {
			if (queenExistsAt(i, j))
				retVal++;
		}

		// backward down diagonal
		for (i = (x - 1), j = (y + 1); ((i > -1) && (j < size)); i--, j++) {
			if (queenExistsAt(i, j))
				retVal++;
		}

		return retVal;
	}

	@Override
	public int hashCode() {
		List<XYLocation> locs = getQueenPositions();
		int result = 17;
		for (XYLocation loc : locs) {
			result = 37 * loc.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if ((o == null) || (this.getClass() != o.getClass()))
			return false;
		NQueensState aBoard = (NQueensState) o;
		boolean retVal = true;
		List<XYLocation> locs = getQueenPositions();

		for (XYLocation loc : locs) {
			if (!(aBoard.queenExistsAt(loc)))
				retVal = false;
		}
		return retVal;
	}

	public void print() {
		System.out.println(getBoardPic());
	}

	public String getBoardPic() {
		StringBuffer buffer = new StringBuffer();
		for (int row = 0; (row < size); row++) { // row
			for (int col = 0; (col < size); col++) { // col
				if (queenExistsAt(col, row))
					buffer.append(" Q ");
				else
					buffer.append(" - ");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();

		for (int row = 0; row < size; row++) { // rows
			for (int col = 0; col < size; col++) { // columns
				if (queenExistsAt(col, row))
					buf.append('Q');
				else
					buf.append('-');
			}
			buf.append("\n");
		}

		return buf.toString();
	}

	public void println() {
		System.out.println(toString());
		System.out.println();
	}

	public String getHtml() {
		StringBuilder sb = new StringBuilder("<html><table border=\"0\">");

		for (int row = 0; row < size; row++) { // rows
			sb.append("<tr>");
			for (int col = 0; col < size; col++) { // columns
				boolean qe = queenExistsAt(col, row);

				sb.append("<td bgcolor=#153450 text-align: center height=\"25\" width=\"25\" >" + (!qe ? "" : "<font size=\"4\" color=\"white\">Q</font>") + "</td>");

			}
			sb.append("\n</tr>");
		}

		sb.append("</table></html>");
		return sb.toString();
	}

}
