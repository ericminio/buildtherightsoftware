package org.ericmignot.jetty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.ServletException;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.junit.Before;
import org.junit.Test;


public class FileHandlerTest {

	private FileHandler fileHandler;
	
	@Before public void
	init() throws Exception {
		fileHandler = new FileHandler();
		assertTrue(fileHandler.getResourceHandler() instanceof ResourceHandler);
	}
	
	@Test public void 
	canExtractExtension() {
		assertEquals("extension", ".png", fileHandler.extractExtension("logo.png"));
		assertEquals("extension", null, fileHandler.extractExtension("/"));
	}
	
	@Test public void
	servesUrlWithPngExtension() throws Exception {
		ResourceHandler handlerMock = mock(ResourceHandler.class);
		fileHandler.setResourceHandler(handlerMock);
		
		fileHandler.handle("my.png", null, null, null);
		verify(handlerMock).setResourceBase("target/images");
		verify(handlerMock).handle("my.png", null, null, null);
	}
	
	@Test public void
	servesUrlWithCssExtension() throws Exception {
		ResourceHandler handlerMock = mock(ResourceHandler.class);
		fileHandler.setResourceHandler(handlerMock);
		
		fileHandler.handle("my.css", null, null, null);
		verify(handlerMock).setResourceBase("target/styles");
		verify(handlerMock).handle("my.css", null, null, null);
	}
	
	@Test public void
	dontServeOtherFiles() throws IOException, ServletException {
		Request requestMock = mock(Request.class);
		
		fileHandler.handle("a file", requestMock, null, null);
		verify(requestMock).setHandled(false);
	}
}
