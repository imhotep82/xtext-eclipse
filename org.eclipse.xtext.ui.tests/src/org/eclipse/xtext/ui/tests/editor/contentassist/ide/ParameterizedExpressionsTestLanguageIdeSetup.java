/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.tests.editor.contentassist.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.ui.tests.editor.contentassist.ParameterizedExpressionsTestLanguageRuntimeModule;
import org.eclipse.xtext.ui.tests.editor.contentassist.ParameterizedExpressionsTestLanguageStandaloneSetup;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class ParameterizedExpressionsTestLanguageIdeSetup extends ParameterizedExpressionsTestLanguageStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new ParameterizedExpressionsTestLanguageRuntimeModule(), new ParameterizedExpressionsTestLanguageIdeModule()));
	}
	
}
