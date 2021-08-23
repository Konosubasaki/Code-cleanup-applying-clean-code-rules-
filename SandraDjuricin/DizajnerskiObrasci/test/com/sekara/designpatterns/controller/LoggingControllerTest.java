package com.sekara.designpatterns.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sekara.designpatterns.view.frame.FrameDrawing;

class LoggingControllerTest {
	private FrameDrawing frame;
	private LoggingController loggingController;
	@BeforeEach
	public void initialization() {
		frame= new FrameDrawing();
		loggingController=new LoggingController(frame);
	}

	@Test
	void testAddLog() {
		String log="Line(X1:1|Y1:1|X2:1|Y2:10|EdgeColor:-16777216)";
		loggingController.addLog(log);
		assertFalse(loggingController.getLogger().isEmpty());	
	}

	@Test
	void testIsLogEmpty() {
		String log="Line(X1:1|Y1:1|X2:1|Y2:10|EdgeColor:-16777216)";
		assertTrue(loggingController.isLogEmpty());

		loggingController.addLog(log);
		assertFalse(loggingController.isLogEmpty());	
	}
}
