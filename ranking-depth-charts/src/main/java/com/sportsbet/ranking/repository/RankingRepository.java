package com.sportsbet.ranking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportsbet.ranking.domain.Player;
import com.sportsbet.ranking.domain.Players;

@Repository
public interface RankingRepository extends JpaRepository<Player, String> {

	Players findByPosition(String position);

}