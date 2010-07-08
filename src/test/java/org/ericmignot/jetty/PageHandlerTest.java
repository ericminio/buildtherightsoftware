package org.ericmignot.jetty;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.ericmignot.page.HomePage;
import org.ericmignot.router.PageRouter;
import org.junit.Before;
import org.junit.Test;

public class PageHandlerTest {

	private PageHandler pageHandler;
	
	private Request requestMock;
	private HttpServletRequest httpRequestMock;
	private HttpServletResponse httpResponseMock;
	private PrintWriter printWriterMock;
	
	@Before public void
	init() throws IOException {
		pageHandler = new PageHandler();
		assertNotNull( "page chooser", pageHandler.getPageRouter() );
		
		requestMock = mock(Request.class);
		httpRequestMock = mock(HttpServletRequest.class);
		httpResponseMock = mock(HttpServletResponse.class);
		printWriterMock = mock(PrintWriter.class);
		
		when(httpResponseMock.getWriter()).thenReturn(printWriterMock);
	}
	
	@Test public void
	alwaysHandleTheRequest() throws IOException, ServletException {
		pageHandler.handle(null, requestMock, httpRequestMock, httpResponseMock);
		verify(httpResponseMock).setContentType("text/html;charset=utf-8");
		verify(httpResponseMock).setStatus(HttpServletResponse.SC_OK);
		verify(requestMock).setHandled(true);
	}
	
	@Test public void 
	askPageChooserWhichPageToServe() throws IOException, ServletException {
		PageRouter pageChooser = mock(PageRouter.class);
		when(pageChooser.choosePage(httpRequestMock)).thenReturn(new HomePage());
		pageHandler.setPageRouter(pageChooser);
		
		pageHandler.handle(null, requestMock, httpRequestMock, httpResponseMock);
		verify(pageChooser).choosePage(httpRequestMock);
	}
}
