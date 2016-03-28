package bots;

import pirates.game.Pirate;

public class Collector {
	Pirate pirate;
	public Collector(Pirate pirate) {
	this.pirate = pirate;	
	}
	public int getID(){
		return pirate.getId();
	}
}
