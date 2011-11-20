package org.ericmignot.store;

import static org.ericmignot.util.FileUtils.readFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.domain.PlainTextSpec;

public class SpecFileStore implements SpecRepository {

	private String path;
	
	public SpecFileStore(String path) {
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
		String content = readFile( path + "/" + title + ".html" );
		String label = readFile( path + "/" + title + ".label" );
		PlainTextSpec newSpec = new PlainTextSpec( title, content );
		newSpec.setLabel( label );
		return newSpec;
	}

	public List<Spec> getSpecs() {
		List<Spec> specs = new ArrayList<Spec>();
		File[] files = new File(path).listFiles( new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith( ".html" );
			}
		});
		for (File file : files) {
			String name = file.getName().substring( 0, file.getName().indexOf(".html") );
			specs.add( getSpecByTitle(name) );
		}
		return specs;
	}

	public List<Spec> getSpecs(String label) {
		if ( label == null || "".equalsIgnoreCase(label) ) {
			return getSpecs();
		}
		List<Spec> specs = new ArrayList<Spec>();
		File[] files = new File(path).listFiles( new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith( ".html" );
			}
		});
		for (File file : files) {
			String name = file.getName().substring( 0, file.getName().indexOf(".html") );
			Spec spec = getSpecByTitle(name);
			if (spec.getLabel().equalsIgnoreCase(label)) {
				specs.add( spec );
			}
		}
		return specs;
	}
	
}
