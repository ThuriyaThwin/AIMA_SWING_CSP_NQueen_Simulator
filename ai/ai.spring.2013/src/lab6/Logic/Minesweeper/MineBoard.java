package lab6.Logic.Minesweeper;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import lab6.Logic.UseCNFConverter;

public class MineBoard {

	public String[][]	data_hidden;

	public String[][]	data_solution;

	public int			board_width		= -1;
	public int			board_height	= -1;

	public MineBoard(String file, final boolean isFile) {
		String data = file;
		if (isFile)
			file = MineBoard.getFileData(file);

		String x = file;
		String xLine[] = x.split("\n");

		int w = 0;
		int h = 0;
		for (String l : xLine) {
			l = l.replaceAll(" ", "");
			if (l.length() == 0)
				continue;
			if (l.length() > w)
				w = l.replaceAll(" ", "").length();
			h++;
		}
		x = "w=" + w + "\n" + "h=" + h + "\n" + file;

		this.getKB(file);

		this.print();

		this.doPermuatation();
	}

	public MineBoard(final int width, final int height, final int n_mine, final int hiden_more) {
		this.board_height = height;
		this.board_width = width;

		if ((width * height) < n_mine) {
			System.out.println("dont have space for " + n_mine + " mine");
			return;
		}
		this.hidden_mine = new HashSet<String>();
		this.data_hidden = new String[this.board_width][this.board_height];
		this.data_solution = new String[this.board_width][this.board_height];
		Set<String> s = new HashSet<String>();

		while (s.size() < n_mine) {
			Random rand = new Random();
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			if (s.add(x + ";" + y))
				this.data_hidden[x][y] = "*";
		}

		for (int height_index = 0; height_index < height; height_index++)
			for (int width_index = 0; width_index < width; width_index++)
				this.setNumArround(height_index, width_index);

		// copy to data_solution
		for (int height_index = 0; height_index < height; height_index++)
			for (int width_index = 0; width_index < width; width_index++)
				this.data_solution[width_index][height_index] = this.data_hidden[width_index][height_index];

		System.out.println("n_mine + hiden_more= " + (n_mine + hiden_more));
		while (s.size() < (n_mine + hiden_more)) {
			Random rand = new Random();
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);

			try {
				int v = Integer.parseInt(this.data_hidden[x][y]);
				if (s.add(x + ";" + y))
					this.data_hidden[x][y] = "*";
			} catch (Exception mE) {

			}
		}
		for (String mString : s)
			System.out.println(mString);

		String pattern = "ABCDEFGHIJKLMNOPQRSTUWXYZ";
		int counter = 0;
		for (int height_index = 0; height_index < height; height_index++)
			for (int width_index = 0; width_index < width; width_index++)
				if (this.data_hidden[width_index][height_index] != null)
					if (this.data_hidden[width_index][height_index].contains("*")) {
						this.data_hidden[width_index][height_index] = String.valueOf(pattern.charAt(counter++));
						this.hidden_mine.add(this.data_hidden[width_index][height_index]);
					}
		this.print();

		this.doPermuatation();
	}

	public void setNumArround(final int height_value, final int width_value) {
		if (this.data_hidden[width_value][height_value] != null)
			if (this.data_hidden[width_value][height_value].equals("*"))
				return;

		int dt_mine = 0;
		for (int height_index = height_value - 1; height_index <= (height_value + 1); height_index++)
			for (int width_index = width_value - 1; width_index <= (width_value + 1); width_index++) {
				if ((height_index == height_value) && (width_value == width_index))
					continue;
				try {
					if (this.data_hidden[width_index][height_index] == null)
						continue;
				} catch (Exception ex) {
					// ex.printStackTrace();
					continue;
				}

				try {
					if (this.data_hidden[width_index][height_index].equals("*"))
						dt_mine++;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		if (dt_mine != 0) {
			this.data_hidden[width_value][height_value] = String.valueOf(dt_mine);
			System.out.println(dt_mine);
		}
	}

	public static void main(final String[] a) {
		System.out.println(MineBoard.flattenOr("A v -B v A v A v -A v A v B v C v D v D vD vD"));
	}

	public List<String>	logic;
	public List<String>	logic_view;
	public Set<String>	hidden_mine;

	private void doPermuatation() {
		System.out.println("-------DO PERMUATATION---------");

		this.logic = new ArrayList<String>();
		this.logic_view = new ArrayList<String>();
		for (int i = 0; i < this.board_height; i++)
			for (int j = 0; j < this.board_width; j++) {
				List<String> a = this.getPermuatationAt(j, i);
				String combine = "";
				String combine_view = "";
				if (a != null) {
					int counter = 0;

					for (String c : a) {
						System.out.println(c);

						combine_view += "(" + c + ") v ";

						c = UseCNFConverter.parseAnd(c);
						c = "(" + c + ")";

						if (counter++ == 0)
							combine += c;
						else
							combine = "(" + combine + " v " + c + ")";
					}
					if (a.size() == 1)
						combine = a.get(0);

					combine_view = combine_view.substring(0, combine_view.length() - 3);
					if (a.size() == 1)
						combine_view = combine_view.substring(1, combine_view.length() - 1);

				}

				if (!this.logic.contains(combine) && (combine.length() > 0))
					this.logic.add(combine);

				if (!this.logic_view.contains(combine_view) && (combine_view.length() > 0))
					this.logic_view.add(combine_view);
			}
	}

	private void print() {
		System.out.println("-------- DATA PRESENTATION ---------");
		for (int height_index = 0; height_index < this.board_height; height_index++) {
			for (int width_index = 0; width_index < this.board_width; width_index++)
				if (this.data_hidden[width_index][height_index] == null)
					System.out.print(" ");
				else
					System.out.print(this.data_hidden[width_index][height_index]);
			System.out.println();
		}
	}

	public List<String> getPermuatationAt(final int w, final int h) {
		String r = "";

		int parseNumber = -1;
		try {
			parseNumber = Integer.parseInt(this.data_hidden[w][h]);
			if (parseNumber <= 0)
				return null;
		} catch (Exception ex) {
			return null;
		}

		for (int x = w - 1; x <= (w + 1); x++)
			for (int y = h - 1; y <= (h + 1); y++) {
				if ((x == w) && (h == y))
					continue;
				try {
					if (this.data_hidden[x][y] == null)
						continue;
				} catch (Exception ex) {
					continue;
				}

				try {

					int valueInt = Integer.parseInt(this.data_hidden[x][y]);

				} catch (Exception e) {

					r += this.data_hidden[x][y];

				}
			}
		System.out.println("parseNumber = " + r);
		if (r.length() == 0)
			return null;
		return XPermutation.getPermutation(r, parseNumber);
	}

	public static int getN(final int n) {

		int j = 1;
		for (int i = 1; i <= n; i++)
			j *= i;
		return j;
	}

	public String getHtml() {

		StringBuilder sb = new StringBuilder("<html><H1>Data presentation</H1><table border=\"0\">");
		for (int height_idx = 0; height_idx < this.board_height; height_idx++) {
			sb.append("<tr>");
			for (int width_idx = 0; width_idx < this.board_width; width_idx++)
				sb.append("<td text-align: center " + this.drawColor(this.data_hidden, width_idx, height_idx) + " height=\"25\" width=\"25\" >" + (this.isType(this.data_hidden, width_idx, height_idx) == null ? "" : "<font size=\"4\" " + (this.isType(this.data_hidden, width_idx, height_idx) ? " color=\"yellow\">" : " color=\"white\">") + this.data_hidden[width_idx][height_idx] + "</font>") + "</td>");
			sb.append("\n</tr>");

		}

		sb.append("</table></html>");
		return sb.toString();

	}

	public String getSolutionHtml() {

		StringBuilder sb = new StringBuilder("<html><H1>PEEK DATA</H1><table border=\"0\">");

		if (this.data_solution != null)
			for (int height_idx = 0; height_idx < this.board_height; height_idx++) {
				sb.append("<tr>");
				for (int width_idx = 0; width_idx < this.board_width; width_idx++)
					sb.append("<td text-align: center " + this.drawColor(this.data_solution, width_idx, height_idx) + " height=\"25\" width=\"25\" >" + (this.isType(this.data_solution, width_idx, height_idx) == null ? "" : "<font size=\"4\" " + (this.isType(this.data_solution, width_idx, height_idx) ? " color=\"yellow\">" : " color=\"white\">") + this.data_solution[width_idx][height_idx] + "</font>") + "</td>");
				sb.append("\n</tr>");

			}
		else
			sb = new StringBuilder("<html><H2>Can not peek mine map beacause of read data from file</H2>");

		sb.append("</table></html>");
		return sb.toString();

	}

	private Boolean isType(final String data[][], final int width, final int height) {
		if (data[width][height] == null)
			return null;
		try {
			int a = Integer.parseInt(data[width][height]);

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	private String drawColor(final String data[][], final int width_index, final int height_index) {

		if (data[width_index][height_index] == null)
			return "bgcolor=#81BEF7";
		try {
			int a = Integer.parseInt(data[width_index][height_index]);

			return "bgcolor=#613D2D";

		} catch (Exception e) {
			return "bgcolor=#EB5E00";
		}

	}

	public static String getFileData(final String file_data) {

		String r = "";
		try {
			FileInputStream fstream = new FileInputStream(file_data);

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;

			while ((strLine = br.readLine()) != null)
				r += "\n" + strLine;

			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return r;
	}

	private void getKB(final String data_stream) {

		String[] lines = data_stream.split("\n");

		if (this.data_hidden != null)
			return;
		try {

			int row = 0;

			for (String strLine : lines)
				try {
					// System.out.println();
					System.out.println(strLine);

					String line = strLine.trim().replaceAll(" ", "");
					line = line.replaceAll("\t", "");
					if (line.length() == 0)
						continue;

					if (this.board_width == -1)
						if (line.startsWith("w="))
							this.board_width = Integer.parseInt(line.substring("w=".length(), line.length()));
					if (this.board_height == -1)
						if (line.startsWith("h="))
							this.board_height = Integer.parseInt(line.substring("h=".length(), line.length()));
					if ((this.board_width != -1) && (this.board_height != -1))
						if (this.data_hidden == null) {
							this.data_hidden = new String[this.board_width][this.board_height];
							this.hidden_mine = new HashSet<String>();
							continue;
						}
					if (this.data_hidden != null) {
						for (int i = 0; i < line.length(); i++)
							this.data_hidden[i][row] = String.valueOf(line.subSequence(i, i + 1));

						row++;
					}

					String pattern = "QWERTYUIOPASDFGHJKLZXCVBNM";
					for (int mI = 0; mI < pattern.length(); mI++)
						if (strLine.contains(String.valueOf(pattern.charAt(mI))))
							this.hidden_mine.add(String.valueOf(pattern.charAt(mI)));
				} catch (Exception e) {
					e.printStackTrace();
				}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public String getHtmlLogic() {

		System.out.println("------- FINAL LOGIC------------");
		int count = 1;

		StringBuilder sb = new StringBuilder("<html><H2>Logic</H2><table border=\"0\">");

		sb.append("  <tr bgcolor=\"#5BC5FA\">\n" + //
				"    <th ><H4>Nature</H4> </th>\n" + //
				"    <th  ><H4>AIMA Binary Pair</H4></th>\n" + //
				"  </tr>"); //
		for (String l : this.logic_view) {

			sb.append("<tr>");
			sb.append("<td bgcolor=#CEECF5 > " + "<H5>" + count + ". " + l + "</H5>" + "</td>");

			String k = UseCNFConverter.parseAnd(this.logic.get(count - 1));
			k = UseCNFConverter.toEngine(k);
			sb.append("<td bgcolor=#CEECF5 > " + "<H5>" + k + "</H5>" + "</td>");
			sb.append("</tr>");

			System.out.println(count + ". " + l);
			count++;
		}

		sb.append("</table></html>");
		return sb.toString();
	}

	public List<String> getCNF() {
		List<String> g = new ArrayList<String>();
		for (String k : this.logic) {
			k = UseCNFConverter.parseAnd(k);
			k = UseCNFConverter.toEngine(k);
			g.add(k);
		}
		return g;
	}

	public String getHtmlCNF() {

		int counter_logic1 = 1;
		int count_cnf = 1;

		String content_html = "<html><H2>Convert Sentences to Conjunctive Normal Form</H2>\n" + //
				"<table border=\"0\">\n" + //
				"  <tr bgcolor=\"#5BC5FA\">\n" + //
				"    <th >Sentences </th>\n" + //
				"    <th  >CNF</th>\n" + //
				"  </tr>"; //

		for (String k : this.logic) {

			k = UseCNFConverter.parseAnd(k);
			k = UseCNFConverter.toEngine(k);

			System.out.println("___________________________________________________________________");
			System.out.println(counter_logic1 + ". " + k);
			System.out.println("-------------------------------------------------------------------");
			Set<String> cnf = UseCNFConverter.getCNF(k);
			int index = 0;
			for (String c : cnf) {
				System.out.println(count_cnf + ". CNF: " + c + " >>> " + UseCNFConverter.toNatural(c));
				if (index++ == 0) {

					content_html += "\n" + "<tr bgcolor=\"#BFE6FE\">";
					content_html += "\n" + " <td bgcolor=\"#FAB4B4\">" + (counter_logic1 + ". " + this.logic_view.get(counter_logic1 - 1)) + "</td> ";
					content_html += "\n" + " <td>" + (count_cnf + ". " + UseCNFConverter.toNatural(c)) + "</td>";
					content_html += "\n" + "</tr>";

				}
				else {

					content_html += "\n" + "<tr bgcolor=\"#BFE6FE\">";
					content_html += "\n" + " <td bgcolor=\"#FFFFFF\"></td> ";
					content_html += "\n" + " <td>" + (count_cnf + ". " + UseCNFConverter.toNatural(c)) + "</td>";
					content_html += "\n" + "</tr>";
				}
				count_cnf++;
			}
			counter_logic1++;
		}
		content_html += "</table></html>";
		// System.out.println(content_html);
		return content_html;
	}

	public static String flattenOr(String s) {
		s = s.replaceAll(" ", "");

		String[] subOr = s.split("v");

		List<String> list = new ArrayList<String>();
		for (String o : subOr)
			if (!list.contains(o))
				list.add(o);

		Set<String> remove = new HashSet<String>();

		for (int i = 0; i < list.size(); i++)
			for (int j = 0; j < list.size(); j++) {
				if (i == j)
					continue;
				String x1 = list.get(i);
				String x2 = list.get(j);

				if (x1 == null)
					continue;
				if (x2 == null)
					continue;

				if (x1.equals(x2)) {
					remove.add(x1);
					continue;
				}
				String content_x1 = x1.replaceAll("-", "");
				String content_x2 = x2.replaceAll("-", "");

				System.out.println("content_x1 =" + content_x1 + "&" + x1);
				System.out.println("content_x2 =" + content_x2 + "&" + x2);
				System.out.println();
				if (content_x1.equals(content_x2)) {
					remove.add(x1);
					remove.add(x2);

					System.out.println("set null");
				}
			}

		System.out.println("-----remove----");
		for (String mString : remove)
			System.out.println(mString);
		System.out.println("------result----- ");
		String result = "";
		for (int i = 0; i < list.size(); i++)
			if (!remove.contains(list.get(i)))
				result += list.get(i) + " v ";
		if (result.length() >= 3)
			result = result.substring(0, result.length() - 3);
		return result;
	}

}
