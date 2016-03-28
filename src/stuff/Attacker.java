package bots;

import pirates.game.Pirate;

public class Attacker {
	Pirate pirate;
	public Attacker(Pirate pirate) {
		this.pirate=pirate;
	}
	public int getID(){
		return pirate.getId();
	}
}
