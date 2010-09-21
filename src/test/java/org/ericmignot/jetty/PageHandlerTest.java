package org.ericmignot.jetty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.ericmignot.core.Spec;
import org.ericmignot.page.PageTemplate;
import org.ericmignot.store.FileRepository;
import org.ericmignot.store.InMemoryRepository;
import org.junit.Before;
import org.junit.Test;
import static org.ericmignot.util.SpecBuilder.aSpec;

public class PageHandlerTest {

	private PageHandler pageHandler;
	
	private Request requestMock;
	private HttpServletRequest httpRequestMock;
	private HttpServletResponse httpResponseMock;
	private PrintWriter printWriterMock;
	
	@Before public void
	init() throws IOException {
		pageHandler = new PageHandler();
		pageHandler.setRepository( new InMemoryRepository() );
		Spec sample = aSpec().withTitle( "sample" ).build();
		pageHandler.getRepository().saveSpec( sample );
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
	askPageChooserWhichPageToServeWhenActionChooserDontFindCandidate() throws IOException, ServletException {
		PageTemplate pageMock = mock(PageTemplate.class);
		PageRouter pageChooser = mock(PageRouter.class);
		when(pageChooser.choosePage(httpRequestMock)).thenReturn(pageMock);
		pageHandler.setPageRouter(pageChooser);
		
		ActionRouter actionRouterMock = mock( ActionRouter.class );
		when(actionRouterMock.chooseAction(httpRequestMock)).thenReturn(null);
		pageHandler.setActionRouter( actionRouterMock );
		
		pageHandler.handle(null, requestMock, httpRequestMock, httpResponseMock);
		verify(actionRouterMock).chooseAction(httpRequestMock);
		verify(pageChooser).choosePage(httpRequestMock);
	}
	
	@Test public void 
	dontAskPageChooserWhichPageToServeWhenActionChooserFindsCandidate() throws IOException, ServletException {
		Action actionMock = mock(Action.class);
		ActionRouter actionRouterMock = mock( ActionRouter.class );
		when(actionRouterMock.chooseAction(httpRequestMock)).thenReturn(actionMock);
		pageHandler.setActionRouter( actionRouterMock );
		
		PageRouter pageChooser = mock(PageRouter.class);
		pageHandler.setPageRouter(pageChooser);
		
		when(requestMock.getRequestURI()).thenReturn("/specs/show/sample");
		
		pageHandler.handle(null, requestMock, httpRequestMock, httpResponseMock);
		verify(actionRouterMock).chooseAction(httpRequestMock);
		verify(pageChooser, never()).choosePage(httpRequestMock);
	}
	
	@Test public void
	useFileRepositoryInProduction() {
		assertTrue( new PageHandler().getRepository() instanceof FileRepository );
	}
	
	@Test public void
	usesSpecsDirectoryInProduction() {
		assertEquals( "specs", ((FileRepository) new PageHandler().getRepository()).getPath() );
	}
}
