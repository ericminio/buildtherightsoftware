package org.ericmignot.core;

import java.io.IOException;

public class TryThisCode {

	private String directory;
	
	private String specX;
	private String repository;
	private String chrono;
	
	public TryThisCode() {
		setDirectory( "specs/" );
	}
	
	public void setDirectory(String path) {
		this.directory = path;
	}
	
	public void setSpecX(String se) {
		this.specX = se;
	}

	public void setGitRepository(String repository) {
		this.repository = repository;
	}

	public void go() throws IOException, InterruptedException {
		GitDownload fetcher = new GitDownload();
		fetcher.setDirectory( getFetcherDirectory() );
		fetcher.fetch( repository );
		
		MavenCompiler compiler = new MavenCompiler();
		compiler.setDirectory( getCompilerDirectory() );
		compiler.mavenCleanAndCompile();
		
		SpecRunner specRunner = new SpecRunner();
		specRunner.setDirectory( getDirectory() );
		specRunner.setSpecXRelativeFile( specX + ".html" );
		specRunner.setClassesRelativeDirectory( getClassesRelativeDirectory() );
		specRunner.setOutputRelativeDirectory( getExecutionOutputDirectory() );
		specRunner.executeSpecification();
	}

	public void setChrono(String chrono) {
		this.chrono = chrono;
	}

	public String getFetcherDirectory() {
		return getDirectory() + "runs/" + chrono;
	}
	
	public String getCompilerDirectory() {
		return getFetcherDirectory() + "/" + extractProjectName( repository );
	}
	
	public String getClassesRelativeDirectory() {
		return "runs/" + chrono + "/" + extractProjectName( repository ) + "/target/classes";
	}

	public String getExecutionOutputDirectory() {
		return "runs/" + chrono + "/" + extractProjectName( repository ) + "/se/out";
	}

	public String extractProjectName(String gitUrl) {
		if ( gitUrl.length() < "git://github.com/*/*.git".length() ) {
			return null;
		} else {
			return gitUrl.substring( gitUrl.lastIndexOf("/") + 1, gitUrl.length()-4 );
		}
	}

	public String getDirectory() {
		return directory;
	}
	
	public String getSpecX() {
		return specX;
	}

	public String getGitRepository() {
		return repository;
	}

	public String getChrono() {
		return chrono;
	}

}
