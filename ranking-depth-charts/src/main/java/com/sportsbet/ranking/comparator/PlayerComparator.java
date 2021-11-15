package com.sportsbet.ranking.comparator;

import java.util.Comparator;


public class PlayerComparator implements Comparator<com.sportsbet.ranking.domain.Player> {

	public int compare(com.sportsbet.ranking.domain.Player p1, com.sportsbet.ranking.domain.Player p2) {
		return p1.getDepth() <= p2.getDepth() ? -1 : 0;
	}

}
