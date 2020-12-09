package com.sekara.designpatterns.command;

public abstract class Command {
	
	private String log;

	public String getLog() {
		return log;
	}

	protected void setLog(String log) {
		this.log = log;
	}
	
	public abstract void execute();
	
	public abstract void unExecute();
	
}