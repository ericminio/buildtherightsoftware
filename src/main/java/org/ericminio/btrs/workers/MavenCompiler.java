package org.ericminio.btrs.workers;

import java.io.File;

import org.ericminio.btrs.domain.Compiler;

public class MavenCompiler implements Compiler {

	private String directory;
	private Runtime runtime;
	
	public MavenCompiler() {
		this.runtime = Runtime.getRuntime();
	}

	public void setWorkingDirectory(String path) {
		this.directory = path;
	}

	public String getWorkingDirectory() {
		return this.directory;
	}

	public String getCompilerCommand() {
		return "mvn clean cobertura:cobertura";
	}

	public void work() throws Exception {
		Process process = this.runtime.exec( getCompilerCommand(), null, new File(directory) );
		process.waitFor();
	}

	public void setRuntime(Runtime runtime) {
		this.runtime = runtime;
	}

	public Runtime getRuntime() {
		return this.runtime;
	}

}
