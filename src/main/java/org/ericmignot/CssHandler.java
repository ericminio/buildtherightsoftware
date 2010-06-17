package org.ericmignot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class CssHandler extends ResourceHandler {

	public CssHandler() {
		setDirectoriesListed(true);
		setResourceBase("target/styles");
	}

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		if (request.getPathInfo().endsWith(".css")) {
			super.handle(target, baseRequest, request, response);
		}
		else {
			baseRequest.setHandled(false);
			return;
		}

	}

}
