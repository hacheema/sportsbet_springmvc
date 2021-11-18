package com.sportsbet.ranking.repository;

import com.sportsbet.ranking.domain.Player;
import com.sportsbet.ranking.domain.Players;

public interface RankingRepository {

	Players findByPosition(String position);
	
	public void savePlayer(Player playerToSave);

}