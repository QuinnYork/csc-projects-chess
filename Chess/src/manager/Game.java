package manager;

import java.util.ArrayList;

import board.Board;
import space.Move;
import space.Player;

public class Game {

	private Board b;
	private ArrayList<Move> moves;
	private Player p1;
	private Player p2;
	
	public Game(Player p1, Player p2)
	{
		this.p1 = p1;
		this.p2 = p2;
	}
}
