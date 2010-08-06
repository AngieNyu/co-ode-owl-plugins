/**
 * 
 */
package org.semanticweb.owl.lint.configuration;

import uk.ac.manchester.cs.owl.lint.commons.NonConfigurableLintConfiguration;

/**
 * @author Luigi Iannone
 * 
 */
public abstract class DefaultLintConfigurationVisitorAdapter implements LintConfigurationVisitor {
	protected abstract void doDefault(LintConfiguration lintConfiguration);

	public void visitNonConfigurableLintConfiguration(
			NonConfigurableLintConfiguration nonConfigurableLintConfiguration) {
		this.doDefault(nonConfigurableLintConfiguration);
	}

	public void visitPropertiesBasedLintConfiguration(
			PropertyBasedLintConfiguration propertiesBasedLintConfiguration) {
		this.doDefault(propertiesBasedLintConfiguration);
	}
}