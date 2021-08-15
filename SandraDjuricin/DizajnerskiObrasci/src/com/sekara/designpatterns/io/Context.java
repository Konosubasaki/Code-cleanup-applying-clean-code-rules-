package com.sekara.designpatterns.io;

import java.io.File;

public class Context implements Strategy {
	
	private Strategy strategy;
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void saveToFile(File file) {
		strategy.saveToFile(file);
	}

	@Override
	public void readFromFile(File file) {
		strategy.readFromFile(file);
	}
}
