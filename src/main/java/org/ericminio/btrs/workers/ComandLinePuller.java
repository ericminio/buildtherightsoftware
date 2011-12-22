package org.ericminio.btrs.workers;

import java.io.File;

import org.ericminio.btrs.domain.SourcePuller;

public abstract class ComandLinePuller implements SourcePuller {

	private String directory;
	protected String url;
	protected Runtime runtime;

	public void setWorkingDirectory(String dir) {
		this.directory = dir;
	}

	public String getWorkingDirectory() {
		return this.directory;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setRuntime(Runtime runtime) {
		this.runtime = runtime;
	}

	public Runtime getRuntime() {
		return this.runtime;
	}

	public void work() throws Exception {
		File dir = new File(directory);
		dir.mkdir();
		Process process = runtime.exec( getPullCommand(), null, dir );
		process.waitFor();
	}
	
	public abstract String getPullCommand();

}