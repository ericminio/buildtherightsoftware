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

	public void saveContent(String specX, String specXContent) throws IOException {
		String file = directory + specX + ".html";
		PrintWriter out
		   = new PrintWriter(new BufferedWriter(new FileWriter( file )));
		out.print( specXContent );
		out.flush();
	}

	public void saveLabel(String specX, String label) throws IOException {
		String file = directory + specX + ".label";
		PrintWriter out
		   = new PrintWriter(new BufferedWriter(new FileWriter( file )));
		out.print( label );
		out.flush();
	}


}
