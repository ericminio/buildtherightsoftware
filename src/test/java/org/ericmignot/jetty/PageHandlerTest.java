package org.ericmignot.jetty;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertEquals;
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
import org.ericmignot.store.FileRepository;
import org.ericmignot.store.InMemoryRepository;
import org.junit.Before;
import org.junit.Test;

public class PageHandlerTest {

	private PageHandler pageHandler;
	
	private HttpServletRequest httpRequestMock;
	private HttpServletResponse httpResponseMock;
	private PrintWriter printWriterMock;
	
	@Before public void
	init() throws IOException {
		pageHandler = new PageHandler();
		
		useInMemoryRepositoryWithASingleSampleSpec();
		
		httpRequestMock = mock(HttpServletRequest.class);
		httpResponseMock = mock(HttpServletResponse.class);
		printWriterMock = mock(PrintWriter.class);
		
		when(httpResponseMock.getWriter()).thenReturn(printWriterMock);
	}

	protected void useInMemoryRepositoryWithASingleSampleSpec() {
		pageHandler.setRepository( new InMemoryRepository() );
		Spec sample = aSpec().withTitle( "sample" ).build();
		pageHandler.getRepository().saveSpec( sample );
	}
	
	@Test public void
	alwaysHandleTheRequest() throws IOException, ServletException {
		Request requestMock = mock(Request.class);
		pageHandler.handle(null, requestMock, httpRequestMock, httpResponseMock);
		verify(httpResponseMock).setContentType("text/html;charset=utf-8");
		verify(httpResponseMock).setStatus(HttpServletResponse.SC_OK);
		verify(requestMock).setHandled(true);
	}
	
	@Test public void 
	askPageChooserWhichPageToServeWhenActionChooserDontFindCandidate() throws IOException, ServletException {
		ActionRouter actionRouterMock = anActionRouterThatNeverIdentifyAction();
		pageHandler.setActionRouter( actionRouterMock );
		
		PageRouter pageChooserMock = mock(PageRouter.class);
		pageHandler.setPageRouter(pageChooserMock);
		
		pageHandler.handle(null, mock(Request.class), httpRequestMock, httpResponseMock);
		verify(actionRouterMock).chooseAction(httpRequestMock);
		verify(pageChooserMock).choosePage(httpRequestMock);
	}

	protected ActionRouter anActionRouterThatNeverIdentifyAction() {
		ActionRouter actionRouterMock = mock( ActionRouter.class );
		when(actionRouterMock.chooseAction(httpRequestMock)).thenReturn(null);
		return actionRouterMock;
	}
	
	@Test public void 
	dontAskPageChooserWhichPageToServeWhenActionChooserFindsCandidate() throws IOException, ServletException {
		Action actionMock = mock(Action.class);
		ActionRouter actionRouterMock = anActionRouterThatIdentifiesAnAction(actionMock);
		pageHandler.setActionRouter( actionRouterMock );
		
		PageRouter pageChooser = mock(PageRouter.class);
		pageHandler.setPageRouter(pageChooser);
		
		when(httpRequestMock.getRequestURI()).thenReturn("/specs/show/sample");
		
		pageHandler.handle(null, mock(Request.class), httpRequestMock, httpResponseMock);
		verify(actionRouterMock).chooseAction(httpRequestMock);
		verify(actionMock).work(httpRequestMock, pageHandler.getRepository(), printWriterMock);
		verify(pageChooser, never()).choosePage(httpRequestMock);
	}

	protected ActionRouter anActionRouterThatIdentifiesAnAction(
			Action actionMock) {
		ActionRouter actionRouterMock = mock( ActionRouter.class );
		when(actionRouterMock.chooseAction(httpRequestMock)).thenReturn(actionMock);
		return actionRouterMock;
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
