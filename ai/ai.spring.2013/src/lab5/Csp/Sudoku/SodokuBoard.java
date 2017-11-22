package lab5.Csp.Sudoku;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;

public class SodokuBoard extends CSP {

	public SodokuBoard(SodokuVariableCollection listVariable) {
		super(listVariable);

		Domain domain = new Domain(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });

		for (Variable var : getVariables()) {
			if (var instanceof SodokuVariable) {
				if (((SodokuVariable) var).value != 0) {
					// set only one variable for fixed variable
					setDomain(var, new Domain(new Integer[] { ((SodokuVariable) var).value }));
				} else {
					// set 1->9 domain
					setDomain(var, domain);
				}
			}
		}
		int countConstraint = 1;
		System.out.println("addConstraint");
		for (int i = 0; i < 9; i++) {
			SodokuConstraint constraint_row = new SodokuConstraint();
			SodokuConstraint constraint_column = new SodokuConstraint();

			for (int j = 0; j < 9; j++) {
				constraint_row.add(listVariable.get(i, j));
				constraint_column.add(listVariable.get(j, i));
			}

			constraint_row.sortScope();
			constraint_column.sortScope();

			System.out.println(countConstraint++ + ". addConstraint row " + (i + 1));
			System.out.println(countConstraint++ + ". addConstraint column " + (i + 1));

			addConstraint(constraint_row);
			addConstraint(constraint_column);

		}

		//add constraint_square
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println(countConstraint++ + ". addConstraint square (" + i + "-" + j + ")");
				addConstraint(listVariable.constraint_square(i, j));
			}
		}

	}

	public void setSodokuListener(ISodokuListener i) {

		for (Constraint c : getConstraints()) {
			if (c instanceof SodokuConstraint) {
				SodokuConstraint constraint = (SodokuConstraint) c;
				constraint.setSodokuListener(i);
			}
		}
	}

	//	private static List<Variable>	variables;
	//
	//	private static List<Variable> collectVariables(String data) {
	//		if (variables == null) {
	//			variables = new ArrayList<Variable>();
	//			for (int i = 0; i < 9; i++) {
	//				for (int j = 0; j < 9; j++) {
	//					variables.add(new SodokuVariable(new XYLocation(i, j)));
	//				}
	//			}
	//			getSodokuData(data);
	//		}
	//
	//		return variables;
	//	}
	//
	//	private static void getSodokuData(String file_data) {
	//
	//		try {
	//			FileInputStream fstream = new FileInputStream(file_data);
	//
	//			DataInputStream in = new DataInputStream(fstream);
	//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
	//
	//			String strLine;
	//			int row = 0;
	//			while ((strLine = br.readLine()) != null) {
	//
	//				//System.out.println();
	//				System.out.println(strLine);
	//
	//				String line = strLine.trim().replace(" ", "");
	//				if (line.length() == 0)
	//					continue;
	//				for (int i = 0; i < line.length(); i++) {
	//					try {
	//						int word = Integer.parseInt(String.valueOf(line.subSequence(i, i + 1)));
	//						if (word != 0) {
	//							processCell(row, i, word);
	//						}
	//					} catch (Exception e) {
	//
	//					}
	//				}
	//				row++;
	//			}
	//
	//			in.close();
	//		} catch (Exception e) {
	//			System.err.println("Error: " + e.getMessage());
	//		}
	//	}
	//
	//	private static void processCell(int row, int column, int value) {
	//		System.out.println("process input cell at( " + row + "-" + column + ") value =  " + value);
	//
	//		SodokuVariable v = get(row, column);
	//		v.value = value;
	//		v.fixed = true;
	//	}
	//
	//	public static String getString() {
	//		StringBuilder sb = new StringBuilder();
	//		for (int i = 0; i < 9; i++) {
	//			for (int j = 0; j < 9; j++) {
	//				int dt = get(i, j).value;
	//				sb.append(dt == 0 ? "-" : dt);
	//				if (((j + 1) % 3) == 0)
	//					sb.append(" | ");
	//			}
	//			if (((i + 1) % 3) == 0)
	//				sb.append("\n----------------");
	//			sb.append("\n");
	//		}
	//		return sb.toString();
	//	}
	//
	//	public static SodokuVariable get(int x, int y) {
	//		for (Variable v : variables) {
	//			if (v instanceof SodokuVariable) {
	//				if ((((SodokuVariable) v).location.getXCoOrdinate() == x//
	//				) && (((SodokuVariable) v).location.getYCoOrdinate() == y))//
	//					return (SodokuVariable) v;
	//			}
	//		}
	//		return null;
	//	}	
	//	private static Constraint constraint_square(int x, int y) {
	//	SodokuConstraint constraint_square = new SodokuConstraint();
	//	int start_x = x * 3;
	//	int end_x = start_x + 3;
	//
	//	int start_y = y * 3;
	//	int end_y = start_y + 3;
	//
	//	for (int k1 = start_x; k1 < end_x; k1++) {
	//		for (int k2 = start_y; k2 < end_y; k2++) {
	//			SodokuVariable v = get(k1, k2);
	//			constraint_square.add(v);
	//		}
	//	}
	//
	//	constraint_square.sortScope();
	//
	//	return constraint_square;
	//}

}
