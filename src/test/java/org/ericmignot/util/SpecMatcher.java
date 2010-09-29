package org.ericmignot.util;

import org.ericmignot.adapters.domain.Spec;
import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;

public class SpecMatcher extends ArgumentMatcher<Spec> {

	private String expectedTitle;
	private String expectedContent;
	private String expectedLabel;
	
	@Override
	public boolean matches(Object argument) {
		boolean match = true;
		if (argument == null) match = false;
		
		if (! expectedTitle.equalsIgnoreCase( ((Spec) argument).getTitle() )) match = false;
		
		if ((expectedContent != null) && !expectedContent.equalsIgnoreCase( ((Spec) argument).getContent() )) match = false;
		
		if ((expectedLabel != null) && !expectedLabel.equalsIgnoreCase( ((Spec) argument).getLabel() )) match = false;
		
		return match;
	}
	
	@Override
	public void describeTo(Description matchDescription) {
		matchDescription.appendText( "a spec with title " ).appendValue( expectedTitle );
		if (expectedLabel != null) {
			matchDescription.appendText( " and the label " ).appendValue( expectedLabel );
		}
		if (expectedContent != null) {
			matchDescription.appendText( " and the content " ).appendValue( expectedContent );
		}
	}
	
	public static SpecMatcher isASpecMatcher() {
		return new SpecMatcher();
	}
	
	public SpecMatcher withTitle(String title) {
		this.expectedTitle = title;
		return this;
	}
	
	public SpecMatcher withContent(String content) {
		this.expectedContent = content;
		return this;
	}

	public SpecMatcher withLabel(String label) {
		this.expectedLabel = label;
		return this;
	}

}
