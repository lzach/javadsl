package dsl;

import dsl.typeset.TypeSet;
import dsl.typesets.AnyLiteralSet;
import dsl.typesets.AnySet;
import dsl.typesets.NoneSet;

import java.util.HashMap;
import java.util.Map;

public class Context {
  private static final TypeSet NoneSet = new NoneSet();
  private final Map<String, Type> typeMap = new HashMap<>();

  private static final TypeSet AnySet = new AnySet();
  private static final TypeSet AnyLiteralSet = new AnyLiteralSet();
  private static final Map<String, Type> AnyMembers = new HashMap<>();

  public final Type Any          = new Type("Any", AnySet);
  public final Type None         = new Type("None", NoneSet);
  public final Type AnyLiteral   = new Type("AnyLiteral",   AnyLiteralSet);


  public Type getType(String typeName) {
    return typeMap.get(typeName);
  }

  public Type constrain(String typeName, String name, TypeSet set) {
    return constrain(getType(typeName), name, set);
  }

  public Type constrain(Type t, String name, TypeSet set) {
    return constrain(t, new Type(name, set));
  }

  public Type constrain(String typeName, String name,  Map<String, String> members) {
    return constrain(getType(typeName), name, members);
  }

  Type constrain(Type t, String name, Map<String, String> members) {
    Type newType = new Type(name, members);

    return constrain(t, newType);
  }

  Type constrain(Type t, Type newType) {

    // check constraints
    register(newType.getName(), newType);
    return newType;
  }

  public Type relax(String typeName, String name, TypeSet set) {
    return relax(getType(typeName), name, set);
  }

  Type relax(Type t, String name, TypeSet set) {
    return relax(t, new Type(name, set));
  }

  public Type relax(String typeName, String name, Map<String, String> members) {
    return relax(getType(typeName), name, members);
  }

  Type relax(Type t, String name, Map<String, String> members) {
    Type newType = new Type(name, members);

    return relax(t, newType);
  }

  Type relax(Type t, Type newType) {

    register(newType.getName(), newType);
    return newType;
  }

  private void register(String name, Type newType) {
    if ( typeMap.containsKey(name) ) {
      throw new RenameException(name + " already exists in this context");
    }
    typeMap.put(name, newType);
  }

  public boolean typeCheck(Type t, String type) {
    return false;
  }

  public void define(String type, String[] ... params) {
    checkTuples(params);
  }

  public void define(String type, Map<String, String> params) {

  }

  public void define(String type, String arrayType) {

  }

  private void checkTuples(String[]... params) {
    for ( String[] t : params ) {
      assert t.length == 2;
    }
  }

  public boolean supertype(Type from, Type to) {
    return false;
  }
}
