package com.sekara.designpatterns.controller;

import com.sekara.designpatterns.toolbars.DrawToolbar;
import com.sekara.designpatterns.view.frame.FrameDrawing;
import javax.swing.*;

public class LoggingController {

	private DefaultListModel<String> logger;
	private boolean isLogEmpty;

	public LoggingController(FrameDrawing frame) {
		DrawToolbar toolbar=frame.getDrawToolbar();
		logger = toolbar.getDefaultListLogModel();
		isLogEmpty = true;
	}

	public DefaultListModel<String> getLogger() {
		return logger;
	}

	public void addLog(String log) {
		logger.addElement(log);
		isLogEmpty = false;
	}

	public boolean isLogEmpty() {
		return isLogEmpty;
	}

}
