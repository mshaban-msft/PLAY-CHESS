package battlePiecesModule;

import gameSetModule.ChessGame;
import validLocationModule.KnightValidLocations;

public class Knight extends Piece{
	
	//01_Constructor**********************
	//*************************************************************************

	
	public Knight(ChessGame gameBoard, String displayName, String type, String team, String enemy, int step,
			boolean active, int initialRow, int initialColumn, int currentRow, int currentColumn) {
		super(gameBoard, displayName, type, team, enemy, step, active, initialRow, initialColumn, currentRow, currentColumn);
		//valid locations object
		validLocations=new KnightValidLocations(this, getGameBoard());
	}
}
