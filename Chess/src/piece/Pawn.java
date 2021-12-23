package piece;

import board.Board;
import space.Space;

public class Pawn extends Piece {

	public Pawn(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Board board, Space start, Space end) {
		if (end.getX() > 7 || end.getX() < 0 || end.getY() > 7 || end.getY() < 0)
			throw new IllegalArgumentException("Move is out of bounds.");
		else if (end.getPiece().isWhite() == this.isWhite() || !end.getPiece().isWhite() == !this.isWhite())
            return false;
		int diffX = Math.abs(start.getX() - end.getX());
		int diffY = Math.abs(start.getY() - end.getY());
		if (isWhite() && start.getY() == 6 || !isWhite() && start.getY() == 1) 
		{
			if (diffY > 2)
				return false;
			else if (diffX == 1 && diffY == 1)
				return end.getPiece().isWhite() != this.isWhite() || !end.getPiece().isWhite() == this.isWhite();
			else {
				for (int i = 0; i < diffY; i++) 
				{
					if (board.getSpace(start.getX(), start.getY() + i).getPiece().isWhite() == this.isWhite() || 
			    			!board.getSpace(start.getX(), start.getY() + i).getPiece().isWhite() == !this.isWhite()) 
			    	{ return false; }
				}
			}
		}
		boolean valid = (diffY == 1);
		return (end.getPiece().isWhite() == this.isWhite() || !end.getPiece().isWhite() == !this.isWhite()) && valid;
	}

}
