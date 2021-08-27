package com.sekara.designpatterns.observable;

import com.sekara.designpatterns.enumerator.ModeType;

public interface Observer {

	void updateUndoRedoButtonsState(int numOfUndoCommands, int numOfRedoCommands);

	void updateFileButtonState(int numOfShapes, boolean logHasLines);

	void updateShapeManipulationButtonsState(int numOfSelectedShapes, ModeType currentMode, int numOfShapes);
	
}
 