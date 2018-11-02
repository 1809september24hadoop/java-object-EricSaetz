package com.revature.model;

import java.util.ArrayList;

public class CharacterEntity extends GameEntity {
	
	ArrayList<ObjectEntity> inventory;
	
	public CharacterEntity(String name) {
		super(name);
		inventory = new ArrayList<ObjectEntity>();
	}
}
