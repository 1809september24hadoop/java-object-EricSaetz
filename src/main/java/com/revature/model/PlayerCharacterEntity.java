package com.revature.model;

import com.revature.interfaces.RealHuman;

public class PlayerCharacterEntity extends CharacterEntity implements RealHuman {
	private String favoriteFood;
	private String favoriteMeleeWeapon;
	private String favoriteLimb;
	
	public PlayerCharacterEntity(String name, String favoriteFood, String favoriteMeleeWeapon, String favoriteLimb) {
		super(name);
		this.favoriteFood = favoriteFood;
		this.favoriteMeleeWeapon = favoriteMeleeWeapon;
		this.favoriteLimb = favoriteLimb;
	}

	public String getFavoriteFood() {
		return favoriteFood;
	}

	public String getFavoriteMeleeWeapon() {
		return favoriteMeleeWeapon;
	}

	public String getFavoriteLimb() {
		return favoriteLimb;
	}
	
	@Override
	public void sayHi() {
		System.out.println("Hello! My name is " + getName()+". My favorite food is "
		+ favoriteFood + " and I love my " + favoriteMeleeWeapon + " and my "+ favoriteLimb);
	}
}
