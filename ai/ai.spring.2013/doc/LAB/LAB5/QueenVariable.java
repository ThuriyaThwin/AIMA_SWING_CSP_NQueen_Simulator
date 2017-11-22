package csp;
import aima.core.search.csp.Variable;

public class QueenVariable extends Variable {
	public int index;
	
	public QueenVariable(String name, int index){
		super(name);
		this.index = index;
	}
}
