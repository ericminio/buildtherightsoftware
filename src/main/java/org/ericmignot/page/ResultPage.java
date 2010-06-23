package org.ericmignot.page;

import java.io.IOException;
import java.util.Date;

import org.ericmignot.TryThisCode;

public class ResultPage implements Page {

	private TryThisCode launcher;
	private String se;
	private String gitRepository;
	private String chrono;
	
	private SecondColumn secondColumn;
	
	public ResultPage(String se, String gitRepository) {
		setLauncher( new TryThisCode() );
		this.se = se;
		this.gitRepository = gitRepository;
		this.chrono = ""+new Date().getTime();
		
		setSecondColumn(new SecondColumn());
		String projectName = launcher.extractProjectName( gitRepository );
		String executionOutputFile = "specs/" + chrono + "/" + projectName + "/se/out/" + se + ".html";
		secondColumn.setContent( executionOutputFile );
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

	public TryThisCode getLauncher() {
		return launcher;
	}

	protected void launch() {
		launcher.setSe( se );
		launcher.setGitRepository( gitRepository );
		launcher.setChrono( chrono );
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
	}

	public String getChrono() {
		return chrono;
	}
	
}
