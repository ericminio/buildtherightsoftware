package org.ericmignot.domain;

import java.io.File;
import java.io.IOException;

import org.ericmignot.adapters.FileWorker;

public class GitPuller implements FileWorker {

	private String directory;
	private String gitUrl;
	
	public void setWorkingDirectory(String dir) {
		this.directory = dir;
	}
	
	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	public void work() {
		File dir = new File( directory );
		dir.mkdir();
		String command = "git clone " + gitUrl;
		try {
			Process process;
			process = Runtime.getRuntime().exec(command, null, dir);
			process.waitFor();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
