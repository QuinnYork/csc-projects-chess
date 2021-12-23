package board;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;
import space.Move;
import space.Space;

public class Board {
	
	private Space[][] board;
	public static final int SIZE = 8;
	public static final int MAX_X_ROOK = 7;
	public static final int MAX_X_KNIGHT = 6;
	public static final int MAX_X_BISHOP = 5;
	public static final int X_QUEEN = 3;
	public static final int X_KING = 4;
	
	
	
	public Board()
	{
		board = new Space[SIZE][SIZE];
		intializeBoard(board);
	}
	
	private void intializeBoard(Space[][] board)
	{
		for (int i = 0; i < 2; i++) 
		{
			for (int j = 0; j < SIZE; j++) 
			{
				if (i == 0)
				{
					if (j == 0 || j == MAX_X_ROOK) { board[i][j] = new Space(new Rook(false), i, j); }
					else if (j == 1 || j == MAX_X_KNIGHT) { board[i][j] = new Space(new Knight(false), i, j); }
					else if (j == 2 || j == MAX_X_BISHOP) { board[i][j] = new Space(new Bishop(false), i, j); }
					else if (j == X_QUEEN) { board[i][j] = new Space(new Queen(false), i, j); }
					else if (j == X_KING) { board[i][j] = new Space(new King(false), i, j); }
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
					if (j == 0 || j == MAX_X_ROOK) { board[i][j] = new Space(new Rook(true), i, j); }
					else if (j == 1 || j == MAX_X_KNIGHT) { board[i][j] = new Space(new Knight(true), i, j); }
					else if (j == 2 || j == MAX_X_BISHOP) { board[i][j] = new Space(new Bishop(true), i, j); }
					else if (j == X_QUEEN) { board[i][j] = new Space(new Queen(true), i, j); }
					else if (j == X_KING) { board[i][j] = new Space(new King(true), i, j); }
				} else { board[i][j] = new Space(new Pawn(true), i, j); }
			}
		}
	}
	
	/**
	 * Given the move, the board will be updated accordingly.
	 * @param m
	 */
	public State update(Move m, State s) //TODO: possibly create an interface that checks for conflict in the given move object in the param.
	{						   // could also add a method in move that checks for conflict
		if (m.isValid())
		{
			if (!(m.getStart().getPiece() instanceof King) && s != State.CHECK)
			{
				Piece p1 = m.getStart().getPiece();
				Piece p2 = m.getEnd().getPiece();
				if (p2.isWhite() != p1.isWhite()) { p2.setKilled(true); }
				board[m.getEnd().getX()][m.getEnd().getY()].setPiece(p1);
				board[m.getStart().getX()][m.getStart().getY()].setPiece(null);
				return detectCheck(m, s);
			} else {
				if (!(m.getStart().getPiece() instanceof King) && s == State.CHECK)
					return State.REDO;
				else if (m.getStart().getPiece() instanceof King && s == State.CHECK) {
					if (detectCheck(m, s) == State.REDO)
						return State.REDO;
					else {
						Piece p1 = m.getStart().getPiece();
						Piece p2 = m.getEnd().getPiece();
						if (p2.isWhite() != p1.isWhite()) { p2.setKilled(true); }
						board[m.getEnd().getX()][m.getEnd().getY()].setPiece(p1);
						board[m.getStart().getX()][m.getStart().getY()].setPiece(null);
						return State.ACTIVE;
					}
				}
			}
		} else {
			System.out.println("Invalid move. Try again.");
			return State.REDO;
		}
		return State.ACTIVE;
	}
	
	public Space[][] getBoard()
	{
		return board;
	}
	
	public Space getSpace(int x, int y) 
	{ 
		return board[x][y]; 
	}
	
	private State detectCheck(Move m, State s)
	{
		
		return null;
	}
	
	private boolean detectCheckmate(Move m)
	{
		
		return false;
	}
	
	

}
