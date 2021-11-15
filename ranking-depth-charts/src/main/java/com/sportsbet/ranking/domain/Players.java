package com.sportsbet.ranking.domain;

import java.util.LinkedList;
import java.util.Objects;

public class Players extends LinkedList<com.sportsbet.ranking.domain.Player> {

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode());
	}

}
