package pb.boomer.gameoflife;
//import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;


public class CellBoard {
	
	int width;
	int height;
	
//	ArrayList<ArrayList<SingleCell>> cells;
	SingleCell cells[][];
	
	
	public CellBoard(int width, int height) {
		initialize(width, height);
	}
	
	public void initialize(int width, int height) {
		this.height = height;
		this.width = width;
		cells = new SingleCell[this.height][this.width];
		for (int i = 0; i < this.height; i++)
			for (int j = 0; j < this.width; j++)
				cells[i][j] = new SingleCell();
		
//		cells = new ArrayList<>(height);
//		for (int i = 0; i < height; i++) {
//			ArrayList<SingleCell> arr = new ArrayList<>();
//			for (int j = 0; j < width; j++) {
//				arr.add(new SingleCell());
//			}
//			cells.add(arr);
//		}
		
		// Set cells' neighbours
		for (int i = 1; i < this.height - 1; i ++)
			for (int j = 1; j < this.width - 1; j++) {
				cells[i][j].addNeighbour(cells[i-1][j-1]);
				cells[i][j].addNeighbour(cells[i-1][j]);
				cells[i][j].addNeighbour(cells[i-1][j+1]);
				cells[i][j].addNeighbour(cells[i][j-1]);
				cells[i][j].addNeighbour(cells[i][j+1]);
				cells[i][j].addNeighbour(cells[i+1][j-1]);
				cells[i][j].addNeighbour(cells[i+1][j]);
				cells[i][j].addNeighbour(cells[i+1][j+1]);
			}
	}
	
	public void startCell(int col, int row) {
		if (verifyCellIndex(col, row)) {
			cells[row][col].setState(SingleCell.State.ALIVE);
		}
		else {
			Logger logger = Logger.getLogger(CellBoard.class.getName());
			logger.warning("Trying to set cell out of board boundaries");
		}
	}
	
	public void changeCell(int col, int row) {
		if (verifyCellIndex(col, row)) {
			cells[row][col].changeState();
		}
		else {
			Logger logger = Logger.getLogger(CellBoard.class.getName());
			logger.warning("Trying to set cell out of board boundaries");
		}
	}
	
	private boolean verifyCellIndex(int col, int row) {
		return col >= 0 && col < width && row >= 0 && row < height;
	}
	
	public void drawBoat(int col, int row, boolean horizontal) {
		if (horizontal == true) {
			startCell(col, row+1);
			startCell(col+1, row);
			startCell(col+2, row);
			startCell(col+3, row+1);
			startCell(col+1, row+2);
			startCell(col+2, row+2);
		}
		else {
			startCell(col+1, row);
			startCell(col, row+1);
			startCell(col, row+2);
			startCell(col+1, row+3);
			startCell(col+2, row+1);
			startCell(col+2, row+2);
		}		
	}
	
	public void drawBoat(int col, int row) {
		drawBoat(col, row, true);
	}
	
	public void drawLongBoat(int col, int row, boolean horizontal) {
		if (horizontal) {
			startCell(col, row+1);
			startCell(col+1, row);
			startCell(col+2, row);
			startCell(col+3, row);
			startCell(col+4, row+1);
			startCell(col+1, row+2);
			startCell(col+2, row+2);
			startCell(col+3, row+2);
		}
		else {
			startCell(col+1, row);
			startCell(col+1, row+4);
			startCell(col, row+1);
			startCell(col, row+2);
			startCell(col, row+3);
			startCell(col+2, row+1);
			startCell(col+2, row+2);
			startCell(col+2, row+3);			
		}
	}
	
	public void drawLongBoat(int col, int row) {
		drawLongBoat(col, row, true);
	}
	
	public void drawRandomly() {
		Random rnd = new Random();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				if (rnd.nextInt(2) == 1)
					startCell(i, j);
			}
	}
	
	public void drawRandomly(double probability) {
		Random rnd = new Random();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				if (rnd.nextDouble() < probability)
					startCell(i, j);
			}
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
	
	public SingleCell.State[][] getStates() {
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
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	

}
