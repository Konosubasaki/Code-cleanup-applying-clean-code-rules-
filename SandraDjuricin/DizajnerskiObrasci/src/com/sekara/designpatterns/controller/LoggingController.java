package com.sekara.designpatterns.controller;

import com.sekara.designpatterns.view.frame.FrameDrawing;
import javax.swing.*;

public class LoggingController {

	private FrameDrawing frame;
	private DefaultListModel<String> logger;
	private boolean isLogEmpty;

	public LoggingController(FrameDrawing frame) {
		this.frame = frame;
		logger = this.frame.getDrawToolbar().getDefaultListLogModel();
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
