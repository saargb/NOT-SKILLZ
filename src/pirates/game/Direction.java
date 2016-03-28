package pirates.game;

public enum Direction {
	NORTH('n'),
	EAST('e'),
	SOUTH('s'),
	WEST('w'),
	NOTHING('-');
	
	private char describer;
	
	private Direction(char c) {
		this.describer = c;
	}
	
	@Override
	public String toString() {
		return new String(new char[] {this.describer});
	}
	
	public boolean isExist(char c) {
		if (c == NORTH.describer || c == EAST.describer || c == SOUTH.describer || c == WEST.describer)
			return true;
		return false;
	}
}
