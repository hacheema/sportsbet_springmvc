package com.sportsbet.ranking.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportsbet.ranking.exception.UserNotFoundException;
import com.sportsbet.ranking.comparator.PlayerComparator;
import com.sportsbet.ranking.model.Player;
import com.sportsbet.ranking.domain.Players;
import com.sportsbet.ranking.repository.impl.RankingRepositoryImpl;

@Service
public class RankingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RankingService.class);

	@Autowired
	RankingRepositoryImpl playerRepository;

	/*
	 * This method ranks Players based on Depth for a position
	 * 
	 * @input: player
	 * 
	 * @output: void
	 */
	public void savePlayer(Player playerDto) {
		com.sportsbet.ranking.domain.Player playerToSave = new com.sportsbet.ranking.domain.Player();
		playerToSave.setId(playerDto.getPlayerId() + playerDto.getPosition());
		playerToSave.setName(playerDto.getName());
		playerToSave.setPosition(playerDto.getPosition());
		playerToSave.setDepth(playerDto.getDepth());
		playerToSave.setPlayerId(playerDto.getPlayerId());
		LOGGER.info("{} saved successfully", playerDto.getPlayerId());
		playerRepository.savePlayer(playerToSave);
	}

	/*
	 * This method ranks Players based on Depth for a position
	 * 
	 * @input: List of Players
	 * 
	 * @output: ascending order of Players based on depth
	 */
	public HashMap<String, Players> rankPlayers(Players playerList) {

		HashMap<String, Players> ranking = new HashMap<>();

		Collections.sort(playerList, new PlayerComparator());
		for (com.sportsbet.ranking.domain.Player player : playerList) {
			ranking.put(player.getPosition(), playerList);
		}
		return ranking;

	}

	/*
	 * This method deletes Player from his position
	 * 
	 * @input: players to delete
	 * 
	 * @output: Boolean : true if player exists, false otherwise
	 */
	/*public Boolean deletePlayersFromPosition(Player playerDto) throws UserNotFoundException {
		Optional<com.sportsbet.ranking.domain.Player> player = playerRepository
				.findById(playerDto.getPlayerId() + playerDto.getPosition());
		try {
			player.orElseThrow(() -> new UserNotFoundException("User Not Found"));

		} catch (UserNotFoundException e) {
			LOGGER.info("{} User Not Found", playerDto.getPlayerId());
			return false;
		}
		playerRepository.deleteById(playerDto.getPlayerId() + playerDto.getPosition());
		return true;

	}*/

	/*
	 * This method fetch Players based on the position
	 * 
	 * @input: player
	 * 
	 * @output: List of Player
	 */
	public Players fetchPlayersForPosition(Player playerDto) {
		return playerRepository.findByPosition(playerDto.getPosition());
	}

	/*
	 * This method fetch all Players for all positions
	 * 
	 * @input: none
	 * 
	 * @output: List of players for all positions
	 */
	/*public List<com.sportsbet.ranking.domain.Player> getFullDepthChart() {
		return playerRepository.findAll();
	}*/

	/*
	 * This method fetch Players listed below for given Player
	 * 
	 * @input: Player
	 * 
	 * @output: List of Players below on depth for given Player
	 */
	public List<com.sportsbet.ranking.domain.Player> getPlayersUnderPlayerInDepthChart(Player playerDto) {
		List<com.sportsbet.ranking.domain.Player> playerForPosition = fetchPlayersForPosition(playerDto);
		List<com.sportsbet.ranking.domain.Player> playerForPositionSorted = new ArrayList<com.sportsbet.ranking.domain.Player>();
		playerForPosition.forEach(eachPlayer -> {
			if (eachPlayer.getPlayerId() != playerDto.getPlayerId() && eachPlayer.getDepth() > playerDto.getDepth()) {
				playerForPositionSorted.add(eachPlayer);
			}
		});
		return playerForPositionSorted;
	}
}
