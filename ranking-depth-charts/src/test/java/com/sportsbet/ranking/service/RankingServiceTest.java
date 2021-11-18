package com.sportsbet.ranking.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sportsbet.ranking.model.Player;
import com.sportsbet.ranking.domain.Players;
import com.sportsbet.ranking.repository.RankingRepository;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class RankingServiceTest {

	@InjectMocks
	RankingService rankingService;
	
	@Mock
	RankingRepository rankingRepository;

	@Test
	public void getPlayersUnderPlayerInDepthChartTest() {
		Player playerDto = new Player();
		playerDto.setDepth(0);
		playerDto.setPlayerId(10);
		playerDto.setPosition("WR");
		playerDto.setName("Ten");
		Players players = new Players();
		com.sportsbet.ranking.domain.Player player1 = new com.sportsbet.ranking.domain.Player();
		player1.setDepth(1);
		player1.setPlayerId(11);
		player1.setPosition("WR");
		player1.setName("Eleven");
		com.sportsbet.ranking.domain.Player player2 = new com.sportsbet.ranking.domain.Player();
		player2.setDepth(2);
		player2.setPlayerId(12);
		player2.setPosition("WR");
		player2.setName("Twelve");
		players.add(player1);
		players.add(player2);
		Mockito.when(rankingRepository.findByPosition("WR")).thenReturn(players);
		List<com.sportsbet.ranking.domain.Player> playersUnderDepthChart = rankingService.getPlayersUnderPlayerInDepthChart(playerDto);
		assertEquals("Not the same order", 11, playersUnderDepthChart.get(0).getPlayerId());
		assertEquals("Not the same order", 12, playersUnderDepthChart.get(1).getPlayerId());
	}

}
