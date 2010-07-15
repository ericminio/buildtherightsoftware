package org.ericmignot.core;

import java.io.File;
import java.io.IOException;

public class MavenCompiler {

	private String directory;

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public void mavenCleanAndCompile() throws IOException, InterruptedException {
		File dir = new File( directory );
		if ( dir.exists() ) {
			Process process = Runtime.getRuntime().exec("mvn clean compile", null, dir);
			process.waitFor();
		}
	}
}
