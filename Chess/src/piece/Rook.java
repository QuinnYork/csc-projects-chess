package piece;

import board.Board;
import space.Space;

public class Rook extends Piece {

	public Rook(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Board board, Space start, Space end) {
		if (end.getX() > 7 || end.getX() < 0 || end.getY() > 7 || end.getY() < 0)
			throw new IllegalArgumentException("Move is out of bounds.");
		else if (end.getPiece().isWhite() == this.isWhite() || !end.getPiece().isWhite() == !this.isWhite())
            return false;
		
		int diffX = Math.abs(end.getX() - start.getX());
		int diffY = Math.abs(end.getY() - start.getY());
		if (diffX == 0 && diffY > 0 || diffX > 0 && diffY == 0)
		{
			if (diffX == 0)
			{
				for (int i = 0; i < diffY; i++)
				{
					if (board.getSpace(start.getX(), start.getY() + i).getPiece().isWhite() && isWhite() || 
			    			!board.getSpace(start.getX(), start.getY() + i).getPiece().isWhite() && !isWhite()) 
			    	{
			    		return false;
			    	}
				}
			} else {
				for (int i = 0; i < diffX; i++)
				{
					if (board.getSpace(start.getX() + i, start.getY()).getPiece().isWhite() && isWhite() || 
			    			!board.getSpace(start.getX() + i, start.getY()).getPiece().isWhite() && !isWhite()) 
			    	{
			    		return false;
			    	}
				}
			}
			return true;
		}
		return false;
	}

}
