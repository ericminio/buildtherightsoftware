package org.ericmignot.controller;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.domain.PlainTextSpec;

public class DefaultController extends ShowController {

	public void handle(HttpServletRequest request, SpecRepository repository,
			Writer out) {
		view.setSpec( sampleSpec() );
		view.render( out );
	}

	private Spec sampleSpec() {
		PlainTextSpec spec = new PlainTextSpec( "sample" );
		spec.setContent( sampleContent() );
		return spec;
	}
	
	private String sampleContent() {
		return "<p>"
				+ "<table>" 
				+ "<tr>" 
				+ "	<td class=\"rulefor\">Rule for</td>" 
				+ "	<td>mastermind</td>" 
				+ "</tr>"
				+ "<tr>" 
				+ "<td class=\"ruleforheader\">given the secret</td>" 
				+ "<td class=\"ruleforheader\">when player plays</td>" 
				+ "<td class=\"ruleforheader\">does he win ?</td>" 
				+ "</tr>"
				+ "<tr> <td>green</td> <td>red</td> <td>no</td> </tr>"
				+ "<tr> <td>green</td> <td>green</td> <td>yes</td> </tr>"
				+ "</table>"
			 + "</p>";
	}

}
