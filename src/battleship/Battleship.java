package battleship;

import java.util.Scanner;

public class BattleShip {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);// La clase Scanner del sistema para
											// meter datos

		//int[] shipsAndWidths = { 5, 4, 4, 3, 3, 2, 1 };
		
		int[] shipsAndWidths = { 1, 1 };

		int shipsCounter = shipsAndWidths.length; // Cantidad de barcos

		ShipBoard shipBoardIa = new ShipBoard();
		ShipBoard shipBoardPlayer = new ShipBoard();

		Ship[] ship = new Ship[shipsCounter];

		for (int x = 0; x < shipsCounter; x++) {
			ship[x] = new Ship(x, shipsAndWidths[x]);
		}

		for (int x = 0; x < shipsCounter; x++) {

			System.out.println(ship[x].getShipWidth());
		}

		System.out.println("Cantidad de barcos: " + shipsAndWidths.length);
		System.out.println();

		System.out.print("Check board: ");
		shipBoardIa.emptyBoard();
		System.out.println("Done"); // Tablero 1: Posiciones reveladas para
									// comprobaciones

		System.out.print("Game board: ");
		shipBoardPlayer.emptyBoard();
		System.out.println("Done"); // Tablero 2: Tablero de juego sin revelar
		System.out.println();

		System.out.println("SHIP POSITION: ");
		System.out.println("**************");
		System.out.println();

		for (int x = 0; x < shipsCounter; x++) {
			ship[x].shipPosition(shipBoardIa);
		}

		for (int x = 0; x < ShipBoard.BOARDWIDTH; x++) {

			for (int y = 0; y < ShipBoard.BOARDWIDTH; y++) {
				System.out.print(shipBoardIa.getBoard()[x][y] + " ");
			}
			System.out.println();
		}

		System.out.print("MenuSwing: ");
		MenuSwing frame = new MenuSwing();
		System.out.println("Done");

		System.out.println("Start game!");

		gameLoop(shipBoardIa, shipBoardPlayer, frame, sc, shipsCounter, ship);

	}

	public static void gameLoop(ShipBoard shipBoardIa, ShipBoard shipBoardPlayer, MenuSwing frame, Scanner sc,
			int shipsCounter, Ship[] ship) {

		while (shipBoardIa.stillShips( shipsCounter)) { // there are still ships on the board

			int[] coords = new int[2];

			getCoords(coords, frame, sc);

			updateBoard(coords, shipBoardIa, shipBoardPlayer, frame, shipsCounter, ship);

			writeBoard(shipBoardPlayer);

		}
		
		System.out.println("Finish");

	}

	public static void writeBoard(ShipBoard shipBoardPlayer) {

		System.out.print("   ");
		for (int x = 0; x < ShipBoard.BOARDWIDTH; x++) {
			System.out.print(x + 1 + " ");

		}
		System.out.println();
		for (int x = 0; x < ShipBoard.BOARDWIDTH; x++) {
			System.out.println();

			if (x == ShipBoard.BOARDWIDTH - 1) {
				System.out.print(x + 1 + " ");
			} else {
				System.out.print(x + 1 + "  ");
			}

			for (int y = 0; y < ShipBoard.BOARDWIDTH; y++) {

				System.out.print(shipBoardPlayer.getBoard()[x][y] + " ");
			}
		}
		System.out.println();

	}

	public static void getCoords(int[] coords, MenuSwing frame, Scanner sc) {

		int x, y;

		System.out.println("X-Coord? (1 - 10)");
		x = sc.nextInt();
		while (x < 1 || x > 10) {
			System.out.println("Between 1 - 10");
			x = sc.nextInt();
		}
		coords[0] = x - 1;

		System.out.println("Y-Coord? (1 - 10)");
		y = sc.nextInt();
		while (y < 1 || y > 10) {
			System.out.println("Between 1 - 10");
			y = sc.nextInt();
		}
		coords[1] = y - 1;

		// sc.close();

		/*
		 * coords[0]=Integer.parseInt(frame.xcoords.getText());
		 * coords[1]=Integer.parseInt(frame.ycoords.getText());
		 * 
		 * frame.xcoords.setText(null); frame.ycoords.setText(null);
		 */

	}

	private static void updateBoard(int[] coords, ShipBoard shipBoardIa, ShipBoard shipBoardPlayer, MenuSwing frame,
			int shipsCounter, Ship[] ship) {

		int x, y;
		int[][] aux = null;
		x = coords[0];
		y = coords[1];

		if (shipBoardIa.getBoard()[x][y] == Ship.EMPTY || shipBoardIa.getBoard()[x][y] == Ship.SHIPSORROUND) {
			shipBoardPlayer.setBoardCoords(x, y, Ship.WATER);
		}
		if (shipBoardIa.getBoard()[x][y] == Ship.SHIP) {

			

			for (int i = 0; i < shipsCounter; i++) {
				aux = new int[2][ship[i].getShipWidth()];
				aux = ship[i].getShipCoords();
				for (int t = 0; t < ship[i].getShipWidth(); t++) {
					if (aux[0][t] == x && aux[1][t] == y) {
						if (ship[i].hit(shipBoardIa)) {
							for (int g = 0; g < ship[i].getShipWidth(); g++) {
								shipBoardIa.setBoardCoords(aux[0][g],aux[1][g], Ship.FIRED);
								shipBoardPlayer.setBoardCoords(aux[0][g],aux[1][g], Ship.FIRED);
							}
						}else{
							shipBoardIa.setBoardCoords(x, y, Ship.HIT);
							shipBoardPlayer.setBoardCoords(x, y, Ship.HIT);
						}
					}
				}
			}
		}
		// frame.frame.repaint();

	}
}