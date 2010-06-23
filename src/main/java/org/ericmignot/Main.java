package org.ericmignot;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
 
public class Main 
{
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { new FileHandler(), new PageHandler() });
        server.setHandler(handlers);
 
        server.start();
        server.join();
    }
}