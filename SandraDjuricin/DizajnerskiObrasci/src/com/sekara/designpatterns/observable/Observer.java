package com.sekara.designpatterns.observable;

import com.sekara.designpatterns.enumerator.ModeType;

public interface Observer {
	
	void update(ModeType currentMode, int numOfShapes, int numOfUndoCommands, int numOfRedoCommands, int numOfSelectedShapes, boolean logHasLines);

}
