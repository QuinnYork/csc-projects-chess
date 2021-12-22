package piece;

import board.Board;
import space.Space;

public class Queen extends Piece {

	public Queen(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Board board, Space start, Space end) {
		if (end.getX() > 7 || end.getX() < 0 || end.getY() > 7 || end.getY() < 0)
			throw new IllegalArgumentException("Move is out of bounds.");
		else if (end.getPiece().isWhite() == this.isWhite() || !end.getPiece().isWhite() == !this.isWhite())
            return false;
		return false;
	}

}
