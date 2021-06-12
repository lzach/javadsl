package dsl.ast;


import dsl.Context;
import dsl.Type;
import dsl.parser.Token;

import java.util.*;

public class ASTBuilder implements ASTMemberBuilder, ASTListBuilder {
  private String typeName = null;
  private Map<String, AST> members = null;
  private List<AST> memberList = null;
  private Object value = null;
  private Token token = null;

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
    if ( ast.getMemberList() != null && ast.getMemberList().length > 0 ) {
      this.memberList = new ArrayList<>(Arrays.asList(ast.getMemberList()));
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
      this.memberList = new ArrayList<>(Arrays.asList(memberList));
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
    if ( memberList.length == 1 && memberList[0] == null ) {
      this.memberList = new ArrayList<>();
    } else {
      this.memberList = new ArrayList<>(Arrays.asList(memberList));
    }
  }
  public ASTBuilder(List<AST>  memberList) {
    this.memberList = memberList;
  }


  public ASTBuilder() {}

  public ASTBuilder setToken(Token token) {
    this.token = token;
    return this;
  }

  @Override
  public ASTBuilder add(String name, AST ast) {
    if ( members == null) {
      createMembers();
    }
    members.put(name, ast);
    return this;
  }
  @Override
  public ASTBuilder addAll(Map<String, AST> asts) {
    if ( members == null) {
      createMembers();
    }
    members.putAll(asts);
    return this;
  }

  @Override
  public ASTBuilder add(AST ast) {
    if ( memberList == null ) {
      createMemberList();
    }
    if ( ast != null ) {
      memberList.add(ast);
    }
    return this;
  }

  @Override
  public ASTBuilder add(int index, AST ast) {
    if ( memberList == null ) {
      createMemberList();
    }
    if ( ast != null ) {
      memberList.add(index, ast);
    }
    return this;
  }


  public ASTBuilder add(int index, ASTBuilder ast) {
    if ( memberList == null ) {
      createMemberList();
    }
    if ( ast != null ) {
      memberList.add(index, ast.create());
    }
    return this;
  }

  @Override
  public ASTBuilder addAll(Collection<AST> asts) {
    if ( memberList == null ) {
      createMemberList();
    }
    memberList.addAll(asts);
    return this;
  }

  @Override
  public ASTBuilder addAll(AST ... asts) {
    if ( memberList == null ) {
      createMemberList();
    }
    memberList.addAll(Arrays.asList(asts));
    return this;
  }

  public ASTBuilder set(Object value) {
    createValue(value);
    return this;
  }
  public ASTBuilder setAdd(Object value) {
    createValue((String)this.value + value);
    return this;
  }

  public ASTBuilder setName(String name) {
//    if ( this.typeName != null ) {
//      throw new InvalidAstException("Can't set name when typeName is set");
//    }
    this.typeName = name;
    return this;
  }


  public boolean canHaveMembers() {
    return members != null || (memberList == null && value == null);
  }

  protected void createMembers() {
    if ( !canHaveMembers() ) {
      throw new InvalidAstException("Can't both have members and values/arrays");
    }
    members = new HashMap<>();
  }
  public boolean canHaveMemberList() {
    return memberList != null || (members == null && value == null);
  }

  protected void createMemberList() {
    if ( !canHaveMemberList() ) {
      throw new InvalidAstException("Can't both have arrays and values/members");
    }
    memberList = new ArrayList<>();
  }
  public boolean canHaveValues() {
    return value != null || (memberList == null && members == null);
  }

  protected void createValue(Object value) {
    if ( !canHaveValues() ) {
      throw new InvalidAstException("Can't both have values and members/arrays");
    }
    this.value = value;
  }

  @Override
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

  @Override
  public AST create() {

    AST ast = null;
    if (typeName == null) {
      typeName = "";
    }
    if ( token != null ) {
      if (members != null) {
        ast = new AST(typeName, members, token);
      } else if (memberList != null) {
        ast = new AST(typeName, token, memberList.toArray(new AST[0]));
      } else if (value != null) {
        ast = AST.create(typeName, value, token);
      } else {
        ast = new AST(typeName, token, new AST[]{});
      }
    } else {
      if (members != null) {
        ast = new AST(typeName, members);
      } else if (memberList != null) {
        ast = new AST(typeName, memberList.toArray(new AST[0]));
      } else if (value != null) {
        ast = AST.create(typeName, value);
      } else {
        ast = AST.emptyList(typeName);
      }
    }
    return ast;
  }

  public String getTypeName() {
    return typeName;
  }

  public Object getValue() {
    return value;
  }

  public int size() {
    return members != null ? members.size() : (memberList != null ? memberList.size(): 0);
  }

  public List<AST> getList() {
    return memberList;
  }
}
