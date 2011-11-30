package org.ericminio.btrs.store;

import static org.ericminio.btrs.domain.matchers.CoreMatchers.*;
import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Map;

import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecMatcher;
import org.junit.Test;

public class InMemoryRepositoryTest {

	private InMemoryRepository repository = new InMemoryRepository();
	
	@Test public void
	storesTheSpecInAnInternalMapUsingTheTitleAsTheKeyWhenSavingTheSpec() {
		Map<String, Spec> mapMock = mock( Map.class );
		repository.setSpecStoringMap( mapMock );
		
		Spec spec = aSpec().withTitle( "a title").build() ;

		repository.saveSpec( spec );
		verify( mapMock ).put( "a title" , spec ); 
	}
	
	@Test public void
	canRetrieveSpecsByName() {
		Spec first = aSpec().withTitle( "first" ).build();
		Spec second = aSpec().withTitle( "second" ).build();
		repository.saveSpec( first );
		repository.saveSpec( second );
		assertThat( repository.getSpecByTitle( "first" ), equalTo( first ) );
		assertThat( repository.getSpecByTitle( "second" ), equalTo( second ) );
	}
	
	@Test public void
	canRetrieveAllSpecs() {
		Spec first = aSpec().withTitle( "first" ).build();
		Spec second = aSpec().withTitle( "second" ).build();
		repository.saveSpec( first );
		repository.saveSpec( second );
		List<Spec> specs = repository.getSpecs( all() );
		assertTrue( specs.contains( first ) );
		assertTrue( specs.contains( second ) );
	}
	
	@Test public void
	canRetrieveSpecsByLabel() {
		Spec first = aSpec().withTitle( "first" ).withLabel( "one" ).build();
		Spec second = aSpec().withTitle( "second" ).withLabel( "two" ).build();
		repository.saveSpec( first );
		repository.saveSpec( second );
		List<Spec> specs = repository.getSpecs( withLabel( "one" ) );
		assertTrue( specs.contains( first ) );
		assertFalse( specs.contains( second ) );
	}
	
}
