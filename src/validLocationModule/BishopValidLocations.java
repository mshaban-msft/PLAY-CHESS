package validLocationModule;

import java.util.ArrayList;

import battlePiecesModule.Piece;
import gameSetModule.ChessBoardSet;

public class BishopValidLocations implements ValidLocations{

	//01_Attributes***********************
	//*************************************************************************
	private ArrayList<int[]> nextValidLocations;
	private Piece piece;
	private ChessBoardSet gameBoard;
	private int currentRow; private int currentCol;
	
	
	//02_Constructor**********************
	//*************************************************************************
	public BishopValidLocations(Piece piece, ChessBoardSet gameBoard) {
		this.piece=piece;
		this.gameBoard=gameBoard;
		nextValidLocations=new ArrayList<int[]>();
		currentRow=-1; currentCol=-1;
	}
	
	
	//03_Methods**************************
	//*************************************************************************
	@Override
	public void setNextValidLocations() {
		nextValidLocations.clear();
		currentRow=piece.getCurrentRow(); 
		currentCol=piece.getCurrentColumn();
		setupDiagonalAMoves();
		setupDiagonalBMoves();
	}

	@Override
	public ArrayList<int[]> getNextValidLocations() {
		return nextValidLocations;
	}

	//01_setup directional moves
	//*********************************************************
	private void setupDiagonalAMoves(){
		int row=currentRow+8;
		int col=currentCol-8;
		for(int i=1; i<=14; i++){
			try{
				row=row-i; col=col+i;
				boolean freeRoute=FreeRouteTester.freeDiagonalARoute(gameBoard, currentRow, currentCol, row, col);
				boolean withinBoardLimits=(col>=0)&&(col<=7)&&(row>=0)&&(row<=7);
				boolean noTeamMate=!(gameBoard.getGameBoard()[row][col].getTeam().equalsIgnoreCase(piece.getTeam()));
				boolean differentCell=(piece.getCurrentColumn()!=col)&&(piece.getCurrentRow()!=row);
				if(freeRoute&&withinBoardLimits&&noTeamMate&&differentCell){
					nextValidLocations.add(new int[]{row, col});
				}
			}catch(Exception ex){}
		}
	}
	
	private void setupDiagonalBMoves(){
		int row=currentRow-8;
		int col=currentCol-8;
		for(int i=1; i<=14; i++){
			try{
				row=row+i; col=col+i;
				boolean freeRoute=FreeRouteTester.freeDiagonalBRoute(gameBoard, currentRow, currentCol, row, col);
				boolean withinBoardLimits=(col>=0)&&(col<=7)&&(row>=0)&&(row<=7);
				boolean noTeamMate=!(gameBoard.getGameBoard()[row][col].getTeam().equalsIgnoreCase(piece.getTeam()));
				boolean differentCell=(piece.getCurrentColumn()!=col)&&(piece.getCurrentRow()!=row);
				if(freeRoute&&withinBoardLimits&&noTeamMate&&differentCell){
					nextValidLocations.add(new int[]{row, col});
				}
			}catch(Exception ex){}
		}
	}

	
	
}