package org.ericmignot.page;

import java.io.IOException;
import java.util.Date;

import org.ericmignot.TryThisCode;

public class ResultPage implements Page {

	private TryThisCode launcher;
	private String chrono;
	
	private SecondColumn secondColumn;
	
	public ResultPage(String se, String gitRepository) {
		this.chrono = ""+new Date().getTime();
		
		TryThisCode launcher = new TryThisCode();
		launcher.setSe( se );
		launcher.setGitRepository( gitRepository );
		launcher.setChrono( getChrono() );
		setLauncher(  launcher );
		
		setSecondColumn(new SecondColumn());
	}

	public String html() {
		launch();
		
		return render();
	}

	protected String render() {
		String content = "";
		
		content += new Header().html();
		
		content += "<td class=firstcolumn>";
		content += new FirstColumn().html();
		content += "</td>";
		
		content += "<td valign=top>";
		content += secondColumn.html();
		content += "</td>";
		
		content += new Footer().html();
		
		return content;
	}

	public void setLauncher(TryThisCode launcher) {
		this.launcher = launcher;
	}

	protected void launch() {
		try {
			launcher.go();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public SecondColumn getSecondColumn() {
		return secondColumn;
	}

	public void setSecondColumn(SecondColumn secondColumn) {
		this.secondColumn = secondColumn;
		updateContent();
	}

	private void updateContent() {
		String executionOutputFile = launcher.getRunnerDirectory() + launcher.getExecutionOutputDirectory() + "/" + launcher.getSe() + ".html";
		secondColumn.setContent( executionOutputFile );
	}

	public String getChrono() {
		return chrono;
	}

	public void setRunnerDirectory(String path) {
		launcher.setRunnerDirectory(path);
		updateContent();
	}

	public TryThisCode getLauncher() {
		return launcher;
	}
	
}
