package org.ericmignot.page;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.ericmignot.core.TryThisCode;

public class ExecutePage extends ShowPage {

	private TryThisCode launcher;
	private String chrono;
	
	public ExecutePage(String specX, String gitRepository) {
		super( specX );
		
		this.chrono = ""+new Date().getTime();
		launcher = new TryThisCode();
		launcher.setSpecX( specX );
		launcher.setGitRepository( gitRepository );
		launcher.setChrono( getChrono() );
	}

	public void setRunnerDirectory(String path) {
		launcher.setDirectory(path);
	}

	public String getChrono() {
		return chrono;
	}

	public void setLauncher(TryThisCode launcher) {
		this.launcher = launcher;
	}

	public TryThisCode getLauncher() {
		return launcher;
	}

	public String getFilePathToBeIncluded() {
		return launcher.getDirectory() 
			+ launcher.getExecutionOutputDirectory() 
			+ "/" + launcher.getSpecX() + ".html";
	}

	public String content() throws IOException {
		workBeforeRenderingHtml();
		return super.content();
	}

	protected void workBeforeRenderingHtml() throws IOException {
		try {
			launcher.go();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected String pageContent() throws IOException {
		String content = 
					modifyLink()
					+ specLabel()
					+ specContent() 
					+ invitationToTryACode()
					+ coberturaReport();
		return content;
	}

	protected String coberturaReport() {
		String coberturaSection = "";
		
		String reportSummaryPath = launcher.getCompilerDirectory() + "/target/site/cobertura/frame-summary.html";
		if ( new File( reportSummaryPath ).exists() ) {
			String fullContent = readFile( reportSummaryPath );
			String body = fullContent.substring( fullContent.indexOf("<body>") );
			body = body.substring( 0, body.indexOf( "</body>" ));
		
			coberturaSection = removeScriptSection( body );
			coberturaSection = removeScriptSection( coberturaSection );
		}
		return coberturaSection;
	}

	public String removeScriptSection(String content) {
		String begin = content.substring( 0, content.indexOf( "<script") );
		String end = content.substring( content.indexOf( "</script>") + 9 );
		return begin + end;
	}
	
}
