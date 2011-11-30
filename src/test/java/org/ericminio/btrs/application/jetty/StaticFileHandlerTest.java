package org.ericminio.btrs.application.jetty;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.ServletException;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.ericminio.btrs.application.jetty.StaticFileHandler;
import org.junit.Before;
import org.junit.Test;


public class StaticFileHandlerTest {

	private StaticFileHandler fileHandler;
	private ResourceHandler handlerMock;
	
	@Before public void
	init() throws Exception {
		fileHandler = new StaticFileHandler();
		
		handlerMock = mock(ResourceHandler.class);
		fileHandler.setResourceHandler(handlerMock);
	}
	
	@Test public void 
	canExtractExtension() {
		assertEquals("3 letters extension", ".png", fileHandler.extractExtension("logo.png"));
		assertEquals("no extension", null, fileHandler.extractExtension("/"));
		assertEquals("2 letters extension", ".js", fileHandler.extractExtension("script.js"));
	}
	
	@Test public void
	servesUrlWithPngExtension() throws Exception {
		fileHandler.handle("my.png", null, null, null);
		verify(handlerMock).setResourceBase("target/images");
		verify(handlerMock).handle("my.png", null, null, null);
	}
	
	@Test public void
	servesUrlWithCssExtension() throws Exception {
		fileHandler.handle("my.css", null, null, null);
		verify(handlerMock).setResourceBase("target/styles");
		verify(handlerMock).handle("my.css", null, null, null);
	}
	
	@Test public void
	servesUrlWithJsExtension() throws Exception {
		fileHandler.handle("my.js", null, null, null);
		verify(handlerMock).setResourceBase("target/js");
		verify(handlerMock).handle("my.js", null, null, null);
	}
	
	@Test public void
	dontServeOtherFiles() throws IOException, ServletException {
		Request requestMock = mock(Request.class);
		
		fileHandler.handle("a file", requestMock, null, null);
		verify(requestMock).setHandled(false);
	}
}
