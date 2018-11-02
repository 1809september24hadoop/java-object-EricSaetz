package com.revature;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exceptions.MissingContentException;
import com.revature.model.Dialogue;
import com.revature.model.KeywordLibrary;
import com.revature.model.PlayerCharacterEntity;

public class Driver {
	private int messageID = 1, prevMessageID;
	private static final Logger LOGGER = Logger.getLogger(Driver.class);
	public static PlayerCharacterEntity player;
	private static Scanner scanner;
	
	public static void main(String arg[]) {
		new Driver();
	}
	
	public Driver() {
		scanner = new Scanner(System.in);
		Dialogue currentDialogue=null,previousDialogue=null;
		String dialogueText;
		
		promptPlayerForFavorites();
		
		while (true) {
			if (messageID-1>=Dialogue.dialogueList.length) {
				throw new MissingContentException();
			}
			currentDialogue =  Dialogue.dialogueList[messageID-1];
			if (prevMessageID != messageID) {
				dialogueText = currentDialogue.getMessage();
				
				for (int i=0;i<KeywordLibrary.entityObjects.length;i++) {
					dialogueText = dialogueText.replaceAll(KeywordLibrary.entityObjects[i], "*"+KeywordLibrary.entityObjects[i]+"*");
				}
				
				System.out.println(dialogueText);
				if (messageID == 6)
					break;
				System.out.println("What do you do?");
			}
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
