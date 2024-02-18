// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.zigzen.psi.impl.*;

public interface ZonTypes {

  IElementType IDENTIFIER = new ZonElementType("IDENTIFIER");
  IElementType INCOMPLETE_STRUCT_PROPERTY = new ZonElementType("INCOMPLETE_STRUCT_PROPERTY");
  IElementType STRING_LITERAL = new ZonElementType("STRING_LITERAL");
  IElementType STRUCT = new ZonElementType("STRUCT");
  IElementType STRUCT_PROPERTY = new ZonElementType("STRUCT_PROPERTY");
  IElementType STRUCT_PROPERTY_MAP = new ZonElementType("STRUCT_PROPERTY_MAP");
  IElementType STRUCT_STRING_ARRAY = new ZonElementType("STRUCT_STRING_ARRAY");

  IElementType BAD_STRING = new ZonTokenType("unterminated string");
  IElementType COMMA = new ZonTokenType(",");
  IElementType COMMENT = new ZonTokenType("comment");
  IElementType COMPLETION_DUMMY = new ZonTokenType("COMPLETION_DUMMY");
  IElementType DOT = new ZonTokenType(".");
  IElementType EQUAL = new ZonTokenType("=");
  IElementType ID = new ZonTokenType("identifier");
  IElementType LBRACE = new ZonTokenType("{");
  IElementType LINE_STRING = new ZonTokenType("multiline string");
  IElementType RBRACE = new ZonTokenType("}");
  IElementType STRING_LITERAL_SINGLE = new ZonTokenType("string");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == IDENTIFIER) {
        return new ZonIdentifierImpl(node);
      }
      else if (type == INCOMPLETE_STRUCT_PROPERTY) {
        return new ZonIncompleteStructPropertyImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new ZonStringLiteralImpl(node);
      }
      else if (type == STRUCT) {
        return new ZonStructImpl(node);
      }
      else if (type == STRUCT_PROPERTY) {
        return new ZonStructPropertyImpl(node);
      }
      else if (type == STRUCT_PROPERTY_MAP) {
        return new ZonStructPropertyMapImpl(node);
      }
      else if (type == STRUCT_STRING_ARRAY) {
        return new ZonStructStringArrayImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
