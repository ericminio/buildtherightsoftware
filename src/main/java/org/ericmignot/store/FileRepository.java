package org.ericmignot.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.ericmignot.core.Spec;
import org.ericmignot.util.FileReader;
import org.ericmignot.util.HtmlParagraphSpec;

public class FileRepository implements Repository {

	private String path;
	
	public FileRepository(String path) {
		setPath( path );
	}

	public void setPath(String newPath) {
		path = newPath;
	}
	
	public String getPath() {
		return path;
	}

	public void saveSpec(Spec spec) {
		try {
			PrintWriter out;
			String contentFile = path + "/" + spec.getTitle() + ".html";
			out = new PrintWriter(new BufferedWriter(new FileWriter( contentFile )));
			out.print( spec.getContent() );
			out.flush();
			contentFile = path + "/" + spec.getTitle() + ".label";
			out = new PrintWriter(new BufferedWriter(new FileWriter( contentFile )));
			out.print( spec.getLabel() );
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Spec getSpecByTitle(String title) {
		String content = new FileReader().readFile( path + "/" + title + ".html" );
		String label = new FileReader().readFile( path + "/" + title + ".label" );
		HtmlParagraphSpec newSpec = new HtmlParagraphSpec( title, content );
		newSpec.setLabel( label );
		return newSpec;
	}

	

	
}
