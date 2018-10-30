package org.apache.hadoop.hive.ql.parse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.apache.hadoop.hive.ql.lib.Node;
import org.spark_project.guava.collect.Lists;

public class InToOrTranslator {

  static final Token AST_OR = new CommonToken(HiveParser.KW_OR, "OR");
  static final Token AST_AND = new CommonToken(HiveParser.KW_AND, "AND");
  static final Token AST_EQUAL = new CommonToken(HiveParser.EQUAL, "=");

  static class InExpression {


  }


  public static List<Node> of(ASTNode e) {
    return getFunctionCallArguments("in", e);
  }

  public static List<Node> getFunctionCallArguments(String functionName, ASTNode e) {
    if (e.getType() == HiveParser.TOK_FUNCTION &&
        e.getChildCount()>2 &&
        e.getChildren()!=null &&
        e.getChild(0).getType() == HiveParser.Identifier &&
        functionName.equals(e.getChild(0).getText())
        ) {
      return e.getChildren().subList(1, e.getChildren().size());
    }
    return null;
  }


  public void processIns(ASTNode root) throws SemanticException {
    Deque<ASTNode> stack = new ArrayDeque<ASTNode>();
    stack.push(root);

    while (!stack.isEmpty()) {
      ASTNode astNode = stack.pop();
      if (astNode.getChildren() == null)
        continue;
      for (int i = astNode.getChildren().size() - 1; i >= 0; i--) {
        ASTNode e = (ASTNode) astNode.getChildren().get(i);
        List<Node> inOperands = of(e);
        if (inOperands != null) {
          ASTNode t = transformInToOr(inOperands);
          if (t != null) {
            astNode.setChild(i, t);
            e = t;
          } else {
            int asd = 1;
          }
        }
        stack.push(e);
      }
    }

  }

  private ASTNode transformInToOr(List<Node> inOperands) {
    List<ASTNode> operands = new ArrayList<>();
    List<Node> lNodes = getNodes(inOperands.get(0));
    for (int i = 1; i < inOperands.size(); i++) {
      List<Node> rNodes = getNodes(inOperands.get(i));
      ASTNode o = createEquals(lNodes, rNodes);
      if (o == null) {
        return null;
      }
      operands.add(o);
    }
    return createASTNode(AST_OR, operands);
  }

  private ASTNode createEquals(List<Node> lNodes, List<Node> rNodes) {
    if (lNodes == null || rNodes == null || lNodes.size() != rNodes.size()) {
      return null;
    }
    List<ASTNode> operands = new ArrayList<>();
    for (int i = 0; i < lNodes.size(); i++) {
      operands.add(createASTNode(AST_EQUAL, (ASTNode) lNodes.get(i), (ASTNode) rNodes.get(i)));
    }
    if (operands.size() > 1) {
      return createASTNode(AST_AND, operands);
    } else {
      return operands.get(0);
    }
  }


  private ASTNode createASTNode(Token t, ASTNode... children) {
    return createASTNode(t, Arrays.asList(children));
  }

  private ASTNode createASTNode(Token t, List<ASTNode> children) {
    ASTNode node = new ASTNode(t);
    node.addChildren(children);
    return node;
  }

  private List<Node> getNodes(Node node) {
    if (isRefOrConst(node)) {
      return Lists.newArrayList((ASTNode) node);
    }
    List<Node> structArgs = getFunctionCallArguments("struct", (ASTNode) node);
    if (structArgs != null && structArgs.stream().allMatch(n -> isRefOrConst(n))) {
      return structArgs;
    }
    return null;
  }

  private boolean isRefOrConst(Node node) {
    // TODO Auto-generated method stub
    return false;
  }

}
