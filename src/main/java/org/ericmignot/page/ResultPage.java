package org.ericmignot.page;

import java.io.IOException;
import java.util.Date;

import org.ericmignot.core.TryThisCode;
import org.ericmignot.page.section.SecondColumn;

public class ResultPage extends Page {

	private TryThisCode launcher;
	private String chrono;
	
	public ResultPage(String se, String gitRepository) {
		this.chrono = ""+new Date().getTime();
		
		TryThisCode launcher = new TryThisCode();
		launcher.setSe( se );
		launcher.setGitRepository( gitRepository );
		launcher.setChrono( getChrono() );
		setLauncher(  launcher );
		
		setSecondColumn(new SecondColumn());
	}

	@Override
	protected void updateSpecificContent() {
		String executionOutputFile = launcher.getRunnerDirectory() + launcher.getExecutionOutputDirectory() + "/" + launcher.getSe() + ".html";
		getSecondColumn().setContent( executionOutputFile );
	}

	@Override
	protected void workBeforeRenderingHtml() {
		try {
			launcher.go();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setRunnerDirectory(String path) {
		launcher.setRunnerDirectory(path);
		updateSpecificContent();
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
	
}
