package org.coode.cardinality.ui.roweditor;

import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.selector.OWLIndividualSelectorPanel;
import org.protege.editor.owl.ui.selector.OWLObjectPropertySelectorPanel;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLProperty;

import javax.swing.*;
import java.awt.*;
/*
* Copyright (C) 2007, University of Manchester
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

/**
 * Author: Nick Drummond<br>
 * http://www.cs.man.ac.uk/~drummond/<br><br>
 * <p/>
 * The University Of Manchester<br>
 * Bio Health Informatics Group<br>
 * Date: Sep 7, 2007<br><br>
 */

public class IndividualFillerRowEditor extends CardinalityRowEditorPanel {

    private OWLObjectPropertySelectorPanel objectPropertySelectorPanel;

    private OWLIndividualSelectorPanel individualSelectorPanel;

    public IndividualFillerRowEditor(OWLEditorKit eKit, OWLClass subject) {
        super(eKit, subject);

        objectPropertySelectorPanel = new OWLObjectPropertySelectorPanel(eKit);
        objectPropertySelectorPanel.setBorder(ComponentFactory.createTitledBorder("Restricted properties"));

        individualSelectorPanel = new OWLIndividualSelectorPanel(eKit);
        individualSelectorPanel.setBorder(ComponentFactory.createTitledBorder("Filler"));

        setLayout(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false);
        splitPane.setResizeWeight(0.5);
        splitPane.setLeftComponent(objectPropertySelectorPanel);
        splitPane.setRightComponent(individualSelectorPanel);
        add(splitPane, BorderLayout.CENTER);
        splitPane.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    }

    protected OWLObject getSelectedFiller() {
        return individualSelectorPanel.getSelectedObject();
    }

    protected OWLProperty getSelectedProperty() {
        return objectPropertySelectorPanel.getSelectedObject();
    }

    public void dispose() {
        objectPropertySelectorPanel.dispose();
        individualSelectorPanel.dispose();
        objectPropertySelectorPanel = null;
        individualSelectorPanel = null;
    }
}
