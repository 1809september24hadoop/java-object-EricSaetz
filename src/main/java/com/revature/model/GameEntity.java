package com.revature.model;

public abstract class GameEntity {
	private String name;

	public GameEntity(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
