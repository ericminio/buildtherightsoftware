package org.ericmignot.application;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.ericmignot.adapters.ActionController;
import org.ericmignot.adapters.Spec;
import org.ericmignot.store.InMemoryRepository;
import org.ericmignot.store.SpecFileStore;
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
	givesTheBallToTheCorrectController() throws IOException, ServletException {
		ActionController controller = mock(ActionController.class);
		Router routerMock = aRouterThatIdentifiesTheController(controller);
		handler.setRouter( routerMock );
		
		when(httpRequestMock.getRequestURI()).thenReturn("/specs/show/sample");
		
		handler.handle(null, mock(Request.class), httpRequestMock, httpResponseMock);
		verify(routerMock).chooseController(httpRequestMock);
		verify(controller).handle(httpRequestMock, handler.getRepository(), printWriterMock);
	}
	
	@Test public void
	useFileRepositoryInProduction() {
		assertTrue( new FeatureHandler().getRepository() instanceof SpecFileStore );
	}
	
	@Test public void
	usesSpecsDirectoryInProduction() {
		assertEquals( "specs", ((SpecFileStore) new FeatureHandler().getRepository()).getPath() );
	}

	protected void useInMemoryRepositoryWithASingleSampleSpec() {
		handler.setRepository( new InMemoryRepository() );
		Spec sample = aSpec().withTitle( "sample" ).build();
		handler.getRepository().saveSpec( sample );
	}
	
	protected Router aRouterThatIdentifiesTheController(
			ActionController controller) {
		Router actionRouterMock = mock( Router.class );
		when(actionRouterMock.chooseController(httpRequestMock)).thenReturn(controller);
		return actionRouterMock;
	}
	
	
}
