package org.coode.oppl.variablemansyntax.generated.factory;

import java.util.List;

import org.coode.oppl.variablemansyntax.ConstraintSystem;
import org.coode.oppl.variablemansyntax.Variable;
import org.coode.oppl.variablemansyntax.bindingtree.BindingNode;
import org.coode.oppl.variablemansyntax.generated.Attribute;
import org.coode.oppl.variablemansyntax.generated.VariableGeneratedValue;

public class RenderingVariableGeneratedValue extends
		VariableGeneratedValue<String> {
	private final ConstraintSystem constraintSystem;

	public RenderingVariableGeneratedValue(Variable variable,
			Attribute attribute, ConstraintSystem constraintSystem) {
		super(variable, attribute);
		this.constraintSystem = constraintSystem;
	}

	@Override
	public String getGeneratedValue(BindingNode node) {
		return RenderingAttributeGenerator.getInstance(this.constraintSystem)
				.getValue(node.getAssignmentValue(getVariable()));
	}

	@Override
	public List<String> getGeneratedValues() {
		return RenderingAttributeGenerator.getInstance(this.constraintSystem)
				.getValues(getVariable());
	}
}