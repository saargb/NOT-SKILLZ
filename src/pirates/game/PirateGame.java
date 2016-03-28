package pirates.game;

import java.util.List;

public interface PirateGame {
	
	//================================================================================
    // Pirate API
    //================================================================================
	/** gets all your pirate ships. List is of constant length. 
	 * @return List of your pirates
	 * */
	public abstract List<Pirate> allMyPirates();

	/** Returns a list of your pirates which are on the map now.
	 * Length of list changes. If you have no pirates on the map - List will be empty.  
	 * @return List of your pirates on map 
	 * */
	public abstract List<Pirate> myPirates();

	public abstract List<Pirate> myPiratesWithTrasures();
	public abstract List<Pirate> myPiratesWithoutTreasures();
	public abstract List<Pirate> myDrunkPirates();
	public abstract List<Pirate> mySoberPirates();

	/** Returns a list of your lost pirate ships.
	 * List is empty if no pirates are lost.
	 * @return List of your lost pirates
	 * */
	public abstract List<Pirate> myLostPirates();

	/** gets all enemy pirate ships. List is of constant length. 
	 * @return List of enemy pirates
	 * */
	public abstract List<Pirate> allEnemyPirates();

	/** Returns a list of enemy pirates which are on the map now.
	 * Length of list changes. If enemy has no pirates on the map - List will be empty.  
	 * @return List of enemy pirates on map 
	 * */
	public abstract List<Pirate> enemyPirates();

	/** Returns a list of enemy lost pirate ships.
	 * List is empty if no pirates are lost.
	 * @return List of enemy lost pirates
	 * */
	public abstract List<Pirate> enemyLostPirates();

	public abstract List<Pirate> enemyPiratesWithTrasures();
	public abstract List<Pirate> enemyPiratesWithoutTreasures();
	public abstract List<Pirate> enemyDrunkPirates();
	public abstract List<Pirate> enemySoberPirates();

	/** Returns your pirate with the specified Id.
	 * @return Pirate with specified Id */
	public abstract Pirate getMyPirate(int id);

	/** Returns enemy pirate with the specified Id.
	 * @return enemy Pirate with specified Id */
	public abstract Pirate getEnemyPirate(int id);

	/**
	 * Return the pirate that is on the given Location
	 * Will be null if no Pirate is at this location
	 * @param location the location to get pirate from 
	 * @return Pirate at this location
	 */
	public abstract Pirate getPirateOn(Location location);

	//================================================================================
    // Treasures API
    //================================================================================
	/** Returns list of all available treasures.
	 * @return list of all available treasures */
	public abstract List<Treasure> treasures();

	//================================================================================
    // Movement API
    //================================================================================
	
	/**
	 * Returns a list of directions from loc1 to loc2
	 * @param loc1 first location
	 * @param loc2 second location
	 * @return List of Directions to get from loc1 to loc2
	 */
	public abstract List<Direction> getDirections(Location loc1, Location loc2);
	
	/**
	 * Returns a list of directions from the pirate to the Location
	 * @param pirate from pirate
	 * @param loc to location
	 * @return
	 */
	public abstract List<Direction> getDirections(Pirate pirate, Location loc);
	
	/**
	 * Returns a list of directions from the pirate to the island
	 * @param pirate from pirate
	 * @param island to island
	 * @return
	 */
	public abstract List<Direction> getDirections(Pirate pirate, Treasure treasure);
	
	/**
	 * Returns a list of directions from a pirate to another pirate
	 * @param pirate1 from pirate
	 * @param pirate2 to pirate
	 * @return
	 */
	public abstract List<Direction> getDirections(Pirate pirate1, Pirate pirate2);
	
	/**
	 * Commands a ??????
	 * @param pirate pirate ?????
	 * @param direction ??????
	 */
	public abstract List<Location> getSailOptions(Pirate pirate, Location destination, int distance);

	/**
	 * Commands a pirate to move in the specified direction
	 * @param pirate pirate to move
	 * @param direction the direction to move the pirate in
	 */
	public abstract void setSail(Pirate pirate, Location destination);
	
	/**
	 * Commands a pirate to move in the specified direction
	 * @param pirate - pirate which attacks
	 * @param target - the enemy target
	 */
	public abstract void attack(Pirate pirate, Pirate target);

	/**
	 * Commands a pirate to defend
	 * @param pirate - pirate to defend
	 */
	public abstract void defend(Pirate pirate);

	/**
	 * Calculates how many turns to move between the two locations
	 * @param loc1 from location
	 * @param loc2 to location
	 * @return number of turns to move between the locations 
	 */
	public abstract int distance(Location loc1, Location loc2);

	/**
	 * Calculates how many turns to move between the two locations
	 * @param pirate from 
	 * @param loc to
	 * @return number of turns to move between the locations 
	 */
	public abstract int distance(Pirate pirate, Location loc);

	/**
	 * Calculates how many turns to move between the two pirates
	 * @param pirate1 from pirate
	 * @param pirate2 to pirate
	 * @return number of turns between the pirates
	 */
	public abstract int distance(Pirate pirate1, Pirate pirate2);
	
	/**
	 * Calculates how many turns to move between the two pirates
	 * @param pirate from 
	 * @param treasure to
	 * @return number of turns between the pirates
	 */
	public abstract int distance(Pirate pirate, Treasure treasure);
	
	/**
	 * Calculates the new Loc if this pirate will move in this direction
	 * @param a Pirate
	 * @param d direction
	 * @return The new Loc after the pirate would move
	 */
	public abstract Location destination(Pirate a, List<Direction> directions);

	/**
	 * Calculates the Location at this direction from the location given
	 * @param loc Location
	 * @param d direction
	 * @return The Loc this direction from the old Loc
	 */
	public abstract Location destination(Location loc, List<Direction> directions);	
	
	/**
	 * Get the number of rows in the game map.
	 * @return number of rows
	 */
	public abstract int getRows();

	/**
	 * Get the number of columns in the game map.
	 * @return number of columns
	 */
	public abstract int getCols();

	
	//================================================================================
    // Debugging API
    //================================================================================
	
	
	/**
	 * Prints a formatted debug message
	 * 
	 * @param message
	 */
	public abstract void debug(String format, Object... args);
	
	/**
	 * Prints a message to the console 
	 * @param message message to print
	 */
	public abstract void debug(String message);
	
	/**
	 * Gets the turn (bigger than 0)
	 * @return the turn number 
	 */
	public abstract int getTurn();
	
	//================================================================================
    // Other API
    //================================================================================
	
	/**
	 * returns whether two pirates are in range
	 * @param a1 first pirate
	 * @param a2 second pirate
	 * @return boolean True if pirates in range, False otherwise.
	 */
	public abstract boolean inRange(Pirate a1, Pirate a2);

	/**
	 * returns whether a location is in a pirate's range
	 * @param a1 Pirate
	 * @param loc2 Location
	 * @return True if location in pirate range, False otherwise.
	 */
	public abstract boolean inRange(Pirate a1, Location loc2);

	/**
	 * returns if two locations are in range
	 * @param loc1 first Location
	 * @param loc2 second Location
	 * @return True if locations in range, False otherwise.
	 */
	public abstract boolean InRange(Location loc1, Location loc2);
	/**
	 * returns if two locations are in range
	 * @param loc1 first Location
	 * @param loc2 second Location
	 * @return True if locations in range, False otherwise.
	 */
	public abstract boolean inRange(Location loc1, Location loc2);

	/**
	 * Returns true if the Location is a Location your pirate can sail to.
	 * Will return false if Loc is in enemy zone or out of the map.
	 * @param location Location to check
	 * @return
	 */
	public abstract boolean isPassable(Location location);	
		
	/**
	 * Returns true if a pirate is at this Location
	 * @param loc Location to check
	 * @return 
	 */
	public abstract boolean isOccupied(Location loc);
	
	/**
	 * Returns an array of the players' scores
	 * first score is the current player's
	 * @return scores - array of scores
	 */
	public abstract int[] getScores();
	
	
	/**
	 * @return the player's score
	 */
	public abstract int getMyScore();
	
	/**
	 * @return the enemey's score
	 */
	public abstract int getEnemyScore();
	
	/**
	 * Return a list containing the points each team got last turn.
	 * 0 index belongs to current player.
	 * @return
	 */
	public abstract int[] getLastTurnPoints();
	
	/**
	 * Number of points needed to win
	 * @return
	 */
	public abstract int getMaxPoints();
	
	/**
	 * Maximum number of turns in the game
	 * @return
	 */
	public abstract int getMaxTurns();
	
	/**
	 * Time remaining for the current turn
	 * @return duration in milliseconds
	 */
	public abstract int getTimeRemaining();
	
	/**
	 * The attack radius in the game. Pirate ships who's distance squared is less or equal to this will attack each other
	 * @return
	 */
	public abstract int getAttackRadius();
	
	/**
	 * @return The number of turns for a lost ship to return to the game
	 */
	public abstract int getSpawnTurns();
	
	/**
	 * @return The actions that can be performed in one turn
	 */
	public abstract int getActionsPerTurn();

	/**
	 * 
	 * @return The number of turns until a ship becomes sober
	 */
	public abstract int getSoberTurns();
	
	/**
	 * 
	 * @return The number of turns until ship reloads
	 */
	public abstract int getReloadTurns();
	
	/**
	 * 
	 * @return The name of the opponent bot
	 */
	public abstract String getOpponentName();
}