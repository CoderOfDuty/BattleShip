package battleship;

import java.util.Scanner;

import battleship.MenuSwing;

public class Battleship {

	final static int BOARDWIDTH = 10;
	final static int HORITZONTAL = 0;
	final static int VERTICAL = 1;
	final static int EMPTY = 0;
	final static int SHIP = 1;
	final static int FIRED = 2;
	final static int HIT = 3;
	final static int SHIPSORROUND = 4;
	final static int WATER = 5;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		
		int[] ships = { 5, 4, 4, 3, 3, 2, 1 };
		int[][] board = new int[BOARDWIDTH][BOARDWIDTH];
		int[][] board2 = new int[BOARDWIDTH][BOARDWIDTH];
		
		int[] [] [] shipCoords = new int [ships.length] [] [];//
		
		for (int i=0 ; i<ships.length; i++){
			shipCoords = new int[i] [ships[i]] [2];
		}
		
		
		
		System.out.print("EmptyBoard: ");
		emptyBoard(board);
		System.out.println("Done");
		System.out.print("EmptyBoard2: ");
		emptyBoard(board2);
		System.out.println("Done");
		System.out.println("ShipPosition: ");
		shipPosition(board, ships, shipCoords);
		System.out.println("Done");
		System.out.print("MenuSwing: ");
		MenuSwing frame = new MenuSwing();
		System.out.println("Done");
		
		System.out.println("Start game!");
				
		for (int x = 0; x < BOARDWIDTH; x++) {
			System.out.println();
			for (int y = 0; y < BOARDWIDTH; y++) {
				System.out.print(board[x][y]+" ");
			}
		}
		System.out.println();
		System.out.println();
		System.out.println();
		
		writeBoard(board2);
		
		System.out.println();
		
		gameLoop(board, board2, frame, sc);
		
		System.out.println("Goodbye!");
		sc.close();
	}

	private static void emptyBoard(int[][] board) {
		for (int x = 0; x < BOARDWIDTH; x++) {
			for (int y = 0; y < BOARDWIDTH; y++) {
				board[x][y] = EMPTY;
			}
		}
	}

	private static void shipPosition(int[][] board,int[] ships, int[][][] shipCoords) {

		
		int i = 0;

		while (i < ships.length) {
			int[] position;

			position = getPositionOrientation(ships[i]);

			if (comprobation(board, position, ships[i])) {
				System.out.println("Ship number: "+i+" Width: "+ships[i]);
				placement(position, ships[i], board, shipCoords,i);
				i++;
			}
		}

	}

	private static boolean comprobation(int[][] board, int[] position, int shipsWidth) {
		int x, y, orientation;
		x = position[0];
		y = position[1];
		orientation = position[2];
		
		if(orientation == HORITZONTAL){//encima de otro barco
			for (int i = 0; i < shipsWidth; i++) {
				if (board[x][y] == SHIP) {
					return false;
				}
			}
		}else{
			for (int i = 0; i < shipsWidth; i++) {
				if (board[x][y] == SHIP) {
					return false;
				}
			}
		}

		if (orientation == HORITZONTAL) { //sobresale de la tabla
			if (x+shipsWidth >= BOARDWIDTH){
				return false;
			}
		} else {
			if (y+shipsWidth >= BOARDWIDTH){
				return false;
			}
		}
		
		
		if(orientation == HORITZONTAL){//adyacente de otro barco
			for (int i = 0; i < shipsWidth; i++) {
				if (board[x+i][y] == SHIPSORROUND) {
					return false;
				}
			}
		}else{
			for (int i = 0; i < shipsWidth; i++) {
				if (board[x][y+i] == SHIPSORROUND) {
					return false;
				}
			}
		}
		return true;
	}

	private static void placement(int[] position, int shipWidth, int[][] board, int[][][] shipCoords, int i2) {
		int x, y, orientation;
		x = position[0];
		y = position[1];
		orientation = position[2];
		
		shipCoords[i2][0][0]= shipWidth;
		shipCoords[i2][0][1]= 0;
		
		for (int i = 0; i < shipWidth; i++) {
			if (orientation == HORITZONTAL) {
				board[x+i][y] = SHIP;
			} else {
				board[x][y+i] = SHIP;
			}
			
			shipCoords[i2][i+1][0]= x;
			shipCoords[i2][i+1][1]= y;
			System.out.println("x: "+x+"   y: "+y);
			
			storeShipCoords(position, shipWidth);
			
		}

		upperEdge(position, board);
		bottomEdge(position, board, shipWidth);
		rightLeftEdge(position, board, shipWidth);
		
	}

	private static void storeShipCoords(int[] position, int shipWidth) {
		// TODO Auto-generated method stub
		
	}

	private static void rightLeftEdge(int[] position, int[][] board, int shipWidth) {
		int x, y, orientation;
		x = position[0];
		y = position[1];
		orientation = position[2];
		
		for (int i = 0; i < shipWidth; i++) {
			if (orientation == HORITZONTAL) {
				if (y - 1 >= 0) {						// OOOO
					board[x + i][y - 1] = SHIPSORROUND; //-XXXX-
				}										// ----
				if (y + 1 < BOARDWIDTH) {				// ----
					board[x + i][y + 1] = SHIPSORROUND; //-XXXX-
				}										// OOOO
			} else {
				if (x - 1 >= 0) {						// |
					board[x - 1][y + i] = SHIPSORROUND; //0X|
				}										// |
				if (x + 1 < BOARDWIDTH) {				// |
					board[x + 1][y + i] = SHIPSORROUND; //|XO
				}										// |
			}
		}
	}

	private static void upperEdge(int[] position, int[][] board) {
		int x, y, orientation;
		x = position[0];
		y = position[1];
		orientation = position[2];

		if (orientation == HORITZONTAL) {
			if (x - 1 >= 0) { 					//  ----
				board[x - 1][y] = SHIPSORROUND; // OXXXX-
			} 									//  ----

		} else {
			if (y - 1 >= 0) { 					//  O
				board[x][y - 1] = SHIPSORROUND; // |x|
			} 									//  |
		}
	}

	private static void bottomEdge(int[] position, int[][] board, int shipWidth) {

		int x, y, orientation;
		x = position[0];
		y = position[1];
		orientation = position[2];

		if (orientation == HORITZONTAL) {				// ----
			if (x + shipWidth < BOARDWIDTH) {			//-XXXXO
				board[x + shipWidth][y] = SHIPSORROUND; // ----
			}

		} else {
			if (y + shipWidth < BOARDWIDTH) {			// |
				board[x][y + shipWidth] = SHIPSORROUND; //|X|
			}											// O
		}
	}

	private static int[] getPositionOrientation(int shipWidth) {
		int orientation, x, y;
		int[] position = new int[3];
		
		
		orientation = (int) (Math.random()*2);
		//System.out.println(orientation);
		position[2] = orientation;

		x = (int) (Math.random() * (BOARDWIDTH-shipWidth+1));
		position[0] = x;
		y = (int) (Math.random() * (BOARDWIDTH-shipWidth+1));
		position[1] = y;
		
		//System.out.println("aleatorio x: "+x+"   y: "+y);
		return position;
	}

	public static void gameLoop(int [] [] board,int [] [] board2, MenuSwing frame, Scanner sc) {
		
		while (stillShips(board)) { // there are still ships on the board
			
			int[] coords = new int [2];
			
			getCoords(coords, frame, sc);

			updateBoard(coords, board, board2, frame);

			writeBoard(board2);

		}
		
	}
	
	private static boolean stillShips(int [] [] board) {
		
		for (int x = 0; x < BOARDWIDTH; x++) {
			for (int y = 0; y < BOARDWIDTH; y++) {
				if(board[x][y]==SHIP){
					return true;
				}
			}
		}
		return false;
	}

	private static void writeBoard(int[][] board2) {
		
		clearConsole();
		System.out.print("   ");
		for (int x = 0; x< BOARDWIDTH; x++) {
			System.out.print(x+1+" ");
			
		}
		System.out.println();
		for (int x = 0; x < BOARDWIDTH; x++) {
			System.out.println();
			
			if (x == BOARDWIDTH -1) {
				System.out.print(x+1+" ");
			} else {
				System.out.print(x+1+"  ");
			}
			
			for (int y = 0; y < BOARDWIDTH; y++) {
				System.out.print(board2[x][y]+" ");
			}
		}
		System.out.println();
		
	}

	public static void getCoords (int [] coords, MenuSwing frame, Scanner sc){
		
		int x, y;
		
		System.out.println("X-Coord? (1 - 10)");
		x = sc.nextInt();
		while(x<1 || x>10){
			System.out.println("Between 1 - 10");
			x = sc.nextInt();
		}
		coords[0] = x-1;
		
		System.out.println("Y-Coord? (1 - 10)");
		y = sc.nextInt();
		while(y<1 || y>10){
			System.out.println("Between 1 - 10");
			y = sc.nextInt();
		}
		coords[1] = y-1;
		
		//sc.close();
		
		/*coords[0]=Integer.parseInt(frame.xcoords.getText());
		coords[1]=Integer.parseInt(frame.ycoords.getText());
		
		frame.xcoords.setText(null);
		frame.ycoords.setText(null);*/
		
	}
	
	private static void updateBoard (int [] coords, int [] [] board,int [] [] board2, MenuSwing frame){
		
		int x, y;
		x = coords[0];
		y = coords[1];
		
		if(board[x][y] == EMPTY || board[x][y] == SHIPSORROUND){
			board2[x][y] = WATER;
		}
		if(board[x][y] == SHIP){
			
			
			board[x][y] = HIT;
			board2[x][y] = HIT;
			
			//repasar el array, comparar coordenadas si un barco ha sido goleado
			//comprovar si el contador llea al limite y ha sido hundido
			//mostrar que ha sido undido
			
			
			//barco completo --> FIRE
		}
		//frame.frame.repaint();
		
	}
	
	public final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        System.out.println();
	    }
	}
}

