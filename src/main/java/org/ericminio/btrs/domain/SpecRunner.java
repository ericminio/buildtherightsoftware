package org.ericminio.btrs.domain;

public interface SpecRunner extends FileWorker {

	public void setSpecFileRelativeFile(String specFileRelativePath);
	
	public void setClassesRelativeDirectory(String classesRelativePath);
	
	public void setOutputRelativeDirectory(String outRelativePath);
	
}
