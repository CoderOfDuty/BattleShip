package battleship;

import java.util.Scanner;
import java.util.ArrayList;

public class BattleShipHiban {
	
	static ArrayList <Ship> shipsTable = new ArrayList <Ship> ();
	
	private static int shipsCounter = 0;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
				
		int[] shipsAndWidths = {5, 4, 4, 3, 3, 2, 1};
				
		Ship aux = new Ship(0,0);
		
		Ship ship[] = new Ship[shipsAndWidths.length];
						
		createShips(ship,shipsAndWidths);
		
		System.out.println("Cantidad de barcos: "+ shipsCounter);
		System.out.println();
		
		System.out.print("Check board: ");
		int[][] boardAux = aux.emptyBoard();
		System.out.println("Done");							//Tablero 1: Posiciones reveladas para comprobaciones
		
		System.out.print("Game board: ");
		int[][] boardMain = aux.emptyBoard();				
		System.out.println("Done");							//Tablero 2: Tablero de juego sin revelar
		System.out.println();	
		
		System.out.println("SHIP POSITION: ");
		System.out.println("**************");
		System.out.println();
		
		positionShips(ship, boardAux);
		
		System.out.println();
		System.out.println("Auxiliar board:");
		System.out.println("***************");
		System.out.println();
		
		drawBoard(boardAux);
		
		System.out.println();
		System.out.println("Game board:");
		System.out.println("***********");
		System.out.println();
		
		drawBoard(boardMain);
		
		//int[] coords = new int [2];
		//coords = getCoords();
				
	}	
	
	private static void createShips (Ship[] ship, int[] shipsAndWidths) {
		
		for (int x = 0; x < shipsAndWidths.length; x++) {			
			ship[x] = new Ship(x, shipsAndWidths[x]);						
			shipsTable.add(ship[x]);
			shipsCounter++;
		}
		
	}
	
	private static void positionShips (Ship[] ship, int[][] board) {
		
		for (int x = 0; x < shipsCounter; x++) {
			ship[x].setBoard(board);
			ship[x].shipPosition();
		}		
	}
	
	private static void drawBoard (int[][] board) {
		
		System.out.print("   ");
		
		for (int x = 0; x < Ship.BOARDWIDTH; x++) {System.out.print((x+1)+" ");}
		
		System.out.println();
		
		for (int x = 0; x < Ship.BOARDWIDTH; x++) {
			
			System.out.println();		
			char c = (char)(x+65);			
			
			System.out.print(c+"  ");
			
			for (int y = 0; y < Ship.BOARDWIDTH; y++) {
				System.out.print(board[x][y]+" ");
			}
		}
		System.out.println();
	}
	
	private static void gameLoop(int [] [] boardAux ,int [] [] boardMain) {
		
		while (stillShips(boardAux)) { // there are still ships on the board
			
			int[] coords = new int [2];			
			coords = getCoords();

			updateBoard(coords, boardAux, boardMain);

			//writeBoard(boardMain);

		}
		
	}

	private static void updateBoard(int[] coords, int[][] boardAux, int[][] boardMain) {
		
		
		
	}

	private static boolean stillShips(int [] [] board) {
		
		for (int x = 0; x < Ship.BOARDWIDTH; x++) {
			for (int y = 0; y < Ship.BOARDWIDTH; y++) {
				if (board[x][y] == Ship.SHIP) {return true;}
			}
		}
		return false;
	}
	
	public static int[] getCoords() {
		
		int[] coords = new int [2];
		
		int x, y;
		char read;
		
		System.out.println("X-Coord? [A - J]");
		
		read = sc.next().charAt(0);		
		x = read - 65;
		
		while(x < 1 || (x > 10 && x < 32) || x > 41) {
			System.out.println("Between A - J");			
			read = sc.next().charAt(0);
			x = read - 65;
		}
		
		if (x >= 32) {x = x - 32;}
		
		System.out.println(x);
		coords[0] = x;
		
		System.out.println("Y-Coord? [1 - 10]");
		y = sc.nextInt();
		
		while(y<1 || y>10){
			System.out.println("Between [1 - 10]");
			y = sc.nextInt();
		}
		
		coords[1] = y - 1;
		
		return coords;
		
	}
	
}
