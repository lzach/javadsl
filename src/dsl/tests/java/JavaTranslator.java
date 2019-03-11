package dsl.tests.java;

import dsl.ast.AST;
import dsl.translators.StringTranslator;

public class JavaTranslator implements StringTranslator {
  @Override
  public String translate(AST ast) {
    return translate(ast, ast.getTypeName());
  }

  public String translate(AST ast, String enforcedType) {
    String str = "";
    if ( enforcedType == null ) {
      return "";
    }
    switch (enforcedType) {
      case "Import":
        str = "import ";

        return "import " + translate(ast.get("name"));
      case "Name":
        if ( ast.getMemberList().length > 0 ) {
          for (AST child : ast.getMemberList()) {
            str += translate(child) + ".";
          }
          str = str.substring(0, str.length() - 1);
        }
        return str;
      case "Class":
        if ( ast.hasMember("modifier")) {
          str = translate(ast.get("modifier")) + " ";
        }
        str += "class " + translate(ast.get("name"));
        if ( ast.hasMember("base") ) {
          str += " extends " + translate(ast.get("base"));
        }
        str += " {\n" + translate(ast.get("attrs"), "AttrList") ;
        if ( ast.hasMember("cons")) {
          for (AST child : ast.get("cons").getMemberList()) {
             str +=  "public " + translate(ast.get("name"))+ "("+ translate(child.get("params"), "ParamList") + ") " + translate(child.get("code"));
          }
        }

        str += translate(ast.get("methods"), "MethodList") + "}\n";
        return str;
      case "Member":
        return translate(ast.get("lhs")) + "." + translate(ast.get("rhs"));
      case "Call":
        return translate(ast.get("function")) + "(" + translate(ast.get("args"), "ArgList") + ")";
      case "Method":
        return "public " + translate(ast.get("returnType")) + " " + translate(ast.get("name")) + "(" +  translate(ast.get("params"), "ParamList") + ") " + translate(ast.get("code"));
      case "Constructor":
        // NOTE: we can't translate constructors outside of the Class node, as we require the name of the class to do it
	      return " ";
      case "Attr":
      case "Define":
        return translate(ast.get("type")) + " " + translate(ast.get("name"));
      case "Param":
        return translate(ast.get("type")) + " " + translate(ast.get("name"));
      case "Arg":
        return translate(ast.get("value"));
      case "ParamList":
      case "ArgList":
      	//HACK: Dirty trick to get type enforcing working for now:
        if ( !ast.isList() ) {
                return translate(ast);
        }
        // TODO: here we need to make sure the arguments/params end up in the right order.
        if ( ast.getMemberList().length > 0 ) {
          for (AST child : ast.getMemberList()) {
            str += translate(child) + ",";
          }
          str = str.substring(0, str.length() - 1);
        }
        return str;

      case "AttrList":
        if ( ast.getMemberList().length > 0 ) {
          for (AST child : ast.getMemberList()) {
            str += translate(child) + ";\n";
          }
          str = str.substring(0, str.length() - 1);
        }
        return str;
      case "MethodList":
        if ( ast.getMemberList().length > 0 ) {
          for (AST child : ast.getMemberList()) {
            str += translate(child) + "\n";
          }
          str = str.substring(0, str.length() - 1);
        }
        return str;
      case "New":
        str = "new " +translate(ast.get("type")) + "(" + translate(ast.get("args")) + ")";
        if ( ast.hasMember("body")) {
          str += translate(ast.get("body"));
        }
        return str;
      case "Convert":
        return "((" + translate(ast.get("type")) + ")(" + translate(ast.get("value")) + "))";
      case "Index":
        return "((" + translate(ast.get("lhs")) + ")[" + translate(ast.get("rhs")) + "])";
      case "Select":
        return "switch (" + translate(ast.get("value")) + ") {\n" + translate(ast.get("block")) + "}\n";
      case "Case":
        return "case " + translate(ast.get("value")) + ":\n" + translate(ast.get("block")) + "\n";
      case "Default":
        return "default:\n" + translate(ast.get("block")) + "\n";
      case "Assign":
        return translate(ast.get("lhs")) + " = " + translate(ast.get("rhs"));
      case "Return":
        return "return " + translate(ast.get("value")) ;
      case "For":
        str = "for (";
        if ( ast.hasMember("var") ) {
          str += translate(ast.get("var")) + " : " + translate(ast.get("expr"));
        } else {
          str += translate(ast.get("init")) + ";" + translate(ast.get("cond")) + ";" + translate(ast.get("post"));
        }
        return str + ")" + translate(ast.get("code"));
      case "If":
        return "if (" + translate(ast.get("cond")) + ")" + translate(ast.get("code")) + ( ast.hasMember("otherwise") ? " else " + translate(ast.get("otherwise")) : "");
      case "While":
        return "while (" + translate(ast.get("cond")) + ")" + translate(ast.get("code"));
      case "Block":
        str = "{\n";
        if ( ast.getMemberList().length > 0 ) {
          for (AST child : ast.getMemberList()) {
            str += translate(child) + ";\n";
          }
        }
        return str + "}\n";
      case "List":
        if ( ast.isList() && ast.getMemberList().length > 0 ) {
          for (AST child : ast.getMemberList()) {
            str += translate(child) + ";\n";
          }
        }
        if ( str.length() > 0 ) {
                str = str.substring(0, str.length() - 1);
        }
        return str;
      case "Eq":
        return "Objects.equals(" + translate(ast.get("lhs")) + ", " + translate(ast.get("rhs")) + ")";
      case "Neq":
        return translate(ast.get("lhs")) + "!=" + translate(ast.get("rhs"));
      case "Add":
        return translate(ast.get("lhs")) + "+" + translate(ast.get("rhs"));
      case "Sub":
        return translate(ast.get("lhs")) + "-" + translate(ast.get("rhs"));
      case "Mul":
        return translate(ast.get("lhs")) + "*" + translate(ast.get("rhs"));
      case "Div":
        return translate(ast.get("lhs")) + "/" + translate(ast.get("rhs"));
      case "IntLit":
        return ast.getValue().toString();
      case "IDLit":
        return ast.getValue().toString();
      case "String":
        if ( ast.isMembers() ) {
          return "\"" + translate(ast.get("value")) + "\"";
        }
        return "\"" + ast.getValue() + "\"";
      case "ID":
        return ast.getValue().toString();

    }
    return "";
  }
}
