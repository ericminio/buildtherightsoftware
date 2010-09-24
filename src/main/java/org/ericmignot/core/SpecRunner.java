package org.ericmignot.core;

import java.io.File;
import java.io.IOException;

public class SpecRunner implements FileWorker {

	private String directory;
	
	private String classesRelativePath;
	private String specFileRelativePath;
	private String outRelativePath;
	
	public void setWorkingDirectory(String path) {
		this.directory = path;
	}
	
	public void setClassesRelativeDirectory(String classesRelativePath) {
		this.classesRelativePath = classesRelativePath;
	}

	public void setSpecFileRelativeFile(String specFileRelativePath) {
		this.specFileRelativePath = specFileRelativePath;
	}
	
	public void setOutputRelativeDirectory(String outRelativePath) {
		this.outRelativePath = outRelativePath;
	}

	public void work() {
		File dir = new File( directory );
		String command = "java -cp greenpepper-core-2.7.jar:" + 
			classesRelativePath +
			" com.greenpepper.runner.Main" +
			" " + specFileRelativePath +
			" -o " + outRelativePath ;
		try {
			Process process;
			process = Runtime.getRuntime().exec( command, null, dir );
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
