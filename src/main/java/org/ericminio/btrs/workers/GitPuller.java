package org.ericminio.btrs.workers;

import java.io.File;

import org.ericminio.btrs.domain.SourceRepositoryPuller;

public class GitPuller implements SourceRepositoryPuller {

	private String directory;
	private String gitUrl;
	private Runtime runtime;
	
	public GitPuller() {
		this.runtime = Runtime.getRuntime();
	}

	public void setWorkingDirectory(String dir) {
		this.directory = dir;
	}

	public String getWorkingDirectory() {
		return this.directory;
	}

	public void setUrl(String url) {
		this.gitUrl = url;
	}

	public String getUrl() {
		return this.gitUrl;
	}

	public String getPullCommand() {
		return "git clone " + gitUrl;
	}

	public void work() throws Exception {
		File dir = new File(directory);
		dir.mkdir();
		Process process = runtime.exec( getPullCommand(), null, dir );
		process.waitFor();
	}
	
	public void setRuntime(Runtime runtime) {
		this.runtime = runtime;
	}

	public Runtime getRuntime() {
		return this.runtime;
	}

}