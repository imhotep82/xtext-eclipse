lexer grammar CodetemplatesContentAssistLexer;

options {
	tokenVocab=InternalCodetemplatesLexer;
}

@header {
package org.eclipse.xtext.ui.codetemplates.ui.contentassist;

// Use our own Lexer superclass by means of import. 
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@members{
  private boolean literal = false;
  private boolean templateVariable = false;
}


Templates : {!literal || templateVariable}?=>'templates';

ReverseSolidusLessThanSignLessThanSign : '\\<<';

For : {!literal || templateVariable}?=>'for';

DollarSignDollarSign : '$$';

DollarSignLeftCurlyBracket : '${' {templateVariable = true;};

LessThanSignLessThanSign : '<<' { if (literal) literal = false; };

GreaterThanSignGreaterThanSign : {!literal || templateVariable}?=>'>>' { if (!literal) literal = true; };

DollarSign : '$';

LeftParenthesis : {!literal || templateVariable}?=>'(';

RightParenthesis : {!literal || templateVariable}?=>')';

Comma : {!literal || templateVariable}?=>',';

FullStop : {!literal || templateVariable}?=>'.';

Colon : {!literal || templateVariable}?=>':';

RightCurlyBracket : {!literal || templateVariable}?=>'}' {templateVariable = false;};



RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_STRING : {!literal || templateVariable}?=>( '\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'' );

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;




