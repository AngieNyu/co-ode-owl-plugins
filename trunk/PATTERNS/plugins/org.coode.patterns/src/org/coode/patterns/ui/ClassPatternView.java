/**
 * Copyright (C) 2008, University of Manchester
 *
 * Modifications to the initial code base are copyright of their
 * respective authors, or their employers as appropriate.  Authorship
 * of the modifications may be determined from the ChangeLog placed at
 * the end of this file.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.coode.patterns.ui;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import org.coode.patterns.PatternManager;
import org.coode.patterns.protege.ProtegePatternModelFactory;
import org.coode.patterns.syntax.PatternParser;
import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.owl.ui.framelist.OWLFrameList2;
import org.protege.editor.owl.ui.view.AbstractOWLClassViewComponent;
import org.semanticweb.owl.model.OWLClass;

/**
 * @author Luigi Iannone
 * 
 */
public class ClassPatternView extends AbstractOWLClassViewComponent {
	protected OWLFrameList2<OWLClass> list;
	private JScrollPane listPane = null;
	private PatternManager patternManager;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4660000035200458342L;

	/**
	 * @see org.protege.editor.owl.ui.view.AbstractOWLClassViewComponent#initialiseClassView()
	 */
	@Override
	public void initialiseClassView() throws Exception {
		this.setLayout(new BorderLayout());
		this.list = new OWLFrameList2<OWLClass>(this.getOWLEditorKit(),
				new PatternClassFrame(this.getOWLEditorKit()));
		this.list.setCellRenderer(new PatternCellRenderer(this
				.getOWLEditorKit()));
		this.listPane = ComponentFactory.createScrollPane(this.list);
		PatternParser.setPatternModelFactory(new ProtegePatternModelFactory(
				this.getOWLModelManager()));
		this.patternManager = PatternManager.getInstance(this.getOWLEditorKit()
				.getModelManager().getOWLOntologyManager());
		this.getOWLEditorKit().getModelManager().addOntologyChangeListener(
				this.patternManager);
		this.add(this.listPane);
	}

	/**
	 * @see org.protege.editor.owl.ui.view.AbstractOWLSelectionViewComponent#disposeView()
	 */
	@Override
	public void disposeView() {
		if (this.list != null) {
			this.list.dispose();
		}
		if (this.patternManager != null) {
			this.getOWLEditorKit().getModelManager()
					.removeOntologyChangeListener(this.patternManager);
		}
	}

	@Override
	protected OWLClass updateView(OWLClass selectedClass) {
		this.list.setRootObject(selectedClass);
		return selectedClass;
	}
}