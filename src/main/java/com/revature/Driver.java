package com.revature;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exceptions.MissingContentException;
import com.revature.model.Dialogue;
import com.revature.model.KeywordLibrary;
import com.revature.model.PlayerCharacterEntity;

public class Driver {
	//keep track of the id of the current Dialogue and the previous Dialogue
	private int messageID = 1, prevMessageID;
	//The logger class
	private static final Logger LOGGER = Logger.getLogger(Driver.class);
	//The player object, there will only ever be 1, so make static
	public static PlayerCharacterEntity player;
	//The scanner to take in input
	private static Scanner scanner;
	
	public static void main(String arg[]) {
		new Driver();
	}
	
	public Driver() {
		scanner = new Scanner(System.in);
		Dialogue currentDialogue=null,previousDialogue=null;
		String dialogueText;
		
		//Prompt the player for a name and their favorite objects
		promptPlayerForFavorites();
		
		//Game Loop
		while (true) {
			//If the Dialogue does not exist, throw MissingContentException
			if (messageID-1>=Dialogue.dialogueList.length) {
				throw new MissingContentException();
			}
			
			//get the dialogue based on the ID
			currentDialogue =  Dialogue.dialogueList[messageID-1];
			
			//if the message is the same as the previous one, there is no need to
			//reprint the dialogue
			if (prevMessageID != messageID) {
				dialogueText = currentDialogue.getMessage();
				
				//place ** around the searchable objects in the dialogue
				for (int i=0;i<KeywordLibrary.entityObjects.length;i++) {
					dialogueText = dialogueText.replaceAll(KeywordLibrary.entityObjects[i], "*"+KeywordLibrary.entityObjects[i]+"*");
				}
				
				//print out the dialogue
				System.out.println(dialogueText);
				if (messageID == 6)
					break;
				System.out.println("What do you do?");
			}
			
			//If the dialogue requires a user action, ask for action and respond appropriately
			if (currentDialogue.requiresAction) {
				prevMessageID = messageID;
				messageID = currentDialogue.performAction(scanner.nextLine().trim().toLowerCase());
			} else {
				messageID = prevMessageID;
			}
		}
		scanner.close();
	}
	
	public void promptPlayerForFavorites() {
		String[] info = new String[4];
		System.out.println("What is your name?");
		info[0] = scanner.nextLine().trim();
		System.out.println("What is your favorite food?");
		info[1] = scanner.nextLine().trim();
		System.out.println("What is your melee weapon of choice?");
		info[2] = scanner.nextLine().trim();
		System.out.println("What is your favorite limb?");
		info[3] = scanner.nextLine().trim();
		
		player = new PlayerCharacterEntity(info[0],info[1],info[2],info[3]);
		
		player.sayHi();
		
		System.out.println("Press enter to begin");
		
		scanner.nextLine();
		
		System.out.println("Very good, beggining the game!");
	}
}
