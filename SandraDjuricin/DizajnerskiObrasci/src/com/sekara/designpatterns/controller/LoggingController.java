package com.sekara.designpatterns.controller;

import com.sekara.designpatterns.command.*;
import com.sekara.designpatterns.enumerator.*;
import com.sekara.designpatterns.io.*;
import com.sekara.designpatterns.model.ModelDrawing;
import com.sekara.designpatterns.model.geometry.*;
import com.sekara.designpatterns.observable.Subject;
import com.sekara.designpatterns.observable.Observer;
import com.sekara.designpatterns.view.dialog.*;
import com.sekara.designpatterns.view.frame.FrameDrawing;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

public class LoggingController {

	private FrameDrawing frame;
	private DefaultListModel<String> logger;
	private boolean isLogEmpty;

	public LoggingController(FrameDrawing frame) {
		this.frame = frame;
		this.logger = this.frame.getDrawToolbar().getDefaultListLogModel();
		isLogEmpty = true;
	}

	public void addLog(String log) {
		logger.addElement(log);
		isLogEmpty = false;
	}

	public boolean isLogEmpty() {
		return isLogEmpty;
	}

}
