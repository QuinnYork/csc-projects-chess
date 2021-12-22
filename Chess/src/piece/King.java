/**
 * 
 */
package piece;

import board.Board;
import space.Space;

/**
 * @author yorkq
 *
 */
public class King extends Piece {
	
	
	public King(boolean white) {
		super(white);
	}

	@Override
	public boolean canMove(Board board, Space start, Space end) {
		if (end.getX() > 7 || end.getX() < 0 || end.getY() > 7 || end.getY() < 0)
			throw new IllegalArgumentException("Move is out of bounds.");
		else if (end.getPiece().isWhite() == this.isWhite() || !end.getPiece().isWhite() == !this.isWhite())
            return false;
		// (0,1) -> (1,2) x++, y++ ; (0,1) -> (1,1) x++; (0,1) -> (0,2) y++
		int x = Math.abs(end.getX() - start.getX());
		int y = Math.abs(end.getY() - start.getY());
		if (x == 1 && y == 0 || x == 0 && y == 1 || x == 1 && y == 1)
		{
			return true;
		} return false;
	}

}
