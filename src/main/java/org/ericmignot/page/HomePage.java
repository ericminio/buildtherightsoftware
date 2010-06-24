package org.ericmignot.page;

public class HomePage extends Page {

	public static final String SE_SAMPLE = "specs/sample.html";

	public HomePage() {
		setSecondColumn(new SecondColumn());
	}
	
	@Override
	protected void updateSpecificContent() {
		getSecondColumn().setContent( SE_SAMPLE );
	}
	
	
}
