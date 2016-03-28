package pirates.game;

public class Treasure extends MapEntity implements Comparable<Treasure> {

	private int id;

	public Treasure (int Id, Location location) {
		super(location);
		this.id = Id;		
	}

	
	public int getId() {
		return id;
	}
	@Override
	public int compareTo(Treasure o) {
		return this.id - o.id;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public String toString() {
		Location location = super.getLocation();
		return String.format("Treasure ID:%1$s LOC:%2$s, %3$s)", id, location.row, location.col);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Treasure &&  this.id == ((Treasure)obj).id;
	}
}