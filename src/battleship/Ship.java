package battleship;

public class Ship {

	public final static int HORITZONTAL = 0;
	public final static int VERTICAL = 1;
	public final static int EMPTY = 0; // No se sabe qué hay
	public final static int SHIP = 1; // Hay barco
	public final static int FIRED = 2; // Barco completado
	public final static int HIT = 3; // Golpe al barco
	public final static int SHIPSORROUND = 4; // Alrededor del barco
	public final static int WATER = 5; // Golpe al agua

	private int idShip;
	private int shipWidth;
	private int[][] shipCoords;
	private int hitCounter;

	private int xBase;
	private int yBase;
	private int orientation;

	public Ship(int id, int width) {
		idShip = id;
		shipWidth = width;
		hitCounter = 0;
	}

	public int getIdShip() {
		return idShip;
	}

	public void setIdShip(int id) {
		this.idShip = id;
	}

	public int getShipWidth() {
		return shipWidth;
	}

	public int getHitCounter() {
		return hitCounter;
	}

	public void setHitCounter(int hit) {
		this.hitCounter = hit;
	}

	public int[][] getShipCoords() {
		return shipCoords;
	}

	public boolean hit(ShipBoard shipBoardIa) {
		hitCounter++;
		if (hitCounter == shipWidth) {
			shipBoardIa.moreFiredShips();
			return true;

		}
		return false;
	}

	public void shipPosition(ShipBoard shipBoardIa) {

		getPositionOrientation();

		if (comprobation(shipBoardIa)) {
			System.out.println("Ship number: " + (idShip + 1) + " Width: " + shipWidth);
			placement(shipBoardIa);
		} else {
			shipPosition(shipBoardIa);
		}
	}

	private void getPositionOrientation() { // Se genera el barco en una
											// posición (una coordenada base)
											// y orientación aleatoria

		xBase = (int) (Math.random() * (ShipBoard.BOARDWIDTH - shipWidth + 1)); // X
																				// BASE
		// System.out.println("xBase del barco "+ (idShip+1) +": "+ xBase);

		yBase = (int) (Math.random() * (ShipBoard.BOARDWIDTH - shipWidth + 1)); // Y
																				// BASE
		// System.out.println("yBase del barco "+ (idShip+1) +": "+ yBase);

		orientation = (int) (Math.random() * 2); // Devuelve un 1 o un 0 para
													// VERTICAL u HORIZONTAL
		// System.out.println("Orientación del barco "+ (idShip+1) +": "+
		// orientation);
	}

	private boolean comprobation(ShipBoard shipBoardIa) {

		if (orientation == HORITZONTAL) {

			// Barco colocado encima de otro
			for (int i = 0; i < shipWidth; i++) {
				if (shipBoardIa.getBoard()[xBase][yBase + i] == SHIP) {
					return false;
				}
			}

			// Barco que sobresale de la tabla
			if (xBase + shipWidth >= ShipBoard.BOARDWIDTH) {
				return false;
			}

			// Barco adyacente directamente a otro
			for (int i = 0; i < shipWidth; i++) {
				if (shipBoardIa.getBoard()[xBase + i][yBase] == SHIPSORROUND) {
					return false;
				}
			}

		} else {

			// Barco colocado encima de otro
			for (int i = 0; i < shipWidth; i++) {
				if (shipBoardIa.getBoard()[xBase + i][yBase] == SHIP) {
					return false;
				}
			}

			// Barco que sobresale de la tabla
			if (yBase + shipWidth >= ShipBoard.BOARDWIDTH) {
				return false;
			}

			// Barco adyacente directamente a otro
			for (int i = 0; i < shipWidth; i++) {
				if (shipBoardIa.getBoard()[xBase][yBase + i] == SHIPSORROUND) {
					return false;
				}
			}

		}
		return true;
	}

	private void placement(ShipBoard shipBoardIa) {

		shipCoords = new int[2][shipWidth];

		if (orientation == HORITZONTAL) {

			for (int i = 0; i < shipWidth; i++) {

				shipCoords[0][i] = xBase + i; // X
				shipCoords[1][i] = yBase; // Y

				shipBoardIa.setBoardCoords(xBase + i, yBase, SHIP);
				System.out.println("x: " + shipCoords[0][i] + "   y: " + shipCoords[1][i]);
			}

		} else {

			for (int i = 0; i < shipWidth; i++) {

				shipCoords[0][i] = xBase; // X
				// System.out.println(shipCoords[0][i]);
				shipCoords[1][i] = yBase + i; // Y

				shipBoardIa.setBoardCoords(xBase, yBase + i, SHIP);
				System.out.println("x: " + shipCoords[0][i] + "   y: " + shipCoords[1][i]);
			}
		}

		// Comprobaciones de límites a los cuatro lados POR CORREGIR
		upperEdge(shipBoardIa);
		bottomEdge(shipBoardIa);
		rightLeftEdge(shipBoardIa);

	}

	private void rightLeftEdge(ShipBoard shipBoardIa) { // Indicador de los
														// marcos laterales del
		// barco

		for (int i = 0; i < shipWidth; i++) {
			if (orientation == HORITZONTAL) {
				if (yBase - 1 >= 0) {
					shipBoardIa.setBoardCoords(xBase + i, yBase - 1, SHIPSORROUND);
				}
				if (yBase + 1 < ShipBoard.BOARDWIDTH) {
					shipBoardIa.setBoardCoords(xBase + i, yBase + 1, SHIPSORROUND);
				}
			} else {
				if (xBase - 1 >= 0) {
					shipBoardIa.setBoardCoords(xBase - 1, yBase + i, SHIPSORROUND);
				}
				if (xBase + 1 < ShipBoard.BOARDWIDTH) {
					shipBoardIa.setBoardCoords(xBase + 1, yBase + i, SHIPSORROUND);
				}
			}
		}

		// Si el barco no toca el borde del tablero por los lados, se colocará
		// SHIPSORROUND en las casillas directamente laterales
	}

	private void upperEdge(ShipBoard shipBoardIa) { // Indicador del marco
													// superior del barco

		if (orientation == HORITZONTAL) {
			if (xBase - 1 >= 0) {
				shipBoardIa.setBoardCoords(xBase - 1, yBase, SHIPSORROUND);
			}

		} else {
			if (yBase - 1 >= 0) {
				shipBoardIa.setBoardCoords(xBase, yBase - 1, SHIPSORROUND);
			}
		}

		// Si el barco no toca el borde del tablero por arriba, se colocará
		// SHIPSORROUND en la casilla directamente superior
	}

	private void bottomEdge(ShipBoard shipBoardIa) { // Indicador del marco
														// inferior del barco

		if (orientation == HORITZONTAL) { // ----
			if (xBase + shipWidth < ShipBoard.BOARDWIDTH) { // -XXXXO
				shipBoardIa.setBoardCoords(xBase + shipWidth, yBase, SHIPSORROUND);// ----
			}

		} else {
			if (yBase + shipWidth < ShipBoard.BOARDWIDTH) { // |
				shipBoardIa.setBoardCoords(xBase, yBase + shipWidth, SHIPSORROUND);// |X|
			} // O
		}

		// Si el barco no toca el borde del tablero por abajo, se colocará
		// SHIPSORROUND en la casilla directamente inferior
	}

}
