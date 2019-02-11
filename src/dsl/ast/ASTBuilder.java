package dsl.ast;


import dsl.Context;
import dsl.Type;

import java.util.*;

public class ASTBuilder {
  private String typeName = null;
  private Map<String, AST> members = null;
  private List<AST> memberList = null;
  private Object value = null;

  /**
   * Create an ASTBuilder with a set initial Language typeName and some initial members.
   *
   * @param typeName the language construct typeName.
   * @param members the initial members.
   */

  public ASTBuilder(String typeName, Map<String, AST> members) {
    this.typeName = typeName;
    this.members = members;
  }


  public ASTBuilder(AST ast) {
    this.typeName = ast.getTypeName();
    this.members = ast.getMemberMap();
    if ( ast.getMemberList() != null ) {
      this.memberList = Arrays.asList(ast.getMemberList());
    }
    this.value = ast.getValue();

  }

  public ASTBuilder(String typeName, Object value) {
    this.typeName = typeName;
    this.value = value;
  }

  public ASTBuilder(String typeName, AST... memberList) {
    this.typeName = typeName;
    if ( memberList.length != 0) {
      this.memberList = Arrays.asList(memberList);
    }
  }

  public ASTBuilder(String typeName, List<AST> memberList) {
    this.typeName = typeName;
    this.memberList = memberList;
  }


   public ASTBuilder(Map<String, AST> members) {
    this.members = members;
  }

  public ASTBuilder(String typeName) {
    this.typeName = typeName;
  }

  public ASTBuilder(Object value) {
    this.value = value;
  }

  public ASTBuilder(AST... memberList) {
    this.memberList = Arrays.asList(memberList);
  }
  public ASTBuilder(List<AST>  memberList) {
    this.memberList = memberList;
  }


  public ASTBuilder() {}

  public ASTBuilder add(String name, AST ast) {
    if ( members == null) {
      createMembers();
    }
    members.put(name, ast);
    return this;
  }
  public ASTBuilder addAll(Map<String, AST> asts) {
    if ( members == null) {
      createMembers();
    }
    members.putAll(asts);
    return this;
  }

  public ASTBuilder add(AST ast) {
    if ( memberList == null ) {
      createMemberList();
    }
    memberList.add(ast);
    return this;
  }
  public ASTBuilder addAll(Collection<AST> asts) {
    if ( memberList == null ) {
      createMemberList();
    }
    memberList.addAll(asts);
    return this;
  }

  public ASTBuilder set(Object value) {
    createValue(value);
    return this;
  }

  public ASTBuilder setName(String name) {
    if ( this.typeName != null ) {
      throw new InvalidAstException("Can't set name when typeName is set");
    }
    this.typeName = name;
    return this;
  }


  public boolean canHaveMembers() {
    return members != null || (memberList == null && value == null);
  }

  protected void createMembers() {
    if ( memberList != null || value != null ) {
      throw new InvalidAstException("Can't both have members and values/arrays");
    }
    members = new HashMap<>();
  }
  public boolean canHaveMemberList() {
    return memberList != null || (members == null && value == null);
  }

  protected void createMemberList() {
    if ( members != null || value != null ) {
      throw new InvalidAstException("Can't both have arrays and values/members");
    }
    memberList = new ArrayList<>();
  }
  public boolean canHaveValues() {
    return value != null || (memberList == null && members == null);
  }

  protected void createValue(Object value) {
    if ( memberList != null || members != null ) {
      throw new InvalidAstException("Can't both have values and members/arrays");
    }
    this.value = value;
  }

  public AST create(Context ctx) {
    Type type = ctx.getType(typeName);
    if ( type == null ) {
      throw new InvalidAstException("Type " + typeName + " doesn't exist in context");
    }

    if (type.isArray() && value instanceof AST) {
      memberList = new ArrayList<>();
      memberList.add((AST)value);
      value = null;
    } else if ( !type.isArray() && memberList != null && memberList.size() == 1) {
      value = memberList.get(0);
      memberList = null;
    }

    AST ast = null;
    if ( members != null ) {
      ast = new AST(typeName, members);
    } else if ( memberList != null  ) {
      ast = new AST(typeName, memberList.toArray(new AST[memberList.size()]));
    } else if ( value != null ) {
      ast = AST.create(typeName, value);
    } else {
      if ( type.isArray() ) {
        ast = new AST(typeName, new AST[]{});
      } else {
        ast = new AST(typeName, members);
      }
    }
    ast.check(ctx);
    return ast;
  }

  public AST create() {

    AST ast = null;
    if ( members != null ) {
      ast = new AST(typeName, members);
    } else if ( memberList != null  ) {
      ast = new AST(typeName, memberList.toArray(new AST[memberList.size()]));
    } else if ( value != null ) {
      ast = AST.create(typeName, value);
    } else {
      ast = new AST(typeName, new AST[]{});
    }
    return ast;
  }

  public String getTypeName() {
    return typeName;
  }
}
