package org.ericminio.btrs.application.jetty;

import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.ericminio.btrs.application.jetty.FeatureHandler;
import org.ericminio.btrs.application.route.Router;
import org.ericminio.btrs.application.route.UserRequest;
import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.store.InMemoryRepository;
import org.ericminio.btrs.store.SpecFileStore;
import org.junit.Before;
import org.junit.Test;

public class FeatureHandlerTest {

	private FeatureHandler handler;
	
	private HttpServletRequest httpRequestMock;
	private HttpServletResponse httpResponseMock;
	private PrintWriter printWriterMock;
	
	@Before public void
	init() throws IOException {
		handler = new FeatureHandler();
		useInMemoryRepositoryWithASingleSampleSpec();
		
		httpRequestMock = mock(HttpServletRequest.class);
		httpResponseMock = mock(HttpServletResponse.class);
		printWriterMock = mock(PrintWriter.class);
		
		when(httpResponseMock.getWriter()).thenReturn(printWriterMock);
	}

	@Test public void
	alwaysHandleTheRequest() throws IOException, ServletException {
		Request requestMock = mock(Request.class);
		handler.handle(null, requestMock, httpRequestMock, httpResponseMock);
		verify(httpResponseMock).setContentType("text/html;charset=utf-8");
		verify(httpResponseMock).setStatus(HttpServletResponse.SC_OK);
		verify(requestMock).setHandled(true);
	}
	
	@Test public void 
	givesTheBallToTheCorrectController() throws Exception {
		UserRequest controller = mock(UserRequest.class);
		Router routerMock = aRouterThatIdentifiesTheController(controller);
		handler.setRouter( routerMock );
		
		when(httpRequestMock.getRequestURI()).thenReturn("/specs/show/sample");
		
		handler.handle(null, mock(Request.class), httpRequestMock, httpResponseMock);
		verify(routerMock).chooseController(httpRequestMock);
		verify(controller).handle(httpRequestMock, handler.getRepository(), printWriterMock);
	}
	
	@Test public void
	displayGenericErrorMessageWhenTheControllerThrowsAnException() throws Exception {
		UserRequest controller = mock(UserRequest.class);
		Router routerMock = aRouterThatIdentifiesTheController(controller);
		handler.setRouter( routerMock );
		doThrow( new Exception( ) ).when( controller ).handle( httpRequestMock, handler.getRepository(), printWriterMock );
		
		handler.handle(null, mock(Request.class), httpRequestMock, httpResponseMock);
		verify(printWriterMock).write( "Error\n" );
	}
	
	@Test public void
	useFileRepositoryInProduction() {
		assertTrue( new FeatureHandler().getRepository() instanceof SpecFileStore );
	}
	
	@Test public void
	usesSpecsDirectoryInProduction() {
		assertEquals( "specs", ((SpecFileStore) new FeatureHandler().getRepository()).getPath() );
	}

	protected void useInMemoryRepositoryWithASingleSampleSpec() throws IOException {
		handler.setRepository( new InMemoryRepository() );
		Spec sample = aSpec().withTitle( "sample" ).build();
		handler.getRepository().saveSpec( sample );
	}
	
	protected Router aRouterThatIdentifiesTheController(UserRequest controller) {
		Router actionRouterMock = mock( Router.class );
		when(actionRouterMock.chooseController(httpRequestMock)).thenReturn(controller);
		return actionRouterMock;
	}
	
	
}
