package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.util.PageFileReader;

public class Page {
	
	private PageFileReader fileReader;
	
	public Page() {
		fileReader = new PageFileReader();
	}
	
	public void setFileReader(PageFileReader fileReader) {
		this.fileReader = fileReader;
	}
	
	public String readFile(String fileName) throws IOException {
		return fileReader.readFile( fileName );
	}

	public String html() throws IOException {
		String content = readFile( "target/html/template.html" );
		return content;
	}

}
