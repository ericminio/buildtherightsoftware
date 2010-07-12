package org.ericmignot.page.section;

public class Modify {

	private String specX;
	
	public void setSpecX(String specX) {
		this.specX = specX;
	}

	public String html() {
		String content = "<p>"
			+ "<a name=modifyLink href=/specs/modify/" + specX +  " class=action >Modify</a>"
			+ "</p>";
		return content;
	}

}
