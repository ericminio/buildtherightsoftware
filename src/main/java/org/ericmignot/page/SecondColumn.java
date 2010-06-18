package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.util.FileReader;


public class SecondColumn {

	public static final String FILE_NAME = "target/html/homePageContent.html";
	private FileReader fileReader;
	
	public SecondColumn() {
		setFileReader( new FileReader() );
	}
	
	public String html() {
		try {
			return fileReader.readFile( FILE_NAME );
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}

}
