package lab2.EightPuzzle;

import aima.core.util.datastructure.XYLocation;

public class EightPuzzleObject {
	public String		name;
	public XYLocation	location;
	public int			id;
	
	public EightPuzzleObject(int id, XYLocation xy) {
		this.id  =id;
		this.location  =xy;
	}	
}
