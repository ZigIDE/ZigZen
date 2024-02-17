package com.github.zigzen.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;

%%

%class ZonLexer
%implements FlexLexer
%function advance
%type IElementType

LF=\n
WHITESPACE=[\s]+
COMMENT="///".*
LINE_COMMENT="//" [^\n]* | "////" [^\n]*

IDENTIFIER=[A-Za-z_][A-Za-z0-9_]*

hex=[0-9a-fA-F]
char_escape
    = "\\x" {hex} {hex}
    | "\\u{" {hex}+ "}"
    | "\\" [nr\\t'\"]

string_char
    = {char_escape}
    | [^\\\"\n]

LINE_STRING=("\\\\" [^\n]* [ \n]*)+

%state STRING_LITERAL
%state ID_STRING
%state UNCLOSED_STRING
%%

<YYINITIAL>      {WHITESPACE}            { return WHITE_SPACE; }

[^] { return BAD_CHARACTER; }
