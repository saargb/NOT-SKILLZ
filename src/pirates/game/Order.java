
package pirates.game;

public class Order {
	private String moves;
	private Object[] arguments;
	
	public Order() {
		this.moves = new String();
		this.arguments = null;
	}
	
	public Order(String moves, Object... arguments) {
		this.moves = moves;
		this.arguments = arguments;
	}
	
	public void addMoves(String s) {
		moves += s;
	}
	
	public void addAttack(int targetId) {
		this.addMoves(String.format("a%d", targetId));
	}
	
	public void addDefense() {
		this.addMoves("d");
	}
	
	public String getMoves() {
		return moves;
	}
	
	public int size() {
		return moves.length();
	}
	
	public Object[] getArguments() {
		return arguments;
	}
	
	@Override
	public String toString() {
		return moves;
	}
}