package space;

public class Player {

	private boolean white;
	private boolean turn;
	
	public Player(boolean white) 
	{
		this.setWhite(white);
	}
	
	public void setTurn(boolean turn) 
	{
		this.turn = turn;
	}
	
	public boolean isTurn() {
		return turn;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}
	
	
}
