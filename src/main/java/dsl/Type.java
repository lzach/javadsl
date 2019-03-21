package dsl;

import dsl.typeset.TypeSet;

import java.util.Map;

public class Type {
  private final String name;
  private final TypeSet set;
  private final Map<String, String> members;
  private final String arrayType;


  Type(String name, Map<String, String> members) {
    this.name = name;
    this.members = members;
    this.set = null;
    arrayType = null;
  }

  public Type(String name, String arrayType) {
    this.name = name;
    this.arrayType = arrayType;
    set = null;
    members = null;
  }

  public Type(String name, TypeSet set) {
    this.name = name;
    this.set = set;
    members = null;
    arrayType = null;
  }

  public String getName() {
    return name;
  }


  public TypeSet getSet() {
    return set;
  }

  public Map<String, String> getMembers() {
    return members;
  }

  public String getMember(String member) {
    return members.get(member);
  }

  public String getArrayType() {
    return arrayType;
  }

  public boolean isArray() {
    return getArrayType() != null;
  }

  public boolean contains(Object value) {
    return false;
  }
}
