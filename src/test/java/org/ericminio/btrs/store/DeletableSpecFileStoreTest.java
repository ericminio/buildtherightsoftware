package org.ericminio.btrs.store;

import static org.ericminio.btrs.store.SpecBuilder.aSpec;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;

import org.ericminio.btrs.domain.Spec;
import org.junit.Test;


public class DeletableSpecFileStoreTest {

	DeletableSpecFileStore repository = new DeletableSpecFileStore("target/test-classes/test-system/");
	
	@Test public void
	canDeleteAllSpecContentFiles() throws IOException {
		Spec tetris = aSpec().withTitle( "tetris" ).withLabel( "game" ).build();
		repository.saveSpec( tetris );
		repository.deleteAllSpecs();
		assertFalse( "spec content exists", new File( "target/test-classes/test-system/tetris.html" ).exists() );
	}
	
	@Test public void
	canDeleteAllSpecLabelsFiles() throws IOException {
		Spec tetris = aSpec().withTitle( "tetris" ).withLabel( "game" ).build();
		repository.saveSpec( tetris );
		repository.deleteAllSpecs();
		assertFalse( "spec label exists", new File( "target/test-classes/test-system/tetris.label" ).exists() );
	}
}
