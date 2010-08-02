package org.ericmignot.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SpecSaver {

	private String directory;
	
	public void setDirectory(String path) {
		this.directory = path;
	}

	public void save(String specX, String specXContent) throws IOException {
		String file = directory + specX + ".html";
		PrintWriter out
		   = new PrintWriter(new BufferedWriter(new FileWriter( file )));
		out.print( specXContent );
		out.flush();
	}


}
