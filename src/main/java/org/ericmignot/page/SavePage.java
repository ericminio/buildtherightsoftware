package org.ericmignot.page;

public class SavePage extends Page {

	private String specX;
	private String specXContent;
	
	public SavePage(String specX, String specXContent) {
		this.specX = specX;
		this.specXContent = specXContent;
	}

	public String getSpecX() {
		return specX;
	}

	public String getSpecXContent() {
		return specXContent;
	}

}
