package lab5.Csp.NQueen;

import java.util.ArrayList;

import aima.core.search.csp.Variable;

public class NQueenVariableCollection extends ArrayList<Variable> {

	public NQueenVariableCollection(int numberOfQueen) {
		for (int i = 0; i < numberOfQueen; i++) {
			add(new QueenVariable("Q" + i, i));
		}
	}

	public void print() {
		for (int i = 0; i < size(); i++) {
			QueenVariable q = (QueenVariable) get(i);
			System.out.println("Queen " + q.index + " >>  q.location = " + q.location);
		}
	}

	public String getHtml() {
		StringBuilder sb = new StringBuilder("<html><table border=\"0\">");

		for (int i = 0; i < size(); i++) {
			sb.append("<tr>");
			for (int j = size() - 1; j >= 0; j--) {
				QueenVariable q = (QueenVariable) get(j);
				sb.append("<td bgcolor=#153450 text-align: center height=\"25\" width=\"25\" >" + (q.location != i ? "" : "<font size=\"4\" color=\"white\">Q</font>") + "</td>");
			}
			sb.append("\n</tr>");
		}

		sb.append("</table></html>");
		return sb.toString();
	}

}
