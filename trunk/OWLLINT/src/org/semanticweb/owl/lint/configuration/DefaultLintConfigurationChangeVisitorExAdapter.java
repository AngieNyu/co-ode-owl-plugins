/**
 * 
 */
package org.semanticweb.owl.lint.configuration;

/**
 * @author Luigi Iannone
 * 
 */
public abstract class DefaultLintConfigurationChangeVisitorExAdapter<O> implements
		LintConfigurationChangeEventVisitorEx<O> {
	protected abstract O doDefault(LintConfigurationChangeEvent e);

	/**
	 * @see org.semanticweb.owl.lint.configuration.LintConfigurationChangeEventVisitorEx#visitPropertyValueChanged(org.semanticweb.owl.lint.configuration.PropertyValueChanged)
	 */
	public O visitPropertyValueChanged(PropertyValueChanged propertyValueChanged) {
		return this.doDefault(propertyValueChanged);
	}
}
