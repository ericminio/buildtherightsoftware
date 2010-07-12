package org.ericmignot.page;

import java.io.IOException;
import java.util.Date;

import org.ericmignot.core.TryThisCode;
import org.ericmignot.page.section.Modify;
import org.ericmignot.util.FileReader;

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

	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
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

	public String pageContent() throws IOException {
		workBeforeRenderingHtml();
		
		String content = "";
		
		content += getModifySection();
		content += fileReader.readFile( specExecutionResultFile() );
		content += fileReader.readFile( "target/html/invitation.html" );
		
		return content;
	}

	private String getModifySection() {
		Modify section = new Modify();
		section.setSpecX( launcher.getSe() );
		return section.html();
	}
	
}
