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
public abstract class Piece {
	
	private boolean white;
	private boolean killed;
	
	public Piece(boolean white) 
	{
		setWhite(white);
		killed = false;
	}
	
	public boolean isWhite () throws NullPointerException
	{
		return white;
	}
	
	public boolean isKilled() 
	{
		return killed;
	}
	
	public void setWhite(boolean white)
	{
		this.white = white;
	}
	
	public void setKilled(boolean killed)
	{
		this.killed = killed;
	}
	
	public abstract boolean canMove(Board board, Space start, Space end); 
}
