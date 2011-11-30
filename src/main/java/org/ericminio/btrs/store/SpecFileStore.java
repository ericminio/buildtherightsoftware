package org.ericminio.btrs.store;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecMatcher;
import org.ericminio.btrs.domain.SpecRepository;

import static org.ericminio.btrs.domain.matchers.CoreMatchers.*;
import static org.ericminio.btrs.store.FileUtils.readFile;
import static org.ericminio.btrs.store.SpecBuilder.aSpec;

public class SpecFileStore implements SpecRepository {

	private String path;

	public SpecFileStore(String path) {
		setPath(path);
	}

	public void setPath(String newPath) {
		path = newPath;
	}

	public String getPath() {
		return path;
	}

	public void saveSpec(Spec spec) throws IOException {
		PrintWriter out;
		String contentFile = path + "/" + spec.getTitle() + ".html";
		out = new PrintWriter(new BufferedWriter(new FileWriter(contentFile)));
		out.print(spec.getContent());
		out.flush();
		contentFile = path + "/" + spec.getTitle() + ".label";
		out = new PrintWriter(new BufferedWriter(new FileWriter(contentFile)));
		out.print(spec.getLabel());
		out.flush();
	}

	public Spec getSpecByTitle(String title) {
		String content = readFile(path + "/" + title + ".html");
		String label = readFile(path + "/" + title + ".label");
		return aSpec().withTitle( title ).withContent( content ).withLabel( label ).build();
	}

	private File[] getAllHtmlFiles() {
		File[] files = new File(path).listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".html");
			}
		});
		return files;
	}

	public List<Spec> getSpecs(SpecMatcher matcher) {
		List<Spec> specs = new ArrayList<Spec>();
		File[] files = getAllHtmlFiles();
		for (File file : files) {
			String name = file.getName().substring(0,
					file.getName().indexOf(".html"));
			Spec spec = getSpecByTitle(name);
			if (matcher.matches(spec)) {
				specs.add(spec);
			}
		}
		return specs;
	}
	
	public List<Spec> getSpecs() {
		return getSpecs( all() );
	}
	
	public List<Spec> getSpecsWithLabel(String label) {
		return getSpecs( withLabel( label ) );
	}
	
	
	

}
