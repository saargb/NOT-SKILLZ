package pirates.game;

public class Move {
	private String describer;
	
	public Move(String s) {
		this.describer = s;
	}
	
	@Override
	public String toString() {
		return this.describer;
	} 

}

class MoveDirection extends Move {
	
		public MoveDirection(String s) {
			super(s);
		}
}

class MoveAttack extends Move {
	public MoveAttack(int targetId) {
		super(String.format("a%d", targetId));
	}
}
