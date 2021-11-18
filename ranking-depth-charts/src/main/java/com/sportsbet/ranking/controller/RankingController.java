package com.sportsbet.ranking.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sportsbet.ranking.exception.UserNotFoundException;
import com.sportsbet.ranking.model.Player;
import com.sportsbet.ranking.config.ApplicationConfiguration;
import com.sportsbet.ranking.domain.Players;
import com.sportsbet.ranking.service.RankingService;

@RestController
@RequestMapping(value = "/ranking")
public class RankingController {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(RankingController.class);

	@Autowired
	RankingService rankingService;

	@PostMapping(value = "/save", produces = { "application/text" }, consumes = { "application/json" })
	public ResponseEntity<String> addPlayerToDepthChart(@RequestBody Player playerDto) {

		LOGGER.info("Going to save Player: {}",playerDto.getPlayerId());
		rankingService.savePlayer(playerDto);

		Players playerListFromDB = rankingService.fetchPlayersForPosition(playerDto);

		if (playerListFromDB.size() == 1) {
			return new ResponseEntity<String>(
					playerDto.getPosition() + ":" + Arrays.asList(playerListFromDB.get(0).getPlayerId()),
					HttpStatus.OK);
		}

		HashMap<String, Players> ranking = rankingService.rankPlayers(playerListFromDB);
		List<Integer> playerIDList = new ArrayList<Integer>();
		Players players = ranking.get(playerDto.getPosition());
		for (int i = 0; i < players.size(); i++) {
			playerIDList.add(players.get(i).getPlayerId());
		}
		return new ResponseEntity<String>(playerDto.getPosition() + ":" + playerIDList, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete", produces = { "application/text" }, consumes = { "application/json" })
	public ResponseEntity<String> removePlayerFromDepthChart(@RequestBody Player playerDto)
			throws UserNotFoundException {
		LOGGER.info("Going to remove Player: {} from: {}", playerDto.getPlayerId(), playerDto.getPosition());
		//Boolean isSuccess = rankingService.deletePlayersFromPosition(playerDto);
		Boolean isSuccess=Boolean.TRUE;
		if (Boolean.TRUE.equals(isSuccess))
			return new ResponseEntity<>("Player deleted", HttpStatus.OK);
		else {
			return new ResponseEntity<>("No such Player found", HttpStatus.OK);
		}
	}

	/*@GetMapping(value = "/fetch", produces = { "application/json" })
	public ResponseEntity<List<com.sportsbet.ranking.domain.Player>> getFullDepthChart() {
		LOGGER.info("Going to fetch full depth chart");
		return new ResponseEntity<>(rankingService.getFullDepthChart(), HttpStatus.OK);
	}*/

	@GetMapping(value = "/fetch", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<List<com.sportsbet.ranking.domain.Player>> getPlayersUnderPlayerInDepthChart(
			@RequestBody Player playerDto) {
		LOGGER.info("Going to fetch Players below: {}", playerDto.getPlayerId());
		List<com.sportsbet.ranking.domain.Player> players = rankingService.getPlayersUnderPlayerInDepthChart(playerDto);
		return new ResponseEntity<List<com.sportsbet.ranking.domain.Player>>(players, HttpStatus.OK);
	}

}
