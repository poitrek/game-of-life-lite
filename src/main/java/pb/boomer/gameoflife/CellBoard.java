package pb.boomer.gameoflife;
//import java.util.ArrayList;

public class CellBoard {
	int width;
	int height;
	
//	ArrayList<ArrayList<SingleCell>> cells;
	SingleCell cells[][];
	
	static char DEAD_CHAR = ' ';
	static char ALIVE_CHAR = '*';
//	public CellBoard() {
//		
//	}
	public CellBoard(int width, int height) {
		initialize(width, height);
	}
	
	public void initialize(int width, int height) {
		this.height = height;
		this.width = width;
//		cells = new ArrayList<>(height);
		cells = new SingleCell[this.height][this.width];
		for (int i = 0; i < this.height; i++)
			for (int j = 0; j < this.width; j++)
				cells[i][j] = new SingleCell();
		
//		for (int i = 0; i < height; i++) {
//			ArrayList<SingleCell> arr = new ArrayList<>();
//			for (int j = 0; j < width; j++) {
//				arr.add(new SingleCell());
//			}
//			cells.add(arr);
//		}
		
		// Set cells' neighboors
		for (int i = 1; i < this.height - 1; i ++)
			for (int j = 1; j < this.width - 1; j++) {
				cells[i][j].addNeighboor(cells[i-1][j-1]);
				cells[i][j].addNeighboor(cells[i-1][j]);
				cells[i][j].addNeighboor(cells[i-1][j+1]);
				cells[i][j].addNeighboor(cells[i][j-1]);
				cells[i][j].addNeighboor(cells[i][j+1]);
				cells[i][j].addNeighboor(cells[i+1][j-1]);
				cells[i][j].addNeighboor(cells[i+1][j]);
				cells[i][j].addNeighboor(cells[i+1][j+1]);
			}
	}
	
	public void startCell(int col, int row) {
		cells[row][col].setState(SingleCell.State.ALIVE);
	}
	
	public void drawBoat(int col, int row) {
		startCell(col, row+1);
		startCell(col+1, row);
		startCell(col+2, row);
		startCell(col+3, row+1);
		startCell(col+1, row+2);
		startCell(col+2, row+2);
	}
	
	public void update() {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				cells[i][j].generateNewState();
			}

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				cells[i][j].updateState();
			}
	}
	
	public SingleCell.State[][] getStates(){
		SingleCell.State states[][] = new SingleCell.State[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				states[i][j] = cells[i][j].getState();
			}
		return states;
	}
	
	public void print() {
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++) {
				if (cells[i][j].getState() == SingleCell.State.ALIVE)
					System.out.print(ALIVE_CHAR);
				else
					System.out.print(DEAD_CHAR);
			}
			System.out.println();
		}
		System.out.println();
	}
	

}