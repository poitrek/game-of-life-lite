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
	
	public static final int rows = 40;
	public static final int columns = 50;
	static CellBoard cellBoard;
	static Rectangle squares[][];
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Game Of Life Lite");
		cellBoard = new CellBoard(columns, rows);
		
		cellBoard.drawBoat(19, 23);
		cellBoard.drawBoat(22, 23);
		cellBoard.drawBoat(14, 23);
//		cellBoard.startCell(19,  23);
		
		GridPane grid = new GridPane();
		squares = new Rectangle[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				squares[i][j] = new Rectangle(10, 10, new Color(0.3, 0.3, 0.3, 1.0));
				GridPane.setConstraints(squares[i][j], j, i);
				grid.getChildren().add(squares[i][j]);
			}
		grid.setVgap(2);
		grid.setHgap(2);
		grid.setPadding(new Insets(10));
		grid.setBackground(new Background(new BackgroundFill(new Color(0.2, 0.2, 0.2, 1.0), null, null)));
		
		updateSquares();
		
		Button updateButton = new Button("Update");
		updateButton.setOnAction(GameOfLifeApp::updateBoard);
		HBox layout = new HBox(grid, updateButton);
		layout.setMinSize(400, 330);
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	public static void updateBoard(ActionEvent event) {
		System.out.println("Update button clicked!");
		cellBoard.update();
		updateSquares();
	}
	
	public static void updateSquares() {
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

}
