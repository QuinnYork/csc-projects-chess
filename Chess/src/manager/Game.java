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
		s = State.IDLE;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void startGame() {
		b = new Board();
		moves = new ArrayList<Move>();
		s = State.ACTIVE;
	}
	
	public void move(Move m) {
		b.update(m, s);
		moves.add(m);
		if (m.getPlayer() == p1) {
			p1.setTurn(false);
			p2.setTurn(true);
		} else {
			p2.setTurn(false);
			p1.setTurn(true);
		}
	}
	
}
