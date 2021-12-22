package space;

import board.Board;

public class Move {
	
	private Player p;
	private Space start;
	private Space end;
	private Board b; // TODO: when using canMove(board, start, end) for whatever piece that is in start, use the board.getBoard() as the param
	
	public Move(Player p, Space start, Space end)
	{
		this.p = p;
		this.start = start;
		this.end = end;
	}
	
	public Player getPlayer()
	{
		return p;
	}
	
	public Space getStart()
	{
		return start;
	}
	
	public Space getEnd()
	{
		return end;
	}
	
	
}
