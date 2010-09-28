package org.ericmignot.util;

import org.ericmignot.adapters.Spec;
import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;

public class SpecMatcher extends ArgumentMatcher<Spec> {

	private String expectedTitle;
	private String expectedContent;
	
	@Override
	public boolean matches(Object argument) {
		boolean match = true;
		if (argument == null) match = false;
		if (! expectedTitle.equalsIgnoreCase( ((Spec) argument).getTitle() )) match = false;
		if ((expectedContent != null) && !expectedContent.equalsIgnoreCase( ((Spec) argument).getContent() )) match = false;
		return match;
	}
	
	@Override
	public void describeTo(Description matchDescription) {
		matchDescription.appendText( "a spec with title " ).appendValue( expectedTitle );
		if (expectedContent != null) {
			matchDescription.appendText( " and the content " ).appendValue( expectedContent );
		}
	}
	
	public static SpecMatcher aSpec() {
		return new SpecMatcher();
	}
	
	public SpecMatcher withTitle(String expectedTitle) {
		this.expectedTitle = expectedTitle;
		return this;
	}
	
	public SpecMatcher withContent(String expectedContent) {
		this.expectedContent = expectedContent;
		return this;
	}

}
