package dsl.definitions;

import dsl.Context;

public class Type {
  public static Context getContext() {
    Context ctx = new Context();
    ctx.define("MemberPair", new String[]{"name", "String"}, new String[]{"type", "Type"});
    ctx.define("MemberPairList", "MemberPair");
    ctx.define("Type",new String[]{"name", "String"}, new String[]{"members", "MemberPairList"});
    ctx.define("Type", "Any");

    ctx.define("Relation", new String[]{"from", "Type"}, new String[]{"to", "Type"});
    return ctx;
  }
}