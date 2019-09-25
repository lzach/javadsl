package patience.interpreter;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import static java.util.Map.Entry;
import java.util.Set;

public class DSLType {
    private final String name;
    private final Map<String, DSLType> memberTypes;
    
    public DSLType(String name, Map<String, DSLType> members) {
        memberTypes = members;
        this.name = name;
    }
    
    protected void addType(String name, DSLType type) {
      memberTypes.put(name, type);
    }
    
    public String getName() { return name; }
    public DSLType getMemberType(String member) { return memberTypes.get(member); }
    public List<String> getMemberNames() { return new ArrayList<String>(memberTypes.keySet()); }
    public boolean equals(Object o) {
      if ( ! (o instanceof DSLType) ) return false;
      DSLType t = (DSLType)o;
      return t.getName().equals(getName());
    }
}

class MethodType extends DSLType {
    private final Map<Integer, String> argIndeces;

    public MethodType(DSLType returnType, Map<String, DSLType> args) {
      super(createName(returnType, args), createMembers(returnType, args));
      argIndeces = new HashMap<Integer, String>();
      Set<Entry<String, DSLType>> types = args.entrySet();
      int i = 0;
      for ( String name : args.keySet() ) {
        argIndeces.put(i, name);
        ++i;
      }
    }

    public MethodType(DSLType returnType, Map<String, DSLType> args, Map<Integer, String> argIndeces) {
      super(createName(returnType, args), createMembers(returnType, args));
      this.argIndeces = argIndeces;
    }

    public MethodType(DSLType returnType, Map<String, DSLType> args, String[] arglist) {
      super(createName(returnType, args), createMembers(returnType, args));
      argIndeces = new HashMap<Integer, String>();
      for ( int i = 0; i < arglist.length; ++i ) {
        argIndeces.put(i, arglist[i]);
      }
    }

    private static String createName(DSLType returnType, Map<String, DSLType> args) {
      String name = returnType.getName() + "(";
      Set<Entry<String, DSLType>> types = args.entrySet();
      int i = 0;
      for ( Entry<String, DSLType> type : types ) {
        name += type.getValue().getName();
        if ( i != types.size() - 1 ) name += ",";
        ++i;
      }
      name += ")";
      return name;
    }

    private static Map<String, DSLType> createMembers(DSLType returnType, Map<String, DSLType> args) {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.putAll(args);
      members.put("$return", returnType);
      return members;
    }

    public DSLType getReturnType() {
      return getMemberType("$return");
    }

    public String getArgumentName(int index) {
      return argIndeces.get(index);
    }
}

class intType extends DSLType  {
    private static final String name = "int";
    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    public intType() { super(name, members); }
}

class booleanType extends DSLType  {
    private static final String name = "Bool";
    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    public booleanType() { super(name, members); }
}
class voidType extends DSLType  {
    private static final String name = "void";
    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    public voidType() { super(name, members); }
}
//class VoidType extends DSLType  {
 //   private static final String name = "Void";
//    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
//    public VoidType() { super(name, members); }
//}

class objectType extends DSLType  {
    private static final String name = "Object";
    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    public objectType() { super(name, members); }
}

//class ObjectType extends DSLType  {
 //   private static final String name = "Object";
 //   private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
 //   public ObjectType() { super(name, members); }
//}

class LessType extends MethodType {
   private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    static {
        members.put("left", new intType());
        members.put("right", new intType());
    }
    public LessType() { super(new booleanType(), members, new String[]{"left", "right"}); } 
}

class GreaterType extends MethodType {
   private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    static {
        members.put("left", new intType());
        members.put("right", new intType());
    }
    public GreaterType() { super(new booleanType(), members, new String[]{"left", "right"}); } 
}
class LessEqualType extends MethodType {
   private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    static {
        members.put("left", new intType());
        members.put("right", new intType());
    }
    public LessEqualType() { super(new booleanType(), members, new String[]{"left", "right"}); } 
}

class GreaterEqualType extends MethodType {
   private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    static {
        members.put("left", new intType());
        members.put("right", new intType());
    }
    public GreaterEqualType() { super(new booleanType(), members, new String[]{"left", "right"}); } 
}
class EqualType extends MethodType {
   private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    static {
        members.put("left", new intType());
        members.put("right", new intType());
    }
    public EqualType() { super(new booleanType(), members, new String[]{"left", "right"}); } 
}

class NotEqualType extends MethodType {
   private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    static {
        members.put("left", new intType());
        members.put("right", new intType());
    }
    public NotEqualType() { super(new booleanType(), members, new String[]{"left", "right"}); } 
}

class OrType extends MethodType {
   private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    static {
        members.put("left", new booleanType());
        members.put("right", new booleanType());
    }
    public OrType() { super(new booleanType(), members, new String[]{"left", "right"}); } 
}

class AndType extends MethodType {
   private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    static {
        members.put("left", new booleanType());
        members.put("right", new booleanType());
    }
    public AndType() { super(new booleanType(), members, new String[]{"left", "right"}); } 
}

class NotType extends MethodType {
   private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    static {
        members.put("operand", new booleanType());
    }
    public NotType() { super(new booleanType(), members); } 
}


class ExpressionType extends DSLType {
    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    public ExpressionType(DSLType rettype, Map<String, DSLType> provides) { super(createName(rettype), provides); }
    public ExpressionType(DSLType rettype) { super(createName(rettype), members); }

    private static String createName(DSLType returnType) {
      return returnType.getName() + "Expression";
    }
}

class ListAllExpressionType extends ExpressionType {

    public ListAllExpressionType(DSLType type) { super(new booleanType(), createMembers(type)); }
    private static Map<String, DSLType> createMembers(DSLType type) {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("current", type);
      members.put("prev", type);
      members.put("next", type);
      return members;
    }
}

class ListExistExpressionType extends ExpressionType {

    public ListExistExpressionType(DSLType type) { super(new booleanType(), createMembers(type)); }
    private static Map<String, DSLType> createMembers(DSLType type) {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("current", type);
      members.put("prev", type);
      members.put("next", type);
      return members;
    }
}

class ListAtType extends MethodType {
    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    static {
        members.put("index", new intType());
    }
    public ListAtType(DSLType type) { super(type, members); }
}


class ListTopType extends MethodType {
    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();

    public ListTopType(DSLType type) { super(type, members); }
}

class ListBottomType extends MethodType {
    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();

    public ListBottomType(DSLType type) { super(type, members); }

}
class ListEmptyType extends MethodType {
    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();

    public ListEmptyType() { super(new booleanType(), members); }

}
class ListAddType extends MethodType {
    public ListAddType(DSLType type) { super(new voidType(), createMembers(type)); }

    private static Map<String, DSLType> createMembers(DSLType type) {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("item", type);
      return members;
    }

}
class ListContainsType extends MethodType {
    public ListContainsType(DSLType type) { super(new booleanType(), createMembers(type)); }

    private static Map<String, DSLType> createMembers(DSLType type) {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("item", type);
      return members;
    }
}

class ListAllType extends MethodType {
    public ListAllType(DSLType type) { super(new booleanType(), createMembers(type)); }

    private static Map<String, DSLType> createMembers(DSLType type) {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("predicate", new ListAllExpressionType(type));
      return members;
    }
}

class ListExistType extends MethodType {
    public ListExistType(DSLType type) { super(new booleanType(), createMembers(type)); }

    private static Map<String, DSLType> createMembers(DSLType type) {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("predicate", new ListExistExpressionType(type));
      return members;
    }
}

class ListType extends DSLType {
    private static final Map<String, DSLType> members = new HashMap<String, DSLType>();
    public ListType() { this(new objectType(), members);}
    public ListType(DSLType type) {
      this(type, members);
    }
    public ListType(DSLType type, Map<String, DSLType> members) { 
      this(createName(type), type, members);
    }
    public ListType(String name) {this(name, new objectType());}
    public ListType(String name, DSLType type) { this(name, type, members); }
    public ListType(String name, DSLType type, Map<String, DSLType> members) { 
      super(name, createMembers(type, members));
    }
    
     private static String createName(DSLType type) {
      return "List<" + type.getName() + ">";
    }

    private static Map<String, DSLType> createMembers(DSLType type, Map<String, DSLType> other) {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("at", new ListAtType(type));
      members.put("top", new ListTopType(type));
      members.put("bottom", new ListBottomType(type));
      members.put("empty", new ListEmptyType());
      members.put("contains", new ListContainsType(type));
      members.put("add", new ListAddType(type));
      members.put("all", new ListAllType(type));
      members.put("exist", new ListExistType(type));
      members.putAll(other);
      return members;
    }
}

class OrListType extends MethodType {
    public OrListType() { super(new booleanType(), createMembers()); }

    private static Map<String, DSLType> createMembers() {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      //members.put("list", new OrGuardedExpressionType(type));
      return members;
    }
}
class OrGuardedType extends MethodType {
    public OrGuardedType() { super(new objectType(), createMembers()); }

    private static Map<String, DSLType> createMembers() {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      //members.put("list", new OrGuardedExpressionType(type));
      return members;
    }
}

class RepeatExpressionType extends ExpressionType {
    public RepeatExpressionType() { super(new objectType(), createMembers()); }
    private static Map<String, DSLType> createMembers() {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("counter", new intType());
      return members;
    }
}

class RepeatType extends MethodType {
    public RepeatType() { super(new objectType(), createMembers()); }

    private static Map<String, DSLType> createMembers() {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("times", new intType());
      members.put("statement", new RepeatExpressionType());
      return members;
    }
}

class ForEachExpressionType extends ExpressionType {
    public ForEachExpressionType() { super(new objectType(), createMembers()); }
    private static Map<String, DSLType> createMembers() {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("index", new intType());
      members.put("item", new objectType());
      return members;
    }
}

class ForEachType extends MethodType {
    public ForEachType() { super(new objectType(), createMembers()); }

    private static Map<String, DSLType> createMembers() {
      Map<String, DSLType> members = new HashMap<String, DSLType>();
      members.put("list", new ListType());
      members.put("statement", new ForEachExpressionType());
      return members;
    }
}
