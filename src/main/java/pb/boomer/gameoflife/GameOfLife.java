package pb.boomer.gameoflife;
import java.io.IOException;
import javafx.application.*;


public class GameOfLife {
	
	public static void main(String args[]) throws InterruptedException, IOException {
		System.out.println("Hi");
//		for (int i = 0; i < 20; i++) {
//			for (int j = 0; j < 20; j++) {
//				System.out.print("*");
//			}
//			System.out.print("\n");
//		}
//		System.out.println("Program ended");
		CellBoard board = new CellBoard();
		board.initialize(60, 20);
		board.startCell(30, 10);
		board.startCell(29, 10);
		board.startCell(31, 10);
		board.startCell(31, 11);
		board.startCell(31, 9);
		
		board.print();
		
		for (int i = 0; i < 10; i++) {
			System.in.read();
			board.update();
			board.print();			
		}
		
	}

}
