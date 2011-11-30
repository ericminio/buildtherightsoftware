package org.ericminio.btrs.domain.matchers;

import org.ericminio.btrs.domain.Spec;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HamcrestSpecMatcher extends TypeSafeMatcher<Spec> {

	private String expectedTitle;
	private String expectedContent;
	private String expectedLabel;
	
	@Override
	protected boolean matchesSafely(Spec spec) {
		boolean match = true;
		if (spec == null) match = false;
		
		if (! expectedTitle.equalsIgnoreCase( spec.getTitle() )) match = false;
		
		if ((expectedContent != null) 
				&& !expectedContent.equalsIgnoreCase( spec.getContent() )) match = false;
		
		if ((expectedLabel != null) 
				&& !expectedLabel.equalsIgnoreCase( spec.getLabel() )) match = false;
		
		return match;
	}
	
	public void describeTo(Description matchDescription) {
		matchDescription.appendText( "a spec with title " ).appendValue( expectedTitle );
		if (expectedLabel != null) {
			matchDescription.appendText( " and the label " ).appendValue( expectedLabel );
		}
		if (expectedContent != null) {
			matchDescription.appendText( " and the content " ).appendValue( expectedContent );
		}
	}
	
	public static HamcrestSpecMatcher isASpec() {
		return new HamcrestSpecMatcher();
	}
	
	public HamcrestSpecMatcher withTitle(String title) {
		this.expectedTitle = title;
		return this;
	}
	
	public HamcrestSpecMatcher withContent(String content) {
		this.expectedContent = content;
		return this;
	}

	public HamcrestSpecMatcher withLabel(String label) {
		this.expectedLabel = label;
		return this;
	}

}
