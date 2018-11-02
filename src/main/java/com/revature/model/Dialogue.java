package com.revature.model;

public abstract class Dialogue {
	private  String message;
	private  int ID;
	public boolean requiresAction = false;
	
	public static final Dialogue dialogueList[] = {
			new Dialogue_1(),
			new Dialogue_2(),
			new Dialogue_3(),
			new Dialogue_4(),
			new Dialogue_5(),
			new Dialogue_6()
	};
	
	public Dialogue(String message, int ID) {
		super();
		this.message = message;
		this.ID = ID;
	}
	
	public Dialogue(String message, int ID, boolean requiresAction) {
		super();
		this.message = message;
		this.requiresAction = requiresAction;
		this.ID = ID;
	}
	
	public int performAction(String action) {
		return getID();
	};

	public String getMessage() {
		return message;
	}

	public int getID() {
		return ID;
	}
	
}
