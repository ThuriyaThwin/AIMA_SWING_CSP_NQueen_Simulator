package lab3.EightPuzzle;


public class PuzzleState {
	int[]  m_state = {0,1,2,//
			3,4,5,//
			6,7,8};
	
	public static final int[]  m_goal_state = {1,2,3,//
											4,0,5,//
											6,7,8};//

	
	
	public PuzzleState(int state[]){
		System.arraycopy(state, 0, m_state, 0, state.length);
	}
	public PuzzleState() {}
	int getX(int index){
		return index%3;
	}
	int getY(int index){
		return index/3;
	}
	int convertToOneD(int x, int y){
		return 3*y + x;
	}
	void swap(int index1, int index2){
		int temp = m_state[index1];
		m_state[index1] = m_state[index2];
		m_state[index2] = temp;
	}
	int getIndexOfEmpty(){
		int index = 0;
		for(index = 0; index <= 8; index++){
			if(m_state[index] == 0){
				break;
			}
		}
		
		return index;
	}
	
	int getIndexOfTile(int tile){
		int index = 0;
		for(index = 0; index <= 8; index++){
			if(m_state[index] == tile){
				break;
			}
		}
		
		return index;
	}
	boolean canMoveLeft(){
		int eIndex = getIndexOfEmpty();
		int x = getX(eIndex);
		if(x > 0) return true;
		else return false;
			
	}
	boolean canMoveRight(){
		int eIndex = getIndexOfEmpty();
		int x = getX(eIndex);
		if(x < 2) return true;
		else return false;	
	}
	boolean canMoveUp(){
		int eIndex = getIndexOfEmpty();
		int y = getY(eIndex);
		if(y > 0) return true;
		else return false;
	}
	boolean canMoveDown(){
		int eIndex = getIndexOfEmpty();
		int y = getY(eIndex);
		if(y < 2) return true;
		else return false;
	}
	
	PuzzleState moveLeft(){
		int eIndex = getIndexOfEmpty();
		int x = getX(eIndex);
		int y = getY(eIndex);
		int newX = x -1;
		int newY = y;
		int newIndex = convertToOneD(newX, newY);
		PuzzleState newState = new PuzzleState(m_state);
		newState.swap(eIndex, newIndex);
		return newState;
		
	}
	PuzzleState moveRight(){
		int eIndex = getIndexOfEmpty();
		int x = getX(eIndex);
		int y = getY(eIndex);
		int newX = x +1;
		int newY = y;
		int newIndex = convertToOneD(newX, newY);
		PuzzleState newState = new PuzzleState(m_state);
		newState.swap(eIndex, newIndex);
		return newState;
	}
	PuzzleState moveUp(){
		int eIndex = getIndexOfEmpty();
		int x = getX(eIndex);
		int y = getY(eIndex);
		int newX = x;
		int newY = y-1;
		int newIndex = convertToOneD(newX, newY);
		PuzzleState newState = new PuzzleState(m_state);
		newState.swap(eIndex, newIndex);
		return newState;
	}
	PuzzleState moveDown(){
		int eIndex = getIndexOfEmpty();
		int x = getX(eIndex);
		int y = getY(eIndex);
		int newX = x;
		int newY = y+1;
		int newIndex = convertToOneD(newX, newY);
		PuzzleState newState = new PuzzleState(m_state);
		newState.swap(eIndex, newIndex);
		return newState;
	}
	
	public String toString(){
		String result = "";
		
		for(int y=0; y<3; y++){
			for(int x=0; x < 3; x++){
				int index = convertToOneD(x,y);
				result += "\t" + m_state[index];				
			}
			result += "\n";
			
		}
		return result;
	}
}
