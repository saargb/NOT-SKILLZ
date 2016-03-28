package bots;

import java.util.Iterator;
import java.util.List;

import pirates.game.Location;
import pirates.game.Pirate;
import pirates.game.PirateGame;

public class attacker {
	List<Pirate> pirates;
	int speed;
	private final int KAMIKAZE_SPEED;
	private final int AMBUSH_SPEED;

	// List<Location> target;
	public attacker(List<Pirate> pirates, int MovesPerTurn) {
		this.pirates = pirates;
		speed = 0;
		AMBUSH_SPEED = (MovesPerTurn / pirates.size()) - 1;
		KAMIKAZE_SPEED = MovesPerTurn - 1;// if you need to shoot at same time
	}

	public boolean isAttacker(Pirate pirate) {
		for (Pirate p : pirates) {
			if (p.getId() == pirate.getId())
				return true;
		}
		return false;
	}

	public Pirate getById(int id) {
		for (Pirate pirate : pirates) {
			if (pirate.getId() == id)
				return pirate;
		}
		return null;
	}

	public boolean isKamikaze(int pirateID) {
		return getById(pirateID).getReloadTurns() > 0;
	}

	public int numberOfKamikaze() {
		int number = 0;
		for (Pirate pirate : pirates) {
			if (isKamikaze(pirate.getId()))
				number++;
		}
		return number;
	}

	public boolean isAmbush(int pirateID) {
		return !isKamikaze(pirateID);
	}

	/**
	 * 
	 * @param game
	 * @param pirateID
	 * @return of closest enemy pirate
	 */
	public Pirate getClosestPirate(PirateGame game, int pirateID) {
		int min = game.distance(getById(pirateID), game.enemyPiratesWithTrasures().get(0));
		Pirate ID = game.enemyPiratesWithTrasures().get(0);
		for (Pirate pirate : game.enemyPiratesWithTrasures()) {
			if (game.distance(getById(pirateID), pirate) < min) {
				min = game.distance(getById(pirateID), pirate);
				ID = pirate;
			}
		}
		return ID;
	}

	public Pirate Shoot(int pirateID, PirateGame game) {
		for (Pirate pirate : game.enemySoberPirates()) {
			if (pirate.hasTreasure() && game.inRange(getById(pirateID), pirate))
				;
			return pirate;
		}
		return null;
	}

	public void doTurn(PirateGame game) {
		boolean fire = true; // no one shoot yet
		boolean huntDown = false; // someone is kamikaze
		int notUsed = 0;
		for (Pirate pirate : pirates) {
			if (numberOfKamikaze() == 0) {
				if (pirate.getTurnsToSober() == 0) {
					if (!pirate.getLocation().equals(game.allEnemyPirates().get(pirate.getId()).getInitialLocation())) {
						game.setSail(pirate,
								game.getSailOptions(pirate,
										game.allEnemyPirates().get(pirate.getId()).getInitialLocation(), AMBUSH_SPEED)
								.get(0));
					} else {
						notUsed += AMBUSH_SPEED;
					}
					if (Shoot(pirate.getId(), game) != null && fire) {
						game.attack(pirate, Shoot(pirate.getId(), game));
						fire = false;
					}
				} else {
					notUsed += AMBUSH_SPEED;
				}
			} // end of no kamikaze
			else {
				if (!huntDown && pirate.getTurnsToSober() == 0) {
					if (getClosestPirate(game, pirate.getId()) != null) {
						if (game.getPirateOn(getClosestPirate(game, pirate.getId()).getInitialLocation()) == null) {
							game.setSail(pirate,
									game.getSailOptions(pirate,
											getClosestPirate(game, pirate.getId()).getInitialLocation(), KAMIKAZE_SPEED)
									.get(0));
							notUsed += KAMIKAZE_SPEED - game.distance(pirate,
									game.getSailOptions(pirate,
											getClosestPirate(game, pirate.getId()).getInitialLocation(), KAMIKAZE_SPEED)
									.get(0));
							huntDown=true;//go get him!
						}
					}
				}
			}
		}
		if (fire) {
			notUsed++;
		}
	}
}
