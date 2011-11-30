package org.ericminio.btrs.application.route.controllers;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecMatcher;
import org.ericminio.btrs.domain.SpecRepository;

public class RepositoryMockBuilder {

	private Map<String, Spec> specs;
	
	public RepositoryMockBuilder() {
		specs = new HashMap<String, Spec>(); 
	}
	
	public static RepositoryMockBuilder aRepo() {
		return new RepositoryMockBuilder();
	}
	
	public RepositoryMockBuilder withSpec(Spec spec) {
		specs.put( spec.getTitle(), spec);
		return this;
	}
	
	public SpecRepository build() {
		SpecRepository repoMock = mock( SpecRepository.class );
		for (Spec spec : specs.values()) {
			when(repoMock.getSpecByTitle( spec.getTitle() ) ).thenReturn( spec );
		}
		when(repoMock.getSpecs( (SpecMatcher) anyObject() )).thenReturn( new ArrayList<Spec>(specs.values()) );
		return repoMock;
	}
	
}
