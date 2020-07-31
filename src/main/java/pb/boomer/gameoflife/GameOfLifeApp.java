package pb.boomer.gameoflife;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GameOfLifeApp extends Application {
	
	public static final int rows = 60;
	public static final int columns = 90;
	static CellBoard cellBoard;
	static Rectangle squares[][];
	
	public static int interval = 200;
	private static boolean simulationRunning = false;
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Game Of Life Lite");
		cellBoard = new CellBoard(columns, rows);
		
//		cellBoard.drawBoat(19, 23);
//		cellBoard.drawBoat(20, 19, false);
//		cellBoard.drawLongBoat(19, 14);
//		cellBoard.drawLongBoat(35, 33);
		cellBoard.drawLongBoat(columns/2-2, rows/2-6);
		cellBoard.drawLongBoat(columns/2-2, rows/2+5);
//		cellBoard.startCell(19,  23);
//		cellBoard.drawRandomly(0.72);
		
		GridPane grid = new GridPane();
		squares = new Rectangle[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				squares[i][j] = new Rectangle(10, 10, new Color(0.3, 0.3, 0.3, 1.0));
				Rectangle square = squares[i][j];
				GridPane.setConstraints(square, j, i);
				grid.getChildren().add(square);
				// Define action on clicking on this square
				squares[i][j].setOnMouseClicked(e -> {
					int col = GridPane.getColumnIndex(square);
					int row = GridPane.getRowIndex(square);
					System.out.println("Column idx: " + col);
					System.out.println("Row idx: " + row);
					cellBoard.changeCell(col, row);
					updateSquare(col, row);
				});
			}
		
		grid.setVgap(2);
		grid.setHgap(2);
		grid.setPadding(new Insets(10));
		grid.setBackground(new Background(new BackgroundFill(new Color(0.2, 0.2, 0.2, 1.0), null, null)));
		
		updateAllSquares();
		
		Button updateButton = new Button("Update");
		updateButton.setOnAction(GameOfLifeApp::updateBoard);
		
		Button runPauseButton = new Button("Run/Pause");
		
		HBox layout = new HBox(grid, updateButton, runPauseButton);
		layout.setMinSize(400, 330);
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	public static void updateBoard(ActionEvent event) {
//		System.out.println("Update button clicked!");
		cellBoard.update();
		updateAllSquares();
	}
	
	// This 100% won't work
	@Deprecated
	public static void runUpdates(ActionEvent event) {
		simulationRunning = !simulationRunning;
		try {
			while (simulationRunning) {
				Thread.sleep(interval);
				updateAllSquares();
			}
		}
		catch (InterruptedException e) {
			System.out.println(e);
		}
	}
	
	static void updateAllSquares() {
		SingleCell.State boardStates[][] = cellBoard.getStates();
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				if (boardStates[i][j] == SingleCell.State.ALIVE) {
					squares[i][j].setFill(new Color(0.9, 0.9, 0.9, 1.0));
				}
				else {
					squares[i][j].setFill(new Color(0.3, 0.3, 0.3, 1.0));
				}
			}
	}
	
	static void updateSquare(int col, int row) {
		if (cellBoard.cells[row][col].getState() == SingleCell.State.ALIVE)
			squares[row][col].setFill(new Color(0.9, 0.9, 0.9, 1.0));
		else
			squares[row][col].setFill(new Color(0.3, 0.3, 0.3, 1.0));
	}

}
