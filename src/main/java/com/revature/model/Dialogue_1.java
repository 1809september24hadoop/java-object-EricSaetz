package com.revature.model;

import com.revature.Driver;
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
			//If the player attempts to search the door, different results depending
			//on whether or not the player has the key
			if (words[0].equals("search") && words[1].equals("door")) {
				for (ObjectEntity obj: Driver.player.inventory)
					if (obj.getName().equals("key")) {
						return 6;
					}
				return 2;
			}
			//If the player searches the bed, give the player the weapon
			else if (words[0].equals("search") && words[1].equals("bed")) {
				
				Driver.player.inventory.add(new ObjectEntity(Driver.player.getFavoriteMeleeWeapon()));
				
				return 3;
			}
			//If the player searches the wardrobe, different results depending on
			//whether or not the player has the weapon; gives the player the key
			else if (words[0].equals("search") && words[1].equals("wardrobe")) {
				
				Driver.player.inventory.add(new ObjectEntity("key"));
				
				for (ObjectEntity obj: Driver.player.inventory) {
					if (obj.getName().equals(Driver.player.getFavoriteMeleeWeapon())) {
						return 5;
					}
				}
				return 4;
			}
			//If the player searches himself
			else if (words[0].equals("search") && words[1].equals("self")) {
				return 7;
			}
		}
		
		
		/*If an invalid command is input, throw an InvalidActionException
		/Then sleep for 100 ms because printStackTrace() is delayed
		/and we want it to show up before the next dialogue output
		*/
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
