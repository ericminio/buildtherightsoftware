package org.ericmignot.core;

import java.io.File;
import java.io.IOException;

public class MavenCompiler implements FileWorker {

	private String directory;

	public void setWorkingDirectory(String path) {
		this.directory = path;
	}

	public void work() {
		File dir = new File( directory );
		if ( dir.exists() ) {
			try {
				Process process;
				process = Runtime.getRuntime().exec("mvn clean cobertura:cobertura", null, dir);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
