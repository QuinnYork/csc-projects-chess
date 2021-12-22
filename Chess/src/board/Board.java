package board;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Queen;
import piece.Rook;
import space.Move;
import space.Space;

public class Board {
	
	private Space[][] board;
	private Move m;
	public static final int SIZE = 8;
	
	public Board()
	{
		board = new Space[SIZE][SIZE];
		
		for (int i = 0; i < 2; i++) 
		{
			for (int j = 0; j < SIZE; j++) 
			{
				if (i == 0)
				{
					if (j == 0 || j == 7) { board[i][j] = new Space(new Rook(false), i, j); }
					else if (j == 1 || j == 6) { board[i][j] = new Space(new Knight(false), i, j); }
					else if (j == 2 || j == 5) { board[i][j] = new Space(new Bishop(false), i, j); }
					else if (j == 3) { board[i][j] = new Space(new Queen(false), i, j); }
					else if (j == 4) { board[i][j] = new Space(new King(false), i, j); }
				} else { board[i][j] = new Space(new Pawn(false), i, j); }
			}
		}
		
		for (int i = 2; i < 6; i++) 
		{
			for (int j = 0; j < SIZE; j++)
			{
				board[i][j] = new Space(null, i, j);
			}
		}
		
		for (int i = 6; i < SIZE; i++) 
		{
			for (int j = 0; j < SIZE; j++) 
			{
				if (i == 0)
				{
					if (j == 0 || j == 7) { board[i][j] = new Space(new Rook(true), i, j); }
					else if (j == 1 || j == 6) { board[i][j] = new Space(new Knight(true), i, j); }
					else if (j == 2 || j == 5) { board[i][j] = new Space(new Bishop(true), i, j); }
					else if (j == 3) { board[i][j] = new Space(new Queen(true), i, j); }
					else if (j == 4) { board[i][j] = new Space(new King(true), i, j); }
				} else { board[i][j] = new Space(new Pawn(true), i, j); }
			}
		}
		
	}
	
	/**
	 * Given the move, the board will be updated accordingly.
	 * @param m
	 */
	public void update(Move m) //TODO: possibly create an interface that checks for conflict in the given move object in the param.
	{						   // could also add a method in move that checks for conflict
		
	}
	
	public Space[][] getBoard()
	{
		return board;
	}
	
	public Space getSpace(int x, int y) { return board[x][y]; }
	
	

}
