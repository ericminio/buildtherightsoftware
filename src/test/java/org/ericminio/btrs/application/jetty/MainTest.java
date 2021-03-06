package org.ericminio.btrs.application.jetty;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.ericminio.btrs.application.jetty.FeatureHandler;
import org.ericminio.btrs.application.jetty.Main;
import org.ericminio.btrs.application.jetty.StaticFileHandler;
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
	
	@Test public void
	canStart() throws Exception {
		Thread thread = new Thread(new Runnable() {
			public void run() {
		        try {
					Main.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        thread.start();
		Thread.sleep( 1000 );
        assertTrue( main.getServer().isStarted() );
        main.getServer().stop();
	}
	
}