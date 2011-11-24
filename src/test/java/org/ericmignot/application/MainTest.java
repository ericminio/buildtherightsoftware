package org.ericmignot.application;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

	private Main main;
	
	@Before
	public void init() throws Exception {
		main = new Main();
	}
	
	@Test public void
	instantiatesServer() throws Exception {
		assertTrue( main.getServer() instanceof Server);
		assertNotNull( main.getServer() );
	}

	@Test
	public void initializeHandlerList() throws Exception {
		Server server = main.getServer();
		assertTrue( server.getHandler() instanceof HandlerCollection );
		HandlerCollection list = (HandlerCollection) server.getHandler();
		assertTrue( list.getHandlers()[0] instanceof StaticFileHandler );
		assertTrue( list.getHandlers()[1] instanceof FeatureHandler );
	}
	
}