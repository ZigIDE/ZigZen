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
  // DOT? COMPLETION_DUMMY
  public static boolean IncompleteStructProperty(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IncompleteStructProperty")) return false;
    if (!nextTokenIs(b, "<incomplete struct property>", COMPLETION_DUMMY, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INCOMPLETE_STRUCT_PROPERTY, "<incomplete struct property>");
    r = IncompleteStructProperty_0(b, l + 1);
    r = r && consumeToken(b, COMPLETION_DUMMY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DOT?
  private static boolean IncompleteStructProperty_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IncompleteStructProperty_0")) return false;
    consumeToken(b, DOT);
    return true;
  }

  /* ********************************************************** */
  // Struct
  static boolean Root(PsiBuilder b, int l) {
    return Struct(b, l + 1);
  }

  /* ********************************************************** */
  // STRING_LITERAL_SINGLE | LINE_STRING+
  public static boolean STRING_LITERAL(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "STRING_LITERAL")) return false;
    if (!nextTokenIs(b, "<string literal>", LINE_STRING, STRING_LITERAL_SINGLE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LITERAL, "<string literal>");
    r = consumeToken(b, STRING_LITERAL_SINGLE);
    if (!r) r = STRING_LITERAL_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LINE_STRING+
  private static boolean STRING_LITERAL_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "STRING_LITERAL_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LINE_STRING);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, LINE_STRING)) break;
      if (!empty_element_parsed_guard_(b, "STRING_LITERAL_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DOT LBRACE (StructPropertyMap | StructStringArray | ()) RBRACE
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

  // StructPropertyMap | StructStringArray | ()
  private static boolean Struct_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struct_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructPropertyMap(b, l + 1);
    if (!r) r = StructStringArray(b, l + 1);
    if (!r) r = Struct_2_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ()
  private static boolean Struct_2_2(PsiBuilder b, int l) {
    return true;
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
  // (StructProperty | IncompleteStructProperty) (COMMA (IncompleteStructProperty? StructProperty IncompleteStructProperty? | IncompleteStructProperty))* COMMA?
  public static boolean StructPropertyMap(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap")) return false;
    if (!nextTokenIs(b, "<struct property map>", COMPLETION_DUMMY, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRUCT_PROPERTY_MAP, "<struct property map>");
    r = StructPropertyMap_0(b, l + 1);
    r = r && StructPropertyMap_1(b, l + 1);
    r = r && StructPropertyMap_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // StructProperty | IncompleteStructProperty
  private static boolean StructPropertyMap_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_0")) return false;
    boolean r;
    r = StructProperty(b, l + 1);
    if (!r) r = IncompleteStructProperty(b, l + 1);
    return r;
  }

  // (COMMA (IncompleteStructProperty? StructProperty IncompleteStructProperty? | IncompleteStructProperty))*
  private static boolean StructPropertyMap_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StructPropertyMap_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StructPropertyMap_1", c)) break;
    }
    return true;
  }

  // COMMA (IncompleteStructProperty? StructProperty IncompleteStructProperty? | IncompleteStructProperty)
  private static boolean StructPropertyMap_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && StructPropertyMap_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IncompleteStructProperty? StructProperty IncompleteStructProperty? | IncompleteStructProperty
  private static boolean StructPropertyMap_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructPropertyMap_1_0_1_0(b, l + 1);
    if (!r) r = IncompleteStructProperty(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IncompleteStructProperty? StructProperty IncompleteStructProperty?
  private static boolean StructPropertyMap_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StructPropertyMap_1_0_1_0_0(b, l + 1);
    r = r && StructProperty(b, l + 1);
    r = r && StructPropertyMap_1_0_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IncompleteStructProperty?
  private static boolean StructPropertyMap_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_1_0_1_0_0")) return false;
    IncompleteStructProperty(b, l + 1);
    return true;
  }

  // IncompleteStructProperty?
  private static boolean StructPropertyMap_1_0_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_1_0_1_0_2")) return false;
    IncompleteStructProperty(b, l + 1);
    return true;
  }

  // COMMA?
  private static boolean StructPropertyMap_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyMap_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // Struct | STRING_LITERAL
  static boolean StructPropertyValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructPropertyValue")) return false;
    boolean r;
    r = Struct(b, l + 1);
    if (!r) r = STRING_LITERAL(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // STRING_LITERAL (COMMA STRING_LITERAL)* COMMA?
  public static boolean StructStringArray(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructStringArray")) return false;
    if (!nextTokenIs(b, "<struct string array>", LINE_STRING, STRING_LITERAL_SINGLE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRUCT_STRING_ARRAY, "<struct string array>");
    r = STRING_LITERAL(b, l + 1);
    r = r && StructStringArray_1(b, l + 1);
    r = r && StructStringArray_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA STRING_LITERAL)*
  private static boolean StructStringArray_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructStringArray_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StructStringArray_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StructStringArray_1", c)) break;
    }
    return true;
  }

  // COMMA STRING_LITERAL
  private static boolean StructStringArray_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructStringArray_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && STRING_LITERAL(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean StructStringArray_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructStringArray_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

}
