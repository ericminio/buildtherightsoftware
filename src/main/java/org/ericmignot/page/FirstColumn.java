package org.ericmignot.page;

public class FirstColumn {

	public static final String LOGO = "<img src=/logo.png />";

	public String html() {
		return "<a href=/ >" + LOGO + "</a>";
	}

}
