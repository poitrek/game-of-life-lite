package pb.boomer.gameoflife;
import java.util.List;
import java.util.ArrayList;

public class SingleCell {
	public static enum State {
			ALIVE, DEAD
	}
	
	public SingleCell() {
		this.currentState = State.DEAD;
		this.neighboors = new ArrayList<>();
	}
	private SingleCell.State currentState;
	private SingleCell.State newState;
	
	private List<SingleCell> neighboors;
	
	public SingleCell.State getState() {
		return currentState;
	}
	public void setState(SingleCell.State state) {
		this.currentState = state;
	}
	
	public void addNeighboor(SingleCell cell) {
		this.neighboors.add(cell);
	}
	
	/**
	 * curState  neighboors  newState
	 * alive	 0-1	  -> dead
	 * alive     4-8	  -> dead
	 * alive 	 2-3	  -> alive
	 * dead		 3		  -> alive
	 * dead		 =/= 3    -> dead
	 */
	public void generateNewState() {
		// Count alive neighboor cells
		int aliveCnt = 0;
		for (SingleCell ngb : this.neighboors) {
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
	
	public void updateState() {
		this.currentState = this.newState;
	}
}
