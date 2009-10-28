package org.coode.oppl.entity;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owl.model.OWLEntity;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyChange;

/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: 23-Sep-2006<br>
 * <br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br>
 * <br>
 */
public class OWLEntityCreationSet<E extends OWLEntity> {
	private E owlEntity;
	private List<OWLOntologyChange> changes;

	public OWLEntityCreationSet(E owlEntity,
			List<? extends OWLOntologyChange> changes) {
		this.owlEntity = owlEntity;
		this.changes = new ArrayList<OWLOntologyChange>(changes);
	}

	public OWLEntityCreationSet(E owlEntity, OWLOntology ontology) {
		this.owlEntity = owlEntity;
		changes = new ArrayList<OWLOntologyChange>();
		// changes.add(new AddEntity(ontology, owlEntity, null));
	}

	public E getOWLEntity() {
		return owlEntity;
	}

	public List<? extends OWLOntologyChange> getOntologyChanges() {
		return changes;
	}
}