package org.ericmignot.page;

import java.io.IOException;
import java.util.Date;

import org.ericmignot.core.TryThisCode;
import org.ericmignot.page.section.ModifyLink;

public class ResultPage extends Page {

	private TryThisCode launcher;
	private String chrono;
	
	public ResultPage(String se, String gitRepository) {
		this.chrono = ""+new Date().getTime();
		
		launcher = new TryThisCode();
		launcher.setSe( se );
		launcher.setGitRepository( gitRepository );
		launcher.setChrono( getChrono() );
		
	}

	public String specExecutionResultFile() {
		return launcher.getRunnerDirectory() 
			+ launcher.getExecutionOutputDirectory() 
			+ "/" + launcher.getSe() + ".html";
	}

	public void setRunnerDirectory(String path) {
		launcher.setRunnerDirectory(path);
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

	public String html() throws IOException {
		String template = super.html();
		String page = template.replaceAll( "page-content", pageContent() );
		return page;
	}

	protected void workBeforeRenderingHtml() {
		try {
			launcher.go();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String pageContent() throws IOException {
		workBeforeRenderingHtml();
		
		String content = "";
		
		content += getModifySection();
		content += readFile( specExecutionResultFile() );
		content += readFile( "target/html/invitation.html" );
		
		return content;
	}

	private String getModifySection() {
		ModifyLink section = new ModifyLink();
		section.setSpecX( launcher.getSe() );
		return section.html();
	}
	
}
