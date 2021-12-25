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
	
	public State update(Move m, State s)
	{
		if (m.isValid()) // valid move for the piece
		{
			if (s == State.CHECK_B || s == State.CHECK_B) // w/b is in check
			{
				Piece p1 = m.getStart().getPiece();
				if (p1 instanceof King) { // in check you must move your king
					State s1 = detectCheck(m, s);
					if (s1 == State.CHECK_B || s1 == State.CHECK_W) { return State.REDO; } // kings move keeps you in check, so redo.
					// else, king makes move and is not in check anymore.
					Piece p2 = m.getEnd().getPiece();
					if (p2.isWhite() != p1.isWhite()) { p2.setKilled(true); }
					board[m.getEnd().getX()][m.getEnd().getY()].setPiece(p1);
					board[m.getStart().getX()][m.getStart().getY()].setPiece(null);
					return s1;
				} else { // in check but you dont try to move king out of check. redo move.
					return State.REDO;
				}
			} else if (s == State.CHECKMATE) // checkmate
				return State.END;
			else { // not in check
				State s1 = detectCheck(m, s);
				Piece p1 = m.getStart().getPiece();
				if (s1 == State.CHECK_W && p1.isWhite() ||
						s1 == State.CHECK_B && !p1.isWhite()) { // invalid move. move is done by white/black and it puts w/b in check.
					return State .REDO;
				} else if (s1 == State.CHECKMATE) { return s1; } 
				else { // not in check and move is valid, edit board as such
					Piece p2 = m.getEnd().getPiece();
					if (p2.isWhite() != p1.isWhite()) { p2.setKilled(true); }
					board[m.getEnd().getX()][m.getEnd().getY()].setPiece(p1);
					board[m.getStart().getX()][m.getStart().getY()].setPiece(null);
					return s1;
				}
			}
		} return State.REDO;
	}
	
	public Space[][] getBoard()
	{
		return board;
	}
	
	public Space getSpace(int x, int y) 
	{ 
		return board[x][y]; 
	}
	
	public State detectCheck(Move m, State s)
	{
		//TODO: detect check by parsing through board for every piece that is not the same color as the king.
		// move can put a player in check and it can keep a player in check
		//ex/ white moves king in or out of check into check with move. update should not allow this move to happen.
		Piece p1 = m.getStart().getPiece();
		Piece p2 = m.getEnd().getPiece(); // if this isn't null, this check could overwrite this piece if move can't be made and make it null 
		if (p1 instanceof King) { // p1 is king. check if move will put it in check/mate
			board[m.getEnd().getX()][m.getEnd().getY()].setPiece(p1);
			board[m.getStart().getX()][m.getStart().getY()].setPiece(null);
			for (Space[] b: board)
				for (Space s1 : b) {
					if (s1.getPiece() != null) {
						Move moveCheck = new Move(null, s1, m.getEnd());
						if (moveCheck.isValid()) {
							if (p1.isWhite()) {
								board[m.getEnd().getX()][m.getEnd().getY()].setPiece(p2);
								board[m.getStart().getX()][m.getStart().getY()].setPiece(p1);
								return State.CHECK_W;
							} else { //TODO: could have update() just not change the board if it will put me in check.
								board[m.getEnd().getX()][m.getEnd().getY()].setPiece(p2);
								board[m.getStart().getX()][m.getStart().getY()].setPiece(p1);
								return State.CHECK_B;
							}
						}
					}
				}
			return State.ACTIVE;
				
		} else { // p1 is not a king, check if move will put the opposing teams king in check
			board[m.getEnd().getX()][m.getEnd().getY()].setPiece(p1); // move 
			board[m.getStart().getX()][m.getStart().getY()].setPiece(null);
			for (Space[] b: board)
				for (Space s1 : b) {
					if (s1.getPiece().isWhite() != p1.isWhite()) {
						Move moveCheck = new Move(null, m.getEnd(), s1);
						if (s1.getPiece() instanceof King && moveCheck.isValid()) {
							if (s1.getPiece().isWhite()) {
								board[m.getEnd().getX()][m.getEnd().getY()].setPiece(p2);
								board[m.getStart().getX()][m.getStart().getY()].setPiece(p1);
								return State.CHECK_W; // check for checkmate 
							} else { //TODO: could have update() just not change the board if it will put me in check.
								board[m.getEnd().getX()][m.getEnd().getY()].setPiece(p2);
								board[m.getStart().getX()][m.getStart().getY()].setPiece(p1);
								return State.CHECK_B;
							}
						}

					}
				}
			return State.ACTIVE;
			
		}
	}
	
	public boolean detectCheckmate() // used when a king is put in check. try all moves for king.
	{
		
		return false;
	}
	
	

}
