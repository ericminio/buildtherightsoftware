package org.ericmignot.page;

public class HomePage {

	public String html() {
		return "<img src=/logo.png />" + teaser();
	}
	
	protected String teaser() {
		String teaser = "" +
			"<img src=/me.png /> I would like this software" +
			"<br>" 
		;
		
		return teaser;
	}

}
