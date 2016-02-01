package battleship;

public class ShipBoard {

	public final static int BOARDWIDTH = 10;

	private int[][] board;
	private int firedShips;
	
	public int getFiredShips(){
		return firedShips;
	}
	
	public void moreFiredShips(){
		firedShips++;
	}

	public ShipBoard() {

		board = new int[BOARDWIDTH][BOARDWIDTH];

	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoardCoords(int x, int y, int value) {
		board[x][y] = value;
	}

	public void emptyBoard() {

		for (int x = 0; x < BOARDWIDTH; x++) {
			for (int y = 0; y < BOARDWIDTH; y++) {
				board[x][y] = Ship.EMPTY;
			}
		}
	}
	
	public boolean stillShips(int shipsCounter) {
		
		if(firedShips==shipsCounter)return false;
		return true;
	}

}
