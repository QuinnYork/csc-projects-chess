package manager;

import java.util.ArrayList;

import board.Board;
import board.State;
import space.Move;
import space.Player;

public class Game {

	private Board b;
	private ArrayList<Move> moves;
	private Player p1;
	private Player p2;
	private State s;
	
	public Game(Player p1, Player p2)
	{
		s = s.IDLE;
		this.p1 = p1;
		this.p2 = p2;
	}
}
