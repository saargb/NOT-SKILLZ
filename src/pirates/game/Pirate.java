package pirates.game;

public class Pirate extends MapEntity implements Comparable<Pirate> {

	private int id;
	private int owner;
	private Location initialLocation;
	private boolean isLost;
	private int turnsToRevive;
	private boolean hasTreasure;
	private int reloadTurns;
	private int turnsToSober;

	public Pirate (int id, int owner, Location location, Location initialLocation, boolean isLost, int turnsToRevive,
			boolean hasTreasure,
			int reloadTurns,
			int turnsToSober)

	{
		super(location);
		this.id = id;
		this.owner = owner;
		this.initialLocation = initialLocation;
		this.isLost = isLost;
		this.turnsToRevive = turnsToRevive;
		this.hasTreasure = hasTreasure;
		this.reloadTurns= reloadTurns;
		this.turnsToSober = turnsToSober;
	}

	public int getId() {
		return id;
	}

	public int getOwner() {
		return owner;
	}

	public Location getInitialLocation() {
		return initialLocation;
	}

	public boolean isLost() {
		return isLost;
	}
	
	public int getTurnsToRevive() {
		return turnsToRevive;
	}

	public boolean hasTreasure() {
		return hasTreasure;
	}
		
	public int getReloadTurns() {
		return reloadTurns;
	}
		
	public int getTurnsToSober() {
		return turnsToSober;
	}
		

	@Override
	public int hashCode() {
		return this.id * 10 + this.owner;

	}
	
	@Override
	public String toString() {
		Location location = super.getLocation();
		return String.format ("Pirate ID:%1$s OWNER:%2$s LOC:(%3$s, %4$s)", id, owner, location.row, location.col);
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Pirate && this.id == ((Pirate)obj).id && this.owner == ((Pirate)obj).owner; 
	}
	
	@Override
	public int compareTo(Pirate o) {
		int idDiff = this.getId() - o.getId();
		if (idDiff == 0) {
			return this.getOwner() - o.getOwner();
		} else {
			return idDiff;
		}
	}
	
}
