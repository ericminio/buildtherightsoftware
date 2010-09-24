package org.ericmignot.application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class StaticFileHandler extends AbstractHandler {

	private ResourceHandler resourceHandler;
	
	Map<String, String> fileDirectories;
	
	public StaticFileHandler() throws Exception {
		setResourceHandler(new ResourceHandler());
		fileDirectories = new HashMap<String, String>();
		fileDirectories.put(".png", "target/images");
		fileDirectories.put(".css", "target/styles");
		fileDirectories.put(".js", "target/js");
	}
	
	public String extractExtension(String path) {
		if (path.indexOf( "." ) != -1) {
			return path.substring( path.indexOf( "." ) );
		}
		else {
			return null;
		}
	}

	public void handle(String target, Request request,
			HttpServletRequest servletRequest, HttpServletResponse response)
			throws IOException, ServletException {
		
		String extension = extractExtension(target);
		if (fileDirectories.containsKey(extension)) {
			resourceHandler.setResourceBase(fileDirectories.get(extension));
			resourceHandler.handle(target, request, servletRequest, response);
	    }
		else {
			request.setHandled(false);
			return;
		}
	}

	public void setResourceHandler(ResourceHandler resourceHandler) throws Exception {
		this.resourceHandler = resourceHandler;
		resourceHandler.doStart();
	}

	public ResourceHandler getResourceHandler() {
		return resourceHandler;
	}

}
