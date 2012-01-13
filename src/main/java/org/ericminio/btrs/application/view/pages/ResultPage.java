package org.ericminio.btrs.application.view.pages;

import static org.ericminio.btrs.store.FileUtils.readFile;

import java.io.IOException;
import java.io.Writer;

import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.domain.Spec;

public class ResultPage implements Renderer {

	private String directory;
	private String chrono;
	private Spec spec;
	private String repositoryName;
	private String sourceRepositoryUrl;
	
	public void setWorkingDirectory(String directory) {
		this.directory = directory;
	}
	
	public void setChrono(String chrono) {
		this.chrono = chrono;
	}
	
	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	public void setGitRepositoryName(String gitRepositoryName) {
		this.repositoryName = gitRepositoryName;
	}


	public String getResultPagePath() {
		return directory + "/runs/" + chrono + "/" + repositoryName + "/se/out/" + spec.getTitle() + ".html";
	}
	
	public String getCoberturaReportPath() {
		return directory + "/runs/" + chrono + "/" + repositoryName + "/target/site/cobertura/frame-summary.html";
	}

	public void render(Writer out) throws IOException {
		String template = readFile( "target/html/template.html" );
		String page = template.replaceAll( "page-content", pageContent() );
		out.write( page );
	}
	
	protected String pageContent() throws IOException {
		String content = sourceRepositoryMention() + specLabel() + specContent() + coberturaReport();
		return content;
	}
	
	protected String sourceRepositoryMention() {
		return "<p><span class=\"repository\">Repository: " + this.sourceRepositoryUrl + "</span></p>";
	}
	
	protected String specLabel() {
		return "<p><span class=\"label\">Label: " + spec.getLabel() + "</span></p>";
	}

	protected String specContent() {
		return readFile( getResultPagePath() );
	}

	protected String coberturaReport() {
		String fullContent = readFile( getCoberturaReportPath() );
		String body = extractBodyContent( fullContent );
		return removeAllScriptSections( body );
	}
	
	public String extractBodyContent(String fullContent) {
		String body = fullContent.substring( fullContent.indexOf("<body>") );
		return body.substring( 6, body.indexOf( "</body>" ));
	}

	public String removeScriptSection(String content) {
		String begin = content.substring( 0, content.indexOf( "<script" ) );
		String end = content.substring( content.indexOf( "</script>" ) + 9 );
		return begin + end;
	}

	public String removeAllScriptSections(String content) {
		String newContent = content;
		while ( newContent.indexOf( "<script" ) != -1) {
			newContent = removeScriptSection( newContent );
		}
		return newContent;
	}

	public void setSourceRepositoryUrl(String url) {
		this.sourceRepositoryUrl = url;
	}
	
}
