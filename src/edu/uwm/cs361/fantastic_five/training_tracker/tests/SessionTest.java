package edu.uwm.cs361.fantastic_five.training_tracker.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Session;


public class SessionTest {
	Session session;
	
	@Before
	public void setUpTest() {
		this.session = new Session("12-12-2013");
	}
	
	@Test
	public void testGetDate() {
		assertEquals("12-12-2013", session.getDate() );
	}

}
