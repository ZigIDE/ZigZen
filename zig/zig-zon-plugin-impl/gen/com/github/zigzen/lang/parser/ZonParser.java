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
  // ZonStruct
  static boolean Root(PsiBuilder b, int l) {
    return ZonStruct(b, l + 1);
  }

  /* ********************************************************** */
  // DOT LBRACE (ZonStructPropertyMap | ZonStructStringArrayElement)? RBRACE
  public static boolean ZonStruct(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStruct")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, LBRACE);
    r = r && ZonStruct_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, ZON_STRUCT, r);
    return r;
  }

  // (ZonStructPropertyMap | ZonStructStringArrayElement)?
  private static boolean ZonStruct_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStruct_2")) return false;
    ZonStruct_2_0(b, l + 1);
    return true;
  }

  // ZonStructPropertyMap | ZonStructStringArrayElement
  private static boolean ZonStruct_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStruct_2_0")) return false;
    boolean r;
    r = ZonStructPropertyMap(b, l + 1);
    if (!r) r = ZonStructStringArrayElement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // DOT IDENTIFIER EQUAL ZonStructPropertyValue
  public static boolean ZonStructProperty(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructProperty")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER, EQUAL);
    r = r && ZonStructPropertyValue(b, l + 1);
    exit_section_(b, m, ZON_STRUCT_PROPERTY, r);
    return r;
  }

  /* ********************************************************** */
  // (ZonStructProperty) (COMMA ZonStructPropertyMap)* COMMA?
  public static boolean ZonStructPropertyMap(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructPropertyMap")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ZonStructPropertyMap_0(b, l + 1);
    r = r && ZonStructPropertyMap_1(b, l + 1);
    r = r && ZonStructPropertyMap_2(b, l + 1);
    exit_section_(b, m, ZON_STRUCT_PROPERTY_MAP, r);
    return r;
  }

  // (ZonStructProperty)
  private static boolean ZonStructPropertyMap_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructPropertyMap_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ZonStructProperty(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA ZonStructPropertyMap)*
  private static boolean ZonStructPropertyMap_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructPropertyMap_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ZonStructPropertyMap_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ZonStructPropertyMap_1", c)) break;
    }
    return true;
  }

  // COMMA ZonStructPropertyMap
  private static boolean ZonStructPropertyMap_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructPropertyMap_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ZonStructPropertyMap(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean ZonStructPropertyMap_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructPropertyMap_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // ZonStruct | (STRING_LITERAL_SINGLE | LINE_STRING+)
  static boolean ZonStructPropertyValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructPropertyValue")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ZonStruct(b, l + 1);
    if (!r) r = ZonStructPropertyValue_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // STRING_LITERAL_SINGLE | LINE_STRING+
  private static boolean ZonStructPropertyValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructPropertyValue_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_LITERAL_SINGLE);
    if (!r) r = ZonStructPropertyValue_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LINE_STRING+
  private static boolean ZonStructPropertyValue_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructPropertyValue_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LINE_STRING);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, LINE_STRING)) break;
      if (!empty_element_parsed_guard_(b, "ZonStructPropertyValue_1_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (STRING_LITERAL_SINGLE | LINE_STRING+) (COMMA ZonStructStringArrayElement)* COMMA?
  public static boolean ZonStructStringArrayElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructStringArrayElement")) return false;
    if (!nextTokenIs(b, "<zon struct string array element>", LINE_STRING, STRING_LITERAL_SINGLE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ZON_STRUCT_STRING_ARRAY_ELEMENT, "<zon struct string array element>");
    r = ZonStructStringArrayElement_0(b, l + 1);
    r = r && ZonStructStringArrayElement_1(b, l + 1);
    r = r && ZonStructStringArrayElement_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // STRING_LITERAL_SINGLE | LINE_STRING+
  private static boolean ZonStructStringArrayElement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructStringArrayElement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING_LITERAL_SINGLE);
    if (!r) r = ZonStructStringArrayElement_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LINE_STRING+
  private static boolean ZonStructStringArrayElement_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructStringArrayElement_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LINE_STRING);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, LINE_STRING)) break;
      if (!empty_element_parsed_guard_(b, "ZonStructStringArrayElement_0_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA ZonStructStringArrayElement)*
  private static boolean ZonStructStringArrayElement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructStringArrayElement_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ZonStructStringArrayElement_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ZonStructStringArrayElement_1", c)) break;
    }
    return true;
  }

  // COMMA ZonStructStringArrayElement
  private static boolean ZonStructStringArrayElement_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructStringArrayElement_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ZonStructStringArrayElement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean ZonStructStringArrayElement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ZonStructStringArrayElement_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

}
