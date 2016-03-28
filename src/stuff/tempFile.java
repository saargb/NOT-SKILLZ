package bots;

import java.util.List;

import pirates.game.Location;
import pirates.game.Pirate;
import pirates.game.PirateBot;
import pirates.game.PirateGame;
import pirates.game.Treasure;

public class tempFile implements PirateBot {
	int delay = 5;

	public Location des(PirateGame game, Pirate p, int b) {
		if (p.hasTreasure()) {
			return p.getInitialLocation();
		}
		int dis = Integer.MAX_VALUE;
		Treasure a = null;
		for (Treasure t : game.treasures()) {
			if (game.distance(p, t) < dis) {
				b *= 2;
				dis = game.distance(p, t);
				if ((b / 2) % 2 == 0)
					a = t;
			}
		}
		return a.getLocation();
	}

	public Treasure closestTreasure(Location l, PirateGame game) {
		Treasure t = game.treasures().get(0);
		for (int i = 1; i < game.treasures().size(); i++) {
			if (distance(l, game.treasures().get(i).getLocation()) > distance(
					l, t.getLocation())) {
				t = game.treasures().get(i);
			}
		}
		return t;
	}

	public double distance(Location l1, Location l2) {
		return Math.sqrt(Math.pow(l1.col - l2.col, 2)
				+ Math.pow(l1.row - l2.row, 2));
	}

	public int speed(Pirate p, int turn) {
		if (p.hasTreasure()) {
			return 1;
		}
		if (turn < delay) {
			return 6;
		}
		return 3;
	}

	public void oneVOne(PirateGame game) {
		Pirate p = game.myPirates().get(0);
		int moves;
		Location destination;
		if (game.enemyDrunkPirates().isEmpty() && !p.hasTreasure()) {
			destination = game.getEnemyPirate(0).getInitialLocation();
			moves = 6;
		} else if (!p.hasTreasure()) {
			destination = closestTreasure(p.getLocation(), game).getLocation();
			moves = 6;
		} else {
			destination = p.getInitialLocation();
			moves = 1;
		}
		List<Location> possibleLocations = game.getSailOptions(p, destination,
				moves);
		Location tempDest = possibleLocations.get(0);
		game.setSail(p, tempDest);
		if (game.enemyDrunkPirates().isEmpty()) {
			if (game.inRange(p, game.enemyPirates().get(0))) {
				game.attack(p, game.getEnemyPirate(0));
			}
		}
	}

	public void coop(PirateGame game) {
		if (!game.enemyPiratesWithTreasures().isEmpty()) {
			if (game.mySoberPirates().get(0).getReloadTurns() > 0) {
				game.setSail(
						game.mySoberPirates().get(0),
						game.getSailOptions(game.mySoberPirates().get(0), game
								.enemyPiratesWithTreasures().get(0)
								.getInitialLocation(), 6).get(0));
			} else {
				if (game.inRange(game.mySoberPirates().get(0), game
						.enemyPiratesWithTreasures().get(0))) {
					game.attack(game.mySoberPirates().get(0), game
							.enemyPiratesWithTreasures().get(0));
				} else {
					game.setSail(
							game.mySoberPirates().get(0),
							game.getSailOptions(
									game.mySoberPirates().get(0),
									game.enemyPiratesWithTreasures().get(0)
											.getLocation(), 6).get(0));
				}
			}
		} else {
			if (game.myPiratesWithTreasures().isEmpty()) {
				game.setSail(
						game.mySoberPirates().get(
								game.mySoberPirates().size() - 1), game
								.getSailOptions(
										game.mySoberPirates()
												.get(game.mySoberPirates()
														.size() - 1), game
												.treasures().get(0).getLocation(), 6).get(0));
			} else {
				game.setSail(game.myPiratesWithTreasures().get(0), game
						.getSailOptions(game.myPiratesWithTreasures().get(0),
								game.myPiratesWithTreasures().get(0)
										.getInitialLocation(), 1).get(0));
			}
		}
	}

	@Override
	public void doTurn(PirateGame game) {
		if (game.myPirates().size() == 1) {
			oneVOne(game);
		} else if (game.myPirates().size() == 2) {
			coop(game);
		} else {
			if (!game.mySoberPirates().isEmpty()) {
				game.setSail(
						game.mySoberPirates().get(0),
						game.getSailOptions(
								game.mySoberPirates().get(0),
								des(game, game.mySoberPirates().get(0), 0),
								speed(game.mySoberPirates().get(0),
										game.getTurn())).get(0));
				if (game.getTurn() > delay)
					game.setSail(
							game.mySoberPirates().get(
									game.mySoberPirates().size() - 1),
							game.getSailOptions(
									game.mySoberPirates().get(
											game.mySoberPirates().size() - 1),
									des(game,
											game.mySoberPirates().get(
													game.mySoberPirates()
															.size() - 1), 2),
									speed(game.mySoberPirates().get(
											game.mySoberPirates().size() - 1),
											game.getTurn())).get(0));
			}
		}
	}
}