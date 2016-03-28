package bots;

import static java.lang.Math.*;
import java.util.*;
import pirates.game.*;

public class MyBot implements PirateBot {
	
	final int TREASURE_HUNTER = 0;
	final int KAMIKAZIE1 = 1;
	final int KAMIKAZIE2 = 2;
	final int KAMIKAZIE3 = 3;
	
	final int MAX_TURNS = 6;
	int turnsLeft;
	
	@Override
	public void doTurn(PirateGame game) {
		kamikazie(game, game.getMyPirate(0));
	}
	
	public void kamikazie(PirateGame game, Pirate mine) {
		List<Location> options = game.getSailOptions(mine, getClosestMovingEnemyPirate(game, mine).getInitialLocation(), 6);
		game.setSail(mine, options.get(0));
	}
	
	public Pirate getClosestMovingEnemyPirate(PirateGame game, Pirate mine) {
		List<Pirate> enemies = game.allEnemyPirates();
		Pirate minEnemy = enemies.get(0);
		for (Pirate p : enemies) {
			if (p.getLocation().compareTo(p.getInitialLocation()) != 0) {
				if (worthIt(game, mine, p)) {
					minEnemy = p;
				}
			}
		}
		return minEnemy;
	}
	
	public boolean worthIt(PirateGame game, Pirate mine, Pirate enemy) {
		Location closestTreasure = getClosestTreasure(game.treasures(), enemy);
		return game.distance(enemy, closestTreasure) + game.distance(enemy.getInitialLocation(), closestTreasure)
				< game.distance(mine, enemy.getInitialLocation());
	}
	
	public void findAndRetrieveTreasure(PirateGame game) {
		Pirate pirate = game.getMyPirate(0);
		Location location = null;
		if (pirate.hasTreasure()) {
			location = game.getSailOptions(pirate, pirate.getInitialLocation(), 1).get(0);
			game.setSail(pirate, location);
		}
		else {
			location = game.getSailOptions(pirate, getClosestTreasure(game.treasures(), pirate), 6).get(0);
			game.setSail(pirate, location);
		}
	}
	
	public Location getClosestTreasure(List<Treasure> treasures, Pirate pirate) {
		Treasure min = treasures.get(0);
		for (Treasure t : treasures) {
			if (distance(pirate.getLocation(), t.getLocation()) < distance(pirate.getLocation(), min.getLocation())) {
				min = t;
			}
		}
		return min.getLocation();
	}
	
	public double distance(Location l1, Location l2) {
		return sqrt(pow(l1.row - l2.row, 2) + pow(l1.col - l2.col, 2));
	}

}
