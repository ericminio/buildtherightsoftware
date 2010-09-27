package org.ericmignot.store;

import static org.ericmignot.util.SpecBuilder.aSpec;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Map;

import org.ericmignot.adapters.Spec;
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
	canRetrieveAllASpecs() {
		Spec first = aSpec().withTitle( "first" ).build();
		Spec second = aSpec().withTitle( "second" ).build();
		repository.saveSpec( first );
		repository.saveSpec( second );
		List<Spec> specs = repository.getSpecs();
		assertTrue( specs.contains(first) );
		assertTrue( specs.contains(second) );
	}
	
}
