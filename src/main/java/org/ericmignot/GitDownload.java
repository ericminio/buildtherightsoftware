package org.ericmignot;

import java.io.File;
import java.io.IOException;

public class GitDownload {

	private String cloneNumber;
	
	public void setCloneNumber(String number) {
		this.cloneNumber = number;
	}

	public void fetch(String gitUrl) throws IOException, InterruptedException {
		File dir = new File("target/clones/" + cloneNumber);
		dir.mkdir();
		
		String command = "git clone " + gitUrl;
		Process process = Runtime.getRuntime().exec(command, null, dir);
		process.waitFor();
	}

}
