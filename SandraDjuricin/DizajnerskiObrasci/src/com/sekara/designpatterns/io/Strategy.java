package com.sekara.designpatterns.io;

import java.io.File;

public interface Strategy {
	
	void saveToFile(File file);
	
	void readFromFile(File file);

}
