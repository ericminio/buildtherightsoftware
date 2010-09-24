package org.ericmignot.application;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;


public class Main {

	public static void main(String[] args) throws Exception {

		int port = 8080;
		if (args.length > 0) {
			port = Integer.parseInt( args[0] );
		}
		Server server = new Server( port );
		
		HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { 
        		new StaticFileHandler(), 
        		new FeatureHandler() });
        server.setHandler(handlers);
        
        server.start();
        server.join();
		
	}

}
