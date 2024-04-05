package zigzen.lang.lexer;

import com.intellij.psi.tree.IElementType;

import static psi.zigzen.ZonTypes.*;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;

%%

%class ZonLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

CRLF=\R
WHITE_SPACE=[\s]+
LINE_COMMENT="//" [^\n]* | "////" [^\n]*
COMMENT="///".*

ID=[A-Za-z_][A-Za-z0-9_]*

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

<YYINITIAL>      {WHITE_SPACE}            { return WHITE_SPACE; }
<YYINITIAL>      "."                      { return DOT; }
<YYINITIAL>      "IntellijIdeaRulezzz"    { return COMPLETION_DUMMY; }
<YYINITIAL>      "{"                      { return LBRACE; }
<YYINITIAL>      "}"                      { return RBRACE; }
<YYINITIAL>      "="                      { return EQUAL; }
<YYINITIAL>      ","                      { return COMMA; }
<YYINITIAL>      {COMMENT}                { return COMMENT; }
<YYINITIAL>      {LINE_COMMENT}           { return COMMENT; }

<YYINITIAL>      {ID}                     { return ID; }
<YYINITIAL>      "@\""                    { yybegin(ID_STRING); }
<ID_STRING>      {string_char}*"\""       { yybegin(YYINITIAL); return ID; }
<ID_STRING>      [^]                      { yypushback(1); yybegin(UNCLOSED_STRING); }

<YYINITIAL>      "\""                     { yybegin(STRING_LITERAL); }
<STRING_LITERAL> {string_char}*"\""       { yybegin(YYINITIAL); return STRING_LITERAL_SINGLE; }
<STRING_LITERAL> [^]                      { yypushback(1); yybegin(UNCLOSED_STRING); }

<UNCLOSED_STRING>[^\n]*{CRLF}             { yybegin(YYINITIAL); return BAD_STRING; }

<YYINITIAL>      {LINE_STRING}            { return LINE_STRING; }

[^] { return BAD_CHARACTER; }
