package org.ericmignot.core;

import java.io.File;
import java.io.IOException;

public class MavenCompiler {

	private String directory;

	public void setDirectory(String path) {
		this.directory = path;
	}

	public void mavenCleanAndCompile() throws IOException, InterruptedException {
		File dir = new File( directory );
		if ( dir.exists() ) {
			Process process = Runtime.getRuntime().exec("mvn clean cobertura:cobertura", null, dir);
			process.waitFor();
		}
	}
}
