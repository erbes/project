package edu.uwm.cs361.fantastic_five.training_tracker.tests;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;

public abstract class AppEngineTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalTaskQueueTestConfig(), new LocalDatastoreServiceTestConfig());

	@Before
	public void setUpHelper() {
		helper.setUp();
	}

	@After
	public void tearDownHelper() {
		helper.tearDown();
	}

	protected PersistenceManager getPersistenceManager()
	{
		return JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
	}
}
