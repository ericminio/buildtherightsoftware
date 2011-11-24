package org.ericmignot.controller;

import static org.ericmignot.util.matchers.SpecMatcher.isASpec;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.Writer;

import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.SpecRenderer;
import org.junit.Before;
import org.junit.Test;


public class DefaultControllerTest {

	private DefaultController controller;
	private SpecRepository repository;
	
	@Before public void
	init() {
		controller = new DefaultController();
		repository = mock(SpecRepository.class);
	}
	
	@Test public void
	triggersSamplePageRendering() throws Exception {
		SpecRenderer viewMock = mock( SpecRenderer.class );
		controller.setRenderer( viewMock );
		
		controller.handle(null, repository, mock( Writer.class ) );
		verify( repository, never() ).getSpecByTitle( anyString() );
		verify( viewMock ).setSpec( argThat( isASpec().withTitle( "sample" )
													  .withContent( expectedContent() ) ));
	}
	
	private String expectedContent() {
		return "<p>"
				+ "<table>" 
				+ "<tr>" 
				+ "	<td class=\"rulefor\">Rule for</td>" 
				+ "	<td>mastermind</td>" 
				+ "</tr>"
				+ "<tr>" 
				+ "<td class=\"ruleforheader\">given the secret</td>" 
				+ "<td class=\"ruleforheader\">when player plays</td>" 
				+ "<td class=\"ruleforheader\">does he win ?</td>" 
				+ "</tr>"
				+ "<tr> <td>green</td> <td>red</td> <td>no</td> </tr>"
				+ "<tr> <td>green</td> <td>green</td> <td>yes</td> </tr>"
				+ "</table>"
			 + "</p>";
	}
}
