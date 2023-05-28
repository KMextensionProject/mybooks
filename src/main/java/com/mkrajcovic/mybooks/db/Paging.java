package com.mkrajcovic.mybooks.db;

public final class Paging {

	private final int from;
	private final int to;

	public Paging(int from, int to) {
		this.from = from;
		this.to = to;
	}

	public int getFrom() {
		return this.from;
	}

	public int getTo() {
		return this.to;
	}
}
