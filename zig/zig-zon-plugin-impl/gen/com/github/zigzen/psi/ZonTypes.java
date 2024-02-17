// This is a generated file. Not intended for manual editing.
package com.github.zigzen.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.zigzen.psi.impl.*;

public interface ZonTypes {

  IElementType ZON_STRUCT = new ZonElementType("ZON_STRUCT");
  IElementType ZON_STRUCT_PROPERTY = new ZonElementType("ZON_STRUCT_PROPERTY");
  IElementType ZON_STRUCT_PROPERTY_MAP = new ZonElementType("ZON_STRUCT_PROPERTY_MAP");
  IElementType ZON_STRUCT_STRING_ARRAY_ELEMENT = new ZonElementType("ZON_STRUCT_STRING_ARRAY_ELEMENT");

  IElementType BAD_STRING = new ZonTokenType("unterminated string");
  IElementType COMMA = new ZonTokenType(",");
  IElementType COMMENT = new ZonTokenType("comment");
  IElementType DOT = new ZonTokenType(".");
  IElementType EQUAL = new ZonTokenType("=");
  IElementType IDENTIFIER = new ZonTokenType("identifier");
  IElementType LBRACE = new ZonTokenType("{");
  IElementType LINE_STRING = new ZonTokenType("multiline string");
  IElementType RBRACE = new ZonTokenType("}");
  IElementType STRING_LITERAL_SINGLE = new ZonTokenType("string");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ZON_STRUCT) {
        return new ZonZonStructImpl(node);
      }
      else if (type == ZON_STRUCT_PROPERTY) {
        return new ZonZonStructPropertyImpl(node);
      }
      else if (type == ZON_STRUCT_PROPERTY_MAP) {
        return new ZonZonStructPropertyMapImpl(node);
      }
      else if (type == ZON_STRUCT_STRING_ARRAY_ELEMENT) {
        return new ZonZonStructStringArrayElementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
