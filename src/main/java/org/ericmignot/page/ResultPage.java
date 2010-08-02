package org.ericmignot.page;

import java.io.IOException;
import java.util.Date;

import org.ericmignot.core.TryThisCode;

public class ResultPage extends ShowPage {

	private TryThisCode launcher;
	private String chrono;
	
	public ResultPage(String specX, String gitRepository) {
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

	public String html() throws IOException {
		workBeforeRenderingHtml();
		return super.html();
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
	
}
