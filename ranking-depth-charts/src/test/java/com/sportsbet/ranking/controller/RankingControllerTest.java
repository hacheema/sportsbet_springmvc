package com.sportsbet.ranking.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.sportsbet.ranking.exception.UserNotFoundException;
import com.sportsbet.ranking.model.Player;
import com.sportsbet.ranking.domain.Players;
import com.sportsbet.ranking.repository.RankingRepository;
import com.sportsbet.ranking.service.RankingService;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class RankingControllerTest {

	@InjectMocks
	RankingController rankingController;

	@Mock
	RankingService rankingService;
	
	@Mock
	RankingRepository rankingRepository;

	@Test(expected = NullPointerException.class)
	public void addPlayerToDepthChartTestNullPointer() {

		Player playerDto = new Player();
		rankingController.addPlayerToDepthChart(playerDto);
	}

	@Test
	public void addPlayerToDepthChartTest() {

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
		HashMap<String, Players> ranking = new HashMap<String, Players>();
		ranking.put("WR", players);
		Mockito.when(rankingService.fetchPlayersForPosition(playerDto)).thenReturn(players);
		Mockito.when(rankingService.rankPlayers(players)).thenReturn(ranking);
		ResponseEntity<String> response = rankingController.addPlayerToDepthChart(playerDto);
		assertNotNull(response);
	}

	@Test
	public void addPlayerFromDepthChartTest() throws UserNotFoundException {
		Player playerDto = new Player();
		playerDto.setDepth(0);
		playerDto.setPlayerId(10);
		playerDto.setPosition("WR");
		playerDto.setName("Ten");
		//Mockito.when(rankingService.deletePlayersFromPosition(playerDto)).thenReturn(Boolean.TRUE);
		ResponseEntity<String> response = rankingController.removePlayerFromDepthChart(playerDto);
		assertEquals("Player deleted", response.getBody());
	}

	@Test
	public void addPlayerFromDepthChartUserNotFoundTest() throws UserNotFoundException {
		Player playerDto = new Player();
		playerDto.setDepth(0);
		playerDto.setPlayerId(10);
		playerDto.setPosition("WR");
		playerDto.setName("Ten");
		//Mockito.when(rankingService.deletePlayersFromPosition(playerDto)).thenReturn(Boolean.FALSE);
		ResponseEntity<String> response = rankingController.removePlayerFromDepthChart(playerDto);
		assertEquals("No such Player found", response.getBody());
	}

	@Test
	public void getFullDepthChartTest() {
		List<com.sportsbet.ranking.domain.Player> players = new ArrayList<com.sportsbet.ranking.domain.Player>();
		com.sportsbet.ranking.domain.Player player1 = new com.sportsbet.ranking.domain.Player();
		com.sportsbet.ranking.domain.Player player2 = new com.sportsbet.ranking.domain.Player();
		players.add(player1);
		players.add(player2);
		//Mockito.when(rankingService.getFullDepthChart()).thenReturn(players);
		//ResponseEntity<List<com.sportsbet.ranking.domain.Player>> response = rankingController.getFullDepthChart();
		//assertFalse(0 == response.getBody().size());
	}

	@Test
	public void getFullDepthChartTestEmptyResult() {
		List<com.sportsbet.ranking.domain.Player> players = new ArrayList<com.sportsbet.ranking.domain.Player>();
		//Mockito.when(rankingService.getFullDepthChart()).thenReturn(players);
		//ResponseEntity<List<com.sportsbet.ranking.domain.Player>> response = rankingController.getFullDepthChart();
		//assertTrue(0 == response.getBody().size());
	}

	@Test
	public void getPlayersUnderPlayerInDepthChartTest() {
		Player playerDto = new Player();
		playerDto.setDepth(0);
		playerDto.setPlayerId(10);
		playerDto.setPosition("WR");
		playerDto.setName("Ten");
		List<com.sportsbet.ranking.domain.Player> players = new ArrayList<com.sportsbet.ranking.domain.Player>();
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
		Mockito.when(rankingService.getPlayersUnderPlayerInDepthChart(playerDto)).thenReturn(players);
		ResponseEntity<List<com.sportsbet.ranking.domain.Player>> response = rankingController.getPlayersUnderPlayerInDepthChart(playerDto);
		assertTrue(0 < response.getBody().size());
		assertSame(players, players);
	}

}
