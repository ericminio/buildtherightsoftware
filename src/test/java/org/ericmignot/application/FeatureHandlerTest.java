package org.ericmignot.application;

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
import org.ericmignot.adapters.Controller;
import org.ericmignot.adapters.Spec;
import org.ericmignot.application.Router;
import org.ericmignot.application.LegacyRouter;
import org.ericmignot.application.FeatureHandler;
import org.ericmignot.store.SpecFileStore;
import org.ericmignot.store.InMemoryRepository;
import org.junit.Before;
import org.junit.Test;


public class FeatureHandlerTest {

	private FeatureHandler pageHandler;
	
	private HttpServletRequest httpRequestMock;
	private HttpServletResponse httpResponseMock;
	private PrintWriter printWriterMock;
	
	@Before public void
	init() throws IOException {
		pageHandler = new FeatureHandler();
		useInMemoryRepositoryWithASingleSampleSpec();
		
		httpRequestMock = mock(HttpServletRequest.class);
		httpResponseMock = mock(HttpServletResponse.class);
		printWriterMock = mock(PrintWriter.class);
		
		when(httpResponseMock.getWriter()).thenReturn(printWriterMock);
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
		Router actionRouterMock = anActionRouterThatNeverIdentifyAction();
		pageHandler.setRouter( actionRouterMock );
		
		LegacyRouter pageChooserMock = mock(LegacyRouter.class);
		pageHandler.setLegacyRouter(pageChooserMock);
		
		pageHandler.handle(null, mock(Request.class), httpRequestMock, httpResponseMock);
		verify(actionRouterMock).chooseController(httpRequestMock);
		verify(pageChooserMock).choosePage(httpRequestMock);
	}

	@Test public void 
	dontAskPageChooserWhichPageToServeWhenActionChooserFindsCandidate() throws IOException, ServletException {
		Controller controller = mock(Controller.class);
		Router actionRouterMock = anActionRouterThatIdentifiesAnAction(controller);
		pageHandler.setRouter( actionRouterMock );
		
		LegacyRouter pageChooser = mock(LegacyRouter.class);
		pageHandler.setLegacyRouter(pageChooser);
		
		when(httpRequestMock.getRequestURI()).thenReturn("/specs/show/sample");
		
		pageHandler.handle(null, mock(Request.class), httpRequestMock, httpResponseMock);
		verify(actionRouterMock).chooseController(httpRequestMock);
		verify(controller).handle(httpRequestMock, pageHandler.getRepository(), printWriterMock);
		verify(pageChooser, never()).choosePage(httpRequestMock);
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
		pageHandler.setRepository( new InMemoryRepository() );
		Spec sample = aSpec().withTitle( "sample" ).build();
		pageHandler.getRepository().saveSpec( sample );
	}
	
	protected Router anActionRouterThatNeverIdentifyAction() {
		Router actionRouterMock = mock( Router.class );
		when(actionRouterMock.chooseController(httpRequestMock)).thenReturn(null);
		return actionRouterMock;
	}
	
	protected Router anActionRouterThatIdentifiesAnAction(
			Controller actionMock) {
		Router actionRouterMock = mock( Router.class );
		when(actionRouterMock.chooseController(httpRequestMock)).thenReturn(actionMock);
		return actionRouterMock;
	}
	
	
}
