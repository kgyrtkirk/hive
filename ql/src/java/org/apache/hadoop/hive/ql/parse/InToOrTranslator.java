package org.apache.hadoop.hive.ql.parse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;

public class InToOrTranslator {

  static final Token AST_OR = new CommonToken(HiveParser.KW_OR, "OR");
  static final Token AST_AND = new CommonToken(HiveParser.KW_AND, "AND");
  static final Token AST_EQUAL = new CommonToken(HiveParser.EQUAL, "=");

  public void processIns(ASTNode root) throws SemanticException {
    Deque<ASTNode> stack = new ArrayDeque<ASTNode>();
    stack.push(root);

    while (!stack.isEmpty()) {
      ASTNode astNode = stack.pop();

      for (int i = astNode.getChildren().size() - 1; i >= 0; i--) {
        ASTNode e = (ASTNode) astNode.getChildren().get(i);
        if (e.getType() == HiveParser.KW_IN) {
          e = transformInToOr(e);
          astNode.setChild(i, e);
        }
        stack.push(e);
      }
    }

  }

  private ASTNode transformInToOr(ASTNode e) {
    List<ASTNode> operands = new ArrayList<>();
    List<ASTNode> lNodes = getNodes(e.getChild(0));
    for (int i = 1; i < e.getChildCount(); i++) {
      List<ASTNode> rNodes = getNodes(e.getChild(i));
      ASTNode o = createEquals(lNodes, rNodes);
      if (o == null) {
        return e;
      }
      operands.add(o);
    }
    return createASTNode(AST_OR, operands);
  }

  private ASTNode createEquals(List<ASTNode> lNodes, List<ASTNode> rNodes) {
    if (lNodes.size() != rNodes.size()) {
      return null;
    }
    List<ASTNode> operands = new ArrayList<>();
    for (int i = 0; i < lNodes.size(); i++) {
      operands.add(createASTNode(AST_EQUAL, lNodes.get(i), rNodes.get(i)));
    }
    return createASTNode(AST_AND, operands);
  }


  private ASTNode createASTNode(Token t, ASTNode... children) {
    return createASTNode(t, Arrays.asList(children));
  }

  private ASTNode createASTNode(Token t, List<ASTNode> children) {
    ASTNode node = new ASTNode(t);
    node.addChildren(children);
    return node;
  }

  private List<ASTNode> getNodes(Tree tree) {
    List<ASTNode> ret = new ArrayList<>();
    ret.add((ASTNode) tree);
    return ret;
  }

}
