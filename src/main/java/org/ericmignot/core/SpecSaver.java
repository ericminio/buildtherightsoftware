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
		save(specXContent, file);
	}

	public void saveLabel(String specX, String label) throws IOException {
		String file = directory + specX + ".label";
		save(label, file);
	}

	protected void save(String content, String file) throws IOException {
		PrintWriter out
		   = new PrintWriter(new BufferedWriter(new FileWriter( file )));
		out.print( content );
		out.flush();
	}

}
