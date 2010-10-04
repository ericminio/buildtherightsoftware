package org.ericmignot.domain;

import java.io.File;
import java.io.IOException;

import org.ericmignot.adapters.domain.RemoteSourcePuller;

public class GitPuller implements RemoteSourcePuller {

	private String directory;
	private String gitUrl;
	
	public void setWorkingDirectory(String dir) {
		this.directory = dir;
	}
	
	public void setUrl(String url) {
		this.gitUrl = url;
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
