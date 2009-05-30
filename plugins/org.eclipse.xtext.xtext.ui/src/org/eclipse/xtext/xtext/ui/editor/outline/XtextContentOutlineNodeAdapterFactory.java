/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.editor.outline;

import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.ui.common.editor.outline.impl.DefaultContentOutlineNodeAdapterFactory;

/**
 * @author Peter Friese - Initial contribution and API
 */
public class XtextContentOutlineNodeAdapterFactory extends DefaultContentOutlineNodeAdapterFactory {

	private static final Class[] types = { ParserRule.class };

	public Class[] getAdapterList() {
		return types;
	}

}
