package org.ericminio.btrs.domain;

public interface SpecRunner extends FileWorker {

	void setSpecFileRelativeFile(String specFileRelativePath);
	
	void setClassesRelativeDirectory(String classesRelativePath);
	
	void setOutputRelativeDirectory(String outRelativePath);
	
}
