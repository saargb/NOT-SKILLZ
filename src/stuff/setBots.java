package bots;

import pirates.game.PirateBot;
import pirates.game.PirateGame;

public class setBots implements PirateBot {
	int cSize = 0;
	int aSize = 0;
	// c = collector , a = attacker
	int[] idType;
	/*		1 = collector , 2 = attacker , 
	4 = assigned collector (for set func) , 5 = assigned attacker (for set func)	*/
	Attacker[] attackers;
	Collector[] collectors;

	public void setPirates(PirateGame game) {
		if (game.treasures().size() < game.mySoberPirates().size()) {
			cSize = game.treasures().size();
			aSize = game.mySoberPirates().size() - cSize;
		} else {
			if (game.mySoberPirates().size() == 1) {
				cSize = 1;
				aSize = 0;
			} else {
				aSize = game.mySoberPirates().size() / 3;
				cSize = game.mySoberPirates().size() - aSize;
			}
		}
		collectors = new Collector[cSize];
		boolean entered;
		for (int i = 0; i < cSize; i++) {
			entered = false;
			for (int j = 0; j < idType.length; j++) {
				if (idType[j] == 1) {
					collectors[i] = new Collector(game.getMyPirate(j));
					idType[j] = 4;
					entered = true;
					break;
				}
			}
			if (!entered) {
				for (int j = 0; j < idType.length; j++) {
					if (idType[j]!= 4 && !(game.getMyPirate(j).getTurnsToSober()>0 || game.getMyPirate(j).getTurnsToRevive()>0)) {
						collectors[i] = new Collector(game.getMyPirate(j));
						idType[j] = 4;
						break;
					}
				}
			}
		}
		attackers = new Attacker[aSize];
		for (int i = 0; i < aSize; i++) {
			entered = false;
			for (int j = 0; j < idType.length; j++) {
				if (idType[j] == 2) {
					attackers[i] = new Attacker(game.getMyPirate(j));
					entered = true;
					idType[j] = 5;
					break;
				}
			}
			if (!entered) {
				for (int j = 0; j < idType.length; j++) {
					if (idType[j]!= 5 && idType[j]!=4 && !(game.getMyPirate(j).getTurnsToSober()>0 || game.getMyPirate(j).getTurnsToRevive()>0)) {
						attackers[i] = new Attacker(game.getMyPirate(j));
						idType[j] = 5;
						break;
					}
				}
			}
		}
		for (int i = 0; i < idType.length; i++) {
			idType[i] = idType[i] % 3;
		}
	}

	@Override
	public void doTurn(PirateGame game) {
		idType = new int[game.myPirates().size()];
		setPirates(game);
		game.debug("attackers:");
		for(int i = 0; i < attackers.length;i++){
			game.debug(attackers[i].getID()+"  ");
		}
		game.debug("collectors::");
		for(int i = 0; i < collectors.length;i++){
			game.debug(collectors[i].getID()+"  ");
		}
	}
}