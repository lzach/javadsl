package dsl.ast;

import dsl.Context;
import dsl.Type;
import dsl.parser.Token;

import java.util.*;

public class AST {
  private final String typeName;
  private final Map<String, AST> members;
  private final Object value;
  private final AST[] memberList;
  private final Token fromToken;
  private boolean checked = false;

  public AST(String type, Map<String, AST> members) {
    this.typeName = type;
    this.members = members;
    this.value = null;
    this.memberList = null;
    this.fromToken  = Token.UNKOWN;
  }

  public AST(String type, Map<String, AST> members, Token fromToken) {
    this.typeName = type;
    this.members = members;
    this.value = null;
    this.memberList = null;
    this.fromToken = fromToken;
  }

  public AST(String type, Object[] ... members) {
    this.typeName = type;
    this.members = listOfListToMap(members);
    this.value = null;
    this.memberList = null;
    this.fromToken  = Token.UNKOWN;
  }

  public AST(String type, Token fromToken, Object[] ... members) {
    this.typeName = type;
    this.members = listOfListToMap(members);
    this.value = null;
    this.memberList = null;
    this.fromToken = fromToken;
  }

  private AST(String type, Object value) {
    this.typeName = type;
    this.members = null;
    this.value = value;
    this.memberList = null;
    this.fromToken  = Token.UNKOWN;
  }

  public AST(String type, Object value, Token fromToken) {
    this.typeName = type;
    this.members = null;
    this.value = value;
    this.memberList = null;
    this.fromToken = fromToken;
  }

  public AST(String type, AST... memberList) {
    this.typeName = type;
    this.members = null;
    this.value = null;
    this.memberList = memberList;
    this.fromToken  = Token.UNKOWN;
  }

  public AST(String type, Token fromToken, AST... memberList) {
    this.typeName = type;
    this.members = null;
    this.value = null;
    this.memberList = memberList;
    this.fromToken = fromToken;
  }

  public static AST IDLit(String name) {
    return create("IDLit", name);
  }


  public String getMemberType() {
    return isMembers() ? "Members" : isList() ? "List" : "Value";
  }


  public static AST create(String type) {
    return new AST(type, new HashMap<>());
  }

  public static AST create(String type, Object value) {
    return new AST(type, value);
  }

  public static AST create(String typeName, Object value, Token token) {
    return new AST(typeName, value, token);
  }

  public Object getValue() {
    if ( value instanceof Token )
      return ((Token)value).getValue();
    return value;
  }

  void check(Context ctx) {
    if (checked) {
      return;
    }
    Type type = ctx.getType(typeName);

    if (members != null) {
      if (type.getArrayType() != null) {
        throw new InvalidAstException(type.getName() + " is an array, not a language construct" + tokenError());
      }

      if (members.size() > 0 && type.getMember("arguments").equals("None")) {
        throw new InvalidAstException(type.getName() + " does not take any parameters" + tokenError());
      }
      Map<String, String> argMembers = ctx.getType(type.getMember("arguments")).getMembers();
      if (argMembers.size() > members.size()) {
        StringBuilder missing = new StringBuilder();
        for (String k : argMembers.keySet()) {
          if (!members.containsKey(k)) {
            missing.append(k).append(", ");
          }
        }
        if (missing.length() > 2) {
          missing = new StringBuilder(missing.substring(0, missing.length() - 2));
        }
        throw new InvalidAstException(type.getName() + " missing members(s): " + missing + tokenError());
      }
      for (String name : members.keySet()) {
        AST ast = members.get(name);
        if (!ast.checked) {
          ast.check(ctx);
        }
        if (!argMembers.containsKey(name)) {
          throw new InvalidAstException(type.getName() + " doesn't have a member called " + name + tokenError());
        }
        Type argType = ctx.getType(argMembers.get(name));
        if (!ctx.supertype(argType, ctx.getType(ast.typeName))) {
          if (ctx.supertype(ctx.getType(ast.typeName), argType)) {
            members.put(name, ast.downcast(ctx, argType.getName()));
          } else {
            throw new InvalidAstException(type.getName() + "." + name + "(" + ast.typeName + ") should be of type " + argMembers.get(name) + tokenError());
          }
        }
      }
    } else if (value != null) {
      if (type.isArray()) {
        throw new InvalidAstException(type.getName() + " is an array, not a literal" + tokenError());
      }
      if (!type.getMember("arguments").equals("None")) {
        throw new InvalidAstException(type.getName() + " does not take any parameters" + tokenError());
      }
      if (!(type.getMember("returns").equals("LiteralType"))) {
        throw new InvalidAstException(type.getName() + " does not produce a literal" + tokenError());
      }
      Type t = ctx.getType(type.getMember("returns"));
      if (!t.contains(value)) {
        throw new InvalidAstException(type.getName() + " requires objects of type " + t.getName() + tokenError());
      }
    } else if (memberList != null) {
      if (type.getArrayType() == null) {
        throw new InvalidAstException(type.getName() + " is not an array" + tokenError());
      }
      Type arrayType = ctx.getType(type.getArrayType());
      int i = 0;
      for (AST ast : memberList) {
        if (!ast.checked) {
          ast.check(ctx);
        }
        if (!ctx.supertype(arrayType, ctx.getType(ast.typeName))) {
          throw new InvalidAstException(type.getName() + "[" + i + "] (" + ast.typeName + ") should be of type " + arrayType.getName() + tokenError());
        }
        i += 1;
      }
    }
    assert ((value == null ? 0 : 1) + (memberList == null ? 0 : 1) + (members == null ? 0 : 1) == 1);
    checked = true;
  }

  private String tokenError() {
    return " " + fromToken.toString() + "\n\n" + fromToken.errorString();
  }

  private AST downcast(Context ctx, String nType) {
    AST ast;
    if (members != null) {
      ast = new AST(nType, members);
    } else if (memberList != null) {
      ast = new AST(nType, memberList);
    } else {
      ast = new AST(nType, value);
    }
    ast.check(ctx);
    return ast;
  }

  public String toString() {
    if (value != null) {
      return value.toString();
    }
    String args = "";
    if (members != null) {
      for (String k : members.keySet()) {
        args += k + ":" + members.get(k) + " ";
      }
    } else {
      for (AST val : memberList) {
        args += val.toString() + " ";
      }
    }
    if (args.length() > 0) {
      args = args.substring(0, args.length() - 1);
    }
    if (args.length() == 0) return typeName;
    return "(" + typeName + " " + args + ")";
  }

  public String getTypeName() {
    return typeName;
  }

  public AST get(String member) {
    if (members == null || !members.containsKey(member)) {
      throw new InvalidAstException("Can't access member " + member + ", not a labeled ast or member doesn't exist" + tokenError());
    }
    return members.get(member);
  }

  public AST[] getMemberList() {
    return memberList;
  }

  private Map<String, AST> listOfListToMap(Object[]... members) {
    checkTuples(members);
    Map<String, AST> asts = new HashMap<>();
    for ( Object[] tuple : members) {
      if ( tuple != null ) {
        asts.put((String) tuple[0], (AST) tuple[1]);
      }
    }
    return asts;
  }

  private static  void  checkTuples(Object[] ... members) {
    for ( Object[] objs : members ) {
      assert objs == null || (objs.length == 2 && objs[0] instanceof String && objs[1] instanceof AST);
    }
  }

  public boolean isMembers() {
    return members != null;
  }

  public boolean isList() {
    return memberList != null;
  }

  public Set<String> getMembers() {
    return members.keySet();
  }

  public boolean hasMember(String name) {
    return isMembers() && members.containsKey(name);
  }

  public boolean isValue() {
    return value != null;
  }

  @Override
  public boolean equals(Object other) {
    if ( !(other instanceof AST) ) {
      return false;
    }
    AST ast = (AST)other;
    return Objects.equals(typeName, ast.typeName) && Objects.equals(members, ast.members) && Arrays.equals(memberList, ast.memberList) && Objects.equals(value, ast.value);
  }

  Map<String, AST> getMemberMap() {
    return members;
  }
}
