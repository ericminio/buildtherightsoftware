package org.ericmignot.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SpecSaver {

	private String specXDirectory;
	
	public void setSpecXDirectory(String dir) {
		this.specXDirectory = dir;
	}

	public void save(String specX, String specXContent) throws IOException {
		String file = specXDirectory + specX + ".html";
		PrintWriter out
		   = new PrintWriter(new BufferedWriter(new FileWriter( file )));
		out.print( specXContent );
		out.flush();
	}


}
