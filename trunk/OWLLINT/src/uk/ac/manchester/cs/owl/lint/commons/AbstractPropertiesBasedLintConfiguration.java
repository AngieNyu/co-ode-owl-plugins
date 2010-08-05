package uk.ac.manchester.cs.owl.lint.commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.semanticweb.owl.lint.Lint;
import org.semanticweb.owl.lint.configuration.LintConfiguration;
import org.semanticweb.owl.lint.configuration.LintConfigurationChangeEvent;
import org.semanticweb.owl.lint.configuration.LintConfigurationChangeListener;
import org.semanticweb.owl.lint.configuration.LintConfigurationVisitor;
import org.semanticweb.owl.lint.configuration.LintConfigurationVisitorEx;
import org.semanticweb.owl.lint.configuration.PropertyBasedLintConfiguration;
import org.semanticweb.owl.lint.configuration.PropertyValueChanged;

/**
 * Abstract implementation of a PropertiesBasedLint that provides a default
 * location for properties. i.e.: the file called
 * {@literal <classname>.properties}
 * 
 * @author Luigi Iannone
 * 
 * 
 */
public abstract class AbstractPropertiesBasedLintConfiguration implements LintConfiguration,
		PropertyBasedLintConfiguration {
	private final Lint<?> lint;
	private Properties properties = null;
	private final Set<LintConfigurationChangeListener> lintConfigurationChangeListeners = new HashSet<LintConfigurationChangeListener>();

	/**
	 * @param lint
	 */
	public AbstractPropertiesBasedLintConfiguration(Lint<?> lint) {
		if (lint == null) {
			throw new NullPointerException("The lint cannot be null");
		}
		this.lint = lint;
	}

	/**
	 * 
	 */
	private void initProperties() {
		this.properties = new Properties();
		String fileName = this.getPropertyFileName();
		InputStream in = this.getLint().getClass().getResourceAsStream(fileName);
		if (in != null) {
			try {
				this.properties.load(in);
			} catch (IOException e) {
				Logger.getLogger(this.getClass().getCanonicalName()).log(
						Level.WARNING,
						"The properties could not be loaded from the file " + fileName);
			}
		}
	}

	public Properties getProperties() {
		if (this.properties == null) {
			this.initProperties();
		}
		return this.properties;
	}

	/**
	 * @return
	 */
	protected String getPropertyFileName() {
		return this.getLint().getClass().getName() + ".properties";
	}

	public void accept(LintConfigurationVisitor visitor) {
		visitor.visitPropertiesBasedLintConfiguration(this);
	}

	/**
	 * @return the lint
	 */
	public Lint<?> getLint() {
		return this.lint;
	}

	public <P> P accept(LintConfigurationVisitorEx<P> visitor) {
		return visitor.visitPropertiesBasedLintConfiguration(this);
	}

	public Set<String> getPropertyKeys() {
		Set<String> toReturn = new HashSet<String>();
		Properties properties = this.getProperties();
		Set<Object> keySet = properties.keySet();
		for (Object object : keySet) {
			// You never know the object could technically be null
			if (object != null) {
				toReturn.add(object.toString());
			}
		}
		return toReturn;
	}

	public String getPropertyValue(String key) {
		if (key == null) {
			throw new NullPointerException("The key cannot be null");
		}
		String property = this.getProperties().getProperty(key);
		return property;
	}

	public void setProperty(String key, String value) {
		if (key == null) {
			throw new NullPointerException("The key cannot be null");
		}
		if (this.getPropertyKeys().contains(key)) {
			String oldValue = this.getProperties().getProperty(key);
			this.getProperties().setProperty(key, value);
			this.notifyListeners(new PropertyValueChanged(this.getLint(), key, oldValue, value));
		}
	}

	public void store() throws IOException {
		FileOutputStream out = new FileOutputStream(this.getFile());
		this.getProperties().store(out, "");
		out.close();
	}

	/**
	 * @return
	 */
	protected File getFile() {
		return new File(this.getLint().getClass().getResource(this.getPropertyFileName()).getFile());
	}

	private void notifyListeners(LintConfigurationChangeEvent event) {
		for (LintConfigurationChangeListener l : this.lintConfigurationChangeListeners) {
			l.configurationChanged(event);
		}
	}

	public void addLintConfigurationChangeListener(LintConfigurationChangeListener l) {
		if (l == null) {
			throw new NullPointerException("The listener cannot be null");
		}
		this.lintConfigurationChangeListeners.add(l);
	}

	public void removeLintConfigurationChangeListener(LintConfigurationChangeListener l) {
		this.lintConfigurationChangeListeners.remove(l);
	}

	public void removeAllListeners() {
		this.lintConfigurationChangeListeners.clear();
	}
}
