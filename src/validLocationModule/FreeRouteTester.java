package validLocationModule;

import gameSetModule.ChessBoardSet;

public class FreeRouteTester {
	
	//****************************************************
	public static boolean freeVerticalRoute(ChessBoardSet gameBoard, int fromRow, int fromCol, int toRow, int toCol){
		if(toRow<fromRow){
			int tmp=toRow; toRow=fromRow; fromRow=tmp;
		}
		//assuming piece moving top to bottom
		try{
			for(int row=fromRow+1; row<=toRow-1; row++){
				if(!(gameBoard.getGameBoard()[row][fromCol].getType().equalsIgnoreCase("blank"))){
					return false;
				}
			}
			return true;
		}catch(Exception ex){
			return false;
		}
		
	}
	
	//****************************************************
	public static boolean freeHorizontalRoute(ChessBoardSet gameBoard, int fromRow, int fromCol, int toRow, int toCol){
		if(toCol<fromCol){
			int tmp=toCol; toCol=fromCol; fromCol=tmp;
		}
		//assuming piece moving from left to right
		try{
			for(int col=fromCol+1; col<=toCol-1; col++){
				if(!(gameBoard.getGameBoard()[fromRow][col].getType().equalsIgnoreCase("blank"))){
					return false;
				}
			}
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	//****************************************************
	public static boolean freeDiagonalARoute(ChessBoardSet gameBoard, int fromRow, int fromCol, int toRow, int toCol){
		if(toCol<fromCol){
			int tmp=toCol; toCol=fromCol; fromCol=tmp;
			int tmp2=toRow; toRow=fromRow; fromRow=tmp2;
		}
		//assuming piece moving 10 o'clock
		try{
			int row=fromRow-1;
			for(int col=fromCol+1; col<=toCol-1; col++){
				if(!(gameBoard.getGameBoard()[row][col].getType().equalsIgnoreCase("blank"))){
					return false;
				}
				row--;
			}
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	//****************************************************
	public static boolean freeDiagonalBRoute(ChessBoardSet gameBoard, int fromRow, int fromCol, int toRow, int toCol){
		if(toCol<fromCol){
			int tmp=toCol; toCol=fromCol; fromCol=tmp;
			int tmp2=toRow; toRow=fromRow; fromRow=tmp2;
		}
		//assuming piece moving 4 o'clock
		try{
			int row=fromRow+1;
			for(int col=fromCol+1; col<=toCol-1; col++){
				if(!(gameBoard.getGameBoard()[row][col].getType().equalsIgnoreCase("blank"))){
					return false;
				}
				row++;
			}
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	//****************************************************
	public static boolean freeKnight(int fromRow, int fromCol, int toRow, int toCol){
		return true;
	}

}
