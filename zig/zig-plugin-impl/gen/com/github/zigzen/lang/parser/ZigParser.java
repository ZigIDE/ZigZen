// This is a generated file. Not intended for manual editing.
package com.github.zigzen.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.zigzen.psi.ZigTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ZigParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
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

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ADDITION_EXPR, ASM_EXPR, ASSIGN_EXPR, BITWISE_EXPR,
      BIT_SHIFT_EXPR, BLOCK_EXPR, BOOL_AND_EXPR, BOOL_OR_EXPR,
      COMPARE_EXPR, CURLY_SUFFIX_EXPR, ERROR_UNION_EXPR, EXPR,
      FOR_EXPR, FOR_TYPE_EXPR, GROUPED_EXPR, IF_EXPR,
      IF_TYPE_EXPR, LABELED_TYPE_EXPR, LOOP_EXPR, LOOP_TYPE_EXPR,
      MULTIPLY_EXPR, PREFIX_EXPR, PRIMARY_EXPR, PRIMARY_TYPE_EXPR,
      SUFFIX_EXPR, SWITCH_EXPR, TYPE_EXPR, WHILE_CONTINUE_EXPR,
      WHILE_EXPR, WHILE_TYPE_EXPR),
  };

  /* ********************************************************** */
  // MultiplyExpr (AdditionOp MultiplyExpr)*
  public static boolean AdditionExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditionExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ADDITION_EXPR, "<addition expr>");
    r = MultiplyExpr(b, l + 1);
    r = r && AdditionExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (AdditionOp MultiplyExpr)*
  private static boolean AdditionExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditionExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!AdditionExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AdditionExpr_1", c)) break;
    }
    return true;
  }

  // AdditionOp MultiplyExpr
  private static boolean AdditionExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditionExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AdditionOp(b, l + 1);
    r = r && MultiplyExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PLUS
  //   | MINUS
  //   | PLUS2
  //   | PLUSPERCENT
  //   | MINUSPERCENT
  //   | PLUSPIPE
  //   | MINUSPIPE
  public static boolean AdditionOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdditionOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADDITION_OP, "<addition op>");
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS2);
    if (!r) r = consumeToken(b, PLUSPERCENT);
    if (!r) r = consumeToken(b, MINUSPERCENT);
    if (!r) r = consumeToken(b, PLUSPIPE);
    if (!r) r = consumeToken(b, MINUSPIPE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_ADDRSPACE LPAREN Expr RPAREN
  public static boolean AddrSpace(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddrSpace")) return false;
    if (!nextTokenIs(b, KEYWORD_ADDRSPACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD_ADDRSPACE, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ADDR_SPACE, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET Expr (COLON Expr)? RBRACKET
  public static boolean ArrayTypeStart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayTypeStart")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Expr(b, l + 1);
    r = r && ArrayTypeStart_2(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, ARRAY_TYPE_START, r);
    return r;
  }

  // (COLON Expr)?
  private static boolean ArrayTypeStart_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayTypeStart_2")) return false;
    ArrayTypeStart_2_0(b, l + 1);
    return true;
  }

  // COLON Expr
  private static boolean ArrayTypeStart_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayTypeStart_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COLON StringList
  public static boolean AsmClobbers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmClobbers")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && StringList(b, l + 1);
    exit_section_(b, m, ASM_CLOBBERS, r);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_ASM KEYWORD_VOLATILE? LPAREN Expr AsmOutput? RPAREN
  public static boolean AsmExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmExpr")) return false;
    if (!nextTokenIs(b, KEYWORD_ASM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ASM);
    r = r && AsmExpr_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && AsmExpr_4(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ASM_EXPR, r);
    return r;
  }

  // KEYWORD_VOLATILE?
  private static boolean AsmExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmExpr_1")) return false;
    consumeToken(b, KEYWORD_VOLATILE);
    return true;
  }

  // AsmOutput?
  private static boolean AsmExpr_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmExpr_4")) return false;
    AsmOutput(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COLON AsmInputList AsmClobbers?
  public static boolean AsmInput(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmInput")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && AsmInputList(b, l + 1);
    r = r && AsmInput_2(b, l + 1);
    exit_section_(b, m, ASM_INPUT, r);
    return r;
  }

  // AsmClobbers?
  private static boolean AsmInput_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmInput_2")) return false;
    AsmClobbers(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACKET IDENTIFIER RBRACKET StringLiteral LPAREN Expr RPAREN
  public static boolean AsmInputItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmInputItem")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, IDENTIFIER, RBRACKET);
    r = r && StringLiteral(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ASM_INPUT_ITEM, r);
    return r;
  }

  /* ********************************************************** */
  // (AsmInputItem COMMA)* AsmInputItem?
  public static boolean AsmInputList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmInputList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASM_INPUT_LIST, "<asm input list>");
    r = AsmInputList_0(b, l + 1);
    r = r && AsmInputList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (AsmInputItem COMMA)*
  private static boolean AsmInputList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmInputList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!AsmInputList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AsmInputList_0", c)) break;
    }
    return true;
  }

  // AsmInputItem COMMA
  private static boolean AsmInputList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmInputList_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AsmInputItem(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // AsmInputItem?
  private static boolean AsmInputList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmInputList_1")) return false;
    AsmInputItem(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COLON AsmOutputList AsmInput?
  public static boolean AsmOutput(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmOutput")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && AsmOutputList(b, l + 1);
    r = r && AsmOutput_2(b, l + 1);
    exit_section_(b, m, ASM_OUTPUT, r);
    return r;
  }

  // AsmInput?
  private static boolean AsmOutput_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmOutput_2")) return false;
    AsmInput(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACKET IDENTIFIER RBRACKET StringLiteral LPAREN (MINUSRARROW TypeExpr | IDENTIFIER) RPAREN
  public static boolean AsmOutputItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmOutputItem")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, IDENTIFIER, RBRACKET);
    r = r && StringLiteral(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && AsmOutputItem_5(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ASM_OUTPUT_ITEM, r);
    return r;
  }

  // MINUSRARROW TypeExpr | IDENTIFIER
  private static boolean AsmOutputItem_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmOutputItem_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AsmOutputItem_5_0(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // MINUSRARROW TypeExpr
  private static boolean AsmOutputItem_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmOutputItem_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MINUSRARROW);
    r = r && TypeExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (AsmOutputItem COMMA)* AsmOutputItem?
  public static boolean AsmOutputList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmOutputList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASM_OUTPUT_LIST, "<asm output list>");
    r = AsmOutputList_0(b, l + 1);
    r = r && AsmOutputList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (AsmOutputItem COMMA)*
  private static boolean AsmOutputList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmOutputList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!AsmOutputList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AsmOutputList_0", c)) break;
    }
    return true;
  }

  // AsmOutputItem COMMA
  private static boolean AsmOutputList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmOutputList_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AsmOutputItem(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // AsmOutputItem?
  private static boolean AsmOutputList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsmOutputList_1")) return false;
    AsmOutputItem(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Expr (AssignOp Expr)?
  public static boolean AssignExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ASSIGN_EXPR, "<assign expr>");
    r = Expr(b, l + 1);
    r = r && AssignExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (AssignOp Expr)?
  private static boolean AssignExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignExpr_1")) return false;
    AssignExpr_1_0(b, l + 1);
    return true;
  }

  // AssignOp Expr
  private static boolean AssignExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AssignOp(b, l + 1);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ASTERISKEQUAL
  //   | ASTERISKPIPEEQUAL
  //   | SLASHEQUAL
  //   | PERCENTEQUAL
  //   | PLUSEQUAL
  //   | PLUSPIPEEQUAL
  //   | MINUSEQUAL
  //   | MINUSPIPEEQUAL
  //   | LARROW2EQUAL
  //   | LARROW2PIPEEQUAL
  //   | RARROW2EQUAL
  //   | AMPERSANDEQUAL
  //   | CARETEQUAL
  //   | PIPEEQUAL
  //   | ASTERISKPERCENTEQUAL
  //   | PLUSPERCENTEQUAL
  //   | MINUSPERCENTEQUAL
  //   | EQUAL
  public static boolean AssignOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASSIGN_OP, "<assign op>");
    r = consumeToken(b, ASTERISKEQUAL);
    if (!r) r = consumeToken(b, ASTERISKPIPEEQUAL);
    if (!r) r = consumeToken(b, SLASHEQUAL);
    if (!r) r = consumeToken(b, PERCENTEQUAL);
    if (!r) r = consumeToken(b, PLUSEQUAL);
    if (!r) r = consumeToken(b, PLUSPIPEEQUAL);
    if (!r) r = consumeToken(b, MINUSEQUAL);
    if (!r) r = consumeToken(b, MINUSPIPEEQUAL);
    if (!r) r = consumeToken(b, LARROW2EQUAL);
    if (!r) r = consumeToken(b, LARROW2PIPEEQUAL);
    if (!r) r = consumeToken(b, RARROW2EQUAL);
    if (!r) r = consumeToken(b, AMPERSANDEQUAL);
    if (!r) r = consumeToken(b, CARETEQUAL);
    if (!r) r = consumeToken(b, PIPEEQUAL);
    if (!r) r = consumeToken(b, ASTERISKPERCENTEQUAL);
    if (!r) r = consumeToken(b, PLUSPERCENTEQUAL);
    if (!r) r = consumeToken(b, MINUSPERCENTEQUAL);
    if (!r) r = consumeToken(b, EQUAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AdditionExpr (BitShiftOp AdditionExpr)*
  public static boolean BitShiftExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitShiftExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, BIT_SHIFT_EXPR, "<bit shift expr>");
    r = AdditionExpr(b, l + 1);
    r = r && BitShiftExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (BitShiftOp AdditionExpr)*
  private static boolean BitShiftExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitShiftExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!BitShiftExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "BitShiftExpr_1", c)) break;
    }
    return true;
  }

  // BitShiftOp AdditionExpr
  private static boolean BitShiftExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitShiftExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = BitShiftOp(b, l + 1);
    r = r && AdditionExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LARROW2
  //   | RARROW2
  //   | LARROW2PIPE
  public static boolean BitShiftOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitShiftOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BIT_SHIFT_OP, "<bit shift op>");
    r = consumeToken(b, LARROW2);
    if (!r) r = consumeToken(b, RARROW2);
    if (!r) r = consumeToken(b, LARROW2PIPE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BitShiftExpr (BitwiseOp BitShiftExpr)*
  public static boolean BitwiseExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitwiseExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, BITWISE_EXPR, "<bitwise expr>");
    r = BitShiftExpr(b, l + 1);
    r = r && BitwiseExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (BitwiseOp BitShiftExpr)*
  private static boolean BitwiseExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitwiseExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!BitwiseExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "BitwiseExpr_1", c)) break;
    }
    return true;
  }

  // BitwiseOp BitShiftExpr
  private static boolean BitwiseExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitwiseExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = BitwiseOp(b, l + 1);
    r = r && BitShiftExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // AMPERSAND
  //   | CARET
  //   | PIPE
  //   | KEYWORD_ORELSE
  //   | KEYWORD_CATCH Payload?
  public static boolean BitwiseOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitwiseOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BITWISE_OP, "<bitwise op>");
    r = consumeToken(b, AMPERSAND);
    if (!r) r = consumeToken(b, CARET);
    if (!r) r = consumeToken(b, PIPE);
    if (!r) r = consumeToken(b, KEYWORD_ORELSE);
    if (!r) r = BitwiseOp_4(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD_CATCH Payload?
  private static boolean BitwiseOp_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitwiseOp_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_CATCH);
    r = r && BitwiseOp_4_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Payload?
  private static boolean BitwiseOp_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitwiseOp_4_1")) return false;
    Payload(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACE Statement* RBRACE
  public static boolean Block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Block")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && Block_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, BLOCK, r);
    return r;
  }

  // Statement*
  private static boolean Block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Statement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Block_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // BlockLabel? Block
  public static boolean BlockExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BlockExpr")) return false;
    if (!nextTokenIs(b, "<block expr>", IDENTIFIER, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BLOCK_EXPR, "<block expr>");
    r = BlockExpr_0(b, l + 1);
    r = r && Block(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BlockLabel?
  private static boolean BlockExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BlockExpr_0")) return false;
    BlockLabel(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // BlockExpr
  //   | AssignExpr SEMICOLON
  public static boolean BlockExprStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BlockExprStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BLOCK_EXPR_STATEMENT, "<block expr statement>");
    r = BlockExpr(b, l + 1);
    if (!r) r = BlockExprStatement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // AssignExpr SEMICOLON
  private static boolean BlockExprStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BlockExprStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AssignExpr(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER COLON
  public static boolean BlockLabel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BlockLabel")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    exit_section_(b, m, BLOCK_LABEL, r);
    return r;
  }

  /* ********************************************************** */
  // CompareExpr (KEYWORD_AND CompareExpr)*
  public static boolean BoolAndExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BoolAndExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, BOOL_AND_EXPR, "<bool and expr>");
    r = CompareExpr(b, l + 1);
    r = r && BoolAndExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (KEYWORD_AND CompareExpr)*
  private static boolean BoolAndExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BoolAndExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!BoolAndExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "BoolAndExpr_1", c)) break;
    }
    return true;
  }

  // KEYWORD_AND CompareExpr
  private static boolean BoolAndExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BoolAndExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_AND);
    r = r && CompareExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BoolAndExpr (KEYWORD_OR BoolAndExpr)*
  public static boolean BoolOrExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BoolOrExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, BOOL_OR_EXPR, "<bool or expr>");
    r = BoolAndExpr(b, l + 1);
    r = r && BoolOrExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (KEYWORD_OR BoolAndExpr)*
  private static boolean BoolOrExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BoolOrExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!BoolOrExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "BoolOrExpr_1", c)) break;
    }
    return true;
  }

  // KEYWORD_OR BoolAndExpr
  private static boolean BoolOrExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BoolOrExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_OR);
    r = r && BoolAndExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COLON IDENTIFIER
  public static boolean BreakLabel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BreakLabel")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COLON, IDENTIFIER);
    exit_section_(b, m, BREAK_LABEL, r);
    return r;
  }

  /* ********************************************************** */
  // BUILTINIDENTIFIER
  public static boolean BuiltinIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BuiltinIdentifier")) return false;
    if (!nextTokenIs(b, BUILTINIDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BUILTINIDENTIFIER);
    exit_section_(b, m, BUILTIN_IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_ALIGN LPAREN Expr RPAREN
  public static boolean ByteAlign(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ByteAlign")) return false;
    if (!nextTokenIs(b, KEYWORD_ALIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD_ALIGN, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, BYTE_ALIGN, r);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_CALLCONV LPAREN Expr RPAREN
  public static boolean CallConv(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallConv")) return false;
    if (!nextTokenIs(b, KEYWORD_CALLCONV)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD_CALLCONV, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, CALL_CONV, r);
    return r;
  }

  /* ********************************************************** */
  // BitwiseExpr (CompareOp BitwiseExpr)?
  public static boolean CompareExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompareExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, COMPARE_EXPR, "<compare expr>");
    r = BitwiseExpr(b, l + 1);
    r = r && CompareExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (CompareOp BitwiseExpr)?
  private static boolean CompareExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompareExpr_1")) return false;
    CompareExpr_1_0(b, l + 1);
    return true;
  }

  // CompareOp BitwiseExpr
  private static boolean CompareExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompareExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CompareOp(b, l + 1);
    r = r && BitwiseExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // EQUALEQUAL
  //   | EXCLAMATIONMARKEQUAL
  //   | LARROW
  //   | RARROW
  //   | LARROWEQUAL
  //   | RARROWEQUAL
  public static boolean CompareOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CompareOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPARE_OP, "<compare op>");
    r = consumeToken(b, EQUALEQUAL);
    if (!r) r = consumeToken(b, EXCLAMATIONMARKEQUAL);
    if (!r) r = consumeToken(b, LARROW);
    if (!r) r = consumeToken(b, RARROW);
    if (!r) r = consumeToken(b, LARROWEQUAL);
    if (!r) r = consumeToken(b, RARROWEQUAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_COMPTIME Block
  public static boolean ComptimeDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ComptimeDecl")) return false;
    if (!nextTokenIs(b, KEYWORD_COMPTIME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_COMPTIME);
    r = r && Block(b, l + 1);
    exit_section_(b, m, COMPTIME_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // (KEYWORD_EXTERN | KEYWORD_PACKED)? ContainerDeclAuto
  public static boolean ContainerDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDecl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTAINER_DECL, "<container decl>");
    r = ContainerDecl_0(b, l + 1);
    r = r && ContainerDeclAuto(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (KEYWORD_EXTERN | KEYWORD_PACKED)?
  private static boolean ContainerDecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDecl_0")) return false;
    ContainerDecl_0_0(b, l + 1);
    return true;
  }

  // KEYWORD_EXTERN | KEYWORD_PACKED
  private static boolean ContainerDecl_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDecl_0_0")) return false;
    boolean r;
    r = consumeToken(b, KEYWORD_EXTERN);
    if (!r) r = consumeToken(b, KEYWORD_PACKED);
    return r;
  }

  /* ********************************************************** */
  // ContainerDeclType LBRACE CONTAINER_DOC_COMMENT? ContainerMembers RBRACE
  public static boolean ContainerDeclAuto(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclAuto")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTAINER_DECL_AUTO, "<container decl auto>");
    r = ContainerDeclType(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && ContainerDeclAuto_2(b, l + 1);
    r = r && ContainerMembers(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // CONTAINER_DOC_COMMENT?
  private static boolean ContainerDeclAuto_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclAuto_2")) return false;
    consumeToken(b, CONTAINER_DOC_COMMENT);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_STRUCT (LPAREN Expr RPAREN)?
  //   | KEYWORD_OPAQUE
  //   | KEYWORD_ENUM (LPAREN Expr RPAREN)?
  //   | KEYWORD_UNION (LPAREN (KEYWORD_ENUM (LPAREN Expr RPAREN)? | Expr) RPAREN)?
  public static boolean ContainerDeclType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTAINER_DECL_TYPE, "<container decl type>");
    r = ContainerDeclType_0(b, l + 1);
    if (!r) r = consumeToken(b, KEYWORD_OPAQUE);
    if (!r) r = ContainerDeclType_2(b, l + 1);
    if (!r) r = ContainerDeclType_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD_STRUCT (LPAREN Expr RPAREN)?
  private static boolean ContainerDeclType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_STRUCT);
    r = r && ContainerDeclType_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LPAREN Expr RPAREN)?
  private static boolean ContainerDeclType_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_0_1")) return false;
    ContainerDeclType_0_1_0(b, l + 1);
    return true;
  }

  // LPAREN Expr RPAREN
  private static boolean ContainerDeclType_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_ENUM (LPAREN Expr RPAREN)?
  private static boolean ContainerDeclType_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ENUM);
    r = r && ContainerDeclType_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LPAREN Expr RPAREN)?
  private static boolean ContainerDeclType_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_2_1")) return false;
    ContainerDeclType_2_1_0(b, l + 1);
    return true;
  }

  // LPAREN Expr RPAREN
  private static boolean ContainerDeclType_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_2_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_UNION (LPAREN (KEYWORD_ENUM (LPAREN Expr RPAREN)? | Expr) RPAREN)?
  private static boolean ContainerDeclType_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_UNION);
    r = r && ContainerDeclType_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LPAREN (KEYWORD_ENUM (LPAREN Expr RPAREN)? | Expr) RPAREN)?
  private static boolean ContainerDeclType_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_3_1")) return false;
    ContainerDeclType_3_1_0(b, l + 1);
    return true;
  }

  // LPAREN (KEYWORD_ENUM (LPAREN Expr RPAREN)? | Expr) RPAREN
  private static boolean ContainerDeclType_3_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_3_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && ContainerDeclType_3_1_0_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_ENUM (LPAREN Expr RPAREN)? | Expr
  private static boolean ContainerDeclType_3_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_3_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ContainerDeclType_3_1_0_1_0(b, l + 1);
    if (!r) r = Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_ENUM (LPAREN Expr RPAREN)?
  private static boolean ContainerDeclType_3_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_3_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ENUM);
    r = r && ContainerDeclType_3_1_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LPAREN Expr RPAREN)?
  private static boolean ContainerDeclType_3_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_3_1_0_1_0_1")) return false;
    ContainerDeclType_3_1_0_1_0_1_0(b, l + 1);
    return true;
  }

  // LPAREN Expr RPAREN
  private static boolean ContainerDeclType_3_1_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclType_3_1_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TestDecl
  //   | ComptimeDecl
  //   | DOC_COMMENT? KEYWORD_PUB? Decl
  public static boolean ContainerDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTAINER_DECLARATION, "<container declaration>");
    r = TestDecl(b, l + 1);
    if (!r) r = ComptimeDecl(b, l + 1);
    if (!r) r = ContainerDeclaration_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DOC_COMMENT? KEYWORD_PUB? Decl
  private static boolean ContainerDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclaration_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ContainerDeclaration_2_0(b, l + 1);
    r = r && ContainerDeclaration_2_1(b, l + 1);
    r = r && Decl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOC_COMMENT?
  private static boolean ContainerDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclaration_2_0")) return false;
    consumeToken(b, DOC_COMMENT);
    return true;
  }

  // KEYWORD_PUB?
  private static boolean ContainerDeclaration_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerDeclaration_2_1")) return false;
    consumeToken(b, KEYWORD_PUB);
    return true;
  }

  /* ********************************************************** */
  // DOC_COMMENT? KEYWORD_COMPTIME? IDENTIFIER (COLON (KEYWORD_ANYTYPE | TypeExpr) ByteAlign?)? (EQUAL Expr)?
  //   | DOC_COMMENT? KEYWORD_COMPTIME? (IDENTIFIER COLON)? !KEYWORD_FN TypeExpr ByteAlign? (EQUAL Expr)?
  public static boolean ContainerField(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTAINER_FIELD, "<container field>");
    r = ContainerField_0(b, l + 1);
    if (!r) r = ContainerField_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DOC_COMMENT? KEYWORD_COMPTIME? IDENTIFIER (COLON (KEYWORD_ANYTYPE | TypeExpr) ByteAlign?)? (EQUAL Expr)?
  private static boolean ContainerField_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ContainerField_0_0(b, l + 1);
    r = r && ContainerField_0_1(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && ContainerField_0_3(b, l + 1);
    r = r && ContainerField_0_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOC_COMMENT?
  private static boolean ContainerField_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_0_0")) return false;
    consumeToken(b, DOC_COMMENT);
    return true;
  }

  // KEYWORD_COMPTIME?
  private static boolean ContainerField_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_0_1")) return false;
    consumeToken(b, KEYWORD_COMPTIME);
    return true;
  }

  // (COLON (KEYWORD_ANYTYPE | TypeExpr) ByteAlign?)?
  private static boolean ContainerField_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_0_3")) return false;
    ContainerField_0_3_0(b, l + 1);
    return true;
  }

  // COLON (KEYWORD_ANYTYPE | TypeExpr) ByteAlign?
  private static boolean ContainerField_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && ContainerField_0_3_0_1(b, l + 1);
    r = r && ContainerField_0_3_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_ANYTYPE | TypeExpr
  private static boolean ContainerField_0_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_0_3_0_1")) return false;
    boolean r;
    r = consumeToken(b, KEYWORD_ANYTYPE);
    if (!r) r = TypeExpr(b, l + 1);
    return r;
  }

  // ByteAlign?
  private static boolean ContainerField_0_3_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_0_3_0_2")) return false;
    ByteAlign(b, l + 1);
    return true;
  }

  // (EQUAL Expr)?
  private static boolean ContainerField_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_0_4")) return false;
    ContainerField_0_4_0(b, l + 1);
    return true;
  }

  // EQUAL Expr
  private static boolean ContainerField_0_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_0_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUAL);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOC_COMMENT? KEYWORD_COMPTIME? (IDENTIFIER COLON)? !KEYWORD_FN TypeExpr ByteAlign? (EQUAL Expr)?
  private static boolean ContainerField_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ContainerField_1_0(b, l + 1);
    r = r && ContainerField_1_1(b, l + 1);
    r = r && ContainerField_1_2(b, l + 1);
    r = r && ContainerField_1_3(b, l + 1);
    r = r && TypeExpr(b, l + 1);
    r = r && ContainerField_1_5(b, l + 1);
    r = r && ContainerField_1_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOC_COMMENT?
  private static boolean ContainerField_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_1_0")) return false;
    consumeToken(b, DOC_COMMENT);
    return true;
  }

  // KEYWORD_COMPTIME?
  private static boolean ContainerField_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_1_1")) return false;
    consumeToken(b, KEYWORD_COMPTIME);
    return true;
  }

  // (IDENTIFIER COLON)?
  private static boolean ContainerField_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_1_2")) return false;
    ContainerField_1_2_0(b, l + 1);
    return true;
  }

  // IDENTIFIER COLON
  private static boolean ContainerField_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // !KEYWORD_FN
  private static boolean ContainerField_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_1_3")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, KEYWORD_FN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ByteAlign?
  private static boolean ContainerField_1_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_1_5")) return false;
    ByteAlign(b, l + 1);
    return true;
  }

  // (EQUAL Expr)?
  private static boolean ContainerField_1_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_1_6")) return false;
    ContainerField_1_6_0(b, l + 1);
    return true;
  }

  // EQUAL Expr
  private static boolean ContainerField_1_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerField_1_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUAL);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ContainerDeclaration* (ContainerField COMMA)* (ContainerField | ContainerDeclaration*)?
  static boolean ContainerMembers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerMembers")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ContainerMembers_0(b, l + 1);
    r = r && ContainerMembers_1(b, l + 1);
    r = r && ContainerMembers_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ContainerDeclaration*
  private static boolean ContainerMembers_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerMembers_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ContainerDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ContainerMembers_0", c)) break;
    }
    return true;
  }

  // (ContainerField COMMA)*
  private static boolean ContainerMembers_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerMembers_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ContainerMembers_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ContainerMembers_1", c)) break;
    }
    return true;
  }

  // ContainerField COMMA
  private static boolean ContainerMembers_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerMembers_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ContainerField(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ContainerField | ContainerDeclaration*)?
  private static boolean ContainerMembers_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerMembers_2")) return false;
    ContainerMembers_2_0(b, l + 1);
    return true;
  }

  // ContainerField | ContainerDeclaration*
  private static boolean ContainerMembers_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerMembers_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ContainerField(b, l + 1);
    if (!r) r = ContainerMembers_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ContainerDeclaration*
  private static boolean ContainerMembers_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ContainerMembers_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ContainerDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ContainerMembers_2_0_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // TypeExpr InitList?
  public static boolean CurlySuffixExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CurlySuffixExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, CURLY_SUFFIX_EXPR, "<curly suffix expr>");
    r = TypeExpr(b, l + 1);
    r = r && CurlySuffixExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // InitList?
  private static boolean CurlySuffixExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CurlySuffixExpr_1")) return false;
    InitList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (KEYWORD_EXPORT | KEYWORD_EXTERN STRING_LITERAL_SINGLE? | (KEYWORD_INLINE | KEYWORD_NOILINE))? FnProto (SEMICOLON | Block)
  //   | (KEYWORD_EXPORT | KEYWORD_EXTERN STRING_LITERAL_SINGLE?)? KEYWORD_THREADLOCAL? VarDecl
  //   | KEYWORD_USINGNAMESPACE Expr SEMICOLON
  public static boolean Decl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DECL, "<decl>");
    r = Decl_0(b, l + 1);
    if (!r) r = Decl_1(b, l + 1);
    if (!r) r = Decl_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (KEYWORD_EXPORT | KEYWORD_EXTERN STRING_LITERAL_SINGLE? | (KEYWORD_INLINE | KEYWORD_NOILINE))? FnProto (SEMICOLON | Block)
  private static boolean Decl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Decl_0_0(b, l + 1);
    r = r && FnProto(b, l + 1);
    r = r && Decl_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (KEYWORD_EXPORT | KEYWORD_EXTERN STRING_LITERAL_SINGLE? | (KEYWORD_INLINE | KEYWORD_NOILINE))?
  private static boolean Decl_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_0_0")) return false;
    Decl_0_0_0(b, l + 1);
    return true;
  }

  // KEYWORD_EXPORT | KEYWORD_EXTERN STRING_LITERAL_SINGLE? | (KEYWORD_INLINE | KEYWORD_NOILINE)
  private static boolean Decl_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_EXPORT);
    if (!r) r = Decl_0_0_0_1(b, l + 1);
    if (!r) r = Decl_0_0_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_EXTERN STRING_LITERAL_SINGLE?
  private static boolean Decl_0_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_0_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_EXTERN);
    r = r && Decl_0_0_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // STRING_LITERAL_SINGLE?
  private static boolean Decl_0_0_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_0_0_0_1_1")) return false;
    consumeToken(b, STRING_LITERAL_SINGLE);
    return true;
  }

  // KEYWORD_INLINE | KEYWORD_NOILINE
  private static boolean Decl_0_0_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_0_0_0_2")) return false;
    boolean r;
    r = consumeToken(b, KEYWORD_INLINE);
    if (!r) r = consumeToken(b, KEYWORD_NOILINE);
    return r;
  }

  // SEMICOLON | Block
  private static boolean Decl_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_0_2")) return false;
    boolean r;
    r = consumeToken(b, SEMICOLON);
    if (!r) r = Block(b, l + 1);
    return r;
  }

  // (KEYWORD_EXPORT | KEYWORD_EXTERN STRING_LITERAL_SINGLE?)? KEYWORD_THREADLOCAL? VarDecl
  private static boolean Decl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Decl_1_0(b, l + 1);
    r = r && Decl_1_1(b, l + 1);
    r = r && VarDecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (KEYWORD_EXPORT | KEYWORD_EXTERN STRING_LITERAL_SINGLE?)?
  private static boolean Decl_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_1_0")) return false;
    Decl_1_0_0(b, l + 1);
    return true;
  }

  // KEYWORD_EXPORT | KEYWORD_EXTERN STRING_LITERAL_SINGLE?
  private static boolean Decl_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_EXPORT);
    if (!r) r = Decl_1_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_EXTERN STRING_LITERAL_SINGLE?
  private static boolean Decl_1_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_1_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_EXTERN);
    r = r && Decl_1_0_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // STRING_LITERAL_SINGLE?
  private static boolean Decl_1_0_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_1_0_0_1_1")) return false;
    consumeToken(b, STRING_LITERAL_SINGLE);
    return true;
  }

  // KEYWORD_THREADLOCAL?
  private static boolean Decl_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_1_1")) return false;
    consumeToken(b, KEYWORD_THREADLOCAL);
    return true;
  }

  // KEYWORD_USINGNAMESPACE Expr SEMICOLON
  private static boolean Decl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Decl_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_USINGNAMESPACE);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_ERROR LBRACE IdentifierList RBRACE
  public static boolean ErrorSetDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ErrorSetDecl")) return false;
    if (!nextTokenIs(b, KEYWORD_ERROR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD_ERROR, LBRACE);
    r = r && IdentifierList(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, ERROR_SET_DECL, r);
    return r;
  }

  /* ********************************************************** */
  // SuffixExpr (EXCLAMATIONMARK TypeExpr)?
  public static boolean ErrorUnionExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ErrorUnionExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ERROR_UNION_EXPR, "<error union expr>");
    r = SuffixExpr(b, l + 1);
    r = r && ErrorUnionExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (EXCLAMATIONMARK TypeExpr)?
  private static boolean ErrorUnionExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ErrorUnionExpr_1")) return false;
    ErrorUnionExpr_1_0(b, l + 1);
    return true;
  }

  // EXCLAMATIONMARK TypeExpr
  private static boolean ErrorUnionExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ErrorUnionExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXCLAMATIONMARK);
    r = r && TypeExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BoolOrExpr
  public static boolean Expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, EXPR, "<expr>");
    r = BoolOrExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (Expr COMMA)* Expr?
  public static boolean ExprList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExprList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR_LIST, "<expr list>");
    r = ExprList_0(b, l + 1);
    r = r && ExprList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (Expr COMMA)*
  private static boolean ExprList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExprList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ExprList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ExprList_0", c)) break;
    }
    return true;
  }

  // Expr COMMA
  private static boolean ExprList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExprList_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr?
  private static boolean ExprList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExprList_1")) return false;
    Expr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DOT IDENTIFIER EQUAL Expr
  public static boolean FieldInit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FieldInit")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, IDENTIFIER, EQUAL);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, FIELD_INIT, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN ExprList RPAREN
  public static boolean FnCallArguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FnCallArguments")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && ExprList(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, FN_CALL_ARGUMENTS, r);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_FN IDENTIFIER? LPAREN ParamDeclList RPAREN ByteAlign? AddrSpace? LinkSection? CallConv? EXCLAMATIONMARK? TypeExpr
  public static boolean FnProto(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FnProto")) return false;
    if (!nextTokenIs(b, KEYWORD_FN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_FN);
    r = r && FnProto_1(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && ParamDeclList(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && FnProto_5(b, l + 1);
    r = r && FnProto_6(b, l + 1);
    r = r && FnProto_7(b, l + 1);
    r = r && FnProto_8(b, l + 1);
    r = r && FnProto_9(b, l + 1);
    r = r && TypeExpr(b, l + 1);
    exit_section_(b, m, FN_PROTO, r);
    return r;
  }

  // IDENTIFIER?
  private static boolean FnProto_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FnProto_1")) return false;
    consumeToken(b, IDENTIFIER);
    return true;
  }

  // ByteAlign?
  private static boolean FnProto_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FnProto_5")) return false;
    ByteAlign(b, l + 1);
    return true;
  }

  // AddrSpace?
  private static boolean FnProto_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FnProto_6")) return false;
    AddrSpace(b, l + 1);
    return true;
  }

  // LinkSection?
  private static boolean FnProto_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FnProto_7")) return false;
    LinkSection(b, l + 1);
    return true;
  }

  // CallConv?
  private static boolean FnProto_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FnProto_8")) return false;
    CallConv(b, l + 1);
    return true;
  }

  // EXCLAMATIONMARK?
  private static boolean FnProto_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FnProto_9")) return false;
    consumeToken(b, EXCLAMATIONMARK);
    return true;
  }

  /* ********************************************************** */
  // ForPrefix Expr (KEYWORD_ELSE Expr)?
  public static boolean ForExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForExpr")) return false;
    if (!nextTokenIs(b, KEYWORD_FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForPrefix(b, l + 1);
    r = r && Expr(b, l + 1);
    r = r && ForExpr_2(b, l + 1);
    exit_section_(b, m, FOR_EXPR, r);
    return r;
  }

  // (KEYWORD_ELSE Expr)?
  private static boolean ForExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForExpr_2")) return false;
    ForExpr_2_0(b, l + 1);
    return true;
  }

  // KEYWORD_ELSE Expr
  private static boolean ForExpr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForExpr_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ForRange | Expr
  public static boolean ForOperand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForOperand")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_OPERAND, "<for operand>");
    r = ForRange(b, l + 1);
    if (!r) r = Expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_FOR LPAREN (ForOperand COMMA)* ForOperand RPAREN PtrIndexPayload
  public static boolean ForPrefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForPrefix")) return false;
    if (!nextTokenIs(b, KEYWORD_FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD_FOR, LPAREN);
    r = r && ForPrefix_2(b, l + 1);
    r = r && ForOperand(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && PtrIndexPayload(b, l + 1);
    exit_section_(b, m, FOR_PREFIX, r);
    return r;
  }

  // (ForOperand COMMA)*
  private static boolean ForPrefix_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForPrefix_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ForPrefix_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ForPrefix_2", c)) break;
    }
    return true;
  }

  // ForOperand COMMA
  private static boolean ForPrefix_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForPrefix_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForOperand(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expr DOT2 Expr?
  public static boolean ForRange(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForRange")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_RANGE, "<for range>");
    r = Expr(b, l + 1);
    r = r && consumeToken(b, DOT2);
    r = r && ForRange_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Expr?
  private static boolean ForRange_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForRange_2")) return false;
    Expr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ForPrefix BlockExpr ( KEYWORD_ELSE Statement )?
  //   | ForPrefix AssignExpr ( SEMICOLON | KEYWORD_ELSE Statement )
  public static boolean ForStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement")) return false;
    if (!nextTokenIs(b, KEYWORD_FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForStatement_0(b, l + 1);
    if (!r) r = ForStatement_1(b, l + 1);
    exit_section_(b, m, FOR_STATEMENT, r);
    return r;
  }

  // ForPrefix BlockExpr ( KEYWORD_ELSE Statement )?
  private static boolean ForStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForPrefix(b, l + 1);
    r = r && BlockExpr(b, l + 1);
    r = r && ForStatement_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( KEYWORD_ELSE Statement )?
  private static boolean ForStatement_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_0_2")) return false;
    ForStatement_0_2_0(b, l + 1);
    return true;
  }

  // KEYWORD_ELSE Statement
  private static boolean ForStatement_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ForPrefix AssignExpr ( SEMICOLON | KEYWORD_ELSE Statement )
  private static boolean ForStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForPrefix(b, l + 1);
    r = r && AssignExpr(b, l + 1);
    r = r && ForStatement_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEMICOLON | KEYWORD_ELSE Statement
  private static boolean ForStatement_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    if (!r) r = ForStatement_1_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_ELSE Statement
  private static boolean ForStatement_1_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_1_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ForPrefix TypeExpr (KEYWORD_ELSE TypeExpr)?
  public static boolean ForTypeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForTypeExpr")) return false;
    if (!nextTokenIs(b, KEYWORD_FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ForPrefix(b, l + 1);
    r = r && TypeExpr(b, l + 1);
    r = r && ForTypeExpr_2(b, l + 1);
    exit_section_(b, m, FOR_TYPE_EXPR, r);
    return r;
  }

  // (KEYWORD_ELSE TypeExpr)?
  private static boolean ForTypeExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForTypeExpr_2")) return false;
    ForTypeExpr_2_0(b, l + 1);
    return true;
  }

  // KEYWORD_ELSE TypeExpr
  private static boolean ForTypeExpr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForTypeExpr_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && TypeExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LPAREN Expr RPAREN
  public static boolean GroupedExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GroupedExpr")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, GROUPED_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // (DOC_COMMENT? IDENTIFIER COMMA)* (DOC_COMMENT? IDENTIFIER)?
  public static boolean IdentifierList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifierList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IDENTIFIER_LIST, "<identifier list>");
    r = IdentifierList_0(b, l + 1);
    r = r && IdentifierList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (DOC_COMMENT? IDENTIFIER COMMA)*
  private static boolean IdentifierList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifierList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!IdentifierList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IdentifierList_0", c)) break;
    }
    return true;
  }

  // DOC_COMMENT? IDENTIFIER COMMA
  private static boolean IdentifierList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifierList_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IdentifierList_0_0_0(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOC_COMMENT?
  private static boolean IdentifierList_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifierList_0_0_0")) return false;
    consumeToken(b, DOC_COMMENT);
    return true;
  }

  // (DOC_COMMENT? IDENTIFIER)?
  private static boolean IdentifierList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifierList_1")) return false;
    IdentifierList_1_0(b, l + 1);
    return true;
  }

  // DOC_COMMENT? IDENTIFIER
  private static boolean IdentifierList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifierList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IdentifierList_1_0_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOC_COMMENT?
  private static boolean IdentifierList_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IdentifierList_1_0_0")) return false;
    consumeToken(b, DOC_COMMENT);
    return true;
  }

  /* ********************************************************** */
  // IfPrefix Expr (KEYWORD_ELSE Payload? Expr)?
  public static boolean IfExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfExpr")) return false;
    if (!nextTokenIs(b, KEYWORD_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IfPrefix(b, l + 1);
    r = r && Expr(b, l + 1);
    r = r && IfExpr_2(b, l + 1);
    exit_section_(b, m, IF_EXPR, r);
    return r;
  }

  // (KEYWORD_ELSE Payload? Expr)?
  private static boolean IfExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfExpr_2")) return false;
    IfExpr_2_0(b, l + 1);
    return true;
  }

  // KEYWORD_ELSE Payload? Expr
  private static boolean IfExpr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfExpr_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && IfExpr_2_0_1(b, l + 1);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Payload?
  private static boolean IfExpr_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfExpr_2_0_1")) return false;
    Payload(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_IF LPAREN Expr RPAREN PtrPayload?
  public static boolean IfPrefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfPrefix")) return false;
    if (!nextTokenIs(b, KEYWORD_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD_IF, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && IfPrefix_4(b, l + 1);
    exit_section_(b, m, IF_PREFIX, r);
    return r;
  }

  // PtrPayload?
  private static boolean IfPrefix_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfPrefix_4")) return false;
    PtrPayload(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IfPrefix BlockExpr ( KEYWORD_ELSE Payload? Statement )?
  //   | IfPrefix AssignExpr ( SEMICOLON | KEYWORD_ELSE Payload? Statement )
  public static boolean IfStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement")) return false;
    if (!nextTokenIs(b, KEYWORD_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IfStatement_0(b, l + 1);
    if (!r) r = IfStatement_1(b, l + 1);
    exit_section_(b, m, IF_STATEMENT, r);
    return r;
  }

  // IfPrefix BlockExpr ( KEYWORD_ELSE Payload? Statement )?
  private static boolean IfStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IfPrefix(b, l + 1);
    r = r && BlockExpr(b, l + 1);
    r = r && IfStatement_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( KEYWORD_ELSE Payload? Statement )?
  private static boolean IfStatement_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_0_2")) return false;
    IfStatement_0_2_0(b, l + 1);
    return true;
  }

  // KEYWORD_ELSE Payload? Statement
  private static boolean IfStatement_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && IfStatement_0_2_0_1(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Payload?
  private static boolean IfStatement_0_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_0_2_0_1")) return false;
    Payload(b, l + 1);
    return true;
  }

  // IfPrefix AssignExpr ( SEMICOLON | KEYWORD_ELSE Payload? Statement )
  private static boolean IfStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IfPrefix(b, l + 1);
    r = r && AssignExpr(b, l + 1);
    r = r && IfStatement_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEMICOLON | KEYWORD_ELSE Payload? Statement
  private static boolean IfStatement_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    if (!r) r = IfStatement_1_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_ELSE Payload? Statement
  private static boolean IfStatement_1_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_1_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && IfStatement_1_2_1_1(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Payload?
  private static boolean IfStatement_1_2_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_1_2_1_1")) return false;
    Payload(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IfPrefix TypeExpr (KEYWORD_ELSE Payload? TypeExpr)?
  public static boolean IfTypeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfTypeExpr")) return false;
    if (!nextTokenIs(b, KEYWORD_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IfPrefix(b, l + 1);
    r = r && TypeExpr(b, l + 1);
    r = r && IfTypeExpr_2(b, l + 1);
    exit_section_(b, m, IF_TYPE_EXPR, r);
    return r;
  }

  // (KEYWORD_ELSE Payload? TypeExpr)?
  private static boolean IfTypeExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfTypeExpr_2")) return false;
    IfTypeExpr_2_0(b, l + 1);
    return true;
  }

  // KEYWORD_ELSE Payload? TypeExpr
  private static boolean IfTypeExpr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfTypeExpr_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && IfTypeExpr_2_0_1(b, l + 1);
    r = r && TypeExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Payload?
  private static boolean IfTypeExpr_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfTypeExpr_2_0_1")) return false;
    Payload(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACE FieldInit (COMMA FieldInit)* COMMA? RBRACE
  //      | LBRACE Expr (COMMA Expr)* COMMA? RBRACE
  //      | LBRACE RBRACE
  public static boolean InitList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InitList")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InitList_0(b, l + 1);
    if (!r) r = InitList_1(b, l + 1);
    if (!r) r = parseTokens(b, 0, LBRACE, RBRACE);
    exit_section_(b, m, INIT_LIST, r);
    return r;
  }

  // LBRACE FieldInit (COMMA FieldInit)* COMMA? RBRACE
  private static boolean InitList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InitList_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && FieldInit(b, l + 1);
    r = r && InitList_0_2(b, l + 1);
    r = r && InitList_0_3(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA FieldInit)*
  private static boolean InitList_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InitList_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!InitList_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InitList_0_2", c)) break;
    }
    return true;
  }

  // COMMA FieldInit
  private static boolean InitList_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InitList_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && FieldInit(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean InitList_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InitList_0_3")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  // LBRACE Expr (COMMA Expr)* COMMA? RBRACE
  private static boolean InitList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InitList_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && Expr(b, l + 1);
    r = r && InitList_1_2(b, l + 1);
    r = r && InitList_1_3(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA Expr)*
  private static boolean InitList_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InitList_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!InitList_1_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InitList_1_2", c)) break;
    }
    return true;
  }

  // COMMA Expr
  private static boolean InitList_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InitList_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean InitList_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InitList_1_3")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // BlockLabel? (Block | LoopStatement)
  public static boolean LabeledStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabeledStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABELED_STATEMENT, "<labeled statement>");
    r = LabeledStatement_0(b, l + 1);
    r = r && LabeledStatement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BlockLabel?
  private static boolean LabeledStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabeledStatement_0")) return false;
    BlockLabel(b, l + 1);
    return true;
  }

  // Block | LoopStatement
  private static boolean LabeledStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabeledStatement_1")) return false;
    boolean r;
    r = Block(b, l + 1);
    if (!r) r = LoopStatement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // BlockLabel Block
  //   | BlockLabel? LoopTypeExpr
  public static boolean LabeledTypeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabeledTypeExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, LABELED_TYPE_EXPR, "<labeled type expr>");
    r = LabeledTypeExpr_0(b, l + 1);
    if (!r) r = LabeledTypeExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BlockLabel Block
  private static boolean LabeledTypeExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabeledTypeExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = BlockLabel(b, l + 1);
    r = r && Block(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BlockLabel? LoopTypeExpr
  private static boolean LabeledTypeExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabeledTypeExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LabeledTypeExpr_1_0(b, l + 1);
    r = r && LoopTypeExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BlockLabel?
  private static boolean LabeledTypeExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabeledTypeExpr_1_0")) return false;
    BlockLabel(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_LINKSECTION LPAREN Expr RPAREN
  public static boolean LinkSection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LinkSection")) return false;
    if (!nextTokenIs(b, KEYWORD_LINKSECTION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD_LINKSECTION, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, LINK_SECTION, r);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_INLINE? (ForExpr | WhileExpr)
  public static boolean LoopExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, LOOP_EXPR, "<loop expr>");
    r = LoopExpr_0(b, l + 1);
    r = r && LoopExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD_INLINE?
  private static boolean LoopExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopExpr_0")) return false;
    consumeToken(b, KEYWORD_INLINE);
    return true;
  }

  // ForExpr | WhileExpr
  private static boolean LoopExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopExpr_1")) return false;
    boolean r;
    r = ForExpr(b, l + 1);
    if (!r) r = WhileExpr(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_INLINE? (ForStatement | WhileStatement)
  public static boolean LoopStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LOOP_STATEMENT, "<loop statement>");
    r = LoopStatement_0(b, l + 1);
    r = r && LoopStatement_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD_INLINE?
  private static boolean LoopStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopStatement_0")) return false;
    consumeToken(b, KEYWORD_INLINE);
    return true;
  }

  // ForStatement | WhileStatement
  private static boolean LoopStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopStatement_1")) return false;
    boolean r;
    r = ForStatement(b, l + 1);
    if (!r) r = WhileStatement(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_INLINE? (ForTypeExpr | WhileTypeExpr)
  public static boolean LoopTypeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopTypeExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, LOOP_TYPE_EXPR, "<loop type expr>");
    r = LoopTypeExpr_0(b, l + 1);
    r = r && LoopTypeExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD_INLINE?
  private static boolean LoopTypeExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopTypeExpr_0")) return false;
    consumeToken(b, KEYWORD_INLINE);
    return true;
  }

  // ForTypeExpr | WhileTypeExpr
  private static boolean LoopTypeExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LoopTypeExpr_1")) return false;
    boolean r;
    r = ForTypeExpr(b, l + 1);
    if (!r) r = WhileTypeExpr(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // PrefixExpr (MultiplyOp PrefixExpr)*
  public static boolean MultiplyExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplyExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, MULTIPLY_EXPR, "<multiply expr>");
    r = PrefixExpr(b, l + 1);
    r = r && MultiplyExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (MultiplyOp PrefixExpr)*
  private static boolean MultiplyExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplyExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!MultiplyExpr_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MultiplyExpr_1", c)) break;
    }
    return true;
  }

  // MultiplyOp PrefixExpr
  private static boolean MultiplyExpr_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplyExpr_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MultiplyOp(b, l + 1);
    r = r && PrefixExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PIPE2
  //   | ASTERISK
  //   | SLASH
  //   | PERCENT
  //   | ASTERISK2
  //   | ASTERISKPERCENT
  //   | ASTERISKPIPE
  public static boolean MultiplyOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MultiplyOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MULTIPLY_OP, "<multiply op>");
    r = consumeToken(b, PIPE2);
    if (!r) r = consumeToken(b, ASTERISK);
    if (!r) r = consumeToken(b, SLASH);
    if (!r) r = consumeToken(b, PERCENT);
    if (!r) r = consumeToken(b, ASTERISK2);
    if (!r) r = consumeToken(b, ASTERISKPERCENT);
    if (!r) r = consumeToken(b, ASTERISKPIPE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DOC_COMMENT? (KEYWORD_NOALIAS | KEYWORD_COMPTIME)? (IDENTIFIER COLON)? ParamType
  //   | DOT3
  public static boolean ParamDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDecl")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAM_DECL, "<param decl>");
    r = ParamDecl_0(b, l + 1);
    if (!r) r = consumeToken(b, DOT3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DOC_COMMENT? (KEYWORD_NOALIAS | KEYWORD_COMPTIME)? (IDENTIFIER COLON)? ParamType
  private static boolean ParamDecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDecl_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ParamDecl_0_0(b, l + 1);
    r = r && ParamDecl_0_1(b, l + 1);
    r = r && ParamDecl_0_2(b, l + 1);
    r = r && ParamType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOC_COMMENT?
  private static boolean ParamDecl_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDecl_0_0")) return false;
    consumeToken(b, DOC_COMMENT);
    return true;
  }

  // (KEYWORD_NOALIAS | KEYWORD_COMPTIME)?
  private static boolean ParamDecl_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDecl_0_1")) return false;
    ParamDecl_0_1_0(b, l + 1);
    return true;
  }

  // KEYWORD_NOALIAS | KEYWORD_COMPTIME
  private static boolean ParamDecl_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDecl_0_1_0")) return false;
    boolean r;
    r = consumeToken(b, KEYWORD_NOALIAS);
    if (!r) r = consumeToken(b, KEYWORD_COMPTIME);
    return r;
  }

  // (IDENTIFIER COLON)?
  private static boolean ParamDecl_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDecl_0_2")) return false;
    ParamDecl_0_2_0(b, l + 1);
    return true;
  }

  // IDENTIFIER COLON
  private static boolean ParamDecl_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDecl_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (ParamDecl COMMA)* ParamDecl?
  public static boolean ParamDeclList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDeclList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAM_DECL_LIST, "<param decl list>");
    r = ParamDeclList_0(b, l + 1);
    r = r && ParamDeclList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (ParamDecl COMMA)*
  private static boolean ParamDeclList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDeclList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ParamDeclList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ParamDeclList_0", c)) break;
    }
    return true;
  }

  // ParamDecl COMMA
  private static boolean ParamDeclList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDeclList_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ParamDecl(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // ParamDecl?
  private static boolean ParamDeclList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamDeclList_1")) return false;
    ParamDecl(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_ANYTYPE
  //   | TypeExpr
  public static boolean ParamType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParamType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAM_TYPE, "<param type>");
    r = consumeToken(b, KEYWORD_ANYTYPE);
    if (!r) r = TypeExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PIPE IDENTIFIER PIPE
  public static boolean Payload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Payload")) return false;
    if (!nextTokenIs(b, PIPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PIPE, IDENTIFIER, PIPE);
    exit_section_(b, m, PAYLOAD, r);
    return r;
  }

  /* ********************************************************** */
  // PrefixOp* PrimaryExpr
  public static boolean PrefixExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, PREFIX_EXPR, "<prefix expr>");
    r = PrefixExpr_0(b, l + 1);
    r = r && PrimaryExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PrefixOp*
  private static boolean PrefixExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixExpr_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PrefixOp(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PrefixExpr_0", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // EXCLAMATIONMARK
  //   | MINUS
  //   | TILDE
  //   | MINUSPERCENT
  //   | AMPERSAND
  //   | KEYWORD_TRY
  //   | KEYWORD_AWAIT
  public static boolean PrefixOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PREFIX_OP, "<prefix op>");
    r = consumeToken(b, EXCLAMATIONMARK);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, TILDE);
    if (!r) r = consumeToken(b, MINUSPERCENT);
    if (!r) r = consumeToken(b, AMPERSAND);
    if (!r) r = consumeToken(b, KEYWORD_TRY);
    if (!r) r = consumeToken(b, KEYWORD_AWAIT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // QUESTIONMARK
  //   | KEYWORD_ANYFRAME MINUSRARROW
  //   | SliceTypeStart (ByteAlign | AddrSpace | KEYWORD_CONST | KEYWORD_VOLATILE | KEYWORD_ALLOWZERO)*
  //   | PtrTypeStart (AddrSpace | KEYWORD_ALIGN LPAREN Expr (COLON Expr COLON Expr)? RPAREN | KEYWORD_CONST | KEYWORD_VOLATILE | KEYWORD_ALLOWZERO)*
  //   | ArrayTypeStart
  public static boolean PrefixTypeOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixTypeOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PREFIX_TYPE_OP, "<prefix type op>");
    r = consumeToken(b, QUESTIONMARK);
    if (!r) r = parseTokens(b, 0, KEYWORD_ANYFRAME, MINUSRARROW);
    if (!r) r = PrefixTypeOp_2(b, l + 1);
    if (!r) r = PrefixTypeOp_3(b, l + 1);
    if (!r) r = ArrayTypeStart(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SliceTypeStart (ByteAlign | AddrSpace | KEYWORD_CONST | KEYWORD_VOLATILE | KEYWORD_ALLOWZERO)*
  private static boolean PrefixTypeOp_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixTypeOp_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SliceTypeStart(b, l + 1);
    r = r && PrefixTypeOp_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ByteAlign | AddrSpace | KEYWORD_CONST | KEYWORD_VOLATILE | KEYWORD_ALLOWZERO)*
  private static boolean PrefixTypeOp_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixTypeOp_2_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PrefixTypeOp_2_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PrefixTypeOp_2_1", c)) break;
    }
    return true;
  }

  // ByteAlign | AddrSpace | KEYWORD_CONST | KEYWORD_VOLATILE | KEYWORD_ALLOWZERO
  private static boolean PrefixTypeOp_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixTypeOp_2_1_0")) return false;
    boolean r;
    r = ByteAlign(b, l + 1);
    if (!r) r = AddrSpace(b, l + 1);
    if (!r) r = consumeToken(b, KEYWORD_CONST);
    if (!r) r = consumeToken(b, KEYWORD_VOLATILE);
    if (!r) r = consumeToken(b, KEYWORD_ALLOWZERO);
    return r;
  }

  // PtrTypeStart (AddrSpace | KEYWORD_ALIGN LPAREN Expr (COLON Expr COLON Expr)? RPAREN | KEYWORD_CONST | KEYWORD_VOLATILE | KEYWORD_ALLOWZERO)*
  private static boolean PrefixTypeOp_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixTypeOp_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PtrTypeStart(b, l + 1);
    r = r && PrefixTypeOp_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (AddrSpace | KEYWORD_ALIGN LPAREN Expr (COLON Expr COLON Expr)? RPAREN | KEYWORD_CONST | KEYWORD_VOLATILE | KEYWORD_ALLOWZERO)*
  private static boolean PrefixTypeOp_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixTypeOp_3_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PrefixTypeOp_3_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PrefixTypeOp_3_1", c)) break;
    }
    return true;
  }

  // AddrSpace | KEYWORD_ALIGN LPAREN Expr (COLON Expr COLON Expr)? RPAREN | KEYWORD_CONST | KEYWORD_VOLATILE | KEYWORD_ALLOWZERO
  private static boolean PrefixTypeOp_3_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixTypeOp_3_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AddrSpace(b, l + 1);
    if (!r) r = PrefixTypeOp_3_1_0_1(b, l + 1);
    if (!r) r = consumeToken(b, KEYWORD_CONST);
    if (!r) r = consumeToken(b, KEYWORD_VOLATILE);
    if (!r) r = consumeToken(b, KEYWORD_ALLOWZERO);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_ALIGN LPAREN Expr (COLON Expr COLON Expr)? RPAREN
  private static boolean PrefixTypeOp_3_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixTypeOp_3_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD_ALIGN, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && PrefixTypeOp_3_1_0_1_3(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COLON Expr COLON Expr)?
  private static boolean PrefixTypeOp_3_1_0_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixTypeOp_3_1_0_1_3")) return false;
    PrefixTypeOp_3_1_0_1_3_0(b, l + 1);
    return true;
  }

  // COLON Expr COLON Expr
  private static boolean PrefixTypeOp_3_1_0_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrefixTypeOp_3_1_0_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // AsmExpr
  //   | IfExpr
  //   | KEYWORD_BREAK BreakLabel? Expr?
  //   | KEYWORD_COMPTIME Expr
  //   | KEYWORD_NOSUSPEND Expr
  //   | KEYWORD_CONTINUE BreakLabel?
  //   | KEYWORD_RESUME Expr
  //   | KEYWORD_RETURN Expr?
  //   | BlockLabel? LoopExpr
  //   | Block
  //   | CurlySuffixExpr
  public static boolean PrimaryExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, PRIMARY_EXPR, "<primary expr>");
    r = AsmExpr(b, l + 1);
    if (!r) r = IfExpr(b, l + 1);
    if (!r) r = PrimaryExpr_2(b, l + 1);
    if (!r) r = PrimaryExpr_3(b, l + 1);
    if (!r) r = PrimaryExpr_4(b, l + 1);
    if (!r) r = PrimaryExpr_5(b, l + 1);
    if (!r) r = PrimaryExpr_6(b, l + 1);
    if (!r) r = PrimaryExpr_7(b, l + 1);
    if (!r) r = PrimaryExpr_8(b, l + 1);
    if (!r) r = Block(b, l + 1);
    if (!r) r = CurlySuffixExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD_BREAK BreakLabel? Expr?
  private static boolean PrimaryExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_BREAK);
    r = r && PrimaryExpr_2_1(b, l + 1);
    r = r && PrimaryExpr_2_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BreakLabel?
  private static boolean PrimaryExpr_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_2_1")) return false;
    BreakLabel(b, l + 1);
    return true;
  }

  // Expr?
  private static boolean PrimaryExpr_2_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_2_2")) return false;
    Expr(b, l + 1);
    return true;
  }

  // KEYWORD_COMPTIME Expr
  private static boolean PrimaryExpr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_COMPTIME);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_NOSUSPEND Expr
  private static boolean PrimaryExpr_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_NOSUSPEND);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_CONTINUE BreakLabel?
  private static boolean PrimaryExpr_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_CONTINUE);
    r = r && PrimaryExpr_5_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BreakLabel?
  private static boolean PrimaryExpr_5_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_5_1")) return false;
    BreakLabel(b, l + 1);
    return true;
  }

  // KEYWORD_RESUME Expr
  private static boolean PrimaryExpr_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_RESUME);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_RETURN Expr?
  private static boolean PrimaryExpr_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_RETURN);
    r = r && PrimaryExpr_7_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr?
  private static boolean PrimaryExpr_7_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_7_1")) return false;
    Expr(b, l + 1);
    return true;
  }

  // BlockLabel? LoopExpr
  private static boolean PrimaryExpr_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PrimaryExpr_8_0(b, l + 1);
    r = r && LoopExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BlockLabel?
  private static boolean PrimaryExpr_8_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryExpr_8_0")) return false;
    BlockLabel(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // BuiltinIdentifier FnCallArguments
  //   | CHAR_LITERAL
  //   | ContainerDecl
  //   | DOT IDENTIFIER
  //   | DOT InitList
  //   | ErrorSetDecl
  //   | FLOAT
  //   | FnProto
  //   | GroupedExpr
  //   | LabeledTypeExpr
  //   | IDENTIFIER
  //   | IfTypeExpr
  //   | INTEGER
  //   | KEYWORD_COMPTIME TypeExpr
  //   | KEYWORD_ERROR DOT IDENTIFIER
  //   | KEYWORD_ANYFRAME
  //   | KEYWORD_UNREACHABLE
  //   | StringLiteral
  //   | SwitchExpr
  public static boolean PrimaryTypeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryTypeExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, PRIMARY_TYPE_EXPR, "<primary type expr>");
    r = PrimaryTypeExpr_0(b, l + 1);
    if (!r) r = consumeToken(b, CHAR_LITERAL);
    if (!r) r = ContainerDecl(b, l + 1);
    if (!r) r = parseTokens(b, 0, DOT, IDENTIFIER);
    if (!r) r = PrimaryTypeExpr_4(b, l + 1);
    if (!r) r = ErrorSetDecl(b, l + 1);
    if (!r) r = consumeToken(b, FLOAT);
    if (!r) r = FnProto(b, l + 1);
    if (!r) r = GroupedExpr(b, l + 1);
    if (!r) r = LabeledTypeExpr(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = IfTypeExpr(b, l + 1);
    if (!r) r = consumeToken(b, INTEGER);
    if (!r) r = PrimaryTypeExpr_13(b, l + 1);
    if (!r) r = parseTokens(b, 0, KEYWORD_ERROR, DOT, IDENTIFIER);
    if (!r) r = consumeToken(b, KEYWORD_ANYFRAME);
    if (!r) r = consumeToken(b, KEYWORD_UNREACHABLE);
    if (!r) r = StringLiteral(b, l + 1);
    if (!r) r = SwitchExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BuiltinIdentifier FnCallArguments
  private static boolean PrimaryTypeExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryTypeExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = BuiltinIdentifier(b, l + 1);
    r = r && FnCallArguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOT InitList
  private static boolean PrimaryTypeExpr_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryTypeExpr_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && InitList(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_COMPTIME TypeExpr
  private static boolean PrimaryTypeExpr_13(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrimaryTypeExpr_13")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_COMPTIME);
    r = r && TypeExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PIPE (ASTERISK? IDENTIFIER COMMA)* (ASTERISK? IDENTIFIER) PIPE
  public static boolean PtrIndexPayload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrIndexPayload")) return false;
    if (!nextTokenIs(b, PIPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIPE);
    r = r && PtrIndexPayload_1(b, l + 1);
    r = r && PtrIndexPayload_2(b, l + 1);
    r = r && consumeToken(b, PIPE);
    exit_section_(b, m, PTR_INDEX_PAYLOAD, r);
    return r;
  }

  // (ASTERISK? IDENTIFIER COMMA)*
  private static boolean PtrIndexPayload_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrIndexPayload_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PtrIndexPayload_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "PtrIndexPayload_1", c)) break;
    }
    return true;
  }

  // ASTERISK? IDENTIFIER COMMA
  private static boolean PtrIndexPayload_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrIndexPayload_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PtrIndexPayload_1_0_0(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // ASTERISK?
  private static boolean PtrIndexPayload_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrIndexPayload_1_0_0")) return false;
    consumeToken(b, ASTERISK);
    return true;
  }

  // ASTERISK? IDENTIFIER
  private static boolean PtrIndexPayload_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrIndexPayload_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PtrIndexPayload_2_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // ASTERISK?
  private static boolean PtrIndexPayload_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrIndexPayload_2_0")) return false;
    consumeToken(b, ASTERISK);
    return true;
  }

  /* ********************************************************** */
  // PIPE ASTERISK? IDENTIFIER PIPE
  public static boolean PtrPayload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrPayload")) return false;
    if (!nextTokenIs(b, PIPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PIPE);
    r = r && PtrPayload_1(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, PIPE);
    exit_section_(b, m, PTR_PAYLOAD, r);
    return r;
  }

  // ASTERISK?
  private static boolean PtrPayload_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrPayload_1")) return false;
    consumeToken(b, ASTERISK);
    return true;
  }

  /* ********************************************************** */
  // ASTERISK
  //   | ASTERISK2
  //   | LBRACKET ASTERISK ("c" | COLON Expr)? RBRACKET
  public static boolean PtrTypeStart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrTypeStart")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PTR_TYPE_START, "<ptr type start>");
    r = consumeToken(b, ASTERISK);
    if (!r) r = consumeToken(b, ASTERISK2);
    if (!r) r = PtrTypeStart_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LBRACKET ASTERISK ("c" | COLON Expr)? RBRACKET
  private static boolean PtrTypeStart_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrTypeStart_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, ASTERISK);
    r = r && PtrTypeStart_2_2(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // ("c" | COLON Expr)?
  private static boolean PtrTypeStart_2_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrTypeStart_2_2")) return false;
    PtrTypeStart_2_2_0(b, l + 1);
    return true;
  }

  // "c" | COLON Expr
  private static boolean PtrTypeStart_2_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrTypeStart_2_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "c");
    if (!r) r = PtrTypeStart_2_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COLON Expr
  private static boolean PtrTypeStart_2_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PtrTypeStart_2_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CONTAINER_DOC_COMMENT? ContainerMembers?
  static boolean Root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Root")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Root_0(b, l + 1);
    r = r && Root_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CONTAINER_DOC_COMMENT?
  private static boolean Root_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Root_0")) return false;
    consumeToken(b, CONTAINER_DOC_COMMENT);
    return true;
  }

  // ContainerMembers?
  private static boolean Root_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Root_1")) return false;
    ContainerMembers(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACKET (COLON Expr)? RBRACKET
  public static boolean SliceTypeStart(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SliceTypeStart")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && SliceTypeStart_1(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, SLICE_TYPE_START, r);
    return r;
  }

  // (COLON Expr)?
  private static boolean SliceTypeStart_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SliceTypeStart_1")) return false;
    SliceTypeStart_1_0(b, l + 1);
    return true;
  }

  // COLON Expr
  private static boolean SliceTypeStart_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SliceTypeStart_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_COMPTIME? VarDecl
  //   | KEYWORD_COMPTIME BlockExprStatement
  //   | KEYWORD_NOSUSPEND BlockExprStatement
  //   | KEYWORD_DEFER BlockExprStatement
  //   | KEYWORD_ERRDEFER Payload? BlockExprStatement
  //   | IfStatement
  //   | LabeledStatement
  //   | SwitchExpr
  //   | AssignExpr SEMICOLON
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = Statement_0(b, l + 1);
    if (!r) r = Statement_1(b, l + 1);
    if (!r) r = Statement_2(b, l + 1);
    if (!r) r = Statement_3(b, l + 1);
    if (!r) r = Statement_4(b, l + 1);
    if (!r) r = IfStatement(b, l + 1);
    if (!r) r = LabeledStatement(b, l + 1);
    if (!r) r = SwitchExpr(b, l + 1);
    if (!r) r = Statement_8(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD_COMPTIME? VarDecl
  private static boolean Statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Statement_0_0(b, l + 1);
    r = r && VarDecl(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_COMPTIME?
  private static boolean Statement_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_0_0")) return false;
    consumeToken(b, KEYWORD_COMPTIME);
    return true;
  }

  // KEYWORD_COMPTIME BlockExprStatement
  private static boolean Statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_COMPTIME);
    r = r && BlockExprStatement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_NOSUSPEND BlockExprStatement
  private static boolean Statement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_NOSUSPEND);
    r = r && BlockExprStatement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_DEFER BlockExprStatement
  private static boolean Statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_DEFER);
    r = r && BlockExprStatement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_ERRDEFER Payload? BlockExprStatement
  private static boolean Statement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ERRDEFER);
    r = r && Statement_4_1(b, l + 1);
    r = r && BlockExprStatement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Payload?
  private static boolean Statement_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_4_1")) return false;
    Payload(b, l + 1);
    return true;
  }

  // AssignExpr SEMICOLON
  private static boolean Statement_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AssignExpr(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (StringLiteral COMMA)* StringLiteral?
  public static boolean StringList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LIST, "<string list>");
    r = StringList_0(b, l + 1);
    r = r && StringList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (StringLiteral COMMA)*
  private static boolean StringList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StringList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StringList_0", c)) break;
    }
    return true;
  }

  // StringLiteral COMMA
  private static boolean StringList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringList_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = StringLiteral(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // StringLiteral?
  private static boolean StringList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringList_1")) return false;
    StringLiteral(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // STRING_LITERAL_SINGLE | STRING_LITERAL_MULTI
  public static boolean StringLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StringLiteral")) return false;
    if (!nextTokenIs(b, "<string literal>", STRING_LITERAL_MULTI, STRING_LITERAL_SINGLE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LITERAL, "<string literal>");
    r = consumeToken(b, STRING_LITERAL_SINGLE);
    if (!r) r = consumeToken(b, STRING_LITERAL_MULTI);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_ASYNC PrimaryTypeExpr SuffixOp* FnCallArguments
  //   | PrimaryTypeExpr (SuffixOp | FnCallArguments)*
  public static boolean SuffixExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, SUFFIX_EXPR, "<suffix expr>");
    r = SuffixExpr_0(b, l + 1);
    if (!r) r = SuffixExpr_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD_ASYNC PrimaryTypeExpr SuffixOp* FnCallArguments
  private static boolean SuffixExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ASYNC);
    r = r && PrimaryTypeExpr(b, l + 1);
    r = r && SuffixExpr_0_2(b, l + 1);
    r = r && FnCallArguments(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SuffixOp*
  private static boolean SuffixExpr_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixExpr_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!SuffixOp(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SuffixExpr_0_2", c)) break;
    }
    return true;
  }

  // PrimaryTypeExpr (SuffixOp | FnCallArguments)*
  private static boolean SuffixExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixExpr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PrimaryTypeExpr(b, l + 1);
    r = r && SuffixExpr_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SuffixOp | FnCallArguments)*
  private static boolean SuffixExpr_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixExpr_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!SuffixExpr_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SuffixExpr_1_1", c)) break;
    }
    return true;
  }

  // SuffixOp | FnCallArguments
  private static boolean SuffixExpr_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixExpr_1_1_0")) return false;
    boolean r;
    r = SuffixOp(b, l + 1);
    if (!r) r = FnCallArguments(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET Expr (DOT2 (Expr? (COLON Expr)?)?)? RBRACKET
  //   | DOT IDENTIFIER
  //   | DOTASTERISK
  //   | DOTQUESTIONMARK
  public static boolean SuffixOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixOp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SUFFIX_OP, "<suffix op>");
    r = SuffixOp_0(b, l + 1);
    if (!r) r = parseTokens(b, 0, DOT, IDENTIFIER);
    if (!r) r = consumeToken(b, DOTASTERISK);
    if (!r) r = consumeToken(b, DOTQUESTIONMARK);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LBRACKET Expr (DOT2 (Expr? (COLON Expr)?)?)? RBRACKET
  private static boolean SuffixOp_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixOp_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Expr(b, l + 1);
    r = r && SuffixOp_0_2(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // (DOT2 (Expr? (COLON Expr)?)?)?
  private static boolean SuffixOp_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixOp_0_2")) return false;
    SuffixOp_0_2_0(b, l + 1);
    return true;
  }

  // DOT2 (Expr? (COLON Expr)?)?
  private static boolean SuffixOp_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixOp_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT2);
    r = r && SuffixOp_0_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Expr? (COLON Expr)?)?
  private static boolean SuffixOp_0_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixOp_0_2_0_1")) return false;
    SuffixOp_0_2_0_1_0(b, l + 1);
    return true;
  }

  // Expr? (COLON Expr)?
  private static boolean SuffixOp_0_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixOp_0_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SuffixOp_0_2_0_1_0_0(b, l + 1);
    r = r && SuffixOp_0_2_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr?
  private static boolean SuffixOp_0_2_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixOp_0_2_0_1_0_0")) return false;
    Expr(b, l + 1);
    return true;
  }

  // (COLON Expr)?
  private static boolean SuffixOp_0_2_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixOp_0_2_0_1_0_1")) return false;
    SuffixOp_0_2_0_1_0_1_0(b, l + 1);
    return true;
  }

  // COLON Expr
  private static boolean SuffixOp_0_2_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SuffixOp_0_2_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SwitchItem (COMMA SwitchItem)* COMMA?
  //   | KEYWORD_ELSE
  public static boolean SwitchCase(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchCase")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_CASE, "<switch case>");
    r = SwitchCase_0(b, l + 1);
    if (!r) r = consumeToken(b, KEYWORD_ELSE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SwitchItem (COMMA SwitchItem)* COMMA?
  private static boolean SwitchCase_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchCase_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SwitchItem(b, l + 1);
    r = r && SwitchCase_0_1(b, l + 1);
    r = r && SwitchCase_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA SwitchItem)*
  private static boolean SwitchCase_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchCase_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!SwitchCase_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SwitchCase_0_1", c)) break;
    }
    return true;
  }

  // COMMA SwitchItem
  private static boolean SwitchCase_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchCase_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && SwitchItem(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA?
  private static boolean SwitchCase_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchCase_0_2")) return false;
    consumeToken(b, COMMA);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_SWITCH LPAREN Expr RPAREN LBRACE SwitchProngList RBRACE
  public static boolean SwitchExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchExpr")) return false;
    if (!nextTokenIs(b, KEYWORD_SWITCH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD_SWITCH, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeTokens(b, 0, RPAREN, LBRACE);
    r = r && SwitchProngList(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, SWITCH_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // Expr (DOT3 Expr)?
  public static boolean SwitchItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchItem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_ITEM, "<switch item>");
    r = Expr(b, l + 1);
    r = r && SwitchItem_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (DOT3 Expr)?
  private static boolean SwitchItem_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchItem_1")) return false;
    SwitchItem_1_0(b, l + 1);
    return true;
  }

  // DOT3 Expr
  private static boolean SwitchItem_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchItem_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT3);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KEYWORD_INLINE? SwitchCase EQUALRARROW PtrIndexPayload? AssignExpr
  public static boolean SwitchProng(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchProng")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_PRONG, "<switch prong>");
    r = SwitchProng_0(b, l + 1);
    r = r && SwitchCase(b, l + 1);
    r = r && consumeToken(b, EQUALRARROW);
    r = r && SwitchProng_3(b, l + 1);
    r = r && AssignExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD_INLINE?
  private static boolean SwitchProng_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchProng_0")) return false;
    consumeToken(b, KEYWORD_INLINE);
    return true;
  }

  // PtrIndexPayload?
  private static boolean SwitchProng_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchProng_3")) return false;
    PtrIndexPayload(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (SwitchProng COMMA)* SwitchProng?
  public static boolean SwitchProngList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchProngList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_PRONG_LIST, "<switch prong list>");
    r = SwitchProngList_0(b, l + 1);
    r = r && SwitchProngList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (SwitchProng COMMA)*
  private static boolean SwitchProngList_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchProngList_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!SwitchProngList_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "SwitchProngList_0", c)) break;
    }
    return true;
  }

  // SwitchProng COMMA
  private static boolean SwitchProngList_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchProngList_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SwitchProng(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  // SwitchProng?
  private static boolean SwitchProngList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SwitchProngList_1")) return false;
    SwitchProng(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DOC_COMMENT? KEYWORD_TEST (STRING_LITERAL_SINGLE | IDENTIFIER)? Block
  public static boolean TestDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TestDecl")) return false;
    if (!nextTokenIs(b, "<test decl>", DOC_COMMENT, KEYWORD_TEST)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TEST_DECL, "<test decl>");
    r = TestDecl_0(b, l + 1);
    r = r && consumeToken(b, KEYWORD_TEST);
    r = r && TestDecl_2(b, l + 1);
    r = r && Block(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DOC_COMMENT?
  private static boolean TestDecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TestDecl_0")) return false;
    consumeToken(b, DOC_COMMENT);
    return true;
  }

  // (STRING_LITERAL_SINGLE | IDENTIFIER)?
  private static boolean TestDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TestDecl_2")) return false;
    TestDecl_2_0(b, l + 1);
    return true;
  }

  // STRING_LITERAL_SINGLE | IDENTIFIER
  private static boolean TestDecl_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TestDecl_2_0")) return false;
    boolean r;
    r = consumeToken(b, STRING_LITERAL_SINGLE);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // PrefixTypeOp* ErrorUnionExpr
  public static boolean TypeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, TYPE_EXPR, "<type expr>");
    r = TypeExpr_0(b, l + 1);
    r = r && ErrorUnionExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PrefixTypeOp*
  private static boolean TypeExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeExpr_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!PrefixTypeOp(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TypeExpr_0", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (KEYWORD_CONST | KEYWORD_VAR) IDENTIFIER (COLON TypeExpr)? ByteAlign? AddrSpace? LinkSection? (EQUAL Expr)? SEMICOLON
  public static boolean VarDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl")) return false;
    if (!nextTokenIs(b, "<var decl>", KEYWORD_CONST, KEYWORD_VAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VAR_DECL, "<var decl>");
    r = VarDecl_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && VarDecl_2(b, l + 1);
    r = r && VarDecl_3(b, l + 1);
    r = r && VarDecl_4(b, l + 1);
    r = r && VarDecl_5(b, l + 1);
    r = r && VarDecl_6(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // KEYWORD_CONST | KEYWORD_VAR
  private static boolean VarDecl_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_0")) return false;
    boolean r;
    r = consumeToken(b, KEYWORD_CONST);
    if (!r) r = consumeToken(b, KEYWORD_VAR);
    return r;
  }

  // (COLON TypeExpr)?
  private static boolean VarDecl_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_2")) return false;
    VarDecl_2_0(b, l + 1);
    return true;
  }

  // COLON TypeExpr
  private static boolean VarDecl_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && TypeExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ByteAlign?
  private static boolean VarDecl_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_3")) return false;
    ByteAlign(b, l + 1);
    return true;
  }

  // AddrSpace?
  private static boolean VarDecl_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_4")) return false;
    AddrSpace(b, l + 1);
    return true;
  }

  // LinkSection?
  private static boolean VarDecl_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_5")) return false;
    LinkSection(b, l + 1);
    return true;
  }

  // (EQUAL Expr)?
  private static boolean VarDecl_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_6")) return false;
    VarDecl_6_0(b, l + 1);
    return true;
  }

  // EQUAL Expr
  private static boolean VarDecl_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "VarDecl_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQUAL);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COLON LPAREN AssignExpr RPAREN
  public static boolean WhileContinueExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileContinueExpr")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COLON, LPAREN);
    r = r && AssignExpr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, WHILE_CONTINUE_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // WhilePrefix Expr (KEYWORD_ELSE Payload? Expr)?
  public static boolean WhileExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileExpr")) return false;
    if (!nextTokenIs(b, KEYWORD_WHILE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = WhilePrefix(b, l + 1);
    r = r && Expr(b, l + 1);
    r = r && WhileExpr_2(b, l + 1);
    exit_section_(b, m, WHILE_EXPR, r);
    return r;
  }

  // (KEYWORD_ELSE Payload? Expr)?
  private static boolean WhileExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileExpr_2")) return false;
    WhileExpr_2_0(b, l + 1);
    return true;
  }

  // KEYWORD_ELSE Payload? Expr
  private static boolean WhileExpr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileExpr_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && WhileExpr_2_0_1(b, l + 1);
    r = r && Expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Payload?
  private static boolean WhileExpr_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileExpr_2_0_1")) return false;
    Payload(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_WHILE LPAREN Expr RPAREN PtrPayload? WhileContinueExpr?
  public static boolean WhilePrefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhilePrefix")) return false;
    if (!nextTokenIs(b, KEYWORD_WHILE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEYWORD_WHILE, LPAREN);
    r = r && Expr(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && WhilePrefix_4(b, l + 1);
    r = r && WhilePrefix_5(b, l + 1);
    exit_section_(b, m, WHILE_PREFIX, r);
    return r;
  }

  // PtrPayload?
  private static boolean WhilePrefix_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhilePrefix_4")) return false;
    PtrPayload(b, l + 1);
    return true;
  }

  // WhileContinueExpr?
  private static boolean WhilePrefix_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhilePrefix_5")) return false;
    WhileContinueExpr(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // WhilePrefix BlockExpr ( KEYWORD_ELSE Payload? Statement )?
  //   | WhilePrefix AssignExpr ( SEMICOLON | KEYWORD_ELSE Payload? Statement)
  public static boolean WhileStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileStatement")) return false;
    if (!nextTokenIs(b, KEYWORD_WHILE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = WhileStatement_0(b, l + 1);
    if (!r) r = WhileStatement_1(b, l + 1);
    exit_section_(b, m, WHILE_STATEMENT, r);
    return r;
  }

  // WhilePrefix BlockExpr ( KEYWORD_ELSE Payload? Statement )?
  private static boolean WhileStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileStatement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = WhilePrefix(b, l + 1);
    r = r && BlockExpr(b, l + 1);
    r = r && WhileStatement_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ( KEYWORD_ELSE Payload? Statement )?
  private static boolean WhileStatement_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileStatement_0_2")) return false;
    WhileStatement_0_2_0(b, l + 1);
    return true;
  }

  // KEYWORD_ELSE Payload? Statement
  private static boolean WhileStatement_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileStatement_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && WhileStatement_0_2_0_1(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Payload?
  private static boolean WhileStatement_0_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileStatement_0_2_0_1")) return false;
    Payload(b, l + 1);
    return true;
  }

  // WhilePrefix AssignExpr ( SEMICOLON | KEYWORD_ELSE Payload? Statement)
  private static boolean WhileStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = WhilePrefix(b, l + 1);
    r = r && AssignExpr(b, l + 1);
    r = r && WhileStatement_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEMICOLON | KEYWORD_ELSE Payload? Statement
  private static boolean WhileStatement_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileStatement_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    if (!r) r = WhileStatement_1_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // KEYWORD_ELSE Payload? Statement
  private static boolean WhileStatement_1_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileStatement_1_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && WhileStatement_1_2_1_1(b, l + 1);
    r = r && Statement(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Payload?
  private static boolean WhileStatement_1_2_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileStatement_1_2_1_1")) return false;
    Payload(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // WhilePrefix TypeExpr (KEYWORD_ELSE Payload? TypeExpr)?
  public static boolean WhileTypeExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileTypeExpr")) return false;
    if (!nextTokenIs(b, KEYWORD_WHILE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = WhilePrefix(b, l + 1);
    r = r && TypeExpr(b, l + 1);
    r = r && WhileTypeExpr_2(b, l + 1);
    exit_section_(b, m, WHILE_TYPE_EXPR, r);
    return r;
  }

  // (KEYWORD_ELSE Payload? TypeExpr)?
  private static boolean WhileTypeExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileTypeExpr_2")) return false;
    WhileTypeExpr_2_0(b, l + 1);
    return true;
  }

  // KEYWORD_ELSE Payload? TypeExpr
  private static boolean WhileTypeExpr_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileTypeExpr_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEYWORD_ELSE);
    r = r && WhileTypeExpr_2_0_1(b, l + 1);
    r = r && TypeExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Payload?
  private static boolean WhileTypeExpr_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileTypeExpr_2_0_1")) return false;
    Payload(b, l + 1);
    return true;
  }

}
