package org.ericmignot;

import java.io.File;
import java.io.IOException;

public class MavenCompiler {

	private String directory;

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public void mavenCleanAndCompile() throws IOException, InterruptedException {
		File dir = new File( directory );
		Process process = Runtime.getRuntime().exec("mvn clean compile", null, dir);
		process.waitFor();
	}
}
