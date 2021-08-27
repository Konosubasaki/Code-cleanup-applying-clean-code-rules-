package com.sekara.designpatterns.command;

public abstract class Command {

	private String log;

	public String getCommandLog() {
		return log;
	}

	protected void setCommandLog(String log) {
		this.log = log;
	}

	public abstract void execute();
 
	public abstract void unExecute();
}