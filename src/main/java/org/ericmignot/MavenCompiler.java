package org.ericmignot;

import java.io.File;
import java.io.IOException;

public class MavenCompiler {

	private String cloneNumber;
	private String name;
	
	public void setCloneNumber(String number) {
		this.cloneNumber = number;
	}

	public void setRepositoryName(String name) {
		this.name = name;
	}

	public void mavenCleanAndCompile() throws IOException, InterruptedException {
		File dir = new File("target/clones/" + cloneNumber + "/" + name);
		Process process = Runtime.getRuntime().exec("mvn clean compile", null, dir);
		process.waitFor();
	}

}
