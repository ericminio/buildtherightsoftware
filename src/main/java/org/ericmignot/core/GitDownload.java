package org.ericmignot.core;

import java.io.File;
import java.io.IOException;

public class GitDownload {

	private String directory;

	public void setDirectory(String path) {
		this.directory = path;
	}

	public void fetch(String gitUrl) throws IOException, InterruptedException {
		File dir = new File( directory );
		dir.mkdir();
		
		String command = "git clone " + gitUrl;
		Process process = Runtime.getRuntime().exec(command, null, dir);
		process.waitFor();
	}


}
