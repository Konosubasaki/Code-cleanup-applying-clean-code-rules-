package com.sekara.designpatterns.view.dialog;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sekara.designpatterns.model.geometry.Circle;
import com.sekara.designpatterns.model.geometry.Point;

class DialogCircleTest {
	private Circle circle;
	private DialogCircle dialogCircle;
	@BeforeEach
	public void initialization() {
		dialogCircle=new DialogCircle();
	//	circle = new Circle(centerOfCircle,20, Color.BLACK, Color.WHITE);
  	}
	@Test
	void testGetCircle() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPoint() {
		fail("Not yet implemented");
	}

	@Test
	void testSetColors() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCircle() {
		fail("Not yet implemented");
	}

}
