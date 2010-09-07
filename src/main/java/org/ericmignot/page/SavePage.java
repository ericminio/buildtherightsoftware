package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.core.SpecSaver;

public class SavePage extends ShowPage {

	private String specXContent;
	private SpecSaver specSaver;
	
	public SavePage(String specX, String specXContent) {
		super(specX);
		this.specXContent = specXContent;
		this.specSaver = new SpecSaver();
	}

	public String getSpecXContent() {
		return specXContent;
	}

	public void setSpecSaver(SpecSaver specSaver) {
		this.specSaver = specSaver;
	}
	
	public SpecSaver getSpecSaver() {
		return specSaver;
	}

	public String content() throws IOException {
		specSaver.setDirectory( getSpecXDirectory() );
		specSaver.save( getSpecX(), specXContent );
		return super.content();
	}
}
