// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.zigzen.psi.ZonTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ZonParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return Root(b, l + 1);
  }

  /* ********************************************************** */
  // ID
  public static boolean Identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Identifier")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // Struct
  static boolean Root(PsiBuilder b, int l) {
    return Struct(b, l + 1);
  }

  /* ********************************************************** */
  // DOT LBRACE (StructPropertyMap | StructStringArrayElement)? RBRACE
  public static boolean Struct(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struct")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, LBRACE);
    r = r && Struct_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, STRUCT, r);
    return r;
  }

  // (StructPropertyMap | StructStringArrayElement)?
  private static boolean Struct_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struct_2")) return false;
    Struct_2_0(b, l + 1);
    return true;
  }

  // StructPropertyMap | StructStringArrayElement
  private static boolean Struct_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struct_2_0")) return false;
    boolean r;
    r = StructPropertyMap(b, l + 1);
    if (!r) r = StructStringArrayElement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // DOT Identifier EQUAL StructPropertyValue
  public static boolean StructProperty(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructProperty")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && Identifier(b, l + 1);
    r = r && consumeToken(b, EQUAL);
    r = r && StructPropertyValue(b, l + 1);
    exit_section_(b, m, STRUCT_PROPERTY, r);
    return r;
  }

  /* ********************************************************** */
  // (StructProperty) (COMMA StructPropertyMap)* COMMA?
  public static boolean StructPropertyMap(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructPropertyMap_0(b, l + 1);
    r = r && StructPropertyMap_1(b, l + 1);
    r = r && StructPropertyMap_2(b, l + 1);
    exit_section_(b, m, STRUCT_PROPERTY_MAP, r);
    return r;
  }

  // (StructProperty)
  private static boolean StructPropertyMap_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructProperty(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA StructPropertyMap)*
  private static boolean StructPropertyMap_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StructPropertyMap_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StructPropertyMap_1", c)) break;
    }
    return true;
  }

  // COMMA StructPropertyMap
  private static boolean StructPropertyMap_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && StructPropertyMap(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean StructPropertyMap_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // Struct | (STRING_LITERAL_SINGLE | LINE_STRING+)
  static boolean StructPropertyValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyValue")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Struct(b, l + 1);
    if (!r) r = StructPropertyValue_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // STRING_LITERAL_SINGLE | LINE_STRING+
  private static boolean StructPropertyValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyValue_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_LITERAL_SINGLE);
    if (!r) r = StructPropertyValue_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LINE_STRING+
  private static boolean StructPropertyValue_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyValue_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LINE_STRING);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, LINE_STRING)) break;
      if (!empty_element_parsed_guard_(b, "StructPropertyValue_1_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (STRING_LITERAL_SINGLE | LINE_STRING+) (COMMA StructStringArrayElement)* COMMA?
  public static boolean StructStringArrayElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructStringArrayElement")) return false;
    if (!nextTokenIs(b, "<struct string array element>", LINE_STRING, STRING_LITERAL_SINGLE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, STRUCT_STRING_ARRAY_ELEMENT, "<struct string array element>");
    r = StructStringArrayElement_0(b, l + 1);
    r = r && StructStringArrayElement_1(b, l + 1);
    r = r && StructStringArrayElement_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // STRING_LITERAL_SINGLE | LINE_STRING+
  private static boolean StructStringArrayElement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructStringArrayElement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_LITERAL_SINGLE);
    if (!r) r = StructStringArrayElement_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LINE_STRING+
  private static boolean StructStringArrayElement_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructStringArrayElement_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LINE_STRING);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, LINE_STRING)) break;
      if (!empty_element_parsed_guard_(b, "StructStringArrayElement_0_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA StructStringArrayElement)*
  private static boolean StructStringArrayElement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructStringArrayElement_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StructStringArrayElement_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StructStringArrayElement_1", c)) break;
    }
    return true;
  }

  // COMMA StructStringArrayElement
  private static boolean StructStringArrayElement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructStringArrayElement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && StructStringArrayElement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean StructStringArrayElement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructStringArrayElement_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

}
