package piece;

import board.Board;
import space.Space;

public class Bishop extends Piece {

	public Bishop(boolean white) {
		super(white);
	}
	
	@Override
	public boolean canMove(Board board, Space start, Space end) { //only knights can jump; check for pieces in the way
		if (end.getX() > 7 || end.getX() < 0 || end.getY() > 7 || end.getY() < 0)
			throw new IllegalArgumentException("Move is out of bounds.");
		else if (end.getPiece().isWhite() == this.isWhite() || !end.getPiece().isWhite() == !this.isWhite())
            return false;
		// (2,0) -> (3,1) ; (2,0) -> (4,2) ; (2,0) -> (5,3)
	    int diffX = (end.getX() - start.getX());
	    boolean valid = true;
	    for (int i = 0; i < diffX; i++)
	    { 								  // since moving diagonally once increments x and y both by one, take diff and loop through each movement
	    								  // until you get to end box. If X start is 2 and X end is 5, it moved 3 times diagonally. Loop 3 times
	    								  // and check the first move space for a piece, second, then third. If move passes a black piece, kill it
	    								  // and replace the killed piece with the bishop. (check for kill in Game where I have the ability to change
	    								  // ending pieces. I can only return true/false in this method.)
	    	if (board.getSpace(start.getX() + i, start.getY() + i).getPiece().isWhite() && isWhite() || 
	    			!board.getSpace(start.getX() + i, start.getY() + i).getPiece().isWhite() && !isWhite()) 
	    	{
	    		valid = false;
	    	}
	    	
	    }
	    
	    return valid;
	}

}
