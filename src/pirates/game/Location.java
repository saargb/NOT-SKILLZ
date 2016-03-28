package pirates.game;

public class Location implements Comparable<Location> {
	public final int row;
	public final int col;
	
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}
	
	@Override
	public int compareTo(Location other) {
		int rowDiff = this.row - other.row;
		int colDiff = this.col - other.col;
		if (rowDiff != 0) {
			return colDiff;
		} else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "<" + row + ", " + col + ">";
	}
	
}
