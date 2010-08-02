package org.ericmignot.core;

import java.io.File;
import java.io.IOException;

public class SpecRunner {

	private String directory;
	
	private String classesRelativePath;
	private String specXRelativePath;
	private String outRelativePath;
	
	public void setDirectory(String path) {
		this.directory = path;
	}
	
	public void setClassesRelativeDirectory(String classesRelativePath) {
		this.classesRelativePath = classesRelativePath;
	}

	public void setSpecXRelativeFile(String specXRelativePath) {
		this.specXRelativePath = specXRelativePath;
	}
	
	public void setOutputRelativeDirectory(String outRelativePath) {
		this.outRelativePath = outRelativePath;
	}

	public void executeSpecification() throws IOException, InterruptedException {
		File dir = new File( directory );
		String command = "java -cp greenpepper-core-2.7.jar:" + 
			classesRelativePath +
			" com.greenpepper.runner.Main" +
			" " + specXRelativePath +
			" -o " + outRelativePath ;
		Process process = Runtime.getRuntime().exec( command, null, dir );
		process.waitFor();
	}

}
