/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.autoedit;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.xtext.ui.editor.model.DocumentUtil;

import com.google.inject.Inject;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public abstract class AbstractTerminalsEditStrategy extends AbstractEditStrategy {
	
	private String leftTerminal, rightTerminal;
	
	public AbstractTerminalsEditStrategy(String leftTerminal, String rightTerminal) {
		if (leftTerminal==null || rightTerminal==null)
			throw new IllegalArgumentException("The terminals must not be null.");
		if (!allowsEqualTerminals() && leftTerminal.equals(rightTerminal)) {
			throw new IllegalArgumentException("The left and right terminal have to be different.");
		}
		this.leftTerminal = leftTerminal;
		this.rightTerminal = rightTerminal;
	}
	
	public String getLeftTerminal() {
		return leftTerminal;
	}
	
	public String getRightTerminal() {
		return rightTerminal;
	}
	
	protected boolean allowsEqualTerminals() {
		return false;
	}
	
	/**
	 * finds the first stop terminal which has not been started after the cursor position.
	 * @throws BadPartitioningException if the document is properly configured
	 */
	protected IRegion findStopTerminal(IDocument document, int offset) throws BadLocationException, BadPartitioningException {
		String documentText = document.get();
		int stopOffset = offset;
		int startOffset = offset;
		while (true) {
			IRegion stop = util.searchInSamePartition(getRightTerminal(), documentText, document, stopOffset);
			if (stop == null) {
				return null;
			}
			IRegion start = util.searchInSamePartition(getLeftTerminal(), documentText, document, startOffset);
			if (start == null || start.getOffset() > stop.getOffset()) {
				return stop;
			}
			stopOffset = util.findNextOffSetInPartition(document, stopOffset, stop.getOffset() + stop.getLength());
			startOffset = util.findNextOffSetInPartition(document, startOffset, start.getOffset() + start.getLength());
		}
	}

	/**
	 * finds the first start terminal which is not closed before the cursor position.
	 * @throws BadPartitioningException if the document is properly configured
	 */
	protected IRegion findStartTerminal(IDocument document, int offset) throws BadLocationException, BadPartitioningException {
		String documentText = document.get();
		int stopOffset = offset;
		int startOffset = offset;
		while (true) {
			IRegion start = util.searchBackwardsInSamePartition(getLeftTerminal(), documentText, document, startOffset);
			if (start == null) {
				return null;
			}
			IRegion stop = util.searchBackwardsInSamePartition(getRightTerminal(), documentText, document, stopOffset);
			if (stop == null || stop.getOffset() < start.getOffset())
				return start;
			stopOffset = stop.getOffset();
			startOffset = start.getOffset();
		}
	}

}