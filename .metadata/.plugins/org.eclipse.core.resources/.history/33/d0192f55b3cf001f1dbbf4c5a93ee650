package org.ericmignot.domain;

import java.io.File;
import java.io.IOException;

import org.ericmignot.adapters.domain.Compiler;

public class MavenCompiler implements Compiler {

	private String directory;

	public void setWorkingDirectory(String path) {
		this.directory = path;
	}

	public String getCompilerCommand() {
		return "mvn clean cobertura:cobertura";
	}
	
	public void work() {
		File dir = new File( directory );
		if ( dir.exists() ) {
			try {
				Process process;
				process = Runtime.getRuntime().exec( getCompilerCommand(), null, dir);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
