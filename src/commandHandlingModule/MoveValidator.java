package commandHandlingModule;

import java.util.ArrayList;

import battlePiecesModule.BlankPiece;
import battlePiecesModule.Piece;
import gameSetModule.ChessBoardSet;

public class MoveValidator {
	
	//01_ATTRIBUTES
	//*************************************************************************
	private ChessBoardSet chessBoard;
	private int fromRow; private int fromCol;
	private int toRow; private int toCol;
	
	
	//02_CONSTRUCTOR
	//*************************************************************************
	public MoveValidator(ChessBoardSet chessBoard){
		this.chessBoard=chessBoard;
	}
	
	//03_INTERFACE METHODS
	//*************************************************************************
	public boolean validateMove(int fromRow, int fromCol, int toRow, int toCol){
		this.fromRow=fromRow; this.fromCol=fromCol;
		this.toRow=toRow; this.toCol=toCol;
		//validate player turn
		boolean validPlayerTurn=validatePlayerTurn();
		/*
		 * valid piece location
		 * 1. free route
		 * 2. legal piece location
		 * 3. enemy occupied
		 * 4. different cell
		 * 5. within board limits
		 */
		boolean validTargetCell=validateTargetCell();
		//will my king become in danger because of this move
		boolean isMyKingInDanger=doesMyKingBecomeInDanger();
		//return test value
		if(validPlayerTurn&&validTargetCell&&(!isMyKingInDanger)){
			return true;
		}
		return false;
	}
	
	//
	//**********************************************************************************
	private boolean validatePlayerTurn(){
		String playerTurn=chessBoard.getPlayerTurn();
		String currentPlayer=chessBoard.getGameBoard()[fromRow][fromCol].getTeam();
		if(currentPlayer.equalsIgnoreCase(playerTurn)){
			return true;
		}
		return false;
	}
	//**********************************************************************************
	private boolean validateTargetCell(){
		ArrayList<int[]> nextValidLocations=chessBoard.getGameBoard()[fromRow][fromCol].generateNextValidLocations();
		//System.out.println("# of locations : "+nextValidLocations.size());
		for(int[] cell:nextValidLocations){
			
			int possibleValidRow=cell[0]; //System.out.println("Possible Row : "+cell[0]);
			int possibleValidCol=cell[1]; //System.out.println("Possible Col : "+cell[1]);
			if((possibleValidRow==toRow)&&(possibleValidCol==toCol)){
				return true;
			}
		}
		return false;
	}
	//**********************************************************************************
	private boolean doesMyKingBecomeInDanger(){
		/*
		 * this method applies the move then undoes
		 * simply to test will my king become in danger
		 */
		//kill target enemy (if exists)*******************
		String enemy=chessBoard.getGameBoard()[fromRow][fromCol].getEnemy();
		String targetCellTeam=chessBoard.getGameBoard()[toRow][toCol].getTeam();
		Piece targetCellPiece=chessBoard.getGameBoard()[toRow][toCol];
		if(targetCellTeam.equalsIgnoreCase(enemy)){
			chessBoard.getGameBoard()[fromRow][fromCol].setActive(false);
		}
		//move piece to specified location****************
		Piece pieceToMove=chessBoard.getGameBoard()[fromRow][fromCol];
		chessBoard.getGameBoard()[fromRow][fromCol]=new BlankPiece();
		chessBoard.getGameBoard()[toRow][toCol]=pieceToMove;
		pieceToMove.setCurrentRow(toRow); pieceToMove.setCurrentColumn(toCol);
		//test king status********************************
		String team=pieceToMove.getTeam();
		Piece kingToTest;
		if(team.equalsIgnoreCase("white")){
			kingToTest=chessBoard.getWhiteTeam().getKing();
		}else{
			kingToTest=chessBoard.getBlackTeam().getKing();
		}
		boolean isKingInDanger=KingStatusHandler.isKingInDanger(chessBoard, kingToTest);
		//undo move***************************************
		chessBoard.getGameBoard()[fromRow][fromCol]=pieceToMove;
		pieceToMove.setCurrentRow(fromRow); pieceToMove.setCurrentColumn(fromCol);
		chessBoard.getGameBoard()[toRow][toCol]=targetCellPiece;
		if(targetCellTeam.equalsIgnoreCase(enemy)){
			chessBoard.getGameBoard()[fromRow][fromCol].setActive(true);
		}
		
		return isKingInDanger;
	}
	
	
	
	
	
	

}