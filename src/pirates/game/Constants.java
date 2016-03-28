package pirates.game;

import java.util.EnumMap;
import java.util.Map;

public class Constants {
	public static final int ME = 0;
	public static final int NO_OWNER = -1;
	public static final int ENEMY = 1;

	public static final int PIRATES = 0;
	public static final int LOST = -1;

	public static final int ISLAND = -1;
	public static final int WATER = -2;
	public static final int ZONE = -4;

	public static final char LAND_IN_PARSE = 'l';
	public static final char WATER_IN_PARSE = 'w';
	public static final char TREASURE_IN_PARSE = '$';

	public static final String PLAYER_PIRATE = "abcdefghij";
	public static final String ISLAND_PIRATE = "ABCDEFGHIJ";
	public static final String PLAYER_ISLAND = "0123456789";
	public static final String MAP_OBJECT = "?%*.!";
	public static final String MAP_RENDER = PLAYER_PIRATE + ISLAND_PIRATE + PLAYER_ISLAND + MAP_OBJECT;
	public static final Map<Direction, Location> AIM = new EnumMap<Direction, Location>(Direction.class);
	static {
		AIM.put(Direction.NORTH, new Location(-1, 0));
		AIM.put(Direction.EAST, new Location(0, 1));
		AIM.put(Direction.SOUTH, new Location(1, 0));
		AIM.put(Direction.WEST, new Location(0, -1));
		AIM.put(Direction.NOTHING, new Location(0, 0));
	}
}
