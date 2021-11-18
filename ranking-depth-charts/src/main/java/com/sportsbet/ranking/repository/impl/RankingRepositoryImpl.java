package com.sportsbet.ranking.repository.impl;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sportsbet.ranking.domain.Player;
import com.sportsbet.ranking.domain.Players;
import com.sportsbet.ranking.repository.RankingRepository;
import com.sportsbet.ranking.service.RankingService;

@Repository
public class RankingRepositoryImpl implements RankingRepository{
	
	@Autowired
    private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RankingRepositoryImpl.class);

	@PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

	
	@Override
	public Players findByPosition(String position) {
		// TODO Auto-generated method stub
		return null;
	}
	
	String query ="INSERT INTO PLAYERS VALUES(?,?,?,?);";

	@Override
	public void savePlayer(Player playerToSave) {
		try {
             jdbcTemplate.execute(query);
        } catch (final Exception e) {
            LOGGER.error("Exception occurred in getShipmentStatusById", e);
        } finally {
            LOGGER.info("Saving Player method");
        }
		
	}}
