package com.revature.model;

import com.revature.exceptions.InvalidActionException;

public class Dialogue_1 extends Dialogue {
	
	public Dialogue_1() {
		super("You awake in an unfamiliar room, on a rather uncomfy bed."
				+ " On the wall opposite the bed lies an old wooden wardrobe, and to the left a simple"
				+ " wooden door. A loud thump coming from your the wardrobe startles you, after a small moment"
				+ " it occurs again. You listen for some time and realize it Whatever it is seems to be"
				+ " occuring at regular intervals.",1,true);
	}
	
	@Override
	public int performAction(String action) {
		String[] words = action.split(" ");
		if (words.length>=2) {
			if (words[0].equals("search") && words[1].equals("door")) {
				for (ObjectEntity obj: GameController.player.inventory)
					if (obj.getName().equals("key")) {
						return 6;
					}
				return 2;
			}
			else if (words[0].equals("search") && words[1].equals("bed")) {
				
				GameController.player.inventory.add(new ObjectEntity(GameController.player.getFavoriteMeleeWeapon()));
				
				return 3;
			}
			else if (words[0].equals("search") && words[1].equals("wardrobe")) {
				
				GameController.player.inventory.add(new ObjectEntity("key"));
				
				for (ObjectEntity obj: GameController.player.inventory) {
					if (obj.getName().equals(GameController.player.getFavoriteMeleeWeapon())) {
						return 5;
					}
				}
				return 4;
			}
		}
		
		
		try {
			throw new InvalidActionException();
		} catch (InvalidActionException e) {
			
			e.printStackTrace();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			return getID();
		}
	}
	
}
