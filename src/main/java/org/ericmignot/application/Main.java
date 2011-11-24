package org.ericmignot.application;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;

public class Main {

	public static Server server;

	public Main() throws Exception {
		buildServer();
		initializeHandlerList();
	}
	
	public void buildServer() throws Exception {
		server = new Server( 8080 );
	}

	private void initializeHandlerList() throws Exception {
		HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { 
        		new StaticFileHandler(), 
        		new FeatureHandler() });
        server.setHandler( handlers );
	}

	public Server getServer() {
		return server;
	}

	public static void main(String[] args) throws Exception {
        new Main().start();
	}

	public void start() throws Exception {
		server.start();
		server.join();
	}

}
