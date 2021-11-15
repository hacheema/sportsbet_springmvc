package com.sportsbet.ranking.model;

import java.util.Objects;

public class Player {

	int playerId;
	String name;
	private int depth;
	private String position;
	
	

	public Player() {
		super();
	}

	public Player(int playerId, String name, int depth, String position) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.depth = depth;
		this.position = position;
	}
	
	public Player(int playerId, String name,String position) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(playerId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Player pl = (Player) obj;
		return Objects.equals(this.playerId,pl.getPlayerId());
	}
}
