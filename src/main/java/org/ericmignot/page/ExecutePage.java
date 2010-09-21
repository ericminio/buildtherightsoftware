package org.ericmignot.page;

import java.io.IOException;
import java.util.Date;

import org.ericmignot.core.TryThisCode;
import static org.ericmignot.util.HtmlManipulator.extractBodyContent;
import static org.ericmignot.util.HtmlManipulator.removeAllScriptSections;

public class ExecutePage extends PageTemplate {

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

	protected void workBeforeRenderingHtml() throws IOException {
		try {
			launcher.go();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String content() throws IOException {
		workBeforeRenderingHtml();
		String content = readFile( "target/html/template.html" );
		content = content.replaceAll( "page-content", pageContent() );
		return content;
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

	protected String specContent() {
		return readFile( getFilePathToBeIncluded() );
	}

	protected String coberturaReport() {
		String fullContent = readFile( getCoberturaReportPath() );
		String body = extractBodyContent( fullContent );
		return removeAllScriptSections( body );
	}
	
	public String getCoberturaReportPath() {
		return launcher.getCompilerDirectory() + "/target/site/cobertura/frame-summary.html";
	}
	
	
}
