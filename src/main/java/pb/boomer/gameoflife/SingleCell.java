package pb.boomer.gameoflife;
import java.util.List;
import java.util.ArrayList;

public class SingleCell {
	
	public static enum State {
			ALIVE, DEAD
	}
	
	public SingleCell() {
		this.currentState = State.DEAD;
		this.neighbours = new ArrayList<>();
	}
	
	// Current state of the cell (ALIVE/DEAD)
	private SingleCell.State currentState;
	
	// The new state that is generated within cell update
	private SingleCell.State newState;
	
	// List of references to adjacent cells (neighbours)
	private List<SingleCell> neighbours;
	
	public SingleCell.State getState() {
		return currentState;
	}
	
	void setState(SingleCell.State state) {
		this.currentState = state;
	}
	
	void addNeighbour(SingleCell cell) {
		this.neighbours.add(cell);
	}
	
	void changeState() {
		if (this.currentState == State.ALIVE)
			this.currentState = State.DEAD;
		else
			this.currentState = State.ALIVE;
	}
	
	/**
	 * Rules:
	 * 
	 * curState  neighbours  newState
	 * alive	 0-1, 4-8 -> dead
	 * alive 	 2-3	  -> alive
	 * dead		 3		  -> alive
	 * dead		 =/= 3    -> dead
	 */
	void generateNewState() {
		// Count alive neighbour cells
		int aliveCnt = 0;
		for (SingleCell ngb : this.neighbours) {
			if (ngb.getState() == State.ALIVE)
				aliveCnt++;
		}
		if (this.currentState == State.ALIVE) {
			if (aliveCnt == 2 || aliveCnt == 3) {
				this.newState = State.ALIVE;
			}
			else
				this.newState = State.DEAD;
		}
		else {
			if (aliveCnt == 3)
				this.newState = State.ALIVE;
			else
				this.newState = State.DEAD;
		}
		
	}
	
	void updateState() {
		this.currentState = this.newState;
	}
}
