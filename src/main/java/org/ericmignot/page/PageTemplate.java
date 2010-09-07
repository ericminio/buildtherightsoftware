package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.jetty.Page;
import org.ericmignot.util.PageFileReader;

public class PageTemplate implements Page {
	
	public static final String DEFAULT_WORKING_DIRECTORY = "specs/";
	
	private PageFileReader fileReader;
	private String specX;
	private String specXDirectory;
	
	public PageTemplate() {
		this(null);
	}
	
	public PageTemplate(String specX) {
		this.specX = specX;
		this.specXDirectory = DEFAULT_WORKING_DIRECTORY;
		fileReader = new PageFileReader();
	}
	
	public String content() throws IOException {
		String content = readFile( "target/html/template.html" );
		return content;
	}
	
	public String getSpecX() {
		return specX;
	}

	public String getSpecXDirectory() {
		return specXDirectory;
	}

	public void setSpecXDirectory(String dir) {
		this.specXDirectory = dir;
	}
	
	public void setFileReader(PageFileReader fileReader) {
		this.fileReader = fileReader;
	}
	
	public String readFile(String fileName) {
		return fileReader.readFile( fileName );
	}

}
