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
package org.coode.oae.ui;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import org.protege.editor.owl.ui.framelist.OWLFrameList2;
import org.protege.editor.owl.ui.view.AbstractOWLClassViewComponent;
import org.semanticweb.owl.model.OWLClass;

/**
 * This view displays the formulas related to the selected class
 * 
 * @author Luigi Iannone
 * 
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Apr 2, 2008
 */
public class OWLArithmeticsFormulaClassView extends
		AbstractOWLClassViewComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -823271855893012472L;
	private OWLFrameList2<OWLClass> list;

	/**
	 * @see org.protege.editor.owl.ui.view.AbstractOWLClassViewComponent#initialiseClassView()
	 */
	@Override
	public void initialiseClassView() throws Exception {
		this.list = new OWLFrameList2<OWLClass>(this.getOWLEditorKit(),
				new OWLArithmeticsFormulaClassFrame(this.getOWLEditorKit()));
		this.setLayout(new BorderLayout());
		JScrollPane sp = new JScrollPane(this.list);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(sp);
	}

	/**
	 * This method is called to request that the view is updated with the
	 * specified class.
	 * 
	 * @param selectedClass
	 *            The class that the view should be updated with. Note that this
	 *            may be <code>null</code>, which indicates that the view
	 *            should be cleared
	 * @return The actual class that the view is displaying after it has been
	 *         updated (may be <code>null</code>)
	 */
	@Override
	protected OWLClass updateView(OWLClass selectedClass) {
		this.list.setRootObject(selectedClass);
		return selectedClass;
	}

	/**
	 * @see org.protege.editor.owl.ui.view.AbstractOWLSelectionViewComponent#disposeView()
	 */
	@Override
	public void disposeView() {
		this.list.dispose();
	}
}
