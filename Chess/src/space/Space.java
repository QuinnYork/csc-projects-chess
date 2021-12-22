package space;

import piece.Piece;

public class Space {
	
	private Piece piece;
	private int x;
	private int y;
	
	public Space(Piece piece, int x, int y)
	{
		this.piece = piece;
		this.x = x;
		this.y = y;
	}

	public Piece getPiece() 
	{
		return piece;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	
	
	
}
