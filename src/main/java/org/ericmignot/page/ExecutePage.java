package org.ericmignot.page;

import java.io.IOException;
import java.util.Date;

import org.ericmignot.core.TryThisCode;
import static org.ericmignot.util.HtmlManipulator.extractBodyContent;
import static org.ericmignot.util.HtmlManipulator.removeAllScriptSections;

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

	public String getCoberturaReportPath() {
		return launcher.getCompilerDirectory() + "/target/site/cobertura/frame-summary.html";
	}
	
	protected String coberturaReport() {
		String fullContent = readFile( getCoberturaReportPath() );
		String body = extractBodyContent( fullContent );
		return removeAllScriptSections( body );
	}
}
