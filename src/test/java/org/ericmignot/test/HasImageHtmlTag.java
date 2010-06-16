package org.ericmignot.test;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;


public class HasImageHtmlTag extends BaseMatcher<String> {

	private String expected;
	
	public HasImageHtmlTag(String fileName) {
		this.expected = "<img src=/" + fileName + "></img>";
	}
	
	public boolean matches(Object obj) {
		String content = obj.toString();
		return ((String) content).indexOf(expected) != -1;
	}

	public void describeTo(Description description) {
		description.appendText("image tag " + expected);
	}
	
	public static HasImageHtmlTag hasImageHtmlTag(String fileName) {
		return new HasImageHtmlTag(fileName);
	}


}
