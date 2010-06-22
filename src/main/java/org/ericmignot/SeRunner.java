package org.ericmignot;

import java.io.File;
import java.io.IOException;

public class SeRunner {

	private String directory;
	private String cloneRelativePath;
	private String seRelativePath;
	private String outRelativePath;
	
	public void setRunnerDirectory(String path) {
		this.directory = path;
	}

	public void setCloneRepository(String cloneRelativePath) {
		this.cloneRelativePath = cloneRelativePath;
	}

	public void setSe(String se) {
		this.seRelativePath = se;
	}
	
	public void setOut(String out) {
		this.outRelativePath = out;
	}

	public void executeSpecification() throws IOException, InterruptedException {
		File dir = new File( directory );
		String command = "java -cp greenpepper-core-2.7.jar:" + cloneRelativePath + "/target/classes" +
			" com.greenpepper.runner.Main" +
			" " + seRelativePath +
			" -o " + outRelativePath ;
		Process process = Runtime.getRuntime().exec( command, null, dir );
		process.waitFor();
	}

}
