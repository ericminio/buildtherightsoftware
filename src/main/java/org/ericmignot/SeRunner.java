package org.ericmignot;

import java.io.File;
import java.io.IOException;

public class SeRunner {

	private String directory;
	private String classesRelativePath;
	private String seRelativePath;
	private String outRelativePath;
	
	public void setRunnerDirectory(String path) {
		this.directory = path;
	}
	
	public void setClassesRelativeDirectory(String cloneRelativePath) {
		this.classesRelativePath = cloneRelativePath;
	}

	public void setSeRelativeFile(String se) {
		this.seRelativePath = se;
	}
	
	public void setOutputRelativeDirectory(String out) {
		this.outRelativePath = out;
	}

	public void executeSpecification() throws IOException, InterruptedException {
		File dir = new File( directory );
		String command = "java -cp greenpepper-core-2.7.jar:" + 
			classesRelativePath +
			" com.greenpepper.runner.Main" +
			" " + seRelativePath +
			" -o " + outRelativePath ;
		Process process = Runtime.getRuntime().exec( command, null, dir );
		process.waitFor();
	}

}
